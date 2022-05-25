<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage User, Clinic/Hospital</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    background-image: url('${pageContext.request.contextPath}/images/bg3.png');
    height: 100vh;
  ">
		<div class="container-sm" style="height: 100vh">
				<div class="container-sm">
					<div class="row">
						<div class="col-12 col-sm-2 justify-content-center">
							<nav class="navbar navbar-light sticky-top"
								style="background-color: #e3f2fd;">
								<form action="/nav/admin" method="get">
									<ul class="navbar-nav">
										<li class="nav-item">
											<div class="row">
												<button type="submit" class="btn btn-black px-4 fs-5"
													name="adminNavButton" value="Home">Home</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="submit"
													class="btn btn-outline-secondary px-4 fs-5"
													name="adminNavButton" value="Tickets">Tickets</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="button" class="btn btn-black px-4 fs-5"
													id="navAdminManage" onclick="toggleManageList()">Managements</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="submit" class="btn btn-black px-4"
													id="navAdminManageOwner" name="adminNavButton"
													value="ManagePetOwner">Pet Owner</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="submit" class="btn btn-black px-4"
													id="navAdminManageVet" name="adminNavButton"
													value="ManageVeterinarian">Veterinarian</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="submit" class="btn btn-black px-4"
													id="navAdminManageCH" name="adminNavButton"
													value="ManageCH">Clinic/Hospital</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="submit" class="btn btn-black px-4 fs-5"
													name="adminNavButton" value="ManageChatbot">Chatbot</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="submit" class="btn btn-black px-4 fs-5"
													id="navAdminCreateAcc" name="adminNavButton"
													value="CreateAcc">Create Acc</button>
											</div>
										</li>
										<li class="nav-item">
											<div class="row">
												<button type="submit" class="btn btn-black px-4 fs-5"
													id="navAdminLogout" name="adminNavButton" value="Logout">Logout</button>
											</div>
										</li>
									</ul>
								</form>
							</nav>
						</div>
						<script
							src="${pageContext.request.contextPath}/js/adminPage/overallFunction.js"></script>

						<div class="col-12 col-sm-8 mt-3">
							<c:if test="${uchType == 'Pet_Owner'}">
								<form action="/manageUCH/admin/petOwner" method="post">
									<div class="container-sm mt-5">
										<div class="card text-dark bg-light">
											<div class="card-header fs-3">
												<c:out value="${petOwner.id}" />
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Username</h5>
														<c:out value="${petOwner.username}" />
													</div>
												</div>
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Phone Number</h5>
														<c:out value="${petOwner.phoneNo}" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Street</h5>
														<c:out value="${petOwner.street}" />
													</div>
												</div>
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Poscode</h5>
														<c:out value="${petOwner.poscode}" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">District</h5>
														<c:out value="${petOwner.district}" />
													</div>
												</div>
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">States</h5>
														<c:out value="${petOwner.states}" />
													</div>
												</div>
											</div>
											<div class="card-body mb-3">
												<div class="card-body">
													<div class="mb-3 row justify-content-sm-center">
														<div class="col-xl-2">
															<button type="button" class="btn btn-primary btn-xxl"
																onclick="history.back()">Confirm</button>
														</div>
														<div class="col-xl-4 ">
															<c:choose>
																<c:when test="${petOwner.user.accStatus == 'BLOCK' }">
																	<button type="submit" class="btn btn-success btn-xxl"
																		id="activateButton" value="Activate"
																		name="manageUCHButton">Activate User</button>
																</c:when>
																<c:otherwise>
																	<button type="submit" class="btn btn-warning btn-xxl"
																		id="blockButton" value="Block" name="manageUCHButton">Block
																		User</button>
																</c:otherwise>
															</c:choose>
														</div>
														<input type="hidden" value="${petOwner.id}" name="id"></input>
													</div>
												</div>
											</div>
										</div>
									</div>
								</form>
							</c:if>
							<c:if test="${uchType == 'Vets'}">
								<form action="/manageUCH/admin/vet" method="post">
									<div class="container-sm mt-5">
										<div class="card text-dark bg-light">
											<div class="card-header fs-3">
												<c:out value="${veterinarian.id}" />
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Real Name</h5>
														<c:out value="${veterinarian.realName}" />
													</div>
												</div>
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Email</h5>
														<c:out value="${veterinarian.email}" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Clinic/Hospital</h5>
														<c:out value="${veterinarian.chId.chName}" />
													</div>
												</div>
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Phone Number</h5>
														<c:out value="${veterinarian.phoneNo}" />
													</div>
												</div>
											</div>
											<div class="card-body mb-3">
												<div class="card-body">
													<div class="mb-3 row justify-content-sm-center">
														<div class="col-sm-2">
															<button type="button" class="btn btn-primary btn-xxl"
																onclick="history.back()">Confirm</button>
														</div>
														<div class="col-sm-4 ">
															<c:choose>
																<c:when
																	test="${veterinarian.user.accStatus == 'ACTIVE' }">
																	<button type="submit" class="btn btn-warning btn-xxl"
																		id="blockButton" value="Block" name="manageUCHButton">Block
																		User</button>
																</c:when>
																<c:otherwise>
																	<button type="submit" class="btn btn-success btn-xxl"
																		id="activateButton" value="Activate"
																		name="manageUCHButton">Activate User</button>
																</c:otherwise>
															</c:choose>
														</div>
														<input type="hidden" value="${veterinarian.id}" name="id"></input>
													</div>
												</div>
											</div>
										</div>
									</div>
								</form>
							</c:if>
							<c:if test="${uchType == 'CH'}">
								<div class="container-sm mt-5">
									<form action="/manageUCH/admin/editCH" method="post">
										<div class="card text-dark bg-light">
											<div class="card-header fs-3">
												<c:out value="${ch.chName}" />
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Street</h5>
														<input type="text" class="form-control" name="street"
															id="street" value="${ch.street}" maxlength="140">
													</div>
												</div>
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Poscode</h5>
														<input type="text" class="form-control" name="poscode"
															id="poscode" value="${ch.poscode}" maxlength="5">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">District</h5>
														<input type="text" class="form-control" name="district"
															id="district" value="${ch.district}" maxlength="18">
													</div>
												</div>
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">States</h5>
														<input type="text" class="form-control" name="states"
															id="states" value="${ch.states}" maxlength="45">
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-4">
													<div class="card-body mb-3">
														<h5 class="card-title">Status</h5>
														<c:out value="${ch.chStatus}" />
													</div>
												</div>
												<div class="col-sm-4">
													<div class="card-body mb-3">
														<h5 class="card-title">Ratings</h5>
														<c:out value="${ch.ratings}" />
													</div>
												</div>
												<div class="col-sm-4">
													<div class="card-body mb-3">
														<h5 class="card-title">Rating No</h5>
														<c:out value="${ch.ratingNo}" />
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Phone Number</h5>
														<input type="text" class="form-control" name="phoneNo"
															id="phoneNo" value="${ch.phoneNo}" maxlength="12">
													</div>
												</div>
											</div>
											<c:choose>
												<c:when test="${operationTimeList.size() > 0 }">
													<c:forEach items="${operationTimeList}" var="operationTime">
														<div class="card-body mb-3 fs-5">
															<div class="row justify-content-start">
																<div class="col-12 col-sm-3">
																	<label for="dayLabel" class="col-form-label">${operationTime.day}</label>
																</div>
																<div class="col-12 col-sm-3">
																	<input type="time" class="form-control"
																		name="startTime${operationTime.day}"
																		id="startTime${operationTime.day}"
																		value="${operationTime.startTime}">
																</div>
																<div class="col-12 col-sm-3">
																	<input type="time" class="form-control"
																		name="endTime${operationTime.day}"
																		id="endTime${operationTime.day}"
																		value="${operationTime.endTime}">
																</div>
															</div>
														</div>
													</c:forEach>
												</c:when>
												<c:otherwise>
													<div class="card-body mb-3 fs-5">
														<div class="row justify-content-start">
															<div class="col-12 col-sm-3">
																<label for="monLabel" class="col-form-label">Monday</label>
															</div>
															<div class="col-12 col-sm-3">
																<input type="time" class="form-control"
																	name="startTimeMonday" id="startTimeMonday">
															</div>
															<div class="col-12 col-sm-3">
																<input type="time" class="form-control"
																	name="endTimeMonday" id="endTimeMonday">
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
																<input type="time" class="form-control"
																	name="endTimeTuesday" id="endTimeTuesday">
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
																<input type="time" class="form-control"
																	name="endTimeFriday" id="endTimeFriday">
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
																<label for="sunLabel" class="col-form-label">Sunday</label>
															</div>
															<div class="col-12 col-sm-3">
																<input type="time" class="form-control"
																	name="startTimeSunday" id="startTimeSunday">
															</div>
															<div class="col-12 col-sm-3">
																<input type="time" class="form-control"
																	name="endTimeSunday" id="endTimeSunday">
															</div>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
											<div class="card-body mb-3">
												<div class="card-body">
													<div class="mb-3 row justify-content-sm-center">
														<div class="col-xl-2">
															<button type="submit" class="btn btn-primary btn-xxl"
																id="saveButton" value="Save" name="manageUCHButton">Save</button>
														</div>
														<div class="col-xl-2">
															<button type="button" class="btn btn-secondary btn-xxl"
																onclick="history.back()">Back</button>
														</div>
														<c:if test="${ch.chStatus != 'ACTIVE' }">
															<div class="col-xl-2 ">
																<button type="submit" class="btn btn-success btn-xxl"
																	id="activateButton" value="Activate"
																	name="manageUCHButton">Activate</button>
															</div>
														</c:if>
														<c:if test="${ch.chStatus == 'ACTIVE' }">
															<div class="col-xl-2 ">
																<button type="submit" class="btn btn-success btn-xxl"
																	id="blockButton" value="Block" name="manageUCHButton">Block
																</button>
															</div>
														</c:if>
														<input type="hidden" value="${ch.chId}" name="id"></input>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
							</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
</body>
<script src="${pageContext.request.contextPath}/js/inputFilter.js"></script>
<script type="text/javascript">
	$(function() {
		$('#saveButton').attr('disabled', true);
		$('#street').focus();
		$('#street, #poscode, #district, #states, #phoneNo').bind(
				'keyup change blur', function() {
					if (allFilled()) {
						$('#saveButton').prop('disabled', false);
					} else {
						$('#saveButton').prop('disabled', true);
					}
				});

		function allFilled() {
			var filled = true;
			$('#street, #poscode, #district, #states').each(function() {
				if ($(this).val() == '')
					filled = false;
			});
			return filled;
		}

	});

	setInputFilter(document.getElementById("phoneNo"), function(value) {
		return /^\d*$/.test(value);
	});
	setInputFilter(document.getElementById("poscode"), function(value) {
		return /^\d*$/.test(value);
	});
</script>
</html>