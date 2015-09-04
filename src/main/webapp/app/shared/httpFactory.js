app.factory('httpFactory', function ($http) {

    var factory = {};

    factory.getExercises = function () {
        return $http.get('./exercises.json');
    };

    factory.deleteExercise = function (id) {
        return $http.delete('./exercises/' + id);
    };

    factory.updateExercise = function (id, exercise) {
        return $http.put('./exercises/' + id, exercise);
    };

    factory.addExercise = function (exercise) {
        return $http.post('./exercises/register', exercise);
    };

    factory.getUsers = function () {
        return $http.get('./users.json');
    };

    factory.updateUser = function (id, user) {
        return $http.put('./user/' + id, user);
    };

    factory.addUser = function (user) {
        return $http.post('./users/register', user);
    };

    factory.deleteUser = function (id) {
        return $http.delete('./users/' + id);
    };

    factory.getUserExercises = function () {
        return $http.get('./users/exercises');
    };

    factory.deleteUserExercise = function (userId, exerciseId) {
        return $http.delete('./users/' + userId + '/exercises/' + exerciseId);
    };

    factory.updateUserExercise = function (userId, exerciseId, userExercise) {
        return $http.put('./users/' + userId + '/exercises/' + exerciseId, userExercise);
    };

    return factory;
});