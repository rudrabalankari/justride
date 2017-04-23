<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<c:if test="${not empty error}">
   ${error}
</c:if>
	<div class="container">
		<div class="col-md-4 col-md-offset-4">
			<div class="well">
				<h2>Register :</h2>
				<form:form method="POST" action="registerform" modelAttribute="user">
					<div class="form-group">
						<label for="First Name">First Name:</label>
						<form:input type="firstname" path="firstName" class="form-control"
							id="firstName" placeholder="First Name" required="required" />
					</div>
					<div class="form-group">
						<label for="lastName">Last Name:</label>
						<form:input path="lastName" class="form-control" id="lastName"
							placeholder="Last Name" required="required" />
					</div>
					<div class="form-group">
						<label for="email">Email:</label>
						<form:input path="email" class="form-control" id="lastName"
							placeholder="Email" required="required" />
					</div>

					<div class="form-group">
						<label for="phonenum">Phone Number (format: xxx-xxx-xxxx):</label>
						<form:input type="tel" maxlength="12"
							pattern="^\d{3}-\d{3}-\d{4}$" path="phone" class="form-control"
							id="phone" placeholder="980-319-3433" required="required" />

					</div>

					<div class="form-group">
						<label for="phonenum">Credit Card No:</label>
						<form:input type="tel" maxlength="9" path="cardNo"
							class="form-control" id="cardNo" pattern="^\d{4}-\d{4}$"
							placeholder="(xxxx-xxxx)" required="required" />
					</div>
	
					<div class="form-group">
						<label for="pwd">Zip Code:</label>
						<form:input type="zip" maxlength="5" minlength="5" path="zip"
							class="form-control" id="zip" placeholder="28262"
							required="required" />
					</div>

					<div class="form-group">
						<label for="password">Password:</label>
						<form:input type="password" path="password" class="form-control"
							id="cnfPass" placeholder="Password" required="required" />
					</div>


					<div class="form-group">
						<label for="reenter">Re-Enter Password:</label>
						<form:input class="form-control" type="password" path="cnfPwd"
							data-match="#password" name="cnfPwd"
							data-match-error="Whoops, these don't match" id="cnfPwd"
							placeholder="Re-Enter Password" required="required" />
					</div>


					<button type="submit" class="btn btn-primary" style="width: 100px">Submit</button>
					<button type="reset" class="btn btn-primary"
						value="reset" style="width: 100px">Reset</button>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>