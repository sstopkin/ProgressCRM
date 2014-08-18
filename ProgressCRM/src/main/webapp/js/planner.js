function getPlannerPage() {
    $.get("planner.html", function(data) {
        $("#mainContainer").html(data);
    });
}

function addPlannerTaskDialog(objectUUID) {

    var some_html = "<label class=\"control-label\">ID объекта</label>";
    some_html += "<label class=\"control-label\">Тип задачи</label>";
    some_html += "<input id=\"plannerAddTaskModalTaskType\" type=\"text\" class=\"form-control\">";
    some_html += "<label class=\"control-label\">Класс задачи</label>";
    some_html += '<select id="plannerAddTaskModalTaskClass" class="form-control">';
    some_html += '<option>event-warning</option>';
    some_html += '<option>event-success</option>';
    some_html += '<option>event-info</option>';
    some_html += '<option>event-special</option>';
    some_html += '<option>event-inverse</option>';
    some_html += '</select>';

    some_html += "<label class=\"control-label\">Дата начала</label>";
    some_html += '<input id="plannerAddTaskModalStratDate" type="text" class="form-control ">';
    some_html += '<div class="bootstrap-timepicker">';
    some_html += '<input id="plannerAddTaskModalStratTime" type="text" class="form-control">';
    some_html += '</div>';

    some_html += "<label class=\"control-label\">Дата конца</label>";
    some_html += "<input id=\"plannerAddTaskModalEndDate\" type=\"text\" class=\"form-control\">";
    some_html += '<div class="bootstrap-timepicker">';
    some_html += '<input id="plannerAddTaskModalEndTime" type="text" class="form-control">';
    some_html += '</div>';

    some_html += "<label class=\"control-label\">Заголовок</label>";
    some_html += "<input id=\"plannerAddTaskModalTaskTitle\" type=\"text\" class=\"form-control\">";
    some_html += "<label class=\"control-label\">Описание</label>";
    some_html += "<textarea id=\"plannerAddTaskModalDescription\" class=\"form-control\"></textarea>";

    var box = bootbox.dialog({
        show: false,
        title: "<h4 class=\"modal-title\">Добавить звонок</h4></div>",
        message: some_html,
        buttons: {
            success: {
                label: "Добавить звонок",
                className: "btn-success",
                callback: function() {
                    $.ajax({
                        type: "POST",
                        url: "api/planner/addtask",
                        data: ({
                            targetobjectuuid: "",
                            tasktype: $('#plannerAddTaskModalTaskType').val(),
                            taskclass: $('#plannerAddTaskModalTaskClass').val(),
                            title: $('#plannerAddTaskModalTaskTitle').val(),
                            description: $('#plannerAddTaskModalDescription').val(),
                            startdate: $('#plannerAddTaskModalStratDate').val() + ' ' + $('#plannerAddTaskModalStratTime').val(),
                            enddate: $('#plannerAddTaskModalEndDate').val() + ' ' + $('#plannerAddTaskModalEndTime').val()
                        }),
                        success: function() {
                            $("#errorBlock").css("display", "none");
                            location.reload();//FIXME
                        },
                        error: function(data) {
                            showDanger(data.responseText);
                        }
                    });
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

    box.on("shown.bs.modal", function() {
        var date = new Date();
        var day = date.getDate();
        day = (parseInt(day, 10) < 10) ? ('0' + day) : (day);
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        $('#plannerAddTaskModalStratDate').datepicker({
            format: "yyyy-mm-dd",
            todayBtn: "linked",
            language: "ru",
            autoclose: true,
            todayHighlight: true
        });
        $('#plannerAddTaskModalStratDate').val(year + "-" + month + "-" + day);
        $('#plannerAddTaskModalEndDate').datepicker({
            format: "yyyy-mm-dd",
            todayBtn: "linked",
            language: "ru",
            autoclose: true,
            todayHighlight: true
        });
        $('#plannerAddTaskModalEndDate').val(year + "-" + month + "-" + day);
        $('#plannerAddTaskModalStratTime').timepicker({
            minuteStep: 5,
            showInputs: false,
            showMeridian: false
        });
        $('#plannerAddTaskModalEndTime').timepicker({
            minuteStep: 5,
            showInputs: false,
            showMeridian: false
        });
    });
    box.modal('show');
}

function plannerGetWorkersTasks() {
//    var permissions = $.ajax({
//        type: "GET",
//        url: "api/auth/validate",
//        async: false
//    }).responseText;
//    $.ajax({
//        type: "GET",
//        url: "api/planner",
//        success: function(data) {
//            $("#errorBlock").css("display", "none");
//            var array = JSON.parse(data);
//            var str = "Planner";
//            array.forEach(function(entry) {
//                str += "<div class = \"media\">";
//                str += "<a class = \"pull-left\" href = \"#\">";
//                str += "<img class=\"media-object\" src=\"images/IT-Icon.png\" alt=\"...\">";
//                str += "</a>";
//                str += "<div class=\"media-body\">";
//                str += "<h6 class=\"media-heading\">";
//                str += entry.creationDate;
//                str += "</h4>";
//                str += "<h4 class=\"media-heading\">";
//                str += entry.taskDescription;
//                str += "</h4>";
//                str += "text";
//                if (permissions == "3") {
//                    str += "<div class=\"btn-toolbar\">";
//                    str += "<div class=\"btn-group\">";
//                    str += "<button type=\"button\" onclick=\"editHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
//                    str += "<button type=\"button\" onclick=\"deleteHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
//                    str += "</div>";
//                    str += "</div>";
//                }
//                for (var i = 0; i < workersList.length; ++i) {
//                    var a = workersList[i];
//                    if (entry.idWorker == a[0]) {
//                        str += "<td>" + a[1] + " " + a[3] + "</td>";
//                    }
//                }
//                str += "<a href=\"#\" onclick=\"return alert(\'" + entry.id + " \')\">ссылка</a>";
//                str += "</div>";
//                str += "</div>";
//            });
//            $("#profilePlannerTasksList").html(str);
//        },
//        error: function(data) {
//            showDanger(data.responseText);
//            return false;
//        }
//    });
}