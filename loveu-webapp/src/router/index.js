import Vue from 'vue'
import Router from 'vue-router'
import loginIndex from '@/pages/login/loginindex'
import homePage from '@/pages/home'
import communityPage from '@/pages/community'
import test from '@/pages/test'
import store from '../store';

Vue.use(Router)

if (localStorage.getItem('token')) {
  store.commit('changeToken', localStorage.getItem('token'))
}
const router = new Router({
  //用来去掉url中的#，虽然#可以去掉，但是会引发刷新后404的错误
  //TODO 启动后台服务检验带#的情况
  // mode: 'history',
  routes: [{
      path: '/',
      // redirect: '/test'
      redirect: '/home'
    },
    {
      path: '/login',
      name: 'loginIndex',
      component: loginIndex
    },
    {
      path: '/test',
      name: 'test',
      component: test
    },
    {
      path: '/home',
      name: 'home',
      component: homePage,
    },
    {
      path: '/community',
      name: 'community',
      component: communityPage,
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/community') {
    next()
  } else {
    let token = localStorage.getItem('token')
    let username = localStorage.getItem('username')
    if (token === null || token === '') {
      next('/login')
    } else if (username === null) {
      next('/community')
    } else {
      next();
    }
  }
});

export default router;
