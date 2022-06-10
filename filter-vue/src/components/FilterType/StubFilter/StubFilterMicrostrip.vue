<template>
  <div>
    <div class="pup-back">
      <div class="self-pup pupsize">
        <div>
          <div class="pup-title">Microsrip Line Modal</div>
          <a-icon type="close" class="pup-close" @click="closePup" />
          <div class="Basic-stub-model">
            <div class="fistModel">
              <div class="card_title">Koloda Identity Transformation</div>
              <div class="section">
                <p>Z={{ Z0 }}Ω</p>
                <img class="source" src="../../../media/imags/Source.png" />
              </div>
              <div
                class="section"
                v-for="(item, index) in Sections"
                :key="index"
              >
                <p :class="index != SectionsLength - 1 ? 'ptip' : 'ptip up'">
                  Z{{ 2 * index + 1 }} = {{ Number(item).toPrecision(4) }}Ω
                </p>
                <p class="stip" v-if="index != SectionsLength - 1">
                  Z{{ 2 * index + 2 }} =
                  {{
                    Number(DesignParam.ZArray[2 * index + 1]).toPrecision(4)
                  }}Ω
                </p>
                <img
                  class="Length"
                  v-if="index != SectionsLength - 1"
                  src="../../../media/imags/length.png"
                />
                <img class="PLine" src="../../../media/imags/PLine.png" />
              </div>
              <div class="section">
                <p>Z={{ Z0 }}Ω</p>
                <img class="end" src="../../../media/imags/endImp.png" />
              </div>
              <div class="Lstyle">
                L(length)={{ DesignParam.L }}m at ω={{ Wc }}GHz
              </div>
            </div>
          </div>
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
          <div v-if="showFinalModal">
            <div class="title">Final modal</div>
            <div class="final-modal">
              <div class="fmstart section1">
                <p class="terminate">
                  Z0={{ Number(filterParam.R) * filterParam.units.R0Unit }}Ω
                </p>
                <img src="../../../media/imags/StubMicroStripStart.png" />
              </div>
              <div
                v-for="(item, index) in DesignParam.ZArray"
                :key="index"
                class="section1 fm"
              >
                <p :class="index % 2 == 0 ? 'sectionZ down' : 'sectionZ'">
                  Z{{ index + 1 }}={{ Number(item).toPrecision(4) }}Ω
                </p>
                <img
                  v-if="index % 2 == 0"
                  src="../../../media/imags/StubPMicroSrip.png"
                />
                <div class="SMS" v-if="index % 2 == 1"></div>
              </div>
              <div class="fmstart section1 Pmend">
                <p class="terminate1">
                  Z0={{ Number(filterParam.R) * filterParam.units.R0Unit }}Ω
                </p>
                <img src="../../../media/imags/StubMicroStripEnd.png" />
              </div>
              <a-button
                @click="showParamTable"
                type="primary"
                class="showDataTable"
                >Show Param Table</a-button
              >
            </div>
          </div>
        </a-spin>
      </div>
    </div>
    <div v-if="showTable" class="paramTable">
      <a-icon
        type="close"
        class="pup-close"
        @click="showTable = false"
      /><a-table
        v-if="tableShow"
        :pagination="false"
        :columns="columns"
        :data-source="Tabledata"
        class="table"
      >
        <a slot="name" slot-scope="text">{{ text }}</a>
      </a-table>
      <p class="tip_LU">Length Unit:</p>
      <a-select
        default-value="m"
        class="selectLengthUnit"
        @change="handleChangeLengthUnit"
      >
        <a-select-option :value="0.001"> mm </a-select-option>
        <a-select-option :value="0.01"> cm </a-select-option>
        <a-select-option :value="1"> m </a-select-option>
      </a-select>
    </div>
  </div>
