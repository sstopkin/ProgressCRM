function getUsersManagementList() {
    $.ajax({
        type: "GET",
        url: "api/workers/getallworkers",
        success: function (data) {
            var list = JSON.parse(data);
            var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="usersListTable">';
            str += "<thead class='t-header'><tr>";
            str += "<th class=\"col-md-1\">id</th>";
            str += "<th class=\"col-md-1\">Email</th>";
            str += "<th class=\"col-md-1\">ФИО</th>";
            str += "<th class=\"col-md-1\">Статус</th>";
            str += "</tr></thead>";
            str += "<tbody class='t-cell'>";
            for (var j = 0; j < list.length; ++j) {
                str += "<tr><td>";
                str += "<a href=\"#workers/view/" + list[j][0] + "\" class=\"btn btn-primary\"><b>" + list[j][0] + "</b></a>";
                str += "</td><td>";
                str += list[j][1];
                str += "</td><td>";
                str += list[j][2] + ' ' + list[j][3] + ' ' + list[j][4];
                str += "</td><td>";
                if (list[j][6] == true) {
                    str += '<a href=\"\" onclick=\"return banUser(' + list[j][0] + ');\"><span class=\"label label-success\"><i class="fa fa-user fa-fw"></i> Активен</span></a>';
                } else {
                    str += '<a href=\"\" onclick=\"return unBanUser(' + list[j][0] + ');\"><span class=\"label label-danger\"><i class="fa fa-user fa-fw"></i> Заблокирован</span></a>';
                }
                str += "</td></tr>";
            }
            str += "</tbody></table>";
            $("#adminTab1").html(str);
            $('#usersListTable').dataTable();
        },
        error: function (data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

function getUsersGroupsManagementList() {
    var arrGroupsList;

    $.when(
            $.ajax({
                type: "GET",
                url: "api/groups/getallgroups",
                success: function (data) {
                    arrGroupsList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            })
            ).then(function () {
        var str = '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="usersListTable">';
        str += "<thead class='t-header'><tr>";
        str += "<th class=\"col-md-1\">id</th>";
        str += "<th class=\"col-md-1\">Название группы</th>";
        str += "<th class=\"col-md-1\">Дата создания</th>";
        str += "</tr></thead>";
        str += "<tbody class='t-cell'>";
        arrGroupsList.forEach(function (entity) {
            str += "<tr><td>";
            str += "<a href=\"#workers/view/" + entity.id + "\" class=\"btn btn-primary\"><b>" + entity.id + "</b></a>";
            str += "</td><td>";
            str += entity.workersGroupName;
            str += "</td><td>";
            str += timeConverter(entity.creationDate, 'human');
            str += "</td></tr>";
        });
        str += "</tbody></table>";
        $("#adminTab2").html(str);
        $('#usersListTable').dataTable();
    });
}

function getUsersInGroupsManagementList() {
    var arrGroupsList;
    var arrWorkersList;

    $.when(
            $.ajax({
                type: "GET",
                url: "api/groups/getallgroups",
                success: function (data) {
                    arrGroupsList = JSON.parse(data);
                    console.log(arrGroupsList)
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/workers/getallworkers",
                success: function (data) {
                    arrWorkersList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            })
            ).then(function () {
//                '<div class="form-group">'
//                '<label>Multiple Selects</label>'
//                    '<select multiple="" class="form-control">'
//                '<option>1</option>'
//                '<option>2</option>'
//                '<option>3</option>'
//                    '<option>4</option>'
//        '<option>5</option>'
//                    '</select>'
//        '</div>'



        var str = '<input type="button" class="btn btn-success pull-right" id="addUserGroupBtn" onclick="addGroup()" value="Добавить группу" />';
        str += '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="usersListTable">';
        str += "<thead class='t-header'><tr>";
        str += "<th class=\"col-md-1\">id</th>";
        str += "<th class=\"col-md-1\">Название группы</th>";
        str += "<th class=\"col-md-1\">Дата создания</th>";
        str += "<th class=\"col-md-1\">Статус</th>";
        str += "</tr></thead>";
        str += "<tbody class='t-cell'>";
        arrGroupsList.forEach(function (entity) {
            str += "<tr><td>";
            str += "<a href=\"#workers/view/" + entity.id + "\" class=\"btn btn-primary\"><b>" + entity.id + "</b></a>";
            str += "</td><td>";
            str += entity.workersGroupName;
            str += "</td><td>";
            str += timeConverter(entity.creationDate, 'human');
            str += "</td><td>";
            if (entity.deleted == true) {
                str += "<a href=\"\" onclick=\"return banUser(" + entity.id + ");\"><span class=\"label label-success\">Активен</span></a>";
            } else {
                str += "<a href=\"\" onclick=\"return unBanUser(" + entity.id + ");\"><span class=\"label label-danger\">Заблокирован</span></a>";
            }
            str += "</td></tr>";
        });
        str += "</tbody></table>";
        $("#adminContent").html(str);
        $('#usersListTable').dataTable();
    });
    return false;
}

function getPermissionManagementList() {
    var arrWorkersList;
    var arrEntities;
    var arrAclList;
    var arrAccessCategoriesList;
    var arrAccessTypesList;
    $.when(
            $.ajax({
                type: "GET",
                url: "api/workers/getallworkers",
                success: function (data) {
                    arrWorkersList = JSON.parse(data);
                }, error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/acl/getentities",
                success: function (data) {
                    arrEntities = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/acl/getacllist",
                success: function (data) {
                    arrAclList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/acl/getaccesscategories",
                success: function (data) {
                    arrAccessCategoriesList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/acl/getaccesstypes",
                success: function (data) {
                    arrAccessTypesList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }}))
            .then(function () {
                var str = '<input type="button" class="btn btn-success pull-right" id="setPermissionsButton" onclick="setPermissionManagementList()" value="Сохранить изменения" />';
                str += '<table class="table table-striped table-bordered" cellspacing="0" width="100%" id="permissionListTable">';
                str += '<thead class="t-header"><tr>';
                str += '<th class="col-md-1">ФИО</th>';
                arrEntities.forEach(function (entry) {
                    str += '<th class="col-md-1">' + entry.entityName + '</th>';
                });
                str += '</tr></thead>';
                str += '<tbody class="t-cell">';
                for (var j = 0; j < arrWorkersList.length; ++j) {
                    str += "<tr><td>";
                    str += arrWorkersList[j][2] + " " + arrWorkersList[j][3] + " " + arrWorkersList[j][4];
                    str += "</td>";
                    arrEntities.forEach(function (entity) {
                        str += "<td>";
                        arrAccessTypesList.forEach(function (type) {
                            var idWorker = arrWorkersList[j][0];
                            var idAccessType = type.id;
                            str += '<p><input type="checkbox" id="aclAccessType_' + idWorker + "_" + entity.id + "_" + idAccessType + '">' + type.accessTypeName;
                            str += '<br>';
                            str += '<input type="hidden" value="-1" id="aclAccessTypeId_' + idWorker + "_" + entity.id + "_" + idAccessType + '">';
                            str += '<select id="aclAccessCategories_' + idWorker + "_" + entity.id + "_" + idAccessType + '" class="form-control">';
                            str += '<option selected="selected" value="-1"></option>';
                            arrAccessCategoriesList.forEach(function (category) {
                                str += '<option value="' + category.id + '">' + category.categoryName + '</option>';
                            });
                            str += '</select>';
                        });
                        str += "</td>";
                    });
                    str += "</tr>";
                }
                str += "</tbody></table>";
                $("#adminContent").html(str);
                arrAclList.forEach(function (entry) {
                    var idWorker = entry.idWorker;
                    var idAccessType = entry.idAccessType;
                    var idEntity = entry.idEntity;
                    var idAccessCategory = entry.idAccessCategory;
                    $('#aclAccessType_' + idWorker + "_" + idEntity + "_" + idAccessType).prop("checked", true);
                    $('#aclAccessCategories_' + idWorker + "_" + idEntity + "_" + idAccessType).val(idAccessCategory);
                    $('#aclAccessTypeId_' + idWorker + "_" + idEntity + "_" + idAccessType).val(entry.id);
                });
            });
}

function setPermissionManagementList() {
    var arrWorkersList;
    var arrEntities;
    var arrAclList = [];
    var arrAccessCategoriesList;
    var arrAccessTypesList;
    var error = false;
    $.when(
            $.ajax({
                type: "GET",
                url: "api/workers/getallworkers",
                success: function (data) {
                    arrWorkersList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/acl/getentities",
                success: function (data) {
                    arrEntities = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/acl/getaccesscategories",
                success: function (data) {
                    arrAccessCategoriesList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            }),
            $.ajax({
                type: "GET",
                url: "api/acl/getaccesstypes",
                success: function (data) {
                    arrAccessTypesList = JSON.parse(data);
                },
                error: function (data) {
                    showDanger(data.responseText);
                    checkStatus();
                    return false;
                }
            })
            )
            .then(function () {

                for (var j = 0; j < arrWorkersList.length; ++j) {
                    arrEntities.forEach(function (entity) {
                        arrAccessTypesList.forEach(function (type) {
                            var idWorker = arrWorkersList[j][0];
                            var idAccessType = type.id;
                            if ($('#aclAccessType_' + idWorker + "_" + entity.id + "_" + idAccessType).is(":checked")) {
                                var idCategory = parseInt($('#aclAccessCategories_' + idWorker + "_" + entity.id + "_" + idAccessType).val());
                                if (idCategory == -1) {
                                    error = true;
                                } else {
                                    var ACLid = parseInt($('#aclAccessTypeId_' + idWorker + "_" + entity.id + "_" + idAccessType).val());
                                    var acl = {id: ACLid, idWorker: idWorker, idEntity: entity.id, idAccessType: idAccessType, idAccessCategory: idCategory};
                                    arrAclList.push(acl);
                                }
                            }
                        });
                    });
                }
                if (error === false) {
                    $.ajax({
                        type: "POST",
                        url: "api/acl/setacllist",
                        data: ({acllist: JSON.stringify(arrAclList)}),
                        success: function (data) {
                            showSuccess("Сохранение прошло успешно.");
                            getPermissionManagementList();
                        },
                        error: function (data) {
                            showDanger(data.responseText);
                            checkStatus();
                            return false;
                        }
                    });
                }
                else {
                    showDanger("Не корректно заполнена таблица.");
                    return false;
                }
            });
}

function banUser(id) {
    $.ajax({
        type: "POST",
        url: "api/admin/banuser",
        data: ({id: id}),
        success: function (data) {
            getUsersManagementList();
        },
        error: function (data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

function unBanUser(id) {
    $.ajax({
        type: "POST", url: "api/admin/unbanuser",
        data: ({id: id}),
        success: function (data) {
            getUsersManagementList();
        },
        error: function (data) {
            showDanger(data.responseText);
            checkStatus();
            return false;
        }
    });
    return false;
}

function addUser() {
    $.get("/templates/modal_adduser.html", function (some_html) {
        var box = bootbox.dialog({show: false,
            title: "<h4 class=\"modal-title\">Добавить пользователя</h4></div>",
            message: some_html,
            buttons: {
                success: {
                    label: "Добавить пользователя",
                    className: "btn-success",
                    callback: function () {
                        $.ajax({
                            type: "POST",
                            url: "api/workers/addworker",
                            data: ({
                                lname: $('#plannerAddUserModalLName').val(),
                                fname: $('#plannerAddUserModalFName').val(),
                                mname: $('#plannerAddUserModalMName').val(),
                                email: $('#plannerAddUserModalEmail').val(),
                                password: $('#plannerAddUserModalPassword').val()
                            }),
                            success: function () {
                                $("#errorBlock").css("display", "none");
                                location.reload(); //FIXME
                            },
                            error: function (data) {
                                showDanger(data.responseText);
                            }
                        });
                    }
                },
                danger: {
                    label: "Отмена",
                    className: "btn-danger",
                    callback: function () {
                    }
                }
            }
        });
        box.on("shown.bs.modal", function () {

        });
        box.modal('show');
    }, 'html');
}

function addGroup() {
    $.get("/templates/modal_addgroup.html", function (some_html) {
        var box = bootbox.dialog({
            show: false,
            title: "<h4 class=\"modal-title\">Добавить группу</h4></div>",
            message: some_html,
            buttons: {
                success: {
                    label: "Добавить группу",
                    className: "btn-success",
                    callback: function () {
                        $.ajax({
                            type: "POST",
                            url: "api/groups/addgroup",
                            data: ({
                                groupname: $('#plannerAddGroupModalGroupName').val(),
                            }),
                            success: function () {
                                $("#errorBlock").css("display", "none");
                                location.reload(); //FIXME
                            },
                            error: function (data) {
                                showDanger(data.responseText);
                            }
                        });
                    }
                },
                danger: {
                    label: "Отмена",
                    className: "btn-danger",
                    callback: function () {
                    }
                }
            }
        });
        box.on("shown.bs.modal", function () {

        });
        box.modal('show');
    }, 'html');
}