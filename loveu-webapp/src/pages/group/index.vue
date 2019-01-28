<template>
  <el-container>
    <el-header>
      <h1>找到她</h1>
    </el-header>
    <el-container>
      <el-aside></el-aside>
      <el-main>
        <div class="find-user">
          <el-col style="width:3%">
            <svg
              class="icon icon-password"
              aria-hidden="true"
            >
              <use xlink:href="#icon-tubiaozhizuomobanyihuifu-"></use>
            </svg>
          </el-col>
          <el-col
            span="5"
            style="margin-right:5px;"
          >
            <el-input
              type="text"
              v-model="username"
              placeholder="用户名"
              auto-complete="one"
              @keyup.enter.native="handleSubmit"
            ></el-input>
          </el-col>
          <el-col span="10">
            <el-button
              @click="findUser()"
              type="primary"
            >查找</el-button>
          </el-col>
        </div>
        <div class="user-info">
          <div
            class="user-info-name"
            @click="confirmJoin()"
          >
            <el-col span="2">
              <svg
                class="icon icon-password"
                aria-hidden="true"
              >
                <use xlink:href="#icon-tubiaozhizuomobanyihuifu-"></use>
              </svg>
            </el-col>
            <el-col
              span="15"
              class="user-info-username"
            >用户名:{{userinfo.username}}</el-col>
          </div>
          <div class="user-info-group">
            <el-col span="2">
              <svg
                class="icon icon-password"
                aria-hidden="true"
              >
                <use xlink:href="#icon-tubiaozhizuomobanyihuifu-"></use>
              </svg>
            </el-col>
            <el-col
              span="15"
              class="user-info-groupname"
            >所在家园:{{ userinfo.groupName }}</el-col>
          </div>
        </div>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data () {
    return {
      username: '',
      userinfo: {
        username: '',
        groupName: '',
      }
    }
  },
  methods: {
    findUser () {
      let that = this
      this.$axios({
        methods: 'put',
        url: '/auth',
        params: {
          username: that.username
        }
      }).then(res => {
        if (res.data.code != 200) {
          that.$message({
            message: res.data.message,
            type: 'error'
          });
        }
        that.userinfo.username = res.data.data.username
        if (res.data.data.group.groupName === '没有组') {
          that.userinfo.groupName = ''
          that.$message({
            message: '该用户暂没有创建家园',
            type: 'warning'
          });
        } else {
          that.userinfo.groupName = res.data.data.group.groupName
        }
      })
    },
    confirmJoin () {
      this.$msgbox({
        title: '',
        message: `是否加入${this.userinfo.username}的家园`,
        showCancelButton: true,
        confirmButtonText: '加入',
        cancelButtonText: '取消',
        beforeClose: (action, instance, done) => {
          if (action === 'confirm') {
            done();
          } else {
            done();
          }
        }
      }).then(action => {
        if (action === 'confirm') {
          this.$axios
            .post('/auth/group', {
              username: this.userinfo.username,
              groupName: this.userinfo.groupName
            })
            .then(res => {
              localStorage.setItem('groupname', res.data.data.group.groupName)
              this.$router.push('/community')
              this.$store.commit('changeGroupname', res.data.data.group.groupName)
            })
        }
      })
    }
  }
}
</script>

<style scoped>
ul li {
  /* 列表符的格式 */
  list-style-type: none;
  width: 20px;
}
.find-user {
  height: 40px;
}
.user-info {
  height: 40px;
  width: 500px;
}
.user-info-name {
  height: 40px;
  cursor: pointer;
}
.user-info-name:hover {
  color: aqua;
}

.user-info-username {
  margin-left: 10px;
  margin-top: 10px;
}

.user-info-groupname {
  margin-left: 10px;
  margin-top: 10px;
}
.icon-password {
  height: 22px;
  width: 22px;
  margin-top: 8px;
}
</style>
