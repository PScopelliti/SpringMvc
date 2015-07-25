$(document).ready(function() {

    $.ajax({
        type: 'GET',
        url: '/exercises',
        dataType: 'json',
        success: function (data) {
          console.log(data);
        }
    });

});
