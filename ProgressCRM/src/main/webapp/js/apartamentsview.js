var map = null;
var placemark = null;
var map_created = false;
function getApartamentViewPage(apartamentId) {
    $.get("apartamentsview.html", function(data) {
        $("#mainContainer").html(data);
        $.ajax({
            type: "GET",
            url: "api/apartament/getapartament?id=" + apartamentId,
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var content = "";

                console.log(array.IsApproved);
                console.log(array.deleted);

                content += "<p>";
                content += "ID = " + array.id;
                content += "</p>";


                content += "<p>";
                switch (array.typeOfSales) {
                    case 1:
                        content += "Эксклюзивная продажа";
                        break;
                    case 2:
                        content += "Общая продажа";
                        break;
                    default:
                        content += "";
                }
                content += "</p>";

                content += "<p>";
                content += "Адрес "
                        + array.cityName + " "
                        + array.streetName + " "
                        + array.houseNumber + " "
                        + array.buildingNumber + " - "
                        + array.roomNumber + " ";
                content += "</p>";

                content += "<p>";
                content += "Количество комнат " + array.rooms;
                content += "</p>";

                content += "<p>";
                content += "Цена = " + array.price;
                content += "</p>";

                if (array.MethodOfPurchase_Mortgage) {
                    content += "<p>";
                    content += "Ипотека";
                    content += "</p>";
                }
                if (array.MethodOfPurchase_PureSale) {
                    content += "<p>";
                    content += "Чистая продажа";
                    content += "</p>";
                }
                if (array.MethodOfPurchase_Exchange) {
                    content += "<p>";
                    content += "Обмен";
                    content += "</p>";
                }
                if (array.MethodOfPurchase_Rent) {
                    content += "<p>";
                    content += "Аренда";
                    content += "</p>";
                }

                content += "<p>";
                content += "Перепланировки: ";
                if (array.rePplanning) {
                    content += "Да";
                }
                else {
                    content += "Нет";
                }
                content += "</p>";

                content += "<p>";
                switch (array.cityDistrict) {
                    case 1:
                        content += "Кировский административный округ";
                        break;
                    case 2:
                        content += "Ленинский административный округ";
                        break;
                    case 3:
                        content += "Октябрьский административный округ";
                        break;
                    case 4:
                        content += "Советский административный округ";
                        break;
                    case 5:
                        content += "Центральный административный округ";
                        break;
                    default:
                        content += "";
                }
                content += "</p>";
                content += "<p>";
                content += "Балкон: ";
                if (array.balcony != 0) {
                    content += array.balcony;
                }
                content += "</p>";

                content += "<p>";
                content += "Лоджия: ";
                if (array.loggia != 0) {
                    content += array.loggia;

                }
                content += "</p>";

                content += "<p>";
                content += "Этажность: " + array.floors;
                content += "</p>";
                content += "<p>";
                content += "Этаж: " + array.floor;
                content += "</p>";

                content += "<p>";
                content += "Год постройки дома: " + array.yearOfConstruction;
                content += "</p>";

                content += "<p>";
                switch (array.material) {
                    case 1:
                        content += "Панельный";
                        break;
                    case 2:
                        content += "Кирпичный";
                        break;
                    case 3:
                        content += "Сборный ж/б";
                        break;
                    case 4:
                        content += "Другое/Не указан";
                        break;
                    default:
                        content += "";
                }
                content += "</p>";

                content += "<p>";
                content += "Описание: " + array.description;
                content += "</p>";

                content += "<p>";
                content += "Информация о клиенте: FIXME";//array.clientDescription
                content += "</p>";
                content += "<p>";
                content += "Телефон клиента: FIXME";
                content += "</p>";



                content += "<p>";
                content += "Объект добавлен: " + array.сreationDate;
                content += "</p>";
                content += "<p>";
                content += "Объект изменен: " + array.lastModify;
                content += "</p>";

                for (var i = 0; i < workersList.length; ++i) {
                    var a = workersList[i];
                    if (array.idWorker == a[0]) {
                        content += "<p>";
                        content += "Автор: " + a[1] + " " + a[3];
                        content += "</p>";
                    }
                }
                console.log(array.kladrId);

                content += "<p>";
                content += "Площадь общая: " + array.sizeApartament;
                content += "</p>";
                content += "<p>";
                content += "Площадь кухни: " + array.sizeKitchen;
                content += "</p>";
                content += "<p>";
                content += "Площадь жилая: " + array.sizeLiving;
                content += "</p>";



                $("#apartamentsFeatures").html(content);


                var maps = "<iframe width=\"425\" height=\"350\" frameborder=\"0\" scrolling=\"no\" marginheight=\"0\" marginwidth=\"0\" src=\"http://maps.google.ru/?ie=UTF8&amp;ll=" + array.apartamentLan + "," + array.apartamentLon + "&amp;spn=" + array.apartamentLan + "," + array.apartamentLon + "&amp;z=17&amp;vpsrc=0&amp;output=embed\"></iframe>";
                maps += "<br/>";
                maps += "<small>";
                maps += "<a href = \"http://maps.google.ru/?ie=UTF8&amp;ll=" + array.apartamentLan + "," + array.apartamentLon + "&amp;spn=" + array.apartamentLan + "," + array.apartamentLon + "&amp;z=4&amp;vpsrc=0&amp;source=embed\" style=\"color:#0000FF;text-align:left\"> Просмотреть увеличенную карту</a>";
                maps += "</small>";

                $("#mapApartamentsView").html(maps);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
        $.ajax({
            type: "GET",
            url: "api/calls/getcalls?id=" + apartamentId,
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "";
                str += "<table class=\"table table-striped table-bordered table-condensed\" style='margin-top:10px;'>";
                str += "<thead class='t-header'>Звонки<tr>";
                str += "<th>Дата</th>";
                str += "<th>Комментарий</th>";
                str += "</tr></thead>";
                str += "<tbody>";
                for (var j = 0; j < array.length; ++j) {
                    str += "<tr><td>";
                    str += array[j].date;
                    str += "</td><td>";
                    str += array[j].description;
                }
                str += "\n</tbody>\n</table>\n";
                $("#customersCalls").html(str);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function apartamentsEditById(apartamentId) {
    $.get("apartamentsadd.html", function(data) {
        $("#mainContainer").html(data);
        $.ajax({
            type: "GET",
            url: "api/apartament/getapartament?id=" + apartamentId,
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
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
                $('#CityDistrict').val(array.citydistrict);
                $('#Floor').val(array.floor);
                $('#Floors').val(array.floors);
                $('#RoomNumber').val(array.roomnumber);
                $('#Material').val(array.material);
                $('#SizeApartament').val(array.sizeapartament);
                $('#SizeLiving').val(array.sizeliving);
                $('#SizeKitchen').val(array.sizekitchen);
                $('#Balcony').val(array.balcony);
                $('#Loggia').val(array.loggia);
                $('#YearOfConstruction').val(array.yearofconstruction);
                $('#Description').val(array.description);
//                    puresale: pursale
//                    mortgage: mortgage
//                    exchange: exchange
//                    rent: rent
//                    replanning: replanning
            }
        });
    });
}