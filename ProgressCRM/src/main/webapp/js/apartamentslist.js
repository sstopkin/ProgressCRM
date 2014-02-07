function getApartamentsListPage() {
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

                    switch (entry.rooms) {
                        case 1:
                            if (flag1 == false) {
                                str += "<tr><td COLSPAN=9><h5><b>1-комнатные</b></h5></td></tr>";
                                flag1 = true;
                            }
                            break;
                        case 2:
                            if (flag2 == false) {
                                str += "<tr><td COLSPAN=9><h5><b>2-комнатные</b></h5></td></tr>";
                                flag2 = true;
                            }
                            break;
                        case 3:
                            if (flag3 == false) {
                                str += "<tr><td COLSPAN=9><h5><b>3-комнатные</b></h5></td></tr>";
                                flag3 = true;
                            }
                            break;
                        case 4:
                            if (flag4 == false) {
                                str += "<tr><td COLSPAN=9><h5><b>4-комнатные</b></h5></td></tr>";
                                flag4 = true;
                            }
                            break;
                        case 5:
                            if (flag5 == false) {
                                str += "<tr><td COLSPAN=9><h5><b>5-комнатные</b></h5></td></tr>";
                                flag5 = true;
                            }
                            break;
                    }

                    str += "<tr>";
                    str += "<td><a href=\"#apartaments/view/" + entry.id + "\"><b>" + entry.id + "</b></a></td>";
                    str += "<td>" + entry.cityName + " "
                            + entry.streetName + " "
                            + entry.houseNumber + " "
                            + entry.buildingNumber + "</td>";
                    str += "<td>" + entry.sizeApartament + " / " + entry.sizeKitchen + " / " + entry.sizeLiving + "</td>";
                    str += "<td>" + entry.floor + " / " + entry.floors + "</td>";
                    str += "<td>" + entry.price + "</td>";
                    for (var i = 0; i < workersList.length; ++i) {
                        var a = workersList[i];
                        if (entry.idWorker == a[0]) {
                            str += "<td>" + a[1] + " " + a[3] + "</td>";
                        }
                    }
                    str += "<td>" + entry.сreationDate + "</td>";
                    if (permissions == "3") {
                        str += "<td>" + "<button type=\"button\" onclick=\"apartamentsEditById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>" + "</td>";
                        str += "<td>" + "<button type=\"button\" onclick=\"apartamentsDeleteById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>" + "</td>";
                    }
                    str += "</tr>";
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