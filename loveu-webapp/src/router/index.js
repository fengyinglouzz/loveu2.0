import loginIndex from '@/pages/login/loginindex'
import signupIndex from '@/pages/login/signupIndex'
import homePage from '@/pages/home'
import communityPage from '@/pages/community'
import myPage from '@/pages/my'
import myarticlePage from '@/pages/article/myarticle'
import herarticlePage from '@/pages/article/herarticle'
import newarticlePage from '@/pages/article/newarticle'
import onearticlePage from '@/pages/article/onearticle'
import mymoneyPage from '@/pages/money'
import anniversaryPage from '@/pages/anniversary'
import test from '@/pages/test'
import store from '../store';

if (localStorage.getItem('token')) {
  store.commit('changeToken', localStorage.getItem('token'))
}
const router = new VueRouter({
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
    path: '/signup',
    name: 'signupIndex',
    component: signupIndex
  },
  {
    path: '/test',
    name: 'test',
    component: test
  },
  {
    path: '/home',
    name: 'home',
    meta: {
      title: '空间',
    },
    component: homePage,
  },
  {
    path: '/community',
    name: 'community',
    component: communityPage,
  },
  {
    path: '/newarticle',
    name: 'newarticle',
    component: newarticlePage,
  },
  {
    path: '/onearticle',
    name: 'onearticle',
    component: onearticlePage,
  },
  {
    path: '/my',
    name: 'my',
    children: [{
      path: '/myarticle',
      name: 'myarticle',
      component: myarticlePage,
    }, {
      path: '/herarticle',
      name: 'herarticle',
      component: herarticlePage,
    }, {
      path: '/mymoney',
      name: 'mymoney',
      component: mymoneyPage,
    }, {
      path: '/anniversary',
      name: 'anniversary',
      component: anniversaryPage,
    },],
    component: myPage,
  }
  ]
})

router.beforeEach((to, from, next) => {
  if (to.path === '/login' || to.path === '/community' || to.path === '/signup') {
    next()
  } else {
    let token = localStorage.getItem('token')
    let username = localStorage.getItem('username')
    if (token === 'specialforsignup') {
      next('/signup')
    } else if (token === null || token === '') {
      next('/login')
    } else if (username === null) {
      next('/community')
    } else {
      next();
    }
  }
});

export default router;