</template>
<script>
import { getAction } from "../../../api/axiosmethod";
import { drag } from "../../../Tools/drag";
export default {
  name: "StubFilterMicrostrip",
  props: ["DesignParam", "filterParam"],
  data() {
    return {
      Slength: "",
      Sections: [],
      SectionsLength: 0,
      spinning: false,
      d: "",
      tableShow: true,
      dUnit: 1,
      DieConst: "",
      dunit: "",
      Z0: 0,
      Wc: "",
      showTable: false,
      showFinalModal: false,
      result: {},
      columns: [
        {
          title: "Name",
          dataIndex: "name",
          scopedSlots: { customRender: "name" },
        },
        {
          title: "Impedance",
          dataIndex: "Impedance",
        },
        {
          title: "Width",
          dataIndex: "Width",
        },
        {
          title: "Length",
          dataIndex: "Length",
        },
      ],
      Tabledata: [],
    };
  },
  methods: {
    closePup() {
      this.$emit("closeMicroSrtip");
    },
    handleChange(val) {
      console.log(val);
      this.dUnit = val;
    },
    showParamTable() {
      this.showTable = true;
      this.$nextTick(() => {
        console.log(document.getElementsByClassName("paramTable")[0]);
        drag(document.getElementsByClassName("paramTable")[0]);
      });
    },
    handleChangeLengthUnit(val) {
      for (let i = 0; i < this.Tabledata.length; i++) {
        this.Tabledata[i].Width = (Number(this.result.W[i]) / val).toPrecision(
          3
        );
        this.Tabledata[i].Length = (
          Number(this.result.lengthArray[i]) / val
        ).toPrecision(3);
      }
      this.tableShow = false;
      this.$nextTick(() => {
        this.tableShow = true;
      });
    },
    generate() {
      if (this.d == "" || this.DieConst == "") {
        this.$message.warning("Please complete the information");
      } else {
        this.spinning = true;
        let url = "app/filterDesign/StubFilter/getmicrostrip";
        let param = {
          DieConst: this.DieConst,
          d: Number(this.d) * this.dUnit,
          Fc: this.DesignParam.getParamWc,
          ZArray: this.DesignParam.ZArray.join(","),
          R0: Number(this.filterParam.R) * this.filterParam.units.R0Unit,
        };
        getAction(url, param)
          .then((res) => {
            console.log(res, "res");
            for (let i = 0; i < res.data.ZArray.length; i++) {
              this.Tabledata[i] = {};
              Object.assign(this.Tabledata[i], { name: `Section ${i + 1}` });
              Object.assign(this.Tabledata[i], { key: i.toString() });
              Object.assign(this.Tabledata[i], {
                Impedance: Number(res.data.ZArray[i]).toPrecision(3) + "Ω",
              });
              Object.assign(this.Tabledata[i], {
                Width: Number(res.data.W[i]).toPrecision(3),
              });
              Object.assign(this.Tabledata[i], {
                Length: Number(res.data.lengthArray[i]).toPrecision(3),
              });
            }
            this.result = res.data;
            this.spinning = false;
            this.showFinalModal = true;
          })
          .catch((err) => {
            console.warn(err);
            this.spinning = false;
          });
      }
    },
  },
  created() {
    this.Slength = this.DesignParam.ElementsValue.length;
    for (let i = 0; i < this.DesignParam.ZArray.length; i++) {
      if (i % 2 == 0) {
        this.Sections.push(this.DesignParam.ZArray[i]);
      }
    }
    this.SectionsLength = this.Sections.length;
    console.log(this.filterParam, this.DesignParam, "1111111");
    this.Z0 = this.filterParam.R;
    this.Wc = this.filterParam.Wc;
  },
};
</script>
<style scoped>
.tip_LU {
  position: relative;
  top: 20px;
}
.selectLengthUnit {
  position: relative;
  top: -20px;
  left: 90px;
}
.sectionZ {
  font-size: 15px;
  position: relative;
}
.down {
  top: 190px;
}
.terminate {
  font-size: 19px;
}
.table {
  margin-top: 40px;
}
.terminate1 {
  font-size: 19px;
  position: relative;
  left: 95px;
}
.showDataTable {
  position: absolute;
  top: 270px;
  right: 100px;
}
.SMS {
  display: inline-block;
  width: 270px;
  height: 20px;
  background-color: rgb(101, 152, 219);
  position: relative;
  left: -80px;
}
.fmstart {
  display: inline-block;
  position: relative;
  left: -60px;
}
.fmstart > img {
  width: 220px;
  height: 40px;
  margin-top: -34px;
}
.fm > img {
  width: 40px;
  height: 130px;
  vertical-align: -130px;
}
.title {
  font-size: 20px;
  text-align: center;
  margin-top: 60px;
  color: black;
}
.final-modal {
  transform: scale(0.8);
  text-align: center;
  margin-top: 10px;
  width: 100%;
  height: 200px;
}
.pupsize {
  width: 1100px;
  height: 900px;
}
.Basic-stub-model {
  text-align: center;
}
.section {
  margin-top: 30px;
  display: inline-block;
  width: 120px;
  height: 100px;
  transition: all 0.6s;
}
.section1 {
  margin-top: 30px;
  display: inline-block;
  width: 80px;
  height: 80px;
  transition: all 0.6s;
}
.fistModel {
  display: inline-block;
  padding: 25px;
  transition: all 0.6s;
}
.Length {
  position: relative;
  width: 80px;
  top: 55px;
  left: 60px;
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
.end {
  width: 120px;
  height: 102px;
  border-left: 1px dotted rgba(0, 0, 0, 0.329);
  padding-left: 15px;
  vertical-align: -52px;
  margin-left: 15px;
}
.select {
  position: absolute;
  left: 250px;
}
.source {
  width: 120px;
  height: 108px;
  vertical-align: -52px;
  border-right: 1px dotted rgba(0, 0, 0, 0.329);
  padding-right: 5px;
}
.SLine {
  height: 180px;
  vertical-align: -50px;
}
.Lstyle {
  position: relative;
  left: -320px;
  top: -170px;
  font-size: 17px;
  margin-bottom: 10px;
}
.PLine {
  height: 140px;
  width: 130px;
  vertical-align: -98px;
}
.ptip {
  position: relative;
  top: 230px;
  left: -80px;
  /* z-index: 999; */
}
.stip {
  position: relative;
  left: 60px;
  top: 50px;
}
.up {
  top: 152px;
}
.Pmend {
  padding-right: 50px;
  position: relative;
  right: 80px;
}
.paramTable {
  position: absolute;
  top: 250px;
  left: 800px;
  background-color: rgb(255, 255, 255);
  border-radius: 10px;
  padding: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>