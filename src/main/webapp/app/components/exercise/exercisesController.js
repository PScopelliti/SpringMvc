app.controller('exercisesController', function ($scope, httpFactory) {
    function init() {
        httpFactory.getExercises()
            .success(function (data) {
                $scope.exercises = data;
            })
            .error(function (error) {

            });
    };

    init();

    $scope.saveExercise = function (data, exercise) {

        var form = this.exerciseform;

        // If the exercise is new
        if (exercise.isNew) {

            return httpFactory.addExercise(data)
                .success(function (response) {
                    // setting exercise id
                    exercise.id = response.id;
                    // Set false is new field, for nex requests.
                    exercise.isNew = false;
                })
                .error(function (error) {
                    for(var i = 0; i < error.errors.length; i++){
                        form.$setError(error.errors[i].field, error.errors[i].message);
                    }
                });
        }
        // If we need to update a previous exercise
        return httpFactory.updateExercise(exercise.id, data)
            .success(function (data) {
                console.log('success', data);
            })
            .error(function (error) {
                for(var i = 0; i < error.errors.length; i++){
                    form.$setError(error.errors[i].field, error.errors[i].message);
                }
            });
    };

    // remove exercise
    $scope.removeExercise = function (index) {
        var id = $scope.exercises[index].id;
        $scope.exercises.splice(index, 1);
        httpFactory.deleteExercise(id)
            .success(function (data) {

            })
            .error(function (error) {

            });
    };

    // add exercise
    $scope.addExercise = function () {
        $scope.inserted = {
            id: undefined,
            name: '',
            description: '',
            isNew: true
        };
        $scope.exercises.push($scope.inserted);
    };

    $scope.cancel = function () {
        for (var i = $scope.exercises.length; i--;) {
            var exercise = $scope.exercises[i];
            // remove new
            if (exercise.isNew) {
                $scope.exercises.splice(i, 1);
            }
        }
    };
});

