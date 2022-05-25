<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinic/Hospital Appointment List</title>
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

				<div class="col-12 col-sm-8 mt-3 height: 100vh;">
					<div class="row">
						<div class="container">
							<div class="btn-group" role="group"
								aria-label="Basic radio toggle button group">
								<input type="radio" class="btn-check" name="btnradio"
									id="btnradio1" autocomplete="off" checked
									onclick="showRequested();"> <label
									class="btn btn-outline-primary" for="btnradio1">Requested
									Appointment</label> <input type="radio" class="btn-check"
									name="btnradio" id="btnradio2" autocomplete="off"
									onclick="showAccepted();"> <label
									class="btn btn-outline-primary" for="btnradio2">Accepted
									Appointment</label> <input type="radio" class="btn-check"
									name="btnradio" id="btnradio3" autocomplete="off"
									onclick="showExpired();"> <label
									class="btn btn-outline-primary" for="btnradio3">Expired
									Appointment</label><input type="radio" class="btn-check"
									name="btnradio" id="btnradio4" autocomplete="off"
									onclick="showCompleted();"> <label
									class="btn btn-outline-primary" for="btnradio4">Completed
									Appointment</label><input type="radio" class="btn-check"
									name="btnradio" id="btnradio5" autocomplete="off"
									onclick="showRejected();"> <label
									class="btn btn-outline-primary" for="btnradio5">Rejected
									Appointment</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="container-sm" id="reqested_div"
							style="display: block;">
							<c:choose>
								<c:when test="${aptRequestList.size() > 0}">
									<div id="aptRequestedDiv"
										class="card justify-content-center bg-light"
										style="display: block;">
										<div class="container">
											<div class="card-body">
												<div class="row justify-content-center">
													<label for="dateLabel"
														class="col-12 col-sm-2 col-form-label">Date</label> <label
														for="timeLabel" class="col-12 col-sm-2 col-form-label">Time</label>
													<label for="nameLabel"
														class="col-12 col-sm-3 col-form-label">Name</label> <label
														for="phoneLabel" class="col-12 col-sm-3 col-form-label">PhoneNo</label>
													<label for="acceptLabel"
														class="col-12 col-sm-2 col-form-label">Action</label>
												</div>
											</div>
										</div>
										<div class="container-sm h-85 overflow auto">
											<c:forEach items="${aptRequestList}" var="aptRequest">
												<div class="card-body">
													<form action="/apt/vet/manageApt" method="get">
														<div class="row justify-content-center bg-white">
															<label for="dateLabel"
																class="col-12 col-sm-2 col-form-label pe-3">${aptRequest.dateString}</label>
															<label for="timeLabel"
																class="col-12 col-sm-2 col-form-label">${aptRequest.aptTime}</label>
															<label for="nameLabel"
																class="col-12 col-sm-3 col-form-label">${aptRequest.petOwner.username}</label>
															<label for="emailLabel"
																class="col-12 col-sm-2 col-form-label">${aptRequest.petOwner.phoneNo}</label>
															<div class="col-12 col-sm-1 me-2">
																<button type="submit"
																	class="btn btn-sm btn-outline-info fs-6 "
																	name="manageAptButton" value="Accepted">
																	<small>Accept</small>
																</button>
															</div>
															<div class="col-12 col-sm-1 ms-2">
																<button type="submit"
																	class="btn btn-sm btn-outline-danger fs-6"
																	name="manageAptButton" value="Rejected">
																	<small>Reject</small>
																</button>
															</div>
															<input type="hidden" name="aptId"
																value="${aptRequest.id}">
														</div>
													</form>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="container justify-content-center fs-3">
										<span class="badge rounded-pill bg-info text-dark">There
											are no appointments requested currently.</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="accepted_div" style="display: none;">
							<c:choose>
								<c:when test="${aptAcceptList.size() > 0}">
									<div id="aptAcceptedDiv"
										class="card justify-content-center bg-light"
										style="display: block;">
										<div class="container">
											<div class="card-body">
												<div class="row justify-content-center">
													<label for="dateLabel"
														class="col-12 col-sm-2 col-form-label">Date</label><label
														for="timeLabel" class="col-12 col-sm-2 col-form-label">Time</label>
													<label for="nameLabel"
														class="col-12 col-sm-3 col-form-label">Name</label> <label
														for="phoneLabel" class="col-12 col-sm-3 col-form-label">PhoneNo</label>
													<label for="completeLabel"
														class="col-12 col-sm-2 col-form-label">Complete</label>
												</div>
											</div>
										</div>
										<div class="container-sm h-85 overflow auto">
											<c:forEach items="${aptAcceptList}" var="aptAccept">
												<div class="card-body">
													<form action="/apt/vet/manageApt" method="get">
														<div class="row justify-content-center bg-white">
															<label for="dateLabel"
																class="col-12 col-sm-2 col-form-label">${aptAccept.dateString}</label>
															<label for="timeLabel"
																class="col-12 col-sm-2 col-form-label">${aptAccept.aptTime}</label>
															<label for="nameLabel"
																class="col-12 col-sm-3 col-form-label">${aptAccept.petOwner.username}</label>
															<label for="emailLabel"
																class="col-12 col-sm-3 col-form-label">${aptAccept.petOwner.phoneNo}</label>
															<div class="col-12 col-sm-2">
																<button type="submit"
																	class="btn btn-outline-success btn-sm fs-6"
																	name="manageAptButton" value="Completed">
																	<small>Complete</small>
																</button>
															</div>
															<input type="hidden" name="aptId" value="${aptAccept.id}">
														</div>
													</form>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="container justify-content-center fs-3">
										<span class="badge rounded-pill bg-info text-dark">There
											are no accepted appointments currently.</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="expired_div" style="display: none;">
							<c:choose>
								<c:when test="${aptExpiredList.size() > 0}">
									<div id="aptExpiredDiv"
										class="card justify-content-center bg-light"
										style="display: block;">
										<div class="container">
											<div class="card-body">
												<div class="row justify-content-center">
													<label for="dateLabel"
														class="col-12 col-sm-2 col-form-label">Date</label><label
														for="timeLabel" class="col-12 col-sm-2 col-form-label">Time</label>
													<label for="nameLabel"
														class="col-12 col-sm-3 col-form-label">Name</label> <label
														for="phoneLabel" class="col-12 col-sm-3 col-form-label">PhoneNo</label>
													<label for="completeLabel"
														class="col-12 col-sm-2 col-form-label">Complete</label>
												</div>
											</div>
										</div>
										<div class="container-sm h-85 overflow auto">
											<c:forEach items="${aptExpiredList}" var="aptExpire">
												<div class="card-body">
													<form action="/apt/vet/manageApt" method="get">
														<div class="row justify-content-center bg-white">
															<label for="dateLabel"
																class="col-12 col-sm-2 col-form-label">${aptExpire.dateString}</label>
															<label for="timeLabel"
																class="col-12 col-sm-2 col-form-label">${aptExpire.aptTime}</label>
															<label for="nameLabel"
																class="col-12 col-sm-3 col-form-label">${aptExpire.petOwner.username}</label>
															<label for="emailLabel"
																class="col-12 col-sm-3 col-form-label">${aptExpire.petOwner.phoneNo}</label>
															<div class="col-12 col-sm-2">
																<button type="submit"
																	class="btn btn-outline-success btn-sm fs-6"
																	name="manageAptButton" value="Completed">
																	<small>Complete</small>
																</button>
															</div>
															<input type="hidden" name="aptId" value="${aptExpire.id}">
														</div>
													</form>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="container justify-content-center fs-3">
										<span class="badge rounded-pill bg-info text-dark">There
											are no expired appointments currently.</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="completed_div" style="display: none;">
							<c:choose>
								<c:when test="${aptCompleteList.size() > 0}">
									<div id="aptCompletedDiv"
										class="card justify-content-center bg-light"
										style="display: block;">
										<div class="container">
											<div class="card-body">
												<div class="row justify-content-center">
													<label for="dateLabel"
														class="col-12 col-sm-2 col-form-label">Date</label><label
														for="timeLabel" class="col-12 col-sm-2 col-form-label">Time</label>
													<label for="nameLabel"
														class="col-12 col-sm-3 col-form-label">Name</label> <label
														for="phoneLabel" class="col-12 col-sm-3 col-form-label">PhoneNo</label>
													<label for="statusLabel"
														class="col-12 col-sm-2 col-form-label">Status</label>
												</div>
											</div>
										</div>
										<div class="container-sm h-85 overflow auto">
											<c:forEach items="${aptCompleteList}" var="aptComplete">
												<div class="card-body">
													<div class="row justify-content-center bg-white">
														<label for="dateLabel"
															class="col-12 col-sm-2 col-form-label">${aptComplete.dateString}</label>
														<label for="timeLabel"
															class="col-12 col-sm-2 col-form-label">${aptComplete.aptTime}</label>
														<label for="nameLabel"
															class="col-12 col-sm-3 col-form-label">${aptComplete.petOwner.username}</label>
														<label for="emailLabel"
															class="col-12 col-sm-3 col-form-label">${aptComplete.petOwner.phoneNo}</label>
														<label for="statusLabel"
															class="col-12 col-sm-2 col-form-label">Completed</label>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="container justify-content-center fs-3">
										<span class="badge rounded-pill bg-info text-dark">There
											are no completed appointments currently.</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="rejected_div" style="display: none;">
							<c:choose>
								<c:when test="${aptRejectList.size() > 0}">
									<div id="aptRejectedDiv"
										class="card justify-content-center bg-light"
										style="display: block;">
										<div class="container">
											<div class="card-body">
												<div class="row justify-content-center">
													<label for="dateLabel"
														class="col-12 col-sm-2 col-form-label">Date</label><label
														for="timeLabel" class="col-12 col-sm-2 col-form-label">Time</label>
													<label for="nameLabel"
														class="col-12 col-sm-3 col-form-label">Name</label> <label
														for="phoneLabel" class="col-12 col-sm-3 col-form-label">PhoneNo</label>
													<label for="statusLabel"
														class="col-12 col-sm-2 col-form-label">Status</label>
												</div>
											</div>
										</div>
										<div class="container-sm h-85 overflow auto">
											<c:forEach items="${aptRejectList}" var="aptReject">
												<div class="card-body">
													<div class="row justify-content-center bg-white">
														<label for="dateLabel"
															class="col-12 col-sm-2 col-form-label">${aptReject.dateString}</label>
														<label for="timeLabel"
															class="col-12 col-sm-2 col-form-label">${aptReject.aptTime}</label>
														<label for="nameLabel"
															class="col-12 col-sm-3 col-form-label">${aptReject.petOwner.username}</label>
														<label for="emailLabel"
															class="col-12 col-sm-3 col-form-label">${aptReject.petOwner.phoneNo}</label>
														<label for="statusLabel"
															class="col-12 col-sm-2 col-form-label">Rejected</label>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</c:when>
								<c:otherwise>
									<div class="container justify-content-center fs-3">
										<span class="badge rounded-pill bg-info text-dark">There
											are no rejected appointments currently.</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
