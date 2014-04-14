function initSearchForm() {
    $.get("search.html", function(data) {
        $("#mainSearchContainer").html(data);
        var date = new Date();
        var day = date.getDate();
        day = (parseInt(day, 10) < 10) ? ('0' + day) : (day);
        var month = date.getMonth() + 1;
        var year = date.getFullYear();
//        $('#announcementsSearchStartDate').val(year + "-" + month + "-" + day);
        $('#mainSearchStartDate').datepicker({
            format: 'yyyy-mm-dd'
        });
//        $('#announcementsSearchEndDate').val(year + "-" + month + "-" + day);
        $('#mainSearchEndDate').datepicker({
            format: 'yyyy-mm-dd'
        });
        $("#mainSearchAuthor").append('<option value="">Все</option>');
        workersList.forEach(function(entry) {
            $("#mainSearchAuthor").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
        });
        $("#mainSearchAssigned").append('<option value="">Все</option>');
        workersList.forEach(function(entry) {
            $("#mainSearchAssigned").append('<option value="' + entry[0] + '">' + entry[1] + " " + entry[2] + " " + entry[3] + '</option>');
        });
    });
}

function mainSearchAction() {
    var permissions = $.ajax({
        type: "GET",
        url: "api/auth/validate",
        async: false
    }).responseText;
    if ((permissions === "3") || (permissions === "2")) {
        $("#addApartamentBtn").css("display", "block");
        $("#genApartamentsPriceBtn").css("display", "block");
    } else {
        $("#addApartamentBtn").css("display", "none");
        $("#genApartamentsPriceBtn").css("display", "none");
    }
    $.ajax({
        type: "GET",
        url: "api/search/search?assigned=" + $("#mainSearchAssigned").val() +
                "&idworker=" + $("#mainSearchAuthor").val() +
                "&startdate=" + $("#mainSearchStartDate").val() +
                "&enddate=" + $("#mainSearchEndDate").val() +
                "&contains=" + $("#mainSearchContains").val() +
                "&type=apartaments",
        success: function(data) {
            $("#errorBlock").css("display", "none");
            drawTable(permissions,data);
        },
        error: function(data) {
            showDanger(data.responseText);
            return false;
        }
    });
}