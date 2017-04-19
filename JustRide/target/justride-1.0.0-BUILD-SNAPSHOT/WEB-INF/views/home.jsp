<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>

<meta charset="utf-8"></meta>
<meta name="viewport" content="width=device-width, initial-scale=1"></meta>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</link>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>

<!-- <script src="resources/js/controller2.js"></script> -->
<link rel="stylesheet" href="resources/css/viewcars.css"></link>
<link rel="stylesheet" type="text/css"
	href="resources/css/HomeScreenCSS.css">
</link>


<script
	src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.37/js/bootstrap-datetimepicker.min.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$(function() {
			$('#datetimepicker6').datetimepicker();
			$('#datetimepicker7').datetimepicker({
				useCurrent : false
			//Important! See issue #1075
			});
			$("#datetimepicker6").on("dp.change", function(e) {
				$('#datetimepicker7').data("DateTimePicker").minDate(e.date);
			});
			$("#datetimepicker7").on("dp.change", function(e) {
				$('#datetimepicker6').data("DateTimePicker").maxDate(e.date);
			});
		});
	});
</script>

</head>
<body ng-app="app" ng-controller="getcontroller2">
	<!-- <body ng-app="app" ng-controller="getcontroller2"> -->
	<!--  <pre>{{test| json}}</pre>-->

	<!-- <div class="well" ng-repeat="obj in test">
<p><h3>{{obj.id}}</h3></p>
<p><strong><h3>{{obj.type}}</h3></strong></p>
</div>
 -->
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
					aria-expanded="false">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#"><font size="20">JustRide</font></a>
			</div>

			<div class="collapse navbar-collapse"
				id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav navbar-right">
					<!-- <li> -->
					<div class="btn-group" role="group" aria-label="...">
						<a href="justride/login">
							<button type="button" class="button">LOGIN</button>
						</a> <a href="justride/register">
							<button type="button" class="button">REGISTER</button>
						</a>
					</div>
					<!-- </li> -->
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container-fluid -->
	</nav>


	<div class="container" style="width: 600px">
		<section class="fare-finder">
			<form id="Car Search">

				<div>
					Location <select>
						<span class="form-group-addon">LOCATION</span>
						<option value="downtown">Down Town 2</option>
						<option value="concord">Concord Mills</option>
						<option value="uncc">UNC, Charlotte</option>
					</select>
				</div>
				<div class="container-fluid" width="50%">
					<div class="form-group">
						<div class='input-group date' id='datetimepicker6'>
							<span class="form-group-addon">Pick Up</span> <input type="text"
								class="form-control" placeholder="MM/DD/YYYY 12:00 PM">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span></input>
						</div>
					</div>
					<div class="form-group">
						<div class="input-group date" id="datetimepicker7">
							<span class="form-group-addon">Drop Off</span> <input type="text"
								class="form-control" placeholder="MM/DD/YYYY 12:00 PM">
							<span class="input-group-addon"> <span
								class="glyphicon glyphicon-calendar"></span>
							</span></input>
						</div>
					</div>
				</div>
				<!-- container-fluid -->
				<a href="#">
					<button type="button" class="button" style="display: block">SUBMIT</button>
				</a>
			</form>
		</section>
		<div ng-view="true"></div>
	</div>
</body>



</html>
