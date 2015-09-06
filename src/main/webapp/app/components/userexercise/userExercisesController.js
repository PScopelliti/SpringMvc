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
        if ($scope.users.length) {
            var selected = $filter('filter')($scope.users, {id: userExercises.userId});
            return selected.length ? selected[0].id : 'Not set';
        } else {
            return userExercises.userId || 'Not set';
        }
    };

    $scope.showExercises = function (userExercises) {
        if ($scope.exercises.length) {
            var selected = $filter('filter')($scope.exercises, {id: userExercises.exerciseId});
            return selected.length ? selected[0].id : 'Not set';
        } else {
            return userExercises.exerciseId || 'Not set';
        }
    };

    // remove user exercise
    $scope.removeUserExercise = function (index) {
        var userId = $scope.userExercises[index].userId;
        var exerciseId = $scope.userExercises[index].exerciseId;
        $scope.userExercises.splice(index, 1);
        httpFactory.deleteUserExercise(userId, exerciseId)
            .success(function (data) {

            })
            .error(function (error) {

            });
    };

    $scope.saveUserExercise = function (data, userExercise) {

        var form = this.userexerciseform;

        if (data.userId == undefined) {
            form.$setError('userid', 'wrong value');
        }

        if (data.exerciseId == undefined) {
            form.$setError('exerciseid', 'wrong value');
        }

        // If we need to update a previous user
        return httpFactory.saveUserExercise(data.userid, data.exerciseid)
            .success(function (data) {
                // setting userexercise data
                userExercise.creationDate = data.createdDate;

                userExercise.isNew = false;
            })
            .error(function (error, status, headers, config) {
                if (status == 400) {
                    return 'Error';
                }
                console.log('error', error);
            });
    };

    $scope.addUserExercise = function () {
        $scope.inserted = {
            userId: '',
            exerciseId: '',
            creationDate: undefined,
            isNew: true
        };
        $scope.userExercises.push($scope.inserted);
    };

    $scope.checkSelection = function (data) {
        console.log('data', data);
    };

    $scope.cancel = function () {
        for (var i = $scope.userExercises.length; i--;) {
            var userExercises = $scope.userExercises[i];
            // remove new
            if (userExercises.isNew) {
                $scope.userExercises.splice(i, 1);
            }
        }
    };
});