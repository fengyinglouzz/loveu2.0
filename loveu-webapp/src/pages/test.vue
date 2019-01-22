<template>
  <el-button @click="print">test</el-button>
</template>

<script>
export default {
  methods: {
    add () {
      this.$store.commit('increment')
      console.log(this.$store.getters.getState)
    },
    print () {
      this.$axios.get("/test/zzy")
        .then(res => {
          console.log(res.data)
        })
    }
  }
}
</script>
