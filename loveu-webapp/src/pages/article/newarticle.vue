<template>
  <el-container>
    <el-header>
      <div class="title">写博客</div>
    </el-header>
    <el-container>
      <el-aside style="width:200px;background-color: darkgray;height:1000px;"></el-aside>
      <el-main>
        <el-button @click="addArticle">发布</el-button>
        <quill-editor
          v-model="content"
          ref="myQuillEditor"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @change="onEditorChange($event)"
        ></quill-editor>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import { quillEditor } from 'vue-quill-editor'
export default {
  data () {
    return {
      title: '标题',
      content: null,
      editorOption: {}
    }
  },
  methods: {
    onEditorBlur () {//失去焦点事件
      console.log('失去')
    },
    onEditorFocus () {//获得焦点事件
      console.log('获得')
    },
    onEditorChange () {//内容改变事件
      console.log('改变')
    },
    addArticle () {
      this.$axios
        .post('/article', {
          title: this.title,
          content: this.content,
          groupName: this.$store.getters.getGroupName,
          username: this.$store.getters.getUsername
        })
        .then(res => {
          if (res.data.code != 200) {
            this.$message.error(res.data.message);
          } else {
            this.$message({
              message: '发布成功',
              type: 'success'
            });
            this.$router.push('/home')
          }
        })
    }
  }
}
</script>

<style scoped>
.title {
  font-size: 35px;
  font-weight: bold;
  font-family: "Courier New", Courier, monospace;
}
</style>
