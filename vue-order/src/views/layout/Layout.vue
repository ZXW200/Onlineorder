<template>
  <div class="layout-container">
    <menu-bar></menu-bar>
    <div class="content">
      <nav-bar></nav-bar>
      <app-main></app-main>
      <!-- 二级路由出口 -->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import NavBar from "./components/NavBar.vue";
import MenuBar from "./components/MenuBar.vue";
import AppMain from "./components/AppMain.vue";
export default {
  components: {
    MenuBar,
    NavBar,
    AppMain,
  },
};
</script>

<style lang="scss" scoped>
.layout-container {
  width: 100%;
  height: 100%;
  background-color: antiquewhite;
  display: flex;
  .content {
    flex: 1;
    background-color: #f0f2f5;
    display: flex;
    flex-direction: column;
  }
}
</style>