$(document).ready(function () {
    $("#chngPwdBtn").click(function () {
        if ($("#chngPassword").val() == $("#chngPasswordCurrent").val()) {
            $("#chngPwdErr").html("Новый пароль не должен совпадать с текущим");
            return false;
        }
        if (($("#chngPassword").val() == "") || ($("#chngPassword2").val() == "")) {
            $("#chngPwdErr").html("Введите новый пароль");
            return false;
        }
        if ($("#chngPassword").val() == $("#chngPassword2").val()) {
            $.ajax({
                type: "POST",
                url: "api/auth/chngpwd",
                data: ({
                    oldpwd: $("#chngPasswordCurrent").val(),
                    newpwd: $("#chngPassword").val()
                }),
                success: function (data) {
                    $("#chngPasswordCurrent").val("");
                    $("#chngPassword").val("");
                    $("#chngPassword2").val("");
                    $("#chngPwdErr").html("");
                    $("#chngPwdModal").modal("hide");
                },
                error: function (data) {
                    $("#chngPwdErr").html(data.responseText);
                    return false;
                }
            });
        } else {
            $("#chngPwdErr").html("Введенные пароли не совпадают");
        }
    });
});

function getProfilePage() {
    $.get("profile.html", function (data) {
        $("#mainContainer").html(data);
//        $('#chngPwd').validate({
//            rules: {
//                oldPassword: {
//                    required: true,
//                    minlength: 6,
//                    maxlength: 50
//                },
//                password: {
//                    required: true,
//                    minlength: 6,
//                    maxlength: 50
//                },
//                password2: {
//                    equalTo: '#chngPassword'
//                }
//            },
//            highlight: function(element, errorClass, validClass) {
//                $(element).closest('.form-group').removeClass('has-success').addClass('has-error');
//            },
//            unhighlight: function(element, errorClass, validClass) {
//                $(element).closest('.form-group').removeClass('has-error').addClass('has-success');
//            },
//            success: function(label) {
//                $(label).closest('form').find('.valid').removeClass("invalid");
//            },
//            errorPlacement: function(error, element) {
//                error.text(element.closest('.form-group').find('.help-block'));
//            }
//        });
        $.ajax({
            type: "GET",
            url: "api/auth/info",
            success: function (data) {
                $("#errorBlock").css("display", "none");
                $("#tags").css("display", "none");
                $("#userProfile").css("display", "block");

                var value = JSON.parse(data);
                $("#profileEmail").html(value.email);
                $("#profileFName").html(value.fName);
                $("#profileMName").html(value.mName);
                $("#profileLName").html(value.lName);
            },
            error: function (data) {
                showDanger(data.responseText);
                return false;
            }
        });
        $.get("templates/calendar.html", function (data) {
            $("#profileCalendar").html(data);
            initCalendar('/api/planner/all');
            $('#fullCalendar').fullCalendar({
                header: {
                    left: 'prev,next today',
                    center: 'title',
                    right: 'month,basicWeek,basicDay'
                },
                defaultDate: '2014-11-12',
                editable: true,
                eventLimit: true, // allow "more" link when too many events
                events: [
                    {
                        title: 'All Day Event',
                        start: '2014-11-01'
                    },
                    {
                        title: 'Long Event',
                        start: '2014-11-07',
                        end: '2014-11-10'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2014-11-09T16:00:00'
                    },
                    {
                        id: 999,
                        title: 'Repeating Event',
                        start: '2014-11-16T16:00:00'
                    },
                    {
                        title: 'Conference',
                        start: '2014-11-11',
                        end: '2014-11-13'
                    },
                    {
                        title: 'Meeting',
                        start: '2014-11-12T10:30:00',
                        end: '2014-11-12T12:30:00'
                    },
                    {
                        title: 'Lunch',
                        start: '2014-11-12T12:00:00'
                    },
                    {
                        title: 'Meeting',
                        start: '2014-11-12T14:30:00'
                    },
                    {
                        title: 'Happy Hour',
                        start: '2014-11-12T17:30:00'
                    },
                    {
                        title: 'Dinner',
                        start: '2014-11-12T20:00:00'
                    },
                    {
                        title: 'Birthday Party',
                        start: '2014-11-13T07:00:00'
                    },
                    {
                        title: 'Click for Google',
                        url: 'http://google.com/',
                        start: '2014-11-28'
                    }
                ]
            });
        });

    });
}