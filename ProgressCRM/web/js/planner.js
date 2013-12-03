function showPlannerAddTaskModal(taskId, typeId) {
    var date = new Date();
    var day = date.getDate();
    day = (parseInt(day, 10) < 10) ? ('0' + day) : (day);
    var month = date.getMonth() + 1;
    var year = date.getFullYear();
    $('#plannerAddTaskModalDate').datepicker({
        format: 'yyyy-mm-dd'
    });
    $('#plannerAddTaskModalDate').val(year + "-" + month + "-" + day);
    $("#plannerAddTaskTaskId").val(taskId);
    $("#plannerAddTaskTypeId").val(typeId);
    $('#plannerAddTaskModal').modal('show');
}

function addPlannerTask() {
    $('#announcementsAddMoadl').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/planner/addtask",
        data: ({
            tasktype: $('#plannerAddTaskTypeId').val(),
            taskid: $('#plannerAddTaskTaskId').val(),
            description: $('#plannerAddTaskModalDescription').val(),
            taskdate: $('#plannerAddTaskModalDate').val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            getАnnouncementsPage();
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}