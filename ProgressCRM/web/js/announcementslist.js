function getАnnouncementsPage() {
    $("#addannouncements").css("display", "none");
    $.get("announcementslist.html", function(data) {
        $("#mainContainer").html(data);
        var date = new Date();
        var day = date.getDay();
        day = (parseInt(day, 10) < 10) ? ('0' + day) : (day);
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        $('#announcementsSearchStartDate').val(day + "-" + month + "-" + year);
        $('#announcementsSearchStartDate').datepicker({
            format: 'dd-mm-yyyy'
        });
        $('#announcementsSearchEndDate').val(day + "-" + month + "-" + year);
        $('#announcementsSearchEndDate').datepicker({
            format: 'dd-mm-yyyy'
        });
        $("#announcementsSearchAuthor").append('<option value="">Все</option>');
        workersList.forEach(function(entry) {
            $("#announcementsSearchAuthor").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
        });
        $.ajax({
            type: "GET",
            url: "api/auth",
            success: function(data) {
                $("#profileLink").html(data);
                $("#logged").css("display", "block");
                $.ajax({
                    type: "GET",
                    url: "api/announcements/getallannouncements",
                    success: function(data) {
                        $("#errorBlock").css("display", "none");
                        writeToDivAnnouncementsList(data);
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
            houseNumber: $('#announcementsHouseNumber').val(),
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

function deleteAnnouncementsById(announcementsId) {
    console.log("deleteAnnouncementsById " + announcementsId);
    $.ajax({
        type: "POST",
        url: "api/announcements/deleteannouncements",
        data: ({id: hdRequestId}),
        success: function(data) {
            getАnnouncementsPage();
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

function searchAnnouncements() {
    $.ajax({
        type: "GET",
        url: "api/announcements/search?" +
                "street=" + $('#announcementsSearchStreet').val() +
                "&housenumber=" + $('#announcementsSearchHouseNumber').val() +
                "&rooms=" + $("#announcementsSearchRooms").val() +
                "&floor=" + $('#announcementsSearchFloor').val() +
                "&floors=" + $('#announcementsSearchFloors').val() +
                "&idworker=" + $('#announcementsSearchAuthor').val() +
                "&startdate=" + $('#announcementsSearchStartDate').val() +
                "&enddate=" + $('#announcementsSearchEndDate').val(),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            var array = JSON.parse(data);
            writeToDivAnnouncementsList(data);
        },
        error: function(data) {
            showDanger(data.responseText);
            return false;
        }
    });
}

function writeToDivAnnouncementsList(data) {
    var permissions;
    $.get("api/auth/validate", function(data3) {
        permissions = data3;
    });
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
        str += entry.id;
        str += "</h4>";
        if (permissions == "3") {
            str += "<div class=\"btn-toolbar\">";
            str += "<div class=\"btn-group\">";
            str += "<button type=\"button\" onclick=\"editHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
            str += "<button type=\"button\" onclick=\"deleteHelpDeskRequestById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
            str += "</div>";
            str += "</div>";
        }
        str += " street: " + entry.street;
        str += " houseNumber: " + entry.houseNumber;
        str += " rooms:" + entry.rooms;
        str += " floor/floors:" + entry.floor + "/" + entry.floors;
        str += " description: " + entry.description;
        for (var i = 0; i < workersList.length; ++i) {
            var a = workersList[i];
            if (entry.idWorker == a[0]) {
                str += " author: " + a[1] + " " + a[2] + " " + a[3];
            }
        }
        str += "<a href=\"#\" onclick=\"return getAnnouncementsViewPage(" + entry.id + ")\">ссылка</a>";
        str += "</div>";
        str += "</div>";
    });
    $("#mainAnnouncementsContainer").html(str);
}