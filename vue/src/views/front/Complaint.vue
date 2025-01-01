<template>
  <div style="width: 40%;margin: 30px auto">
    <h4 style="color: #666666;font-size: 20px">欢迎您发表您的宝贵意见</h4>
    <div style="margin-top: 20px">
      <el-input v-model="content" :row="4" type="textarea" placeholder="请输入您的意见/投诉"
                style="width: 100%;height: 100px"></el-input>
    </div>
    <div style="margin-top: 20px;text-align:right">
      <el-button style="padding: 12px 25px" type="primary" @click="commit">提交</el-button>
    </div>
    <div style="front-size:16px;margin-top: 30px;font-weight: bold;color: #666666">
      您的反馈:{{ tableData.length }}}
    </div>
    <div style="font-size: 18px;margin-top: 20px">
      <el-table :data="tableData" stripe>
        <el-table-column prop="content" label="反馈内容" show-overflow-tooltip></el-table-column>
        <el-table-column prop="time" label="反馈时间" width="150px"></el-table-column>
        <el-table-column prop="status" label="处理状态" width="100px">
          <template v-slot="scope">
            <el-tag type="success" v-if="scope.row.status==='已处理'">已处理</el-tag>
            <el-tag type="danger" v-else>待处理</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "Complaint",
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
      this.$request.get("/complaint/selectAll").then(res => {
        if (res.code === '200') {
          this.tableData = res.data
        } else {
          this.$massage.error(res.msg)
        }
      })
    },
    commit() {
      let data = {
        content: this.content
      }
      this.$request.post("/complaint/add", data).then(res => {
        if (res.code === '200') {
          this.massage.success("反馈成功，我们会尽快处理您反馈的问题")
          this.content = null
          this.load()
        } else {
          this.massage.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>