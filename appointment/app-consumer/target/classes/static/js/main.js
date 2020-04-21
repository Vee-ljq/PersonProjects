var loginUser = {
    "username": "zhangsan",
    "nickname": "总有刁民想害朕",
    "avatar": "img/a3.jpg",
    "balance": 0.00,
    "merchant": false
};

/**
 * 提示消息
 * @param content
 */
function showMsg(content) {
    layer.msg(content);
}

/**
 * 提示错误
 */
function showError(content) {
    layer.msg(content, function () {

    });
}


/**
 * 获取url地址中的参数值
 * @name 参数名
 */
function getUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
    var r = window.location.search.substr(1).match(reg);  //匹配目标参数
    if (r != null) return unescape(r[2]); return null; //返回参数值
}

/**
 * 获取当前页面真实的url地址
 */
function GetPageUrl(){
    var url = document.location.toString();
    var arrUrl = url.split("//");

    var start = arrUrl[1].indexOf("/");
    var relUrl = arrUrl[1].substring(start); //stop省略，截取从start开始到结尾的所有字符

    /*if(relUrl.indexOf("?") != -1){
        relUrl = relUrl.split("?")[0];
    }*/

    return relUrl;
}