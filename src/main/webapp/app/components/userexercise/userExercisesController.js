app.controller('userExercisesController', function ($scope, $filter, httpFactory) {
    function init() {
        httpFactory.getUserExercises()
            .success(function (data) {
                $scope.userExercises = data;
            })
            .error(function (error) {

            });
    };

    init();

    $scope.users = [];
    $scope.loadUsers = function () {
        httpFactory.getUsers()
            .success(function (data) {
                $scope.users = data;
            })
            .error(function (error) {

            });
    };

    $scope.exercises = [];
    $scope.loadExercises = function () {
        httpFactory.getExercises()
            .success(function (data) {
                $scope.exercises = data;
            })
            .error(function (error) {

            });
    };

    $scope.showUsers = function (userExercises) {
        if (userExercises.pk.user && $scope.users.length) {
            var selected = $filter('filter')($scope.users, {id: userExercises.pk.user.id});
            return selected.length ? selected[0].id : 'Not set';
        } else {
            return userExercises.pk.user.id || 'Not set';
        }
    };

    $scope.showExercises = function (userExercises) {
        if (userExercises.pk.exercise && $scope.exercises.length) {
            var selected = $filter('filter')($scope.exercises, {id: userExercises.pk.exercise.id});
            return selected.length ? selected[0].id : 'Not set';
        } else {
            return userExercises.pk.exercise.id || 'Not set';
        }
    };

    // remove user exercise
    $scope.removeUserExercise = function (index) {
        var userId = $scope.userExercises[index].pk.user.id;
        var exerciseId = $scope.userExercises[index].pk.exercise.id;
        $scope.userExercises.splice(index, 1);
        httpFactory.deleteUserExercise(userId, exerciseId)
            .success(function (data) {

            })
            .error(function (error) {

            });
    };

    $scope.saveUserExercise = function (data, userExercise) {

        var form = this.userexerciseform;

        console.log('data', data);
        console.log('userExercise', userExercise);

        // If we need to update a previous user
        return httpFactory.updateUserExercise(data.userid, data.exerciseid, userExercise)
            .success(function (data) {
                console.log('success', data);
            })
            .error(function (error) {
                for (var i = 0; i < error.errors.length; i++) {
                    form.$setError(error.errors[i].field, error.errors[i].message);
                }
            });
    };
});