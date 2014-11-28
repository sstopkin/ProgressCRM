function getApartamentViewPage(apartamentId) {
    $.get("apartamentsview.html", function (data) {
        $("#mainContainer").html(data);
        var permissions = $.ajax({
            type: "GET",
            url: "api/auth/validate",
            async: false
        }).responseText;
        var content = "";
        var array;
        $.ajax({
            type: "GET",
            url: "api/apartament/getapartamentfull?id=" + apartamentId,
            async: false,
            success: function (data) {
                $("#errorBlock").css("display", "none");
                array = JSON.parse(data);
                initMapView(array.apartamentLan, array.apartamentLon,
                        array.cityName + " "
                        + array.streetName + " "
                        + array.houseNumber + " "
                        + array.buildingNumber + " - "
                        + array.roomNumber);
                content += "<input onclick=\"window.location = '/api/report/getapartamentsreport/" + array.id + "';\" type=\"button\" class=\"btn btn-info pull-right\" id=\"addApartamentBtn\" value=\"Карточка\" />";
                if (permissions == "3") {
                    content += "<a href=\"#apartaments/edit/" + array.id + "\" class=\"btn btn-warning\"><span class=\"glyphicon glyphicon-pencil\"></span>Редактировать</a>";
                    content += "<button type=\"button\" onclick=\"confirmActionDelete('apartamentsDeleteById(" + array.id + ")');\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-remove\"></span>Удалить</button>";
                }
                content += "<p>";
                content += "ID = " + array.id;
                content += "</p>";
                content += "<p>";
                content += "Статус: ";
                switch (array.typeOfSales) {
                    case 1:
                        content += "В работе";
                        break;
                    case 4:
                        content += "Архив";
                        break;
                    case 5:
                        content += "Не выбран";
                        break;
                    default:
                        content += "";
                }
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
                content += "Адрес: "
                        + array.cityName + " "
                        + array.streetName + " "
                        + array.houseNumber + " "
                        + array.buildingNumber + " - "
                        + array.roomNumber + " ";
                content += "</p>";
                content += "<p>";
                content += "Количество комнат: " + array.rooms;
                content += "</p>";
                content += "<p>";
                content += "Цена: " + array.price;
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
                if (array.balcony === 0) {
                    content += "Нет";
                }
                else {
                    content += array.balcony;
                }
                content += "</p>";
                content += "<p>";
                content += "Лоджия: ";
                if (array.loggia === 0) {
                    content += "Нет";
                }
                else {
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
                content += "Материал дома: ";
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
                content += "Объект добавлен: " + timeConverter(array.сreationDate);
                content += "</p>";
                content += "<p>";
                content += "Объект изменен: " + timeConverter(array.lastModify);
                content += "</p>";
                content += "<p>";
                content += "Добавлено: " + getWorkersFullNameById(array.idWorker);
                content += "</p>";
                content += "<p>";
                content += "Риэлтор: " + getWorkersFullNameById(array.idWorkerTarget);
                content += "</p>";
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
                content += "<p>";
                content += "Описание: " + array.description;
                content += "</p>";
            },
            error: function (data) {
                showDanger(data.responseText);
                return false;
            }
        });
        $.ajax({
            type: "GET",
            url: "api/customers/getcustomer?id=" + array.idCustomer,
            async: false,
            success: function (data) {
                var array = JSON.parse(data);
                content += "<p>";
                content += "<b>Информация о владельце: </b>"; //array.clientDescription
                content += "</p>";
                content += array.customersFname + " ";
                content += array.customersLname + " ";
                content += array.customersMname + " ";
                content += array.customersPhone + " ";
                content += "</p>";
            }
        });
        $.ajax({
            type: "GET",
            url: "api/calls/getcalls?objectUUID=" + array.ApartamentUUID,
            success: function (data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "";
                var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="callsListTable">';
                str += "<thead class='t-header'><tr>";
                str += "<th>Дата</th>";
                str += "<th>Входящий номер</th>";
                str += "<th>Комментарий</th>";
                str += "</tr></thead>";
                str += "<tbody>";
                for (var j = 0; j < array.length; ++j) {
                    str += "<tr><td>";
                    str += timeConverter(array[j].date);
                    str += "</td><td>";
                    str += array[j].incomingPhoneNumber;
                    str += "</td><td>";
                    str += array[j].description;
                }
                str += "\n</tbody>\n</table>\n";
                $("#customersCalls").html(str);
                $('#callsListTable').dataTable();
            },
            error: function (data) {
                showDanger(data.responseText);
                return false;
            }
        });
        $.ajax({
            type: "GET",
            url: "api/comments/getcomments?objectUUID=" + array.ApartamentUUID,
            success: function (data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "";
                var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="commentsListTable">';
                str += "<thead class='t-header'><tr>";
                str += "<th>Дата</th>";
                str += "<th>Комментарий</th>";
                str += "</tr></thead>";
                str += "<tbody>";
                for (var j = 0; j < array.length; ++j) {
                    str += "<tr><td>";
                    str += timeConverter(array[j].сreationDate);
                    str += "</td><td>";
                    str += array[j].text;
                }
                str += "\n</tbody>\n</table>\n";
                $("#workersComments").html(str);
                $('#commentsListTable').dataTable();
            },
            error: function (data) {
                showDanger(data.responseText);
                return false;
            }
        });
        getFileManagerPage(array.filespaceUUID, array.ApartamentUUID);
        $("#apartamentsFeatures").html(content);
        getPlannerPage(array.ApartamentUUID);
        $("#addApartamentTaskBtn").click(function () {
            addPlannerTaskDialog(array.ApartamentUUID);
        });

    });
}

function getFileManagerPage(filespaceUUID, ApartamentUUID) {
    if (filespaceUUID == "") {
        $("#apartamentsFilemanager").html("<input onclick=\"createApartamentsFilespace('" + ApartamentUUID + "');\" type=\"button\" class=\"btn btn-success pull-right\" value=\"Создать хранилище\" />");
    }
    else {
        $.ajax({
            type: "GET",
            url: "api/filespaces/getfilespace?uuid=" + filespaceUUID,
            async: false,
            success: function (data) {
                var str;
                $.get("fm.html", function (data) {
                    $("#apartamentsFilemanager").html(data);
                });
                getFolderList(data);
            }
        });
    }
}

function createApartamentsFilespace(targetuuid) {
    $.ajax({
        type: "POST",
        url: "api/filespaces/createfilespace",
        data: ({
            targetuuid: targetuuid,
            type: 1
        }),
        success: function () {
            location.reload(); //FIXME
            $("#errorBlock").css("display", "none");
        },
        error: function (data) {
            showDanger(data.responseText);
        }
    });
}

function getPlannerPage(apartementsUUID) {
    $.get("templates/calendar.html", function (data) {
        $("#apartamentsTasks").html(data);
        initCalendar('/api/planner/uuid/' + apartementsUUID);
    });
}

function initMapView(apartamentLan, apartamentLon, address) {
    var myMap;
//    <div id="map" style="width: 450px; height: 450px"></div>
//    $("#mapApartamentsView").html(maps);
    $('#toggle').bind({
        click: function () {
            if (!myMap) {
                myMap = new ymaps.Map('mapApartamentsView', {
                    center: [apartamentLan, apartamentLon],
                    zoom: 16
                });

                // Для добавления элемента управления на карту
                // используется поле map.controls.
                // Это поле содержит ссылку на экземпляр класса map.control.Manager.

                // Добавление элемента в коллекцию производится
                // с помощью метода add.

                // В метод add можно передать строковый идентификатор
                // элемента управления и его параметры.
                myMap.controls
                        // Кнопка изменения масштаба.
                        .add('zoomControl', {left: 5, top: 5});

                // В конструкторе элемента управления можно задавать расширенные
                // параметры, например, тип карты в обзорной карте.
//                            .add(new ymaps.control.MiniMap({
//                                type: 'yandex#publicMap'
//                            }));

                /*
                 // Удаление элементов управления производится через метод remove.
                 myMap.controls
                 .remove(trafficControl)
                 .remove('mapTools');
                 */

                myPlacemark = new ymaps.Placemark([apartamentLan, apartamentLon], {
                    // Чтобы балун и хинт открывались на метке, необходимо задать ей определенные свойства.
                    balloonContentHeader: "",
                    balloonContentBody: address,
                    balloonContentFooter: "",
                    hintContent: ""
                });

                myMap.geoObjects.add(myPlacemark);
                $("#toggle").attr('value', 'Скрыть карту');
            }
            else {
                myMap.destroy();// Деструктор карты
                myMap = null;
                $("#toggle").attr('value', 'Показать карту снова');
            }
        }
    });
}