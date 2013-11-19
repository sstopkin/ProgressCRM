function getapartamentsListPage() {
    $.get("apartamentslist.html", function(data) {
        var permissions = $.ajax({
            type: "GET",
            url: "api/auth/validate",
            async: false
        }).responseText;
        if ((permissions == "3") || (permissions == "2")) {
            $("#addApartamentBtn").css("display", "block");
            $("#genApartamentsPriceBtn").css("display", "block");
        } else {
            $("#addApartamentBtn").css("display", "none");
            $("#genApartamentsPriceBtn").css("display", "none");
        }
        $("#mainContainer").html(data);
        var permissions;
        var userId;
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
                str += "<th>Адрес</th>";
                str += "<th>Комнат</th>";
                str += "<th>Площадь О/К/Ж</th>";
                str += "<th>Этаж</th>";
                str += "<th>Цена</th>";
                str += "<th>Автор</th>";
                str += "<th>Дата</th>";
                if (permissions == "3") {
                    str += "<th>Редактировать</th>";
                    str += "<th>Удалить</th>";
                }
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

                    switch (entry.apartaments.rooms) {
                        case 1:
                            if (flag1 == false) {
                                str += "<tr><td COLSPAN=8>1-комнатные</td></tr>";
                                flag1 = true;
                            }
                            break;
                        case 2:
                            if (flag2 == false) {
                                str += "<tr><td COLSPAN=8>2-комнатные</td></tr>";
                                flag2 = true;
                            }
                            break;
                        case 3:
                            if (flag3 == false) {
                                str += "<tr><td COLSPAN=8>3-комнатные</td></tr>";
                                flag3 = true;
                            }
                            break;
                        case 4:
                            if (flag4 == false) {
                                str += "<tr><td COLSPAN=8>4-комнатные</td></tr>";
                                flag4 = true;
                            }
                            break;
                        case 5:
                            if (flag5 == false) {
                                str += "<tr><td COLSPAN=8>5-комнатные</td></tr>";
                                flag5 = true;
                            }
                            break;
                    }


                    str += "<tr>";
                    str += "<td><a href=\"#\" onclick=\"return getApartamentViewPage(" + entry.apartaments.id + ")\">" + entry.apartaments.id + "</a></td>";
                    str += "<td>" + entry.apartaments.shortAddress + "</td>";
                    str += "<td>" + entry.apartaments.rooms + "</td>";
                    str += "<td>" + entry.apartaments.sizeApartament + " / " + entry.apartaments.sizeKitchen + " / " + entry.apartaments.sizeLiving + "</td>";
                    str += "<td>" + entry.apartaments.floor + " / " + entry.apartaments.floors + "</td>";
                    str += "<td>" + entry.apartaments.price + "</td>";
                    for (var i = 0; i < workersList.length; ++i) {
                        var a = workersList[i];
                        if (entry.apartaments.idWorker == a[0]) {
                            str += "<td>" + a[1] + a[3] + "</td>";
                        }
                    }
                    str += "<td>" + entry.apartaments.сreationDate + "</td>";
                    if (permissions == "3") {
                        str += "<td>" + "<button type=\"button\" onclick=\"apartamentsEditById(" + entry.apartaments.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>" + "</td>";
                        str += "<td>" + "<button type=\"button\" onclick=\"apartamentsDeleteById(" + entry.apartaments.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>" + "</td>";
                    }
                    str += "</tr>";




//                    str += "<div class = \"media\">";
//                    if (permissions == "3") {
//                        str += "<div class=\"btn-toolbar\">";
//                        str += "<div class=\"btn-group\">";
//
//                        str += "";
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