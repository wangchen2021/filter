<template>
  <div>
    <!-- 数据表(归一个元件值) -->
    <div class="clearTopMargin"></div>
    <div class="standard">
      <div class="self-card table-size">
        <div class="card_title">Data Table[N(Steps)={{ Step }}]</div>
        <div>
          <a-table :columns="columns" :data-source="data" :pagination="false">
            <a slot="gi" slot-scope="text">{{ text }}</a>
            <p slot="βl" slot-scope="text">{{ text }}°</p>
          </a-table>
        </div>
      </div>
    </div>
    <!-- 原型电路展示 -->
    <div class="self-card circuit-show">
      <div class="card_title">Prototype Circuit</div>
      <div class="circuit-component" v-for="(item, index) in data" :key="index">
        <div v-if="index % 2 == 0">
          <div class="tips">{{ index + 1 }}(C={{ item.ComponentValue }})</div>
          <img class="circuit-C" src="../../../media/imags/Cp.png" />
        </div>
        <div v-if="index % 2 == 1">
          <div class="tips">{{ index + 1 }}(L={{ item.ComponentValue }})</div>
          <img class="circuit-L" src="../../../media/imags/Id.png" />
        </div>
      </div>
    </div>
    <!-- //最终模型 -->
    <div class="self-card last-modal">
      <div class="card_title">Final Circuit</div>
      <div class="lost-component">
        <div class="last-tips">Z={{ DesignParam.R0 }}Ω</div>
        <img
          class="final-section"
          src="../../../media/imags/FinalSection.png"
        />
      </div>
      <div class="lost-component" v-for="(item, index) in data" :key="index">
        <div class="last-tips">
          Z{{ index + 1 }}={{ DesignParam.ZArray[index] }}Ω
        </div>
        <img
          class="final-section"
          src="../../../media/imags/FinalSection.png"
        />
      </div>
      <div class="lost-component">
        <div class="last-tips">Z={{ DesignParam.R0 }}Ω</div>
        <img
          class="final-section last-one"
          src="../../../media/imags/FinalSection.png"
        />
      </div>
    </div>
    <NavShow
      @showResults="showResults"
      @showMicrostrip="showMicrostrip"
      @showLearnMore="showLearnMore"
    />
    <StepImpMicrostrip
      v-if="MicrostripShow"
      @closePup="closePup"
      :filterParam="filterParam"
      :DesignParam="DesignParam"
    />
    <SimulationResults @closeResults="closeResults" v-if="SimulationShow" />
    <LearnMore @closeLearnMorePup="closeLearnMorePup" v-if="LearnMoreShow" />
  </div>
</template>

<script>
import NavShow from "../../nav/NavShow.vue";
import StepImpMicrostrip from "./StepImpMicrostrip.vue";
import LearnMore from "./LearnMore.vue";
import SimulationResults from "./SimulationResults.vue";
export default {
  name: "StepImpShow",
  props: ["DesignParam", "filterParam"],
  components: {
    NavShow,
    StepImpMicrostrip,
    LearnMore,
    SimulationResults,
  },
  data() {
    return {
      MicrostripShow: false,
      LearnMoreShow: false,
      SimulationShow: false,
      Step: 0,
      columns: [
        {
          title: "gi",
          dataIndex: "gi",
          key: "gi",
          scopedSlots: { customRender: "gi" },
        },
        {
          title: "Component value",
          dataIndex: "ComponentValue",
          key: "ComponentValue",
          width: 110,
        },
        {
          title: "β*l",
          dataIndex: "EleLength",
          key: "EleLength",
          scopedSlots: { customRender: "βl" },
          //   width: 80,
        },
        // {
        //   title: "L",
        //   dataIndex: "Length",
        //   key: "Length",
        //   //   width: 80,
        // },
      ],
      data: [
        // {
        //   key: "1",
        //   gi: "John Brown",
        //   ComponentValue: 32,
        // },
        // {
        //   key: "2",
        //   gi: "Jim Green",
        //   ComponentValue: 42,
        // },
      ],
    };
  },
  created() {
    console.log(this.filterParam, "filterParam");
    let Array = this.DesignParam.CircuitValue;
    this.Step = this.DesignParam.steps;
    for (let i = 1; i < Array.length; i++) {
      this.data[i - 1] = {
        key: i,
        gi: "g" + i,
        ComponentValue: this.DesignParam.CircuitValue[i - 1],
        EleLength: this.DesignParam.BeltaTimesL[i - 1],
      };
    }
  },
  methods: {
    showResults() {
      this.SimulationShow = true;
    },
    showMicrostrip() {
      this.MicrostripShow = true;
    },
    showLearnMore() {
      console.log("111");
      this.LearnMoreShow = true;
    },
    closePup() {
      this.MicrostripShow = false;
    },
    closeLearnMorePup() {
      this.LearnMoreShow = false;
    },
    closeResults() {
      console.log(11);
      this.SimulationShow = false;
    },
  },
};
</script>

<style scoped>
.standard {
  display: inline-block;
  height: 500px;
}
.clearTopMargin {
  height: 70px;
  /* margin-top: -100px; */
}
.table-size {
  display: inline-block;
  /* margin-top: 20px; */
  margin-left: 50px;
  width: 400px;
  padding-left: 40px;
  padding-right: 40px;
  transition: all 0.7s;
}
.table-size:hover {
  transform: scale(1.2);
}
.circuit-show {
  display: inline-block;
  margin-left: 100px;
  padding: 20px;
  height: 350px;
  transition: all 0.7s;
  vertical-align: top;
}
.circuit-show:hover {
  transform: scale(1.2);
}
.circuit-component {
  display: inline-block;
  width: 130px;
  height: 130px;
  /* border: solid;  */
  /* border-left: dotted; */
  margin-top: 20px;
}
.circuit-C {
  width: 150px;
  height: 130px;
  transition: all 0.7s;
}
.circuit-L {
  margin-top: -25px;
  width: 150px;
  height: 157px;
  transition: all 1s;
}
.tips {
  font-size: 14px;
  margin-bottom: 30px;
  margin-left: 50px;
}
.last-modal {
  padding: 30px;
  /* width: 600px; */
  height: 230px;
  float: left;
  margin-left: 550px;
  margin-top: -70px;
  transition: all 0.7s;
}
.last-modal:hover {
  transform: scale(1.2);
}
.lost-component {
  display: inline-block;
  width: 100px;
  height: 100px;
  /* border: solid; */
}
.final-section {
  width: 103px;
  height: 100px;
}
.last-one {
  transform: rotateY(110rad);
}
.last-tips {
  text-align: center;
  font-size: 17px;
}
</style>