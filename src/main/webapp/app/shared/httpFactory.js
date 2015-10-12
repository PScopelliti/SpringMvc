app.factory('httpFactory', function ($http) {

    var factory = {};

    factory.getExercises = function () {
        return $http.get('./api/v1/exercises.json');
    };

    factory.deleteExercise = function (id) {
        return $http.delete('./api/v1/exercises/' + id);
    };

    factory.updateExercise = function (id, exercise) {
        return $http.put('./api/v1/exercises/' + id, exercise);
    };

    factory.addExercise = function (exercise) {
        return $http.post('./api/v1/exercises/register', exercise);
    };

    factory.getUsers = function () {
        return $http.get('./api/v1/users.json');
    };

    factory.updateUser = function (id, user) {
        return $http.put('./api/v1/users/' + id, user);
    };

    factory.addUser = function (user) {
        return $http.post('./api/v1/users/register', user);
    };

    factory.deleteUser = function (id) {
        return $http.delete('./api/v1/users/' + id);
    };

    factory.getUserExercises = function () {
        return $http.get('./api/v1/users/exercises');
    };

    factory.deleteUserExercise = function (userId, exerciseId) {
        return $http.delete('./api/v1/users/' + userId + '/exercises/' + exerciseId);
    };

    factory.saveUserExercise = function (userId, exerciseId) {
        return $http.post('./api/v1/users/' + userId + '/exercises/' + exerciseId);
    };

    return factory;
});