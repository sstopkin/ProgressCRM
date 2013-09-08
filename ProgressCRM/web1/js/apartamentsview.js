//var map = null;
//var placemark = null;
//var map_created = false;
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
//                MapUpdate(array.apartaments.cityName,
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

                if (array.apartaments.balcony != 0) {
                    content += "<p>";
                    content += "Балкон " + array.apartaments.balcony;
                    content += "</p>";
                }
                if (array.apartaments.loggia != 0) {
                    content += "<p>";
                    content += "Лоджия " + array.apartaments.loggia;
                    content += "</p>";
                }

                console.log(array.apartaments.clientDescription);
                console.log(array.apartaments.clientPhone);
                console.log(array.apartaments.description);
                console.log(array.apartaments.floor);
                console.log(array.apartaments.floors);
                console.log(array.apartaments.idWorker);
                console.log(array.apartaments.kladrId);
                console.log(array.apartaments.lastModify);

                console.log(array.apartaments.material);
                console.log(array.apartaments.price);
                console.log(array.apartaments.rePplanning);
                console.log(array.apartaments.sizeApartament);
                console.log(array.apartaments.sizeKitchen);
                console.log(array.apartaments.idWorker);
                console.log(array.apartaments.kladrId);
                console.log(array.apartaments.lastModify);
                console.log(array.apartaments.loggia);
                console.log(array.apartaments.material);
                console.log(array.apartaments.price);
                console.log(array.apartaments.rePplanning);
                console.log(array.apartaments.sizeApartament);
                console.log(array.apartaments.sizeKitchen);
                console.log(array.apartaments.sizeLiving);
                console.log(array.apartaments.typeOfSales);
                console.log(array.apartaments.yearOfConstruction);
                console.log(array.apartaments.sizeLiving);
                console.log(array.apartaments.typeOfSales);
                console.log(array.apartaments.yearOfConstruction);
                console.log(array.apartaments.сreationDate);
                console.log(array.apartaments.сreationDate);

                $("#apartamentsFeatures").html(content);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

// Обновляет карту
var MapUpdate = function(city, street, building, buildingAdd) {
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

