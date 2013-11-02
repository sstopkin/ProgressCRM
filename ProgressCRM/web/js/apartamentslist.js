function getapartamentsListPage() {
    $.get("apartamentslist.html", function(data) {
        $.get("api/auth/validate", function(data) {
            if ((data == "3") || (data == "2")) {
                $("#addApartamentBtn").css("display", "block");
                $("#genApartamentsPriceBtn").css("display", "block");
            } else {
                $("#addApartamentBtn").css("display", "none");
                $("#genApartamentsPriceBtn").css("display", "none");
            }
        });
        $("#mainContainer").html(data);
        var permissions;
        var userId;
        $.get("api/auth/validate", function(data3) {
            permissions = data3;
            if (permissions == "3") {
                $("#approvingTagsForTasks").css("display", "block");
            }
        });
        $.get("api/auth/author", function(data2) {
            userId = data2;
        });
        $.ajax({
            type: "GET",
            url: "api/apartament/getallapartament",
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "";
                var flag1 = false;
                var flag2 = false;
                var flag3 = false;
                var flag4 = false;
                var flag5 = false;

                array.forEach(function(entry) {

                    str += "<H3>";
                    switch (entry.apartaments.rooms) {
                        case 1:
                            if (flag1 == false) {
                                str += "1-комнатные";
                                flag1 = true;
                            }
                            break;
                        case 2:
                            if (flag2 == false) {
                                str += "2-комнатные";
                                flag2 = true;
                            }
                            break;
                        case 3:
                            if (flag3 == false) {
                                str += "3-комнатные";
                                flag3 = true;
                            }
                            break;
                        case 4:
                            if (flag4 == false) {
                                str += "4-комнатные";
                                flag4 = true;
                            }
                            break;
                        case 5:
                            if (flag5 == false) {
                                str += "5-комнатные";
                                flag5 = true;
                            }
                            break;
                    }
                    str += "</H3>";
                    str += "<div class = \"media\">";
                    if (permissions == "3") {
                        str += "<div class=\"btn-toolbar\">";
                        str += "<div class=\"btn-group\">";

                        str += "<button type=\"button\" onclick=\"apartamentsDeleteById(" + entry.apartaments.id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";

                        str += "</div>";
                        str += "</div>";
                    }
                    str += "</div>";
                    str += "<a class = \"pull-left\" href = \"#\">";
                    str += "<img class=\"media-object\" src=\"images/home.png\" alt=\"...\">";
                    str += "</a>";
                    str += "<div class=\"media-body\">";
                    str += "<h4 class=\"media-heading\">"
                            + entry.apartaments.id + " "
                            + entry.apartaments.rooms + " "
                            + entry.apartaments.cityName + " "
                            + entry.apartaments.streetName + " "
                            + entry.apartaments.houseNumber + " "
                            + entry.apartaments.buildingNumber + " - "
                            + entry.apartaments.roomNumber + " "
                            + entry.apartaments.sizeApartament + "/"
                            + entry.apartaments.sizeLiving + "/"
                            + entry.apartaments.sizeKitchen + " "
                            + "<p>Цена: " + entry.apartaments.price + "</p>";
                    str += "</h4>";
                    str += "<a href=\"#\" onclick=\"return getApartamentViewPage(\'" + entry.apartaments.id + " \')\">ссылка</a>";
                    str += "</div>";
                    str += "</div>";
                });

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