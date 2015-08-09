app.controller('usersController', function ($scope, httpFactory) {
    function init() {
        httpFactory.getUsers()
            .success(function (data) {
                $scope.users = data;
            })
            .error(function (error) {

            });
    };

    init();

    $scope.saveUser = function (data, user) {

        var form = this.userform;

        // If the user is new
        if (user.isNew) {
            // Set false is new field, for nex requests.
            user.isNew = false;

            return httpFactory.addUser(data)
                .success(function (response) {
                    // setting user id
                    user.id = response.id;
                })
                .error(function (error) {
                    form.$setError('name', 'Sticazzi');
                });
        }
        // If we need to update a previous user
        return httpFactory.updateEUser(user.id, data)
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
    $scope.removeUser = function (index) {
        var id = $scope.users[index].id;
        $scope.users.splice(index, 1);
        httpFactory.deleteUser(id)
            .success(function (data) {

            })
            .error(function (error) {

            });
    };

    // add user
    $scope.addUser = function () {
        $scope.inserted = {
            id: undefined,
            name: '',
            description: '',
            isNew: true
        };
        $scope.users.push($scope.inserted);
    };

    $scope.cancel = function () {
        for (var i = $scope.users.length; i--;) {
            var user = $scope.users[i];
            // remove new
            if (user.isNew) {
                $scope.users.splice(i, 1);
            }
        }
    };
});

