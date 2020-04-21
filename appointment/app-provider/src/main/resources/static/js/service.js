let shopmsg = {
    "id": null,
    "shopname": null,
    "shopbrief": null,
    "shopaddress": null,
    "indexurl": null,
    "buypeople": null,
    "sellcount": null,
    "shopdetail":null,
    "stoptime":null
}
let vue = new Vue({
    el:"#app",
    data:{
        shopmsg:shopmsg
    },
    methods:{
        nowapp:function (shopid) {
            alert("1111")
            $.ajax({
                url:"/app/order/nowapp",
                type:"post",
                data:"shopid="+shopid,
                success:function (result) {
                    layer.msg("预约成功，请及时赴约哦！");
                }
            });

        },
        nowcol:function (shopid) {
            alert("222")
            $.ajax({
                url:"/app/collection/nowcol",
                type:"post",
                data:"shopid="+shopid,
                success:function () {
                    layer.msg("收藏成功");
                }
            });
        }
    }
});
/**
 * 异步获取店铺信息
 */
$.ajax({
    url:"http://127.0.0.1:99/app/shop/getshopmsg",
    type:"post",
    success:function (result) {
        shopmsg.id = result.id;
        shopmsg.shopname = result.shopname;
        shopmsg.shopbrief = result.shopbrief;
        shopmsg.shopaddress = result.shopaddress;
        shopmsg.shopdetail = result.shopdetail;
        shopmsg.buypeople = result.buypeople;
        shopmsg.indexurl = result.indexurl;
        shopmsg.sellcount = result.sellcount;
        shopmsg.stoptime = result.stoptime;
    }
});

