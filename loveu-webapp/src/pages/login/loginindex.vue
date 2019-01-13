//登录组件
<template>
  <div class="main">
    <h4 class="title">
      <div class="normal-title">
        <a class="normal-title-login" href="/">登录</a>
        <b>|</b>
        <a class="normal-title-signup" href="/">注册</a>
      </div>
    </h4>
    <div class="login-container">
      <form>
        <div class="input-email">
          <input placeholder="手机号或邮箱">
        </div>
        <div class="input-password">
          <input placeholder="密码">
        </div>
      </form>
    </div>
      <el-button type="primary" @click="login">登录</el-button>

  </div>
</template>

<script>
export default {
  data() {
    return {
    };
  },
  methods: {
    login() {
      this.$axios.get('/test/zzy')
        .then(response => {
          console.log(response.data)
        })
        .catch((error) => {
          console.log(error)
        });
    }
  },
  components: {}
};
</script>

<style scoped>
.normal-title .normal-title-login {
  font-weight: 700;
  color: #ea6f5a;
}
.normal-title .normal-title-signup {
  font-weight: 700;
  color: #7ecc35;
}
.main {
  width: 400px;
  margin: 60px auto 0;
  /* 上 左右 下 的内边距 */
  padding: 50px 50px 30px;
  background-color: rgb(255, 212, 241);
  /* 圆角边框 */
  border-radius: 15px;
  box-shadow: 0 0 8px rgba(0, 0, 0, 0.1);
  vertical-align: middle;
  display: inline-block;
}
.main .el-button {
    margin: 30px auto 0;
    border-radius: 10px;
}
.normal-title a {
  /* 让超链接没有下划线 */
  text-decoration: none;
  color: #5c5b5f;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  font-size: 22px;
}
.normal-title b {
  font-size: 20px;
  margin-left: 20px;
  margin-right: 20px;
}
.login-container {
  background-color: #141a48;
  margin: 0px;
  overflow: hidden;
}
.login-container .input-email input {
  width: 100%;
  height: 40px;
  margin-bottom: 0;
  padding: 4px 12px 4px 35px;
  border: 1px solid #c8c8c8;
  border-radius: 0 0 4px 4px;
  vertical-align: middle;
}
.login-container .input-password input {
  width: 100%;
  height: 40px;
  margin-bottom: 0;
  padding: 4px 12px 4px 35px;
  border: 1px solid #c8c8c8;
  border-radius: 0 0 4px 4px;
  vertical-align: middle;
}
</style>

