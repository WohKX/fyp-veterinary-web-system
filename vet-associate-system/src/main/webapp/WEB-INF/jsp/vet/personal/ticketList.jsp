<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Veterinarian Ticket List</title>
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
													id="navVetInquiry" name="vetNavButton" value="Inquiry"
													disabled>Inquiry</button>
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
							<div class="row">
								<c:choose>
									<c:when test="${ticketList.size() > 0 }">
										<div id="inquiryDiv" style="display: block;">
											<table class="table table-primary table-striped table-hover"
												id="inquiryTable">
												<thead>
													<tr>
														<th>Date</th>
														<th>Type</th>
														<th>Title</th>
														<th>Responser</th>
														<th>Status</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${ticketList}" var="ticket">
														<tr>
															<td><c:out value="${ticket.timeString}" /></td>
															<td><c:out value="${ticket.ticketType}" /></td>
															<td><c:out value="${ticket.ticketTitle}" /></td>
															<td><c:out value="${ticket.responser.username}" /></td>
															<td><c:out value="${ticket.ticketStatus}" /></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<span class="badge bg-light text-info pt-3">${infoMsg}</span>
										</div>
									</c:when>
									<c:otherwise>
										<div class="container justify-content-center fs-3">
											<span class="badge rounded-pill bg-info text-dark">There
												are no tickets currently.</span>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
							<div class="row">
								<div class="container justify-context-start">
									<span class="badge bg-light text-info pt-3">${msg}</span>
								</div>
							</div>
							<div class="row">
								<div class="col-12 col-sm-8 mt-3"></div>
								<div class="col-12 col-sm-4 mt-3">
									<form action="/ticket/vet/personal/raiseTicket">
										<button type="submit" class="btn btn-primary btn-md">Raise
											an Inquiry</button>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		
</body>
<script type="text/javascript">
	$(function() {
		$('#inquiryTable').DataTable();

	});
</script>
</html>