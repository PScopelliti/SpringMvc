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

        var form = this.rowform;

        // If the exercise is new
        if (exercise.isNew) {
            // Set false is new field, for nex requests.
            exercise.isNew = false;

            return httpFactory.addExercise(data)
                .success(function (response) {
                    // setting exercise id
                    exercise.id = response.id;
                })
                .error(function (error) {
                    form.$setError('name', 'Sticazzi');
                });
        }
        // If we need to update a previous exercise
        return httpFactory.updateExercise(exercise.id, data)
            .success(function (data) {
                console.log('success', data);
            })
            .error(function (error) {
                for(var i = 0; i < error.errorFields.length; i++){
                    form.$setError(error.errorFields[i].field, error.errorFields[i].message);
                }
            });
    };

    // remove user
    $scope.removeExercise = function (index) {
        var id = $scope.exercises[index].id;
        $scope.exercises.splice(index, 1);
        httpFactory.deleteExercise(id)
            .success(function (data) {

            })
            .error(function (error) {

            });
    };

    // add user
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
            var user = $scope.exercises[i];
            // remove new
            if (user.isNew) {
                $scope.exercises.splice(i, 1);
            }
        }
    };
});

