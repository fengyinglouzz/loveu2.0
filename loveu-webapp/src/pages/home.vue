<template>
  <div>
    <top-line></top-line>
    <el-container>
      <el-header>
        <a class="group-name">{{ this.groupname }}</a>
        <span>
          <el-badge
            :value="1"
            class="new-article"
          >
            <el-button size="small">恋爱记</el-button>
          </el-badge>
          <el-badge
            :value="2"
            class="new-message"
            type="primary"
          >
            <el-button size="small">私聊</el-button>
          </el-badge>
        </span>
        <el-button
          class="create-article"
          type="success"
        >写恋爱记</el-button>
      </el-header>
      <el-container>
        <el-aside width="400px">
          <el-carousel
            indicator-position="outside"
            height="600px"
          >
            <el-carousel-item
              v-for="item in imgsrc"
              :key="item.index"
            >
              <img :src="item.src">
            </el-carousel-item>
          </el-carousel>
        </el-aside>
        <el-main>
          <article-list></article-list>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>

<script>
import TopLine from '@/components/Topline'
import ArticleList from '@/components/ArticleList'

export default {
  data () {
    return {
      groupname: '',
      imgsrc: [
        {
          'index': '1',
          'src': require('@/assets/img/1.jpg'),
        },
        {
          'index': '2',
          'src': require('@/assets/img/2.jpg'),
        },
        {
          'index': '3',
          'src': require('@/assets/img/3.jpg'),
        }
      ],
    }
  },
  created () {
    this.groupname = this.$store.getters.getGroupName
  },
  components: {
    TopLine,
    ArticleList,
  }
}
</script>

<style scoped>
.group-name {
  margin-left: 50px;
  font-size: 40px;
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  color: coral;
}
.new-article {
  margin-left: 60px;
  margin-right: 40px;
}
.create-article {
  position: absolute;
  margin-top: 20px;
  right: 10%;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.el-header {
  margin-bottom: 50px;
}
</style>
