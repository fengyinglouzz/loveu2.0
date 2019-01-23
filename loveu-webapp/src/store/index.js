// vuex
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 创建vuex的store
const store = new Vuex.Store({
  state: {
    code: '',
    count: 5,
    username: localStorage.getItem('username') ? localStorage.getItem('username') : '',
    groupname: localStorage.getItem('groupname') ? localStorage.getItem('groupname') : '',
    token: localStorage.getItem('token') ? localStorage.getItem('token') : ''
  },
  //更改state的状态
  mutations: {
    increment(state) {
      state.count++
    },
    decrement(state) {
      state.count--
    },
    changeCode(state, code) {
      state.code = code
    },
    changeUsernameFromLs(state) {
      state.username = localStorage.getItem('username')
    },
    changeGroupnameFromLs(state) {
      state.groupname = localStorage.getItem('groupname')
    },
    changeToken(state, token) {
      state.token = token
      localStorage.setItem('token', token)
    },
    changeUsername(state, username) {
      state.username = username
      localStorage.setItem('username', username)
    },
    changeGroupname(state, groupname) {
      state.groupname = groupname
      localStorage.setItem('groupname', groupname)
    }
  },
  //有异步的时候
  actions: {
    increment(context) {
      context.commit('increment')
    },
    decrement(context) {
      setTimeout(function () {
        context.commit('decrement')
      }, 10)
    }
  },
  //通过getter进行数据获取
  getters: {
    getCode(state) {
      return state.code;
    },
    getState(state) {
      return state.count > 0 ? state.count : 0;
    },
    getGroupName(state) {
      return state.groupname;
    },
    getUsername(state) {
      return state.username;
    }
  }
})

export default store
