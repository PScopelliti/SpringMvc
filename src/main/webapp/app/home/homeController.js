app.controller('exercisesController', function ($scope, $modal, exerciseFactory) {
    function init() {
        exerciseFactory.getExercises().success(function (data) {
            $scope.exercises = data;
        });
    };

    $scope.delete = function (exercise) {
        exerciseFactory.deleteExercise(exercise.id).success(function (data) {
            console.log('res', data);
        });
    };

    $scope.edit = function (exercise) {
        console.log("edit", exercise);
    };

    $scope.addExercise = function () {

    };

    $scope.items = ['item1', 'item2', 'item3'];

    $scope.animationsEnabled = true;

    $scope.open = function (size) {

        var modalInstance = $modal.open({
            animation: $scope.animationsEnabled,
            templateUrl: 'myModalContent.html',
            controller: 'ModalInstanceCtrl',
            size: size,
            resolve: {
                items: function () {
                    return $scope.items;
                }
            }
        });

        modalInstance.result.then(function (selectedItem) {
            $scope.selected = selectedItem;
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());
        });
    };

    $scope.toggleAnimation = function () {
        $scope.animationsEnabled = !$scope.animationsEnabled;
    };

    init();
});

app.controller('ModalInstanceCtrl', function ($scope, $modalInstance, items) {

    $scope.items = items;
    $scope.selected = {
        item: $scope.items[0]
    };

    $scope.ok = function () {
        $modalInstance.close($scope.selected.item);
    };

    $scope.cancel = function () {
        $modalInstance.dismiss('cancel');
    };
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


