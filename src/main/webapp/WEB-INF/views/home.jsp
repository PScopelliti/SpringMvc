<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Spittr</title>

    <link href="../../css/home.css" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/bootstrap-table.min.css">

</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Project name</a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">Home</a></li>
                <li><a href="#about">About</a></li>
                <li><a href="#contact">Contact</a></li>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</nav>

<div class="container">

    <div class="starter-template">
        <h1>Welcome to Exercises</h1>

        <div class="row">

            <div class="col-lg-6">
                <div id="toolbar-exercise">
                    <button id="button-remove-exercise" class="btn btn-default">Remove</button>
                    <button id="button-add-exercise" class="btn btn-default">Add</button>
                </div>
                <table id="exercise-table"></table>
            </div>
            <div class="col-lg-6">
                <div id="toolbar-user">
                    <button id="button-remove-user" class="btn btn-default">Remove</button>
                    <button id="button-add-user" class="btn btn-default">Add</button>
                </div>
                <table id="user-table"></table>
            </div>
        </div>
    </div>

</div>
<!-- /.container -->


<!-- Modal window -->

<!-- Add Exercise Modal -->

<div class="modal fade" id="addExerciseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addExerciseModalLabel">Add Exercise</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label">Exercise name</label>
                    <input type="text" class="form-control" id="recipient-exercise-name">
                </div>
                <div class="form-group">
                    <label class="control-label">Exercise description</label>
                    <input type="text" class="form-control" id="recipient-exercise-description">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<!-- Add User Modal -->

<div class="modal fade" id="addUserModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="addUserModalLabel">Add User</h4>
            </div>
            <div class="modal-body">
                <div class="form-group">
                    <label class="control-label">Username</label>
                    <input type="text" class="form-control" id="recipient-user-username">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript
    ================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/bootstrap-table.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.8.1/locale/bootstrap-table-zh-CN.min.js"></script>
<script src="../../js/home.js"></script>
</body>
</html>
