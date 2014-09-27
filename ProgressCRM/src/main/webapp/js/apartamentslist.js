function getApartamentsListPage(status, statusText) {
    $.get("apartamentslist.html", function(data) {
        var permissions = $.ajax({
            type: "GET",
            url: "api/auth/validate",
            async: false
        }).responseText;
        if ((permissions === "3") || (permissions === "2")) {
            $("#addApartamentBtn").css("display", "block");
            $("#genApartamentsPriceBtn").css("display", "block");
        } else {
            $("#addApartamentBtn").css("display", "none");
            $("#genApartamentsPriceBtn").css("display", "none");
        }
        $("#mainContainer").html("<div id=\"mainSearchContainer\" class=\"container\"></div>" + data);
//        initSearchForm('apartaments');
        $("#apartamentsListHeaderText").html(statusText);
        $("#genApartamentsPriceBtn").click(function() {
            window.location = '/api/report/getprice?status=' + status
        });
        $.ajax({
            type: "GET",
            url: "api/apartament/getallapartament?status=" + status,
            success: function(data) {
                drawApartamentsListTable(data);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function drawApartamentsListTable(data) {
    $("#errorBlock").css("display", "none");
    var array = JSON.parse(data);
    var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="apartamentsListTable">';
    str += "<thead>";
    str += "<tr>";
    str += "<th>#</th>";
    str += '<th>Адрес</th>';
    str += '<th>Комнат</th>';
    str += "<th>Площадь О/К/Ж</th>";
    str += '<th>Этаж</th>';
    str += '<th>Цена</th>';
    str += "<th>Добавлено</th>";
    str += "<th>Риэлтор</th>";
    str += "<th>Дата</th>";
    str += "<th>Звонок</th>";
    str += "<th>Коментарий</th>";
    str += "</tr>";
    str += "</thead>";
    str += "<tbody>";
    str += draw(array);
    str += "</tbody>";
    $("#divApartamentsList").html(str);
    $('#apartamentsListTable').dataTable();
}

function draw(array) {
    var str = "";
    array.forEach(function(entry) {
        str += "<tr>";
        str += "<td><a href=\"#apartaments/view/" + entry.id + "\" class=\"btn btn-primary\"><b>" + entry.id + "</b></a></td>";
        str += "<td>" + entry.cityName + " "
                + entry.streetName + " "
                + entry.houseNumber + " "
                + entry.buildingNumber + "</td>";
        str += "<td>" + entry.rooms + "</td>";
        str += "<td>" + entry.sizeApartament + " / " + entry.sizeKitchen + " / " + entry.sizeLiving + "</td>";
        str += "<td>" + entry.floor + " / " + entry.floors + "</td>";
        str += "<td>" + entry.price + "</td>";
        str += "<td>" + getWorkersFullNameById(entry.idWorker) + "</td>";
        str += "<td>" + getWorkersFullNameById(entry.idWorkerTarget) + "</td>";
        str += "<td>" + timeConverter(entry.сreationDate) + "</td>";
        str += "<td>" + "<button type=\"button\" onclick=\"addCallDialog('" + entry.ApartamentUUID + "');\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-earphone\"></span></button>" + "</td>";
        str += "<td>" + "<button type=\"button\" onclick=\"addCommentDialog('" + entry.ApartamentUUID + "');\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-comment\"></span></button>" + "</td>";
        str += "</tr>";
    });
    return str;
}

function apartamentsDeleteById(apartamentsId) {
    console.log("apartamentsDeleteById " + apartamentsId);
    $.ajax({
        type: "POST",
        url: "api/apartament/remove",
        data: ({id: apartamentsId}),
        success: function() {
            //FIXME! /list/all
            document.location.href = "#apartaments/list/price";
        },
        error: function(data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

function apartamentsEditById(apartamentId) {
    $.get("apartamentsadd.html", function(data) {
        $("#mainContainer").html(data);
        mapSet();
        $.ajax({
            type: "GET",
            url: "api/apartament/getapartament?id=" + apartamentId,
            success: function(data) {
                var array = JSON.parse(data);
                workersList.forEach(function(entry) {
                    $("#ApartamentsIdWorkerTarget").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
                });
                $("#ApartamentsIdWorkerTarget").val(array.idWorkerTarget);

                $("#errorBlock").css("display", "none");

                $('#TypeOfSales').val(array.typeOfSales);

                $('#apartamentCity').text(array.cityName);
                $('#apartamentStreet').text(array.streetName);
                $('#apartamentBuilding').text(array.houseNumber);
                $('#apartamentBuildingAdd').text(array.buildingNumber);

                $('#Rooms').val(array.rooms);
                $('#address').text(array.shortAddress);
                $("#apartamentLan").text(array.apartamentLan);
                $("#apartamentLon").text(array.apartamentLon);
                $('#Price').val(array.price);
                $('#CityDistrict').val(array.cityDistrict);
                $('#Floor').val(array.floor);
                $('#Floors').val(array.floors);
                $('#RoomNumber').val(array.roomNumber);
                $('#Material').val(array.material);
                $('#SizeApartament').val(array.sizeApartament);
                $('#SizeLiving').val(array.sizeLiving);
                $('#SizeKitchen').val(array.sizeKitchen);
                $('#Balcony').val(array.balcony);
                $('#Loggia').val(array.loggia);
                $('#YearOfConstruction').val(array.yearOfConstruction);
                $('#Description').val(array.description);
                $('#IdCustomer').val(array.idCustomer);
                $('#idWorkerTarget').val(array.idWorkerTarget);
                $('#DwellingType').val(array.dwellingType);
                $('#ApartamentStatus').val(array.status);

                $('#PureSale').prop("checked", array.MethodOfPurchase_PureSale);
                $('#Mortgage').prop("checked", array.MethodOfPurchase_Mortgage);
                $('#Exchange').prop("checked", array.MethodOfPurchase_Exchange);
                $('#Rent').prop("checked", array.MethodOfPurchase_Rent);
                $('#RePlanning').prop("checked", array.rePplanning);

                $("#apartamentEditReadyLink").css("display", "block");
                $("#apartamentEditReadyLink").click(function() {
                    $.ajax({
                        type: "POST",
                        url: "api/apartament/editapartament",
                        data: ({
                            id: apartamentId,
                            typeofsales: $('#TypeOfSales').val(),
                            rooms: $('#Rooms').val(),
                            dwellingType: $('#DwellingType').val(),
                            //FIXME!!
                            price: $('#Price').val(),
                            citydistrict: $('#CityDistrict').val(),
                            floor: $('#Floor').val(),
                            floors: $('#Floors').val(),
                            roomnumber: $('#RoomNumber').val(),
                            material: $('#Material').val(),
                            sizeapartament: $('#SizeApartament').val(),
                            sizeliving: $('#SizeLiving').val(),
                            sizekitchen: $('#SizeKitchen').val(),
                            balcony: $('#Balcony').val(),
                            loggia: $('#Loggia').val(),
                            yearofconstruction: $('#YearOfConstruction').val(),
                            description: $('#Description').val(),
                            puresale: $('#PureSale').prop("checked"),
                            mortgage: $('#Mortgage').prop("checked"),
                            exchange: $('#Exchange').prop("checked"),
                            rent: $('#Rent').prop("checked"),
                            replanning: $('#RePlanning').prop("checked"),
                            idCustomer: $("#IdCustomer").val(),
                            idWorkerTarget: $("#ApartamentsIdWorkerTarget").val(),
                            status: $("#ApartamentStatus").val()
                        }),
                        success: function(data) {
                            document.location.href = "#apartaments/view/" + apartamentId;
                            $("#errorBlock").css("display", "none");
                        },
                        error: function(data) {
                            showDanger(data.responseText);
                        }
                    });
                });
            }
        });
    });
}