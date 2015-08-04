var app = angular.module('app', [

    /*
     * Order is not important. Angular makes a
     * pass to register all of the modules listed
     * and then when app.dashboard tries to use app.data,
     * it's components are available.
     */

    /*
     * Everybody has access to these.
     * We could place these under every feature area,
     * but this is easier to maintain.
     */
    "xeditable"
]);


app.run(function(editableOptions) {
    editableOptions.theme = 'bs3'; // bootstrap3 theme. Can be also 'bs2', 'default'
});