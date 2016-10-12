angular.module('indigo.dashboard', ['ngRoute']).config(function ($routeProvider) {
    $routeProvider.when('/dashboard', {templateUrl: 'js/dashboard/dashboard.view.html', controller: 'dashboardCtrl',
        activetab: 'dashboard',
        data: {
        //authorizedRoles: [USER_ROLES.admin, USER_ROLES.editor]
    }});
}).controller('dashboardCtrl', function ($scope, DashboardResource, DocumentResource) {
    $scope.slas = [];

    DashboardResource.query({}, function (data) {
        $scope.slas = data.data;

        data.data.forEach(function (sla_type) {
            sla_type.slas = [];
            DocumentResource.query({query_id: sla_type.id}, function (data) {
                sla_type.documents = data.data;
            });
        })
    }, function (resonse) {

    });
});