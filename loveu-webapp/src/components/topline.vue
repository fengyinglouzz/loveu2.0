// 顶部组件
<template>
  <div>
    <div class="topline">
      <a
        class="topline-img"
        href="/"
      >
        <img src="@/assets/img/title.png">
      </a>
      <div class="topline-menu">
        <ul class="topline-menu-nav">
          <li class="a">
            <a href="https://www.baidu.com">热闻</a>
          </li>
          <li class="b">
            <a href="https://blog.csdn.net/qq_37684824">博客</a>
          </li>
        </ul>
      </div>
      <div class="topline-auth">
        <div
          class="topline-auth-auth"
          v-if="isLogin"
        >
          <a class="topline-auth-auth-sth">zzy</a>
          <div class="topline-auth-auth-sth">|</div>
          <a
            @click="logout"
            class="topline-auth-auth-sth"
            onMouseOver="this.style.color='#F00'"
            onMouseOut="this.style.color='rgb(77, 180, 187)'"
          >退出</a>
        </div>
        <div v-if="!isLogin && !isLoginPage">
          <a
            href="/#/login"
            class="topline-auth-auth-sth"
            onMouseOver="this.style.color='#F00'"
            onMouseOut="this.style.color='rgb(77, 180, 187)'"
          >登陆</a>
        </div>
      </div>
    </div>
    <hr>
  </div>
</template>

<script>
export default {
  computed: {
    isLogin () {
      return this.$store.getters.getUsername;
    },
    isLoginPage () {
      return this.$router.currentRoute.path === '/login';
    }
  },
  data () {
    return {
    }
  },
  methods: {
    logout () {
      localStorage.removeItem('username');
      localStorage.removeItem('groupname');
      this.$store.commit('changeUsernameFromLs');
      this.$router.push({ path: "/community" });
    },
  }
};
</script>

<style scoped>
.topline-menu-nav a {
  /* 让超链接没有下划线 */
  text-decoration: none;
  color: #5c5b5f;
  font-family: cursive;
  font-size: 25px;
}
.topline {
  /* 上移元素 */
  transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  -webkit-transform: translateY(-50%);
  /* 子元素横向排列 */
  display: flex;
}
.topline-img {
  /* 左边留出空间 */
  margin-left: 30px;
}

.topline-img img {
  /* 设置图片大小 */
  width: 150px;
  height: 50px;
}
.topline-menu {
  /* 周围加多大的隔离框 */
  margin: 0;
}
.topline-menu-nav {
  /* 去掉li标签左侧的小黑点 */
  list-style: none;
  /* 横向排列li标签 */
  display: flex;
}
.topline-menu-nav li {
  /* 设置li左侧间距 */
  margin-left: 30px;
}
.topline-auth {
  /* 块级元素 */
  display: block;
  position: absolute;
  right: 5%;
  top: 38%;
}
.topline-auth-auth-sth {
  /* 横向排列元素 */
  float: left;
  color: rgb(77, 180, 187);
  margin-left: 10px;
  margin-right: 10px;
}
.topline-auth-auth a {
  cursor: pointer;
}
/* 钢针分割线 */
hr {
  border-top: 0px;
  filter: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10);
  color: #987cb9;
}
</style>
