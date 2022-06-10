<template>
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
              { required: true, message: 'Please select your Usage scenario!' },
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
              rules: [{ required: true, message: 'Please input your Steps!' }],
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
                { required: true, message: 'Please input your MinLoss!' },
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
            { rules: [{ required: true, message: 'Please input your Wc!' }] },
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
            { rules: [{ required: true, message: 'Please input your W!' }] },
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
            { rules: [{ required: true, message: 'Please input your Zl!' }] },
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
            { rules: [{ required: true, message: 'Please input your Zh!' }] },
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
            { rules: [{ required: true, message: 'Please input your R0!' }] },
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
      <a-button type="primary" html-type="submit"> Submit Params </a-button>
    </a-form-item>
  </a-form>
</template>
<script>
import { getAction } from "../../../api/axiosmethod";
export default {
  name: "StepImpParam",
  data() {
    return {
      DeterminedStep: false,
      DeterminedMiniLoss: false,
      formLayout: "horizontal",
      form: this.$form.createForm(this, { name: "coordinated" }),
      formData: {},
      WUnit: 1,
      WcUnit: 1,
      ZlUnit: 1,
      ZhUnit: 1,
      R0Unit: 1,
    };
  },
  mounted() {},
  methods: {
    WchandleChange(value) {
      this.WcUnit = value;
    },
    WhandleChange(value) {
      this.WUnit = value;
    },
    ZlhandleChange(value) {
      this.ZlUnit = value;
    },
    ZhhandleChange(value) {
      this.ZhUnit = value;
    },
    R0handleChange(value) {
      this.R0Unit = value;
    },
    handleSubmit(e) {
      console.log(this.$refs.WcSelect);
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
          this.$emit("getParam", values, true);
          this.formData = values;
          this.$message.success("Submit successfully");
        }
      });
    },
    getStepImp(type) {
      //阶跃阻抗滤波器
      let param = {};
      if (type == 1) {
        param = {
          type: this.formData.scenario,
          W: Number(this.formData.W) * this.WUnit,
          Wc: Number(this.formData.Wc) * this.WcUnit,
          MinLoss: this.formData.MinLoss,
          Zl: Number(this.formData.Zl) * this.ZlUnit,
          Zh: Number(this.formData.Zh) * this.ZhUnit,
          R0: Number(this.formData.R0) * this.R0Unit,
        };
      } else if (type == 2) {
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
            this.$message.warning("Out of calculable range.Please try again.");
            setTimeout(() => {
              location.reload();
            }, 3000);
          } else {
            this.$emit("getDesignParam", res.data);
            this.$emit("showFilter",'StepImpShow');
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    handleSelectChange(value) {
      console.log(value);
      if (value == "Minimum loss (DB) determined") {
        this.DeterminedStep = false;
        this.DeterminedMiniLoss = true;
      } else if (value == "Required steps determined") {
        this.DeterminedStep = true;
        this.DeterminedMiniLoss = false;
      }
    },
  },
};
</script>

<style>
</style>