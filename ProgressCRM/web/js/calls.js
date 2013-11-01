function submitCall() {
//    $.ajax({
//        type: "GET",
//        url: "api/apartament/getapartament?id=" + $("#submitCallId").val(),
//        success: function(data) {
//            $("#errorBlock").css("display", "none");
//            var array = JSON.parse(data);
//            $("#callFindApartamentsResult").text(array.apartaments.cityName + " " + array.apartaments.streetName
//                    + " " + array.apartaments.houseNumber + " " + array.apartaments.buildingNumber);
//            
//        }
//    });
    $.ajax({
        type: "POST",
        url: "api/calls/addcall",
        data: ({
            id: $("#submitCallId").val(),
            description: $('#submitCallDescription').val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            $('#addCourseBtn').css('display', 'block');
            location.reload();//FIXME
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}
