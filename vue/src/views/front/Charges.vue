<template>
  <div style="width: 70%; margin: 20px auto">
    <div style="margin: 20px 0; font-size: 15px; color: #666666">我的缴费信息（{{ tableData.length }}）</div>
    <div class="table">
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="userName" label="业主姓名" show-overflow-tooltip></el-table-column>
        <el-table-column prop="project" label="收费项目" show-overflow-tooltip></el-table-column>
        <el-table-column prop="price" label="收费金额"></el-table-column>
        <el-table-column prop="content" label="收费说明"></el-table-column>
        <el-table-column prop="status" label="缴费状态">
          <template v-slot="scope">
            <el-tag type="danger" v-if="scope.row.status === '未缴费'">未缴费</el-tag>
            <el-tag type="success" v-else>已缴费</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="time" label="缴费时间" width="180px"></el-table-column>

        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" @click="pay(scope.row)" size="mini" :disabled="scope.row.status === '已缴费'">缴费</el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination" style="margin-top: 20px">
        <el-pagination
            background
            @current-change="handleCurrentChange"
            :current-page="pageNum"
            :page-sizes="[5, 10, 20]"
            :page-size="pageSize"
            layout="total, prev, pager, next"
            :total="total">
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Parking",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
    }
  },
  created() {
    this.load(1)
  },
  methods: {
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/charges/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userId: this.user.id
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
    pay(row) {
      this.$request.post('/charges/pay', row).then(res => {
        if (res.code === '200') {
          this.$message.success('操作成功')
          this.load(this.pageNum)
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>
