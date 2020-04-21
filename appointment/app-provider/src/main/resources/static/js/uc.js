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
            "count": null,
            "url":"/app/order/dapp?flag=0"
        },
        {
            "title": "待评价",
            "count": null,
            "url":"/app/order/dapp?flag=1"
        },
        {
            "title": "已完成",
            "count": null,
            "url":"/app/order/dapp?flag=2"
        },
        {
            "title": "全部",
            "count": null,
            "url":"/app/order/dapp?flag=4",
        }
    ],
    // 商户预约信息统计
    "merchantCount": [
        {
            "title": "待赴约",
            "count": 3,
            "url":"/app/order/shoplist?flag=0"
        },
        {
            "title": "已完成",
            "count": 0,
            "url":"/app/order/shoplist?flag=2"
        },
        {
            "title": "全部",
            "count": 0,
            "url":"/app/order/shoplist?flag=4"
        }
    ],
    "services": [
        /*{
            "id": 1,
            "title": "Tony老师特约造型设计",
            "image": "img/t1.png",
            "status": 1
        },
        {
            "id": 2,
            "title": "Tony老师特约造型设计",
            "image": "img/t1.png",
            "status": 0
        },
        {
            "id": 3,
            "title": "Tony老师特约造型设计",
            "image": "img/t1.png",
            "status": -1
        },
        {
            "id": 4,
            "title": "Tony老师特约造型设计",
            "image": "img/t1.png",
            "status": 1
        }*/
    ]
};
/**
 * 四个异步请求，将获得的数字，分别放在count这  待赴约
 */
$.ajax({
    url:"/app/order/getOrderCount",
    type:"post",
    data:"flag = 0",
    success:function (result) {
        pageData.userCount[0].count = result[0];
        pageData.userCount[1].count = result[1];
        pageData.userCount[2].count = result[2];
        pageData.userCount[3].count = result[3];
        pageData.merchantCount[0].count = result[4];
        pageData.merchantCount[1].count = result[5];
        pageData.merchantCount[2].count = result[6];
    }
});

/**
 * 异步请求商家的服务信息
 */
$.ajax({
    url:"/app/order/getShopGoods",
    type:"post",
    success:function (result) {
        for (let i = 0;i<result.length;i++){
            let good = {
                "id": result[i].id,
                "title": result[i].goodsname,
                "image": result[i].indexurl,
                "status": result[i].status
            };
            pageData.services.push(good);
        }

    }

});


$(function () {

    // 识别是否来自商户申请页面
    var apply = getUrlParam("apply");
    if(apply != null){
        user.merchant = true;
    }


    // 初始化Vue实例
    var vue = new Vue({
        el: "#app",
        data: {
            user: user,
            pd: pageData
        },
        methods: {
            changeMenu: function (mid) {
                this.pd.pageMenus.mid = mid;
                $.ajax({
                    url:"/app/shop/checkShanghu",
                    type:"post",
                    success:function (result) {
                        if(result!=''){
                            //说明该用户是商户
                            //window.location = "uc.html?apply=0";
                            user.merchant = true;
                        }else{
                            user.merchant = false;
                        }
                    }
                });
            }
        }
    });




});
