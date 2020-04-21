var pageData = {
    "imageFile": null,
    "service": {
        "title": "",
        "endTime": "",
        "address": "",
        "summary": "",
        "content": "",
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
                // 保存
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
