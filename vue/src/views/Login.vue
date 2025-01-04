<template>
  <div class="container">
    <div class="login-card">
      <div class="login-title">欢迎使用小区物业管理系统</div>
      <el-form :model="form" :rules="rules" ref="formRef" class="login-form">
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" placeholder="请输入账号" v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="请输入密码" show-password v-model="form.password">
          </el-input>
        </el-form-item>
        <el-form-item prop="role">
          <el-select v-model="form.role" placeholder="请选择角色">
            <el-option label="管理员" value="ADMIN"></el-option>
            <el-option label="业主" value="USER"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" class="login-button" @click="login">登录</el-button>
        </el-form-item>
        <div class="login-footer">
          还没有账号？请 <a href="/register" class="register-link">注册</a>
        </div>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      form: { role: 'ADMIN' },
      rules: {
        username: [{ required: true, message: '请输入账号', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      }
    };
  },
  methods: {
    login() {
      this.$refs['formRef'].validate(valid => {
        if (valid) {
          this.$request.post('/login', this.form).then(res => {
            if (res.code === '200') {
              localStorage.setItem("xm-user", JSON.stringify(res.data));
              this.$message.success('登录成功');
              if (res.data.role === 'ADMIN') {
                this.$router.push('/');
              } else {
                location.href = '/front/home';
              }
            } else {
              this.$message.error(res.msg);
            }
          });
        }
      });
    }
  }
};
</script>

<style scoped>
.container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-image: url("@/assets/imgs/bg.jpg");
  background-size: cover;
  background-position: center;
}

.login-card {
  width: 500px;
  padding: 40px;
  background-color: #fbd17b;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  text-align: center;
}

.login-card:hover {
  box-shadow: 0 6px 12px rgba(0, 0, 0, 0.15);
}

.login-title {
  font-size: 24px;
  font-weight: bold;
  color: #333;
  margin-bottom: 30px;
}

.login-form {
  text-align: left;
}

.login-button {
  width: 100%;
  font-size: 16px;
  padding: 12px;
}

.login-button:hover {
  background-color: #fbd17b;
  border-color: #2a60c9;
}

.login-footer {
  margin-top: 20px;
  font-size: 14px;
  color: #666;
}

.register-link {
  color: #2a60c9;
  text-decoration: none;
}

.register-link:hover {
  text-decoration: underline;
}
</style>
