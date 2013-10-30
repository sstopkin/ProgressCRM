function getCustomersPage() {
    $.get("customers.html", function(data) {
//        $.get("api/auth/validate", function(data) {
//            if ((data == "3") || (data == "2")) {
//                $("#addApartamentBtn").css("display", "block");
//                $("#genApartamentsPriceBtn").css("display", "block");
//            } else {
//                $("#addApartamentBtn").css("display", "none");
//                $("#genApartamentsPriceBtn").css("display", "none");
//            }
//        });
        $("#mainContainer").html(data);
//        var permissions;
//        var userId;
//        $.get("api/auth/validate", function(data3) {
//            permissions = data3;
//            if (permissions == "3") {
//                $("#approvingTagsForTasks").css("display", "block");
//            }
//        });
//        $.get("api/auth/author", function(data2) {
//            userId = data2;
//        });
//        $.ajax({
//            type: "GET",
//            url: "api/apartament/getallapartament",
//            success: function(data) {
//                $("#errorBlock").css("display", "none");
//                var array = JSON.parse(data);
//                var str = "";
//                array.forEach(function(entry) {
//                    str += "<div class = \"media\">";
//                    if (permissions == "3") {
//                        str += "<div class=\"btn-toolbar\">";
//                        str += "<div class=\"btn-group\">";
//
//                        str += "<button type=\"button\" onclick=\"apartamentsDeleteById(" + entry.apartaments.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
//
//                        str += "</div>";
//                        str += "</div>";
//                    }
//                    str += "</div>";
//                    str += "<a class = \"pull-left\" href = \"#\">";
//                    str += "<img class=\"media-object\" src=\"images/home.png\" alt=\"...\">";
//                    str += "</a>";
//                    str += "<div class=\"media-body\">";
//                    str += "<h4 class=\"media-heading\">"
//                            + entry.apartaments.id + " "
//                            + entry.apartaments.cityName + " "
//                            + entry.apartaments.streetName + " "
//                            + entry.apartaments.houseNumber + " "
//                            + entry.apartaments.buildingNumber + " - "
//                            + entry.apartaments.roomNumber + " "
//                            + entry.apartaments.sizeApartament + "/"
//                            + entry.apartaments.sizeLiving + "/"
//                            + entry.apartaments.sizeKitchen + " "
//                            + "<p>Цена: " + entry.apartaments.price + "</p>";
//                    str += "</h4>";
//                    str += "<a href=\"#\" onclick=\"return getApartamentViewPage(\'" + entry.apartaments.id + " \')\">ссылка</a>";
//                    str += "</div>";
//                    str += "</div>";
//                });
//
//                $("#divApartamentsList").html(str);
//            },
//            error: function(data) {
//                showDanger(data.responseText);
//                return false;
//            }
//        });
    });
}

function addCustomer() {
    console.log("addCustomerById");
    $("#addCustomer").css("display", "block");
    $("#errorBlock").css("display", "none");
    
    $("#customersAddReadyLink").css("display", "inline");
    $("#customersAddReadyLink").click(function() {
//        if (
//                ($('#TypeOfSales').val() == "")
//                || ($('#Price').val() == "")
//                || ($('#CityDistrict').val() == "")
//                || ($('#Floor').val() == "")
//                || ($('#Floors').val() == "")
//                || ($('#RoomNumber').val() == "")
//                || ($('#Material').val() == "")
//                || ($('#SizeApartament').val() == "")
//                || ($('#SizeLiving').val() == "")
//                || ($('#SizeKitchen').val() == "")
//                || ($('#Balcony').val() == "")
//                || ($('#Loggia').val() == "")
//                || ($('#YearOfConstruction').val() == "")
//                || ($('#Description').val() == "")
//                || ($('#ClientPhone').val() == "")
//                ) {
//            $("#errorBlock").addClass("alert-danger");
//            $("#errorMessage").html("Не все поля заполнены");
//            $("#errorBlock").css("display", "block");
//            return false;
//        }
        var pursale;
        $("#PureSale").click(function() {
            if (document.getElementById("PureSale").checked) {
                pursale = true;
            } else {
                pursale = false;
            }
        });
        var mortgage;
        $("#Mortgage").click(function() {
            if (document.getElementById("Mortgage").checked) {
                mortgage = true;
            } else {
                mortgage = false;
            }
        });
        var exchange;
        $("#Exchange").click(function() {
            if (document.getElementById("Exchange").checked) {
                exchange = true;
            } else {
                exchange = false;
            }
        });
        var rent;
        $("#Rent").click(function() {
            if (document.getElementById("Rent").checked) {
                rent = true;
            } else {
                rent = false;
            }
        });
        var replanning;
        $("#RePlanning").click(function() {
            if (document.getElementById("RePlanning").checked) {
                replanning = true;
            } else {
                replanning = false;
            }
        });
        $.ajax({
            type: "POST",
            url: "api/apartament/addapartament",
            data: ({
                typeofsales: $('#TypeOfSales').val(),
                cityName: $('#apartamentCity').text(),
                streetName: $('#apartamentStreet').text(),
                houseNumber: $('#apartamentBuilding').text(),
                buildingNumber: $('#apartamentBuildingAdd').text(),
                rooms: $('#Rooms').val(),
                kladrId: $('#Price').val(),
                shortAddress: $('#address').text(),
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
                puresale: pursale,
                mortgage: mortgage,
                exchange: exchange,
                rent: rent,
                replanning: replanning,
                clientphone: $('#ClientPhone').val(),
                clientdescription: $('#ClientDescription').val(),
            }),
            success: function(data) {
                location.reload();//FIXME
                $("#errorBlock").css("display", "none");
                $('#addCourseBtn').css('display', 'block');
//                getCoursesPage();
            },
            error: function(data) {
                showDanger(data.responseText);
            }
        });
        $("#addApartaments").css("display", "none");
    });
}

function editCustomerById(customersId) {
    console.log("editCustomerById " + customersId);
}

function customersDeleteById(customersId) {
    console.log("customersDeleteById " + customersId);
    $.ajax({
        type: "POST",
        url: "api/customer/remove",
        data: ({id: customersId}),
        success: function(data) {
            getCustomersListPage();
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
