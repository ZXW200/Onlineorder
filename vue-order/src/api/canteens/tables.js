import request from '@/utils/request'

// 查询餐桌列表
export function listTables(query) {
  return request({
    url: '/tables',
    method: 'get',
    params: query
  })
}

// 查询餐桌详细
export function getTables(id) {
  return request({
    url: '/tables/' + id,
    method: 'get'
  })
}

// 新增餐桌
export function addTables(data) {
  return request({
    url: '/tables',
    method: 'post',
    data: data
  })
}

// 修改餐桌
export function updateTables(data) {
  return request({
    url: '/tables',
    method: 'put',
    data: data
  })
}

// 删除餐桌
export function delTables(id) {
  return request({
    url: '/tables/' + id,
    method: 'delete'
  })
}
