import axios from "axios";
import router from '../router'
//创建axios的实例对象
const instance = axios.create({
    baseURL: "http://127.0.0.1:18080/api",
    timeout: 10000
})

//请求拦截器
instance.interceptors.request.use((config) => {
    let token = localStorage.getItem('token')
    if (token) {
        config.headers.authorization = 'bearer ' + token
    }
    return config
}, err => Promise.reject(err))

//响应拦截器 , 所有接口都会走响应拦截器
instance.interceptors.response.use((response) => {
    return response
}, err => Promise.reject(err))
//使用axios实例发送对象
export default instance