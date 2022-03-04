var loginApp = angular.module("LoginForm", []);

loginApp.controller("LoginController", function ($scope, $http) {
    $scope.loginObject = {
        username: "",
        password: ""
    }

    $scope.loginSubmit = function () {
        var method = "POST";
        var url = "/login";

        $http({
            method: method,
            url: url,
            data: angular.toJson($scope.loginSubmit),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(
            function () {
                var status = res.status;
                var header = res.header;
                var config = res.config;
                alert("Error : " + status + " : " + data);

            },
            function () {
                var status = res.status;
                var header = res.header;
                var config = res.config;
                alert("Error : " + status + " : " + data);
            })

    }

})