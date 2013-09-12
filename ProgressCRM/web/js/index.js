var transfer;
var type;
$(document).ready(function() {
    $("#closeAlert").click(function() {
        $("#errorBlock").css("display", "none");
    });
    $("#closeInfo").click(function() {
        $("#helpInfoBlock").css("display", "none");
        $('#taskContentHelp').addClass("hiddenHelp");
    });
    $.ajax({
        type: "GET",
        url: "api/auth",
        success: function(data) {
            $("#profileLink").html(data);
            $("#logged").css("display", "block");
            $.get("api/auth/validate", function(data3) {
                var permissions = data3;
                if (permissions == "3") {
                    $('#adminTabLink').css("display", "block");
                }
            });
        },
        error: function(data) {
            $("#loginForm").css("display", "block");
            $('#adminTabLink').css("display", "none");
        }
    });
    $("#mainLink").click(function(e) {
        e.preventDefault();
        getMainPage();
    });
    $("#apartamentsLink").click(function(e) {
        e.preventDefault();
        getapartamentsListPage();
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
//    getNews();
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

function getHelpDeskPage() {
    $("#addApartaments").css("display", "none");
    $.get("hd.html", function(data) {
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
    $("#addApartaments").css("display", "none");
    $.get("api/news", function(data) {
        var str = "<table class=\"table\"><tbody>\n";
        var ids = [];
        var content = "course";
        var list = JSON.parse(data);
        for (var i = 0; i < list.length; ++i) {
            ids[i] = list[i].id;
            str += "<tr><td style='border-bottom-style:dashed; border-color:#fff; border-width:1px; border-top-style:none'>";
            str += "\n\<h3><a href=\"#\" onclick=\"return getContent(\'" + content +
                    "\', " + list[i].id + ", " + i + ", \'" + content + "/view\')\">" + list[i].title + "</a>\n</h3>\n";
            str += "<div class=\"row\">\n<div class=\"col-md-7 col-md-offset-1\">\n<p>";
            str += list[i].description + "</p>\n</div>\n</div>";
            str += "</tr></td>";
        }
        str += "\n</tbody>\n</table>\n";
        transfer = ids;
        $("#news").html(str);
    });
    return false;
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