<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messaging</title>
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
							<div class="card text-center text-dark bg-light">
								<div class="card-body">
									<div class="row justify-content-center">
										<form action="/messages/petOwner/listChatRoom" method="get">
											<div class="col-12 col-sm-2">
												<button type="submit"
													class="btn btn-outline-secondary btn-sm fs-5">Back</button>
											</div>
										</form>
										<div class="col-12 col-sm-10 text-primary fs-4">${currentUser.name}</div>
									</div>
								</div>
								<div class="card-body">
									<div class="mb-3 row">
										<div class="container-sm" style="height: 500px;">
											<div class="container-sm h-100 overflow-auto"
												id="chatboxContainer">
												<div class="row mb-2 justify-content-center">
													<form action="/messages/petOwner/getAllMsg" method="get">
														<button type="submit" id="submitButton"
															class="btn btn-outline-success btn-sm fs-6"
															name="currentUser" value="${currentUser.id}">Show
															All</button>
													</form>
												</div>
												<div class="container" id="chatbox">
													<c:forEach items="${chatMessages}" var="message">
														<c:choose>
															<c:when test="${message.sender.id == UID }">
																<div class="row justify-content-end mb-2">
																	<div class="col-12 col-sm-5"></div>
																	<div class="col-12 col-sm-7 justify-content-end">
																		<div class="d-flex flex-row-reverse bd-highlight">
																			<c:choose>
																				<c:when test="${message.messageType == 'TEXT' }">
																					<p class="fs-5">
																						<c:out value="${message.messageText}"></c:out>
																					</p>
																				</c:when>
																				<c:otherwise>
																					<img src="${message.messageImage}"
																						style="width: 480px;">
																				</c:otherwise>
																			</c:choose>
																			<div class="p-2 bd-highlight fs-6">
																				<c:out value="${message.timeString}"></c:out>
																			</div>
																		</div>
																	</div>
																</div>
															</c:when>
															<c:otherwise>
																<div class="row justify-content-start mb-2">
																	<div class="col-12 col-sm-7 justify-content-end">
																		<div class="d-flex flex-row bd-highlight">
																			<c:choose>
																				<c:when test="${message.messageType == 'TEXT' }">
																					<p class="fs-5">
																						<c:out value="${message.messageText}"></c:out>
																					</p>
																				</c:when>
																				<c:otherwise>
																					<img src="${message.messageImage}"
																						style="width: 480px;">
																				</c:otherwise>
																			</c:choose>
																			<div class="p-2 bd-highlight fs-6">
																				<c:out value="${message.timeString}"></c:out>
																			</div>
																		</div>
																	</div>
																	<div class="col-12 col-sm-5"></div>
																</div>
															</c:otherwise>
														</c:choose>
													</c:forEach>
												</div>
											</div>
										</div>
									</div>
									<div class="mb-3 row" style="height: 50px;">
										<div class="col-12 col-sm-6" id="textInputDiv">
											<input type="text" class="form-control overflow-auto h-100"
												id="contentText" placeholder="Enter your message here"
												maxlength="230" />
										</div>
										<div class="col-12 col-sm-4" id="imageInputDiv">
											<input class="form-control" type="file"
												accept=".png, .jpg, .jpeg" id="contentImage">
										</div>
										<div class="col-12 col-sm-2">
											<button type="button" class="btn btn-light btn-sm fs-4"
												value="${currentUser.id}" id="sendButton">Send</button>
										</div>
									</div>
								</div>
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
		$("#chatboxContainer").scrollTop(function() {
			return this.scrollHeight;
		});
		$('#contentText').keypress(function(e) {
			if ($('#contentText').val() != '') {
				if (e.keyCode == 13)
					$('#sendButton').click();
			}
		});

		$('#sendButton').prop('disabled', true);
		$('#contentText').focus();
		$('#contentText, #contentImage').bind(
				'keyup change blur',
				function() {

					if ($('#contentText').val() != ''
							&& $('#contentImage').val() == '') {
						$('#contentImage').hide();
						$('#imageInputDiv').removeClass("col-sm-4").addClass(
								"col-sm-1");
						$('#textInputDiv').removeClass("col-sm-6").addClass(
								"col-sm-9");
					} else if ($('#contentText').val() == ''
							&& $('#contentImage').val() == '') {
						$('#textInputDiv').removeClass("col-sm-9").addClass(
								"col-sm-6");
						$('#imageInputDiv').removeClass("col-sm-1").addClass(
								"col-sm-4");
						$('#contentImage').show();
					}
					if ($('#contentImage').val() != ''
							&& $('#contentText').val() == '') {
						$('#contentText').hide();
						$('#textInputDiv').removeClass("col-sm-6").addClass(
								"col-sm-1");
						$('#imageInputDiv').removeClass("col-sm-4").addClass(
								"col-sm-9");
					} else if ($('#contentImage').val() == ''
							&& $('#contentText').val() == '') {
						$('#imageInputDiv').removeClass("col-sm-9").addClass(
								"col-sm-4");
						$('#textInputDiv').removeClass("col-sm-1").addClass(
								"col-sm-6");
						$('#contentText').show();
					}
					if (($('#contentText').val() != '')
							|| ($('#contentImage').val() != '')) {
						$('#sendButton').prop('disabled', false);
					} else {
						$('#sendButton').prop('disabled', true);
					}
				});
	});
	var activeUser = document.getElementById("sendButton").value;
	document.getElementById("sendButton").addEventListener('click', function() {
		sendContent(activeUser);
	});

	setInterval(function() {
		receiveMessage(activeUser);
	}, 3500);
</script>
<script src="${pageContext.request.contextPath}/js/messaging.js"></script>
</html>