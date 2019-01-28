// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import App from './App'
import router from './router'
import store from './store'
import './assets/icon/iconfont.css'
import './assets/icon/iconfont.js'
// import 'font-awesome/css/font-awesome.min.css'
// import 'vue-awesome/icons'
// import Icon from 'vue-awesome/components/Icon'
import VueQuillEditor from 'vue-quill-editor'

// require styles 引入样式
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

// Vue.component('icon', Icon)
Vue.use(VueQuillEditor)

// 引用axios，并设置基础URL为后端服务api地址
axios.defaults.baseURL = 'http://47.94.104.248:666'
axios.defaults.headers.common['token'] = store.state.token
// 将API方法绑定到全局
Vue.prototype.$axios = axios

Vue.config.productionTip = false

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  // 注入 store
  store,
  render: h => h(App),
  template: '<App/>'
})
