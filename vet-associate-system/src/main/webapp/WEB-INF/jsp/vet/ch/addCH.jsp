<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Veterinarian Add Clinic/Hospital</title>
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
    background-image: url('${pageContext.request.contextPath}/images/bg2.png');
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
					<div class="container-sm mt-5">
						<form action="/ticket/vet/addCH" method="post"
							enctype="multipart/form-data">
							<div class="card text-dark bg-light">
								<div class="card-header fs-3">Add New Clinic/Hospital</div>
								<div class="row">
									<div class="card-body mb-3">
										<h5 class="card-title">Clinic/Hospital Name</h5>
										<input type="text" class="form-control" name="chName"
											id="chName" maxlength="75">
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<h5 class="card-title">Street</h5>
											<input type="text" class="form-control" name="street"
												id="street" maxlength="140">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<h5 class="card-title">Poscode</h5>
											<input type="text" class="form-control" name="poscode"
												id="poscode" maxlength="5">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<h5 class="card-title">District</h5>
											<input type="text" class="form-control" name="district"
												id="district" maxlength="18">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<h5 class="card-title">States</h5>
											<input type="text" class="form-control" name="states"
												id="states" maxlength="45">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<h5 class="card-title">Phone Number</h5>
											<input type="text" class="form-control" name="phoneNo"
												id="phoneNo" maxlength="12">
										</div>
									</div>
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<h5 class="card-title">Proven Image</h5>
											<input class="form-control" type="file"
												accept=".png, .jpg, .jpeg" id="provenImage"
												name="provenImage">
										</div>
									</div>
								</div>
								<div class="row">
									<div class="card-body mb-3">
										<h5 class="card-title">Description</h5>
										<textarea class="form-control" name="description"
											id="description" placeholder="Please enter the description"
											rows="3"></textarea>
									</div>
								</div>
								<div class="card-body fs-5">
									<div class="row justify-content-start">
										<div class="col-12 col-sm-3">
											<label for="monLabel" class="col-form-label">Monday</label>
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="startTimeMonday" id="startTimeMonday">
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control" name="endTimeMonday"
												id="endTimeMonday">
										</div>
									</div>
									<div class="row justify-content-start">
										<div class="col-12 col-sm-3">
											<label for="tuesLabel" class="col-form-label">Tuesday</label>
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="startTimeTuesday" id="startTimeTuesday">
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control" name="endTimeTuesday"
												id="endTimeTuesday">
										</div>
									</div>
									<div class="row justify-content-start">
										<div class="col-12 col-sm-3">
											<label for="wedLabel" class="col-form-label">Wednesday</label>
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="startTimeWednesday" id="startTimeWednesday">
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="endTimeWednesday" id="endTimeWednesday">
										</div>
									</div>
									<div class="row justify-content-start">
										<div class="col-12 col-sm-3">
											<label for="thursLabel" class="col-form-label">Thursday</label>
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="startTimeThursday" id="startTimeThursday">
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="endTimeThursday" id="endTimeThursday">
										</div>
									</div>
									<div class="row justify-content-start">
										<div class="col-12 col-sm-3">
											<label for="friLabel" class="col-form-label">Friday</label>
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="startTimeFriday" id="startTimeFriday">
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control" name="endTimeFriday"
												id="endTimeFriday">
										</div>
									</div>
									<div class="row justify-content-start">
										<div class="col-12 col-sm-3">
											<label for="satLabel" class="col-form-label">Saturday</label>
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="startTimeSaturday" id="startTimeSaturday">
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="endTimeSaturday" id="endTimeSaturday">
										</div>
									</div>
									<div class="row justify-content-start">
										<div class="col-12 col-sm-3">
											<label for="sunLabel" class=" col-form-label">Sunday</label>
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control"
												name="startTimeSunday" id="startTimeSunday">
										</div>
										<div class="col-12 col-sm-3">
											<input type="time" class="form-control" name="endTimeSunday"
												id="endTimeSunday">
										</div>
									</div>
								</div>
								<div class="card-body mb-3">
									<div class="card-body">
										<div class="mb-3 row justify-content-sm-center">
											<div class="col-xl-2">
												<button type="submit" class="btn btn-primary btn-xxl"
													id="submitButton">Submit</button>
											</div>
											<div class="col-xl-2">
												<button type="button" class="btn btn-secondary btn-xxl"
													onclick="history.back()">Cancel</button>
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
	</div>

</body>
<script
	src="${pageContext.request.contextPath}/js/veterinarianPage/createCH.js"></script>
<script src="${pageContext.request.contextPath}/js/inputFilter.js"></script>
<script type="text/javascript">
	setInputFilter(document.getElementById("phoneNo"), function(value) {
		return /^\d*$/.test(value);
	});
	setInputFilter(document.getElementById("poscode"), function(value) {
		return /^\d*$/.test(value);
	});
</script>
</html>