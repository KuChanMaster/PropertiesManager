<template>
  <div>
    <div class="search">
      <el-input placeholder="请输入业主姓名查询" style="width: 200px" v-model="userName"></el-input>
      <el-input placeholder="请输入收费项目查询" style="width: 200px; margin: 0 5px" v-model="project"></el-input>
      <el-input placeholder="请输入缴费状态查询" style="width: 200px" v-model="status"></el-input>
      <el-button type="info" plain style="margin-left: 10px" @click="load(1)">查询</el-button>
      <el-button type="warning" plain style="margin-left: 10px" @click="reset">重置</el-button>
    </div>

    <div class="operation">
      <el-button type="primary" plain @click="handleAdd">新增</el-button>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
    </div>

    <div class="table">
      <el-table :data="tableData" stripe @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center"></el-table-column>
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
        <el-table-column prop="time" label="缴费时间"></el-table-column>

        <el-table-column label="操作" width="180" align="center">
          <template v-slot="scope">
            <el-button plain type="primary" @click="handleEdit(scope.row)" size="mini"
                       :disabled="scope.row.status === '已缴费'">编辑
            </el-button>
            <el-button plain type="danger" size="mini" @click=del(scope.row.id)
                       :disabled="scope.row.status === '已缴费'">删除
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <div class="pagination">
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


    <el-dialog title="收费信息" :visible.sync="fromVisible" width="40%" :close-on-click-modal="false" destroy-on-close>
      <el-form label-width="100px" style="padding-right: 50px" :model="form" :rules="rules" ref="formRef">
        <el-form-item prop="userId" label="选择业主">
          <el-select v-model="form.userId" placeholder="请选择业主" style="width: 100%">
            <el-option v-for="item in userData" :key="item.value" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="project" label="收费项目">
          <el-select v-model="form.project" placeholder="请选择项目" style="width: 100%">
            <el-option label="物业费" value="物业费"></el-option>
            <el-option label="车位费" value="车位费"></el-option>
            <el-option label="燃气费" value="燃气费"></el-option>
            <el-option label="水费" value="水费"></el-option>
            <el-option label="电费" value="电费"></el-option>
            <el-option label="其他" value="其他"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item prop="price" label="收费金额">
          <el-input v-model="form.price" placeholder="收费金额" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item prop="content" label="收费说明">
          <el-input type="textarea" :rows="4" v-model="form.content" placeholder="收费说明"
                    autocomplete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="fromVisible = false">取 消</el-button>
        <el-button type="primary" @click="save">确 定</el-button>
      </div>
    </el-dialog>


  </div>
</template>

<script>
export default {
  name: "Charges",
  data() {
    return {
      tableData: [],  // 所有的数据
      pageNum: 1,   // 当前的页码
      pageSize: 10,  // 每页显示的个数
      total: 0,
      userName: null,
      project: null,
      status: null,
      fromVisible: false,
      form: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      rules: {
        userId: [
          {required: true, message: '请选择业主', trigger: 'blur'},
        ],
        project: [
          {required: true, message: '请选择收费项目', trigger: 'blur'},
        ],
        price: [
          {required: true, message: '请输入收费金额', trigger: 'blur'},
        ],
        content: [
          {required: true, message: '请输入收费说明', trigger: 'blur'},
        ]
      },
      ids: [],
      userData: []
    }
  },
  created() {
    this.load(1)
    this.loadUser()
  },
  methods: {
    loadUser() {
      this.$request.get('/user/selectAll').then(res => {
        if (res.code === '200') {
          this.userData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    handleAdd() {   // 新增数据
      this.form = {
        status: '未缴费'
      }  // 新增数据的时候清空数据
      this.fromVisible = true   // 打开弹窗
    },
    handleEdit(row) {   // 编辑数据
      this.form = JSON.parse(JSON.stringify(row))  // 给form对象赋值  注意要深拷贝数据
      this.fromVisible = true   // 打开弹窗
    },
    save() {   // 保存按钮触发的逻辑  它会触发新增或者更新
      this.$refs.formRef.validate((valid) => {
        if (valid) {
          this.$request({
            url: this.form.id ? '/charges/update' : '/charges/add',
            method: this.form.id ? 'PUT' : 'POST',
            data: this.form
          }).then(res => {
            if (res.code === '200') {  // 表示成功保存
              this.$message.success('保存成功')
              this.load(1)
              this.fromVisible = false
            } else {
              this.$message.error(res.msg)  // 弹出错误的信息
            }
          })
        }
      })
    },
    del(id) {   // 单个删除
      this.$confirm('您确定删除吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/charges/delete/' + id).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    handleSelectionChange(rows) {   // 当前选中的所有的行数据
      this.ids = rows.map(v => v.id)   //  [1,2]
    },
    delBatch() {   // 批量删除
      if (!this.ids.length) {
        this.$message.warning('请选择数据')
        return
      }
      this.$confirm('您确定批量删除这些数据吗？', '确认删除', {type: "warning"}).then(response => {
        this.$request.delete('/charges/delete/batch', {data: this.ids}).then(res => {
          if (res.code === '200') {   // 表示操作成功
            this.$message.success('操作成功')
            this.load(1)
          } else {
            this.$message.error(res.msg)  // 弹出错误的信息
          }
        })
      }).catch(() => {
      })
    },
    load(pageNum) {  // 分页查询
      if (pageNum) this.pageNum = pageNum
      this.$request.get('/charges/selectPage', {
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          userName: this.userName,
          project: this.project,
          status: this.status,
        }
      }).then(res => {
        this.tableData = res.data?.list
        this.total = res.data?.total
      })
    },
    reset() {
      this.userName = null
      this.project = null
      this.status = null
      this.load(1)
    },
    handleCurrentChange(pageNum) {
      this.load(pageNum)
    },
  }
}
</script>

<style scoped>

</style>
