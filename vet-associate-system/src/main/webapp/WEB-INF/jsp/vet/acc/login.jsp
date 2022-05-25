<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Veterinarian Login</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="bg-image d-flex justify-content-center align-items-center"
		style="
    background-image: url('${pageContext.request.contextPath}/images/bg1.png');
    height: 100vh;
  ">
		<div class="container-sm justify-content-center mt-5"
			style="height: 100vh">
			<form action="/login/vet" method="post">
				<div class="card text-center text-dark bg-light">
					<div class="card-header fs-3">Veterinarian Login</div>
					<div class="card-body">
						<div class="mb-3 row">
							<label for="userIdLabel" class="col-sm-3 col-form-label">UserID</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="userId"
									id="userId" placeholder="Insert Your IC here"
									onkeyup="valUserId()" onblur="valUserId()" />
							</div>
							<div class="col-sm-3">
								<span class="badge bg-light text-danger pt-3" id="userIdErr"></span>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="passwordLabel" class="col-sm-3 col-form-label">Password</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" name="password"
									id="password" onkeyup="valPassword()" onblur="valPassword()" />
							</div>
							<div class="col-sm-3">
								<span class="badge bg-light text-danger pt-3" id="passwordErr"></span>
							</div>
						</div>
						<div class="mb-3 row">
							<div class="col-12 col-sm-6">
								<button type="submit" class="btn btn-primary btn-xxl"
									name="submitButton" id="submitButton" value="login">Login</button>
							</div>
							<div class="col-12 col-sm-6">
								<button type="reset" class="btn btn-secondary btn-xxl"
									value="Reset">Reset</button>
							</div>
						</div>
						<div class="mb-3 row justify-content-center">
							<div class="col-sm-6">
								<button type="submit" class="btn btn-outline-primary btn-xxl"
									name="submitButton" id="submitButton" value="register">Register
									New Acc</button>
							</div>
						</div>
					</div>
					<div class="row justify-content-center">
						<div class="col-12 col-sm-4">
							<span class="badge rounded-pill bg-info text-dark fs-6"><c:out
									value="${param.msg}"></c:out></span>
						</div>
					</div>
				</div>
			</form>
			<div class="row justify-content-center">
				<div class="row fs-5">
					<a href="/petOwner/acc/login">Login as Pet Owner</a>
				</div>
				<div class="row fs-5">
					<a href="/vet/acc/forgetPassw">Forget Password</a>
				</div>
				<div class="row fs-6">
					<div class="col-12">
						<span class="badge rounded-pill bg-light text-dark fs-6">Contact
							Administrator through wkxtp056443@gmail.com if you faced any
							problem.</span>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/js/veterinarianPage/login.js"></script>
</html>