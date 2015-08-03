app.controller('exercisesController', function ($scope, exerciseFactory) {
    function init() {
        exerciseFactory.getExercises().success(function (data) {
            $scope.exercises = data;
        });
    };

    $scope.delete = function (exercise) {
        exerciseFactory.deleteExercise(exercise.id).success(function (data) {
            console.log('res',data);
        });
    };

    $scope.edit = function (exercise) {
        console.log("edit", exercise);
    };

    $scope.addExercise = function () {

    };

    init();
});

app.factory('exerciseFactory', function ($http) {

    var factory = {};

    factory.getExercises = function () {
        return $http.get('/exercises.json');
    };

    factory.deleteExercise = function (id) {
        return $http.delete('/exercise/' + id);
    };

    return factory;
});


