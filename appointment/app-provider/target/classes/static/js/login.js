function change(obj){
    obj.src="/verification";
}

/**
 * 利用vue获取input框中的值
 * @type {null}
 */
let phone = null;
let onepwd = null;
let twopwd = null;
let yzm = null;
let code = null;
let vue = new Vue({
    el: "#app",
    data: {
        phone:phone,
    },
    methods: {
        getphone: function () {
            phone = this.$refs.phone.value;
        },
        checkpwd: function () {
            onepwd = this.$refs.onepwd.value;
            twopwd = this.$refs.twopwd.value;
        },
        yzm: function () {
            yzm = this.$refs.yzm.value;
        },
        duanxin: function () {
            duanxin = this.$refs.duanxin.value;
        }
    }
});

/**
 * 判断两次密码是否一致
 */
function  register() {
    if(onepwd==twopwd){
        //说明数据都正确，直接提交就可以
        $("#regform").submit();
    }else{
        layer.msg("两次密码不一致");
    }

}


/**
 * 异步获取手机验证码
 */
function  dianji() {
    alert("点击成功")
    alert(phone)
    $.ajax({
        url:"http://127.0.0.1:99/test/message",
        type:"get",
        data:"phone="+phone,
        success:function (result) {
            alert("获取到数据")
        }
    });
}