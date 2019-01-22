<template>
  <div>
    <top-line></top-line>
    <div class="page-title">社区现在没有人</div>
  </div>
</template>
<script>
import TopLine from '@/components/Topline'

export default {
  components: {
    TopLine,
  }
}
</script>

<style scoped>
.page-title {
  margin-right: auto;
  margin-left: auto;
  width: 300px;
  height: 30px;
  box-shadow: 0 0 3px #000 inset;
}
</style>
