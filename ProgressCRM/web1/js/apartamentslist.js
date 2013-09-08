function getapartamentsListPage() {
    $.get("apartamentslist.html", function(data) {
        $.get("api/auth/validate", function(data) {
            if ((data == "3") || (data == "2")) {
                $("#addApartamentBtn").css("display", "block");
            } else {
                $("#addApartamentBtn").css("display", "none");
            }
        });
        $("#mainContainer").html(data);
        $.ajax({
            type: "GET",
            url: "api/apartament/getallapartament",
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "";
                array.forEach(function(entry) {
                    str += "<div class = \"media\">";
                    str += "<a class = \"pull-left\" href = \"#\">";
                    str += "<img class=\"media-object\" src=\"js/lib/highslide/images/thumbstrip24.thumb.png\" alt=\"...\">";
                    str += "</a>";
                    str += "<div class=\"media-body\">";
                    str += "<h4 class=\"media-heading\">"
                            + entry.apartaments.streetName + " "
                            + entry.apartaments.houseNumber + " "
                            + entry.apartaments.buildingNumber + " - "
                            + entry.apartaments.price;
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