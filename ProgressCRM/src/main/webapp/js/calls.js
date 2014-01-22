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
            location.reload();//FIXME
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}