<script type="text/javascript">
	var request_div = document.getElementById("reqested_div");
	var accepted_div = document.getElementById("accepted_div");
	var completed_div = document.getElementById("completed_div");
	var rejected_div = document.getElementById("rejected_div");
	var expired_div = document.getElementById("expired_div");

	function showRequested() {
		request_div.style.display = 'block';
		accepted_div.style.display = 'none';
		completed_div.style.display = 'none';
		rejected_div.style.display = 'none';
		expired_div.style.display = 'none';
	}
	function showAccepted() {
		request_div.style.display = 'none';
		accepted_div.style.display = 'block';
		completed_div.style.display = 'none';
		rejected_div.style.display = 'none';
		expired_div.style.display = 'none';
	}
	function showCompleted() {
		request_div.style.display = 'none';
		accepted_div.style.display = 'none';
		completed_div.style.display = 'block';
		rejected_div.style.display = 'none';
		expired_div.style.display = 'none';
	}
	function showRejected() {
		request_div.style.display = 'none';
		accepted_div.style.display = 'none';
		completed_div.style.display = 'none';
		rejected_div.style.display = 'block';
		expired_div.style.display = 'none';
	}
	function showExpired() {
		request_div.style.display = 'none';
		accepted_div.style.display = 'none';
		completed_div.style.display = 'none';
		rejected_div.style.display = 'none';
		expired_div.style.display = 'block';
	}
</script>
</html>