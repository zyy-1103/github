$(function () {
        //回车提交
	let u = location.href.split("/")[location.href.split("/").length - 1];
    let $con=$(".form-control")
    $con.keypress(function (event) {
        if (event.which === 13) {
            if (u == "register") {
                register();
            }else {
                fun();
            }
        }
    });
});

//提交登录信息
function fun() {
    let u=$("#inputUsername");
    let p = $("#inputPassword");
    let e = $("#errorMsg");
    let x = /[\u4e00-\u9fa5]{2,16}/;
    let y = /[\w.]{6,16}/;
    if (u.val() === "" || p.val() === "") {
        e.text("输入框不能为空");
        return false;
    }else if (!x.test(u.val()) ) {
        e.html("用户名:请输入您的真实姓名");
        return false;
    }else if(!y.test(p.val())){
		e.html("密码:6-16位数字字母下划线小数点");
		return false;
	}
    let r;
    if ($("#remember").is(":checked")) {
        r = $("#remember").val();
    }else {
        r = "no";
    }
    $.ajax({
        url: "login",
        type: "post",
        data: {
            user: u.val(),
            password: p.val(),
            remember: r
        },
        dataType: "json",
        success: function (data) {
            e.text();
            if (data.code == 1) {
                location.href = "/";
            } else {
                $("#errorMsg").text(data.code);
            }
        },
        error: function () {
            alert("由于服务器内部原因导致请求失败，请刷新页面重试");
        }
    });
}

function getCode() {
    let email = $("#inputEmail").val();
    if (email == "") {
        $("#errorMsg").text("邮箱不能为空");
        return false;
    }
    let x = /^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/;
    if (!x.test(email)) {
        $("#errorMsg").text("邮箱格式错误");
        return false;
    }
    let timeo=60;
    let timeStop = setInterval(function () {
        timeo--;
        let t = $("#basic-addon1")
        if (timeo > 0) {
            t.text("重新发送" + timeo + "s");
            t.removeAttr("onclick");
        } else {
            timeo = 60;
            t.text("重新发送");
            t.attr("onclick", "getCode()");
            clearInterval(timeStop);
        }
    },1000);
    $.ajax({
        url: "getCode",
        type: "post",
        data:{
            email: email
        },
        success: function () {

        },
        error: function () {
            alert("由于服务器内部原因导致请求失败，请刷新页面重试");
        }
    });
}

function register() {
    alert(1);
    let user = $("#inputUsername").val();
    let pwd = $("#inputPassword").val();
    let pwd2 = $("#inputPassword2").val();
    let email = $("#inputEmail").val();
    let emailCode = $("#emailCode").val();
    let state = $("#inlineFormCustomSelect").val();
    let age = $("#inputAge").val();
    let addr = $("#inputAddress").val();
	alert(1);
	alert(state);
    let error=$("#errorMsg")

    let userName=/[\u4e00-\u9fa5]{2,16}/;
    let passWord=/[\w.]{6,16}/;
	let Age=/[0-9]{1,3}/;
    if (user == "" || pwd == "" || pwd2 == "" || email == "" || emailCode == "" ||state =="" || age=="" ||addr=="") {
        error.text("输入框不能为空");
        return false;
    }else if(state=="0"){
		error.text("请选择工作状态");
		return false;
	}else if(!Age.test(age)){
		error.text("请输入正确的年龄");
		return false;
	}else if(!userName.test(user)){
    	error.text("用户名需为数字|字母|下划线4~16位")
    	return false;
    }else if(!passWord.test(pwd)){
    	error.text("密码需为数字|字母|下划线|小数点6~16位")
    	return false;
    }else if(pwd!=pwd2){
    	error.text("两次密码不一致");
    	return false;
    }
    let x = /^[0-9a-zA-Z_.-]+[@][0-9a-zA-Z_.-]+([.][a-zA-Z]+){1,2}$/;
    if (!x.test(email)) {
        error.text("邮箱格式错误");
        return false;
    }

    $.ajax({
        url: "register",
        type: "post",
        data:{
            user:user,
            password:pwd,
            password2:pwd2,
            email:email,
            code:emailCode,
			age:age,
			address:addr,
			workState:state
        },
        dataType:"json",
        success: function (data) {
            if (data.code == 1) {
                alert("注册成功");
                location.href = "/";
            }else {
                error.html(data.code);
            }
        },
        error: function () {
            alert("由于服务器内部原因导致请求失败，请刷新页面重试");
        }
    });

}