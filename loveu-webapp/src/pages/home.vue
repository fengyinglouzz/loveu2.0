<template>
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
    </el-header>
    <el-container>
      <el-aside width="200px">
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
      <el-main>Main</el-main>
    </el-container>
  </el-container>
</template>

<script>
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
  }
}
</script>

<style scoped>
.group-name {
  font-size: 50px;
  color: coral;
}
.new-article {
  margin-left: 50px;
  margin-right: 50px;
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
  margin-bottom: 100px;
}
</style>
