// 底部组件
<template>
  <div class="beian">
    <a href="http://www.beian.miit.gov.cn">{{beianhao}}</a>
  </div>
</template>

<script>
export default {
  data () {
    return {
      beianhao: '京ICP备18059511号-1',
    }
  },
}
</script>

<style>
.beian {
  width: 100%;
  position: absolute;
  bottom: 0;
  color: darkmagenta;
  font-size: 15px;
}
</style>
