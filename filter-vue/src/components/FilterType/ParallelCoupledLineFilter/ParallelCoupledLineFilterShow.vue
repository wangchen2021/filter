<template>
  <div>
    <div class="self-card card-size">
      <div class="card_title">Param of Filter</div>
      <a-table :columns="columns" :data-source="Tabledata" :pagination="false">
        <a slot="Step" slot-scope="text">{{ text }}</a>
      </a-table>
    </div>
    <div class="self-card modal">
      <div class="card_title">Modal of Filter</div>
      <div class="section">
        <p class="Z0Top">Z0={{ filterParam.R }}Ω</p>
        <img
          src="../../../media/imags/ParallelCoupledLineFilter.png"
          alt="error"
        />
        <p class="Z0">
          Z0e={{ Tabledata[0].Z0e }}Ω; Z0o={{ Tabledata[0].Z0o }}Ω
        </p>
      </div>
      <div class="section" v-for="(item, index) of sections" :key="index">
        <img
          src="../../../media/imags/ParallelCoupledLineFilter.png"
          alt="error"
        />
        <p class="Z0">
          Z0e={{ Tabledata[index + 1].Z0e }}Ω; Z0o={{
            Tabledata[index + 1].Z0o
          }}Ω
        </p>
      </div>
      <div class="section">
        <img
          src="../../../media/imags/ParallelCoupledLineFilter.png"
          alt="error"
        />
        <p class="Z0">Z0={{ filterParam.R }}Ω</p>
      </div>
      <div class="tip">All line is λ/4</div>
      <div class="tip">
        The attenuation at {{ filterParam.Fl
        }}{{ filterParam.units.WcUnit == 1000000000 ? "GHz" : "Hz" }} is
        {{ Number(DesignParam.loss).toPrecision(3) }}dB
      </div>
    </div>
    <div class="self-card MLM-card">
      <a-tooltip>
        <template slot="title"> Substrate thickness </template>
        <div class="input-item">
          <p class="input-title">d</p>
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
          <p class="input-title">εr</p>
          <a-input v-model="DieConst"></a-input>
        </div>
      </a-tooltip>
      <a-button @click="Generate()" class="MLMbutton" type="primary"
        >Generate</a-button
      >
    </div>
    <div class="self-card"></div>
  </div>
</template>

<script>
import { getAction } from "../../../api/axiosmethod";
import { drag } from "../../../Tools/drag";
export default {
  props: ["filterParam", "DesignParam"],
  data() {
    return {
      dUnit: 1,
      d: "",
      DieConst: "",
      sections: [],
      columns: [
        {
          title: "Step",
          dataIndex: "Step",
          key: "Step",
          scopedSlots: { customRender: "Step" },
        },
        {
          title: "RN",
          dataIndex: "RN",
          key: "RN",
        },
        {
          title: "Z0Jn",
          dataIndex: "Z0Jn",
          key: "Z0Jn",
        },

        {
          title: "Z0o",
          dataIndex: "Z0o",
          key: "Z0o",
          ellipsis: true,
        },
        {
          title: "Z0e",
          dataIndex: "Z0e",
          key: "Z0e",
          ellipsis: true,
        },
      ],

      Tabledata: [
        // {
        //   key: "1",
        //   Step: "S1",
        //   Z0Jn: 32,
        //   RN: 30,
        //   Z0o: 30,
        //   Z0e: 30,
        // },
      ],
    };
  },
  methods: {
    handleChange(value) {
      this.dUnit = value;
    },
    Generate() {
      if (this.d != "" && this.DieConst != "") {
        let url =
          "app/filterDesign/ParallelCoupledLineFilter/MicrostripLineModal";
        let param = {
          d: Number(this.d) * this.dUnit,
          Er: Number(this.DieConst),
          Fc: Number(this.filterParam.Wc) * this.filterParam.units.WcUnit,
          Z0o: this.DesignParam.Z0o.join(","),
          Z0e: this.DesignParam.Z0e.join(","),
        };
        getAction(url, param)
          .then((res) => {
            console.log(res);
          })
          .catch((err) => {
            console.log(err);
          });
      } else {
        this.$message.warning("Please input all value!");
      }
    },
  },
  created() {
    console.log(this.filterParam, this.DesignParam);
    for (let i = 0; i < this.DesignParam.Z0Jn.length; i++) {
      let line = {
        key: `${i}`,
        Step: `S${i + 1}`,
        Z0Jn: Number(this.DesignParam.Z0Jn[i]).toPrecision(3),
        RN: this.DesignParam.Elements[i],
        Z0o: Number(this.DesignParam.Z0o[i]).toPrecision(3),
        Z0e: Number(this.DesignParam.Z0e[i]).toPrecision(3),
      };
      this.Tabledata.push(line);
    }
    this.sections = [...this.DesignParam.Z0Jn];
    this.sections.pop();
  },
  mounted() {
    let sections = document.getElementsByClassName("section");
    let leftPx = 0;
    for (let item of sections) {
      item.style.left = leftPx + 50 + "px";
      leftPx = leftPx + 50;
    }
    document.getElementsByClassName("modal")[0].style.width =
      leftPx + 280 + "px";
    let cards = document.getElementsByClassName("self-card");
    for (let item of cards) {
      drag(item);
    }
  },
};
</script>

<style scoped>
.card-size {
  width: 600px;
  padding: 30px;
  position: absolute;
  top: 100px;
  left: 50px;
}
.modal {
  position: absolute;
  top: 100px;
  left: 750px;
  margin: 20px;
  padding: 20px;
}
.input-item {
  display: inline-block;
  width: 150px;
  margin-top: 20px;
  margin-left: 100px;
}
img {
  width: 140px;
  height: 30px;
}
.Z0 {
  display: inline;
  margin-left: 10px;
}
.Z0Top {
  position: absolute;
  left: -50px;
}
.section {
  position: relative;
}
.tip {
  text-align: center;
  font-size: 16px;
}
.MLM-card {
  position: absolute;
  padding: 20px;
  top: 500px;
  left: 780px;
}
.MLMbutton {
  margin-left: 30px;
}
.select {
  position: absolute;
  top: 73px;
}
.input-title {
  position: relative;
  top: 37px;
  left: -20px;
}
</style>