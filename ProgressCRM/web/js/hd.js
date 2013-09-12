$("#addHelpDeskRequestModalBtn").click(function() {
    alert("asd");
//    $("#regErr").css("display", "none");
//    if ($("#emailDiv").hasClass("has-error")) {
//        $("#regErr").css("display", "inline");
//        $("#regErr").html("Неверный Email");
//        return false;
//    }
//
//    if ($("#nameDiv").hasClass("has-error")) {
//        $("#regErr").css("display", "inline");
//        $("#regErr").html("Неверно задано имя");
//        return false;
//    }
//
//    if ($("#lnameDiv").hasClass("has-error")) {
//        $("#regErr").css("display", "inline");
//        $("#regErr").html("Неверно задана фамилия");
//        return false;
//    }
//
//    if ($("#passwordDiv").hasClass("has-error")) {
//        $("#regErr").css("display", "inline");
//        $("#regErr").html("Пароль должен содержать от 6 символов и\\или цифр");
//        return false;
//    }
//
//    if (($("#email").val() == "") || ($("#name").val() == "")
//            || ($("#lname").val() == "") || ($("#password").val() == "")
//            || ($("#password2").val() == "")) {
//        $("#regErr").css("display", "inline");
//        $("#regErr").html("Не все обязательные поля заполнены");
//        return false;
//    }
//    if (($("#email").val().length > 50)
//            || ($("#name").val().length > 50)
//            || ($("#lname").val().length > 50)
//            || ($("#password").val().length > 50)) {
//        $("#regErr").css("display", "inline");
//        $("#regErr").html("Превышена максимальная длина поля");
//        return false;
//    }
//    if ($("#password").val() != $("#password2").val()) {
//        $("#regErr").css("display", "inline");
//        $("#regErr").html("Пароли не совпадают");
//        return false;
//    }
//    $.ajax({
//        type: "POST",
//        url: "api/reg",
//        data: ({
//            email: $("#email").val(),
//            name: $("#name").val(),
//            lname: $("#lname").val(),
//            pass: $("#password").val()
//        }),
//        success: function(data) {
//            $("#registerModal").modal("hide");
//            $("#regNames").html($("#name").val() + " " + $("#lname").val());
//            $("#regEmails").html($("#email").val());
//            $("#regSuccess").modal("show");
//            $.ajax({
//                type: "POST",
//                url: "api/auth/login",
//                data: ({
//                    login: $("#email").val(),
//                    pass: $("#password").val()
//                }),
//                success: function(data) {
//                    $("#profileLink").html($("#name").val() + " " + $("#lname").val());
//                    document.cookie = "token=" + data;
//                    $("#loginForm").css("display", "none");
//                    $("#logged").css("display", "block");
//                    $("#email").val("");
//                    $("#name").val("");
//                    $("#lname").val("");
//                    $("#password").val("");
//                    $("#password2").val("");
//                }
//            });
//        },
//        error: function(data) {
//            $("#regErr").css("display", "inline");
//            $("#regErr").html(data.responseText);
//            return false;
//        }
//    });
});
