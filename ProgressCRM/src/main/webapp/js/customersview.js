function getCustomerViewPage(customerId) {
    $.get("customersview.html", function(data) {
        $("#mainContainer").html(data);
        var content = "";
        $.ajax({
            type: "GET",
            url: "api/customers/getcustomer?id=" + customerId,
            async: false,
            success: function(data) {
                var array = JSON.parse(data);
                content += "<p>";
                content += "<b>Общая информация: </b>"; //array.clientDescription
                content += "</p>";
                content += array.customersFname + " ";
                content += array.customersLname + " ";
                content += array.customersMname + " ";
                content += array.customersPhone + " ";
                content += "</p>";
            }
        });
        $("#customersInfo").html(content);
        $.ajax({
            type: "GET",
            url: "api/customers/getcustomerobjects?id=" + customerId,
            async: false,
            success: function(data) {
                var array = JSON.parse(data);
                var content;
                content += "<p>";
                content += "<b>Общая информация: </b>"; //array.clientDescription
                content += "</p>";
                content += array.customersFname + " ";
                content += array.customersLname + " ";
                content += array.customersMname + " ";
                content += array.customersPhone + " ";
                content += "</p>";
                $("#customersObjects").html(array);
            }
        });
    });
}