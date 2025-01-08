<template>
  <div style="width: 60%; margin: 30px auto">
    <div style="display: flex; font-size: 20px; justify-content: center">
      <div style="flex: 1">{{ activityData.title }}</div>
      <img v-if="praiseData.length" src="@/assets/imgs/praise.png" alt=""
           style="width: 30px; height: 30px; cursor: pointer" @click="doPraise(0)">
      <img v-else src="@/assets/imgs/praise_done.png" alt="" style="width: 30px; height: 30px; cursor: pointer"
           @click="doPraise(1)">
    </div>
    <div style="margin-top: 10px; color: #666666">
      <span style="margin-right: 30px">点赞：{{ activityData.count }}</span>
      <span>发布时间：{{ activityData.time }}</span>
    </div>
    <div style="margin-top: 20px" v-html="activityData.content" class="w-e-text w-e-text-container"></div>
  </div>
</template>

<script>
import E from 'wangeditor'

export default {
  data() {
    return {
      activityId: this.$route.query.id,
      activityData: {},
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      praiseData: []
    }
  },
  mounted() {
    this.loadActivity()
    this.checkPraise()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadActivity() {
      this.$request.get('/activity/selectById/' + this.activityId).then(res => {
        if (res.code === '200') {
          this.activityData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    checkPraise() {
      this.$request.get('/praise/selectAll',{
        params: {
          activityId: this.activityId,
          userId: this.user.id
        }
      }).then(res => {
        if (res.code === '200') {
          this.praiseData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    },
    doPraise(flag) {
      this.$request.get('/praise/do', {
        params: {
          activityId: this.activityId,
          userId: this.user.id
        }
      }).then(res => {
        if (res.code === '200') {
          if (1 === flag) {
            this.$message.success('谢谢您的支持')
          } else {
            this.$message.success('取消成功')
          }
          this.loadActivity()
          this.checkPraise()
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>
