import Vue from 'vue'
import Router from 'vue-router'
import loginIndex from '@/pages/login/loginindex'

Vue.use(Router)

export default new Router({
  //用来去掉url中的#
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'loginIndex',
      component: loginIndex
    },
    {
      path: '/login',
      name: 'loginIndex',
      component: loginIndex
    }
  ]
})
