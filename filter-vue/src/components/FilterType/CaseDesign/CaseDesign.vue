<template>
  <div class="Init">
    <img src="../../../media/imags/design.jpg" class="background" />
    <a-modal
      :maskClosable="false"
      v-model="FirstShow"
      title="Case Select"
      @ok="handleOk"
      @cancel="flesh"
    >
      <div>
        <a-select
          class="selectFilterType"
          ref="select"
          v-model="Type"
          style="width: 120px"
        >
          <a-select-option value="Low-Pass">Low-Pass</a-select-option>
          <a-select-option value="Band-Pass">Band-Pass</a-select-option>
        </a-select>
      </div>
      <div class="lowpass" v-if="Type == 'Band-Pass'">
        <a-form
          :form="form"
          :label-col="{ span: 5 }"
          :wrapper-col="{ span: 12 }"
          @submit="handleSubmit"
        >
          <a-popover>
            <template slot="content">
              <p>Equal ripple characteristics</p>
            </template>
            <a-form-item label="Ripple">
              <a-select
                v-decorator="[
                  'EqualRippleCharacteristics',
                  {
                    rules: [
                      {
                        required: true,
                        message:
                          'Please select your Equal ripple characteristics!',
                      },
                    ],
                  },
                ]"
                placeholder="Please select your Equal ripple characteristics"
                @change="handleSelectChange"
              >
                <a-select-option value="0.5dB"> 0.5dB </a-select-option>
                <a-select-option value="3dB"> 3dB </a-select-option>
              </a-select>
            </a-form-item>
          </a-popover>
          <a-popover>
            <template slot="content">
              <p>The number of steps of filter</p>
            </template>
            <a-form-item label="Steps">
              <a-input
                v-decorator="[
                  'Steps',
                  {
                    rules: [
                      { required: true, message: 'Please input your Steps!' },
                    ],
                  },
                ]"
              />
            </a-form-item>
          </a-popover>
          <a-popover>
            <template slot="content">
              <p>Center Frequency</p>
            </template>
            <a-form-item label="Fc">
              <a-input
                v-decorator="[
                  'Wc',
                  {
                    rules: [
                      { required: true, message: 'Please input your Wc!' },
                    ],
                  },
                ]"
                ><a-select
                  slot="addonAfter"
                  :default-value="1"
                  style="width: 80px"
                  @change="WchandleChange"
                  ref="WcSelect"
                >
                  <a-select-option :value="1"> Hz</a-select-option>
                  <a-select-option :value="1000"> kHz </a-select-option>
                  <a-select-option :value="1000000"> MHz </a-select-option>
                  <a-select-option :value="1000000000"> GHz </a-select-option>
                </a-select>
              </a-input>
            </a-form-item>
          </a-popover>

          <a-popover>
            <template slot="content">
              <p>Loss at Frequency</p>
            </template>
            <a-form-item label="Fl">
              <a-input
                v-decorator="[
                  'Fl',
                  {
                    rules: [
                      { required: true, message: 'Please input your Fl!' },
                    ],
                  },
                ]"
                ><a-select
                  slot="addonAfter"
                  :default-value="1"
                  style="width: 80px"
                  @change="FlhandleChange"
                  ref="FlSelect"
                >
                  <a-select-option :value="1"> Hz</a-select-option>
                  <a-select-option :value="1000"> kHz </a-select-option>
                  <a-select-option :value="1000000"> MHz </a-select-option>
                  <a-select-option :value="1000000000"> GHz </a-select-option>
                </a-select>
              </a-input>
            </a-form-item>
          </a-popover>

          <a-popover>
            <template slot="content">
              <p>Bandwidth</p>
            </template>
            <a-form-item label="Bandwidth">
              <a-input
                v-decorator="[
                  'Bandwidth',
                  {
                    rules: [
                      {
                        required: true,
                        message: 'Please input your Bandwidth!',
                      },
                    ],
                  },
                ]"
              />
            </a-form-item>
          </a-popover>

          <a-popover>
            <template slot="content">
              <p>Filter Impedance</p>
            </template>
            <a-form-item label="R">
              <a-input
                v-decorator="[
                  'R',
                  {
                    rules: [
                      { required: true, message: 'Please input your R!' },
                    ],
                  },
                ]"
                ><a-select
                  slot="addonAfter"
                  :default-value="1"
                  style="width: 80px"
                  @change="R0handleChange"
                >
                  <a-select-option :value="1"> Ω</a-select-option>
                  <a-select-option :value="1000"> kΩ </a-select-option>
                </a-select></a-input
              >
            </a-form-item>
          </a-popover>
          <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
            <a-button type="primary" html-type="submit">
              Submit Params
            </a-button>
          </a-form-item>
        </a-form>
      </div>
      <div v-if="Type == 'Low-Pass'">
        <a-radio-group class="lowType" v-model="lowType">
          <a-radio-button value="a"
            >Need Equal ripple characteristics</a-radio-button
          >
          <a-radio-button value="b"
            >Need scenes requiring steep cut-off</a-radio-button
          >
          <a-radio-button value="c">Need set minimum loss</a-radio-button>
          <a-radio-button value="d">No another requirements</a-radio-button>
        </a-radio-group>
        <div v-if="lowType == 'c' || lowType == 'd'">
          <a-form
            :form="form"
            :label-col="{ span: 5 }"
            :wrapper-col="{ span: 12 }"
            @submit="handleSubmit"
          >
            <a-form-item label="scenario">
              <a-select
                v-decorator="[
                  'scenario',
                  {
                    rules: [
                      {
                        required: true,
                        message: 'Please select your Usage scenario!',
                      },
                    ],
                  },
                ]"
                placeholder="Please select your Usage scenario!"
                @change="handleSelectChange"
              >
                <a-select-option value="Minimum loss (DB) determined">
                  Minimum loss (dB) determined
                </a-select-option>
                <a-select-option value="Required steps determined">
                  Required steps determined
                </a-select-option>
              </a-select>
            </a-form-item>
            <a-popover>
              <template slot="content">
                <p>The number of steps of filter</p>
              </template>
              <a-form-item label="Steps" v-if="DeterminedStep">
                <a-input
                  v-decorator="[
                    'Steps',
                    {
                      rules: [
                        { required: true, message: 'Please input your Steps!' },
                      ],
                    },
                  ]"
                />
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Minimum loss(dB)</p>
              </template>
              <a-form-item label="MinLoss(dB)" v-if="DeterminedMiniLoss">
                <a-input
                  v-decorator="[
                    'MinLoss',
                    {
                      rules: [
                        {
                          required: true,
                          message: 'Please input your MinLoss!',
                        },
                      ],
                    },
                  ]"
                />
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Cut-off Frequency</p>
              </template>
              <a-form-item label="Wc">
                <a-input
                  v-decorator="[
                    'Wc',
                    {
                      rules: [
                        { required: true, message: 'Please input your Wc!' },
                      ],
                    },
                  ]"
                  ><a-select
                    slot="addonAfter"
                    :default-value="1"
                    style="width: 80px"
                    @change="WchandleChange"
                    ref="WcSelect"
                  >
                    <a-select-option :value="1"> Hz</a-select-option>
                    <a-select-option :value="1000"> kHz </a-select-option>
                    <a-select-option :value="1000000"> MHz </a-select-option>
                    <a-select-option :value="1000000000"> GHz </a-select-option>
                  </a-select>
                </a-input>
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Insertion frequency at minimum loss</p>
              </template>
              <a-form-item label="W" v-if="DeterminedMiniLoss">
                <a-input
                  v-decorator="[
                    'W',
                    {
                      rules: [
                        { required: true, message: 'Please input your W!' },
                      ],
                    },
                  ]"
                  ><a-select
                    slot="addonAfter"
                    :default-value="1"
                    style="width: 80px"
                    @change="WhandleChange"
                  >
                    <a-select-option :value="1"> Hz</a-select-option>
                    <a-select-option :value="1000"> kHz </a-select-option>
                    <a-select-option :value="1000000"> MHz </a-select-option>
                    <a-select-option :value="1000000000"> GHz </a-select-option>
                  </a-select></a-input
                >
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Minimum actual line resistance</p>
              </template>
              <a-form-item label="Zl">
                <a-input
                  v-decorator="[
                    'Zl',
                    {
                      rules: [
                        { required: true, message: 'Please input your Zl!' },
                      ],
                    },
                  ]"
                  ><a-select
                    slot="addonAfter"
                    :default-value="1"
                    style="width: 80px"
                    @change="ZlhandleChange"
                  >
                    <a-select-option :value="1"> Ω</a-select-option>
                    <a-select-option :value="1000"> kΩ </a-select-option>
                  </a-select>
                </a-input>
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Maximum actual line resistance</p>
              </template>
              <a-form-item label="Zh">
                <a-input
                  v-decorator="[
                    'Zh',
                    {
                      rules: [
                        { required: true, message: 'Please input your Zh!' },
                      ],
                    },
                  ]"
                  ><a-select
                    slot="addonAfter"
                    :default-value="1"
                    style="width: 80px"
                    @change="ZhhandleChange"
                  >
                    <a-select-option :value="1"> Ω</a-select-option>
                    <a-select-option :value="1000"> kΩ </a-select-option>
                  </a-select></a-input
                >
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Filter Impedance</p>
              </template>
              <a-form-item label="R0">
                <a-input
                  v-decorator="[
                    'R0',
                    {
                      rules: [
                        { required: true, message: 'Please input your R0!' },
                      ],
                    },
                  ]"
                  ><a-select
                    slot="addonAfter"
                    :default-value="1"
                    style="width: 80px"
                    @change="R0handleChange"
                  >
                    <a-select-option :value="1"> Ω</a-select-option>
                    <a-select-option :value="1000"> kΩ </a-select-option>
                  </a-select></a-input
                >
              </a-form-item>
            </a-popover>
            <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
              <a-button type="primary" html-type="submit">
                Submit Params
              </a-button>
            </a-form-item>
          </a-form>
        </div>
        <div v-if="lowType == 'a' || lowType == 'b'">
          <a-form
            :form="form"
            :label-col="{ span: 5 }"
            :wrapper-col="{ span: 12 }"
            @submit="handleSubmit"
          >
            <a-popover>
              <template slot="content">
                <p>Equal ripple characteristics</p>
              </template>
              <a-form-item label="Ripple">
                <a-select
                  v-decorator="[
                    'EqualRippleCharacteristics',
                    {
                      rules: [
                        {
                          required: true,
                          message:
                            'Please select your Equal ripple characteristics!',
                        },
                      ],
                    },
                  ]"
                  placeholder="Please select your Equal ripple characteristics"
                  @change="handleSelectChange"
                >
                  <a-select-option value="0.5dB"> 0.5dB </a-select-option>
                  <a-select-option value="3dB"> 3dB </a-select-option>
                </a-select>
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>The number of steps of filter</p>
              </template>
              <a-form-item label="Steps">
                <a-input
                  v-decorator="[
                    'Steps',
                    {
                      rules: [
                        { required: true, message: 'Please input your Steps!' },
                      ],
                    },
                  ]"
                />
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Cut-off Frequency</p>
              </template>
              <a-form-item label="Wc">
                <a-input
                  v-decorator="[
                    'Wc',
                    {
                      rules: [
                        { required: true, message: 'Please input your Wc!' },
                      ],
                    },
                  ]"
                  ><a-select
                    slot="addonAfter"
                    :default-value="1"
                    style="width: 80px"
                    @change="WchandleChange"
                    ref="WcSelect"
                  >
                    <a-select-option :value="1"> Hz</a-select-option>
                    <a-select-option :value="1000"> kHz </a-select-option>
                    <a-select-option :value="1000000"> MHz </a-select-option>
                    <a-select-option :value="1000000000"> GHz </a-select-option>
                  </a-select>
                </a-input>
              </a-form-item>
            </a-popover>
            <a-popover>
              <template slot="content">
                <p>Filter Impedance</p>
              </template>
              <a-form-item label="R">
                <a-input
                  v-decorator="[
                    'R',
                    {
                      rules: [
                        { required: true, message: 'Please input your R!' },
                      ],
                    },
                  ]"
                  ><a-select
                    slot="addonAfter"
                    :default-value="1"
                    style="width: 80px"
                    @change="R0handleChange"
                  >
                    <a-select-option :value="1"> Ω</a-select-option>
                    <a-select-option :value="1000"> kΩ </a-select-option>
                  </a-select></a-input
                >
              </a-form-item>
            </a-popover>
            <a-form-item :wrapper-col="{ span: 12, offset: 5 }">
              <a-button type="primary" html-type="submit">
                Submit Params
              </a-button>
            </a-form-item>
          </a-form>
        </div>
      </div>
    </a-modal>
    <ParallelCoupledLineFilter
      :filterParam="filterParam"
      :DesignParam="DesignParam"
      v-if="show == 'ParallelCoupledLineFilter'"
    />
    <StepImpShow
      :filterParam="filterParam"
      :DesignParam="DesignParam"
      v-if="show == 'StepImpShow'"
    />
    <StubFilterShow
      :filterParam="filterParam"
      :DesignParam="DesignParam"
      v-if="show == 'StubFilterShow'"
    />
  </div>
