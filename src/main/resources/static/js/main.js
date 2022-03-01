var app = angular.module("EmployeeManagement", []);

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
			url: '/rest/employees'
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
		/*this.employeeForm.id = e.id;
		this.employeeForm.name = e.name;
		this.employeeForm.password = e.password;*/
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

	};
	
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
		if ($scope.employeeForm.id == -1) {
			method = "POST";
			url = '/rest/employees';
		} else {
			method = "PUT";
			url = '/rest/employees';

		}
		$http({
			method: method,
            url: url,
            data: angular.toJson($scope.employeeForm),
            headers: {
                'Content-Type': 'application/json'
            }
		}).then(
			function(){
			_success();
			
		}, 
			function(){
			//	_error();
			})
	};
	
	
	

	function _clearForm() {
		$scope.employeeForm.id = -1;
		$scope.employeeForm.name = "";
		$scope.employeeForm.password = "";
	};
	
	$scope.deleteEmployee = function(id) {
		$http({
			method: "DELETE",
			url: "/rest/employees/" + id,
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