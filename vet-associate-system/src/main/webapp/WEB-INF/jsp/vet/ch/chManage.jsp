<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Clinic/Hospital</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="bg-image"
		style="
    background-image: url('${pageContext.request.contextPath}/images/bg2.png');
    height: 100vh;
  ">
		<div class="mask" style="background-color: rgba(0, 0, 0, 0.6);">
			<div class="d-flex justify-content-center align-items-center h-100">
				<div class="container-sm">
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
													name="vetNavButton" value="MyCH">My
													Clinic/Hospital</button>
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
								<form action="/manageUCH/vet/editCH" method="post">
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
															<label for="sunLabel" class=" col-form-label">Sunday</label>
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
													<div class="col-sm-4">
														<button type="submit"
															class="btn btn-outline-success btn-sm"
															name="manageCHButton" value="manageApt">Check
															Appointment</button>
													</div>
													<div class="col-sm-2">
														<button type="submit" class="btn btn-primary btn-sm"
															name="manageCHButton" value="save" id="saveButton">Save</button>
													</div>
													<div class="col-sm-2">
														<button type="button" class="btn btn-secondary btn-sm"
															onclick="history.back()">Back</button>
													</div>
													<input type="hidden" value="${ch.chId}" name="id"></input>
												</div>
											</div>
										</div>
									</div>
								</form>
								<div class="container-sm mt-5 justify-content-center"
									id="commentDiv" style="display: block;">
									<c:choose>
										<c:when test="${comments.size() > 0 }">
											<c:forEach items="${comments}" var="comment">
												<div class="container-sm justify-content-start">
													<div class="card">
														<div class="card-body">
															<div class="row mb-3">
																<div class="col-sm-6">
																	<p class="text-dark text-start fs-5">${comment.writter.name}</p>
																</div>
																<div class="col-sm-3">
																	<p class="text-black-50 text-end fs-6">${comment.timeString}</p>
																</div>
																<div class="col-sm-3">
																	<p class="text-black-50 text-end fs-6">Rating:
																		${comment.ratings}</p>
																</div>
															</div>
															<div class="row mb-10">
																<p class="text-black-50 text-end fs-6">${comment.descriptions}</p>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<div class="container-sm justify-content-center">
												<span class="badge rounded-pill bg-info text-dark">There
													are no comments about this clinic/hospital currently.</span>
											</div>
										</c:otherwise>
									</c:choose>
								</div>
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
	setInputFilter(document.getElementById("phoneNo"), function(value) {
		return /^\d*$/.test(value);
	});
	setInputFilter(document.getElementById("poscode"), function(value) {
		return /^\d*$/.test(value);
	});

	$(function() {
		$('#saveButton').prop('disabled', true);
		$('#street').focus();
		$('#street, #poscode, #district, #states').bind('keyup change blur',
				function() {
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
</script>
</html>