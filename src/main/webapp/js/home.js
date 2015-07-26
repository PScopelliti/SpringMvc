$(document).ready(function () {

    getExerciseAndFillTable('/exercises')

});


function getExerciseAndFillTable(url) {

    $('#table').bootstrapTable({
        url: url,
        columns: [{
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
