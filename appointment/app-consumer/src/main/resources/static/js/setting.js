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
            user: loginUser
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
            loginUser.avatar = e.target.result;
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

        /*$.ajax({
            type : "POST",
            url : dataUrl +  "/api/member/set_avatar",
            data : form_data,
            xhrFields:{withCredentials:true},
            dataType : "JSON",
            processData: false,
            contentType: false,
            success : function(result) {
                if(result.code == 0){

                    layer.msg("修改成功！");
                }else{
                    checkError(result.code, result.msg);
                }
            }
        });*/
    }
}