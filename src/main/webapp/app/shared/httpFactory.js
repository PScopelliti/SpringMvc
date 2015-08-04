
app.factory('httpFactory', function ($http) {

    var factory = {};

    factory.getExercises = function () {
        return $http.get('./exercises.json');
    };

    factory.deleteExercise = function (id) {
        return $http.delete('./exercise/' + id);
    };

    return factory;
});