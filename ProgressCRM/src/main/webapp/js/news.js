function getNews() {
    var str = "";
    $.get("api/news", function(data) {

        var array = JSON.parse(data);
        array.forEach(function(entry) {
            str += "<div class=\"panel panel-info\">";
            str += "<div class=\"panel-heading\">#" + entry.id + " | " + "<b>" + entry.header + "</b>" + " | " + entry.lastModify + "</div>";
            str += "<div class=\"panel-body\">";
            str += "<div class=\"media-body\">";
            if (permissions == "3") {
                str += "<button type=\"button\" onclick=\"confirmActionDelete('deleteNewsById(" + entry.id + ")');\" class=\"btn btn-danger pull-right\"><span class=\"glyphicon glyphicon-remove\"></span></button>";
                str += "<button type=\"button\" onclick=\"editNewsById(" + entry.id + ");\" class=\"btn btn-warning pull-right\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
            }
            str += "<p>" + entry.text + "</p>";
            for (var it = 0; it < workersList.length; ++it) {
                var a = workersList[it];
                if (entry.idWorker == a[0]) {
                    str += "<p><i>" + a[3] + " " + a[1] + "</i></p>";
                }
            }
            str += "</div>";
            str += "</div>";
            str += "</div>";
            str += "</div>";
        });

        $("#news").html(str);
    }).fail(function(data) {
        showDanger(data.responseText);
    });
}

function  editNewsById(id) {
    alert(editNewsById + " " + id);
}

function addNews() {
    $.get("/templates/modal_news.html", function(some_html) {
        var box = bootbox.dialog({
            show: false,
            title: "<h4 class=\"modal-title\">Добавить новость</h4></div>",
            message: some_html,
            buttons: {
                success: {
                    label: "Добавить новость",
                    className: "btn-success",
                    callback: function() {
                        $.ajax({
                            type: "POST",
                            url: "api/news/addnews",
                            data: ({
                                header: $('#newsHeader').val(),
                                text: $('#newsText').val()
                            }),
                            success: function() {
                                $("#errorBlock").css("display", "none");
                                location.reload();//FIXME
                            },
                            error: function(data) {
                                showDanger(data.responseText);
                            }
                        });
                    }
                },
                danger: {
                    label: "Отмена",
                    className: "btn-danger",
                    callback: function() {
                    }
                }
            }
        });

//    box.on("shown.bs.modal", function() {
//
//    });
        box.modal('show');
    }, 'html');
}