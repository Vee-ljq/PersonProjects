let orderlist = [];
let vue = new Vue({
    el:"#app",
    data:{
        dlist:orderlist
    },
    methods:{
        delete1:function (id) {
            debugger
            alert("删除中");
            $.ajax({
                url:"/app/collection/delCollection",
                type:"post",
                data:"collectionid="+id,
                success:function (result) {
                    layer.msg("删除成功");
                    window.location.href="/collection.html";
                }
            });
        }
    }
});


$.ajax({
    url:"/app/collection/getCollectionData",
    type:"post",
    success:function (result) {
        for (let i = 0;i<result.length;i++){
            let list = {
                "merchant":result[i].shop.shopname,
                "service":result[i].shop.shopbrief,
                "address":result[i].shop.shopaddress,
                "time":result[i].time,
                "date":result[i].date,
                "id":result[i].id
            };
            orderlist.push(list);
        }

    }
});