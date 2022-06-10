<template>
  <router-view>
    <div id="app" class="center"></div>
  </router-view>
</template>
<script>
export default {
  name: "app",
  components: {},
  // mounted() {
  //   this.$router.push({ path: "/login" });
  // },
};
</script>
<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
.center {
  align-items: center;
  width: 100%;
  height: 100%;
}
html,
body,
#app {
  height: 100%;
}
</style>
