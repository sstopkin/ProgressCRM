$(document).ready(function () {
    $("#loginBtn").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "api/auth/login",
            data: ({
                login: $("#loginInput").val(),
                pass: $("#passInput").val()
            }),
            success: function (data) {
                $("#errorBlock").css("display", "none");
                var current = new Date();
                current.setTime(current.getTime() + 7 * 3600 * 24 * 1000);
                document.cookie = "token=" + data + "; expires=" + current.toUTCString();
                $.get("api/auth", function (data2) {
                    $("#navbarUsername").html(data2);
                });
                $("#progresscrm_login").css("display", "none");
                $("#progresscrm").css("display", "block");
                location.hash = "";
                trueAuth();
            },
            error: function (data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });

});

function logOut() {
    $.ajax({
        type: "GET",
        url: "api/auth/logout",
        success: function (data) {
            var cookie_date = new Date();
            cookie_date.setTime(cookie_date.getTime() - 1);
            document.cookie = "token=; expires=" + cookie_date.toGMTString();
            $("#progresscrm").css("display", "none");
            $("#progresscrm_login").css("display", "block");
            $('#adminTabLink').css("display", "none");
            location.hash = "";
            location.reload();
        },
        error: function (data) {
            showDanger(data.responseText);
            return false;
        }
    });
}