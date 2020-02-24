<template>
  <div id="app">
    <router-view></router-view>
    <foot-line></foot-line>
  </div>
</template>

<script>
import FootLine from '@/components/FootLine'

export default {
  name: 'App',
  components: {
    FootLine,
  },
}
</script>

<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /* 居中 这里禁用 */
  /* text-align: center; */
  color: #2c3e50;
  /* margin-top: 60px; */
}
.icon {
  width: 30px;
  height: 30px;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
a {
  text-decoration: none;
  color: coral;
}
</style>
