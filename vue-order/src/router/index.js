import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)
import Layout from "../views/layout/Layout.vue"
const routes = [
  {
    path: "/",
    redirect: "/login",
  },
  // 登录
  {
    path: "/login",
    component: () => import("@/views/Login.vue")
  },
  // 注册
  {
    path: "/register",
    component: () => import("@/views/Register.vue")
  },

   // 主页模块
   {
    path: '/index',
    component: () => import('@/views/Index.vue')
  },

  // 后台菜单列表
  {
    path: '/menu',
    component: Layout,
    meta: { name: 'menuList' },
    children: [
      {
        path: '',
        component: () => import('../views/canteen/items/index.vue')
      }
    ],
  },
  // 订单列表
  {
    path: '/order',
    component: Layout,
    meta: { name: 'orderList' },
    children: [
      {
        path: '',
        component: () => import('../views/canteen/orders/index.vue')
      },
     
    ],
  },
  // 订单详情
  {
    path: '/details',
    component: Layout,
    meta: { name: 'orderDetails' },
    children: [
      {
        path: '',
        component: () => import('../views/canteen/details/index.vue')
      }
    ],
  },
  // 预订列表
  {
    path: '/reservation',
    component: Layout,
    meta: { name: 'bookingList' },
    children: [
      {
        path: '',
        component: () => import('../views/canteen/reservations/index.vue')
      }
    ],
  },

  // 餐桌列表
  {
    path: '/tables',
    component: Layout,
    meta: { name: 'tableList' },
    children: [
      {
        path: '',
        component: () => import('../views/canteen/tables/index.vue')
      }
    ],
  },
  
  // 用户管理
  {
    path: '/user',
    component: Layout,
    meta: { name: 'userList' },
    children: [
      {
        path: '',
        component: () => import('../views/canteen/user/index.vue')
      }
    ],
  },

  // 404
  {
    path: '*',
    component: () => import('@/views/Error.vue')
  },
]


const originalPush = VueRouter.prototype.push;
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const router = new VueRouter({
  routes
})

export default router
