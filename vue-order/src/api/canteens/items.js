import request from '@/utils/request'

// 查询菜单项列表
export function listItems(query) {
  return request({
    url: '/menuItems',
    method: 'get',
    params: query
  })
}

// 查询菜单项详细
export function getItems(id) {
  return request({
    url: '/menuItems/' + id,
    method: 'get'
  })
}

// 新增菜单项
export function addItems(data) {
  return request({
    url: '/menuItems',
    method: 'post',
    data: data
  })
}

// 修改菜单项
export function updateItems(data) {
  return request({
    url: '/menuItems',
    method: 'put',
    data: data
  })
}

// 删除菜单项
export function delItems(id) {
  return request({
    url: '/menuItems/' + id,
    method: 'delete'
  })
}
