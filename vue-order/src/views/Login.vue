<template>
  <div class="main">
    <div class="box">
      <div class="headline">Order Management System</div>
      <el-form size="medium" :rules="rules" ref="ruleForm" :model="formData">
        <!-- account number -->
        <el-form-item class="username" prop="username">
          <el-input prefix-icon="el-icon-user-solid" placeholder="Please enter account number" v-model="formData.username"
            clearable>
          </el-input>
        </el-form-item>
        <!-- password -->
        <el-form-item class="password" prop="password">
          <el-input prefix-icon="el-icon-lock" placeholder="Please enter password" :type="isOpen ? 'text' : 'password'"
            v-model="formData.password" clearable></el-input>
        </el-form-item>
        <!-- Login -->
        <el-form-item>
          <el-button type="primary" @click="submit">Login</el-button>
        </el-form-item>
        <div class="bottom">
          <div class="visitor">
            <router-link :to="'/index'">Visitor login</router-link>
          </div>
          <div class="register">
            <router-link class="link-type" :to="'/Register'">Register</router-link>
          </div>

        </div>
      </el-form>
    </div>
  </div>
</template> 

<script>
import { login } from "../api/login";
export default {
  data() {
    return {
      formData: {
        username: "",
        password: "",
      },
      isOpen: false,
      rules: {
        username: [
          { required: true, trigger: "blur", message: "Please enter your account number" },
          { min: 5, max: 12, message: 'User account length must be between 5 and 12', trigger: 'blur' }
        ],
        password: [
          { required: true, trigger: "blur", message: "Please enter your password" },
          { min: 5, max: 12, message: 'The user password must be between 5 and 12', trigger: 'blur' }
        ]
      },
    };
  },
  created() {
    window.addEventListener("keyup", this.keyUp);
  },
  methods: {
    openEyes() {
      this.isOpen = !this.isOpen;
    },

    submit() {
      //Judgment check
      this.$refs.ruleForm.validate(bool => {
        //succeed
        if (bool) {
          login(this.formData).then(res => {
            console.log(res)
            if (res.status == 200 && res.data.role == "user") {
              //Login prompt
              this.$message({
                message: 'Land Successfully',
                type: 'success'
              });
              //token
              localStorage.setItem("token", res.data.token);
              setTimeout(() => {
                this.$router.push({
                  path: '/index',
                  query: {
                    userId: res.data.id
                  }
                });
              }, 500)
            } else if (res.status == 200 && res.data.role == "Admin") {
              this.$message({
                message: 'Land Successfully',
                type: 'success'
              });
              //token
              localStorage.setItem("token", res.data.token);
              setTimeout(() => {
                this.$router.push({
                  path: '/menu',
                  // query: {
                  //   userId: res.data.data.id
                  // }
                });
              }, 500)
            } else {
              this.$message.error({
                message: 'Login Failure'
              });
            }
          })
        }
      });
    },
    keyUp(e) {
      if (e.keyCode == 13) {
        this.submit();
      }
    },

    destroyed() {
      window.removeEventListener("keyup", this.keyUp);
    },
  },
  created() {
    window.addEventListener("keyup", this.keyUp);
  },
  destroyed() {
    window.removeEventListener("keyup", this.keyUp);
  },
};
</script>

<style lang="scss" scoped>
.main {
  width: 100%;
  height: 100%;
  background: url('../assets/images/hero_bg_1.jpg');
  background-size: cover;
  display: flex;
  justify-content: center;
  align-items: center;

  .box {
    width: 350px;
    height: 450px;
    background-color: #ffffff;
    border-radius: 5px;

    .headline {
      font-size: 20px;
      text-align: center;
      margin: 40px 0;
    }

    .el-form {
      .el-form-item {
        margin: 0 40px;
      }

      .username {
        margin-bottom: 20px;
      }

      .password {
        margin-bottom: 20px;
      }

      .el-button--primary {
        width: 100%;
        margin-bottom: 20px;
      }
     
      .bottom {
        display: flex;
        justify-content: space-between;
        margin: 0 40px;
        font-size: 14px;
      }

    }

  }
}
</style>../api/login