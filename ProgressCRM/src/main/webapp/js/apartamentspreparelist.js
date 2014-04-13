function getApartamentsPrepareListPage() {
    $.get("apartamentspreparelits.html", function(data) {
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
        $("#mainContainer").html(data);
        var userId;
        $.get("api/auth/author", function(data2) {
            userId = data2;
        });
        $.ajax({
            type: "GET",
            url: "api/apartament/getallapartamentзprepare",
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
                str += "<th>Добавлено</th>";
                str += "<th>Риэлтор</th>";
                str += "<th>Дата</th>";
                str += "<th>Звонок</th>";
                if (permissions == "3") {
                    str += "<th>Редактировать</th>";
                    str += "<th>Удалить</th>";
                }
                str += "</tr>";
                str += "</thead>";
                str += "<tbody>";

                var apartsArray = [];
                var dormitoryArray = [];
                var barchelorArray = [];
                var subrentalArray = [];

                array.forEach(function(entry) {
                    switch (entry.dwellingType) {
                        case 1:
                            apartsArray.push(entry);
                            break;
                        case 2:
                            dormitoryArray.push(entry);
                            break;
                        case 3:
                            barchelorArray.push(entry);
                            break;
                        case 4:
                            subrentalArray.push(entry);
                            break;
                    }
                });
                if (barchelorArray.length !== 0) {
                    str += drawPrepare(barchelorArray, permissions, "Малосемейки");
                }
                if (subrentalArray.length !== 0) {
                    str += drawPrepare(subrentalArray, permissions, "Подселение");
                }
                if (dormitoryArray !== 0) {
                    str += drawPrepare(dormitoryArray, permissions, "Гостинки");
                }
                if (apartsArray !== 0) {
                    str += drawPrepare(apartsArray, permissions, "Квартиры");
                }
                str += "</tbody>";
                $("#divApartamentsPrepareList").html(str);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function drawPrepare(array, permissions, catName) {
    var flag1 = false;
    var flag2 = false;
    var flag3 = false;
    var flag4 = false;
    var flag5 = false;

    var str = "<tr><td COLSPAN=11><h5><b>" + catName + "</b></h5></td></tr>";

    array.forEach(function(entry) {
        switch (entry.rooms) {
            case 1:
                if (flag1 == false) {
                    str += "<tr><td COLSPAN=11><h5><b>1-комнатные</b></h5></td></tr>";
                    flag1 = true;
                }
                break;
            case 2:
                if (flag2 == false) {
                    str += "<tr><td COLSPAN=11><h5><b>2-комнатные</b></h5></td></tr>";
                    flag2 = true;
                }
                break;
            case 3:
                if (flag3 == false) {
                    str += "<tr><td COLSPAN=11><h5><b>3-комнатные</b></h5></td></tr>";
                    flag3 = true;
                }
                break;
            case 4:
                if (flag4 == false) {
                    str += "<tr><td COLSPAN=11><h5><b>4-комнатные</b></h5></td></tr>";
                    flag4 = true;
                }
                break;
            case 5:
                if (flag5 == false) {
                    str += "<tr><td COLSPAN=11><h5><b>5-комнатные</b></h5></td></tr>";
                    flag5 = true;
                }
                break;
        }
        str += "<tr>";
        str += "<td><a href=\"#apartaments/view/" + entry.id + "\" class=\"btn btn-default\"><b>" + entry.id + "</b></a></td>";
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
            if (entry.idWorkerTarget == a[0]) {
                str += "<td>" + a[1] + " " + a[3] + "</td>";
            }
        }
        str += "<td>" + entry.сreationDate + "</td>";
        str += "<td>" + "<button type=\"button\" onclick=\"addCallDialog('" + entry.ApartamentUUID + "');\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-earphone\"></span></button>";
        str += "<button type=\"button\" onclick=\"addCommentDialog('" + entry.ApartamentUUID + "');\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-comment\"></span></button>" + "</td>";
        if (permissions == "3") {
            str += "<td><a href=\"#apartaments/edit/" + entry.id + "\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></a></td>";
            str += "<td>" + "<button type=\"button\" onclick=\"apartamentsDeleteById(" + entry.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>" + "</td>";
        }
        str += "</tr>";
    });
    return str;
}