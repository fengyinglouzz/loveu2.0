<template>
  <ul class="article-list">
    <li
      v-for="item in articleList"
      :key="item.title"
    >
      <div class="article-title">{{ item.title }}</div>
      <div class="article-abstract">{{ item.abstract }}</div>
      <div class="article-date">{{ item.date }}</div>
    </li>
  </ul>
</template>

<script>
export default {
  data () {
    return {
      articleList: [
        {
          "title": "标题一",
          "abstract": "离开静安寺砥砺奋进阿斯兰的会计法拉克圣诞节福利卡束带结发",
          "date": "2019-01-02 20:55"
        }, {
          "title": "标题二",
          "abstract": "asdfasdfwqsdlkfjaslkdjflkasjdflmzxc.v,m阿斯顿发斯蒂芬",
          "date": "2019-01-02 20:55"
        }, {
          "title": "标题三",
          "abstract": "完全二群翁人情味儿去玩儿去玩儿去完成V型这款车V领就撒地方撒旦离开就要",
          "date": "2019-01-02 20:55"
        }, {
          "title": "标题四",
          "abstract": "概阿斯蒂芬请问二千万请问去玩儿去玩儿去玩儿要",
          "date": "2019-01-02 20:55"
        },
      ]
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
.article-abstract {
  font-size: 20px;
  margin-top: 3px;
  margin-bottom: 8px;
}
.article-date {
  font-size: 20px;
  margin-top: 3px;
  margin-bottom: 16px;
}
</style>
