$().ready(function () {

    var validator = $("#loginInfo").validate({
        errorPlacement:function (error, element) {                   <!--一定要换行这样写？？？？-->
            $( element )
                .closest( "div" )
                .find( "label[for='" + element.attr("id") + "']" )
                .append(error);
        },
        errorElement: "span",
        messages: {
            username: {
                required:"(用户名不能为空！)",
                minlength: " (用户名不能少于五个字符！)",
                maxlength: "(多了也不行啊！)"
            },
            password: {
                required:"(密码不能为空！)",
                minlength: "(密码也不能少于五个字符)"
            }
        }
    });
});


var login = new Vue({

    el:'#app',

    data:{
        username:'',
        password:'',
    }

});

