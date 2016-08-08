App.controller('addnewController', ['$scope','$http','$routeParams', '$location',
  function($scope,$http,$routeParams,$location) {
  	$scope.nuser = {};

  	$scope.addnew = function(item){
  		console.log(item);

  	  $http.post('/createUser',item)
        .success(function(response){
          console.log(response);
          $location.url('index.html');

        })
        .error(function(response){
            console.log('error',response.message);
        }) 

    };


 }]);