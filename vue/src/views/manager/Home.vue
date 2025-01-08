<template>
  <div>
    <div class="card" style="padding: 15px">
      您好，{{ user?.name }}！欢迎使用本系统
    </div>

    <div style="display: flex; margin: 10px 0">
      <div style="width: 45%;height:350px" class="card">
        <div style="margin-bottom: 30px; font-size: 20px; font-weight: bold">公告列表</div>
        <div>
          <el-timeline reverse slot="reference">
            <el-timeline-item v-for="item in notices" :key="item.id" :timestamp="item.time">
              <el-popover
                  placement="right"
                  width="200"
                  trigger="hover"
                  :content="item.content">
                <span slot="reference">{{ item.title }}</span>
              </el-popover>
            </el-timeline-item>
          </el-timeline>
        </div>
      </div>
      <div style="width: 45%;height: 350px" class="card" id="pie1">
      </div>

    </div>
    <div style="display: flex; margin: 10px 0">
      <div style="width: 45%;height: 350px" class="card" id="pie2"></div>
      <div style="width: 45%;height: 350px" class="card" id="pie3"></div>
    </div>
  </div>
</template>

<script>
import * as echarts from "echarts";

let pie1Options = {
  title: {
    text: '小区闲置车位统计', // 主标题
    subtext: '统计维度车位状态', // 副标题
    left: 'center'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b} : {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: '', // 鼠标移上去显示内容
      type: 'pie',
      radius: '50%',
      center: ['50%', '60%'],
      data: []
    }
  ]
}
let pie2Options = {
  title: {
    text: '投诉处理统计', // 主标题
    subtext: '统计维度：处理状态', // 副标题
    left: 'right'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b}: {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 10,
    data: []
  },
  series: [
    {
      name: '',
      type: 'pie',
      radius: ['50%', '70%'],
      avoidLabelOverlap: false,
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '30',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: []
    }
  ]
}
let pie3Options = {
  title: {
    text: '物业报修处理统计', // 主标题
    subtext: '统计维度：处理状态', // 副标题
    left: 'right'
  },
  tooltip: {
    trigger: 'item',
    formatter: '{a} <br/>{b}: {c} ({d}%)'
  },
  legend: {
    orient: 'vertical',
    left: 10,
    data: []
  },
  series: [
    {
      name: '',
      type: 'pie',
      radius: ['50%', '70%'],
      avoidLabelOverlap: false,
      label: {
        show: false,
        position: 'center'
      },
      emphasis: {
        label: {
          show: true,
          fontSize: '30',
          fontWeight: 'bold'
        }
      },
      labelLine: {
        show: false
      },
      data: []
    }
  ]
}

export default {
  name: 'Home',

  data() {
    return {
      user: JSON.parse(localStorage.getItem('xm-user') || '{}'),
      notices: []
    }
  },
  created() {
    this.$request.get('/notice/selectAll').then(res => {
      this.notices = res.data || []

    })
    this.getPie1Data()
    this.getPie2Data()
    this.getPie3Data()
  },
  methods: {
    getPie1Data() {
      this.$request.get('/echarts/pie1').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('pie1');
          let myChart = echarts.init(chartDom);
          pie1Options.series[0].data = res.data
          myChart.setOption(pie1Options)
        }
      })
    },

    getPie2Data() {
      this.$request.get('/echarts/pie2').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('pie2');
          let myChart = echarts.init(chartDom);
          pie2Options.series[0].data = res.data
          myChart.setOption(pie2Options)
        }
      })
    },
    getPie3Data() {
      this.$request.get('/echarts/pie3').then(res => {
        if (res.code === '200') {
          let chartDom = document.getElementById('pie3');
          let myChart = echarts.init(chartDom);
          pie3Options.series[0].data = res.data
          myChart.setOption(pie3Options)
        }
      })
    },
  }
}
</script>
