var app = angular.module("EmployeeManagement", []);
let getJwt = "";

let localJwt = "Gril ";

app.run(function ($http){
	$http.defaults.headers.common.Authorization = localJwt + getJwt;
})
let jwt = "";
app.controller("EmployeeController", function($scope, $http) {
	$scope.employees = [];
	$scope.employeeForm = {
		id: -1,
		name: "",
		password: ""
	}
	
	function _refeshEmployeeData() {
		$http({
			method: 'GET',
			url: '/rest/employees',
			headers: {
				Authorization: `Bearer ${localStorage.getItem('access_token')}`
			}
		}).then(
			function(res) {
				$scope.employees = res.data;

			},
			function(res) {
				console.log("Error : " + res.status + " : " + res.data);
			}
		)
	}
	
	_refeshEmployeeData();
	
	
	$scope.editEmployee = function(e) {
		_createForm(e);
		
	}
	
	function _createForm(e){
		$scope.employeeForm.id = e.id;
		$scope.employeeForm.name = e.name;
		$scope.employeeForm.password = e.password;
	}
	
	function _success() {
		_refeshEmployeeData();
		_clearForm();

	}


	
	function _error(res) {
		//var data = res.data;
		var status = res.status;
		var header = res.header;
		var config = res.config;
		alert("Error : " + status + " : " + data);
	}
	$scope.employeeSubmit = function() {
		var method = "";
		var url = "";
		const accessToken = localStorage.getItem('access_token')
		if ($scope.employeeForm.id == -1) {
			method = "POST";
			url = '/rest/employees';
		} else {
			method = "PUT";

		}
		$http({
			method: method,
            url: url,
            data: angular.toJson($scope.employeeForm),
            headers: {
                'Content-Type': 'application/json',
				'Authorization': 'Bearer ' + accessToken
            }
		}).then(
			function(){
			_success();
			
		}, 
			function(){
			//	_error();
			})
	};

	$scope.loginForm = {
		username: "",
		password: ""
	}

	$scope.loginSubmit = function () {
		var method = "POST";
		var url = "/login";

		$http({
			method: method,
			url: url,
			data: angular.toJson($scope.loginForm),
			headers: {
				'Content-Type': 'application/json',
			}
		}).then(
			function(res){
				jwt = res.data.jwt;
				localStorage.setItem("access_token", jwt);

			},_error)

	}
	

	function _clearForm() {
		$scope.employeeForm.id = -1;
		$scope.employeeForm.name = "";
		$scope.employeeForm.password = "";
	};
	
	$scope.deleteEmployee = function(id) {
		$http({
			method: "DELETE",
			url: "/rest/employees/" + id,
			headers: {
				Authorization: `Bearer ${localStorage.getItem('access_token')}`
			}
		}).then(
			function(res) {
				alert("Xóa thành công employee có mã " + id);
				_success();
			},
			function(res) {
				console.log(res.status + " : " + res.data);
			}
		)
	}



})

