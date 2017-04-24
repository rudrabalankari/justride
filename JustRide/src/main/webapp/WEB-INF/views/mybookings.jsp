<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel=stylesheet
	href="http://localhost:8080/justride/resources/css/MyReservation.css">

</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a href="#" class="navbar-brand" style="font-size: 40px">JustRide</a>
		</div>
		<ul class="nav navbar-nav navbar-right">
			Hello ${userName}
			<a href="#" button type="submit" class="btn btn-primary"
				style="margin: 13px 12px 12px 10px;">Logout</a>
		</ul>
	</div>
	</nav>
	<c:if test="${not empty error}">
   ${error}
</c:if>
	<div class="container"
		style="vertical-align: center; align: center; padding: inherit">
		<h2>
			<img
				src="http://localhost:8080/justride/resources/images/JustRideLogo.png"
				id="logo"> MY RESERVATIONS
		</h2>
	</div>

	<div class="container text-center" id="mainCon">
		<div class="row">
			<div class="col-sm-10 col-sm-offset-0 well" id="mainCol">

				<div class="container-fluid" id="R-table">
					<table class="table table-striped" border="0">
						<tr>
							<th>Booking ID</th>
							<th>Pickup Date</th>
							<th>Dropoff Date</th>
							<th>Pickup Location</th>
							<th>Dropoff Location</th>
							<th>Vehicle</th>
							<th></th>
						</tr>
						<c:forEach items="${bookings}" var="booking">
							<tr>
								<td>${booking.bookingId}</td>
								<td>${booking.stringInTime}</td>
								<td>${booking.stringOutTime}</td>
								<td>${booking.pickupLocation}</td>
								<td>${booking.pickupLocation}</td>
								<td>${booking.carName}</td>
								<td><form action="deleteBooking">
										<button type="submit" name="bookingId"
											value="${booking.bookingId}" class="btn btn-default">
											<span class="glyphicon glyphicon-remove"></span>
										</button>
									</form></td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
</html>