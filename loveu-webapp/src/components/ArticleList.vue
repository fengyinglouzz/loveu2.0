<template>
  <ul class="article-list">
    <h1>{{ this.person }}</h1>
    <li
      v-for="item in articleList"
      :key="item.id"
    >
      <div
        class="article-one"
        @click="gothisarticle(item.id)"
      >
        <div class="article-title">{{ item.title }}</div>
        <div class="article-content">{{ item.content }}</div>
        <div class="article-date">{{ item.createDate }}</div>
      </div>
      <hr
        style="FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10);opacity:0.2;"
        width="100%"
        color="#987cb9"
        size="1"
      >
    </li>
  </ul>
</template>

<script>
export default {
  created () {
    // 拿到路径尾部的那个字符串
    switch (this.$router.history.current.name) {
      case 'home': this.person = 'home'; break
      case 'herarticle': this.person = 'she'; break
      case 'myarticle': this.person = 'my'; break
    };
    this.getArticleList()
  },
  data () {
    return {
      person: '',
      articleList: [],
    }
  },
  methods: {
    getArticleList () {
      this.$axios({
        methods: 'get',
        url: '/article',
        params: {
          groupName: this.$store.getters.getGroupName
        }
      }).then(res => {
        this.articleList = res.data.data
      })
    },
    gothisarticle (id) {
      this.$store.commit('changeArticleId', id)
      this.$router.push('/onearticle')
    }
  }
}
</script>

<style scoped>
ul li {
  /* 列表符的格式 */
  list-style-type: none;
}
.article-title {
  font-size: 30px;
  font-weight: 1000;
  margin-bottom: 25px;
}
.article-content {
  font-size: 20px;
  margin-top: 3px;
  margin-bottom: 8px;
  overflow: hidden; /*内容超出后隐藏*/
  text-overflow: ellipsis; /* 超出内容显示为省略号*/
  white-space: nowrap; /*文本不进行换行*/
}
.article-date {
  font-size: 20px;
  margin-top: 15px;
  margin-bottom: 16px;
}
.article-one {
  display: block;
  padding: 12px 15px;
  margin-bottom: 1px;
}
.article-one:hover {
  background-color: rgb(248, 248, 248);
}
</style>
