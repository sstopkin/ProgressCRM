var type;
var workersList = "";
var KLADR_token = '51dfe5d42fb2b43e3300006e';
var KLADR_key = '86a2c2a06f1b2451a87d05512cc2c3edfdf41969';
var KLADR_parentId = '5500000100000';

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
            $("#profileLink").html(data);
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
    if (permissions == "3") {
        $('#adminTabLink').css("display", "block");
    }
    parseUrl(location.href);
}

function getMainPage() {
    $.get("main.html", function (data) {
        $("#mainContainer").html(data);
    });
}

function getNewsPage() {
    $.get("news.html", function (data) {
        $("#mainContainer").html(data);
    });
    getNews();
}

function getAdminPage() {
    $.get("api/auth/validate", function (data) {
        if (data == "3") {
            $.get("admin.html", function (data) {
                $("#mainContainer").html(data);
            });
            getUsersManagementList();
        }
        else {
            showWarning("У вас недостаточно прав для совершения данного действия");
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

function checkStatus() {
    $.ajax({
        type: "GET",
        url: "api/auth",
        success: function (data) {
            $("#profileLink").html(data);
            $("#progresscrm").css("display", "block");
            $("#progresscrm_login").css("display", "none");
//            $.get("api/auth/validate", function(data3) {
//                var permissions = data3;
//                if (permissions == "3") {
//                    $('#adminTabLink').css("display", "block");
//                }
//            });
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

function timeConverter(UNIX_timestamp) {
    var a = new Date(UNIX_timestamp);
//    var months = ['Янв', 'Фев', 'Мар', 'Апр', 'Май', 'Июнь', 'Июль', 'Авг', 'Сен', 'Окт', 'Ноя', 'Дек'];
    var year = a.getFullYear();
//    var month = months[a.getMonth() - 1];
    var month = a.getMonth();
    month = (parseInt(month, 10) < 10) ? ('0' + month) : (month);
    var date = a.getDate();
    date = (parseInt(date, 10) < 10) ? ('0' + date) : (date);
    var hour = a.getHours();
    var min = a.getMinutes();
    var sec = a.getSeconds();
//    var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec;
    var time = year + '-' + month + '-' + date + ' ' + hour + ':' + min + ':' + sec;
    return time;
}

function getTimeStamp(date) {
    return new Date(date).getTime();
}

function initCalendar(path) {
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth() + 1; //January is 0!
    var yyyy = today.getFullYear();
    if (dd < 10) {
        dd = '0' + dd;
    }
    if (mm < 10) {
        mm = '0' + mm;
    }
    today = yyyy + '-' + mm + '-' + dd;

    "use strict";

    var options = {
        events_source: path, //srv
        view: 'month',
        tmpl_path: 'js/lib/bootstrap-calendar/tmpls/',
        tmpl_cache: false,
        language: 'ru-RU',
        first_day: 1,
        day: today,
        modal: true,
        onAfterEventsLoad: function (events) {
            if (!events) {
                return;
            }
            var list = $('#eventlist');
            list.html('');

            $.each(events, function (key, val) {
                $(document.createElement('li'))
                        .html('<a href="' + val.url + '">' + val.title + '</a>')
                        .appendTo(list);
            });
        },
        onAfterViewLoad: function (view) {
            $('.page-header h3').text(this.getTitle());
            $('.btn-group button').removeClass('active');
            $('button[data-calendar-view="' + view + '"]').addClass('active');
        },
        classes: {
            months: {
                general: 'label'
            }
        }
    };

    var calendar = $('#calendar').calendar(options);



    $('.btn-group button[data-calendar-nav]').each(function () {
        var $this = $(this);
        $this.click(function () {
            calendar.navigate($this.data('calendar-nav'));
        });
    });

    $('.btn-group button[data-calendar-view]').each(function () {
        var $this = $(this);
        $this.click(function () {
            calendar.view($this.data('calendar-view'));
        });
    });

    $('#events-in-modal').change(function () {
        var val = $(this).is(':checked') ? $(this).val() : null;
        calendar.setOptions({modal: val});
    });

    $('#events-modal .modal-header, #events-modal .modal-footer').click(function (e) {
        e.preventDefault();
        e.stopPropagation();
    });
}