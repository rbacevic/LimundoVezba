angular.module('limundo.Controlers',[])
.controller('KategorijeCtrl', function($scope, Kategorije) {
	
	$scope.svekategorije = Kategorije.query();
	
});











































/*var limApp = angular.module('limApp', []);

limApp.controller('ListaAuckijaCtrl',[ '$scope', '$http', 
		function($scope,$http){ 
	$http.get('http://localhost:8080/Test/test/aukcije').success(function(data) {
		$scope.aukcije= data;

		
	})
	
  $scope.orderProp= 'id';
  
}]);

limApp.service('ClanoviCtrl', ['$scope','$http', 
      function($scope,$http){
	$http.get().success(function(data){
		$scope.clanovi=data;
		return $scope.clanovi;
	})
}]);                            
                                  
                                 


var onClick = function($scope,ClanoviCtrl) {

   
	
	$scope.username= username;
	$scope.password= password;
	
	$scope.forEach(ClanoviCtrl.clanovi, function(values, key) {
		if($scope.equals(valueOf(key), $scope.username))
			if($scope.equals(valueOf(key), $scope.password)){
				alert.bind('Login');
			}
			
		
		
	})
	
	
	
};*/