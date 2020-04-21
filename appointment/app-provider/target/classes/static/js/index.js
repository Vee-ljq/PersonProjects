// 页面数据，此处为演示数据
var pageData = {
    // 导航菜单
    navs: {
        "mid": 1,
        "menus": [
            {
                "id": 1,
                "title": "推荐"
            },
            {
                "id": 2,
                "title": "课程"
            },
            {
                "id": 3,
                "title": "美食"
            },
            {
                "id": 4,
                "title": "美容"
            },
            {
                "id": 5,
                "title": "健身"
            }
        ]
    },
    // 轮播图
    carousel: [

    ],
    // 服务列表
    services: [

    ]
};

$(function () {
    // 此处应该发起Ajax，拉去首页数据
    $.ajax({
        url:"app/shop/getindexdata",
        type:"post",
        success:function (result) {
            /*pageData.services = [];
            pageData.carousel = [];*/
            for(let i = 0;i<result.length;i++){
                let shop1 = {
                    "id": result[i].id,
                    "image": result[i].indexurl,
                    "title":result[i].shopname,
                    "summary": result[i].shopbrief,
                    "address": result[i].shopaddress,
                    "sell": result[i].sellcount
                }
                pageData.services.push(shop1);
                let img = {
                    "img":result[i].indexurl,
                    "url":"/app/shop/btnappointment?id="+result[i].id
                }
                pageData.carousel.push(img);
            }
        }
    });
    // 成功获取数据后，初始化Vue实例
    initVue();

});



/**
 * 初始化Vue实例
 */
function initVue() {
    // 初始化Vue实例
    var vue = new Vue({
        el: "#app",
        data: {
            pd: pageData
        },
        mounted: function(){
            initSwiper();
        },
        updated: function () {

        },
        methods: {
            changeMenu: function(mid){
                this.pd.navs.mid = mid;
                alert(mid)
                if (mid==1){
                    $.ajax({
                        url:"app/shop/getindexdata",
                        type:"post",
                        success:function (result) {
                            pageData.services = [];
                            pageData.carousel = [];
                            for(let i = 0;i<result.length;i++){
                                let shop1 = {
                                    "id": result[i].id,
                                    "image": result[i].indexurl,
                                    "title":result[i].shopname,
                                    "summary": result[i].shopbrief,
                                    "address": result[i].shopaddress,
                                    "sell": result[i].sellcount
                                }
                                pageData.services.push(shop1);
                                let img = {
                                    "img":result[i].indexurl,
                                    "url":"/app/shop/btnappointment?id="+result[i].id
                                }
                                pageData.carousel.push(img);
                            }
                        }
                    });
                }else{
                    $.ajax({
                        url:"/app/shop/changeMenu",
                        type:"post",
                        data:"id="+mid,
                        success:function (result) {
                            pageData.services = [];
                            pageData.carousel = [];
                            for(let i = 0;i<result.length;i++){
                                let shop1 = {
                                    "id": result[i].id,
                                    "image": result[i].indexurl,
                                    "title":result[i].shopname,
                                    "summary": result[i].shopbrief,
                                    "address": result[i].shopaddress,
                                    "sell": result[i].sellcount
                                }
                                pageData.services.push(shop1);
                                let img = {
                                    "img":result[i].indexurl,
                                    "url":"/app/shop/btnappointment?id="+result[i].id
                                }
                                pageData.carousel.push(img);
                            }
                        }

                    });
                }

            }
        }
    });
}

/**
 * 初始化轮播图
 */
function initSwiper() {
    var swiper = new Swiper('.swiper-container', {
        loop: true,
        autoplay:true,
        pagination: {
            el: '.swiper-pagination',
        },
    });
}

/**
 * 通过点击不同的菜单，切换不同的预约类别
 * @param id
 */
/*
function  changeMenu(id) {
    pageData.navs.mid=id;
    alert(id)
    $.ajax({
        url:"/app/shop/changeMenu",
        type:"post",
        data:"id="+id,
        success:function (result) {
            for(let i = 0;i<result.length;i++){
                let shop1 = {
                    "id": result[i].id,
                    "image": result[i].indexurl,
                    "title":result[i].shopname,
                    "summary": result[i].shopbrief,
                    "address": result[i].shopaddress,
                    "sell": result[i].sellcount
                }
                pageData.services.push(shop1);

            }
        }

    });
}*/
