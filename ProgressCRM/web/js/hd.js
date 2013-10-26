function getHelpDeskPage() {
    var permissions;
    $("#addApartaments").css("display", "none");
    $.get("hd.html", function(data) {
        $("#mainContainer").html(data);
        $.ajax({
            type: "GET",
            url: "api/auth",
            success: function(data) {
                $("#profileLink").html(data);
                $("#logged").css("display", "block");
                $.get("api/auth/validate", function(data3) {
                    permissions = data3;
                });
                $.ajax({
                    type: "GET",
                    url: "api/helpdesk/getallrequest",
                    success: function(data) {
                        $("#errorBlock").css("display", "none");
                        var array = JSON.parse(data);
                        var str = "";
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
                            str += entry.request;
                            str += "</h4>";
                            if (permissions == "3") {
                                str += "<div class=\"btn-toolbar\">";
                                str += "<div class=\"btn-group\">";
                                str += "<button type=\"button\" onclick=\"deleteHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
                                str += "<button type=\"button\" onclick=\"editHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
                                str += "</div>";
                                str += "</div>";
                            }
                            str += "<a href=\"#\" onclick=\"return alert(\'" + entry.id + " \')\">ссылка</a>";
                            str += "</div>";
                            str += "</div>";
                        });
//                console.log(array.apartaments.IsApproved);
//                console.log(array.apartaments.deleted);
//
//
//                content += "<p>";
//                content += "ID = " + array.apartaments.id;
//                content += "</p>";


                        $("#mainHelpDeskContainer").html(str);
                    },
                    error: function(data) {
                        showDanger(data.responseText);
                        return false;
                    }
                });
            },
            error: function(data) {
                $("#loginForm").css("display", "block");
            }
        });

    });
}

function addHelpDeskRequest() {
//    alert($('#hdRequest').val());
//    alert($('#hdText').val());
    $('#myModal').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/helpdesk/addrequest",
        data: ({
            request: $('#hdRequest').val(),
            text: $('#hdText').val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            getHelpDeskPage();
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}

function deleteHelpDeskRequestById(hdRequestId) {
    console.log("deleteHelpDeskRequestById " + hdRequestId);
    $.ajax({
        type: "POST",
        url: "api/helpdesk/deleterequest",
        data: ({id: hdRequestId}),
        success: function(data) {
            getapartamentsListPage();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            checkStatus();
            return false;
        }
    });
    return false;
}