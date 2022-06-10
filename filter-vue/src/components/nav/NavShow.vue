<template>
  <div class="self-card nav-card">
    <div><a-icon type="left" /></div>
    <a-button class="nav-item" @click="getMicroStrip">
      <a-icon type="highlight" /> Get Microsrip modal
    </a-button>
    <a-button class="nav-item" @click="TurnshowResults">
      <a-icon type="dot-chart" /> Simulation Results
    </a-button>
    <!-- <div class="nav-item">Get Microsrip modal</div> -->
    <a-button class="nav-item" @click="TurnshowLearnMore">
      <a-icon type="copy" /> Learn more
    </a-button>
    <a-button class="nav-item" @click="refresh">
      <a-icon type="arrow-left" /> Return
    </a-button>
  </div>
</template>
<script>
export default {
  name: "NavShow",
  data() {
    return {};
  },
  methods: {
    refresh() {
      location.reload();
    },
    getMicroStrip() {
      this.$emit("showMicrostrip");
    },
    TurnshowLearnMore() {
      this.$emit("showLearnMore");
    },
    TurnshowResults() {
      this.$emit("showResults");
    },
  },
};
</script>
<style>
html,
body {
  overflow-x: hidden;
}
</style>
<style scoped>
.nav-card {
  position: absolute;
  width: 300px;
  top: 100px;
  padding: 20px;
  /* height: 50px; */
  right: -250px;
  transition: all 0.7s;
  z-index: 999;
}
.nav-card:hover {
  transform: translateX(-220px);
}
.nav-item {
  display: block;
  margin-top: 20px;
  text-align: center;
  font-size: 18px;
  color: rgb(81, 135, 216);
  cursor: pointer;
  width: 100%;
  border: none;
}
.nav-item:hover {
  background-color: bisque;
}
</style>