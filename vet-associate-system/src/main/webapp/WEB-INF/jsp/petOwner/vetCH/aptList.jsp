<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Appointment List</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.css">
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.js"></script>
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
											id="navOwnerProfile" name="ownerNavButton" value="Profile">Profile</button>
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
											name="ownerNavButton" value="Appointment" disabled>My
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
									onclick="showCompleted();"> <label
									class="btn btn-outline-primary" for="btnradio3">Completed
									Appointment</label> <input type="radio" class="btn-check"
									name="btnradio" id="btnradio4" autocomplete="off"
									onclick="showRejected();"> <label
									class="btn btn-outline-primary" for="btnradio4">Rejected
									Appointment</label> <input type="radio" class="btn-check"
									name="btnradio" id="btnradio5" autocomplete="off"
									onclick="showExpired();"> <label
									class="btn btn-outline-primary" for="btnradio5">Expired
									Appointment</label>
							</div>
						</div>
					</div>
					<div class="row">
						<div id="reqested_div" style="display: block;">
							<c:choose>
								<c:when test="${aptRequestList.size() > 0}">
									<div id="aptRequestedDiv" style="display: block;">
										<table class="table table-primary table-striped table-hover"
											id="aptRequestTable">
											<thead>
												<tr>
													<th>Clinic/Hospital</th>
													<th>Phone No</th>
													<th>Date</th>
													<th>Status</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${aptRequestList}" var="aptRequest">
													<tr>
														<td><c:out value="${aptRequest.chId.chName}" /></td>
														<td><c:out value="${aptRequest.chId.phoneNo}" /></td>
														<td><c:out value="${aptRequest.dateString}" /></td>
														<td><c:out value="${aptRequest.aptStatus}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<div class="container justify-content-center fs-3">
										<span class="badge rounded-pill bg-info text-dark">There
											are no requested appointments currently.</span>
									</div>
								</c:otherwise>
							</c:choose>
						</div>
						<div id="accepted_div" style="display: none;">
							<c:choose>
								<c:when test="${aptAcceptList.size() > 0}">
									<div id="aptAcceptedDiv" style="display: block;">
										<table class="table table-primary table-striped table-hover"
											id="aptAcceptTable">
											<thead>
												<tr>
													<th>Clinic/Hospital</th>
													<th>Phone No</th>
													<th>Date</th>
													<th>Status</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${aptAcceptList}" var="aptAccept">
													<tr>
														<td><c:out value="${aptAccept.chId.chName}" /></td>
														<td><c:out value="${aptAccept.chId.phoneNo}" /></td>
														<td><c:out value="${aptAccept.dateString}" /></td>
														<td><c:out value="${aptAccept.aptStatus}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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
						<div id="completed_div" style="display: none;">
							<c:choose>
								<c:when test="${aptCompleteList.size() > 0}">
									<div id="aptCompletedDiv" style="display: block;">
										<table class="table table-primary table-striped table-hover"
											id="aptCompleteTable">
											<thead>
												<tr>
													<th>Clinic/Hospital</th>
													<th>Phone No</th>
													<th>Date</th>
													<th>Status</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${aptCompleteList}" var="aptComplete">
													<tr>
														<td><c:out value="${aptComplete.chId.chName}" /></td>
														<td><c:out value="${aptComplete.chId.phoneNo}" /></td>
														<td><c:out value="${aptComplete.dateString}" /></td>
														<td><c:out value="${aptComplete.aptStatus}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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
									<div id="aptRejectedDiv" style="display: block;">
										<table class="table table-primary table-striped table-hover"
											id="aptRejectTable">
											<thead>
												<tr>
													<th>Clinic/Hospital</th>
													<th>Phone No</th>
													<th>Date</th>
													<th>Status</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${aptRejectList}" var="aptReject">
													<tr>
														<td><c:out value="${aptReject.chId.chName}" /></td>
														<td><c:out value="${aptReject.chId.phoneNo}" /></td>
														<td><c:out value="${aptReject.dateString}" /></td>
														<td><c:out value="${aptReject.aptStatus}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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
						<div id="expired_div" style="display: none;">
							<c:choose>
								<c:when test="${aptExpiredList.size() > 0}">
									<div id="aptExpiredDiv" style="display: block;">
										<table class="table table-primary table-striped table-hover"
											id="aptExpireTable">
											<thead>
												<tr>
													<th>Clinic/Hospital</th>
													<th>Phone No</th>
													<th>Date</th>
													<th>Status</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${aptExpiredList}" var="aptExpire">
													<tr>
														<td><c:out value="${aptExpire.chId.chName}" /></td>
														<td><c:out value="${aptExpire.chId.phoneNo}" /></td>
														<td><c:out value="${aptExpire.dateString}" /></td>
														<td><c:out value="${aptExpire.aptStatus}" /></td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
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
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#aptRequestTable').DataTable();
		$('#aptAcceptTable').DataTable();
		$('#aptCompleteTable').DataTable();
		$('#aptRejectTable').DataTable();
		$('#aptExpireTable').DataTable();

	});
	function showRequested() {
		document.getElementById("reqested_div").style.display = 'block';
		document.getElementById("accepted_div").style.display = 'none';
		document.getElementById("completed_div").style.display = 'none';
		document.getElementById("rejected_div").style.display = 'none';
		document.getElementById("expired_div").style.display = 'none';
	}
	function showAccepted() {
		document.getElementById("reqested_div").style.display = 'none';
		document.getElementById("accepted_div").style.display = 'block';
		document.getElementById("completed_div").style.display = 'none';
		document.getElementById("rejected_div").style.display = 'none';
		document.getElementById("expired_div").style.display = 'none';
	}
	function showCompleted() {
		document.getElementById("reqested_div").style.display = 'none';
		document.getElementById("accepted_div").style.display = 'none';
		document.getElementById("completed_div").style.display = 'block';
		document.getElementById("rejected_div").style.display = 'none';
		document.getElementById("expired_div").style.display = 'none';
	}
	function showRejected() {
		document.getElementById("reqested_div").style.display = 'none';
		document.getElementById("accepted_div").style.display = 'none';
		document.getElementById("completed_div").style.display = 'none';
		document.getElementById("rejected_div").style.display = 'block';
		document.getElementById("expired_div").style.display = 'none';
	}
	function showExpired() {
		document.getElementById("reqested_div").style.display = 'none';
		document.getElementById("accepted_div").style.display = 'none';
		document.getElementById("completed_div").style.display = 'none';
		document.getElementById("rejected_div").style.display = 'none';
		document.getElementById("expired_div").style.display = 'block';
	}
</script>
</html>