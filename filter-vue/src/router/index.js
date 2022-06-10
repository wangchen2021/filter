import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/login',
    name: 'login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../components/Login.vue')
  },
  {
    path: '/homepage',
    name: 'homepage',
    component: () => import(/* webpackChunkName: "about" */ '../components/HomePage.vue')
  },
  {
    path: '/ModalShow',
    name: 'ModalShow',
    component: () => import(/* webpackChunkName: "about" */ '../components/FilterType/FilterModal/FilterModalShow.vue'),
  },
  {
    path: '/typedesign',
    name: 'typedesign',
    component: () => import(/* webpackChunkName: "about" */ '../components/TypeDesign.vue'),
  },

  {
    path: '/CaseDesign',
    name: 'CaseDesign',
    component: () => import(/* webpackChunkName: "about" */ '../components/FilterType/CaseDesign/CaseDesign.vue'),
  },
]

const router = new VueRouter({
  routes
})

export default router
// 验证token实现路由 作为路由守卫
router.beforeEach((to, from, next) => {
  let cookies = document.cookie.split(",")[0].search("user")
  if (to.path == "/") {
    if (cookies < 0) {
      // 默认去登录页
      next("/Login")
    } else {
      next("/homepage")
    }
  }
  //守卫作用
  if (cookies >= 0 || to.path == "/Login") {
    next()
  } else {
    next("/Login")
  }
});
