<template>
  <div class="signup-container">
    <div>
      <!-- <i class="iconfont icon-yuzhouxingqiu-04"></i> -->
      <i class="fa fa-spinner fa-pulse fa-4x"></i>
    </div>
    <el-form
      :model="signupForm"
      :rules="ruleforsignup"
      status-icon
      ref="signupForm"
      label-position="left"
      label-width="0px"
      class="signup-page"
    >
      <h3 class="title-signup">注册</h3>
      <el-form-item prop="email">
        <el-col :span="14">
          <el-input
            class="input-email"
            type="text"
            v-model="signupForm.email"
            placeholder="邮箱"
            auto-complete
          ></el-input>
        </el-col>
        <el-col :span="10">
          <el-button
            type="warning"
            v-if="!this.hassend"
            @click="sendemail"
          >发送验证码</el-button>
        </el-col>
        <el-col :span="10">
          <el-button
            type="warning"
            v-if="this.hassend"
            @click="sendemail"
            disabled
          >已发送</el-button>
        </el-col>
      </el-form-item>
      <el-form-item prop="code">
        <el-col :span="23">
          <el-input
            type="text"
            v-if="this.hassend"
            v-model="signupForm.code"
            placeholder="请输入邮件中的验证码"
            auto-complete
          ></el-input>
        </el-col>
      </el-form-item>
      <el-form-item prop="username">
        <el-col :span="23">
          <el-input
            type="text"
            v-model="signupForm.username"
            placeholder="用户名"
            auto-complete
          ></el-input>
        </el-col>
      </el-form-item>
      <el-form-item prop="password">
        <el-col :span="23">
          <el-input
            type="password"
            v-model="signupForm.password"
            placeholder="密码"
            auto-complete="one"
            @keyup.enter.native="handleSubmit"
          ></el-input>
        </el-col>
      </el-form-item>
      <el-form-item prop="confirmpass">
        <el-col :span="23">
          <el-input
            type="password"
            v-model="signupForm.confirmpass"
            placeholder="确认密码"
            auto-complete="one"
            @keyup.enter.native="handleSubmit"
          ></el-input>
        </el-col>
      </el-form-item>
      <el-form-item>
        <el-button
          type="text"
          @click="gologin"
        >点我返回登录</el-button>
      </el-form-item>
      <el-form-item style="width:100%;">
        <el-button
          type="primary"
          style="width:100%"
          @click="handleSubmit"
          :loading="signuping"
        >注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data () {
    return {
      hassend: false,
      blank: '1',
      signuping: false,
      token: "",
      signupForm: {
        email: "",
        code: "",
        username: "",
        password: "",
        confirmpass: "",
      },
      ruleforsignup: {
        email: [
          {
            required: true,
            message: "邮箱不能为空",
            trigger: "blur"
          }
        ],
        code: [
          {
            required: true,
            message: "验证码不能为空",
            trigger: "blur"
          }
        ],
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
        ],
      },
    };
  },
  methods: {
    sendemail () {
      this.hassend = true;
      this.$axios
        .post(`/mail/${this.signupForm.email}`)
        .then(res => {
          if (res.data.code != 200) {
            that.$message.error(res.data.message);
          } else {
            this.$store.commit('changeCode', res.data.data.code)
          }
        })
    },
    gologin () {
      localStorage.removeItem('token')
      this.$router.push('/#/login')
    },
    handleSubmit () {
      if (this.signupForm.code != this.$store.getters.getCode) {
        this.$message({
          message: '验证失败，请检查验证码',
          type: 'warning'
        })
      } else {
        if (this.signupForm.confirmpass != this.signupForm.password) {
          this.$message({
            message: '两次输入的密码不一致~',
            type: 'warning'
          })
        } else {
          let that = this;
          this.$refs.signupForm.validate(valid => {
            if (valid) {
              this.logining = true;
              this.$axios
                .post("/auth/signup", {
                  username: this.signupForm.username,
                  password: this.signupForm.password,
                  email: this.signupForm.email,
                })
                .then(res => {
                  console.log(res);
                  if (res.data.code != 200) {
                    that.$message.error(res.data.message);
                    this.logining = false;
                  } else {
                    that.token = res.data.data["token"];
                    this.$store.commit("changeToken", that.token);
                    this.$store.commit("changeUsername", that.signupForm.username);
                    this.$router.push('/#/community');
                  }
                })
            } else {
              console.log("error submit!");
              return false;
            }
          })
        }
      }
    }
  }
}
</script>

<style scoped>
.icon-yuzhouxingqiu-04 {
  font-size: 50px;
}
.signup-container {
  width: 100%;
  height: 100%;
  /* 全屏背景图 */
  /* background: #fff url("../../assets/img/heart.jpg") no-repeat; */
  /* background-size: cover; */
}
.signup-page {
  -webkit-border-radius: 5px;
  text-align: center;
  border-radius: 30px;
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
