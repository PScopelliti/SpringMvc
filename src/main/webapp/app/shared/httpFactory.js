app.factory('httpFactory', function ($http) {

    var factory = {};

    factory.getExercises = function () {
        return $http.get('./exercises.json');
    };

    factory.deleteExercise = function (id) {
        return $http.delete('./exercise/' + id);
    };

    factory.updateExercise = function (id, exercise) {
        return $http.put('./exercise/' + id, exercise);
    };

    factory.addExercise = function (exercise) {
        return $http.post('./exercise/register', exercise);
    };

    factory.getUsers = function () {
        return $http.get('./users.json');
    };

    factory.updateUser = function (id, user) {
        return $http.put('./user/' + id, user);
    };

    factory.addUser = function (user) {
        return $http.post('./user/register', user);
    };

    factory.deleteUser = function (id) {
        return $http.delete('./user/' + id);
    };

    factory.getUserExercises = function () {
        return $http.get('./user/exercises');
    };

    factory.deleteUserExercise = function (userId, exerciseId) {
        return $http.delete('./user/' + userId + '/exercise/' + exerciseId);
    };

    factory.updateUserExercise = function (userId, exerciseId, userExercise) {
        return $http.put('./user/' + userId + '/exercise/' + exerciseId, userExercise);
    };

    return factory;
});