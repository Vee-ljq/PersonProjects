let orderlist = []

let vue = new Vue({
    el:"#app",
    data:{
        dlist:orderlist
    },
    methods:{
        sign:function (id) {
            $.ajax({
                url:"/app/order/changeStatus",
                type:"post",
                data:{
                    "id":id,
                    "status":0
                },
                success:function () {
                    layer.msg("签到成功！");
                }
            });
        },
        evaluate:function (id) {
            $.ajax({
                url:"/app/order/changeStatus",
                type:"post",
                data:{
                    "id":id,
                    "status":1
                },
                success:function () {
                    layer.msg("评价成功！");
                }
            });
        }
    }
});

$.ajax({
    url:"/app/order/getdappdata",
    type:"post",
    success:function (result) {
        for (let i = 0;i<result.length;i++){
            let list = {
                "merchant":result[i].shop.shopname,
                "service":result[i].shop.shopbrief,
                "address":result[i].shop.shopaddress,
                "time":result[i].time,
                "date":result[i].date,
                "status":result[i].status,
                "id":result[i].id
            };
            orderlist.push(list);
        }

    }

});