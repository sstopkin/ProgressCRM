function addApartament() {
    $.get("/templates/modal_apartaments.html", function (data) {
        $("#mainContainer").html(data);
        $("#errorBlock").css("display", "none");
        mapInit();
        workersList.forEach(function (entry) {
            $("#ApartamentsIdWorkerTarget").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
        });
        $("#apartamentAddReadyLink").css("display", "inline");
        //price
        $("#apartamentAddReadyLink").click(function () {
            if ($('#IdCustomer').val() == "") {
                showWarning("Не выбран клиент");
                return false;
            }
            if ($('#ApartamentsIdWorkerTarget').val() == -1) {
                showWarning("Не назначен риэлтор");
                return false;
            }
            if (($('#DwellingType').val() == "0") || ($('#Rooms').val() == "0")) {
                showWarning("Не заполнены поля: \"Тип жилого помещения\" или \"Кол-во комнат\"");
                return false;
            }
            if (
                    ($('#apartamentCity').val() == "") ||
                    ($('#apartamentStreet').val() == "") ||
                    ($('#apartamentBuilding').val() == "")
                    ) {
                showWarning("Неправильно заполнен адрес объекта");
                return false;
            }
            $.ajax({
                type: "POST",
                url: "api/apartament/addapartament",
                data: ({
                    typeofsales: $('#TypeOfSales').val(),
                    cityName: $('#apartamentCity').val(),
                    streetName: $('#apartamentStreet').val(),
                    houseNumber: $('#apartamentBuilding').val(),
                    buildingNumber: "",
                    rooms: $('#Rooms').val(),
                    dwellingType: $('#DwellingType').val(),
                    //FIXME!!
                    kladrId: $('#Price').val(),
                    shortAddress: $('#address').text(),
                    apartamentLan: $("#apartamentLan").text(),
                    apartamentLon: $("#apartamentLon").text(),
                    price: $('#Price').val(),
                    citydistrict: $('#CityDistrict').val(),
                    floor: $('#Floor').val(),
                    floors: $('#Floors').val(),
                    roomnumber: $('#RoomNumber').val(),
                    material: $('#Material').val(),
                    sizeapartament: $('#SizeApartament').val(),
                    sizeliving: $('#SizeLiving').val(),
                    sizekitchen: $('#SizeKitchen').val(),
                    balcony: $('#Balcony').val(),
                    loggia: $('#Loggia').val(),
                    yearofconstruction: $('#YearOfConstruction').val(),
                    description: $('#Description').val(),
                    puresale: $('#PureSale').prop("checked"),
                    mortgage: $('#Mortgage').prop("checked"),
                    exchange: $('#Exchange').prop("checked"),
                    rent: $('#Rent').prop("checked"),
                    replanning: $('#RePlanning').prop("checked"),
                    idWorkerTarget: $("#ApartamentsIdWorkerTarget").val(),
                    idCustomer: $("#IdCustomer").val(),
                    status: $("#ApartamentStatus").val()
                }),
                success: function (data) {
                    bootbox.confirm("Объект добавлен", function (result) {
                        //FIXME! /list/all
                        document.location.href = "#apartaments/list/price";
                        location.reload();
                    });
                },
                error: function (data) {
                    showDanger(data.responseText);
                }
            });
        });
    });
}

function mapInit() {
    var $region = $('[name="region"]'),
            $district = $('[name="district"]'),
            $city = $('[name="city"]'),
            $street = $('[name="street"]'),
            $building = $('[name="building"]');

    var map = null,
            map_created = false;

    $.kladr.setDefault({
        parentInput: '.js-form-address',
        verify: true,
        labelFormat: function (obj, query) {
            var label = '';

            var name = obj.name.toLowerCase();
            query = query.name.toLowerCase();

            var start = name.indexOf(query);
            start = start > 0 ? start : 0;

            if (obj.typeShort) {
                label += obj.typeShort + '. ';
            }

            if (query.length < obj.name.length) {
                label += obj.name.substr(0, start);
                label += '<strong>' + obj.name.substr(start, query.length) + '</strong>';
                label += obj.name.substr(start + query.length, obj.name.length - query.length - start);
            } else {
                label += '<strong>' + obj.name + '</strong>';
            }

            if (obj.parents) {
                for (var k = obj.parents.length - 1; k > -1; k--) {
                    var parent = obj.parents[k];
                    if (parent.name) {
                        if (label)
                            label += '<small>, </small>';
                        label += '<small>' + parent.name + ' ' + parent.typeShort + '.</small>';
                    }
                }
            }

            return label;
        },
        select: function (obj) {
            setLabel($(this), obj.type);
            log(obj);
            addressUpdate();
            mapUpdate();
        },
        check: function (obj) {
            if (obj) {
                setLabel($(this), obj.type);
            }

            log(obj);
            addressUpdate();
            mapUpdate();
        }
    });

    $region.kladr('type', $.kladr.type.region);
    $district.kladr('type', $.kladr.type.district);
    $city.kladr('type', $.kladr.type.city);
    $street.kladr('type', $.kladr.type.street);
    $building.kladr('type', $.kladr.type.building);

    // Включаем получение родительских объектов для населённых пунктов
    $city.kladr('withParents', true);

    // Отключаем проверку введённых данных для строений
    $building.kladr('verify', false);

    ymaps.ready(function () {
        if (map_created)
            return;
        map_created = true;

        map = new ymaps.Map('map', {
            center: [54.989342, 73.368212],
            zoom: 12,
            controls: []
        });

        map.controls.add('zoomControl', {
            position: {
                right: 10,
                top: 10
            }
        });
    });

    function setLabel($input, text) {
        text = text.charAt(0).toUpperCase() + text.substr(1).toLowerCase();
        $input.parent().find('label').text(text);
    }

    function mapUpdate() {
        var zoom = 4;

        var address = $.kladr.getAddress('.js-form-address', function (objs) {
            var result = '',
                    name = '',
                    type = '';

            for (var i in objs) {
                if (objs.hasOwnProperty(i)) {
                    if ($.type(objs[i]) === 'object') {
                        name = objs[i].name;
                        type = ' ' + objs[i].type;
                    }
                    else {
                        name = objs[i];
                        type = '';
                    }

                    if (result)
                        result += ', ';
                    result += type + name;

                    switch (objs[i].contentType) {
                        case $.kladr.type.region:
                            zoom = 4;
                            break;

                        case $.kladr.type.district:
                            zoom = 7;
                            break;

                        case $.kladr.type.city:
                            zoom = 12;
                            break;

                        case $.kladr.type.street:
                            zoom = 14;
                            break;

                        case $.kladr.type.building:
                            zoom = 16;
                            break;
                    }
                }
            }

            return result;
        });

        if (address && map_created) {
            var geocode = ymaps.geocode(address);
            geocode.then(function (res) {
                map.geoObjects.each(function (geoObject) {
                    map.geoObjects.remove(geoObject);
                });

                var position = res.geoObjects.get(0).geometry.getCoordinates(),
                        placemark = new ymaps.Placemark(position, {}, {});
                $("#apartamentLan").text(position[0]);
                $("#apartamentLon").text(position[1]);
                map.geoObjects.add(placemark);
                map.setCenter(position, zoom);
            });
        }
    }

    function addressUpdate() {
        var address = $.kladr.getAddress('.js-form-address');

        $('#address').text(address);
    }

    function log(obj) {
        var $log, i;

        $('.js-log li').hide();

        for (i in obj) {
            $log = $('#' + i);

            if ($log.length) {
                $log.find('.value').text(obj[i]);
                $log.show();
            }
        }
    }
}