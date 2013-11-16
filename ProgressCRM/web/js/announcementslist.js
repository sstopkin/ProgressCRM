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
        data: ({id: announcementsId}),
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
    var permissions = $.ajax({
        type: "GET",
        url: "api/auth/validate",
        async: false
    }).responseText;
    var array = JSON.parse(data);
    var str = "<table class=\"table table-bordered\">";
    str += "<thead>";
    str += "<tr>";
    str += "<th>#</th>";
    str += "<th>Улица</th>";
    str += "<th>Номер дома</th>";
    str += "<th>Кол-во комнат</th>";
    str += "<th>Этаж</th>";
    str += "<th>Описание</th>";
    str += "<th>Автор</th>";
    str += "<th>Дата</th>";
    if (permissions == "3") {
        str += "<th>Редактировать</th>";
        str += "<th>Удалить</th>";
    }
    str += "</tr>";
    str += "</thead>";
    str += "<tbody>";
    array.forEach(function(entry) {
        str += "<tr>";
        str += "<td><a href=\"#\" onclick=\"return getAnnouncementsViewPage(" + entry.id + ")\">" + entry.id + "</a></td>";
        str += "<td>" + entry.street + "</td>";
        str += "<td>" + entry.houseNumber + "</td>";
        str += "<td>" + entry.rooms + "</td>";
        str += "<td>" + entry.floor + " / " + entry.floors + "</td>";
        str += "<td>" + entry.description + "</td>";
        for (var i = 0; i < workersList.length; ++i) {
            var a = workersList[i];
            if (entry.idWorker == a[0]) {
                str += "<td>" + a[1] + " " + a[3] + "</td>";
            }
        }
        str += "<td>" + entry.creationDate + "</td>";

        if (permissions == "3") {
            str += "<td>" + "<button type=\"button\" onclick=\"editAnnouncementsById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>" + "</td>";
            str += "<td>" + "<button type=\"button\" onclick=\"deleteAnnouncementsById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>" + "</td>";
        }
        str += "</tr>";
    });
    str += "</tbody>";
    $("#mainAnnouncementsContainer").html(str);
}