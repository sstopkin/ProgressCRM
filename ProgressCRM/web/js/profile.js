$(document).ready(function() {
    $("#profileLink").click(function(e) {
        e.preventDefault();
        getProfilePage("profile");
    });
    $("#chngPwdBtn").click(function() {
        if ($("#chngPassword").val() == $("#chngPasswordCurrent").val()) {
            $("#chngPwdErr").html("Новый пароль не должен совпадать с текущим");
            return false;
        }
        if (($("#chngPassword").val() == "") || ($("#chngPassword2").val() == "")) {
            $("#chngPwdErr").html("Введите новый пароль");
            return false;
        }
        if ($("#chngPassword").val() == $("#chngPassword2").val()) {
            $.ajax({
                type: "POST",
                url: "api/auth/chngpwd",
                data: ({
                    oldpwd: $("#chngPasswordCurrent").val(),
                    newpwd: $("#chngPassword").val()
                }),
                success: function(data) {
                    $("#chngPasswordCurrent").val("");
                    $("#chngPassword").val("");
                    $("#chngPassword2").val("");
                    $("#chngPwdErr").html("");
                    $("#chngPwdModal").modal("hide");
                },
                error: function(data) {
                    $("#chngPwdErr").html(data.responseText);
                    return false;
                }
            });
        } else {
            $("#chngPwdErr").html("Введенные пароли не совпадают");
        }
    });
});

function getProfilePage() {
    $("#addApartaments").css("display", "none");
    $.get("profile.html", function(data) {
        $("#mainContainer").html(data);
        $.ajax({
            type: "GET",
            url: "api/auth/info",
            success: function(data) {
                $("#errorBlock").css("display", "none");
                $("#tags").css("display", "none");
                $("#userProfile").css("display", "block");

                var value = JSON.parse(data);
                $("#profileEmail").html(value.email);
                $("#profileName").html(value.name);
                $("#profileLName").html(value.lname);
                $("#profilePoints").html(value.points);


                $("#profileProgressBar").html(resultStr);
                var hei = document.getElementById('forh').offsetHeight;
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

