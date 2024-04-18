<template>
  <div>
    <!-- 导航栏 -->
    <div class="nav">
      <div class="left">
        <el-breadcrumb class="separator" separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/menu' }">Index</el-breadcrumb-item>
          <el-breadcrumb-item :to="{ path: value.path }" v-for="value in getPath" :key="value.path">{{ value.meta.name
          }}</el-breadcrumb-item>
        </el-breadcrumb>
      </div>
      <div class="right">
        <el-dropdown @command="handle">
          <span class="el-dropdown-link">
            Welcome
          </span>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item command="/login">logOut</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {};
  },

  methods: {
    handle(params) {
      if (params == "/login") {
        localStorage.removeItem("token");
      }
      this.$router.push(params)
    },
  },

  computed: {
    getPath() {
      return this.$route.matched.filter((v) => v.meta.name);
    },
  },
};
</script>

<style lang="scss" scoped>
.nav {
  width: 100%;
  height: 60px;
  background-color: #f0f2f5;
  display: flex;
  justify-content: space-between;
  align-items: center;

  .left {
    position: relative;
    display: flex;
    align-items: center;

    .icon-order {
      position: absolute;
      left: 10px;
      font-size: 22px;
    }

    .separator {
      margin-left: 40px;
    }
  }

  .right {
    margin-right: 30px;

    .avatar {
      display: flex;
      align-items: center;

      .el-dropdown-link {
        margin-right: 10px;
      }
    }
  }
}
</style>
