<template>
  <div class="Init">
    <img
      class="background"
      src="https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F038a17d55deb73600000159950c6be4.jpg&refer=http%3A%2F%2Fimg.zcool.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1639381427&t=9d4cb479fa07f77dd8109fd47ab0ace6"
    />
    <div class="container">
      <a-popover trigger="hover">
        <template slot="content">
          <p>A filter design assistant software</p>
          <p>
            Suitable filters can be built online <br />according to your needs
          </p>
        </template>
        <h3 id="title">Filter constructor</h3>
      </a-popover>
      <div id="login">
        <div class="items">
          <div class="inputs">
            <div class="input">
              Username <a-input v-model="username" style="width: 60%"></a-input>
            </div>
            <div class="input">
              Password
              <a-input
                type="password"
                v-model="password"
                style="width: 60%"
              ></a-input>
            </div>
          </div>
        </div>
        <div class="buttons items">
          <a-button class="loginbutton" @click="Login">Login</a-button>
          <a-button class="loginbutton">Register</a-button>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import { putAction } from "../api/axiosmethod";
export default {
  name: "Login",
  data() {
    return {
      username: "",
      password: "",
    };
  },
  methods: {
    Login() {
      let param = {};
      let url = "http://localhost:7000/FBuilder/login";
      param = { username: this.username, password: this.password };
      putAction(url, param)
        .then((res) => {
          console.log("res", res.data.result);
          if (res.data.result == "Login successfully") {
            let userMessage = {};
            Object.assign(userMessage, { Token: res.data.Token });
            Object.assign(userMessage, { user: res.data.user });
            let hour = 3600;
            this.$cookies.set("user", this.username, 1 * hour);
            this.$cookies.set("pass", this.password, 1 * hour);
            this.$router.push({ path: "/homepage" });
          } else if (res.data.result == "Login failed") {
            this.$message.error(res.data.user);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style scoped>
.buttons {
  text-align: center;
}
#title {
  position: absolute;
  top: 10%;
  left: 20%;
  transform: translateX(-50%);
  font-size: 30px;
  color: rgb(83, 127, 221);
}
input {
  margin-left: 20px;
}
.input {
  margin-top: 15%;
  margin-right: 10%;
}
.inputs {
  font-size: 18px;
  color: rgb(83, 127, 221);
  width: 60%;
  height: 60%;
  margin: 0 auto;
  margin-top: 5%;
}
p {
  font-size: 18px;
  color: rgb(83, 127, 221);
}
.ant-input:-webkit-autofill {
  box-shadow: 0 0 0 1000px #caeca3d8 inset;
}
.ant-input:hover,
input:focus {
  box-shadow: 0 0 0 1000px #72dba7e1 inset;
}
.loginbutton {
  background-color: rgba(111, 231, 211, 0.801);
  border: none;
  margin: 40px;
  color: rgb(83, 127, 221);
  width: 100px;
  font-size: 18px;
}
#login {
  display: grid;
  position: relative;
  top: 50%;
  left: 50%;
  width: 50%;
  height: 350px;
  transform: translate(-50%, -50%);
  background-color: rgba(153, 205, 212, 0.705);
  border-radius: 10px;
  grid-template-rows: 65% 35%;
}
</style>