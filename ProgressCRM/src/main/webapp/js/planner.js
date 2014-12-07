function addPlannerTaskDialog(objectUUID) {
    $.get("/templates/modal_planner.html", function (some_html) {
        var box = bootbox.dialog({
            show: false,
            title: "<h4 class=\"modal-title\">Добавить задачу</h4></div>",
            message: some_html,
            buttons: {
                success: {
                    label: "Добавить задачу",
                    className: "btn-success",
                    callback: function () {
                        $.ajax({
                            type: "POST",
                            url: "api/planner/addtask",
                            data: ({
                                targetobjectuuid: $('#plannerAddTaskModalTaskObjectId').val(),
                                tasktype: $('#plannerAddTaskModalTaskType').val(),
                                taskcolor: $('select[name="plannerColorPickerList"]').val(),
                                title: $('#plannerAddTaskModalTaskTitle').val(),
                                description: $('#plannerAddTaskModalDescription').val(),
                                startdate: getTimeStamp($('#plannerAddTaskModalStratDate').val() + ' ' + $('#plannerAddTaskModalStratTime').val()),
                                enddate: getTimeStamp($('#plannerAddTaskModalEndDate').val() + ' ' + $('#plannerAddTaskModalEndTime').val())
                            }),
                            success: function () {
                                $("#errorBlock").css("display", "none");
                                location.reload(); //FIXME
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
            $('#plannerAddTaskModalStratDate').val(timeConverter(new Date().getTime(), 'short'));
            $('#plannerAddTaskModalEndDate').datepicker({
                format: "yyyy-mm-dd",
                todayBtn: "linked",
                language: "ru",
                autoclose: true,
                todayHighlight: true
            });
            $('#plannerAddTaskModalEndDate').val(timeConverter(new Date().getTime(), 'short'));
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
            $('select[name="plannerColorPickerList"]').simplecolorpicker({theme: 'glyphicons'});
        });
        box.modal('show');
    }, 'html');
}

function editPlannerTaskById(id) {
    var array;
    $.get("/templates/modal_planner.html", function (some_html) {
        var box = bootbox.dialog({
            show: false,
            title: "<h4 class=\"modal-title\">Редактировать задачу</h4></div>",
            message: some_html,
            buttons: {
                success: {
                    label: "Сохранить задачу",
                    className: "btn-success",
                    callback: function () {
                        $.ajax({
                            type: "POST",
                            url: "api/planner/edittask",
                            data: ({
                                id: array.id,
                                targetobjectuuid: $('#plannerAddTaskModalTaskObjectId').val(),
                                tasktype: $('#plannerAddTaskModalTaskType').val(),
                                taskcolor: $('select[name="plannerColorPickerList"]').val(),
                                title: $('#plannerAddTaskModalTaskTitle').val(),
                                description: $('#plannerAddTaskModalDescription').val(),
                                startdate: getTimeStamp($('#plannerAddTaskModalStratDate').val() + ' ' + $('#plannerAddTaskModalStratTime').val()),
                                enddate: getTimeStamp($('#plannerAddTaskModalEndDate').val() + ' ' + $('#plannerAddTaskModalEndTime').val())
                            }),
                            success: function () {
                                $("#errorBlock").css("display", "none");
                                location.reload(); //FIXME
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
            $.ajax({
                type: "GET",
                url: "api/planner/gettask?id=" + id,
                success: function (data) {
                    $("#errorBlock").css("display", "none");
                    array = JSON.parse(data);
                    $('#plannerAddTaskModalTaskTitle').val(array.title);
                    $('#plannerAddTaskModalDescription').val(array.description);

                    $('#plannerAddTaskModalStratDate').datepicker({
                        format: "yyyy-mm-dd",
                        todayBtn: "linked",
                        language: "ru",
                        autoclose: true,
                        todayHighlight: true
                    });
                    $('#plannerAddTaskModalStratDate').val(timeConverter());
                    $('#plannerAddTaskModalEndDate').datepicker({
                        format: "yyyy-mm-dd",
                        todayBtn: "linked",
                        language: "ru",
                        autoclose: true,
                        todayHighlight: true
                    });
                    $('#plannerAddTaskModalEndDate').val(timeConverter());
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
                    $('select[name="plannerColorPickerList"]').val(array.color);
                    $('select[name="plannerColorPickerList"]').simplecolorpicker({theme: 'glyphicons'});
                    $('#plannerAddTaskModalStratDate').val(array.start.slice(0, 10));
                    $('#plannerAddTaskModalStratTime').val(array.start.slice(11, 16));
                    $('#plannerAddTaskModalEndDate').val(array.end.slice(0, 10));
                    $('#plannerAddTaskModalEndTime').val(array.end.slice(11, 16));
                    $('#plannerAddTaskModalTaskObjectId').val(array.targetOjectUUID);

                },
                error: function (data) {
                    showDanger(data.responseText);
                    return false;
                }
            });
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

function initCalendar(eventsList, div) {
    $.get("/templates/calendar.html", function (data) {
        $(div).html(data);
        var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="plannerTaskListTable">';
        str += "<thead>";
        str += "<tr>";
        str += "<th>#</th>";
        str += "<th>Заголовок</th>";
        str += "<th>Описание</th>";
        str += "<th>Дата</th>";
        str += "<th>1</th>";
        str += "<th>2</th>";
        str += "</tr>";
        str += "</thead>";
        str += "<tbody>";
        eventsList.forEach(function (entry) {
            str += "<tr>";
            str += "<td><a href=\"#customers/view/" + entry.id + "\" class=\"btn btn-primary\"><b>" + entry.id + "</b></a></td>";
            str += "<td>" + entry.title + "</td>";
            str += "<td>" + entry.description + "</td>";
            str += "<td>" + entry.start + " " + entry.end + "</td>";
            str += "<td>" + "<button type=\"button\" onclick=\"confirmActionDelete('deletePlannerTaskById(" + entry.id + ")');\" class=\"btn btn-danger pull-right\"><span class=\"glyphicon glyphicon-remove\"></span></button>" + "</td>";
            str += "<td>" + "<button type=\"button\" onclick=\"editPlannerTaskById(" + entry.id + ");\" class=\"btn btn-warning pull-right\"><span class=\"glyphicon glyphicon-pencil\"></span></button>" + "</td>";
            str += "</tr>";
        });
        str += "</tbody>";
        $("#fullCalendarList").html(str);
        $('#plannerTaskListTable').dataTable();
        $('#fullCalendar').fullCalendar({
//            dayClick: function () {
//                alert('a day has been clicked!');
//                $('#fullCalendar').fullCalendar('next');
//            },
            eventClick: function (calEvent, jsEvent, view) {
                editPlannerTaskById(calEvent.id);
//                alert('Event: ' + calEvent.id);
//                alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
//                alert('View: ' + view.name);
                // change the border color just for fun
//                $(this).css('border-color', 'red');
            },
            header: {
                left: 'prevYear,prev,today,next,nextYear',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            firstDay: 1, //Monday
            fixedWeekCount: true,
            weekNumbers: 1,
            defaultDate: timeConverter(new Date().getTime()),
            businessHours: {
                start: '09:00', // a start time (10am in this example)
                end: '18:00', // an end time (6pm in this example)
                dow: [1, 2, 3, 4, 5]
                        // days of week. an array of zero-based day of week integers (0=Sunday)
                        // (Monday-Thursday in this example)
            },
            editable: false,
            lang: "ru",
            eventLimit: true, // allow "more" link when too many events
            events: eventsList
        });
    });
}