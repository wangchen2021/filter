<template>
  <div>
    <NavShow
      @showResults="showResults"
      @showMicrostrip="showMicrostrip"
      @showLearnMore="showLearnMore"
    />
    <div class="lumped-circuit-model">
      <div class="self-card fistModel">
        <div class="card_title">Lumped Circuit Model</div>
        <div class="section">
          <img class="source" src="../../../media/imags/Source.png" />
        </div>
        <div
          class="section"
          v-for="(item, index) in this.DesignParam.ElementsValue"
          :key="index"
        >
          <p v-if="index % 2 == 0 && index != Slength">
            L{{ index + 1 }} = {{ item }}
          </p>
          <p v-if="index % 2 == 1 && index != Slength">
            C{{ index + 1 }} = {{ item }}
          </p>
          <img
            class="circuit-L"
            src="../../../media/imags/Id.png"
            v-if="index % 2 == 0 && index != Slength"
          />
          <img
            class="circuit-C"
            src="../../../media/imags/Cp.png"
            v-if="index % 2 == 1 && index != Slength"
          />
          <img
            class="end"
            src="../../../media/imags/endImp.png"
            v-if="index == Slength"
          />
        </div>
      </div>
    </div>
    <div class="Basic-stub-model">
      <div class="self-card fistModel">
        <div class="card_title">Basic stub model</div>
        <div class="section">
          <img class="source" src="../../../media/imags/Source.png" />
        </div>
        <div
          class="section"
          v-for="(item, index) in this.DesignParam.RTZArray"
          :key="index"
        >
          <p v-if="index % 2 == 0 && index != Slength">
            Z{{ index + 1 }} = {{ item }}
          </p>
          <p v-if="index % 2 == 1 && index != Slength">
            Z{{ index + 1 }} = {{ Number(item).toPrecision(4) }}
          </p>
          <img
            class="SLine"
            src="../../../media/imags/SLine.png"
            v-if="index % 2 == 0 && index != Slength"
          />
          <img
            class="PLine"
            src="../../../media/imags/PLine.png"
            v-if="index % 2 == 1 && index != Slength"
          />
          <img
            class="end"
            src="../../../media/imags/endImp.png"
            v-if="index == Slength"
          />
        </div>
        <div class="Lstyle">L(length)=λ/8 at ω=1</div>
      </div>
    </div>
    <StubFilterMicrostrip :filterParam='filterParam' :DesignParam='DesignParam' v-if="MicrostripShow" @closeMicroSrtip='closeMicroSrtip'/>
  </div>
</template>

<script>
import NavShow from "../../nav/NavShow.vue";
import StubFilterMicrostrip from './StubFilterMicrostrip.vue'
export default {
  name: "StubFilterShow",
  props: ["DesignParam", "filterParam"],
  components: {
    NavShow,
    StubFilterMicrostrip
  },
  data() {
    return {
      Slength: "",
      MicrostripShow:false
    };
  },

  mounted() {
    this.Slength = this.DesignParam.ElementsValue.length - 1;
    console.log(
      this.filterParam,
      this.DesignParam,
      this.DesignParam.ElementsValue.length
    );
  },
  methods: {
    showResults() {
      this.SimulationShow = true;
    },
    showMicrostrip() {
      this.MicrostripShow = true;
    },
    showLearnMore() {
      this.LearnMoreShow = true;
    },
    closeMicroSrtip() {
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
.Basic-stub-model {
  position: absolute;
  text-align: center;
  top: 450px;
  left: 200px;
  width: 1550px;
}
.lumped-circuit-model {
  position: absolute;
  text-align: center;
  top: 50px;
  left: 200px;
  width: 1550px;
}
.section {
  margin-top: 30px;
  display: inline-block;
  width: 120px;
  height: 100px;
  transition: all 0.6s;
}
.fistModel {
  display: inline-block;
  padding: 25px;
  transition: all 0.6s;
}
.fistModel:hover {
  transform: scale(1.2);
}
.circuit-L {
  width: 135px;
  height: 120px;
}
.circuit-C {
  width: 135px;
  height: 99px;
  vertical-align: -56px;
}
.end {
  width: 120px;
  height: 102px;
  border-left: 1px dotted rgba(0, 0, 0, 0.329);
  padding-left: 15px;
  vertical-align: -52px;
  margin-left: 15px;
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
  right: -220px;
  top: 10px;
  font-size: 17px;
  margin-bottom: 10px;
}
.PLine {
  height: 140px;
  width: 130px;
  vertical-align: -98px;
}
</style>