<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Profile</title>
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
						<form action="/nav/petOwner" method="get">
							<ul class="navbar-nav">
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											name="ownerNavButton" value="Home">Home</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="button" class="btn btn-black px-4 fs-5"
											id="navOwnerVCH" onclick="toggleVCHList()">Vets,
											Clinic/Hospital</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4"
											id="navOwnerVets" name="ownerNavButton" value="SearchCH">Search
											Clinic/Hospital</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4"
											id="navOwnerCH" name="ownerNavButton" value="SearchVet">Search
											Veterinarian</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											name="ownerNavButton" value="Conversation">
											Conversation<span class="badge bg-danger"
												id="notificationBadge" style="display: none;"></span>
										</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="button" class="btn btn-black px-4 fs-5"
											id="navOwnerPersonal" onclick="togglePersonalList()">Personal</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4"
											id="navOwnerProfile" name="ownerNavButton" value="Profile"
											disabled>Profile</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4"
											id="navOwnerInquiry" name="ownerNavButton" value="Inquiry">Inquiry</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											name="ownerNavButton" value="Appointment">My
											Appointment</button>
									</div>
								</li>
								<li class="nav-item">
									<div class="row">
										<button type="submit" class="btn btn-black px-4 fs-5"
											id="navOwnerLogout" name="ownerNavButton" value="Logout">Logout</button>
									</div>
								</li>
							</ul>
						</form>
					</nav>
				</div>
				<script
					src="${pageContext.request.contextPath}/js/petOwnerPage/overallFunction.js"></script>


				<div class="col-12 col-sm-8 mt-3">
					<div class="container-sm mt-5">
						<form action="/manageUCH/petOwner/editProfile" method="post">
							<div class="card text-dark bg-light">
								<div class="card-header fs-3">Edit Your Profile</div>
								<div class="card-body">
									<div class="mb-3 row">
										<label for="nameLabel" class="col-sm-3 col-form-label">Name</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="username"
												id="username" value="${petOwner.username}" maxlength="40">
										</div>
										<div class="col-sm-3">
											<span class="badge bg-light text-danger pt-3"
												id="usernameErr"></span>
										</div>
									</div>
									<div class="mb-3 row">
										<label for="emailLabel" class="col-sm-3 col-form-label">Email</label>
										<div class="col-sm-6">
											<p class="text-start fs-5">${petOwner.id}</p>
										</div>
										<div class="col-sm-3"></div>
									</div>

									<div class="mb-3 row">
										<label for="streetLabel" class="col-sm-3 col-form-label">Street</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="street"
												id="street" value="${petOwner.street}" />
										</div>
										<div class="col-sm-3"></div>
									</div>
									<div class="mb-3 row">
										<label for="poscodeLabel" class="col-sm-3 col-form-label">Poscode</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="poscode"
												id="poscode" value="${petOwner.poscode}" maxlength="5" />
										</div>
										<div class="col-sm-3"></div>
									</div>
									<div class="mb-3 row">
										<label for="districtLabel" class="col-sm-3 col-form-label">District</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="district"
												id="district" value="${petOwner.district}" />
										</div>
										<div class="col-sm-3"></div>
									</div>
									<div class="mb-3 row">
										<label for="statesLabel" class="col-sm-3 col-form-label">States</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="states"
												id="states" value="${petOwner.states}" />
										</div>
										<div class="col-sm-3"></div>
									</div>
									<div class="mb-5 row">
										<label for="phoneNoLabel" class="col-sm-3 col-form-label">PhoneNo</label>
										<div class="col-sm-6">
											<input type="text" class="form-control" name="phoneNo"
												id="phoneNo" value="${petOwner.phoneNo}" maxlength="12" />
										</div>
										<div class="col-sm-3"></div>
									</div>
									<div class="mb-3 row justify-content-center">
										<div class="col-sm-6">
											<span class="badge bg-light text-info fs-5">${msg}</span>
										</div>
									</div>
								</div>
								<div class="card-body mb-3">
									<div class="card-body">
										<div class="mb-3 row justify-content-end">
											<div class="col-sm-2">
												<button type="submit" class="btn btn-primary btn-xxl fs-5"
													id="saveButton">Save</button>
											</div>
											<div class="col-sm-2 ">
												<button type="button" class="btn btn-secondary btn-xxl fs-5"
													onclick="history.back()">Back</button>
											</div>
											<div class="col-sm-4">
												<button type="button" class="btn btn-secondary btn-sm fs-5"
													id="changePasswordButton" onclick="toggleChangePasswDiv()">Change
													Password</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</form>
						<div class="mt-3 row justify-content-center" id="changePasswDiv"
							style="display: none;">

							<div class="col-12 col-sm-8">
								<form action="/manageUCH/petOwner/changePassw" method="post">
									<div class="card text-dark bg-light">
										<div class="card-header fs-4">${petOwner.username}</div>
										<div class="card-body">
											<div class="mb-3 row">
												<label for="oldPasswLabel"
													class="col-sm-4 col-form-label fs-6">Current
													Password:</label>
												<div class="col-sm-8">
													<input type="password" class="form-control fs-6"
														name="oldPassw" id="oldPassw" maxlength="25"
														onkeyup="valPassword()" />
												</div>
											</div>
											<div class="mb-3 row">
												<label for="newPasswLabel"
													class="col-sm-4 col-form-label fs-6">New Password:</label>
												<div class="col-sm-8">
													<input type="password" class="form-control fs-6"
														name="newPassw" id="newPassw" maxlength="25"
														onkeyup="valPassword()" />
												</div>
											</div>
											<div class="mb-3 row">
												<label for="conPasswLabel"
													class="col-sm-4 col-form-label fs-6">Confirm
													Password:</label>
												<div class="col-sm-8">
													<input type="password" class="form-control fs-6"
														name="conPassw" id="conPassw" maxlength="25"
														onkeyup="valPassword()" />
												</div>
											</div>
											<div class="mb-3 row">
												<div class="col-sm-6">
													<button type="submit" class="btn btn-primary btn-xxl"
														id="changePasswButton">Confirm</button>
												</div>
											</div>
											<div class="mb-3 row">
												<span class="badge bg-light text-danger pt-3" id="msg1"></span>
											</div>
											<div class="mb-3 row">
												<span class="badge bg-light text-danger pt-3" id="msg2"></span>
											</div>
											<div class="mb-3 row">
												<span class="badge bg-light text-danger pt-3" id="msg3"></span>
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${pageContext.request.contextPath}/js/inputFilter.js"></script>
<script type="text/javascript">
	setInputFilter(document.getElementById("poscode"), function(value) {
		return /^\d*$/.test(value);
	});
	setInputFilter(document.getElementById("phoneNo"), function(value) {
		return /^\d*$/.test(value);
	});
</script>
<script
	src="${pageContext.request.contextPath}/js/petOwnerPage/editProfile.js"></script>
</html>