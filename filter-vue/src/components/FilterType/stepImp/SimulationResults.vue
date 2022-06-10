<template>
  <div class="pup-back">
    <div class="self-pup pupsize">
      <div>
        <div class="pup-title">
          Examples of Simulation
        </div>
        <a-icon type="close" class="pup-close" @click="closePup" />
        <img class="results-photo" src="../../../media/imags/StepsResults.png"/>
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
      this.$emit("closeResults");
    },
  },
};
</script>
<style scoped>
.pupsize {
  padding: 20px;
  /* width: 1000px; */
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
.results-photo{
    margin-top: 20px;
}
</style>