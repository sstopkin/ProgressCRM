function getCallsStatsPage() {
//    $.ajax({
//        type: "GET",
//        url: "api/workers/getallworkers",
//        success: function (data) {
//            var list = JSON.parse(data);
//            var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="usersListTable">';
//            str += "<thead class='t-header'><tr>";
//            str += "<th class=\"col-md-1\">id</th>";
//            str += "<th class=\"col-md-1\">Email</th>";
//            str += "<th class=\"col-md-1\">Фамилия</th>";
//            str += "<th class=\"col-md-1\">Имя</th>";
//            str += "<th class=\"col-md-1\">Отчество</th>";
//            str += "<th class=\"col-md-1\">Статус</th>";
//            str += "</tr></thead>";
//            str += "<tbody class='t-cell'>";
//
//            for (var j = 0; j < list.length; ++j) {
//                str += "<tr><td>";
//                str += "<a href=\"#workers/view/" + list[j][0] + "\" class=\"btn btn-primary\"><b>" + list[j][0] + "</b></a>";
//                str += "</td><td>";
//                str += list[j][1];
//                str += "</td><td>";
//                str += list[j][2];
//                str += "</td><td>";
//                str += list[j][3];
//                str += "</td><td>";
//                str += list[j][4];
//                str += "</td><td>";
//                if (list[j][6] == true) {
//                    str += "<a href=\"\" onclick=\"return banUser(" + list[j][0] + ");\"><span class=\"label label-success\">Активен</span></a>";
//                } else {
//                    str += "<a href=\"\" onclick=\"return unBanUser(" + list[j][0] + ");\"><span class=\"label label-danger\">Заблокирован</span></a>";
//                }
//                str += "</td></tr>";
//            }
//            str += "</tbody></table>";
//            $("#mainPageContainer").html(str);
//            $('#usersListTable').dataTable();
//        },
//        error: function (data) {
//            showDanger(data.responseText);
//            checkStatus();
//            return false;
//        }
//    });
//    return false;
}