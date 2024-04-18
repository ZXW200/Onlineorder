import request from '@/utils/request'

// 查询预定列表
export function listReservations(query) {
  return request({
    url: '/reservations',
    method: 'get',
    params: query
  })
}

// 查询预定详细
export function getReservations(id) {
  return request({
    url: '/reservations/' + id,
    method: 'get'
  })
}

// 新增预定
export function addReservations(data) {
  return request({
    url: '/reservations',
    method: 'post',
    data: data
  })
}
// export const addReservations = (data) => request({
//   url: '/reservations',
//   method: 'post',
//   data
// })


// 修改预定
export function updateReservations(data) {
  return request({
    url: '/reservations',
    method: 'put',
    data: data
  })
}

// 删除预定
export function delReservations(id) {
  return request({
    url: '/reservations/' + id,
    method: 'delete'
  })
}
