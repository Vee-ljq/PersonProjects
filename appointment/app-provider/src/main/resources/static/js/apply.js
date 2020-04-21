var pageData = {
    "imageFile": null,
    "imageFile1":null,
    "service": {
        "shopname": "",
        "stoptime": "",
        "shopaddress": "",
        "shopbrief": "",
        "shopdetail": "",
        "category":"",
        "recom":""
    }
};



$(function () {

    // 初始化Vue实例
    var vue = new Vue({
        el: "#app",
        data: {
            pd: pageData
        },
        methods: {
            showSelect: function () {
                $("#fileInput1").click();
            },
            showSelect1: function () {
                $("#fileInput2").click();
            },
            upload: function () {
                // 上传图片
            },
            upload1: function () {
                // 上传图片
            },
            saveForm: function () {
                // 保存
                layer.msg("提交申请成功，等待审核中...", function () {
                    var form_data = new FormData();
                    form_data.append("file", pageData.imageFile);
                    form_data.append("file1", pageData.imageFile1);
                    form_data.append("shopname",pageData.service.shopname);
                    form_data.append("stoptime",pageData.service.stoptime);
                    form_data.append("shopaddress",pageData.service.shopaddress);
                    form_data.append("shopbrief",pageData.service.shopbrief);
                    form_data.append("shopdetail",pageData.service.shopdetail);
                    form_data.append("category",pageData.service.category);
                    form_data.append("recom",pageData.service.recom);
                    $.ajax({
                        type : "POST",
                        url : "/app/shop/applyShang",
                        data :form_data,
                        xhrFields:{withCredentials:true},
                        dataType : "JSON",
                        processData: false,
                        contentType: false,
                        success : function(result) {
                            layer.msg("保存成功，即将返回列表页面")
                            window.location.href="/uc.html?apply=0";
                        }
                    });
                });
            }
        }
    });

});

/**
 * 预览图片
 * @param source
 */
function previewImage(source) {
    var file = source.files[0];
    pageData.imageFile = file;
    if(window.FileReader){
        var fr = new FileReader();
        fr.onloadend = function (e) {
            pageData.service.imageData = e.target.result;
        }
        fr.readAsDataURL(file);
    }
}
function previewImage1(source) {
    var file1 = source.files[0];
    pageData.imageFile1 = file1;
    if(window.FileReader){
        var fr = new FileReader();
        fr.onloadend = function (e) {
            pageData.service.imageData1 = e.target.result;
        }
        fr.readAsDataURL(file1);
    }
}