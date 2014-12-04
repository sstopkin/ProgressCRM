function addPlannerTaskDialog(objectUUID) {
    $.get("/templates/modal_planner.html", function (some_html) {
        var box = bootbox.dialog({
            show: false,
            title: "<h4 class=\"modal-title\">Добавить задачу</h4></div>",
            message: some_html,
            buttons: {
                success: {
                    label: "Добавить звонок",
                    className: "btn-success",
                    callback: function () {
                        $.ajax({
                            type: "POST",
                            url: "api/planner/addtask",
                            data: ({
                                targetobjectuuid: $('#plannerAddTaskModalTaskObjectId').val(),
                                tasktype: $('#plannerAddTaskModalTaskType').val(),
                                taskclass: $('#plannerAddTaskModalTaskClass').val(),
                                title: $('#plannerAddTaskModalTaskTitle').val(),
                                description: $('#plannerAddTaskModalDescription').val(),
                                startdate: getTimeStamp($('#plannerAddTaskModalStratDate').val() + ' ' + $('#plannerAddTaskModalStratTime').val()),
                                enddate: getTimeStamp($('#plannerAddTaskModalEndDate').val() + ' ' + $('#plannerAddTaskModalEndTime').val())
                            }),
                            success: function () {
                                $("#errorBlock").css("display", "none");
                                location.reload();//FIXME
                            },
                            error: function (data) {
                                showDanger(data.responseText);
                            }
                        });
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

        box.on("shown.bs.modal", function () {
            $('#plannerAddTaskModalStratDate').datepicker({
                format: "yyyy-mm-dd",
                todayBtn: "linked",
                language: "ru",
                autoclose: true,
                todayHighlight: true
            });
            $('#plannerAddTaskModalStratDate').val(timeConverter(new Date().getTime(), true));
            $('#plannerAddTaskModalEndDate').datepicker({
                format: "yyyy-mm-dd",
                todayBtn: "linked",
                language: "ru",
                autoclose: true,
                todayHighlight: true
            });
            $('#plannerAddTaskModalEndDate').val(timeConverter(new Date().getTime(), true));
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
            $('#plannerAddTaskModalTaskObjectId').val(objectUUID);
        });
        box.modal('show');
    }, 'html');
}

function deletePlannerTaskById(customersId) {
    $.ajax({
        type: "POST",
        url: "api/planner/remove",
        data: ({id: customersId}),
        success: function (data) {
//            document.location.href = "#profile/list/current";
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