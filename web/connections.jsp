
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="profileInfo">
<head>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="lib/animate/animate.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap-theme.min.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="lib/angular/angular.js"></script>
    <script type="text/javascript" src="lib/angular/angular-resource.js"></script>
    <script type="text/javascript" src="js/profileInfo.js"></script>
    <script type="text/javascript" src="js/controllers.js"></script>
    <script type="text/javascript" src="js/services.js"></script>
</head>
</head>
<body ng-controller="profileShowCtrl">

<div class="navbar navbar-inverse">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-inverse-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <img class="navbar-brand" src="img/nav-logo.png"/>
    </div>
    <div class="navbar-collapse collapse navbar-inverse-collapse">
        <ul class="nav navbar-nav">
            <li><a href="profile.jsp">Profile</a></li>
            <li class="active"><a href="connections.jsp">Connections</a></li>

            </li>
        </ul>
        <form class="navbar-form navbar-left">
            <input class="form-control col-lg-8" placeholder="Search" type="text">
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="logout">Logout</a></li>

        </ul>
    </div>
</div>


<div class="container">
    <div class="animated lightSpeedIn">
        <div class="jumbotron">
            <div ng-show="profile.connections">
                <div class="row">
                    <div class="col-md-12" align="justify">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><b>Connections</b>

                                    <h3 class="panel-title"></h3></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div ng-repeat="connection in profile.connections">
                                        <div class="col-xs-5">
                                            <img src="{{connection.pictureUrl}}" alt="...">
                                            <h4> {{connection.firstName}}&nbsp;{{connection.lastName}}</h4>
                                            <h7>{{connection.headLine}}</h7>
                                            <br/><br/>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
