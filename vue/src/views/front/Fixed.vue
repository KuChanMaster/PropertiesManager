<template>
  <div class="repair-form-container">
    <h2>报修需求提交</h2>
    <p class="description">如果您有报修需求，欢迎提交给我们</p>
    <el-input
        type="textarea"
        :rows="4"
        v-model="content"
        placeholder="请输入您报修诉求，包括联系方式，麻烦详细填写"
        class="repair-textarea"
    ></el-input>
    <div class="submit-button-container">
      <el-button type="primary" @click="commit" class="submit-button">提交</el-button>
    </div>
    <h3 class="history-title">历史报修问题处理明细：{{ tableData.length }}</h3>
    <el-table :data="tableData" stripe class="repair-table">
      <el-table-column prop="content" label="报修内容" show-overflow-tooltip></el-table-column>
      <el-table-column prop="time" label="提交时间" width="150px" show-overflow-tooltip></el-table-column>
      <el-table-column prop="name" label="业主姓名" width="100px"></el-table-column>
      <el-table-column prop="status" label="处理状态" width="100px">
        <template v-slot="scope">
          <el-tag type="success" v-if="scope.row.status === '已处理'">已处理</el-tag>
          <el-tag type="danger" v-else>待处理</el-tag>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      content: null,
      tableData: []
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      this.$request.get('/fixed/selectAll').then(res => {
        if (res.code === '200') {
          this.tableData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    commit() {
      let data = {
        content: this.content
      }
      this.$request.post('/fixed/add', data).then(res => {
        if (res.code === '200') {
          this.$message.success('提交成功，我们会认真尽快联系您')
          this.content = null
          this.load()
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>
.repair-form-container {
  width: 70%; /* 增加宽度 */
  margin: 30px auto;
  padding: 40px; /* 增加内边距 */
  border-radius: 12px; /* 圆角加大 */
  box-shadow: 0 4px 16px 0 rgba(0, 0, 0, 0.1); /* 阴影加深 */
  background-color: #f9fafc;
}

.description {
  color: #666666;
  font-size: 18px; /* 字体大小加大 */
  margin-bottom: 30px; /* 增加间距 */
}

.repair-textarea {
  margin-bottom: 30px; /* 增加间距 */
}

.submit-button-container {
  display: flex;
  justify-content: flex-end;
  margin-bottom: 40px; /* 增加间距 */
}

.history-title {
  font-size: 18px; /* 字体大小加大 */
  font-weight: bold;
  color: #666666;
  margin-bottom: 30px; /* 增加间距 */
}

.repair-table {
  width: 100%;
}
</style>



