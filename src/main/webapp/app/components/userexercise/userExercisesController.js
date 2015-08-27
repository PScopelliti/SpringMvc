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

    $scope.showUsers = function (userExercises) {
        console.log(userExercises);
        if (userExercises.pk.user && $scope.users.length) {
            var selected = $filter('filter')($scope.users, {id: userExercises.pk.user.id});
            return selected.length ? selected[0].id : 'Not set';
        } else {
            return userExercises.pk.user.id || 'Not set';
        }
    };
});