<template>
  <div class="main">
    <div class="box">
      <div class="headline">Order Management System</div>
      <el-form size="medium" :rules="rules" ref="ruleForm" :model="formData">
        <!-- account number -->
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user-solid" placeholder="Please enter account number" v-model="formData.username" clearable>
          </el-input>
        </el-form-item>
        <!-- password -->
        <el-form-item prop="password">
          <el-input type="password" placeholder="Please enter password" prefix-icon="el-icon-lock" v-model="formData.password"
            clearable></el-input>
        </el-form-item>
        <!-- confirm password -->
        <el-form-item prop="confirmPassword">
          <el-input type="password" placeholder="Please confirm password" prefix-icon="el-icon-lock" v-model="formData.confirmPassword"
            clearable></el-input>
        </el-form-item>
        <!-- user name -->
        <el-form-item prop="role">
          <!-- <el-input placeholder="Please enter user name" prefix-icon="el-icon-user" v-model="formData.role" clearable></el-input> -->
          <el-select v-model="formData.role" placeholder="Please select a username">
            <el-option v-for="item in users" :key="item.value" :label="item.label" :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
        <!-- Register -->
        <el-form-item>
          <el-button type="primary" @click="submit">Register</el-button>
        </el-form-item>
        <div class="register">
          <router-link class="link-type" :to="'/login'">Login</router-link>
        </div>
      </el-form>
    </div>
  </div>
</template> 
  
<script>
import { createUser } from "../api/login";
export default {
  data() {
    const equalToPassword = (rule, value, callback) => {
      if (this.formData.password !== value) {
        callback(new Error("The two passwords you typed do not match"));
      } else {
        callback();
      }
    };
    return {
      users:[
        {
          value: 'user',
          label: 'user'
        },
        // {
        //   value: 'manager',
        //   label: 'manager'
        // }
      ],
      formData: {
        username: "",
        password: "",
        confirmPassword: "",
        role: ""
      },
      rules: {
        username: [
          { required: true, trigger: "blur", message: "Please enter your account number" },
          { min: 5, max: 12, message: 'User account length must be between 5 and 12', trigger: 'blur' }
        ],
        password: [
          { required: true, trigger: "blur", message: "Please enter your password" },
          { min: 5, max: 12, message: 'The user password must be between 5 and 12', trigger: 'blur' }
        ],
        confirmPassword: [
          { required: true, trigger: "blur", message: "Please confirm your password" },
          { required: true, validator: equalToPassword, trigger: "blur" }
        ]
      },
    };
  },
  created() {
    window.addEventListener("keyup", this.keyUp);
  },
  methods: {
    submit() {
      //Judgment check
      this.$refs.ruleForm.validate((bool) => {
        //succeed
        if (bool) {
          //Register
          createUser(this.formData).then(res => {
            if (res.data) {
              //Registration prompt
              this.$message({
                message: 'registered successfully',
                type: 'success'
              });
              //Skip to login
              this.$router.push("/login");
            }
          }).catch(() => {
            this.$message.error({
              message: 'fail to register'
            });
          })
        }
      });
    },
    keyUp(e) {
      if (e.keyCode == 13) {
        this.submit();
      }
    },
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
        margin: 20px 40px;
      }

      .el-button--primary {
        width: 100%;
      }

      .register {
        display: flex;
        justify-content: end;
        margin-right: 30px;
        font-size: 14px;
      }
    }

  }
}
</style>../api/login