function getHelpDeskPage() {
    $("#addApartaments").css("display", "none");
    $.get("hd.html", function(data) {
        $("#mainContainer").html(data);
        $.ajax({
            type: "GET",
            url: "api/helpdesk/getallrequest",
            success: function(data) {
                $("#errorBlock").css("display", "none");
                var array = JSON.parse(data);
                var str = "";
                array.forEach(function(entry) {
                    str += "<div class = \"media\">";
                    str += "<a class = \"pull-left\" href = \"#\">";
                    str += "<img class=\"media-object\" src=\"images/IT-Icon.png\" alt=\"...\">";
                    str += "</a>";
                    str += "<div class=\"media-body\">";
                    str += "<h4 class=\"media-heading\">"
                            + entry.request
                            + entry.creationDate + "</p>";
                    str += "</h4>";
                    str += "<a href=\"#\" onclick=\"return alert(\'" + entry.id + " \')\">ссылка</a>";
                    str += "</div>";
                    str += "</div>";
                });
//                console.log(array.apartaments.IsApproved);
//                console.log(array.apartaments.deleted);
//
//
//                content += "<p>";
//                content += "ID = " + array.apartaments.id;
//                content += "</p>";


                $("#mainHelpDeskContainer").html(str);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function addHelpDeskRequest() {
//    alert($('#hdRequest').val());
//    alert($('#hdText').val());
    $('#myModal').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/helpdesk/addrequest",
        data: ({
            request: $('#hdRequest').val(),
            text: $('#hdText').val(),
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            getHelpDeskPage();
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}