var transfer;
var type;
$(document).ready(function() {
    $("#closeAlert").click(function() {
        $("#errorBlock").css("display", "none");
    });
    $("#closeInfo").click(function() {
        $("#helpInfoBlock").css("display", "none");
        $('#taskContentHelp').addClass("hiddenHelp");
    });
    $("#addApartaments").css("display", "none");
    $.ajax({
        type: "GET",
        url: "api/auth",
        success: function(data) {
            $("#profileLink").html(data);
            $("#logged").css("display", "block");
            $.get("api/auth/validate", function(data3) {
                var permissions = data3;
            });
        },
        error: function(data) {
            $("#loginForm").css("display", "block");
        }
    });
    getMainPage();
    $("#mainLink").click(function(e) {
        e.preventDefault();
        getMainPage();
    });
    $("#apartamentsLink").click(function(e) {
        e.preventDefault();
        getapartamentsListPage();
    });
    $("#helpDeskLink").click(function(e) {
        e.preventDefault();
        getHelpDeskPage();
    });

    $("#tasksLink").click(function(e) {
        e.preventDefault();
        getTasksPage("task");
    });
    $('#adminLink').click(function(e) {
        e.preventDefault();
        getAdminPage("admin");
    });


    $("#aboutLink").click(function(e) {
        e.preventDefault();
        getAboutPage("about");
    });

    $("#callsLink").click(function(e) {
        e.preventDefault();
        getCallsPage();
    });
    $(function() {
        function log() {
            var str = "";

            plupload.each(arguments, function(arg) {
                var row = "";

                if (typeof (arg) != "string") {
                    plupload.each(arg, function(value, key) {
                        // Convert items in File objects to human readable form
                        if (arg instanceof plupload.File) {
                            // Convert status to human readable
                            switch (value) {
                                case plupload.QUEUED:
                                    value = 'QUEUED';
                                    break;

                                case plupload.UPLOADING:
                                    value = 'UPLOADING';
                                    break;

                                case plupload.FAILED:
                                    value = 'FAILED';
                                    break;

                                case plupload.DONE:
                                    value = 'DONE';
                                    break;
                            }
                        }

                        if (typeof (value) != "function") {
                            row += (row ? ', ' : '') + key + '=' + value;
                        }
                    });

                    str += row + " ";
                } else {
                    str += arg + " ";
                }
            });

            $('#log').append(str + "\n");
        }

        $("#uploader").pluploadQueue({
            // General settings
            runtimes: 'html5,gears,flash,silverlight,browserplus,html4',
            url: 'api/fileupload/photo',
            max_file_size: '10mb',
            chunk_size: '1mb',
            unique_names: true,
            // Resize images on clientside if we can
            resize: {width: 320, height: 240, quality: 90},
            // Specify what files to browse for
            filters: [
                {title: "Image files", extensions: "jpg,gif,png"},
                {title: "Zip files", extensions: "zip"}
            ],
            // Flash settings
            flash_swf_url: '/plupload/js/plupload.flash.swf',
            // Silverlight settings
            silverlight_xap_url: '/plupload/js/plupload.silverlight.xap',
            // PreInit events, bound before any internal events
            preinit: {
                Init: function(up, info) {
                    log('[Init]', 'Info:', info, 'Features:', up.features);
                },
                UploadFile: function(up, file) {
                    log('[UploadFile]', file);

                    // You can override settings before the file is uploaded
                    // up.settings.url = 'upload.php?id=' + file.id;
                    // up.settings.multipart_params = {param1 : 'value1', param2 : 'value2'};
                }
            },
            // Post init events, bound after the internal events
            init: {
                Refresh: function(up) {
                    // Called when upload shim is moved
                    log('[Refresh]');
                },
                StateChanged: function(up) {
                    // Called when the state of the queue is changed
                    log('[StateChanged]', up.state == plupload.STARTED ? "STARTED" : "STOPPED");
                },
                QueueChanged: function(up) {
                    // Called when the files in queue are changed by adding/removing files
                    log('[QueueChanged]');
                },
                UploadProgress: function(up, file) {
                    // Called while a file is being uploaded
                    log('[UploadProgress]', 'File:', file, "Total:", up.total);
                },
                FilesAdded: function(up, files) {
                    // Callced when files are added to queue
                    log('[FilesAdded]');

                    plupload.each(files, function(file) {
                        log('  File:', file);
                    });
                },
                FilesRemoved: function(up, files) {
                    // Called when files where removed from queue
                    log('[FilesRemoved]');

                    plupload.each(files, function(file) {
                        log('  File:', file);
                    });
                },
                FileUploaded: function(up, file, info) {
                    // Called when a file has finished uploading
                    log('[FileUploaded] File:', file, "Info:", info);
                },
                ChunkUploaded: function(up, file, info) {
                    // Called when a file chunk has finished uploading
                    log('[ChunkUploaded] File:', file, "Info:", info);
                },
                Error: function(up, args) {
                    // Called when a error has occured
                    log('[error] ', args);
                }
            }
        });
    });
});

