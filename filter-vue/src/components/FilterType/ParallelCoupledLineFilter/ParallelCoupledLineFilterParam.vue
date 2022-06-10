<template>
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
                  message: 'Please select your Equal ripple characteristics!',
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
              rules: [{ required: true, message: 'Please input your Steps!' }],
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
        <p>Loss at Frequency</p>
      </template>
      <a-form-item label="Fl">
        <a-input
          v-decorator="[
            'Fl',
            { rules: [{ required: true, message: 'Please input your Fl!' }] },
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
                { required: true, message: 'Please input your Bandwidth!' },
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
            { rules: [{ required: true, message: 'Please input your R!' }] },
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
  data() {
    return {
      DeterminedStep: false,
      DeterminedMiniLoss: false,
      formLayout: "horizontal",
      form: this.$form.createForm(this, { name: "coordinated" }),
      formData: {},
      WcUnit: 1,
      R0Unit: 1,
      FlUnit: 1,
    };
  },
  methods: {
    WchandleChange(value) {
      this.WcUnit = value;
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
          console.log("Received values of form: ", values);
          Object.assign(values, { units: unitArray });
          this.$emit("getParam", values, true);
          this.formData = values;
          this.$message.success("Submit successfully");
        }
      });
    },
    getParallelCoupledLineFilter() {
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
          this.$emit("getDesignParam", res.data);
          this.$emit("showFilter", "ParallelCoupledLineFilter");
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