<template>
  <div id="app">
    <top-line></top-line>
    <router-view/>
  </div>
</template>

<script>
import topLine from '@/components/topline'
export default {
  components: {
    //这里如果是都小写就不行
    topLine,
  },
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

</style>
