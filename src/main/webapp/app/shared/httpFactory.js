app.factory('httpFactory', function ($http) {

    var factory = {};

    factory.getExercises = function () {
        return $http.get('./exercises.json');
    };

    factory.deleteExercise = function (id) {
        return $http.delete('./exercise/' + id);
    };

    factory.updateExercise = function (id, exercise) {
        return $http.put('./exercise/' + id, exercise);
    };

    factory.addExercise = function (exercise) {
        return $http.post('./exercise/register', exercise);
    };

    return factory;
});