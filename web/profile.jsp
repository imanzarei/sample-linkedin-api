
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app="profileInfo">
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap.min.css">
    <!-- Optional theme -->
    <link rel="stylesheet" href="lib/bootstrap/css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="lib/animate/animate.css">
    <!-- Latest compiled and minified JavaScript -->
    <script src="lib/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="lib/angular/angular.js"></script>
    <script type="text/javascript" src="lib/angular/angular-resource.js"></script>
    <script type="text/javascript" src="js/profileInfo.js"></script>
    <script type="text/javascript" src="js/controllers.js"></script>
    <script type="text/javascript" src="js/services.js"></script>
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
            <li class="active"><a href="#">Profile</a></li>
            <li><a href="connections.jsp">Connections</a></li>


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

<div class="animated lightSpeedIn">
    <div class="container">

        <div class="jumbotron">
            <div class="row">
                <div class="col-md-3 col-md-offset-1 text-center">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <img style="height: 25%; width: 70%;" src="{{profile.pictureUrl}}" class="img-thumbnail"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-8">
                    <h2 class="welcomeText heading">{{profile.formattedName}}</h2>
                </div>
                <h5 class="welcomeText headingInfo">{{profile.headLine}}</h5>
            </div>
        </div>
    </div>

    <div class="container">

        <h3>Background </h3>

        <div class="jumbotron">
            <div ng-show="profile.summary">
                <div class="row">
                    <div class="col-md-12" align="justify">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><b>Summary</b>

                                    <h3 class="panel-title"></h3></h3>
                            </div>
                            <div class="panel-body">
                                <p class="text-left">{{profile.summary}}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div ng-show="profile.positions">
                <div class="row">
                    <div class="col-md-12" align="justify">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><b>Experience</b>

                                    <h3 class="panel-title"></h3></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div ng-repeat="position in profile.positions">
                                        <div class="col-xs-6">
                                            <h4> {{position.title}} </h4>
                                            <h7>{{position.summary}}</h7>
                                            <br/>
                                            <h7>{{position.startDate.year}}-{{position.startDate.month}} &nbsp;&nbsp;
                                                <span ng-if="position.current == true">Current</span> <span
                                                        ng-if="position.current != true">{{position.endDate.year}}-{{position.endDate.month}}</span>
                                            </h7>
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
            <div ng-show="profile.languages">
                <div class="row">
                    <div class="col-md-12" align="justify">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title"><b>Languages</b>

                                    <h3 class="panel-title"></h3></h3>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div ng-repeat="language in profile.languages">
                                        <div class="col-xs-6">
                                            <h4> {{language.name}} </h4>
                                            <h7>{{language.proficiency}}</h7>
                                            <br/>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div ng-show="profile.skills">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h2 class="panel-title"><b>Skills </b>

                                    <h3 class="panel-title"></h3></h2>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div ng-repeat="skill in profile.skills">
                                        <!-- <div class="col-xs-6">
                                             <h4> {{language.name}} </h4>
                                             <h7>{{language.proficiency}}</h7><br/>
                                         </div>-->
                                        <div class="col-xs-3">
                                            <a href="#" class="btn btn-primary">{{skill.name}}</a>
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


    <div class="container">


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
                                    <div ng-repeat="connection in profile.connections | limitTo: 6">
                                        <div class="col-xs-5">
                                            <img src="{{connection.pictureUrl}}" alt="...">
                                            <h4> {{connection.firstName}}&nbsp;{{connection.lastName}}</h4>
                                            <h7>{{connection.headLine}}</h7>
                                            <br/><br/>
                                        </div>
                                    </div>
                                    <%--    <a href="#" >see more</a>--%>
                                </div>

                            </div>
                            <a style="margin-left:92% " href="connections.jsp">see more</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>
