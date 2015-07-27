$(document).ready(function () {

    getExerciseAndFillTable('/exercises');
    getUsersAndFillTable('/users');

    // Add onclick listener
    performClickRemoveUser();
    performClickRemoveExercise();

    performClickAddUser();
    performClickAddExercise();

});

function getUsersAndFillTable(url) {

    $('#user-table').bootstrapTable({
        url: url,
        toolbar: '#toolbar-user',
        singleSelect: true,
        columns: [{
            field: 'state',
            checkbox: true
        }, {
            field: 'id',
            title: 'User ID'
        }, {
            field: 'username',
            title: 'Username'
        }]
    });
}

function getExerciseAndFillTable(url) {

    $('#exercise-table').bootstrapTable({
        url: url,
        toolbar: '#toolbar-exercise',
        singleSelect: true,
        columns: [{
            field: 'state',
            checkbox: true
        }, {
            field: 'id',
            title: 'Exercise ID'
        }, {
            field: 'name',
            title: 'Exercise Name'
        }, {
            field: 'description',
            title: 'Exercise Description'
        }]
    });
}

function performClickRemoveUser() {

    var button = $('#button-remove-user');
    var table = $('#user-table');

    button.click(function () {
        var selection = table.bootstrapTable('getSelections');
        if (selection.length == 1 && performDelete('/user', selection[0].id)) {
            var ids = $.map(selection, function (row) {
                return row.id;
            });
            table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
        }
    });
}

function performClickRemoveExercise() {

    var button = $('#button-remove-exercise');
    var table = $('#exercise-table');

    button.click(function () {
        var selection = table.bootstrapTable('getSelections');
        if (selection.length == 1 && performDelete('/exercise', selection[0].id)) {
            var ids = $.map(selection, function (row) {
                return row.id;
            });
            table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
        }
    });
}

function performClickAddUser(){
    var button = $('#button-add-user');

    button.click(function () {
        $('#addExerciseModal').modal();
    });
}

function performClickAddExercise(){
    var button = $('#button-add-exercise');

    button.click(function () {
        $('#addUserModal').modal();
    });
}

function performDelete(url, id) {

    // When you specify: dataType: 'json',
    // jQuery will fire the error event if the response cannot be parsed as JSON,
    // even if server returns 200 OK.
    return jQuery.ajax({
        type: "DELETE",
        url: url + '/' + id,
        dataType: "html",
        success: function (data, status, jqXHR) {
            if (jqXHR.status == 200) {
                return true;
            }
            return false;
        },
        error: function (jqXHR, status) {
            return false;
        }
    });

}


/**
 * General jquery ajax request.
 *
 * jQuery.ajax({
 *   type: "GET|POST|DELETE|PUT",
 *   url: url,
 *   data: data,
 *   dataType:"text|html|json|jsonp|script|xml"
 *   success: success_callback,
 *   error: error_callback
 *});
 */

