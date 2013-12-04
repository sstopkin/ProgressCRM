function getCustomersRentPage() {
    $("#addcustomersRent").css("display", "none");
    $.get("customersrentlist.html", function(data) {
        $("#mainContainer").html(data);
        var date = new Date();
        var day = date.getDate();
        day = (parseInt(day, 10) < 10) ? ('0' + day) : (day);
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
        $('#customersRentSearchStartDate').datepicker({
            format: 'yyyy-mm-dd'
        });
        $('#customersRentSearchEndDate').datepicker({
            format: 'yyyy-mm-dd'
        });
        $("#customersRentSearchAuthor").append('<option value="">Все</option>');
        workersList.forEach(function(entry) {
            $("#customersRentSearchAuthor").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
        });

        $('#customersRentSearchStreet').kladr({
            token: KLADR_token,
            key: KLADR_key,
            type: $.ui.kladrObjectType.STREET,
            parentType: $.ui.kladrObjectType.CITY,
            parentId: KLADR_parentId
        });
        
        $('#customersRentStreet').kladr({
            token: KLADR_token,
            key: KLADR_key,
            type: $.ui.kladrObjectType.STREET,
            parentType: $.ui.kladrObjectType.CITY,
            parentId: KLADR_parentId
        });

        $.ajax({
            type: "GET",
            url: "api/auth",
            success: function(data) {
                $("#profileLink").html(data);
                $("#logged").css("display", "block");
                $.ajax({
                    type: "GET",
                    url: "api/customersrent/getallcustomersrent",
                    success: function(data) {
                        $("#errorBlock").css("display", "none");
                        writeToDivCustomersRentList(data);
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

function addCustomersRent() {
    $('#customersRentAddMoadl').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/customersrent/addcustomersrent",
        data: ({
            street: $('#customersRentStreet').val(),
            houseNumber: $('#customersRentHouseNumber').val(),
            rooms: $('#customersRentRooms').val(),
            floor: $('#customersRentFloor').val(),
            floors: $('#customersRentFloors').val(),
            price: $('#customersRentPrice').val(),
            phone: $('#customersRentPhone').val(),
            description: $('#customersRentDescription').val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            document.location.href = "#customersrent";
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}

function deleteCustomersRentById(customersRentId) {
    console.log("deleteCustomersRentById " + customersRentId);
    $.ajax({
        type: "POST",
        url: "api/customersrent/deletecustomersrent",
        data: ({id: customersRentId}),
        success: function(data) {
            document.location.href = "#customersrent";
        },
        error: function(data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

function searchCustomersRent() {
    $.ajax({
        type: "GET",
        url: "api/customersrent/search?" +
                "street=" + $('#customersRentSearchStreet').val() +
                "&housenumber=" + $('#customersRentSearchHouseNumber').val() +
                "&rooms=" + $("#customersRentSearchRooms").val() +
                "&floor=" + $('#customersRentSearchFloor').val() +
                "&floors=" + $('#customersRentSearchFloors').val() +
                "&idworker=" + $('#customersRentSearchAuthor').val() +
                "&startdate=" + $('#customersRentSearchStartDate').val() +
                "&enddate=" + $('#customersRentSearchEndDate').val(),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            var array = JSON.parse(data);
            writeToDivCustomersRentList(data);
        },
        error: function(data) {
            showDanger(data.responseText);
            return false;
        }
    });
}

function writeToDivCustomersRentList(data) {
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
        str += "<td><a href=\"#customersrent/view/"+entry.id+"\"\">" + entry.id + "</a></td>";
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
            str += "<td>" + "<button type=\"button\" onclick=\"editCustomersRentById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>" + "</td>";
            str += "<td>" + "<button type=\"button\" onclick=\"deleteCustomersRentById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>" + "</td>";
        }
        str += "</tr>";
    });
    str += "</tbody>";
    $("#mainCustomersRentContainer").html(str);
}