import request from '@/utils/request'

// 查询订单列表
export function listOrders(query) {
  return request({
    url: '/orders',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getDetail(id) {
  return request({
    url: '/orders/getDetail/' + id,
    method: 'get'
  })
}
// 查询订单详细
export function getOrders(id) {
  return request({
    url: '/orders/' + id,
    method: 'get'
  })
}

// 新增订单
export function addOrders(data) {
  return request({
    url: '/orders',
    method: 'post',
    data: data
  })
}

// 修改订单
export function updateOrders(data) {
  return request({
    url: '/orders',
    method: 'put',
    data: data
  })
}

// 删除订单
export function delOrders(id) {
  return request({
    url: '/orders/' + id,
    method: 'delete'
  })
}
