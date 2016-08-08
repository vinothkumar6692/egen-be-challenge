App.controller('homeController', ['$scope','$http','$routeParams', '$location',
  function($scope,$http,$routeParams,$location) {

    $scope.order = function (predicate) {  
	    $scope.reverse = ($scope.predicate === predicate) ? !$scope.reverse : false;  
	    $scope.predicate = predicate;  
    };  

    $scope.navigate = function (){
    	$location.url('/addnew');
    };

    $scope.edit = function (id){
    	$location.url('/update/'+id);
    };    

    /* $scope.users = [{
    	first_name : 'Vignesh',
    	last_name : 'ramesh',
    	emailid: 'vingesh.6v@gmail.com',
    	company : 'verizon',
    	city : 'NY'

    }]; */

    $http.get('/findAllUser')
        .success(function(response){
          console.log(response);
            $scope.users = response

        })
        .error(function(response){
            console.log('error',response.message);
        })

 }]);