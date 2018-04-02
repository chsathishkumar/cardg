cardg
		.controller(
				'cardgController',
				function($scope, $location, $resource, $rootScope, $window,
						$http, $filter, $mdSidenav) {
					
					$scope.playerA;
					$scope.playerA;
					$scope.clickCount=0;
					$scope.choosePlayer='';
					var count = 0;					
					var numberOfCards = 0;
					
					$scope.playerA3cards = [];
					$scope.playerB3cards = [];
					
					$scope.getCard = function(){
						count = $scope.clickCount++;
						count = count%2;
						$scope.playerA='0';
						$scope.playerB='0';
						if(count == 0){
							$scope.playerB='0';
							$scope.playerACard.forEach(function(value, index, array){
								numberOfCards++;
								//alert($scope.playerA == 0);
								if(numberOfCards <=13){
									$scope.playerA3cards.push(value);
									if($scope.playerA3cards.length > 13){
										$scope.playerA3cards.splice(0,13);
										$scope.playerB3cards.splice(0,13);
									}
									//$scope.playerACard.splice(index, 1); 
								}
								
								
								/*$scope.playerACard.forEach(function(value, index, array){
									//alert($scope.playerA == 0);
									if($scope.playerA == 0){
										$scope.playerA = value;
										//alert(value);
										$scope.playerACard.splice(index, 1); 
									}*/
								
								
							});
							$scope.playerACard = $scope.playerACard.filter(function(x) { return $scope.playerA3cards.indexOf(x) < 0 })
							numberOfCards = 0;
						}else{						
							$scope.playerA='0';
							$scope.playerBCard.forEach(function(value, index, array){
								numberOfCards++;
								//alert($scope.playerA == 0);
								if(numberOfCards <=13){
									$scope.playerB3cards.push(value);
									if($scope.playerB3cards.length > 13){
										$scope.playerB3cards.splice(0,13);
										$scope.playerA3cards.splice(0,13);
									}
									//$scope.playerBCard.splice(index, 1); 
								}
								/*if($scope.playerB == 0){
									$scope.playerB = value;
									//alert(value);
									$scope.playerBCard.splice(index, 1); 
								}*/
							});
							$scope.playerBCard = $scope.playerBCard.filter(function(x) { return $scope.playerB3cards.indexOf(x) < 0 })
							numberOfCards = 0;
						}
						
					}
					
					$scope.playerACard = [];
					$scope.playerBCard = [];
					$scope.getShuffledCards = function(){
						$scope.clickCount=0;
						count = 0;					
						numberOfCards = 0;
						
						$scope.playerA3cards = [];
						$scope.playerB3cards = [];
						$http.get('getShuffledCards', {
							params : {}
						}).then(function(response) {
							console.log(response);
					    	 if (response.data.cardAndPlayerMap.Player1.length > 0 || response.data.cardAndPlayerMap.Player2>0) {
					    		 console.log(response);
					    		 $scope.playerACard = response.data.cardAndPlayerMap.Player1;
					    		 $scope.playerBCard = response.data.cardAndPlayerMap.Player2;
					    		 $scope.getCard();
					    		
					    	 }else {
									console.log("No date retruned from Server side !!!");
								}
						});
						
						}
					
					$scope.getGroupA = function(listAcards){
						console.log("===================");
						console.log(listAcards);
						console.log("===================");
						var data = new FormData();
						data.append("listAcards", listAcards);
						/*$http.get('groupCards', {
							params : {}
						}).then(function(response) {
							console.log(response);
						});*/
						
						$http.post('groupACards', data, {
							withCredentials : false,
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).success(function(resp) {
							$scope.playerA3cards = resp;
						}).error(function() {
							$scope.errorMessage = 'Invalid';
						});
						
						}
					
					$scope.getGroupB = function(listBcards){
						console.log("===================");
						console.log(listBcards);
						console.log("===================");
						var data = new FormData();
						data.append("listBcards", listBcards);
						/*$http.get('groupCards', {
							params : {}
						}).then(function(response) {
							console.log(response);
						});*/
						
						$http.post('groupBCards', data, {
							withCredentials : false,
							transformRequest : angular.identity,
							headers : {
								'Content-Type' : undefined
							}
						}).success(function(resp) {
							$scope.playerB3cards = resp;
						}).error(function() {
							$scope.errorMessage = 'Invalid';
						});
						
						}
				});

cardg.directive('preventDefault', function() {
	return function(scope, element, attrs) {
		jQuery(element).click(function(event) {
			event.preventDefault();
		});
	}
});

cardg.directive("scrollTo", [ "$window", function($window) {
	return {
		restrict : "AC",
		compile : function() {

			function scrollInto(elementId) {
				if (!elementId)
					$window.scrollTo(0, 0);
				// check if an element can be found with id attribute
				var el = document.getElementById(elementId);
				// if (el)
				// el.scrollIntoView();

				$('html, body').animate({
					scrollTop : $('#' + elementId).offset().top
				}, 1000);
			}

			return function(scope, element, attr) {
				element.bind("click", function(event) {
					scrollInto(attr.scrollTo);
				});
			};
		}
	};
} ]);

cardg.filter('pagination', function() {
	return function(input, start) {
		start = +start;
		return input.slice(start);
	};
});