function addApartament() {
    alert("asd");
    $("#addApartaments").css("display", "block");
    $("#errorBlock").css("display", "none");
    $("#apartamentAddReadyLink").css("display", "inline");
    $("#apartamentAddReadyLink").click(function() {
        if (
                ($('#TypeOfSales').val() == "")
                || ($('#Price').val() == "")
                || ($('#CityDistrict').val() == "")
                || ($('#Floor').val() == "")
                || ($('#Floors').val() == "")
                || ($('#Material').val() == "")
                || ($('#SizeApartament').val() == "")
                || ($('#SizeLiving').val() == "")
                || ($('#SizeKitchen').val() == "")
                || ($('#Balcony').val() == "")
                || ($('#Loggia').val() == "")
                || ($('#YearOfConstruction').val() == "")
                || ($('#Description').val() == "")
                || ($('#ClientPhone').val() == "")
                ) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html("Не все поля заполнены");
            $("#errorBlock").css("display", "block");
            return false;
        }
        var pursale;
        $("#PureSale").click(function() {
            if (document.getElementById("PureSale").checked) {
                pursale = true;
            } else {
                pursale = false;
            }
        });
        var mortgage;
        $("#Mortgage").click(function() {
            if (document.getElementById("Mortgage").checked) {
                mortgage = true;
            } else {
                mortgage = false;
            }
        });
        var exchange;
        $("#Exchange").click(function() {
            if (document.getElementById("Exchange").checked) {
                exchange = true;
            } else {
                exchange = false;
            }
        });
        var rent;
        $("#Rent").click(function() {
            if (document.getElementById("Rent").checked) {
                rent = true;
            } else {
                rent = false;
            }
        });
        var replanning;
        $("#RePlanning").click(function() {
            if (document.getElementById("RePlanning").checked) {
                replanning = true;
            } else {
                replanning = false;
            }
        });
        $.ajax({
            type: "POST",
            url: "api/apartament/addapartament",
            data: ({
                typeofsales: $('#TypeOfSales').val(),
                cityName: $('#apartamentCity').text(),
                streetName: $('#apartamentStreet').text(),
                houseNumber: $('#apartamentBuilding').text(),
                buildingNumber: $('#apartamentBuildingAdd').text(),
                kladrId: $('#Price').val(),
                price: $('#Price').val(),
                citydistrict: $('#CityDistrict').val(),
                floor: $('#Floor').val(),
                floors: $('#Floors').val(),
                material: $('#Material').val(),
                sizeapartament: $('#SizeApartament').val(),
                sizeliving: $('#SizeLiving').val(),
                sizekitchen: $('#SizeKitchen').val(),
                balcony: $('#Balcony').val(),
                loggia: $('#Loggia').val(),
                yearofconstruction: $('#YearOfConstruction').val(),
                description: $('#Description').val(),
                puresale: pursale,
                mortgage: mortgage,
                exchange: exchange,
                rent: rent,
                replanning: replanning,
                clientphone: $('#ClientPhone').val(),
                clientdescription: $('#ClientDescription').val(),
            }),
            success: function(data) {
                $("#errorBlock").css("display", "none");
                $('#addCourseBtn').css('display', 'block');
                getCoursesPage();
            },
            error: function(data) {
                showDanger(data.responseText);
            }
        });
    });
}