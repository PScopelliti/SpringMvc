app.directive('usertable', function() {
    var directive = {};

    directive.restrict = 'E'; /* restrict this directive to elements */
    directive.templateUrl = 'app/components/user/userTable.html';

    return directive;
});