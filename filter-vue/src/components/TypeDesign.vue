<template>
  <div class="Init">
    <img src="../media/imags/design.jpg" class="background" />
    <!-- 类型选择 -->
    <a-modal
      class="type-select"
      :maskClosable="false"
      v-model="visible"
      title="Select type of filter"
      @ok="handleOk"
      @cancel="returnhome"
    >
      <p>Please select the type of filter</p>
      <a-select
        class="selectFilter"
        default-value="Please select type"
        @change="handleChange"
      >
        <a-icon slot="suffixIcon" type="smile" />
        <a-select-option value="stepped-impedance filter">
          Stepped-impedance filter
        </a-select-option>
        <a-select-option value="stub filter"> Stub filter </a-select-option>
        <a-select-option value="parallel coupled line filter">
          Parallel coupled line filter
        </a-select-option>
        <a-select-option value="disabled" disabled> Disabled </a-select-option>
      </a-select>
      <div style="margin-top: 20px">
        <a-radio-group
          v-if="this.type == 'stepped-impedance filter' || 'stub filter'"
          name="radioGroup"
          @change="passtype"
        >
          <a-radio :value="1"> low-pass </a-radio>
          <a-radio :value="2"> band-pass </a-radio>
        </a-radio-group>
      </div>
    </a-modal>
    <a-modal
      :maskClosable="false"
      v-model="varvisible"
      title="Input Parameter"
      @ok="handleOk1"
      @cancel="returnselect"
      html-type="submit"
    >
      <!-- 阶跃阻抗输入参数 -->
      <StepImpParam
        v-if="type == 'stepped-impedance filter'"
        ref="StepImpParam"
        @showFilter="showFilter"
        @getDesignParam="getDesignParam"
        @getParam="getParam"
      />
      <!-- 阶跃阻抗输入参数 -->
      <StubFilterParam
        v-if="type == 'stub filter'"
        ref="StubFilter"
        @showFilter="showFilter"
        @getDesignParam="getDesignParam"
        @getParam="getParam"
      />
      <ParallelCoupledLineFilterParam
        ref="ParallelCoupledLineFilter"
        v-if="type == 'parallel coupled line filter'"
        @getParam="getParam"
        @getDesignParam="getDesignParam"
        @showFilter="showFilter"
      />
    </a-modal>
    <StepImpShow
      :filterParam="filterParam"
      :DesignParam="DesignParam"
      v-if="FilterTypeShow == 'StepImpShow'"
    />
    <StubFilterShow
      :filterParam="filterParam"
      :DesignParam="DesignParam"
      v-if="FilterTypeShow == 'StubFilter'"
    />
    <ParallelCoupledLineFilterShow
      v-if="FilterTypeShow == 'ParallelCoupledLineFilter'"
      :filterParam="filterParam"
      :DesignParam="DesignParam"
    />
  </div>
</template>
<script>
import StepImpShow from "./FilterType/stepImp/StepImpShow.vue";
import StepImpParam from "./FilterType/stepImp/StepImpParam.vue";
import StubFilterShow from "./FilterType/StubFilter/StubFilterShow.vue";
import StubFilterParam from "./FilterType/StubFilter/StubFilterParam.vue";
import ParallelCoupledLineFilterParam from "./FilterType/ParallelCoupledLineFilter/ParallelCoupledLineFilterParam.vue";
import ParallelCoupledLineFilterShow from "./FilterType/ParallelCoupledLineFilter/ParallelCoupledLineFilterShow.vue";
export default {
  name: "TypeDesign",
  components: {
    StepImpParam,
    StepImpShow,
    StubFilterParam,
    StubFilterShow,
    ParallelCoupledLineFilterParam,
    ParallelCoupledLineFilterShow,
  },
  data() {
    return {
      visible: true,
      varvisible: false,
      FilterTypeShow: "",
      type: "",
      pass: "",
      submitFlag: false,
      filterParam: {},
      DesignParam: {},
    };
  },
  methods: {
    getDesignParam(val) {
      this.DesignParam = val;
      // console.log("计算获得的参数", this.DesignParam);
    },
    getParam(val, submitFlag) {
      this.filterParam = val;
      console.log("111", this.filterParam);
      this.submitFlag = submitFlag;
    },
    passtype(value) {
      if (value.target.value == 1) {
        this.pass = "Lowpass";
      } else if (value.target.value == 2) {
        this.pass = "Highpass";
      }
      console.log(this.pass);
    },
    returnselect() {
      (this.varvisible = false), (this.visible = true);
    },
    handleChange(value) {
      this.type = value;
      console.log(`selected `, this.type);
    },
    returnhome() {
      this.$router.push({ path: "/homepage" });
    },
    showModal() {
      this.visible = true;
    },
    handleOk(e) {
      console.log(e);
      if (this.type == "" || this.pass == "") {
        this.$message.warning("Please complete the information");
      } else {
        this.visible = false;
        this.varvisible = true;
      }
    },
    handleOk1() {
      if (this.type == "stepped-impedance filter") {
        if (this.submitFlag) {
          this.varvisible = false;
          if (this.filterParam.scenario == "Minimum loss (DB) determined") {
            this.$refs.StepImpParam.getStepImp(1);
          } else if (this.filterParam.scenario == "Required steps determined") {
            this.$refs.StepImpParam.getStepImp(2);
          }
        } else {
          this.$message.warning("Please submit complete parameters");
        }
      } else if (this.type == "stub filter") {
        this.$refs.StubFilter.getStubFilter();
        this.varvisible = false;
      } else if (this.type == "parallel coupled line filter") {
        this.$refs.ParallelCoupledLineFilter.getParallelCoupledLineFilter();
        this.varvisible = false;
      }
    },
    showFilter(val) {
      this.$nextTick(() => {
        this.FilterTypeShow = val;
      });
    },
  },
};
</script>
<style>
.selectFilter > .ant-select-selection--single {
  width: 250px;
}
</style>
<style scoped>
</style>