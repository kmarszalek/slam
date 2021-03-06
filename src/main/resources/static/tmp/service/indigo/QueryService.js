var app = angular.module('indigo');

app.service("QueryService", ['$http', '$q', function ($http, $q) {

    // Return public API.
    return ({
        getAvailableQueries: getAvailableQueries,
        getQueryDocuments: getQueryDocuments
    });

    // ---
    // PUBLIC METHODS.
    // ---

    // I get all of the friends in the remote collection.
    function getAvailableQueries(queryCategoryId) {
        // var queryCategoryId = "default";

        var request = $http({
            headers : { 'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8' },
            method : "get",
            url : "/query/available",
            params : {
                queryCategoryId : queryCategoryId
            }
        });

        return (request.then(handleSuccess, handleError));
    }

    function getQueryDocuments(queryId) {
        var request = $http({
            headers: {'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'},
            method: "get",
            url: "query/documents",
            params: {
                queryId: queryId
            }
        });

        return (request.then(handleSuccess, handleError));

    }

    // ---
    // PRIVATE METHODS.
    // ---

    // I transform the error response, unwrapping the application dta from
    // the API response payload.
    function handleError(response) {
        // The API response from the server should be returned in a
        // nomralized format. However, if the request was not handled by the
        // server (or what not handles properly - ex. server error), then we
        // may have to normalize it on our end, as best we can.
        if (!angular.isObject(response.data) || !response.data.message) {

            return ($q.reject("An unknown error occurred."));

        }

        // Otherwise, use expected error message.
        return ($q.reject(response.data));

    }

    // I transform the successful response, unwrapping the application data
    // from the API response payload.
    function handleSuccess(response) {
        return (response.data);

    }

}]);