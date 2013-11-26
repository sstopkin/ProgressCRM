function submitCall() {
    $.ajax({
        type: "POST",
        url: "api/calls/addcall",
        data: ({
            id: $("#submitCallId").val(),
            description: $('#submitCallDescription').val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            $('#addCourseBtn').css('display', 'block');
            location.reload();//FIXME
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}
