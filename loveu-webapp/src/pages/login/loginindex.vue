<template>
  <div class="login-container">
    <el-form
      :model="loginForm"
      :rules="rule"
      status-icon
      ref="loginForm"
      label-position="left"
      label-width="0px"
      class="login-page"
    >
      <h3
        class="title-login"
        v-if="iflogin"
      >登录</h3>
      <h3
        class="title-signup"
        v-if="!iflogin"
      >注册</h3>
      <el-form-item
        prop="username"
        v-if="iflogin"
      >
        <el-input
          type="text"
          v-model="loginForm.username"
          placeholder="用户名"
          auto-complete
        ></el-input>
      </el-form-item>
      <el-form-item
        prop="password"
        v-if="iflogin"
      >
        <el-input
          type="password"
          v-model="loginForm.password"
          placeholder="密码"
          auto-complete="one"
          @keyup.enter.native="handleSubmit"
        ></el-input>
      </el-form-item>
      <el-checkbox
        v-model="checked"
        class="rememberme"
      >记住密码</el-checkbox>
      <el-form-item>
        <el-button
          type="text"
          @click="trans"
          v-if="iflogin"
        >还没有账号？点我注册</el-button>
        <el-button
          type="text"
          @click="trans"
          v-if="!iflogin"
        >点我返回登录</el-button>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          type="primary"
          style="width:100%"
          @click="handleSubmit"
          :loading="logining"
          v-if="iflogin"
        >登录</el-button>
        <el-button
          type="primary"
          style="width:100%"
          @click="handleSubmit"
          :loading="logining"
          v-if="!iflogin"
        >注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  created: function () { },
  data () {
    return {
      iflogin: true,
      logining: false,
      token: "",
      loginForm: {
        username: "",
        password: ""
      },
      signupForm: {
        email: "",
        username: "",
        password: "",
      },
      rule: {
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
    trans () {
      this.iflogin = !this.iflogin;
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
.login-container {
  width: 100%;
  height: 100%;
}
.login-page {
  -webkit-border-radius: 5px;
  border-radius: 5px;
  margin: 180px auto;
  width: 350px;
  padding: 35px 35px 15px;
  background: rgb(253, 217, 225);
  border: 1px solid #eaeaea;
  /* box-shadow: 0 0 25px #cac6c6; */
}
label.el-checkbox.rememberme {
  margin: 0px 0px 15px;
  text-align: left;
}
</style>
