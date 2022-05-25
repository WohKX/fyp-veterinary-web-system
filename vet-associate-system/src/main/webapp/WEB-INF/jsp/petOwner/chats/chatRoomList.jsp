<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Conversation List</title>
</head>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script src="https://code.jquery.com/jquery-3.6.0.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"></script>
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
						<div class="container-sm mt-3">
							<div class="mb-3 row">
								<c:choose>
									<c:when test="${chatRoomList.size() > 0}">
										<div class="container-sm" style="height: 700px;">
											<div class="container-sm h-100 overflow-auto">
												<div class="card">
													<div class="card-header text-primary fs-3">Chat Room</div>
													<c:forEach items="${chatRoomList}" var="chatroom">
														<div class="card-body mb-1">
															<div class="row justify-content-center mb-1">
																<c:choose>
																	<c:when test="${chatroom.userOne.id == UID }">
																		<form action="/messages/petOwner/getMsg" method="get">
																			<div class="col-12 col-sm-10">
																				<div class="row justify-content-start">
																					<div class="col">
																						<c:out value="${chatroom.userTwo.name}"></c:out>
																					</div>
																				</div>
																				<div class="row justify-content-start">
																					<c:choose>
																						<c:when test="${chatroom.newMessageCount > 0 }">
																							<div class="col-12 col-sm-8">
																								<span
																									class="badge bg-light text-info pt-1 fs-6">You
																									have ${chatroom.newMessageCount} new messages.</span>
																							</div>
																							<div class="col-12 col-sm-4">
																								<p class="text-start text-secondary fs-6">${chatroom.messageTime}</p>
																							</div>
																						</c:when>
																						<c:otherwise>
																							<div class="col">
																								<p class="text-start text-secondary fs-6">(No
																									New Messages)</p>
																							</div>
																						</c:otherwise>
																					</c:choose>
																				</div>
																			</div>
																			<div class="col-12 col-sm-2">
																				<button type="submit" name="currentUser"
																					value="${chatroom.userTwo.id}">View</button>
																			</div>
																		</form>
																	</c:when>
																	<c:otherwise>
																		<form action="/messages/petOwner/getMsg" method="get">
																			<div class="col-12 col-sm-10">
																				<div class="row justify-content-start">
																					<div class="col">
																						<c:out value="${chatroom.userOne.name}"></c:out>
																					</div>
																				</div>
																				<div class="row justify-content-start">
																					<c:choose>
																						<c:when test="${chatroom.newMessageCount > 0 }">
																							<div class="col-12 col-sm-8">
																								<span class="badge bg-light text-info pt-1 fs-6">You
																									have ${chatroom.newMessageCount} new messages.</span>
																							</div>
																							<div class="col-12 col-sm-4">
																								<p class="text-start text-secondary fs-6">${chatroom.messageTime}</p>
																							</div>
																						</c:when>
																						<c:otherwise>
																							<div class="col">
																								<p class="text-start text-secondary fs-6">(No
																									New Messages)</p>
																							</div>
																						</c:otherwise>
																					</c:choose>
																				</div>
																			</div>
																			<div class="col-12 col-sm-2">
																				<button type="submit" name="currentUser"
																					value="${chatroom.userOne.id}">View</button>
																			</div>
																		</form>
																	</c:otherwise>
																</c:choose>
															</div>
														</div>
													</c:forEach>
												</div>
											</div>
										</div>
									</c:when>
									<c:otherwise>
										<div class="container justify-content-center fs-3">
											<span class="badge rounded-pill bg-info text-dark">There
												are no conversations currently.</span>
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
</body>
</html>