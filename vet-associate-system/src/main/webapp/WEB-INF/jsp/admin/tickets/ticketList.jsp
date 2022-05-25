<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Ticket Listing</title>
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
    background-image: url('${pageContext.request.contextPath}/images/bg4.png');
    height: 100vh;
  ">
		<div class="container-sm" style="height: 100vh">
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
											name="adminNavButton" value="Tickets" disabled>Tickets</button>
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
											id="navAdminManageCH" name="adminNavButton" value="ManageCH">Clinic/Hospital</button>
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

				<div class="col-12 col-sm-8 mt-5">
					<div class="row">
						<div class="container">
							<div class="btn-group" role="group"
								aria-label="Basic radio toggle button group">
								<input type="radio" class="btn-check" name="btnradio"
									id="btnradio1" autocomplete="off" checked
									onclick="showNewTicket();"> <label
									class="btn btn-outline-primary" for="btnradio1">New
									Tickets</label> <input type="radio" class="btn-check" name="btnradio"
									id="btnradio2" autocomplete="off"
									onclick="showReviewedTicket();"> <label
									class="btn btn-outline-primary" for="btnradio2">Reviewed
									Tickets</label> <input type="radio" class="btn-check" name="btnradio"
									id="btnradio3" autocomplete="off"
									onclick="showCompletedTicket();"> <label
									class="btn btn-outline-primary" for="btnradio3">Completed
									Tickets</label>
							</div>
						</div>
					</div>
					<div class="row">
						<form action="/ticket/admin/view" method="get">
							<div id="new_div" style="display: block;">
								<c:choose>
									<c:when test="${newTickets.size() > 0}">
										<div id="newTicketDiv" style="display: block;">
											<table class="table table-primary table-striped table-hover"
												id="newTicketTable">
												<thead>
													<tr>
														<th>Date</th>
														<th>Type</th>
														<th>Title</th>
														<th>##</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${newTickets}" var="newTicket">
														<tr>
															<td><c:out value="${newTicket.timeString}" /></td>
															<td><c:out value="${newTicket.ticketType}" /></td>
															<td><c:out value="${newTicket.ticketTitle}" /></td>
															<td><button class="btn btn-outline-info btn-sm"
																	type="submit" name="ticketId" value="${newTicket.id}">View</button></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</c:when>
									<c:otherwise>
										<div class="container justify-content-center fs-3">
											<span class="badge rounded-pill bg-info text-dark">There
												are no new tickets currently.</span>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
							<div id="reviewed_div" style="display: none;">
								<c:choose>
									<c:when test="${reviewedTickets.size() > 0}">
										<div id="reviewedTicketDiv" style="display: block;">
											<table class="table table-primary table-striped table-hover"
												id="reviewedTicketTable">
												<thead>
													<tr>
														<th>Date</th>
														<th>Type</th>
														<th>Title</th>
														<th>Responser</th>
														<th>##</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${reviewedTickets}" var="reviewedTicket">
														<tr onclick="">
															<td><c:out value="${reviewedTicket.timeString}" /></td>
															<td><c:out value="${reviewedTicket.ticketType}" /></td>
															<td><c:out value="${reviewedTicket.ticketTitle}" /></td>
															<td><c:out value="${reviewedTicket.responser.username}" /></td>
															<td><button class="btn btn-outline-info btn-sm"
																	type="submit" name="ticketId"
																	value="${reviewedTicket.id}">View</button></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</c:when>
									<c:otherwise>
										<div class="container justify-content-center fs-3">
											<span class="badge rounded-pill bg-info text-dark">There
												are no reviewed tickets currently.</span>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
							<div id="completed_div" style="display: none;">
								<c:choose>
									<c:when test="${completedTickets.size() > 0}">
										<div id="completedTicketDiv" style="display: block;">
											<table class="table table-primary table-striped table-hover"
												id="completedTicketTable">
												<thead>
													<tr>
														<th>Date</th>
														<th>Type</th>
														<th>Title</th>
														<th>Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${completedTickets}"
														var="completedTicket">
														<tr>
															<td><c:out value="${completedTicket.timeString}" /></td>
															<td><c:out value="${completedTicket.ticketType}" /></td>
															<td><c:out value="${completedTicket.ticketTitle}" /></td>
															<td><c:out value="${completedTicket.ticketStatus}" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</div>
									</c:when>
									<c:otherwise>
										<div class="container justify-content-center fs-3">
											<span class="badge rounded-pill bg-info text-dark">There
												are no completed tickets currently.</span>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</form>
						<div class="row">
							<div class="container justify-context-start">
								<span class="badge bg-light text-info pt-3">${msg}</span>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#newTicketTable').DataTable();
		$('#reviewedTicketTable').DataTable();
		$('#completedTicketTable').DataTable();

	});
	function showNewTicket() {
		document.getElementById("new_div").style.display = 'block';
		document.getElementById("reviewed_div").style.display = 'none';
		document.getElementById("completed_div").style.display = 'none';
	}
	function showReviewedTicket() {
		document.getElementById("new_div").style.display = 'none';
		document.getElementById("reviewed_div").style.display = 'block';
		document.getElementById("completed_div").style.display = 'none';
	}
	function showCompletedTicket() {
		document.getElementById("new_div").style.display = 'none';
		document.getElementById("reviewed_div").style.display = 'none';
		document.getElementById("completed_div").style.display = 'block';
	}
</script>
</html>