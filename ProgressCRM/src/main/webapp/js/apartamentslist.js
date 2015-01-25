function getApartamentsListPage(status, statusText) {
    $.get("apartamentslist.html", function (data) {
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
        $("#mainContainer").html("<div id=\"mainSearchContainer\" class=\"container\"></div>" + data);
//        initSearchForm('apartaments');
        $("#apartamentsListHeaderText").html(statusText);
        $("#genApartamentsPriceBtn").click(function () {
            window.location = '/api/report/getprice?status=' + status;
        });
        $.ajax({
            type: "GET",
            url: "api/apartament/getallapartament?status=" + status,
            success: function (data) {
                var array = JSON.parse(data);
                drawApartamentsListTable(array, status);
                drawApartamentsListMap(array, status);
            },
            error: function (data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function drawApartamentsListTable(array, status) {
    $("#errorBlock").css("display", "none");
    var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="apartamentsListTable">';
    str += "<thead>";
    str += "<tr>";
    str += "<th>#</th>";
    str += '<th>Адрес</th>';
    str += '<th>Комнат</th>';
    str += "<th>Площадь<br>О/К/Ж</th>";
    str += '<th>Этаж</th>';
    str += '<th>Цена</th>';
    str += "<th>Добавлено</th>";
    str += "<th>Риэлтор</th>";
    str += "<th>Дата</th>";
    str += "<th>Действие</th>";
    str += "</tr>";
    str += "</thead>";
    str += "<tbody>";
    str += draw(array, status);
    str += "</tbody>";
    $("#divApartamentsList").html(str);
    $('#apartamentsListTable').dataTable();
}

function draw(array, status) {
    var str = "";
    array.forEach(function (entry) {
        if (entry.isAD == '1') {
            str += '<tr class="success">';
        }
        else {
            str += "<tr>";
        }
        str += '<td><a href="#apartaments/view/' + entry.id + '" class="btn btn-primary"><b>' + entry.id + '</b></a></td>';
        str += "<td><address>" + entry.cityName + "<br>"
                + entry.streetName + "<br>"
                + entry.houseNumber + " "
                + entry.buildingNumber + "</address></td>";
        str += "<td>" + entry.rooms + "</td>";
        str += "<td>" + entry.sizeApartament + " / " + entry.sizeKitchen + " / " + entry.sizeLiving + "</td>";
        str += "<td>" + entry.floor + " / " + entry.floors + "</td>";
        str += "<td>" + entry.price + "</td>";
        str += "<td>" + getWorkersFullNameById(entry.idWorker) + "</td>";
        str += "<td>" + getWorkersFullNameById(entry.idWorkerTarget) + "</td>";
        str += "<td>" + timeConverter(entry.сreationDate, 'human') + "</td>";
        str += '<td>';
        str += '<div class="btn-group">';
        str += '<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">';
        str += 'Действия <span class="caret"></span>';
        str += '</button>';
        str += '<ul class="dropdown-menu" role="menu">';
        str += '<li><button type=\"button\" onclick=\"addCallDialog(\'' + entry.ApartamentUUID + '\');\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-earphone\"></span> Добавить звонок</button></li>';
        str += '<li class="divider"></li>';
        str += '<li><button type=\"button\" onclick=\"addCommentDialog(\'' + entry.ApartamentUUID + '\');\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-comment\"></span> Добавить комментарий</button></li>';
        str += '<li class="divider"></li>';
        if (status == '1') {//Ad for price objects
            if (entry.isAD == '0') {
                str += '<li><button type=\"button\" onclick=\"setApartamentsAdState(\'' + entry.id + '\',1);\" class=\"btn btn-success\"><span class=\"glyphicon glyphicon-ok\"></span> Добавить рекламу</button></li>';
            } else {
                str += '<li><button type=\"button\" onclick=\"setApartamentsAdState(\'' + entry.id + '\',0);\" class=\"btn btn-danger\"><span class=\"glyphicon glyphicon-remove\"></span> Убрать рекламу</button></li>';
            }
        }
        str += '</ul>';
        str += '</div>';
        str += '</td>';
        str += "</tr>";
    });
    return str;
}

function drawApartamentsListMap(array, status) {
    var myMap;
    $('#toggleMapApartamentsListView').bind({
        click: function () {
            if (!myMap) {
                myMap = new ymaps.Map('mapApartamentsListView', {
                    center: [54.989342, 73.368212],
                    zoom: 11
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

                array.forEach(function (entry) {
                    myPlacemark = new ymaps.Placemark([entry.apartamentLan, entry.apartamentLon], {
                        iconContent: "",
                        // Чтобы балун и хинт открывались на метке, необходимо задать ей определенные свойства.
                        balloonContentHeader: "ID " + entry.id,
                        balloonContentBody: entry.cityName + " "
                                + entry.streetName + " "
                                + entry.houseNumber + " "
                                + entry.buildingNumber + " - "
                                + entry.roomNumber,
                        balloonContentFooter: "Этаж " + entry.floor + "/" + entry.floors + ", Цена " + entry.price,
                        hintContent: entry.cityName + " "
                                + entry.streetName + " "
                                + entry.houseNumber + " "
                                + entry.buildingNumber + " - "
                                + entry.roomNumber
                    });
                    myMap.geoObjects.add(myPlacemark);
                });
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

function setApartamentsAdState(apartamentId, state) {
    $.ajax({
        type: "POST",
        url: "api/apartament/ad",
        data: ({
            id: apartamentId,
            state: state
        }),
        success: function (data) {
            getApartamentsListPage(1, "Прайс");
        },
        error: function (data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

function apartamentsDeleteById(apartamentsId) {
    $.ajax({
        type: "POST",
        url: "api/apartament/remove",
        data: ({id: apartamentsId}),
        success: function () {
            //FIXME! /list/all
            document.location.href = "#apartaments/list/price";
        },
        error: function (data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

