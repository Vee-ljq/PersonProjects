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
        {
            "img": "img/t1.png",
            "url": "#"
        },
        {
            "img": "img/t3.jpeg",
            "url": "#"
        }
    ],
    // 服务列表
    services: [
        {
            "id": 1,
            "image": "img/t1.png",
            "title": "冬至饺子馆",
            "summary": "扈三娘饺子馆，新鲜好吃，就怕你不来！",
            "address": "万科A3",
            "sell": 12
        },
        {
            "id": 2,
            "image": "img/t1.png",
            "title": "冬至饺子馆",
            "summary": "扈三娘饺子馆，新鲜好吃，就怕你不来！",
            "address": "万科A3",
            "sell": 12
        },
        {
            "id": 3,
            "image": "img/t1.png",
            "title": "冬至饺子馆",
            "summary": "扈三娘饺子馆，新鲜好吃，就怕你不来！",
            "address": "万科A3",
            "sell": 12
        },
        {
            "id": 4,
            "image": "img/t1.png",
            "title": "冬至饺子馆",
            "summary": "扈三娘饺子馆，新鲜好吃，就怕你不来！",
            "address": "万科A3",
            "sell": 12
        },
        {
            "id": 5,
            "image": "img/t1.png",
            "title": "冬至饺子馆",
            "summary": "扈三娘饺子馆，新鲜好吃，就怕你不来！",
            "address": "万科A3",
            "sell": 12
        }
    ]
};

$(function () {
    // 此处应该发起Ajax，拉去首页数据
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