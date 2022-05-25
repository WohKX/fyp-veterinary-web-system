<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Owner Register</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
<body>
	<div class="bg-image d-flex justify-content-center align-items-center"
		style="
    background-image: url('${pageContext.request.contextPath}/images/bg1.png');
    height: 100vh;
  ">
		<div class="container-sm mt-5" style="height: 100vh">
			<div class="card text-center text-dark bg-light">
				<form action="/register/petOwner" method="post">

					<div class="card-header fs-3">Pet Owner Register</div>
					<div class="card-body">
						<div class="mb-3 row">
							<label for="nameLabel" class="col-sm-3 col-form-label">Name</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="username"
									id="username" placeholder="Insert Your Name"
									onkeyup="valUsername()" onblur="valUsername()" maxlength="40" />
							</div>
							<div class="col-sm-3">
								<span class="badge bg-light text-danger pt-3" id="usernameErr"></span>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="emailLabel" class="col-sm-3 col-form-label">Email</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="email" id="email"
									placeholder="Insert Your Email" onkeyup="valEmail()"
									onchange="valEmail()" oninput="valEmail()" onblur="valEmail()"
									maxlength="70" />
							</div>
							<div class="col-sm-3">
								<span class="badge bg-light text-danger pt-3" id="emailErr"></span>
							</div>
						</div>
						<div class="mb-3 row">
							<label for="passwordLabel" class="col-sm-3 col-form-label">Password</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" name="password"
									id="password" placeholder="Insert Your Password" maxlength="25"
									onkeyup="valPassword(),confirmPassword()"
									onblur="valPassword(),confirmPassword()" />
							</div>
							<div class="col-sm-3">
								<span class="badge bg-light text-danger pt-3" id="passwordErr"></span>
							</div>
						</div>
						<div class="mb-5 row">
							<label for="passwordLabel2" class="col-sm-3 col-form-label">Confirm
								Password</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" name="password2"
									id="password2" placeholder="Confirm Your Password"
									maxlength="25" onkeyup="confirmPassword()"
									onblur="confirmPassword()" />
							</div>
							<div class="col-sm-3">
								<span class="badge bg-light text-danger pt-3" id="passwordErr2"></span>
							</div>
						</div>
						<div class="mb-3 row justify-content-sm-center">
							<div class="col-xl-1">
								<button type="submit" class="btn btn-primary btn-xxl"
									id="submitButton" value="Register" name="registerButton">Register</button>
							</div>
							<div class="col-xl-1">
								<button type="reset" class="btn btn-secondary btn-xxl">Reset</button>
							</div>
							<div class="col-xl-1">
								<button type="button" class="btn btn-secondary btn-xxl"
									onclick="history.back()">Cancel</button>
							</div>
							<input type="hidden" value="Pet_Owner" name="usertype"></input>
						</div>
						<span class="badge rounded-pill bg-info text-dark" id="msg"><c:out
								value="${msg}" /></span>
					</div>

				</form>
			</div>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/js/petOwnerPage/register.js"></script>
<script type="text/javascript">
	//setInterval(function(){countNewMessage(${msg}),countMessage(${msg});},6000);

	function countNewMessage(test) {
		var dataToBeSend = {
			id : test,
			user : test
		};
		$.ajax({
			url : 'http://localhost:8080/register/test',
			type : "POST",
			data : dataToBeSend,
			dataType : 'json',
			success : function(result) {
				console.log(result);
				const msg = document.getElementById("msg");
				//msg.innerHTML = msg.innerHTML + result.key1;
				let userList = result.userList;
				userList.map(function(user) {
					console.log(user.id);
					msg.innerHTML = user.name;
					msg.textContent = user.name;

				});

			},
			error : function(error) {
				console.log('Error ${error}')
			}
		});
	}
</script>
</html>