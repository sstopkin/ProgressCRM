function initSearchForm(type) {
    if (type === "undefined") {
        $("#mainSearchContainer").html("");
    }
    else {
        $.get("search.html", function(data) {
            $("#mainSearchContainer").html(data);
//        $('#announcementsSearchStartDate').val(year + "-" + month + "-" + day);timeConverter(new Date().getTime(), true)
            $('#mainSearchStartDate').datepicker({
                format: "yyyy-mm-dd",
                todayBtn: "linked",
                language: "ru",
                autoclose: true,
                todayHighlight: true
            });
//        $('#announcementsSearchEndDate').val(year + "-" + month + "-" + day);timeConverter(new Date().getTime(), true)
            $('#mainSearchEndDate').datepicker({
                format: "yyyy-mm-dd",
                todayBtn: "linked",
                language: "ru",
                autoclose: true,
                todayHighlight: true
            });
            $("#mainSearchAuthor").append('<option value="">Все</option>');
            workersList.forEach(function(entry) {
                $("#mainSearchAuthor").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
            });
            $("#mainSearchAssigned").append('<option value="">Все</option>');
            workersList.forEach(function(entry) {
                $("#mainSearchAssigned").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
            });
            $("#mainSearchActionButtonDiv").html("<input onclick=\"mainSearchAction('" + type + "');\" type=\"button\" class=\"btn btn-primary pull-right\" value=\"Поиск\">");
        });
    }
}

function mainSearchAction(type) {
    var permissions = $.ajax({
        type: "GET",
        url: "api/auth/validate",
        async: false
    }).responseText;
    $.ajax({
        type: "GET",
        url: "api/search/search?assigned=" + $("#mainSearchAssigned").val() +
                "&idworker=" + $("#mainSearchAuthor").val() +
                "&startdate=" + $("#mainSearchStartDate").val() +
                "&enddate=" + $("#mainSearchEndDate").val() +
                "&contains=" + $("#mainSearchContains").val() +
                "&type=" + type,
        success: function(data) {
            $("#errorBlock").css("display", "none");
            drawApartamentsListTable(permissions, data);
        },
        error: function(data) {
            showDanger(data.responseText);
            return false;
        }
    });
}