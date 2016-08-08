App.controller('updateController', ['$scope','$http','$routeParams', '$location',
  function($scope,$http,$routeParams,$location) {
  	$scope.id = $routeParams.id;
    $scope.user = {};
    console.log($scope.id);

  	$http.get('/findUser/'+$scope.id)
        .success(function(response){
          console.log(response);
            $scope.user = response

        })
        .error(function(response){
            console.log('error',response.message);
        })

    $scope.update = function(user){
      console.log(user);
      delete user['_id'];
      console.log('update');
      $http.post('/updateUser/'+user.id,user)
        .success(function(response){
          console.log(response);
            $location.url('index.html');

        })
        .error(function(response){
            console.log('error',response.message);
        })
    };


 }]);