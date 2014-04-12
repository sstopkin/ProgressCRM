function initSearchForm() {
    $.get("search.html", function(data) {
        $("#mainSearchContainer").html(data);
    });
}