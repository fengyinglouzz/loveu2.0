<template>
  <div class="login-container">
    <div>
      <i class="fa fa-spinner fa-pulse fa-4x"></i>
      <svg
        class="icon"
        style="height:4em;width:4em;"
        aria-hidden="true"
      >
        <use xlink:href="#icon-yuzhouxingqiu-04"></use>
      </svg>
    </div>
    <el-form
      :model="loginForm"
      :rules="ruleforlogin"
      status-icon
      ref="loginForm"
      label-position="left"
      label-width="0px"
      class="login-page"
    >
      <h3 class="title-login">登录</h3>
      <el-form-item prop="username">
        <el-col span="2">
          <svg
            class="icon icon-user"
            aria-hidden="true"
          >
            <use xlink:href="#icon--"></use>
          </svg>
          <!-- <i class="iconfont icon--"></i> -->
        </el-col>
        <el-col span="21">
          <el-input
            class="input-username"
            type="text"
            v-model="loginForm.username"
            placeholder="用户名"
            auto-complete
          ></el-input>
        </el-col>
      </el-form-item>
      <el-form-item prop="password">
        <el-col span="2">
          <svg
            class="icon icon-password"
            aria-hidden="true"
          >
            <use xlink:href="#icon-tubiaozhizuomobanyihuifu-"></use>
          </svg>
        </el-col>
        <el-col span="21">
          <el-input
            type="password"
            v-model="loginForm.password"
            placeholder="密码"
            auto-complete="one"
            @keyup.enter.native="handleSubmit"
          ></el-input>
        </el-col>
      </el-form-item>
      <el-checkbox
        v-model="checked"
        class="rememberme"
      >记住密码</el-checkbox>
      <el-form-item>
        <el-button
          type="text"
          @click="gosignup"
        >还没有账号？点我注册</el-button>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          type="primary"
          style="width:100%"
          @click="handleSubmit"
          :loading="logining"
        >登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      blank: '1',
      logining: false,
      token: "",
      loginForm: {
        username: "",
        password: ""
      },
      ruleforlogin: {
        username: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur"
          }
        ],
        password: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur"
          }
        ]
      },
      checked: false
    };
  },
  methods: {
    gosignup () {
      localStorage.setItem('token', 'specialforsignup')
      this.$router.push('/#/signup')
    },
    handleSubmit (event) {
      let that = this;
      this.$refs.loginForm.validate(valid => {
        if (valid) {
          this.logining = true;
          this.$axios
            .post("/auth/login", {
              username: this.loginForm.username,
              password: this.loginForm.password
            })
            .then(res => {
              console.log(res);
              if (res.data.code != 200) {
                that.$message.error(res.data.message);
                this.logining = false;
              } else {
                that.token = res.data.data["token"];
                this.$store.commit("changeToken", that.token);
                this.$store.commit("changeUsername", that.loginForm.username);
                this.$axios
                  .get(`/group/${this.loginForm.username}`)
                  .then(res => {
                    if (res.data.code != 200) {
                      that.$message.error(res.data.message);
                      this.logining = false;
                    } else {
                      this.$store.commit("changeGroupname", res.data.data);
                      this.$router.push({ path: "/home" });
                    }
                  })
                // this.$store.commit("changeGroupname", that.loginForm.username);
              }
            });
        } else {
          console.log("error submit!");
          return false;
        }
      });
    }
  }
};
</script>

<style scoped>
.icon-yuzhouxingqiu-04 {
  font-size: 50px;
}
.login-container {
  width: 100%;
  height: 100%;
  /* 全屏背景图 */
  /* background: #fff url("../../assets/img/heart.jpg") no-repeat; */
  /* background-size: cover; */
}
.login-page {
  -webkit-border-radius: 5px;
  text-align: center;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: rgb(253, 217, 225);
  border: 1px solid #eaeaea;
  /* box-shadow: 0 0 25px #cac6c6; */
}
.icon-user {
  margin-top: 6px;
}
.icon-password {
  height: 22px;
  width: 22px;
  margin-top: 8px;
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>
