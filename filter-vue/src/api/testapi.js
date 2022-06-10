import {getAction} from './axiosmethod'
export function testApi(){
    let url="http://localhost:7000/test/note"
    let param={test:"test"}
    getAction(url,param).then((res)=>{
        console.log(res);
    }).catch((err)=>{
        console.log(err);
    })
}