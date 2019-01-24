<template>
  <div>
    <top-line></top-line>
    <el-container>
      <el-aside
        width="200px"
        style="background-color: rgb(238, 241, 246)"
      >
        <el-menu :default-openeds="this.openeds">
          <el-submenu index="1">
            <template slot="title">
              <svg
                class="icon"
                aria-hidden="true"
              >
                <use xlink:href="#icon-miao"></use>
              </svg>
              <!-- <i class="el-icon-message"></i> -->
              恋爱记
            </template>
            <el-menu-item-group>
              <el-submenu index="1-1">
                <template slot="title">
                  <svg
                    class="icon"
                    aria-hidden="true"
                  >
                    <use xlink:href="#icon-chakan"></use>
                  </svg>查看
                </template>
                <el-menu-item
                  index="1-1-1"
                  @click="goarticle('her')"
                >
                  <svg
                    class="icon"
                    aria-hidden="true"
                  >
                    <use xlink:href="#icon-xingbienv"></use>
                  </svg>她的
                </el-menu-item>
                <el-menu-item
                  index="1-1-2"
                  @click="goarticle('my')"
                >
                  <svg
                    class="icon"
                    aria-hidden="true"
                  >
                    <use xlink:href="#icon-xingbienan"></use>
                  </svg>我的
                </el-menu-item>
              </el-submenu>
              <el-menu-item
                index="1-2"
                disabled
              >
                <svg
                  class="icon"
                  aria-hidden="true"
                >
                  <use xlink:href="#icon-directmailyoujiantuisong"></use>
                </svg>推送
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <svg
                class="icon"
                aria-hidden="true"
              >
                <use xlink:href="#icon-tubiaozhizuomoban-"></use>
              </svg>
              我们
            </template>
            <el-menu-item-group>
              <el-tooltip
                class="item"
                effect="light"
                content="回家咯~"
                placement="right"
              >
                <el-menu-item
                  index="2-1"
                  @click="gohome()"
                >
                  <svg
                    class="icon"
                    aria-hidden="true"
                  >
                    <use xlink:href="#icon-fangzi"></use>
                  </svg>家园
                </el-menu-item>
              </el-tooltip>
              <el-menu-item
                index="2-2"
                @click="goanniversary()"
              >
                <svg
                  class="icon"
                  aria-hidden="true"
                >
                  <use xlink:href="#icon-rili"></use>
                </svg>纪念日
              </el-menu-item>
              <el-menu-item
                index="2-2"
                @click="gomoney()"
              >
                <svg
                  class="icon"
                  aria-hidden="true"
                >
                  <use xlink:href="#icon-jinbi"></use>
                </svg>小金库
              </el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-submenu
            index="3"
            disabled
          >
            <template slot="title">
              <svg
                class="icon"
                aria-hidden="true"
              >
                <use xlink:href="#icon-xiaoxi2"></use>
              </svg>我的消息
            </template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="分组2">
              <el-menu-item index="3-3">选项3</el-menu-item>
            </el-menu-item-group>
            <el-submenu index="3-4">
              <template slot="title">选项4</template>
              <el-menu-item index="3-4-1">选项4-1</el-menu-item>
            </el-submenu>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>

import TopLine from '@/components/TopLine'
export default {
  data () {
    return {
      useroute: 'true',
      openeds: ["1", "1-1"],
    }
  },
  components: {
    TopLine,
  },
  methods: {
    goarticle (one) {
      this.$router.push(`/${one}article`)
    },
    gohome () {
      this.$router.push('/home')
    },
    gomoney () {
      this.$router.push('/mymoney')
    },
    goanniversary () {
      this.$router.push('/anniversary')
    }
  }
}
</script>

<style scoped>
</style>
