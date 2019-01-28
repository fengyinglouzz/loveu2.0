<template>
  <div>
    <el-dialog
      title="提示"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose"
    >
      <span>你还没有自己的家园，是否找找你的另一半或者自己创建家园</span>
      <span
        slot="footer"
        class="dialog-footer"
      >
        <el-button
          type="primary"
          @click="goGroup()"
        >去看看~</el-button>
        <el-button @click="goCommunity()">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data () {
    return {
      dialogVisible: false,
    }
  },
  created () {
    this.dialogVisible = this.$store.getters.getGroupName === '没有组'
  },
  methods: {
    handleClose (done) {
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        })
        .catch(_ => { });
    },
    goGroup () {
      this.$router.push('/group')
    },
    goCommunity () {
      this.dialogVisible = false
      this.$router.push('/community')
    }
  }
}
</script>

<style>
</style>