</template>

<script>
import ParallelCoupledLineFilter from "../ParallelCoupledLineFilter/ParallelCoupledLineFilterShow.vue";
import StepImpShow from "../stepImp/StepImpShow.vue";
import StubFilterShow from "../StubFilter/StubFilterShow.vue";
import { getAction } from "../../../api/axiosmethod";
export default {
  name: "CaseDesign",
  components: {
    ParallelCoupledLineFilter,
    StepImpShow,
    StubFilterShow,
  },
  data() {
    return {
      filterParam: {},
      DesignParam: {},
      show: "",
      SIFType: 0,
      FirstShow: true,
      Type: "",
      lowType: "",
      DeterminedStep: false,
      DeterminedMiniLoss: false,
      formLayout: "horizontal",
      form: this.$form.createForm(this, { name: "coordinated" }),
      formData: {},
      WcUnit: 1,
      R0Unit: 1,
      FlUnit: 1,
      ZlUnit: 1,
      ZhUnit: 1,
    };
  },
  methods: {
    flesh() {
      this.$router.push({ path: "/homepage" });
    },
    WchandleChange(value) {
      this.WcUnit = value;
    },
    WhandleChange(value) {
      this.WUnit = value;
    },
    FlhandleChange(value) {
      this.FlUnit = value;
    },
    ZhhandleChange(value) {
      this.ZhUnit = value;
    },
    R0handleChange(value) {
      this.R0Unit = value;
    },
    ZlhandleChange(value) {
      this.ZlUnit = value;
    },
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          let unitArray = {
            WUnit: this.WUnit,
            WcUnit: this.WcUnit,
            ZlUnit: this.ZlUnit,
            ZhUnit: this.ZhUnit,
            R0Unit: this.R0Unit,
          };
          Object.assign(values, { units: unitArray });
          this.filterParam = values;
          this.formData = values;
          this.$message.success("Submit successfully");
        }
      });
    },
    handleSelectChange(value) {
      console.log(value);
      if (value == "Minimum loss (DB) determined") {
        this.DeterminedStep = false;
        this.DeterminedMiniLoss = true;
        this.SIFType = 1;
      } else if (value == "Required steps determined") {
        this.DeterminedStep = true;
        this.DeterminedMiniLoss = false;
        this.SIFType = 2;
      }
    },
    handleOk() {
      if (this.Type == "Band-Pass") {
        let param = {};
        param = {
          ERC: this.formData.EqualRippleCharacteristics,
          steps: this.formData.Steps,
          BW: this.formData.Bandwidth,
          Floss: Number(this.formData.Fl) * this.FlUnit,
          Fc: Number(this.formData.Wc) * this.WcUnit,
          Z0: Number(this.formData.R) * this.R0Unit,
        };

        let url = "app/filterDesign/ParallelCoupledLineFilter";
        getAction(url, param)
          .then((res) => {
            console.log(res);
            this.DesignParam = res.data;
            this.show = "ParallelCoupledLineFilter";
            this.FirstShow = false;
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (
        this.Type == "Low-Pass" &&
        (this.lowType == "c" || this.lowType == "d")
      ) {
        //阶跃阻抗滤波器
        let param = {};
        if (this.SIFType == 1) {
          param = {
            type: this.formData.scenario,
            W: Number(this.formData.W) * this.WUnit,
            Wc: Number(this.formData.Wc) * this.WcUnit,
            MinLoss: this.formData.MinLoss,
            Zl: Number(this.formData.Zl) * this.ZlUnit,
            Zh: Number(this.formData.Zh) * this.ZhUnit,
            R0: Number(this.formData.R0) * this.R0Unit,
          };
        } else if (this.SIFType == 2) {
          param = {
            type: this.formData.scenario,
            Steps: this.formData.Steps,
            Wc: Number(this.formData.Wc) * this.WcUnit,
            Zl: Number(this.formData.Zl) * this.ZlUnit,
            Zh: Number(this.formData.Zh) * this.ZhUnit,
            R0: Number(this.formData.R0) * this.R0Unit,
          };
        }

        let url = "app/filterDesign/steppedImpedance";
        getAction(url, param)
          .then((res) => {
            console.log(res);
            if (res.data.overflow == "true") {
              this.$message.warning(
                "Out of calculable range.Please try again."
              );
              setTimeout(() => {
                location.reload();
              }, 3000);
            } else {
              this.DesignParam = res.data;
              this.show = "StepImpShow";
              this.FirstShow = false;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (
        this.Type == "Low-Pass" &&
        (this.lowType == "a" || this.lowType == "b")
      ) {
        //阶跃阻抗滤波器
        let param = {};
        param = {
          Ripple: this.formData.EqualRippleCharacteristics,
          Steps: this.formData.Steps,
          Wc: Number(this.formData.Wc) * this.WcUnit,
          R: Number(this.formData.R) * this.R0Unit,
        };

        let url = "app/filterDesign/StubFilter";
        getAction(url, param)
          .then((res) => {
            console.log(res);
            if (res.data.overflow == "true") {
              this.$message.warning(
                "Out of calculable range.Please try again."
              );
              setTimeout(() => {
                location.reload();
              }, 3000);
            } else {
              this.DesignParam = res.data;
              this.show = "StubFilterShow";
              this.FirstShow = false;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },
  },
};
</script>

<style scoped>
.selectFilterType {
  margin-bottom: 50px;
}
.lowType {
  margin-bottom: 50px;
}
</style>