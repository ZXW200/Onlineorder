import request from '@/utils/request'

// 查询订单详细列表
export function listDetails(query) {
  return request({
    url: '/orderDetails',
    method: 'get',
    params: query
  })
}

// 查询订单详细
export function getDetails(id) {
  return request({
    url: '/orderDetails/' + id,
    method: 'get'
  })
}

// 新增订单详细
export function addDetails(data) {
  return request({
    url: '/orderDetails',
    method: 'post',
    data: data
  })
}

// 修改订单详细
export function updateDetails(data) {
  return request({
    url: '/orderDetails',
    method: 'put',
    data: data
  })
}

// 删除订单详细
export function delDetails(id) {
  return request({
    url: '/orderDetails/' + id,
    method: 'delete'
  })
}
