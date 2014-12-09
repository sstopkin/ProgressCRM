$(document).ready(function () {
    $("#chngPwdBtn").click(function () {
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
                success: function (data) {
                    $("#chngPasswordCurrent").val("");
                    $("#chngPassword").val("");
                    $("#chngPassword2").val("");
                    $("#chngPwdErr").html("");
                    $("#chngPwdModal").modal("hide");
                },
                error: function (data) {
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
    $.get("profile.html", function (data) {
        $("#mainContainer").html(data);
//        $('#chngPwd').validate({
//            rules: {
//                oldPassword: {
//                    required: true,
//                    minlength: 6,
//                    maxlength: 50
//                },
//                password: {
//                    required: true,
//                    minlength: 6,
//                    maxlength: 50
//                },
//                password2: {
//                    equalTo: '#chngPassword'
//                }
//            },
//            highlight: function(element, errorClass, validClass) {
//                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
//            },
//            unhighlight: function(element, errorClass, validClass) {
//                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
//            },
//            success: function(label) {
//                $(label).closest('form').find('.valid').removeClass("invalid");
//            },
//            errorPlacement: function(error, element) {
//                error.text(element.closest('.form-group').find('.help-block'));
//            }
//        });
        $.ajax({
            type: "GET",
            url: "api/auth/info",
            success: function (data) {
                $("#errorBlock").css("display", "none");
                $("#tags").css("display", "none");
                $("#userProfile").css("display", "block");

                var value = JSON.parse(data);
                $("#profileEmail").html(value.email);
                $("#profileFName").html(value.fName);
                $("#profileMName").html(value.mName);
                $("#profileLName").html(value.lName);
            },
            error: function (data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function runOktellClient() {
    //    Пример подключения к серверу Oktell при помощи oktell.js
    // дополнительные параметры подключения смотрите в документации oktell.js
    oktell.connect({
        url: [$('#oktellServerAddress').val()], // ip-адрес вашего сервера Oktell ['']
        login: $('#oktellUserLogin').val(), // необходимо подставить логин текущего пользователя
        oktellVoice: false, // используем веб-телефон Oktell-voice.js
        password: $('#oktellUserPassword').val(), // необходимо подставить пароль пользователя
        callback: function (data) {
            if (data.result) {
                alert("ok");
            }
        }
    });
    // Пример инициализации oktell-panel.js
    $.oktellPanel({
        oktell: window.oktell, // можно задать ссылку на объект Oktell.js
        oktellVoice: window.oktellVoice, // можно задать ссылку на объект Oktell-voice.js
        dynamic: false, // если true, то панель не скрывается для окна шириной больше 1200px;
        // если false, то панель скрывается для любой ширины окна
        position: 'right', // положение панели, возможные варианты 'right' и 'left'
        ringtone: 'media/ring.mp3', // путь до мелодии вызова
        debug: false, // логирование в консоль
        lang: 'ru', // язык панели, также поддерживаются английский 'en' и чешский 'cz'
        showAvatar: false, // показывать аватары пользователей в списке
        hideOnDisconnect: true, // скрывать панель при разрывае соединения с сервером Oktell
        useNotifies: true, // показывать webkit уведомления при входящем вызове
        container: $('#oktellContainer'), //false DOMElement или jQuery элемент, который нужно использовать как контейнер.
        useSticky: true, // использовать залипающие заголовки;
        // на мобильных устройствах и при использовании контейнера (параметр container)
        // не используются.
        useNativeScroll: true, // использовать нативный скролл для списка.
        // на мобильных устройствах и при использовании контейнера (параметр container)
        // всегда используется нативный скролл.
        withoutPermissionsPopup: false, // не использовать попап для запросов доступа к микрофону
        withoutCallPopup: false, // не использовать попап для входящих вызовов
        withoutError: false // не показывать ошибки соединения с сервером Oktell
    });
}