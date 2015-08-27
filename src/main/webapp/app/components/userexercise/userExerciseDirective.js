app.directive('userexercisetable', function() {
    var directive = {};

    directive.restrict = 'E'; /* restrict this directive to elements */
    directive.templateUrl = 'app/components/userexercise/userExerciseTable.html';

    return directive;
});