import axios from "axios"
//封装的axios方法 第一个参数是url  第二个参数是传输的对象
export function getAction(url, parameter) {
  return axios({
    url: url,
    method: 'get',
    params: parameter
  })
}
export function putAction(url, parameter) {
  return axios({
    url: url,
    method: 'put',
    data: parameter
  })
}
export function httpAction(url, parameter, method) {
  return axios({
    url: url,
    method: method,
    data: parameter
  })
}
export function postAction(url, parameter) {
  return axios({
    url: url,
    method: 'post',
    data: parameter
  })
}
export function deleteAction(url, parameter) {
  return axios({
    url: url,
    method: 'delete',
    data: parameter
  })
}