<template>
  <div class="login-container">
    <el-form :model="ruleForm" :rules="rule"
    status-icon
    ref="ruleForm"
    label-position="left"
    label-width="0px"
    class="login-page">
      <h3 class="title">登录</h3>
      <el-form-item prop="username">
        <el-input 
        type="text"
        v-model="ruleForm.username"
        placeholder="用户名"
        auto-complete
        ></el-input>
      </el-form-item>
      <el-form-item prop="password">
        <el-input 
        type="password"
        v-model="ruleForm.password"
        placeholder="密码"
        auto-complete="one"
        @keyup.enter.native="handleSubmit"
        ></el-input>
      </el-form-item>
      <el-checkbox v-model="checked" class="rememberme">记住密码</el-checkbox>
      <el-form-item style="width:100%;">
        <el-button type="primary" style="width:100%" @click="handleSubmit" :loading="logining">登录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      logining: false,
      token: '',
      ruleForm: {
        username: "",
        password: ""
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
    handleSubmit(event) {
      let that = this
      this.$refs.ruleForm.validate((valid) => {
        if(valid) {
          this.logining = true;
          this.$axios.post("/auth/login", {
            username: this.ruleForm.username,
            password: this.ruleForm.password
          })
          .then(res => {
            console.log(res)
            if(res.status != 200) {
              that.$message.error(res.data.message)
            } else {
              that.token = res.data.data['token']
              this.$store.commit('changeToken', that.token)
              this.$store.commit('changeUsername', that.ruleForm.username)
              this.$router.push({ path: '/test'})
            }
          })
        } else {
          console.log('error submit!');
          return false;
        }
      })
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