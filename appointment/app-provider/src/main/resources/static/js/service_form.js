var pageData = {
    "imageFile": null,
    "service": {
        "goodsname": "",
        "stoptime": "",
        "goodsaddress": "",
        "goodsbrief": "",
        "goodsdetail": "",
        "image": "",
        "imageData": ""
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
                $("#fileInput").click();
            },
            upload: function () {
                // 上传图片
            },
            saveForm: function () {
                alert("要保存啦");
/*
                $.ajax({
                    url:"/app/order/addService",
                    type:"post",
                    data:{
                        "goodsname": pageData.service.goodsname,
                        "stoptime": pageData.service.stoptime,
                        "goodsaddress": pageData.service.goodsaddress,
                        "goodsbrief": pageData.service.goodsbrief,
                        "goodsdetail": pageData.service.goodsdetail,
                        "imageFile":pageData.imageFile
                    },
                    success:function (result) {
                        layer.msg("保存成功，即将返回列表页面")
                        window.location.href="/uc.html";
                    }
                });*/


                var form_data = new FormData();
                form_data.append("file", pageData.imageFile);
                form_data.append("goodsname",pageData.service.goodsname);
                form_data.append("stoptime",pageData.service.stoptime);
                form_data.append("goodsaddress",pageData.service.goodsaddress);
                form_data.append("goodsbrief",pageData.service.goodsbrief);
                form_data.append("goodsdetail",pageData.service.goodsdetail);
                $.ajax({
                    type : "POST",
                    url : "/app/order/addService",
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
