import Vue from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios';
import Antd from 'ant-design-vue/lib';
import 'ant-design-vue/dist/antd.css';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './css/Base.css'
import VueCookies from 'vue-cookies'
import 'lib-flexible'
import './css/Base.css'
Vue.use(VueCookies)
Vue.config.productionTip = false;
Vue.prototype.$axios = axios; //全局注册，使用方法为:this.$axios
Vue.config.productionTip = false
Vue.use(Antd);
Vue.use(ElementUI);
//设置默认请求头
// axios.defaults.headers['Content-Type'] = 'application/x-www-form-urlencoded'
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
