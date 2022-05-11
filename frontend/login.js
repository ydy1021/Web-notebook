function fnLogin() {
    var oUname = document.getElementById("uname")
    // $('#uname')
    var oUpass = document.getElementById("upass")
    var oError = document.getElementById("error_box")
    var isError = true;
    var paramData={};
    paramData.phoneNumber  =oUname.value; 
    paramData.password=oUpass.value;
    console.log(paramData.password);
$.ajax({
url: "http://localhost/user/userLogin",
type: "POST",   
dataType: "json",
data:paramData,
async : false,
cache : false,
success: function (resultsData) {
if (resultsData !== " "){
var code=resultsData.code;
if (code=="200") {
 window.alert("登录成功");
location.href='http://192.168.3.43:8080/';  //登录成功跳转到主页
}else{
    window.alert("Wrong user or password!")
layer.msg(resultsData.msg);
}
}
},
error: function(resultsData){
layer.alert(resultsData.msg);
}
});

    
    window.alert("Successfully login")
   // top.location=' http://47.99.244.26:8080/';
    top.location=' http://192.168.3.43:8080/';//跳转到另一个界面
    //window.location.href = '/index.html';
    //axios.defaults.baseURL=' http://47.99.244.26:8080/';
   }
   
   