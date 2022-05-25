<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Veterinarian Forget Password</title>
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
		<div class="container-sm justify-content-center mt-5"
			style="height: 100vh">
			<div class="card">
				<div class="card-header fs-3">Forget Password</div>
				<div class="card-body">
					<form action="/login/vet/forgetPassword" method="get">
						<div class="mb-3 row justify-content-start">
							<label for="idLabel" class="col-sm-3 col-form-label">UserID</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="userId"
									id="userId" placeholder="Insert Your IC here" />
							</div>
						</div>
						<div class="mb-3 row justify-content-start">
							<label for="idLabel" class="col-sm-3 col-form-label">Email</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" name="email" id="email"
									placeholder="Insert Your Email here" />
							</div>
						</div>
						<div class="mb-3 row justify-content-center">
							<div class="col-12 col-sm-3">
								<button type="submit" class="btn btn-primary btn-sm fs-5">Confirm</button>
							</div>
							<div class="col-12 col-sm-3">
								<button type="button" class="btn btn-secondary btn-sm fs-5"
									onclick="history.back()">Back</button>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>