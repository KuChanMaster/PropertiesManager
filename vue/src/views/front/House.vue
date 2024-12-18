<template>
  <div style="width: 70%; margin: 20px auto">
<!--取出管理员house功能的增，删，改只留下查询-->
    <div class="table">
      <el-table :data="tableData" stripe>
        <el-table-column prop="id" label="序号" width="80" align="center" sortable></el-table-column>
        <el-table-column prop="code" label="房屋号" show-overflow-tooltip></el-table-column>
        <el-table-column prop="userName" label="业主" show-overflow-tooltip></el-table-column>
        <el-table-column prop="building" label="楼栋号"></el-table-column>
        <el-table-column prop="unit" label="单元号"></el-table-column>
        <el-table-column prop="area" label="面积/㎡"></el-table-column>
        <el-table-column prop="type" label="房型"></el-table-column>
        <el-table-column prop="content" label="附加说明"></el-table-column>
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
  name: "House",
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
      this.$request.get('/house/selectPage', {
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
    // 分页
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>