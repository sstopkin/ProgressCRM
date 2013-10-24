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
//                ymaps.ready(function() {
//                    if (map_created)
//                        return;
//                    map_created = true;
//
//                    map = new ymaps.Map('mapApartamentsView', {
//                        center: [73.378, 54.983],
//                        zoom: 12
//                    });
//                    map.controls.add('smallZoomControl', {top: 5, left: 5});
//                });
//                MapUpdateView(array.apartaments.cityName,
//                        array.apartaments.streetName,
//                        array.apartaments.houseNumber,
//                        array.apartaments.buildingNumber);
                var content = "";

                console.log(array.apartamentsPhotosList);

                console.log(array.apartaments.IsApproved);
                console.log(array.apartaments.deleted);

                content += "<p>";
                content += "ID = " + array.apartaments.id;
                content += "</p>";


                content += "<p>";
                switch (array.apartaments.typeOfSales) {
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
                        + array.apartaments.cityName + " "
                        + array.apartaments.streetName + " "
                        + array.apartaments.houseNumber + " "
                        + array.apartaments.buildingNumber + " - "
                        + array.apartaments.roomNumber + " ";
                content += "</p>";

                content += "<p>";
                content += "Количество комнат " + array.apartaments.rooms;
                content += "</p>";

                content += "<p>";
                content += "Цена = " + array.apartaments.price;
                content += "</p>";

                if (array.apartaments.MethodOfPurchase_Mortgage) {
                    content += "<p>";
                    content += "Ипотека";
                    content += "</p>";
                }
                if (array.apartaments.MethodOfPurchase_PureSale) {
                    content += "<p>";
                    content += "Чистая продажа";
                    content += "</p>";
                }
                if (array.apartaments.MethodOfPurchase_Exchange) {
                    content += "<p>";
                    content += "Обмен";
                    content += "</p>";
                }
                if (array.apartaments.MethodOfPurchase_Rent) {
                    content += "<p>";
                    content += "Аренда";
                    content += "</p>";
                }

                content += "<p>";
                content += "Перепланировки: ";
                if (array.apartaments.rePplanning) {
                    content += "Да";
                }
                else {
                    content += "Нет";
                }
                content += "</p>";

                content += "<p>";
                switch (array.apartaments.cityDistrict) {
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
                if (array.apartaments.balcony != 0) {
                    content += array.apartaments.balcony;
                }
                content += "</p>";

                content += "<p>";
                content += "Лоджия: ";
                if (array.apartaments.loggia != 0) {
                    content += array.apartaments.loggia;

                }
                content += "</p>";

                content += "<p>";
                content += "Этажность: " + array.apartaments.floors;
                content += "</p>";
                content += "<p>";
                content += "Этаж: " + array.apartaments.floor;
                content += "</p>";

                content += "<p>";
                content += "Год постройки дома: " + array.apartaments.yearOfConstruction;
                content += "</p>";

                content += "<p>";
                switch (array.apartaments.material) {
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
                content += "Описание: " + array.apartaments.description;
                content += "</p>";

                content += "<p>";
                content += "Информация о клиенте: " + array.apartaments.clientDescription;
                content += "</p>";
                content += "<p>";
                content += "Телефон клиента: " + array.apartaments.clientPhone;
                content += "</p>";



                content += "<p>";
                content += "Объект добавлен: " + array.apartaments.сreationDate;
                content += "</p>";
                content += "<p>";
                content += "Объект изменен: " + array.apartaments.lastModify;
                content += "</p>";

                console.log(array.apartaments.idWorker);
                console.log(array.apartaments.kladrId);

                content += "<p>";
                content += "Площадь общая: " + array.apartaments.sizeApartament;
                content += "</p>";
                content += "<p>";
                content += "Площадь кухни: " + array.apartaments.sizeKitchen;
                content += "</p>";
                content += "<p>";
                content += "Площадь жилая: " + array.apartaments.sizeLiving;
                content += "</p>";



                $("#apartamentsFeatures").html(content);
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

// Обновляет карту
var MapUpdateView = function(city, street, building, buildingAdd) {
    var zoom = 12;
    var address = '';

    var cityVal = $.trim(city);
    if (cityVal) {
        if (address)
            address += ', ';
        address += (cityVal ? (' ') : '') + cityVal;
        zoom = 12;
    }

    var streetVal = $.trim(street);
    if (streetVal) {
        if (address)
            address += ', ';
        address += (streetVal ? (' ') : '') + streetVal;
        zoom = 14;
    }

    var buildingVal = $.trim(building);
    if (buildingVal) {
        if (address)
            address += ', ';
        address += 'д. ' + buildingVal;
        zoom = 16;
    }

    var buildingAddVal = $.trim(buildingAdd);
    if (buildingAddVal) {
        if (address)
            address += ', ';
        address += buildingAddVal;
        zoom = 16;
    }
    console.log("MAP " + address);
    if (address && map_created) {
        var geocode = ymaps.geocode(address);
        geocode.then(function(res) {
            map.geoObjects.each(function(geoObject) {
                map.geoObjects.remove(geoObject);
            });

            var position = res.geoObjects.get(0).geometry.getCoordinates();

            placemark = new ymaps.Placemark(position, {}, {});

            map.geoObjects.add(placemark);
            map.setCenter(position, zoom);
        });
    }
};

