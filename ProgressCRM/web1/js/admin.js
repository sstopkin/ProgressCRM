var taskId;
var access;
var codes = [];

$(document).ready(function() {
    $("#approvedTagLinkForCourses").click(function() {
        getList("course", undefined, 0, true);
        return false;
    });
    $("#nonApprovedTagLinkForCourses").click(function() {
        getList("course", undefined, 0, false);
        return false;
    });
    $("#allTagLinkForCourses").click(function() {
        getList("course", undefined, 0);
        return false;
    });

    $("#approvedTagLinkForTasks").click(function() {
        getList("task", undefined, 0, true);
        return false;
    });
    $("#nonApprovedTagLinkForTasks").click(function() {
        getList("task", undefined, 0, false);
        return false;
    });
    $("#allTagLinkForTasks").click(function() {
        getList("task", undefined, 0);
        return false;
    });
});

function getUnevaluatedTasks() {
    $.ajax({
        type: "GET",
        url: "api/admin/getunevaluated",
        success: function(data) {
            var list = JSON.parse(data);
            var str = "";
            str += "<table class=\"table table-hover table-bordered\" style='margin-top:10px; margin-bottom:10px;' >";
            str += "<thead><tr>";
            str += "<th>Email</th>";
            str += "<th>Задание</th>";
            str += "<th>Исходный код</th>";
            str += "<th>Оценка</th>";
            str += "</tr></thead>";
            str += "<tbody>";

            for (var j = 0; j < list.length; ++j) {
                str += "<tr><td>";
                str += list[j][1];
                str += "</td><td>";
                str += "<a href=\"#\" onclick=\"return getContent(\'task" +
                        "\', " + list[j][0].taskId + ", " + j + ")\">" + list[j][2] + "</a>";
                str += "</td><td>";
                codes[j] = list[j][0].source;
                str += "<a href=\"#\" onclick=\"viewCode(" + j + ");\">Просмотр кода</a>";
                str += "</td><td align='center'>";
                str += "<div class='btn-group'>"
                str += "<button type='button' onclick=\"return checkSuccessTask(" + list[j][0].id + ");\" class='btn btn-success'>Верно</button>"
                str += "<button type='button' onclick=\"return checkFailTask(" + list[j][0].id + ");\" class='btn btn-danger'>Неверно</button>"
                str += "</div>"
                ///str += "<a href=\"#\" onclick=\"checkSuccessTask(" + list[j][0].id + ");\"><span class=\"glyphicon glyphicon-ok\"></span></a>";
                //str += "<a href=\"#\" onclick=\"checkFailTask(" + list[j][0].id + ");\"><span class=\"glyphicon glyphicon-remove\"></span></a>";
                str += "</td></tr>";
            }
            str += "</tbody></table>";
            $("#adminContent").html(str);
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function getUsersList() {
    $.ajax({
        type: "GET",
        url: "api/admin/getallusers",
        success: function(data) {
            var list = JSON.parse(data);
            var str = "";
            str += "<table class=\"table table-hover table-bordered\" style='margin-top:10px; margin-bottom:10px;' >";
            str += "<thead><tr>";
            str += "<th>Email</th>";
            str += "<th>Уровень</th>";
            str += "<th>Сумма баллов</th>";
            str += "<th>Активен</th>";
            str += "</tr></thead>";
            str += "<tbody>";

            for (var j = 0; j < list.length; ++j) {
                str += "<tr><td>";
                str += list[j][0].email;
                str += "</td><td>";
                str += list[j][1];
                str += "</td><td>";
                str += "<div class=\"col-md-8\"><div class='input-group'><input class=\"form-control\" type=\"text\" id=\"userPoints" + j + "\" value=\"" + list[j][0].points + "\">";
                str += "<span class=\"input-group-btn\"><button class='btn btn-default' type='button' onclick=\"return changePoints(" + list[j][0].id + ", " + j + ");\">Установить очки</button></span></div></div>";
                str += "</td><td>";
                str += list[j][0].isActive;
                if (list[j][0].isActive == true) {
                    str += "<a href=\"#\" onclick=\"return banUser(" + list[j][0].id + ");\"><span class=\"glyphicon glyphicon-remove-sign\"></span></a>";
                } else {
                    str += "<a href=\"#\" onclick=\"return unBanUser(" + list[j][0].id + ");\"><span class=\"glyphicon glyphicon-plus-sign\"></span></a>";
                }
                str += "</td></tr>";
            }
            str += "</tbody></table>";
            $("#adminContent").html(str);
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function getModerationPage() {
    $.ajax({
        type: "GET",
        url: "api/admin/getnonapprovedcourses",
        success: function(data) {
            var list = JSON.parse(data);
            var str = "";
            str += "<div><table class=\"table table-hover table-bordered\" style='margin-top:10px; margin-bottom:10px;'>";
            str += "<thead><tr>";
            str += "<th>#</th>";
            str += "<th>Название курса</th>";
            str += "</tr></thead>";
            str += "<tbody>";

            for (var j = 0; j < list.length; ++j) {
                str += "<tr><td class=\"col-md-1\">";
                str += j;
                str += "</td><td>";
                str += "<a href=\"#\" onclick=\"return getContent(\'course" +
                        "\', " + list[j].id + ", " + j + ")\">" + list[j].title + "</a>";
                str += "</td></tr>";
            }
            str += "</tbody></table></div>\n";
            $.ajax({
                type: "GET",
                url: "api/admin/getnonapprovedtasks",
                success: function(data) {
                    var list = JSON.parse(data);
                    str += "<div><table class=\"table table-hover table-bordered\" style='margin-top:10px; margin-bottom:10px;' >";
                    str += "<thead><tr>";
                    str += "<th>#</th>";
                    str += "<th>Название задания</th>";
                    str += "</tr></thead>";
                    str += "<tbody>";

                    for (var j = 0; j < list.length; ++j) {
                        str += "<tr><td class=\"col-md-1\">";
                        str += j;
                        str += "</td><td>";
                        str += "<a href=\"#\" onclick=\"return getContent(\'task" +
                                "\', " + list[j].id + ", " + j + ")\">" + list[j].title + "</a>";
                        str += "</td></tr>";
                    }
                    str += "</tbody></table></div>";
                    $("#adminContent").html(str);
                }
            });
        }
    });
    return false;
}

function viewCode(j) {
    $("#codeFormMessage").html(codes[j]);
    $("#codeForm").modal('show');
}

function changePoints(id, j) {
    $.ajax({
        type: "POST",
        url: "api/admin/chngpoints",
        data: ({
            id: id,
            points: $("#userPoints" + j).val()
        }),
        success: function(data) {
            getUsersList();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function banUser(id) {
    $.ajax({
        type: "POST",
        url: "api/admin/banuser",
        data: ({id: id}),
        success: function(data) {
            getUsersList();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function unBanUser(id) {
    $.ajax({
        type: "POST",
        url: "api/admin/unbanuser",
        data: ({id: id}),
        success: function(data) {
            getUsersList();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function checkSuccessTask(id) {
    $.ajax({
        type: "POST",
        url: "api/admin/evaluatesuccess",
        data: ({id: id}),
        success: function() {
            getUnevaluatedTasks();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function checkFailTask(id) {
    $.ajax({
        type: "POST",
        url: "api/admin/evaluatefail",
        data: ({id: id}),
        success: function() {
            getUnevaluatedTasks();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}


function editContentAndHref(_content, id, href) {
    if (href) {
        location.hash = href + "/" + id;
    }
    editContent(_content, id);
    return false;
}

function editContent(_content, id) {
    $("#footerContent").css("display", "none");
    $.get("create.html", function(data) {
        $("#mainContainer").html(data);
        $("#accessCheck").click(function() {
            if (document.getElementById("accessCheck").checked) {
                access = true;
            } else {
                access = false;
            }
        });
        if (_content == "course") {
            $("#courseEditReadyLink").css("display", "inline");
            $("#createPageTitle").html("Редактирование курса");
            $.ajax({
                type: "GET",
                url: "api/course/getcourse",
                data: ({id: id}),
                success: function(data) {
                    var content = JSON.parse(data);
                    $('#contentName').val(content.course.title);
                    $('#contentDesc').val(content.course.description);
                    $('#contentTags').val(content.Tags);
                    tinyMCE.get('content').setContent(content.course.content);
                    $("#courseEditReadyLink").click(function() {
                        if (($('#contentName').val() == "") || ($('#contentDesc').val() == "") ||
                                ($('#contentTags').val() == "") ||
                                (tinyMCE.get('content').getContent() == "")) {
                            $("#errorMessage").html("Не все поля заполнены");
                            $("#errorBlock").css("display", "block");
                            return false;
                        }
                        $.ajax({
                            type: "POST",
                            url: "api/course/editcourse",
                            data: ({
                                id: id,
                                name: $('#contentName').val(),
                                desc: $('#contentDesc').val(),
                                tags: $('#contentTags').val(),
                                content: tinyMCE.get('content').getContent()
                            }),
                            success: function(data) {
                                $("#errorBlock").css("display", "none");
                                location.hash = "course";
                                getCoursesPage();
                            },
                            error: function(data) {
                                $("#errorMessage").html(data.responseText);
                                $("#errorBlock").css("display", "block");
                                return false;
                            }
                        });
                    });
                },
                error: function(data) {
                    $("#errorBlock").addClass("alert-danger");
                    $("#errorMessage").html(data.responseText);
                    $("#errorBlock").css("display", "block");
                    return false;
                }
            });
        }
        if (_content == "task") {
            $("#exHelp").attr("data-content", "Наборы эталонных вводов/выводов указываются в формате JSON:\n[\"ввод1\",\"ввод2\"] ");
            $("#exHelp").popover("hide");
            $("#accessCheck").removeAttr("checked", "checked");
            $("#accessBlock").css('display', 'inline');
            $("#contentHelpBox").css("display", "block");
            $("#taskEditReadyLink").css("display", "inline");
            $("#taskEditReadyLink1").css("display", "inline");
            $("#contentForTasks").css("display", "block");
            $("#createPageTitle").html("Редактирование задания");

            type = 0;
            $("#step1Link").click(step1Click);
            $("#step2Link").click(step2Click);
            $("#step3Link").click(step3Click);

            $.ajax({
                type: "GET",
                url: "api/task/gettask",
                data: ({id: id}),
                success: function(data) {
                    taskId = id;
                    var content = JSON.parse(data);
                    $('#contentName').val(content.task.title);
                    $('#contentDesc').val(content.task.description);
                    $('#contentHelp').val(content.task.helpText);
                    $('#contentSourse').val(content.task.sourceCode);
                    $('#contentTags').val(content.Tags);
                    tinyMCE.get('content').setContent(content.task.content);
                    $('#unitTest').val(content.task.test);
                    $.ajax({
                        type: "GET",
                        url: "api/task/getreference",
                        data: ({id: taskId}),
                        success: function(data) {
                            var ref = JSON.parse(data);
                            $('#exInput').val(ref[0]);
                            $('#exOutput').val(ref[1]);
                        }
                    });

                    $("#typeSelect").val(content.task.taskType);
                    $("#diffSelect").val(content.task.difficulty);
                    if (content.task.anonymousAccess == true) {
                        document.getElementById("accessCheck").checked = true;
                    } else {
                        document.getElementById("accessCheck").checked = false;
                    }
                    if (content.task.taskType == 1) {
                        $("#step3Link").css("display", "none");
                        $("#exInputBlock").css("display", "block");
                        $("#exOutputBlock").css("display", "block");
                        $("#taskEditReadyLink").css("display", "none");
                        $("#taskEditReadyLink1").css("display", "inline");
                        $("#taskEditReadyLink1").unbind();
                        $("#taskEditReadyLink1").bind("click", editTaskType1);
                    }
                    if (content.task.taskType == 2) {
                        $("#step3Link").css("display", "block");
                        $("#step2Link").removeClass("step-active-at-end step-next-at-end");
                        $("#step2Link").addClass("step-next");
                        $("#exOutputBlock").css("display", "none");
                        $("#exInputBlock").css("display", "none");
                        $("#taskEditReadyLink1").css("display", "none");
                        $("#taskEditReadyLink").css("display", "inline");
                        $("#taskEditReadyLink").unbind();
                        $("#taskEditReadyLink").bind("click", editTaskType2);
                    }
                    if (content.task.taskType == 3) {
                        $("#step3Link").css("display", "none");
                        $("#exInputBlock").css("display", "none");
                        $("#exOutputBlock").css("display", "none");
                        $("#taskEditReadyLink").css("display", "none");
                        $("#taskEditReadyLink1").css("display", "inline");
                        $("#taskEditReadyLink1").unbind();
                        $("#taskEditReadyLink1").bind("click", editTaskType3);
                    }
                },
                error: function(data) {
                    $("#errorBlock").addClass("alert-danger");
                    $("#errorMessage").html(data.responseText);
                    $("#errorBlock").css("display", "block");
                    return false;
                }
            });
        }

        $("#typeSelect").unbind();
        $("#typeSelect").change(function() {
            if ($(this).val() == 1) {
                $("#step3Link").css("display", "none");
                $("#exInputBlock").css("display", "block");
                $("#exOutputBlock").css("display", "block");
                $("#taskEditReadyLink").css("display", "none");
                $("#taskEditReadyLink1").css("display", "inline");
                $("#taskEditReadyLink1").unbind();
                $("#taskEditReadyLink1").bind("click", editTaskType1);
            }
            if ($(this).val() == 2) {
                $("#step3Link").css("display", "block");
                $("#step2Link").removeClass("step-active-at-end step-next-at-end");
                $("#step2Link").addClass("step-next");
                $("#exOutputBlock").css("display", "none");
                $("#exInputBlock").css("display", "none");
                $("#taskEditReadyLink1").css("display", "none");
                $("#taskEditReadyLink").css("display", "inline");
                $("#taskEditReadyLink").unbind();
                $("#taskEditReadyLink").bind("click", editTaskType2);
            }
            if ($(this).val() == 3) {
                $("#step3Link").css("display", "none");
                $("#exInputBlock").css("display", "none");
                $("#exOutputBlock").css("display", "none");
                $("#taskEditReadyLink").css("display", "none");
                $("#taskEditReadyLink1").css("display", "inline");
                $("#taskEditReadyLink1").unbind();
                $("#taskEditReadyLink1").bind("click", editTaskType3);
            }
        });
    });
}

function editTaskType1() {
    if (($('#contentName').val() == "") || ($('#contentDesc').val() == "") ||
            ($('#contentTags').val() == "") || ($('#contentHelp').val() == "") ||
            (tinyMCE.get('content').getContent() == "") || ($("#exInput").val() == "") ||
            ($("#exOutput").val() == "") || ($("#diffSelect").val() == 0) ||
            ($("#typeSelect").val() == 0)) {
        $("#errorBlock").addClass("alert-danger");
        $("#errorMessage").html("Не все поля заполнены");
        $("#errorBlock").css("display", "block");
        return false;
    }
    $.ajax({
        type: "POST",
        url: "api/task/edittask",
        data: ({
            id: taskId,
            name: $('#contentName').val(),
            desc: $('#contentDesc').val(),
            tags: $('#contentTags').val(),
            sourse: $("#contentSourse").val(),
            content: tinyMCE.get('content').getContent(),
            help: $('#contentHelp').val(),
            input: $("#exInput").val(),
            output: $("#exOutput").val(),
            access: access,
            type: $("#typeSelect").val(),
            diff: $("#diffSelect").val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            location.hash = "task";
            getTasksPage();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
}

function editTaskType2() {
    if (($('#contentName').val() == "") || ($('#contentDesc').val() == "") ||
            ($('#contentTags').val() == "") || ($('#contentHelp').val() == "") ||
            (tinyMCE.get('content').getContent() == "") || ($("#unitTest").val() == "") ||
            ($("#diffSelect").val() == 0) || ($("#typeSelect").val() == 0)) {
        $("#errorBlock").addClass("alert-danger");
        $("#errorMessage").html("Не все поля заполнены");
        $("#errorBlock").css("display", "block");
        return false;
    }
    $.ajax({
        type: "POST",
        url: "api/task/edittask",
        data: ({
            id: taskId,
            name: $('#contentName').val(),
            desc: $('#contentDesc').val(),
            tags: $('#contentTags').val(),
            sourse: $("#contentSourse").val(),
            content: tinyMCE.get('content').getContent(),
            help: $('#contentHelp').val(),
            test: $('#unitTest').val(),
            access: access,
            type: $("#typeSelect").val(),
            diff: $("#diffSelect").val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            location.hash = "task";
            getTasksPage();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
}

function editTaskType3() {
    if (($('#contentName').val() == "") || ($('#contentDesc').val() == "") ||
            ($('#contentTags').val() == "") || ($('#contentHelp').val() == "") ||
            (tinyMCE.get('content').getContent() == "") ||
            ($("#diffSelect").val() == 0) || ($("#typeSelect").val() == 0)) {
        $("#errorBlock").addClass("alert-danger");
        $("#errorMessage").html("Не все поля заполнены");
        $("#errorBlock").css("display", "block");
        return false;
    }
    $.ajax({
        type: "POST",
        url: "api/task/edittask",
        data: ({
            id: taskId,
            name: $('#contentName').val(),
            desc: $('#contentDesc').val(),
            tags: $('#contentTags').val(),
            sourse: $("#contentSourse").val(),
            content: tinyMCE.get('content').getContent(),
            help: $('#contentHelp').val(),
            access: access,
            type: $("#typeSelect").val(),
            diff: $("#diffSelect").val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            location.hash = "task";
            getTasksPage();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
}

function removeContent(_content, id) {
    $.ajax({
        type: "GET",
        url: "api/" + _content + "/get" + _content,
        data: ({id: id}),
        success: function(data) {
            var content = JSON.parse(data);
            if (_content == "course") {
                content.content = content.course;
            } else {
                content.content = content.task;
            }
            $("#confirmationFormMessage").html("Вы действительно хотите удалить "
                    + content.content.title + "?");
            $("#confirmationForm").modal('show');
            $("#confirmBtn").unbind();
            $("#confirmBtn").bind("click", function() {
                $.ajax({
                    type: "POST",
                    url: "api/" + _content + "/remove",
                    data: ({id: id}),
                    success: function(data) {
                        $("#confirmationForm").modal('hide');
                        if (_content == "course") {
                            location.hash = "course";
                            getCoursesPage();
                        } else {
                            location.hash = "task";
                            getTasksPage();
                        }
                    },
                    error: function(data) {
                        $("#errorBlock").addClass("alert-danger");
                        $("#errorMessage").html(data.responseText);
                        $("#errorBlock").css("display", "block");
                        return false;
                    }
                });
            });
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function approveContent(_content, id) {
    $.ajax({
        type: "POST",
        url: "api/" + _content + "/approve",
        data: ({id: id}),
        success: function(data) {
            if (_content == "course") {
                location.hash = "course";
                getCoursesPage();
            } else {
                location.hash = "task";
                getTasksPage();
            }
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}

function disApproveContent(_content, id) {
    $.ajax({
        type: "POST",
        url: "api/" + _content + "/disapprove",
        data: ({id: id}),
        success: function(data) {
            if (_content == "course") {
                location.hash = "course";
                getCoursesPage();
            } else {
                location.hash = "task";
                getTasksPage();
            }
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            return false;
        }
    });
    return false;
}
