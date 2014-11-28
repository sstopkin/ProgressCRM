function addCallDialog(objectUUID) {
    var some_html = "<label class=\"control-label\">ID объекта</label>";
    some_html += "<input id=\"apartamentsAddCallObjectId\" value=\"" + objectUUID + "\" type=\"text\" class=\"form-control\" disabled>";
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
                callback: function () {
                    $.ajax({
                        type: "POST",
                        url: "api/calls/addcall",
                        data: ({
                            objectUUID: objectUUID,
                            incomingPhoneNumber: $("#apartamentsAddCallIncomingPhoneNumber").val(),
                            description: $('#apartamentsAddCallDescription').val()
                        }),
                        success: function () {
                            $("#errorBlock").css("display", "none");
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
}

function addCommentDialog(objectUUID) {
    var some_html = "<label class=\"control-label\">ID объекта</label>";
    some_html += "<input id=\"apartamentsAddCommentObjectId\" value=\"" + objectUUID + "\" type=\"text\" class=\"form-control\" disabled>";
    some_html += "<label class=\"control-label\">Описание</label>";
    some_html += "<textarea id=\"apartamentsAddCommentText\" class=\"form-control\"></textarea>";
    bootbox.dialog({
        title: "<h4 class=\"modal-title\">Добавить комментарий</h4></div>",
        message: some_html,
        buttons: {
            success: {
                label: "Добавить комментарий",
                className: "btn-success",
                callback: function () {
                    $.ajax({
                        type: "POST",
                        url: "api/comments/addcomment",
                        data: ({
                            objectUUID: objectUUID,
                            text: $('#apartamentsAddCommentText').val()
                        }),
                        success: function () {
                            $("#errorBlock").css("display", "none");
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
}