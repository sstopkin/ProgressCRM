var transfer;
var type;
var workersList;
$(document).ready(function() {
    $("#closeAlert").click(function() {
        $("#errorBlock").css("display", "none");
    });
    $("#closeInfo").click(function() {
        $("#helpInfoBlock").css("display", "none");
        $('#taskContentHelp').addClass("hiddenHelp");
    });
    $("#addApartaments").css("display", "none");
    $.ajax({
        type: "GET",
        url: "api/auth",
        success: function(data) {
            $("#profileLink").html(data);
            $("#logged").css("display", "block");
            $.get("api/auth/validate", function(data3) {
                var permissions = data3;
            });
        },
        error: function(data) {
            $("#loginForm").css("display", "block");
        }
    });
    getAllWorkersList();
    getMainPage();
    $("#mainLink").click(function(e) {
        e.preventDefault();
        getMainPage();
    });
    $("#announcementsLink").click(function(e) {
        e.preventDefault();
        getАnnouncementsPage();
    });
    $("#announcementsRentLink").click(function(e) {
        e.preventDefault();
        getАnnouncementsRentPage();
    });
    $("#apartamentsLink").click(function(e) {
        e.preventDefault();
        getapartamentsListPage();
    });
    $("#customersLink").click(function(e) {
        e.preventDefault();
        getCustomersPage();
    });
    $("#helpDeskLink").click(function(e) {
        e.preventDefault();
        getHelpDeskPage();
    });

    $("#tasksLink").click(function(e) {
        e.preventDefault();
        getTasksPage("task");
    });
    $('#adminLink').click(function(e) {
        e.preventDefault();
        getAdminPage("admin");
    });


    $("#aboutLink").click(function(e) {
        e.preventDefault();
        getAboutPage("about");
    });

    $("#callsLink").click(function(e) {
        e.preventDefault();
        getCallsPage();
    });
});

function getMainPage() {
    $("#addApartaments").css("display", "none");
    $.get("main.html", function(data) {
        $("#mainContainer").html(data);
    });
    getNews();
}

function getAboutPage() {
    $("#addApartaments").css("display", "none");
    $.get("about.html", function(data) {
        $("#mainContainer").html(data);
    });
}

function getCallsPage() {
    $("#addApartaments").css("display", "none");
    $.get("calls.html", function(data) {
        $("#mainContainer").html(data);
    });
}

function getAdminPage() {
    $("#addApartaments").css("display", "none");
    $.get("api/auth/validate", function(data) {
        if (data == "3") {
            $.get("admin.html", function(data) {
                $("#mainContainer").html(data);
                $("#usersLink").click(getUsersList);
                $("#verifyTasksLink").click(getUnevaluatedTasks);
                $("#moderationLink").click(getModerationPage)
                getUsersList();
            });
        }
    });
}

function getNews() {
    var permissions;
    if (permissions == "3") {

    }
    $.ajax({
        type: "GET",
        url: "api/auth/validate",
        success: function(data) {
            permissions = data;
            $.get("api/news", function(data) {
                var str = "<table class=\"table\"><tbody>\n";
                var ids = [];
                var content = "course";
                var list = JSON.parse(data);
                for (var i = 0; i < list.length; ++i) {
                    ids[i] = list[i].id;
                    str += "<tr><td>";
                    str += "<h3>" + list[i].header + "</h3>";
                    str += "<div class=\"row\">";
                    str += "<div class=\"col-md-7 col-md-offset-1\">";
                    if (permissions == "3") {
                        str += "<div class=\"btn-toolbar\">";
                        str += "<div class=\"btn-group\">";

                        str += "<button type=\"button\" onclick=\"editNewsById(" + list[i].id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
                        str += "<button type=\"button\" onclick=\"deleteNewsById(" + list[i].id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";

                        str += "</div>";
                        str += "</div>";
                    }
                    str += "<p>" + list[i].text + "</p>";
                    str += "</div>";
                    for (var it = 0; it < workersList.length; ++it) {
                        var a = workersList[it];
                        if (list[i].idWorker == a[0]) {
                            str += "<p>" + a[1] + a[3] + "</p>";
                        }
                    }
                    str += "<p>" + list[i].lastModify + "</p>";
                    str += "</div>";
                    str += "</tr></td>";
                }
                str += "\n</tbody>\n</table>\n";
                transfer = ids;
                $("#news").html(str);
            });

        },
        error: function(data) {
            showWarning(data.responseText);
        }
    });
    $("#addApartaments").css("display", "none");
    return false;
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
            getMainPage();
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}

function showDanger(message) {
    $("#errorBlock").addClass("alert-danger");
    $("#errorMessage").html(message);
    $("#errorBlock").css("display", "block");
}

function showWarning(message) {
    $("#errorBlock").addClass("alert-warning");
    $("#errorMessage").html(message);
    $("#errorBlock").css("display", "block");
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
//            $('#adminTabLink').css("display", "none");
        }
    });
}

function deleteNewsById(newsId) {
    $.ajax({
        type: "POST",
        url: "api/news/deletenews",
        data: ({id: newsId}),
        success: function(data) {
            getMainPage();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            checkStatus();
            return false;
        }
    });
    return false;
}

function returnSearchResult() {
    $("#customerSearchModal").modal('hide');//FIXME toggle?
    console.log($(":radio[name=browser]").filter(":checked").val());
    $("#IdCustomer").val($(":radio[name=browser]").filter(":checked").val());

}

function customersSearchAction() {
    $.ajax({
        type: "GET",
        url: "api/customers/search?query=" + $("#customersSearchQuery").val(),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            var array = JSON.parse(data);
            var str = "";
            str += "<table class=\"table table-striped table-bordered table-condensed\" style='margin-top:10px;'>";
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
        },
        error: function(data) {
            showDanger(data.responseText);
            return false;
        }
    });
}

function customersShowModal() {
    console.log("customersInitModal");
    $('#customerSearchModal').modal('show')
    customersSearchAction();
}

function getAllWorkersList() {
    $.ajax({
        type: "GET",
        url: "api/auth/userslist",
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