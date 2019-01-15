// vuex
import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

// 创建vuex的store
const store = new Vuex.Store({
    state: {
        count: 5,
        username: '',
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
        changeToken(state, token) {
            state.token = token
            localStorage.setItem('token', token)
        },
        changeUsername(state, username) {
            state.username = username
            localStorage.setItem('username', username)
        }
    },
    //有异步的时候
    actions: {
        increment(context) {
            context.commit('increment')
        },
        decrement(context) {
            setTimeout(function() {
                context.commit('decrement')
            }, 10)
        }
    },
    //通过getter进行数据获取
    getters: {
        getState(state) {
            return state.count > 0 ? state.count : 0;
        }
    }
})

export default store