<template>
  <div class="feedback-container">
    <div class="feedback-card">
      <h4 class="feedback-title">欢迎您发表您的宝贵意见</h4>
      <div class="feedback-input-container">
        <el-input
            v-model="content"
            :rows="4"
            type="textarea"
            placeholder="请输入您的意见/投诉"
            class="feedback-input">
        </el-input>
      </div>
      <div class="feedback-submit">
        <el-button type="primary" size="large" @click="commit" class="submit-button">提交反馈</el-button>
      </div>
    </div>
    <div class="feedback-summary-container">
      <div class="feedback-summary">您的反馈总数: {{ tableData.length }} 条</div>
      <el-table :data="tableData" stripe class="feedback-table">
        <el-table-column prop="content" label="反馈内容" show-overflow-tooltip></el-table-column>
        <el-table-column prop="time" label="反馈时间" width="150px"></el-table-column>
        <el-table-column prop="status" label="处理状态" width="100px">
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.status === '已处理'">已处理</el-tag>
            <el-tag type="danger" v-else>待处理</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      content: null,
      tableData: []
    };
  },
  created() {
    this.load();
  },
  methods: {
    load() {
      this.$request.get("/complaint/selectAll").then(res => {
        if (res.code === '200') {
          this.tableData = res.data;
        } else {
          this.$message.error(res.msg);
        }
      });
    },
    commit() {
      if (!this.content) {
        this.$message.warning("请输入反馈内容");
        return;
      }
      const data = { content: this.content };
      this.$request.post("/complaint/add", data).then(res => {
        if (res.code === '200') {
          this.$message.success("反馈成功，我们会尽快处理您反馈的问题");
          this.content = null;
          this.load();
        } else {
          this.$message.error(res.msg);
        }
      });
    }
  }
};
</script>

<style scoped>
.feedback-container {
  width: 70%; /* 增加宽度 */
  margin: 50px auto;
  font-family: Arial, sans-serif;
  color: #333;
  background: #f9fafc;
  padding: 40px; /* 增加内边距 */
  border-radius: 12px; /* 圆角加大 */
  box-shadow: 0 4px 16px rgba(0, 0, 0, 0.1); /* 阴影加深 */
}

.feedback-card {
  background: #ffffff;
  padding: 40px; /* 增加内边距 */
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 40px; /* 增加间距 */
}

.feedback-title {
  font-size: 28px; /* 字体大小加大 */
  margin-bottom: 30px;
  text-align: center;
  color: #555;
  font-weight: bold;
}

.feedback-input-container {
  margin-bottom: 30px; /* 增加间距 */
}

.feedback-input {
  width: 100%;
  height: 120px; /* 增加高度 */
  border-radius: 8px; /* 圆角加大 */
  border: 1px solid #ddd;
  padding: 15px; /* 增加内边距 */
}

.feedback-submit {
  text-align: center;
}

.submit-button {
  font-size: 18px; /* 字体大小加大 */
  padding: 15px 40px; /* 增加内边距 */
}

.feedback-summary-container {
  background: #ffffff;
  padding: 40px; /* 增加内边距 */
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.feedback-summary {
  font-size: 20px; /* 字体大小加大 */
  margin-bottom: 30px;
  font-weight: bold;
  text-align: center;
}

.feedback-table {
  margin-top: 30px; /* 增加间距 */
}
</style>



