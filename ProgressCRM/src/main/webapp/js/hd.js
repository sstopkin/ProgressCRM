function getHelpDeskPage() {
    $.get("hd.html", function(data) {
        $("#mainContainer").html(data);
        var permissions = $.ajax({
            type: "GET",
            url: "api/auth/validate",
            async: false
        }).responseText;
        $.ajax({
            type: "GET",
            url: "api/helpdesk/getallrequest",
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "";
                array.forEach(function(entry) {
                    str += "<div class=\"panel panel-info\">";
                    str += "<div class=\"panel-heading\">Заявка #" + entry.id + "</div>";
                    str += "<div class=\"panel-body\">";

                    str += "<div class = \"media\">";
                    str += "<a class = \"pull-left\" href = \"#\">";
                    str += "<img class=\"media-object\" src=\"images/apple2.png\" alt=\"...\">";
                    str += "</a>";
                    str += "<div class=\"media-body\">";
                    str += "<h4 class=\"media-heading\">";
                    str += "<b>";
                    if (permissions === "3") {
                        str += "<button type=\"button\" onclick=\"confirmActionDelete('deleteHelpDeskRequestById(" + entry.id + ")');\" class=\"btn btn-danger pull-right\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
                        str += "<button type=\"button\" onclick=\"editHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-warning pull-right\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
                    }
                    str += entry.request;
                    str += "</b>";
                    str += "</h4>";
                    str += "<h5 class=\"media-heading\">";
                    str += timeConverter(entry.creationDate,'human');
                    str += "</h5>";
                    str += entry.text;
                    str += "<p><i>" + getWorkersFullNameById(entry.workerId) + "</i></p>";
//                    str += "<a href=\"#\" onclick=\"return alert(\'" + entry.id + " \')\">ссылка</a>";
                    str += "</div>";
                    str += "</div>";
                    str += "</div>";
                    str += "</div>";
                });
                $("#mainHelpDeskContainer").html(str);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function addHelpDeskRequest() {
    $('#myModal').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/helpdesk/addrequest",
        data: ({
            request: $('#hdRequest').val(),
            text: $('#hdText').val()
        }),
        success: function(data) {
            location.reload();
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}

function deleteHelpDeskRequestById(hdRequestId) {
    $.ajax({
        type: "POST",
        url: "api/helpdesk/deleterequest",
        data: ({id: hdRequestId}),
        success: function(data) {
            location.reload();
        },
        error: function(data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}