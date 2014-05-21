function addPlannerTaskDialog(objectUUID) {
    var date = new Date();
    var day = date.getDate();
    day = (parseInt(day, 10) < 10) ? ('0' + day) : (day);
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    $('#plannerAddTaskModalDate').datepicker({
        format: 'yyyy-mm-dd'
    });
    $('#plannerAddTaskModalDate').val(year + "-" + month + "-" + day);

    var some_html = "<label class=\"control-label\">ID объекта</label>";
    some_html += "<input id=\"apartamentsAddCallObjectId\" value=\"" + objectUUID + "\" type=\"text\" class=\"form-control\" disabled>";
    some_html += "<label class=\"control-label\">Дата</label>";
    some_html += "<input id=\"plannerAddTaskModalDate\" type=\"text\" class=\"form-control\">";
    some_html += "<label class=\"control-label\">Описание</label>";
    some_html += "<textarea id=\"plannerAddTaskModalDescription\" class=\"form-control\"></textarea>";

    bootbox.dialog({
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
                            objectUUID: objectUUID,
                            incomingPhoneNumber: $("#apartamentsAddCallIncomingPhoneNumber").val(),
                            description: $('#apartamentsAddCallDescription').val()
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
}

function plannerGetWorkersTasks() {
    var permissions = $.ajax({
        type: "GET",
        url: "api/auth/validate",
        async: false
    }).responseText;
    $.ajax({
        type: "GET",
        url: "api/planner",
        success: function(data) {
            $("#errorBlock").css("display", "none");
            var array = JSON.parse(data);
            var str = "Planner";
            array.forEach(function(entry) {
                str += "<div class = \"media\">";
                str += "<a class = \"pull-left\" href = \"#\">";
                str += "<img class=\"media-object\" src=\"images/IT-Icon.png\" alt=\"...\">";
                str += "</a>";
                str += "<div class=\"media-body\">";
                str += "<h6 class=\"media-heading\">";
                str += entry.creationDate;
                str += "</h4>";
                str += "<h4 class=\"media-heading\">";
                str += entry.taskDescription;
                str += "</h4>";
                str += "text";
                if (permissions == "3") {
                    str += "<div class=\"btn-toolbar\">";
                    str += "<div class=\"btn-group\">";
                    str += "<button type=\"button\" onclick=\"editHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
                    str += "<button type=\"button\" onclick=\"deleteHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
                    str += "</div>";
                    str += "</div>";
                }
                for (var i = 0; i < workersList.length; ++i) {
                    var a = workersList[i];
                    if (entry.idWorker == a[0]) {
                        str += "<td>" + a[1] + " " + a[3] + "</td>";
                    }
                }
                str += "<a href=\"#\" onclick=\"return alert(\'" + entry.id + " \')\">ссылка</a>";
                str += "</div>";
                str += "</div>";
            });
            $("#profilePlannerTasksList").html(str);
        },
        error: function(data) {
            showDanger(data.responseText);
            return false;
        }
    });
}