var pageData = {
    "pageMenus": {
        "mid": 1,
        "menus": [
            {
                "id": 1,
                "title": "用户"
            },
            {
                "id": 2,
                "title": "商户"
            }
        ]
    },
    // 用户预约信息统计
    "userCount": [
        {
            "title": "待赴约",
            "count": 3
        },
        {
            "title": "待评价",
            "count": 0
        },
        {
            "title": "已完成",
            "count": 0
        },
        {
            "title": "全部",
            "count": 0
        }
    ],
    // 商户预约信息统计
    "merchantCount": [
        {
            "title": "待赴约",
            "count": 3
        },
        {
            "title": "已完成",
            "count": 0
        },
        {
            "title": "全部",
            "count": 0
        }
    ],
    "services": [
        {
            "id": 1,
            "title": "Tony老师特约造型设计",
            "image": "static/img/t1.png",
            "status": 1
        },
        {
            "id": 2,
            "title": "Tony老师特约造型设计",
            "image": "static/img/t1.png",
            "status": 0
        },
        {
            "id": 3,
            "title": "Tony老师特约造型设计",
            "image": "static/img/t1.png",
            "status": -1
        },
        {
            "id": 4,
            "title": "Tony老师特约造型设计",
            "image": "static/img/t1.png",
            "status": 1
        }
    ]
};


$(function () {

    // 识别是否来自商户申请页面
    var apply = getUrlParam("apply");
    if(apply != null){
        loginUser.merchant = true;
    }

    // 初始化Vue实例
    var vue = new Vue({
        el: "#app",
        data: {
            user: loginUser,
            pd: pageData
        },
        methods: {
            changeMenu: function (mid) {
                this.pd.pageMenus.mid = mid;
            }
        }
    });

});
