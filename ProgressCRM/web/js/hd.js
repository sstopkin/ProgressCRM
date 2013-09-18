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
                var content = "";
//
//                console.log(array.apartamentsPhotosList);
//
//                console.log(array.apartaments.IsApproved);
//                console.log(array.apartaments.deleted);
//
//
//                content += "<p>";
//                content += "ID = " + array.apartaments.id;
//                content += "</p>";


                $("#mainHelpDeskContainer").html(content);
            },
            error: function(data) {
                showDanger(data.responseText);
                return false;
            }
        });
    });
}

function addHelpDeskRequest() {

}