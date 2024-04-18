//引入工具函数
import instance from '@/utils/request'

// 登录
export const login = (data) => instance({
    url: '/login',
    method: 'post',
    data
})

// 注册
export const createUser = (data) => instance({
    url: '/createUser',
    method: 'post',
    data
})
//重置密码
export const resetPassword = (data) => instance({
    url: '/ResetPassword',
    method: 'post',
    data
})
