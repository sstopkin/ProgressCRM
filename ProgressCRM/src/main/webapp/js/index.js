var type;
var workersList = null;
var taskStatusListJSON = "[[0,\"Нет статуса\"],[1,\"Новый\"],[2,\"В работе\"],[3,\"Прогресс\"],[4,\"Заселился\"]]";
var taskStatusList = null;
var KLADR_token = '51dfe5d42fb2b43e3300006e';
var KLADR_key = '86a2c2a06f1b2451a87d05512cc2c3edfdf41969';
var KLADR_parentId = '5500000100000';

var permissions;

var map = null;
var placemark = null;
var map_created = false;
$(document).ready(function() {
    getAllWorkersList();
    $.ajax({
        type: "GET",
        url: "api/auth",
        async: false,
        success: function(data) {
            $("#profileLink").html(data);
            $("#logged").css("display", "block");
            if (workersList == null) {
                getAllWorkersList();
            }
        },
        error: function(data) {
            $("#loginForm").css("display", "block");
            showDanger(data.responseText);
        }
    });
    permissions = $.ajax({
        type: "GET",
        url: "api/auth/validate",
        async: false
    }).responseText;
    if (permissions == "3") {
        $('#adminTabLink').css("display", "block");
    }
    parseUrl(location.href);
    $('#loginForm').keydown(function(event) {
        if (event.which == 13) {
            $('#loginBtn').click();
        }
    });
    $("#closeAlert").click(function() {
        $("#errorBlock").css("display", "none");
    });
});

function getMainPage() {
    $.get("main.html", function(data) {
        $("#mainContainer").html(data);
    });
}

function getNewsPage() {
    $.get("news.html", function(data) {
        $("#mainContainer").html(data);
    });
    getNews();
}

function getAboutPage() {
    $.get("about.html", function(data) {
        $("#mainContainer").html(data);
    });
}

function getAdminPage() {
    $.get("api/auth/validate", function(data) {
        if (data == "3") {
            $.get("admin.html", function(data) {
                $("#mainContainer").html(data);
           });
        }
        else {
            showWarning("У вас недостаточно прав для совершения данного действия");
        }
    });
}

function getNews() {
    var str = "";

    $.get("api/news", function(data) {

        var array = JSON.parse(data);
        array.forEach(function(entry) {
            str += "<div class=\"panel panel-info\">";
            str += "<div class=\"panel-heading\">#" + entry.id + " | " + "<b>" + entry.header + "</b>" + " | " + entry.lastModify + "</div>";
            str += "<div class=\"panel-body\">";
            str += "<div class=\"media-body\">";
            if (permissions == "3") {
                str += "<button type=\"button\" onclick=\"confirmActionDelete('deleteNewsById(" + entry.id + ")');\" class=\"btn btn-danger pull-right\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
                str += "<button type=\"button\" onclick=\"editNewsById(" + entry.id + ");\" class=\"btn btn-warning pull-right\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
            }
            str += "<p>" + entry.text + "</p>";
            for (var it = 0; it < workersList.length; ++it) {
                var a = workersList[it];
                if (entry.idWorker == a[0]) {
                    str += "<p><i>" + a[3] + " " + a[1] + "</i></p>";
                }
            }
            str += "</div>";
            str += "</div>";
            str += "</div>";
            str += "</div>";
        });

        $("#news").html(str);
    }).fail(function(data) {
        showDanger(data.responseText);
    });
}

function  editNewsById(id) {
    alert(editNewsById + " " + id);
}

function addNews() {
    $('#newsModal').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/news/addnews",
        data: ({
            header: $('#newsHeader').val(),
            text: $('#newsText').val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            location.reload();
        },
        error: function(data) {
            showDanger(data.responseText);
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
        success: function(data) {
            $("#profileLink").html(data);
            $("#logged").css("display", "block");
            $("#loginForm").css("display", "none");
//            $.get("api/auth/validate", function(data3) {
//                var permissions = data3;
//                if (permissions == "3") {
//                    $('#adminTabLink').css("display", "block");
//                }
//            });
        },
        error: function(data) {
            $("#loginForm").css("display", "block");
            $("#logged").css("display", "none");
        }
    });
}

function deleteNewsById(newsId) {
    $.ajax({
        type: "POST",
        url: "api/news/deletenews",
        data: ({id: newsId}),
        success: function(data) {
            location.href("#news");
        },
        error: function(data) {
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
        success: function(data) {
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
        error: function(data) {
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
        success: function(data) {
            workersList = JSON.parse(data);
            return true;
        },
        error: function(data) {
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
                callback: function() {
                    eval(func + ';');
                }
            },
            danger: {
                label: "Отмена",
                className: "btn-danger",
                callback: function() {
                }
            }
        }
    });
}

function getCountData() {
    $.ajax({
        type: "GET",
        url: "api/auth/info",
        success: function(data) {
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
        error: function(data) {
            showDanger(data.responseText);
            return false;
        }
    });
}