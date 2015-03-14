function getWorkersViewPage(workerId) {
    $.get("customersview.html", function(data) {
        $("#mainContainer").html(data);
        var content = "<p>";
        content += "<b>Общая информация: </b>"; //array.clientDescription
        content += "</p>";
        $.ajax({
            type: "GET",
            url: "api/workers/getworker?id=" + workerId,
            async: false,
            success: function(data) {
//                var array = JSON.parse(data);
//                content += "<p>";
//                content += "ID: " + array.id;
//                content += "</p>";
//                content += "<p>";
//                content += "ФИО: " + array.customersLname + " " + array.customersMname + " " + array.customersFname;
//                content += "</p>";
//                content += "<p>";
//                content += "Адрес: " + array.customersAddress;
//                content += "</p>";
//                content += "<p>";
//                content += "Дата рождения: " + array.customersDayOfBirthday + " " + array.customersMonthOfBirthday + " " + array.customersYearOfBirthday;
//                content += "</p>";
//                content += "<p>";
//                content += "Телефон: " + array.customersPhone;
//                content += "</p>";
//                content += "<p>";
//                content += "E-mail: " + array.customersEmail;
//                content += "</p>";
//                content += "<p>";
//                content += "Пол: ";
//                switch (array.customersSex) {
//                    case "1":
//                        content += "Мужской";
//                        break;
//                    case "2":
//                        content += "Женский";
//                        break;
//                    default:
//                        content += "Не указан";
//                        break;
//                }
//                content += "</p>";
//                content += "<p>";
//                content += "Дополнительно: " + array.customersExtra;
//                content += "</p>";
            }
        });
        content += "</p>";
        $("#customersInfo").html(content);
        $.ajax({
            type: "GET",
            url: "api/workers/getworkerobjects?id=" + workerId,
            async: false,
            success: function(data) {
                var array = JSON.parse(data);
                var str = "<b>Объекты собственника: </b>"; //array.clientDescription
                str += '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="workerObjectsListTable">';
                str += "<thead>";
                str += "<tr>";
                str += "<th>#</th>";
                str += "<th>Адрес</th>";
                str += "<th>Площадь О/К/Ж</th>";
                str += "<th>Этаж</th>";
                str += "<th>Цена</th>";
                str += "<th>Автор</th>";
                str += "<th>Риэлтор</th>";
                str += "<th>Дата</th>";
                str += "</tr>";
                str += "</thead>";
                str += "<tbody>";
                array.forEach(function(entry) {
                    str += "<tr>";
                    str += "<td><a href=\"#apartaments/view/" + entry.id + "\" class=\"btn btn-primary\"><b>" + entry.id + "</b></a></td>";
                    str += "<td>" + entry.cityName + " "
                            + entry.streetName + " "
                            + entry.houseNumber + " "
                            + entry.buildingNumber + "</td>";
                    str += "<td>" + entry.sizeApartament + " / " + entry.sizeKitchen + " / " + entry.sizeLiving + "</td>";
                    str += "<td>" + entry.floor + " / " + entry.floors + "</td>";
                    str += "<td>" + entry.price + "</td>";
                    str += "<td>" + getWorkersFullNameById(entry.idWorker) + "</td>";
                    str += "<td>" + getWorkersFullNameById(entry.idWorkerTarget) + "</td>";
                    str += "<td>" + timeConverter(entry.сreationDate,'human') + "</td>";
                });
                str += "</tbody>";
                $("#customersObjects").html(str);
                $('#workerObjectsListTable').dataTable();
            }
        });
    });
}