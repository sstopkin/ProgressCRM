function getАnnouncementsPage() {
    var permissions;
    $("#addannouncements").css("display", "none");
    $.get("announcements.html", function(data) {
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
                    url: "api/announcements/getallannouncements",
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
                                str += "<button type=\"button\" onclick=\"editHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
                                str += "<button type=\"button\" onclick=\"deleteHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
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


                        $("#mainAnnouncementsContainer").html(str);
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

function addAnnouncements() {
    $('#announcementsAddMoadl').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/announcements/addannouncements",
        data: ({
            street: $('#announcementsStreet').val(),
            rooms: $('#announcementsRooms').val(),
            floor: $('#announcementsFloor').val(),
            floors: $('#announcementsFloors').val(),
            phone: $('#announcementsPhone').val(),
            description: $('#announcementsDescription').val()
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
//
//function deleteHelpDeskRequestById(hdRequestId) {
//    console.log("deleteHelpDeskRequestById " + hdRequestId);
//    $.ajax({
//        type: "POST",
//        url: "api/news/deletenews",
//        data: ({id: hdRequestId}),
//        success: function(data) {
//            getapartamentsListPage();
//        },
//        error: function(data) {
//            $("#errorBlock").addClass("alert-danger");
//            $("#errorMessage").html(data.responseText);
//            $("#errorBlock").css("display", "block");
//            checkStatus();
//            return false;
//        }
//    });
//    return false;
//}