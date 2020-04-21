let shopmsg = []
let vue = new Vue({
    el:"#app",
    data:{
         shopmsg:shopmsg
    },
    methods:{
        jiedai:function (id) {
            //点击接待后变成待评价
            $.ajax({
                url:"/app/order/changeStatus",
                type:"post",
                data:{
                    "id":id,
                    "status":0
                },
                success:function () {
                    layer.msg("接待成功！");
                }
            });
        }
    }
});
/**
 * 打开商户的待赴约什么的，显示出对应的信息
 */
$.ajax({
    url:"/app/order/getShopDai",
    type:"post",
    success:function (result) {
        for (let i = 0;i<result.length;i++){
            let shop = {
                "nickname":result[i].user.nickname,
                "shopname":result[i].shop.shopname,
                "shopaddress":result[i].shop.shopaddress,
                "date":result[i].date,
                "time":result[i].time,
                "status":result[i].status,
                "id":result[i].id
            };
            shopmsg.push(shop);
        }

    }
});