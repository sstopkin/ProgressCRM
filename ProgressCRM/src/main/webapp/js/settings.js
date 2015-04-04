function getSettingsPage() {
    $.get("settings.html", function (data) {
        $("#mainContainer").html(data);
    })
}