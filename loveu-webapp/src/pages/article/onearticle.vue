<template>
  <div
    v-html="content"
    class="ql-editor"
  >{{ content }}</div>
</template>

<script>
export default {
  data () {
    return {
      content: '<p>2341243216543465214346542165443621434621346542163442164453</p><p>2143321334212131432114332114331243221334212132431321234</p><p>sdfsadfsadfsadf</p><p>撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方撒打发士大夫撒地方撒地方</p>'
    }
  },
  created () {
    // you can use current editor object to do something(editor methods)
    this.getArticleById()
    // this.editor to do something...
  },
  methods: {
    // 转码 暂时用不到 https://blog.csdn.net/senmage/article/details/82388728
    escapeStringHTML (str) {
      str = str.replace(/</g, '<');
      str = str.replace(/>/g, '>');
      return str;
    },
    getArticleById () {
      this.$axios({
        methods: 'get',
        url: `/article/${this.$store.getters.getArticleId}`,
      }).then(res => {
        this.content = res.data.data.content
      })
    },
  }
}
</script>

<style scoped>
</style>
