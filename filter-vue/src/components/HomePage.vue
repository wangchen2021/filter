<template>
  <div class="Init">
    <img class="background" src="../media/imags/homepage.jpg" />
    <div class="container">
      <div id="header">
        <ul>
          <li><router-link to="/ModalShow">Filter Type</router-link></li>
          <li>
            <a>Filter Design</a>
            <div class="items">
              <ul>
                <li>
                  <a @click="turnpage('typedesign')">Type Design</a>
                </li>
              </ul>
            </div>
            <div class="items">
              <ul>
                <li>
                  <a @click="turnpage('CaseDesign')">Case Design</a>
                </li>
              </ul>
            </div>
          </li>
          <li><a>Design Principle</a></li>
          <li><a>Help</a></li>
        </ul>
      </div>

      <div id="logout">
        <a-popconfirm
          title="Are you sure to logout?"
          ok-text="Yes"
          cancel-text="No"
          @confirm="confirm"
          @cancel="cancel"
        >
          <a href="#">Logout</a>
        </a-popconfirm>
      </div>
      <div class="text">
        <div class="line">
          Software for automating the design of planar filters for use in next
          generation wireless systems
        </div>
        <div class="line">
          Author: Wang Chen<br />BUPT ID:2018212769<br />QM ID:190012636
        </div>
      </div>
    </div>
  </div>
</template>
<script>
export default {
  name: "HomePage",
  data() {
    return {};
  },
  components: {},
  methods: {
    turnpage(path) {
      this.$router.push({ path: path });
    },
    confirm() {
      this.turnpage("login");
      this.$cookies.remove("user");
      this.$cookies.remove("pass");
      this.$message.success("Logout successfully");
    },
    cancel() {},
  },
};
</script>
<style scoped>
#logout {
  float: right;
  margin-top: 10px;
  font-size: 15px;
  color: rgba(126, 114, 233, 0.911);
  cursor: pointer;
}
.items {
  display: none;
  width: inherit;
  height: 50px;
  background-color: rgba(171, 158, 245, 0.795);
  transition: all 0.5s;
}
#header {
  margin-top: 10px;
  height: 50px;
  background-color: rgba(140, 191, 240, 0.795);
  border-radius: 5px;
}
ul li {
  float: left;
}
ul li a {
  display: inline-block;
  height: 50px;
  line-height: 50px;
  text-align: center;
  padding-left: 20px;
  padding-right: 20px;
  font-size: 21px;
}
ul li a:hover {
  background-color: rgba(113, 240, 202, 0.664);
}
ul li:hover .items {
  display: block;
}
.text {
  padding-top: 110px;
  width: 500px;
  height: 600px;
  background-color: rgb(245, 243, 243, 0.9);
  border-radius: 10px;
  margin: 0 auto;
  margin-top: 100px;
}
.line {
  margin-top: 150px;
  text-align: center;
  font-size: 20px;
  color: rgb(76, 144, 233);
}
</style>