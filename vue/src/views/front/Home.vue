<template>
  <div class="homepage-container">
    <div class="main-content">
      <el-carousel :interval="4000" type="card" height="350px">
        <el-carousel-item v-for="item in carouselData" :key="item">
          <img :src="item" alt="carousel image" class="carousel-image">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="header-text">高档小区 一流物业 诚挚为您服务</div>
    <div class="image-grid">
      <img
          src="@/assets/imgs/complaint.jpg"
          class="grid-image"
          @click="navTo('/front/complaint')"
          alt="Complaint">
      <img
          src="@/assets/imgs/activity.png"
          class="grid-image"
          @click="navTo('/front/activity')"
          alt="Activity">
      <img
          src="@/assets/imgs/fixed.jpg"
          class="grid-image"
          @click="navTo('/front/fixed')"
          alt="Fixed">
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      carouselData: [
        require('@/assets/imgs/lun-1.png'),
        require('@/assets/imgs/lun-2.png'),
        require('@/assets/imgs/lun-3.png'),
      ],
    };
  },
  methods: {
    navTo(url) {
      location.href = url;
    },
  },
};
</script>

<style scoped>
.homepage-container {
  background: linear-gradient(to bottom, #f5f7fa, #c3cfe2);
  padding: 20px;
  text-align: center;
  font-family: 'Arial', sans-serif;
}

.main-content {
  margin-bottom: 30px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  overflow: hidden;
}

.carousel-image {
  width: 100%;
  height: 350px;
  object-fit: cover;
  border-radius: 10px;
}

.header-text {
  font-size: 24px;
  color: #333;
  margin: 20px 0;
  font-weight: bold;
}

.image-grid {
  display: flex;
  justify-content: center;
  gap: 15px;
}

.grid-image {
  width: 250px;
  height: 250px;
  border-radius: 15px;
  cursor: pointer;
  transition: transform 0.3s, box-shadow 0.3s;
  object-fit: cover;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.grid-image:hover {
  transform: scale(1.05);
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.3);
}
</style>