function getMainPage() {
    $("#addApartaments").css("display", "none");
    $.get("main.html", function(data) {
        $("#mainContainer").html(data);
    });
    getNews();
}

function getAboutPage() {
    $("#addApartaments").css("display", "none");
    $.get("about.html", function(data) {
        $("#mainContainer").html(data);
    });
}

function getCallsPage() {
    $("#addApartaments").css("display", "none");
    $.get("calls.html", function(data) {
        $("#mainContainer").html(data);
    });
}

function getAdminPage() {
    $("#addApartaments").css("display", "none");
    $.get("api/auth/validate", function(data) {
        if (data == "3") {
            $.get("admin.html", function(data) {
                $("#mainContainer").html(data);
                $("#usersLink").click(getUsersList);
                $("#verifyTasksLink").click(getUnevaluatedTasks);
                $("#moderationLink").click(getModerationPage)
                getUsersList();
            });
        }
    });
}

function getNews() {
    var permissions;
    if (permissions == "3") {

    }
    $.ajax({
        type: "GET",
        url: "api/auth/validate",
        success: function(data) {
            permissions = data;
            $.get("api/news", function(data) {
                var str = "<table class=\"table\"><tbody>\n";
                var ids = [];
                var content = "course";
                var list = JSON.parse(data);
                for (var i = 0; i < list.length; ++i) {
                    ids[i] = list[i].id;
                    str += "<tr><td>";
                    str += "<h3>" + list[i].header + "</h3>";
                    str += "<div class=\"row\">";
                    str += "<div class=\"col-md-7 col-md-offset-1\">";
                    if (permissions == "3") {
                        str += "<div class=\"btn-toolbar\">";
                        str += "<div class=\"btn-group\">";

                        str += "<button type=\"button\" onclick=\"editNewsById(" + list[i].id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-pencil\"></span></button>";
                        str += "<button type=\"button\" onclick=\"deleteNewsById(" + list[i].id + ");\" class=\"btn btn-default\"><span class=\"glyphicon glyphicon-remove\"></span></button>";

                        str += "</div>";
                        str += "</div>";
                    }
                    str += "<p>" + list[i].text + "</p>";
                    str += "</div>";
                    str += "<p>" + list[i].lastModify + "</p>";
                    str += "</div>";
                    str += "</tr></td>";
                }
                str += "\n</tbody>\n</table>\n";
                transfer = ids;
                $("#news").html(str);
            });

        },
        error: function(data) {
            showWarning(data.responseText);
        }
    });
    $("#addApartaments").css("display", "none");
    return false;
}

function  editNewsById(id) {
    alert(editNewsById + " " + id);
}

function addNews() {
    $('#newsModal').modal('toggle');
    $.ajax({
        type: "POST",
        url: "api/news/addnews",
        data: ({
            header: $('#newsHeader').val(),
            text: $('#newsText').val()
        }),
        success: function(data) {
            $("#errorBlock").css("display", "none");
            getMainPage();
        },
        error: function(data) {
            showDanger(data.responseText);
        }
    });
}

function showDanger(message) {
    $("#errorBlock").addClass("alert-danger");
    $("#errorMessage").html(message);
    $("#errorBlock").css("display", "block");
}

function showWarning(message) {
    $("#errorBlock").addClass("alert-warning");
    $("#errorMessage").html(message);
    $("#errorBlock").css("display", "block");
}

function checkStatus() {
    $.ajax({
        type: "GET",
        url: "api/auth",
        success: function(data) {
            $("#profileLink").html(data);
            $("#logged").css("display", "block");
            $("#loginForm").css("display", "none");
//            $.get("api/auth/validate", function(data3) {
//                var permissions = data3;
//                if (permissions == "3") {
//                    $('#adminTabLink').css("display", "block");
//                }
//            });
        },
        error: function(data) {
            $("#loginForm").css("display", "block");
            $("#logged").css("display", "none");
//            $('#adminTabLink').css("display", "none");
        }
    });
}

function deleteNewsById(newsId) {
    $.ajax({
        type: "POST",
        url: "api/news/deletenews",
        data: ({id: newsId}),
        success: function(data) {
            getMainPage();
        },
        error: function(data) {
            $("#errorBlock").addClass("alert-danger");
            $("#errorMessage").html(data.responseText);
            $("#errorBlock").css("display", "block");
            checkStatus();
            return false;
        }
    });
    return false;
}