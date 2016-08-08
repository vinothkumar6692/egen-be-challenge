'use strict'

/* egenApp Module */
var egenApp = angular.module('egenApp', [
  'ngRoute',
  'App'
])


egenApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider
  .when('/', {
    templateUrl: 'views/home.html',
    controller: 'homeController'
  })
  .when('/addnew', {
    templateUrl: 'views/addnew.html',
    controller: 'addnewController'
  })
  .when('/update/:id', {
    templateUrl: 'views/update.html',
    controller: 'updateController'
  })  
  .otherwise({
    redirectTo: '/'
  });
}]);


var App = angular.module('App',[]);


App.filter('searchUser', function () {
 
  return function (input, selectedData) {
    if (!angular.isUndefined(input) && !angular.isUndefined(selectedData) && selectedData.length > 0) {
              var tempClients = [];
              var data = selectedData.split(',');
              data = data.filter(Boolean)
              angular.forEach(data, function (id) {
                  tempClients = []
                  angular.forEach(input, function (item) {
                      if (item.firstName.toLowerCase().indexOf(id) > -1 || item.lastName.toLowerCase().indexOf(id) > -1 || item.email.toLowerCase().indexOf(id) > -1 || item.company.website.toLowerCase().indexOf(id) > -1|| item.address.city.toLowerCase().indexOf(id) > -1|| item.company.name.toLowerCase().indexOf(id) > -1 || item.address.state.toLowerCase().indexOf(id) > -1 || item.address.street.toLowerCase().indexOf(id) > -1 || item.address.zip.toLowerCase().indexOf(id) > -1 || item.address.country.toLowerCase().indexOf(id) > -1) {
                        tempClients.push(item);
                      }
                  });
                  input = tempClients;
              });
              return tempClients;
          } else {
              return input;
          }
      };
});
