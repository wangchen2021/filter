<template>
  <div class="pup-back">
    <div class="self-pup pupsize">
      <div>
        <div class="pup-title">Microsrip Line Modal</div>
        <a-icon type="close" class="pup-close" @click="closePup" />
      </div>
      <a-spin :spinning="spinning">
        <a-tooltip>
          <template slot="title"> Substrate thickness </template>
          <div class="input-item">
            <p>d</p>
            <a-input v-model="d"></a-input>
            <a-select default-value="m" class="select" @change="handleChange">
              <a-select-option :value="0.001"> mm </a-select-option>
              <a-select-option :value="0.01"> cm </a-select-option>
              <a-select-option :value="1"> m </a-select-option>
            </a-select>
          </div>
        </a-tooltip>
        <a-tooltip>
          <template slot="title"> Relative dielectric constant </template>
          <div class="input-item">
            <p>εr</p>
            <a-input v-model="DieConst"></a-input>
          </div>
        </a-tooltip>
        <div class="input-item">
          <a-button @click="generate" type="primary">Generate</a-button>
        </div>
        <div class="modal-show" v-if="modalShow">
          <div class="Microstrip-modal">
            <div class="modal-section2"></div>
            <div
              :class="index % 2 == 0 ? 'modal-section1' : 'modal-section2'"
              v-for="(item, index) in Warray"
              :key="index"
            >
              <div class="section-name">step:{{ index + 1 }}</div>
            </div>
            <div class="modal-section3"></div>
          </div>
          <a-table
            :columns="columns"
            :data-source="data"
            :pagination="false"
            v-if="tableShow"
          >
            <a slot="value" slot-scope="text">{{ text }}</a>
          </a-table>
          <div class="select-unit">
            <div style="display: inline-block">Length Unit:</div>
            <a-select default-value="m" @change="handleChange2" class="unit">
              <a-select-option :value="0.001"> mm </a-select-option>
              <a-select-option :value="0.01"> cm </a-select-option>
              <a-select-option :value="1"> m </a-select-option>
            </a-select>
          </div>
        </div>
      </a-spin>
    </div>
  </div>
</template>
<script>
import { getAction } from "../../../api/axiosmethod";
export default {
  name: "StepImpMicrostrip",
  data() {
    return {
      d: "",
      DieConst: "",
      dunit: "",
      Warray: [],
      Larray: [],
      spinning: false,
      tableShow: true,
      modalShow: false,
      columns: [
        {
          title: "Microstrip line value",
          dataIndex: "value",
          key: "value",
          scopedSlots: { customRender: "value" },
        },
      ],
      data: [
        {
          key: "1",
          value: "Width(Wi)",
        },
        {
          key: "2",
          value: "Length(Li)",
        },
      ],
    };
  },
  props: ["DesignParam", "filterParam"],
  methods: {
    closePup() {
      this.$emit("closePup");
    },
    handleChange(value) {
      console.log(`selected ${value}`);
      this.dunit = value;
    },
    handleChange2(value) {
      console.log(value);
      for (let i = 0; i < this.Larray.length; i++) {
        let j = i + 1;
        let name = "Step" + j;
        this.data[0][name] = (Number(this.Warray[i]) / value).toPrecision(3);
        this.data[1][name] = (Number(this.Larray[i]) / value).toPrecision(3);
      }
      this.tableShow = false;
      this.$nextTick(() => {
        this.tableShow = true;
      });
    },
    generate() {
      console.log(this.DesignParam, this.filterParam);
      if (this.d == "" || this.DieConst == "") {
        this.$message.warning("Please enter complete parameters！");
      } else {
        this.spinning = true;
        this.modalShow = false;
        this.Larray = [];
        this.Warray = [];
        this.columns = [
          {
            title: "Microstrip line value",
            dataIndex: "value",
            key: "value",
            scopedSlots: { customRender: "value" },
          },
        ];
        this.data = [
          {
            key: "1",
            value: "Width(Wi)",
          },
          {
            key: "2",
            value: "Length(Li)",
          },
        ];
        let url = "app/filterDesign/steppedImpedance/getmicrostrip";
        let BL = this.DesignParam.BeltaTimesL.join(",");
        let ZArray = this.DesignParam.ZArray.join(",");
        let param = {
          d: Number(this.d) * this.dunit,
          DieConst: this.DieConst,
          ZArray: ZArray,
          BL: BL,
          fc: Number(this.filterParam.Wc) * this.filterParam.units.WcUnit,
        };
        getAction(url, param)
          .then((res) => {
            console.log(res);
            for (let i = 0; i < res.data.W.length; i++) {
              this.Warray[i] = Number(res.data.W[i]).toPrecision(3);
              this.Larray[i] = Number(res.data.lengthArray[i]).toPrecision(3);
            }
            console.log(this.Warray, this.Larray, "222");
            for (let i = 0; i < this.Warray.length; i++) {
              let j = i + 1;
              let name = "Step" + j;
              let obj = {
                title: name,
                dataIndex: name,
                key: name,
              };
              this.columns.push(obj);
              this.data[0][name] = this.Warray[i];
              this.data[1][name] = this.Larray[i];
            }
            this.$nextTick(() => {
              this.modalShow = true;
            });

            this.spinning = false;
          })
          .catch((err) => {
            console.log(err);
            this.spinning = false;
          });
      }
    },
  },
};
</script>
<style scoped>
.pupsize {
  width: 70%;
  height: 70%;
}
.input-item {
  display: inline-block;
  width: 150px;
  margin-top: 20px;
  margin-left: 100px;
}
.input-item > p {
  display: inline-block;
  position: relative;
  top: 50px;
  left: -30px;
  font-size: 20px;
  color: rgb(50, 115, 201);
}
.select {
  position: absolute;
  left: 250px;
}
.modal-show {
  padding: 20px;
  margin-top: 35px;
  position: relative;
  width: 100%;
  height: 100px;
  border-top: 1px solid #91d5ff;
  text-align: center;
  transition: all 0.7s;
}
.modal-section1 {
  width: 100px;
  height: 70px;
  display: inline-block;
  margin-top: 40px;
  margin-bottom: 100px;
  transition: all 0.7s;
  vertical-align: middle;
  background-color: rgb(45, 144, 224);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.modal-section2 {
  width: 100px;
  height: 25px;
  display: inline-block;
  margin-top: 40px;
  margin-bottom: 100px;
  vertical-align: middle;
  transition: all 0.7s;
  background-color: rgb(45, 144, 224);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.modal-section1:hover {
  transform: scale(1.2);
}
.modal-section2:hover {
  transform: scale(1.2);
}
.section-name {
  color: rgb(255, 255, 255);
  font-size: 20px;
}
.modal-section3 {
  width: 100px;
  height: 50px;
  display: inline-block;
  margin-top: 40px;
  margin-bottom: 100px;
  vertical-align: middle;
  transition: all 0.7s;
  background-color: rgb(45, 144, 224);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
.select-unit {
  position: relative;
  text-align: center;
  width: 220px;
  top: 20px;
  right: -1050px;
}
.unit {
  margin-left: 10px;
}
</style>