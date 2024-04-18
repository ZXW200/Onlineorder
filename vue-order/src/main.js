import Vue from 'vue'
import App from './App.vue'
import router from './router'

import '@/assets/style/ruoyi.scss' // ruoyi css
import store from './store'
// 引入element
import 'element-ui/lib/theme-chalk/index.css';
import locale from 'element-ui/lib/locale/lang/en'
import ElementUI from 'element-ui';
import 'default-passive-events'
import plugins from './plugins' // plugins
// 引入公共样式
import "./assets/style/normalize.css"
import "./assets/style/reset.css"
//引入国际化语言包
// import i18n from './utils/i18n/i18n'
// 分页组件
import Pagination from "./components/Pagination/index.vue";
import { resetForm } from "@/utils/reset";
Vue.prototype.resetForm = resetForm
Vue.config.productionTip = false
Vue.use(plugins)
Vue.use(ElementUI, { locale })
Vue.component('Pagination', Pagination)
new Vue({
  router,
  store,
  // i18n,
  render: h => h(App)
}).$mount('#app')
