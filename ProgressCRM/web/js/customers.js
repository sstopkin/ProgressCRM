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
    console.log("addCustomer");
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
        $.ajax({
            type: "POST",
            url: "api/customers/addcustomer",
            data: ({
                customersFname: $('#customersFname').val(),
                customersMname: $('#customersMname').val(),
                customersLname: $('#customersLname').val(),
                customersYearOfBirthday: $('#customersYearOfBirthday').val(),
                customersMonthOfBirthday: $('#customersMonthOfBirthday').val(),
                customersDayOfBirthday: $('#customersDayOfBirthday').val(),
                customersSex: $('#customersSex').val(),
                customersEmail: $('#customersEmail').val(),
                customersPhone: $('#customersPhone').val(),
                customersAddress: $('#customersAddress').val(),
                customersExtra: $('#customersExtra').val()
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
