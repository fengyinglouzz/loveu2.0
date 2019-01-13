// 顶部组件
<template>
  <div>
    <div class="topline">
      <a class="topline-img" href="/">
        <img src="@/assets/img/title.png">
      </a>
      <div class="topline-menu">
        <ul class="topline-menu-nav">
          <li class="a">
            <a href="https://www.baidu.com">热闻</a>
          </li>
          <li class="b">
            <a href="https://blog.csdn.net/qq_37684824">博客</a>
          </li>
        </ul>
      </div>
    </div>
<hr/>



  </div>
</template>

<script>
export default {
  
};
</script>

<style scoped>
.topline-menu-nav a {
  /* 让超链接没有下划线 */
  text-decoration: none;
  color: #5c5b5f;
  font-family:cursive;
  font-size: 25px;
}
.topline {
  /* 上移元素 */
  transform: translateY(-50%);
  -ms-transform: translateY(-50%);
  -webkit-transform: translateY(-50%);
  /* 子元素横向排列 */
  display: flex;
}
.topline-img {
  /* 左边留出空间 */
  margin-left: 30px;
}

.topline-img img {
  /* 设置图片大小 */
  width: 150px;
  height: 50px;
}
.topline-menu {
  /* 周围加多大的隔离框 */
  margin: 0;
}
.topline-menu-nav {
  /* 去掉li标签左侧的小黑点 */
  list-style: none;
  /* 横向排列li标签 */
  display: flex;
}
.topline-menu-nav li {
  /* 设置li左侧间距 */
  margin-left: 30px;
}
/* 钢针分割线 */
hr {
  border-top: 0px;
  FILTER: progid:DXImageTransform.Microsoft.Glow(color=#987cb9,strength=10);
  color:#987cb9 ;
}
</style>
