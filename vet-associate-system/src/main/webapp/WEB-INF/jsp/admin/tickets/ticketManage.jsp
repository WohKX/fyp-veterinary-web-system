<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Ticket</title>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
					<c:choose>
						<c:when test="${ticket.ticketType == 'Veterinarian_New_Acc'}">
							<form action="/ticket/admin/vetAcc" method="get">
								<div class="container-sm mt-5">
									<div class="card text-dark bg-light">
										<div class="card-header fs-3">
											<c:out value="${ticket.ticketTitle}" />
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Raiser</h5>
													<c:out value="${ticket.raiser.name}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Responser</h5>
													<c:out value="${ticket.responser.username}" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Ticket Date</h5>
													<c:out value="${ticket.ticketDate}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Ticket Status</h5>
													<c:out value="${ticket.ticketStatus}" />
												</div>
											</div>
										</div>
										<div class="card-body mb-3">
											<h5 class="card-title">Description</h5>
											<c:out value="${ticket.description}" />
										</div>
										<div class="card-body">
											<h5 class="card-title">Credential Image</h5>
											<img
												src="data:${ticket.imageType};base64,${ticket.provenImage}"
												class="img-fluid">
										</div>
										<div class="card-body mb-3">
											<div class="card-body">
												<div class="mb-3 row justify-content-sm-center">
													<div class="col-xl-2 ">
														<button type="submit" class="btn btn-primary btn-xxl"
															id="submitButton" value="Approve"
															name="manageTicketButton">Approve</button>
													</div>
													<div class="col-xl-2">
														<button type="submit" class="btn btn-secondary btn-xxl"
															value="Review" name="manageTicketButton">Review</button>
													</div>
													<div class="col-xl-2">
														<button type="button" class="btn btn-secondary btn-xxl"
															onclick="history.back()">Back</button>
													</div>
													<div class="col-xl-2">
														<button type="submit" class="btn btn-danger btn-xxl"
															value="Reject" name="manageTicketButton">Reject</button>
													</div>
													<input type="hidden" value="${ticket.id}" name="ticketId"></input>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</c:when>
						<c:when
							test="${ticket.ticketType == 'CH_Registration' || ticket.ticketType == 'CH_Change_Owner'}">
							<form action="/ticket/admin/manageCH" method="get">
								<div class="container-sm mt-5">
									<div class="card text-dark bg-light">
										<div class="card-header fs-3">
											<c:out value="${ticket.ticketTitle}" />
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Raiser</h5>
													<c:out value="${ticket.raiser.name}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Responser</h5>
													<c:out value="${ticket.responser.username}" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Ticket Date</h5>
													<c:out value="${ticket.ticketDate}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Ticket Status</h5>
													<c:out value="${ticket.ticketStatus}" />
												</div>
											</div>
										</div>
										<div class="card-body mb-3">
											<h5 class="card-title">Description</h5>
											<p class="text-start fw-bold fs-6">
												<c:out value="${ticket.description}" />
											</p>
											<div class="container-sm">
												<div class="row">
													<div class="col-sm-3">
														<p class="text-start fw-bold fs-6">Clinic/Hospital
															Name</p>
													</div>
													<div class="col-sm-9">
														<p class="text-start fs-6">
															<c:out value="${ch.chName}" />
														</p>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<p class="text-start fw-bold fs-6">Street</p>
													</div>
													<div class="col-sm-9">
														<p class="text-start fs-6">
															<c:out value="${ch.street}" />
														</p>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<p class="text-start fw-bold fs-6">Poscode</p>
													</div>
													<div class="col-sm-9">
														<p class="text-start fs-6">
															<c:out value="${ch.poscode}" />
														</p>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<p class="text-start fw-bold fs-6">District</p>
													</div>
													<div class="col-sm-9">
														<p class="text-start fs-6">
															<c:out value="${ch.district}" />
														</p>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<p class="text-start fw-bold fs-6">States</p>
													</div>
													<div class="col-sm-9">
														<p class="text-start fs-6">
															<c:out value="${ch.states}" />
														</p>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-3">
														<p class="text-start fw-bold fs-6">Current Status</p>
													</div>
													<div class="col-sm-9">
														<p class="text-start fs-6">
															<c:out value="${ch.chStatus}" />
														</p>
													</div>
												</div>
											</div>
										</div>
										<div class="card-body">
											<h5 class="card-title">Owner Proven Image</h5>
											<img
												src="data:${ticket.imageType};base64,${ticket.provenImage}"
												class="img-fluid">
										</div>
										<div class="card-body mb-3">
											<div class="card-body">
												<div class="mb-3 row justify-content-sm-center">
													<div class="col-xl-2 ">
														<button type="submit" class="btn btn-primary btn-xxl"
															id="submitButton" value="Approve"
															name="manageTicketButton">Approve</button>
													</div>
													<div class="col-xl-2">
														<button type="submit" class="btn btn-secondary btn-xxl"
															value="Review" name="manageTicketButton">Review</button>
													</div>
													<div class="col-xl-2">
														<button type="button" class="btn btn-secondary btn-xxl"
															onclick="history.back()">Back</button>
													</div>
													<div class="col-xl-2">
														<button type="submit" class="btn btn-danger btn-xxl"
															value="Reject" name="manageTicketButton">Reject</button>
													</div>
													<input type="hidden" value="${ticket.id}" name="ticketId"></input>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</c:when>

						<c:otherwise>
							<form action="/ticket/admin/defaults" method="get">
								<div class="container-sm mt-5">
									<div class="card text-dark bg-light">
										<div class="card-header fs-3">
											<c:out value="${ticket.ticketTitle}" />
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Raiser</h5>
													<c:out value="${ticket.raiser.name}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Responser</h5>
													<c:out value="${ticket.responser.username}" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Ticket Date</h5>
													<c:out value="${ticket.ticketDate}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Ticket Status</h5>
													<c:out value="${ticket.ticketStatus}" />
												</div>
											</div>
										</div>
										<div class="card-body mb-3">
											<h5 class="card-title">Description</h5>
											<c:out value="${ticket.description}" />
										</div>
										<c:if test="${ticket.provenImage != null}">
											<div class="card-body">
												<h5 class="card-title">Support Image</h5>
												<img
													src="data:${ticket.imageType};base64,${ticket.provenImage}"
													class="img-fluid">
											</div>
										</c:if>
										<div class="card-body mb-3">
											<div class="card-body">
												<div class="mb-3 row justify-content-sm-center">
													<div class="col-xl-2 ">
														<button type="submit" class="btn btn-primary btn-xxl"
															id="submitButton" value="Approve"
															name="manageTicketButton">Approve</button>
													</div>
													<div class="col-xl-2">
														<button type="submit" class="btn btn-secondary btn-xxl"
															value="Review" name="manageTicketButton">Review</button>
													</div>
													<div class="col-xl-2">
														<button type="button" class="btn btn-secondary btn-xxl"
															onclick="history.back()">Back</button>
													</div>
													<div class="col-xl-2">
														<button type="submit" class="btn btn-danger btn-xxl"
															value="Reject" name="manageTicketButton">Reject</button>
													</div>
													<input type="hidden" value="${ticket.id}" name="ticketId"></input>
													<input type="hidden" value="${ticket.ticketType}"
														name="ticketType"></input>
												</div>
											</div>
										</div>
									</div>
								</div>
							</form>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</div>
	</div>
</body>
</html>