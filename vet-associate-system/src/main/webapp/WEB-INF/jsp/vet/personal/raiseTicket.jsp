<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Inquiry</title>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div
		class="bg-image d-flex justify-content-center align-items-center h-100"
		style="
    background-image: url('${pageContext.request.contextPath}/images/bg4.png');
    height: 100vh;
  ">
		<div class="container-sm" style="height: 100vh">
			<div class="row">
				<div class="col-12 col-sm-2 justify-content-center">
					<nav class="navbar navbar-light sticky-top"
						style="background-color: #e3f2fd;">
						<form action="/nav/vet" method="get">
							<ul class="navbar-nav">
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											name="vetNavButton" value="Home">Home</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											name="vetNavButton" value="MyCH">My Clinic/Hospital</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											name="vetNavButton" value="Conversation">
											Conversation<span class="badge bg-danger"
												id="notificationBadge" style="display: none;"></span>
										</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											id="navVetProfile" name="vetNavButton" value="Profile">Profile</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											id="navVetInquiry" name="vetNavButton" value="Inquiry">Inquiry</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											id="navVetLogout" name="vetNavButton" value="Logout">Logout</button>
									</div>
								</li>
							</ul>
						</form>
					</nav>
				</div>
				<script
					src="${pageContext.request.contextPath}/js/veterinarianPage/overallFunction.js"></script>
				<div class="col-12 col-sm-8 mt-3">
					<form action="/ticket/vet/create" method="post"
						enctype="multipart/form-data">
						<div class="card text-dark bg-light">
							<div class="card-header mb-3 fs-3">Raise an Inquiry</div>
							<div class="mb-3 row justify-content-center fs-5">
								<label for="ticketTitleLabel"
									class="col-12 col-sm-3 col-form-label">Title</label>
								<div class="col-sm-8">
									<input type="text" class="form-control" name="title" id="title"
										placeholder="Please enter inquiry title" />
								</div>
							</div>
							<div class="mb-3 row justify-content-center fs-5">
								<label for="questionsLabel" class="col-sm-3 col-form-label">Description</label>
								<div class="co-12 col-sm-8">
									<textarea class="form-control" name="description"
										id="description" placeholder="Please enter the description"
										rows="3" maxlength="500"></textarea>
								</div>
							</div>
							<div class="mb-3 row justify-content-center fs-5">
								<label for="provenImage" class="col-12 col-sm-3 col-form-label">Support
									Image</label>
								<div class="col-12 col-sm-8 mt-3">
									<input class="form-control" type="file"
										accept=".png, .jpg, .jpeg" id="provenImage" name="provenImage">
								</div>
							</div>
							<div class="card-body mb-3">
								<div class="card-body">
									<div class="mb-3 row justify-content-sm-center">
										<div class="col-12 col-sm-2">
											<button type="submit" class="btn btn-primary btn-xxl fs-5"
												id="submitButton" value="Save" name="manageTicketButton">Save</button>
										</div>
										<div class="col-12 col-sm-2">
											<button type="button" class="btn btn-secondary btn-xxl fs-5"
												onclick="history.back()">Back</button>
										</div>
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/js/veterinarianPage/raiseTicket.js"></script>
</html>