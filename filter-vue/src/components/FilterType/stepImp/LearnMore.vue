<template>
  <div class="pup-back">
    <div class="self-pup pupsize">
      <div>
        <div class="pup-title">More Details About Stepped-Impedance Filter (Low-pass)</div>
        <a-icon type="close" class="pup-close" @click="closePup" />
        <div class="content">
          <div class="advantage"><a-icon type="smile" /> 1. Easy to make.</div>
          <div class="advantage">
            <a-icon type="smile" /> 2. Compact structure.
          </div>
          <div class="disadvantage">
            <a-icon type="frown" /> 3. Poor electrical characteristics.
          </div>
          <div class="disadvantage">
            <a-icon type="frown" /> 4. Not applicable to scenes requiring steep
            cut-off.
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "LearnMore",
  data() {
    return {};
  },
  methods: {
    closePup() {
      this.$emit("closeLearnMorePup");
    },
  },
};
</script>
<style scoped>
.pupsize {
  padding: 20px;
  width: 1000px;
  /* height: 800px; */
}
.advantage {
  padding-left: 50px;
  font-size: 20px;
  color: rgb(36, 189, 36);
  margin-bottom: 20px;
}
.disadvantage {
  padding-left: 50px;
  font-size: 20px;
  color: rgb(218, 52, 52);
  margin-bottom: 20px;
}
.content {
  margin-top: 40px;
}
</style>