<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
body {
	background-color: aliceblue;
}

}
.form-group {
	width: 300px;
}

.well {
	background-color: azure;
	height: 330px;
	width: 330px;
	position: relative;
	top: 100px;
}
</style>



</head>
<body>
	<c:if test="${not empty error}">
   ${error}
</c:if>

	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<div class="well">
				<h2>Log In</h2>
				<form:form method="POST" action="loginform" modelAttribute="user">

					<div class="form-group">
						<label for="email">Email:</label>
						<form:input path="email" class="form-control" id="lastName"
							placeholder="Email" required="required" />
					</div>

					<div class="form-group">
						<label for="password">Password:</label>
						<form:input type="password" path="password" class="form-control"
							id="password" placeholder="Password" required="required" />
					</div>
					<button type="submit" class="btn btn-primary" style="width: 100px">Submit</button>
					<button type="reset" class="btn btn-primary" style="width: 100px"
						value="reset">Reset</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>