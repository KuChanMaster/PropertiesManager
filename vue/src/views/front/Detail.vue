<template>
  <div style="width: 60%; margin: 30px auto">
    <div style="display: flex; font-size: 20px; justify-content: center">
      <div style="flex: 1">{{ activityData.title }}</div>
      <img src="@/assets/imgs/praise.png" alt="" style="width: 30px; height: 30px; cursor: pointer">
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
      activityData: {}
    }
  },
  mounted() {
    this.loadActivity()
  },
  // methods：本页面所有的点击事件或者其他函数定义区
  methods: {
    loadActivity() {
      //'?t='+ (new Date()).getTime().toString() 报304
      this.$request.get('/activity/selectById/' + this.activityId).then(res => {
        if (res.code === '200') {
          this.activityData = res.data
        } else {
          this.$message.error(res.msg)
        }
      })
    }
  }
}
</script>
