app.controller('exercisesController', function ($scope, httpFactory) {
    function init() {
        httpFactory.getExercises().success(function (data) {
            $scope.exercises = data;
        });
    };

    init();

    $scope.saveExrecise = function (data, id) {
        //$scope.user not updated yet
        //angular.extend(data, {id: id});
        //return $http.post('/saveUser', data);
        console.log('save')
    };

    // remove user
    $scope.removeExercise = function (index) {
        var id = $scope.exercises[index].id;
        $scope.exercises.splice(index, 1);
        httpFactory.deleteExercise(id);
    };

    // add user
    $scope.addExercise = function () {
        $scope.inserted = {
            id: $scope.exercises.length + 1,
            name: '',
            description: ''
        };
        $scope.exercises.push($scope.inserted);
    };
});

