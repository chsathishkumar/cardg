cardg.controller('cardgController', function($scope, $location, $resource,
		$rootScope, $window, $http, $filter, $mdSidenav) {

	
	$scope.loginCheck = 0;
	
	if (localStorage.getItem('loginCheck'))
		$scope.loginCheck = 1;
	
	
	// *************** LOGIN ***********************
	$scope.login = function() {
		var data = new FormData();
		$scope.userEmail = $scope.parent.email;
		data.append('userEmail', $scope.parent.email);
		data.append('userPassword', $scope.parent.password);
		$http.post('login', data, {
			withCredentials : false,
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(
				function(resp) {
					console.log(resp);
					if (resp != '') {
						$scope.userName = resp.userName;
						$scope.loginCheck = 1;
						localStorage.setItem('loginCheck',
								$scope.loginCheck);
						localStorage.setItem('userName',
								$scope.userName);
						localStorage.setItem('userType',
								resp.userType);
						localStorage.setItem('userEmail',
								$scope.parent.email);
						console.log(resp.userType);
						$('.modal-backdrop').remove();
						$('.in').remove();

						if (resp.userType == 'Manager')
							$location.path('/dealer');
						else
							$location.path('/dealer');
						$window.scrollTo(0, 0);

					} else {
						$scope.errorMessage = 'Invalid Login';
					}
				}).error(function() {
			$scope.errorMessage = 'Invalid Login';
		});
	}
	$scope.userName = localStorage.getItem('userName');
	
	
	// ***************************** LOGOUT
	// ***************************
	$scope.logout = function() {
		$rootScope.spinnerKey = true;
		$scope.userName = null;
		localStorage.removeItem('userName');
		localStorage.removeItem('userEmail');
		localStorage.removeItem('userType');
		localStorage.removeItem('loginCheck');
		$scope.loginCheck = 0;
		$location.path('/dealer');
		$rootScope.spinnerKey = false;

	}
	
	// ***************************** SIGN UP ***************
	$scope.parent = {};
	$scope.signup = function() {
		alert($scope.parent.signupFullname);
		var data = new FormData();
		data.append('userName', $scope.parent.signupFullname);
		data.append('userEmail', $scope.parent.signupEmail);
		data.append('userPassword',
				$scope.parent.signupPassword);
		data.append('userLocation', $scope.parent.userLocation);
		data.append('userType', $scope.parent.userType);
		if ($scope.parent.signupEmail != null
				&& $scope.parent.signupPassword != null) {
			$http(
					{
						url : 'getProfile',
						method : "GET",
						dataType : "json",
						headers : {
							'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
						},
						params : {
							'userEmail' : $scope.parent.signupEmail
						}
					})
					.then(
							function(response) {
								if (response.data == '') {
									$http
											.post(
													'signup',
													data,
													{
														withCredentials : false,
														transformRequest : angular.identity,
														headers : {
															'Content-Type' : undefined
														}
													})
											.success(
													function(
															resp) {
														$(
																'.modal-backdrop')
																.remove();
														bootbox
																.alert('Please check your email to authenticate/activate your Email ID');
														$location
																.path('/dealer');
													})
											.error(
													function() {
														console
																.log('My Error');
														$location
																.path('/dealer');
													});
								} else {
									$scope.errorMessage = 'User already exists. Please try again'
								}
							})
		}
	}
	
	$scope.home = function() {
		$location.path('/dealer');
	}
	
	
	$scope.profile = function() {
		$location.path('/profile');
	}

	$scope.getProfile = function() {
		var data = new FormData();
		data.append('userName', $scope.userEmail);
		$http
				.get(
						'getProfile',
						{
							params : {
								'userEmail' : localStorage
										.getItem('userEmail')
							}
						})
				.then(
						function(response) {
							$scope.profileData = response.data;
							$scope.address = $scope.profileData.userAddressOne
									+ '\n'
									+ $scope.profileData.userAddressTwo
									+ '\n'
									+ $scope.profileData.userCity;
							$scope.phoneNumber = $scope.profileData.userPhonenumber;
						});

	}

	$scope.updateProfile = function() {
		$window.scrollTo(0, 0);
		var data = new FormData();
		data.append('userEmail', localStorage
				.getItem('userEmail'));
		data.append('userName', $scope.userName);
		data.append('phoneNumber',
				$scope.profileData.userPhonenumber);
		data.append('addressOne',
				$scope.profileData.userAddressOne);
		data.append('addressTwo',
				$scope.profileData.userAddressTwo);
		data.append('city', $scope.profileData.userCity);
		data.append('state', $scope.profileData.userState);
		data.append('country', $scope.profileData.userCountry);
		data.append('zipcode', $scope.profileData.zipCode);
		$http.post('updateProfile', data, {
			withCredentials : false,
			transformRequest : angular.identity,
			headers : {
				'Content-Type' : undefined
			}
		}).success(function() {
			bootbox.alert('Profile updated successfully');
		})
	}
	
	/**
	 * Build handler to open/close a SideNav; when animation
	 * finishes report completion in console
	 */
	function buildDelayedToggler(navID) {
		return debounce(function() {
			// Component lookup should always be available since
			// we are not using `ng-if`
			$mdSidenav(navID).toggle().then(function() {
				$log.debug("toggle " + navID + " is done");
			});
		}, 200);
	}

	function buildToggler(navID) {
		return function() {
			// Component lookup should always be available since
			// we are not using `ng-if`
			$mdSidenav(navID).toggle().then(function() {
				$log.debug("toggle " + navID + " is done");
			});
		};
	}

	$scope.toggleLeft = buildToggler('left');
	$scope.toggleRight = buildToggler('right');

	function buildToggler(componentId) {
		$window.scrollTo(0, 0);
		return function() {
			$mdSidenav(componentId).toggle();
		};
	}

	
	//$scope.loginCheck = 0;
	// To Display cards for 2 players
	$scope.playerA;
	$scope.playerA;
	$scope.clickCount = 0;
	var count = 0;
	var numberOfCards = 0;
	
	$scope.trashinPool = [];
	$scope.pickNextCard = function(){
		//$scope.openCard = $scope.playerCard[1];
		$scope.addToPlayerList($scope.playerCard[1]);
		//$scope.playerCard.splice($scope.openCard, 1);
		$scope.trashinPool.push($scope.openCard);
		
		for( var j = $scope.playerCard.length; j--;){ 
			if($scope.playerCard[j]==$scope.playerCard[1]){
				$scope.playerCard.splice(j, 1);
			}		

			}
		
		$scope.firstOpenCard = '';
	}
	
	$scope.addToPlayerList = function(openCard){
		$scope.playerA3cards.push(openCard);
		//$scope.openCard = '';
		
		//$scope.playerCard.splice($scope.openCard, 1); 
		$scope.firstOpenCard = '';
		
	}
	
	
	$scope.throwCardToTrashA = function(playerA){
		//alert(playerA);
		if($scope.playerA3cards.length == 14){
			//$scope.trashinPool.push(playerA);
			
			for( var j = $scope.playerA3cards.length; j--;){ 
				if($scope.playerA3cards[j]==playerA){
					$scope.playerA3cards.splice(j, 1);
				}
				

				}
			$scope.openCard = playerA;
		}
	}
	
	$scope.throwCardToTrashB = function(playerB){
		if($scope.playerB3cards.length == 14){
			$scope.trashinPool.push(playerB);
			for( var j = $scope.playerB3cards.length; j--;){ 
				if($scope.playerB3cards[j]==playerB){
					alert(playerB);
					$scope.playerB3cards.splice(j, 1);
				}
			}	
		}
	}

	$scope.playerA3cards = [];
	$scope.playerB3cards = [];

	$scope.userList = [];
	$scope.getCard = function() {
		$scope.userList = ['sk@gmail.com','vema@gmail.com'];
		var data = new FormData();
		data.append('user1', 'sk@gmail.com');
		data.append('user2', 'vema@gmail.com');
		$http.post('createGame', data, {
				withCredentials : false,
				transformRequest : angular.identity,
				headers : {
					'Content-Type' : undefined
			}
		}).success(function(resp) {
			//
		})
		
		
		count = $scope.clickCount++;
		count = count % 2;
		$scope.playerA = '0';
		$scope.playerB = '0';
		//if (count == 0) {
		//$scope.playerB = '0';
		$scope.playerCard.forEach(function(value, index, array) {
			numberOfCards++;
			if (numberOfCards <= 13) {
				$scope.playerA3cards.push(value);
				if ($scope.playerA3cards.length > 13) {
					$scope.playerA3cards.splice(0, 13);
					//$scope.playerB3cards.splice(0, 13);
				}
				//$scope.playerCard.splice($scope.playerCard.indexOf(value),1);
			}
			
			if (numberOfCards > 13 && numberOfCards <= 26) {
				$scope.playerB3cards.push(value);
				if ($scope.playerB3cards.length > 13) {
					$scope.playerB3cards.splice(0, 13);
					//$scope.playerA3cards.splice(0, 13);
				}
				//$scope.playerCard.splice($scope.playerCard.indexOf(value),1);
			}
		});
		
		for( var i = $scope.playerA3cards.length; i--;){ 

			$scope.playerCard.splice(i, 1); 

			}
		for( var j = $scope.playerB3cards.length; j--;){ 

			$scope.playerCard.splice(j, 1); 

			}
		/*$scope.playerCard = $scope.playerCard.filter(function(x) {
			return $scope.playerA3cards.indexOf(x) < 0
		})*/
		//$scope.openJokerCard = '';
		//$scope.openCard = '';
		//numberOfCards = 0;
	//} else {
		//$scope.playerA = '0';
		/*$scope.playerCard.forEach(function(value, index, array) {
			numberOfCards++;
			if (numberOfCards <= 13) {
				$scope.playerB3cards.push(value);
				if ($scope.playerB3cards.length > 13) {
					$scope.playerB3cards.splice(0, 13);
					//$scope.playerA3cards.splice(0, 13);
				}
				//$scope.playerCard.splice($scope.playerCard.indexOf(value),1);
			}
		});*/
		
		/*$scope.playerCard = $scope.playerCard.filter(function(x) {
			return $scope.playerB3cards.indexOf(x) < 0
		})*/
		
		numberOfCards = 0;
		
	//}
		
		$scope.openCardAndJoker();

	}
	
	$scope.firstOpenCard = '';
	$scope.openJokerCard = '';
	$scope.openCard = '';
	$scope.openCardAndJoker = function(){
		//alert($scope.playerCard);
		if($scope.firstOpenCard == ''){
			$scope.firstOpenCard = $scope.playerCard[1];
			/*$scope.playerCard = $scope.playerCard.filter(function(x) {
				return $scope.openCard.indexOf(x) < 0
			})*/
			$scope.playerCard.splice($scope.firstOpenCard, 1); 
			//$scope.firstOpenCard = $scope.openCard; 
		}
		
		if($scope.openJokerCard == ''){
			$scope.openJokerCard = $scope.playerCard[1];
			/*$scope.playerCard = $scope.playerCard.filter(function(x) {
				return $scope.openJokerCard.indexOf(x) < 0
			})*/
			$scope.playerCard.splice($scope.openJokerCard, 1); 
		}
		
	}
	/*$scope.openCard = function(openCard){
		alert($scope.openCard);
		$scope.playerCard = $scope.playerCard.filter(function(x) {
			return $scope.openCard.indexOf(x) < 0
		})
	}*/

	// Get all shuffled cards
	$scope.playerCard = [];
	$scope.getShuffledCards = function() {
		$scope.clickCount = 0;
		count = 0;
		numberOfCards = 0;

		$scope.playerA3cards = [];
		$scope.playerB3cards = [];
		$http.get('getShuffledCards', {
			params : {}
		}).then(function(response) {
			if (response.data.cardAndPlayerMap.Player.length > 0) {
				console.log(response);
				$scope.playerCard = response.data.cardAndPlayerMap.Player;
				$scope.getCard();

			} else {
				console.log("No date retruned from Server side !!!");
			}
		});

	}
	
	

	$scope.getGroupA = function(listAcards) {
		console.log("===================");
		console.log(listAcards);
		console.log("===================");
		var data = new FormData();
		data.append("listAcards", listAcards);
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

	$scope.getGroupB = function(listBcards) {
		console.log("===================");
		console.log(listBcards);
		console.log("===================");
		var data = new FormData();
		data.append("listBcards", listBcards);

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