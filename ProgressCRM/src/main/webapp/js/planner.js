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
            var date = new Date();
            var day = date.getDate();
            day = (parseInt(day, 10) < 10) ? ('0' + day) : (day);
            var month = date.getMonth() + 1;
            month = (parseInt(month, 10) < 10) ? ('0' + month) : (month);
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
            $('#plannerAddTaskModalTaskObjectId').val(objectUUID);
        });
        box.modal('show');
    }, 'html');
}