var type;
var workersList = "";
var oktell;

var permissions;

var map = null;
var placemark = null;
var map_created = false;
$(document).ready(function () {
    $('#loginForm').keydown(function (event) {
        if (event.which == 13) {
            $('#loginBtn').click();
        }
    });
    $("#closeAlert").click(function () {
        $("#errorBlock").css("display", "none");
    });
    $.ajax({
        type: "GET",
        url: "api/auth",
        async: false,
        success: function (data) {
            $("#navbarUsername").html(data);
            $("#progresscrm").css("display", "block");
            trueAuth();
        },
        error: function (data) {
            $("#progresscrm_login").css("display", "block");
        }
    });
});

function trueAuth() {
    if (workersList == "") {
        getAllWorkersList();
    }
    permissions = $.ajax({
        type: "GET",
        url: "api/auth/validate",
        async: false
    }).responseText;
    if (permissions == "true") {
        $("#adminMenu").css("display", "block");
    }
    parseUrl(location.href);
    $.ajax({
        type: "GET",
        url: "api/settings",
        async: false,
        success: function (data) {
            var array = JSON.parse(data);
            if (getParameterValue(array, "oktell.enabled") === "true") {//STARTING OKTELL
                var address = getParameterValue(array, "oktell.server.address");
                var login = getParameterValue(array, "oktell.server.login");
                var password = getParameterValue(array, "oktell.server.password");
                runOktellClient(address, login, password);
            }

        },
        error: function (data) {
            showDanger("Ошибка загрузки настроек с сервера");
        }
    });
    (function () {
        var monthNames = ["Января", "Февраля", "Марта", "Апреля", "Мая", "Июня", "Июля", "Августа", "Сентября", "Октября", "Ноября", "Декабря"];
        var dayNames = ["Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"];

        // Create a newDate() object
        var newDate = new Date();

        // Extract the current date from Date object
        newDate.setDate(newDate.getDate());

        // Output the day, date, month and year   
        $('#date').html(dayNames[newDate.getDay()] + " " + newDate.getDate() + ' ' + monthNames[newDate.getMonth()] + ' ' + newDate.getFullYear());

//        setInterval(function () {
//
//            // Create a newDate() object and extract the seconds of the current time on the visitor's
//            var seconds = new Date().getSeconds();
//
//            // Add a leading zero to seconds value
//            $("#sec").html((seconds < 10 ? "0" : "") + seconds);
//        }, 1000);

        setInterval(function () {

            // Create a newDate() object and extract the minutes of the current time on the visitor's
            var minutes = new Date().getMinutes();

            // Add a leading zero to the minutes value
            $("#min").html((minutes < 10 ? "0" : "") + minutes);
        }, 1000);

        setInterval(function () {

            // Create a newDate() object and extract the hours of the current time on the visitor's
            var hours = new Date().getHours();

            // Add a leading zero to the hours value
            $("#hours").html((hours < 10 ? "0" : "") + hours);
        }, 1000);
    })();
}

function getParameterValue(array, paramName) {
    console.log("getParameterValue: ", array, paramName);
    for (var j = 0; j < array.length; ++j) {
        if (array[j].parameter === paramName) {
            console.log("getParameterValue", "return", array[j].value);
            return array[j].value;
        }
    }
}

function getMainPage() {
    $.get("main.html", function (data) {
        $("#mainContainer").html(data);
    });
    getCallsStatsPage();
}

function getNewsPage() {
    $.get("news.html", function (data) {
        $("#mainContainer").html(data);
    });
    getNews();
}

function getAdminPage(page) {
    $.get("api/auth/validate", function (data) {
        if (data == "true") {
            switch (page) {
                case 1:
                    $.get("/templates/page_userandgroupmanagement.html", function (data) {
                        $("#mainContainer").html(data);
                    });
                    getUsersManagementList();
                    getUsersGroupsManagementList();
                    break
                case 2:

                    break
                case 3:
                    $.get("admin.html", function (data) {
                        $("#mainContainer").html(data);
                    });
                    getPermissionManagementList();
                    break
                default:
                    break
            }
        }
        else {
            showDanger("У вас недостаточно прав для совершения данного действия");
        }
    });
}

function showDanger(message) {
    var some_html = '<div class="bs-callout bs-callout-danger">';
    some_html += '<h4>Ошибка</h4>';
    some_html += '<p>' + message + '</p>';
    some_html += '</div>';
    bootbox.alert(some_html);
}

function showWarning(message) {
    var some_html = '<div class="bs-callout bs-callout-warning">';
    some_html += '<h4>Предупреждение</h4>';
    some_html += '<p>' + message + '</p>';
    some_html += '</div>';
    bootbox.alert(some_html);
}

function showSuccess(message) {
    var some_html = '<div class="bs-callout bs-callout-info">';
    some_html += '<h4>Выполнено успешно</h4>';
    some_html += '<p>' + message + '</p>';
    some_html += '</div>';
    bootbox.alert(some_html);
}

function checkStatus() {
    $.ajax({
        type: "GET",
        url: "api/auth",
        success: function (data) {
            $("#navbarUsername").html(data);
            $("#progresscrm").css("display", "block");
            $("#progresscrm_login").css("display", "none");
        },
        error: function (data) {
            $("#progresscrm_login").css("display", "block");
            $("#progresscrm").css("display", "none");
        }
    });
}

