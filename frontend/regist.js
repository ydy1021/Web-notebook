function fnLogin() {
    var oUname = document.getElementById("uname")
    var oUpass = document.getElementById("upass")
    var oError = document.getElementById("error_box")
    var isError = true;


    var paramData={};
    paramData.phoneNumber  =oUname.value; 
    paramData.password=oUpass.value;
$.ajax({
url: "http://localhost/user/add",
type: "POST",   
dataType: "json",
data:paramData,
async : false,
cache : false,
success: function (resultsData) {
if (resultsData !== " "){
var code=resultsData.code;
if (code=="200") {
 window.alert("Successfully register");
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
    // window.alert("Successfully login")
    // top.location=' http://192.168.3.43:8080/';
   }
   
   