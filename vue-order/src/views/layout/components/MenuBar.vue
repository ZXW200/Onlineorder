<template>
  <div>
    <!-- 侧边栏 -->
    <div class="menu">
      <div class="head-content">
        <img src="../../../assets/logo.png" alt="" />
        <p>Coffee</p>
      </div>
      <!-- 菜单容器组件   -->
      <el-menu
        :default-active="$route.path"
        class="el-menu-vertical-demo"
        background-color="#304156"
        text-color="#FFF"
        unique-opened
        router
        :collapse-transition="false"
      >
        <!-- 菜单管理 -->
        <el-menu-item index="/menu">
          <i class="iconfont icon-home"></i>
          <span slot="title">Menu</span>
        </el-menu-item>

        <!-- 订单管理 -->
        <el-menu-item index="/order">
          <i class="iconfont icon-order"></i>
          <span slot="title">Order</span>
        </el-menu-item>

         <!-- 预定管理 -->
         <el-menu-item index="/reservation">
          <i class="iconfont icon-goods"></i>
          <span slot="title">Reservation</span>
        </el-menu-item>

        <!-- 餐桌管理 -->
        <el-menu-item index="/tables">
          <i class="iconfont icon-shop"></i>
          <span slot="title">Table</span>
        </el-menu-item>

        <!-- 用户管理 -->
        <el-menu-item index="/user">
          <i class="iconfont"></i>
          <span slot="title">User</span>
        </el-menu-item>
      </el-menu>
    </div>
  </div>
</template>

<script>
export default {

};
</script>

<style lang="scss" scoped>
.menu {
  height: 100%;
  background-color: #304156;
  .head-content {
    display: flex;
    justify-content: center;
    align-items: center;
    padding-top: 20px;

    img {
      width: 35px;
      border-radius: 5px;
      margin-right: 5px;
      height: 35px;
    }
    p {
      margin-left: 3px;
      color: white;
      font-size: 16px;
    }
  }
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
  width: 160px;
  min-height: 400px;
}
.el-submenu .el-menu-item {
  background-color: #202f3f !important;
}
.el-menu {
  border-right: none;
}
.iconfont {
  font-size: 18px;
  margin-right: 10px;
}
</style>