function deleteNewsById(newsId) {
    $.ajax({
        type: "POST",
        url: "api/news/deletenews",
        data: ({id: newsId}),
        success: function (data) {
            location.reload();
        },
        error: function (data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

function returnSearchResult() {
    $("#customerSearchModal").modal('hide');//FIXME toggle?
    console.log($(":radio[name=browser]").filter(":checked").val());
    var customerSearchResultField = $("#customerSearchResultField");
    $("#" + customerSearchResultField.val()).val($(":radio[name=browser]").filter(":checked").val());

}

function customersSearchAction(divName) {
    $.ajax({
        type: "GET",
        url: "api/customers/search?query=" + $("#customersSearchQuery").val(),
        success: function (data) {
            $("#errorBlock").css("display", "none");
            var array = JSON.parse(data);
            var str = "";
            var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="customersSearchListTable">';
            str += "<thead class='t-header'>Звонки<tr>";
            str += "<th>ID</th>";
            str += "<th>Фамилия</th>";
            str += "<th>Имя</th>";
            str += "<th>Отчество</th>";
            str += "</tr></thead>";
            str += "<tbody>";
            for (var j = 0; j < array.length; ++j) {
                str += "<tr>";
                str += "<td><input type=\"radio\" name=\"browser\" value=\"" + array[j].id + "\"> ";
                str += "</td><td>";
                str += array[j].customersLname;
                str += "</td><td>";
                str += array[j].customersFname;
                str += "</td><td>";
                str += array[j].customersMname;
                str += "</td></tr>";
            }
            str += "\n</tbody>\n</table>\n";
            $("#customerSearchResultTable").html(str);
            $('#filemanagerListTable').dataTable();
            $("#customerSearchResultField").val(divName);
        },
        error: function (data) {
            showDanger(data.responseText);
            return false;
        }
    });
}

function customersShowModal(divName) {
    $('#customerSearchModal').modal('show')
    customersSearchAction(divName);
}

function getAllWorkersList() {
    $.ajax({
        type: "GET",
        url: "api/auth/userslist",
        async: false,
        success: function (data) {
            workersList = JSON.parse(data);
            return true;
        },
        error: function (data) {
            showDanger(data.responseText);
            return false;
        }
    });
}

function confirmActionDelete(func) {
    confirmAction(func, "Вы уверены что хотите удалить?");
}

function confirmAction(func, message) {
    var some_html = '<div class="bs-callout bs-callout-info">';
    some_html += '<p>' + message + '</p>';
    some_html += '</div>';
    bootbox.dialog({
        message: some_html,
        title: "Подтвердверждение действия",
        buttons: {
            success: {
                label: "Подтвердить",
                className: "btn-success",
                callback: function () {
                    eval(func + ';');
                }
            },
            danger: {
                label: "Отмена",
                className: "btn-danger",
                callback: function () {
                }
            }
        }
    });
}

function getCountData() {
    $.ajax({
        type: "GET",
        url: "api/auth/info",
        success: function (data) {
            $("#errorBlock").css("display", "none");
            $("#tags").css("display", "none");
            $("#userProfile").css("display", "block");

            var value = JSON.parse(data);
            $("#apartamentsPrepareCount").html(value.email);
            $("#apartamentsPriceCount").html(value.fName);
            $("#apartamentsArchiveCount").html(value.mName);
            $("#apartamentsNotsetCount").html(value.lName);
            $("#customersCurrentCount").html(value.lName);
            $("#customersArchiveCount").html(value.lName);
            $("#customersNotsetCount").html(value.lName);
            plannerGetWorkersTasks();
        },
        error: function (data) {
            showDanger(data.responseText);
            return false;
        }
    });
}

function getWorkersFullNameById(idWorker) {
    for (var i = 0; i < workersList.length; ++i) {
        var a = workersList[i];
        if (idWorker === a[0]) {
            return a[1] + " " + a[3];
        }
    }
}

function timeConverter(UNIX_timestamp, param) {
    var a = new Date(UNIX_timestamp);
    var year = a.getFullYear();
    var months = ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июнь', 'Июль', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек'];
    var month = a.getMonth() + 1;
    month = (parseInt(month, 10) < 10) ? ('0' + month) : (month);
    var date = a.getDate();
    date = (parseInt(date, 10) < 10) ? ('0' + date) : (date);
    var hour = a.getHours();
    hour = (parseInt(hour, 10) < 10) ? ('0' + hour) : (hour);
    var min = a.getMinutes();
    min = (parseInt(min, 10) < 10) ? ('0' + min) : (min);
    var sec = a.getSeconds();
    sec = (parseInt(sec, 10) < 10) ? ('0' + sec) : (sec);
    switch (param) {
        case 'short':
            return year + '-' + month + '-' + date;
        case 'human':
            var month = months[a.getMonth()];
            return date + '-' + month + '-' + year + ' ' + hour + ':' + min;
        case 'human-short':
            var month = months[a.getMonth()];
            return date + '-' + month + '-' + year;
        case 'full':
            return year + '-' + month + '-' + date + ' ' + hour + ':' + min + ':' + sec;
        default:
            return year + '-' + month + '-' + date + ' ' + hour + ':' + min + ':' + sec;
    }
}

function getTimeStamp(date) {
    return new Date(date).getTime();
}

function runOktellClient(url, login, password) {
    //    Пример подключения к серверу Oktell при помощи oktell.js
    // дополнительные параметры подключения смотрите в документации oktell.js
    oktell.connect({
        url: [url], // ip-адрес вашего сервера Oktell ['']
        login: login, // необходимо подставить логин текущего пользователя
        oktellVoice: false, // используем веб-телефон Oktell-voice.js
        password: password, // необходимо подставить пароль пользователя
        callback: function (data) {
            if (data.result) {
                $('#notificationsArea').notify({
                    message: {text: 'Oktell клиент запущен!'}
                }).show(); // for the ones that aren't closable and don't fade out there is a .close() function.
            }
        }
    });
    // Пример инициализации oktell-panel.js
    $.oktellPanel({
        oktell: oktell, //window.oktell можно задать ссылку на объект Oktell.js
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