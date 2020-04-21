var pageData = {
    "nickname": "",
    "mobile": "",
    "oldPassword": "",
    "newPassword": "",
    "newPassword2": "",
    "avatar": ""
};


$(function () {
    var vue = new Vue({
        el: "#app",
        data: {
            pd: pageData,
            user: user
        },
        methods: {
            showDialog: function (id) {
                layer.open({
                    type: 1,
                    closeBtn: 1,
                    shadeClose: true,
                    title: false,
                    resize: false,
                    content: $('#' + id)
                });
            }

        }
    });
});

function checkpwd() {
    if($("#oldpwd").val()!=user.password){
        layer.msg("旧密码输入错误！")
    }else{
        let pwd1 = $("#newpwd1").val();
        let pwd2 = $("#newpwd2").val();
        if(pwd1==pwd2){
            $("#checkpwd").submit();
        }else{
            layer.msg("两次密码输入不一致")
        }
    }

}

/**
 * 显示文件选择框
 */
function selectFile() {
    $("#image_input").click();
}

/**
 * 显示图片预览
 * @param source
 */
function showImagePreView(source){
    imageFile = source.files[0];
    if(window.FileReader) {
        var fr = new FileReader();
        fr.onloadend = function(e) {
            user.avatar = e.target.result;
        };
        fr.readAsDataURL(imageFile);
    }
}

/**
 * 保存用户头像
 * @returns
 */
function saveAvatar(){
    if(imageFile == null){
        layer.msg("请选择新的头像图片", function(){});
    }else{
        var form_data = new FormData();
        form_data.append("file", imageFile);
        $.ajax({
            type : "POST",
            url : "/app/user/set_avatar",
            data : form_data,
            xhrFields:{withCredentials:true},
            dataType : "JSON",
            processData: false,
            contentType: false,
            success : function(result) {
                if(result.code == 0){

                    layer.msg("修改成功！");
                    layer.close();
                }else{
                    checkError(result.code, result.msg);
                }
            }
        });
    }
}