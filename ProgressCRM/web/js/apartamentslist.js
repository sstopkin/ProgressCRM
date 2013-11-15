function getapartamentsListPage() {
    $.get("apartamentslist.html", function(data) {
        $.get("api/auth/validate", function(data) {
            if ((data == "3") || (data == "2")) {
                $("#addApartamentBtn").css("display", "block");
                $("#genApartamentsPriceBtn").css("display", "block");
            } else {
                $("#addApartamentBtn").css("display", "none");
                $("#genApartamentsPriceBtn").css("display", "none");
            }
        });
        $("#mainContainer").html(data);
        var permissions;
        var userId;
        $.get("api/auth/validate", function(data3) {
            permissions = data3;
            if (permissions == "3") {
                $("#approvingTagsForTasks").css("display", "block");
            }
        });
        $.get("api/auth/author", function(data2) {
            userId = data2;
        });
        $.ajax({
            type: "GET",
            url: "api/apartament/getallapartament",
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "<table class=\"table table-bordered\">";
                str += "<thead>";
                str += "<tr>";
                str += "<th>#</th>";
                str += "<th>Улица</th>";
                str += "<th>Номер дома</th>";
                str += "<th>Кол-во комнат</th>";
                str += "<th>Этаж/Этажность</th>";
                str += "<th>Описание</th>";
                str += "<th>Автор</th>";
                str += "<th>Дата</th>";
                str += "</tr>";
                str += "</thead>";
                str += "<tbody>";

//IsApproved: false
//MethodOfPurchase_Exchange: false
//MethodOfPurchase_Mortgage: false
//MethodOfPurchase_PureSale: false
//MethodOfPurchase_Rent: false
//balcony: 0
//buildingNumber: ""
//cityDistrict: 5
//cityName: "г. Омск"
//deleted: false
//description: "текст"
//floor: 1
//floors: 5
//houseNumber: "д. 92"
//id: 4
//idCustomer: 1
//idWorker: 2
//kladrId: "123"
//lastModify: "Sep 23, 2013 12:44:16 PM"
//loggia: 0
//material: 2
//price: 123
//rePplanning: false
//roomNumber: 1
//rooms: 3
//shortAddress: "644089, г. Омск, пр-кт. Мира, д. 92"
//sizeApartament: 234
//sizeKitchen: 23
//sizeLiving: 123
//streetName: "пр-кт. Мира"
//typeOfSales: 2
//yearOfConstruction: 124
                var flag1 = false;
                var flag2 = false;
                var flag3 = false;
                var flag4 = false;
                var flag5 = false;

                array.forEach(function(entry) {

                    str += "<H3>";
                    switch (entry.apartaments.rooms) {
                        case 1:
                            if (flag1 == false) {
                                str += "1-комнатные";
                                flag1 = true;
                            }
                            break;
                        case 2:
                            if (flag2 == false) {
                                str += "2-комнатные";
                                flag2 = true;
                            }
                            break;
                        case 3:
                            if (flag3 == false) {
                                str += "3-комнатные";
                                flag3 = true;
                            }
                            break;
                        case 4:
                            if (flag4 == false) {
                                str += "4-комнатные";
                                flag4 = true;
                            }
                            break;
                        case 5:
                            if (flag5 == false) {
                                str += "5-комнатные";
                                flag5 = true;
                            }
                            break;
                    }
                    str += "</H3>";


                    str += "<tr>";
                    str += "<td><a href=\"#\" onclick=\"return getAnnouncementsViewPage(" + entry.apartaments.id + ")\">" + entry.apartaments.id + "</a></td>";
                    str += "<td>" + entry.street + "</td>";
                    str += "<td>" + entry.houseNumber + "</td>";
                    str += "<td>" + entry.rooms + "</td>";
                    str += "<td>" + entry.floor + " / " + entry.floors + "</td>";
                    str += "<td>" + entry.description + "</td>";
                    for (var i = 0; i < workersList.length; ++i) {
                        var a = workersList[i];
                        if (entry.idWorker == a[0]) {
                            str += "<td>" + a[1] + a[3] + "</td>";
                        }
                    }
                    str += "<td>" + entry.creationDate + "</td>";
                    str += "</tr>";




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
//                            + entry.apartaments.rooms + " "
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
                });
                str += "</tbody>";
                $("#divApartamentsList").html(str);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function apartamentsDeleteById(apartamentsId) {
    console.log("apartamentsDeleteById " + apartamentsId);
    $.ajax({
        type: "POST",
        url: "api/apartament/remove",
        data: ({id: apartamentsId}),
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