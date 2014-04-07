function addCallDialog(objectType, objectId) {
    var some_html = "<label class=\"control-label\">ID объекта</label>";
    some_html += "<input id=\"apartamentsAddCallObjectId\" value=\"" + objectId + "\" type=\"text\" class=\"form-control\" disabled>";
    some_html += "<label class=\"control-label\">Входящий номер</label>";
    some_html += "<input id=\"apartamentsAddCallIncomingPhoneNumber\" type=\"text\" class=\"form-control\">";
    some_html += "<label class=\"control-label\">Описание</label>";
    some_html += "<textarea id=\"apartamentsAddCallDescription\" class=\"form-control\"></textarea>";
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
                        url: "api/calls/addcall",
                        data: ({
                            id: objectId,
                            incomingPhoneNumber: $("#apartamentsAddCallIncomingPhoneNumber").val(),
                            description: $('#apartamentsAddCallDescription').val(),
                            type: objectType
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

