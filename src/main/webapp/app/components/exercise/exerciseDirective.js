app.directive('exercisetable', function() {
    var directive = {};

    directive.restrict = 'E'; /* restrict this directive to elements */
    directive.templateUrl = 'app/components/exercise/exerciseTable.html';

    return directive;
});