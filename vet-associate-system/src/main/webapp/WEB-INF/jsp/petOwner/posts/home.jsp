<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
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
										<c:choose>
											<c:when test="${messageCount > 0}">
												<button type="submit" class="btn btn-black px-4 fs-5"
													name="ownerNavButton" value="Conversation">
													Conversation<span class="badge rounded-pill bg-danger"
														id="notificationBadge" style="display: block;">${messageCount}</span>
												</button>
											</c:when>
											<c:otherwise>
												<button type="submit" class="btn btn-black px-4 fs-5"
													name="ownerNavButton" value="Conversation">
													Conversation<span class="badge rounded-pill bg-danger"
														id="notificationBadge" style="display: none;"></span>
												</button>
											</c:otherwise>
										</c:choose>
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

				<div class="col-12 col-sm-8 mt-5">
					<div class="row justify-content-start">
						<form action="/home/petOwner/search" method="post">
							<div class="row mb-5">
								<div class="col-sm-4">
									<input type="text" class="form-control fs-6" name="searchBar"
										id="searchBar">
								</div>
								<div class="col-sm-2">
									<button type="submit" class="btn btn-success fs-6"
										value="search" id="searchButton">Search</button>
								</div>
							</div>
						</form>
					</div>
					<div class="card text-center text-dark bg-light mb-3">
						<div class="card-body">
							<form action="/home/petOwner/newPost" method="post"
								enctype="multipart/form-data">
								<div class="row mb-3 justify-content-start">
									<div class="col-12 col-sm-3">
										<label for="username" class="form-label fs-5">${petOwner.username}</label>
									</div>
								</div>
								<div class="row mb-3">
									<textarea class="form-control fs-6" id="descriptions"
										name="descriptions" rows="3" maxlength="500"></textarea>
								</div>
								<div class="row mb-3">
									<div class="col-12 col-sm-6 justify-content-start">
										<input class="form-control" type="file"
											accept=".png, .jpg, .jpeg" id="snImage" name="snImage">
									</div>
									<div class="col-12 col-sm-6 justify-content-end">
										<button type="submit" class="btn btn-outline-info fs-6"
											id="shareButton" name="shareButton">Share</button>
									</div>
								</div>
							</form>
						</div>
					</div>
					<c:choose>
						<c:when test="${socialNetworks.size() > 0}">
							<c:forEach items="${socialNetworks}" var="socialNetwork">
								<div class="card mb-3">
									<form action="/home/petOwner/view" method="get">
										<div class="card-body">
											<div class="row mt-5">
												<div class="col-sm">
													<div
														class="row justify-content-start text-primary fs-5 mb-3 ms-2">
														<c:out value="${socialNetwork.writter.name}"></c:out>
													</div>
													<div class="row justify-content-start fs-6 mb-2 ms-3">
														<c:out value="${socialNetwork.descriptions}"></c:out>
													</div>
													<c:if test="${socialNetwork.snImage != null}">
														<div class="row justify-content-center">
															<img
																src="data:${socialNetwork.imageType};base64,${socialNetwork.snImage}"
																style="width: 800px;">
														</div>
													</c:if>
													<div class="row justify-content-end fs-6 text-info">
														<c:out value="${socialNetwork.timeString}"></c:out>
													</div>
												</div>
											</div>
										</div>
										<div class="card-body">
											<div class="row mt-2 justify-content-start">
												<c:if test="${socialNetwork.comments.size() > 0}">
													<div class="col-sm">
														<c:forEach items="${socialNetwork.comments}" var="comment">
															<div class="row py-2">
																<div class="col-12 col-sm-2">
																	<c:out value="${comment.writter.name}"></c:out>
																</div>
																<div class="col-12 col-sm-8">
																	<c:out value="${comment.descriptions}"></c:out>
																</div>
																<div class="col-12 col-sm-2 text-info fs-6">
																	<c:out value="${comment.timeString}"></c:out>
																</div>
															</div>
														</c:forEach>
													</div>
												</c:if>
											</div>
										</div>
										<div class="card-body">
											<div class="row justify-content-end">
												<div class="col-12 col-sm-3">
													<button type="submit"
														class="btn btn-outline-success fs-6 mb-3"
														value="${socialNetwork.id}" name="socialNetworkId">View
														Details</button>
												</div>
											</div>
										</div>
									</form>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="row mt-5 justify-content-center fs-3">
								<span class="badge rounded-pill bg-info text-dark">There
									are no posts currently.</span>
							</div>
						</c:otherwise>
					</c:choose>
				</div>

			</div>
			<div class="row">
				<div class="col-12 col-sm-5 "
					style="position: fixed; bottom: 10px; right: 0; height: 532px;"
					id="chatbotCol">
					<div class="container-sm ">
						<div class="row mb-1 justify-content-end" id="chatbotDiv"
							style="display: show;">
							<div class="col-12 col-sm-7 ">
								<div class="card text-dark bg-light">
									<div class="card-body mb-2">
										<h6 class="card-title">VASS Chat</h6>
									</div>
									<div class="row justify-content-start">
										<div class="col justify-content-start">
											<div class="container-sm bg-white text-dark"
												style="height: 380px;">
												<div class="container-sm h-100 overflow-auto"
													id="chatbotContainer">
													<form action="/nav/petOwner" method="get">
														<div id="chatbot">
															<div class="row justify-content-start">
																<div class="col-12 col-sm-8">
																	<p class="fst-italic">Hi, how can I help you?</p>
																</div>
															</div>
															<div class="row mb-1 justify-content-start">
																<div class="col-12 col-sm-8">
																	<button type="submit" id="submitButton"
																		class="btn btn-outline-success btn-sm text-sm"
																		name="ownerNavButton" value="SearchCH">Search
																		for clinic/hospital.</button>
																</div>
															</div>
															<div class="row mb-1 justify-content-start">
																<div class="col-12 col-sm-8">
																	<button type="submit" id="submitButton"
																		class="btn btn-outline-success btn-sm text-sm"
																		name="ownerNavButton" value="SearchVet">Search
																		for veterinarian</button>
																</div>
															</div>
															<div class="row mb-1 justify-content-start">
																<div class="col-12 col-sm-8">
																	<button type="submit" id="submitButton"
																		class="btn btn-outline-success btn-sm text-sm"
																		name="ownerNavButton" value="Profile">Edit my
																		profile</button>
																</div>
															</div>
															<div class="row mb-1 justify-content-start">
																<div class="col-12 col-sm-8">
																	<button type="submit" id="submitButton"
																		class="btn btn-outline-success btn-sm text-sm"
																		name="ownerNavButton" value="Inquiry">Raise a
																		problem or inquiry.</button>
																</div>
															</div>
														</div>
													</form>
												</div>
											</div>
										</div>
									</div>
									<div class="row" style="height: 35px;">
										<div class="col-12 col-sm-9" id="textInputDiv">
											<input type="text" class="form-control overflow-auto h-100"
												id="contentText" placeholder="Enter your question here" />
										</div>
										<div class="col-12 col-sm-2">
											<button type="button" class="btn btn-light btn-sm"
												id="sendChatbotButton">
												<small>Send</small>
											</button>
										</div>


									</div>
								</div>
							</div>
						</div>
						<div class="row justify-content-end">
							<div class="col-12 col-sm-3 ">
								<button type="button" id="chatbotButton"
									class="btn btn-outline-info btn-sm fs-6">VASS Helper
									here</button>
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
		$('#searchButton').prop('disabled', true);
		$('#searchBar').bind('keyup change blur', function() {
			if ($('#searchBar').val() != '') {
				$('#searchButton').prop('disabled', false);
			} else {
				$('#searchButton').prop('disabled', true);
			}
		});

		$('#shareButton').prop('disabled', true);
		$('#descriptions').bind('keyup change blur', function() {
			if ($('#descriptions').val() != '') {
				$('#shareButton').prop('disabled', false);
			} else {
				$('#shareButton').prop('disabled', true);
			}
		});

		$('#searchBar').keypress(function(e) {
			if ($('#searchBar').val() != '') {
				if (e.keyCode == 13)
					$('#searchButton').click();
			}
		});

		$('#chatbotButton').click(function() {
			if ($('#chatbotDiv').is(':hidden')) {
				$('#chatbotCol').css('height', '532px');
				$('#chatbotDiv').show();
				$('#contentText').focus();
				$("#chatbotContainer").scrollTop(function() {
					return this.scrollHeight;

				});
			} else {
				$('#chatbotCol').css('height', '45px');
				$('#chatbotDiv').hide();
			}

		})

		$('#sendChatbotButton').prop('disabled', true);
		$('#contentText').bind('keyup change blur', function() {
			if ($('#contentText').val() != '') {
				$('#sendChatbotButton').prop('disabled', false);
			} else {
				$('#sendChatbotButton').prop('disabled', true);
			}
		});
		$('#contentText').keypress(function(e) {
			if ($('#contentText').val() != '') {
				if (e.keyCode == 13)
					$('#sendChatbotButton').click();
			}
		});
	});

	function setNewText(text) {
		const chatboxDiv = document.getElementById("chatbot");
		const newMessageDiv = document.createDocumentFragment();

		let new_row = document.createElement('div');
		let new_col = document.createElement('div');
		new_row.classname = "row justify-content-start";
		new_col.classname = "col-12 col-sm-8";

		let newText = document.createElement("p");
		newText.className = "fst-italic";
		newText.innerHTML = text;

		new_col.appendChild(newText);
		new_row.appendChild(new_col);
		newMessageDiv.appendChild(new_row);
		chatboxDiv.appendChild(newMessageDiv);
	}

	function setNewButton(buttonText, buttonValue) {
		const chatboxDiv = document.getElementById("chatbot");
		const newMessageDiv = document.createDocumentFragment();

		let new_row = document.createElement('div');
		let new_col = document.createElement('div');
		new_row.classname = "row mb-1 justify-content-start";
		new_col.classname = "col-12 col-sm-8";

		let newButton = document.createElement("button");
		newButton.className = "btn btn-outline-success btn-sm text-sm";
		newButton.name = "ownerNavButton";
		newButton.type = "submit";
		newButton.innerText = buttonText;
		newButton.value = buttonValue;

		new_col.appendChild(newButton);
		new_row.appendChild(new_col);
		newMessageDiv.appendChild(new_row);
		chatboxDiv.appendChild(newMessageDiv);

	}

	function sendChatbot(contentText) {
		let dataToBeSend = {
			messageContent : contentText
		};
		console.log(contentText);
		$.ajax({
			url : window.location.protocol + "//" + window.location.host
					+ "/chatbot/send",
			type : "POST",
			data : dataToBeSend,
			dataType : 'json',
			success : function(result) {
				let message = result;

				const chatboxDiv = document.getElementById("chatbot");
				const newMessageDiv = document.createDocumentFragment();
				let new_row = document.createElement('div');
				let new_col = document.createElement('div');
				new_row.classname = "row justify-content-end";
				new_col.classname = "col-12 col-sm-8";

				let newText = document.createElement("p");
				newText.className = "fst-normal text-end";
				newText.innerHTML = contentText;

				new_col.appendChild(newText);
				new_row.appendChild(new_col);
				newMessageDiv.appendChild(new_row);
				chatboxDiv.appendChild(newMessageDiv);

				if (message.responseType == "button") {
					console.log(message.response);
					console.log(message.responseValue);
					setNewButton(message.response, message.responseValue);
				} else {
					setNewText(message.response);
				}

				initializing();
			},
			error : function(error) {
				console.log('Error ' + error);
			}
		});
	}

	function initializing() {
		const scrollDiv = document.getElementById("chatbotContainer");
		const contentText = document.getElementById("contentText");
		document.getElementById("sendChatbotButton").disabled = true;
		contentText.value = "";
		scrollDiv.scrollTop = scrollDiv.scrollHeight;
	}

	document.getElementById("sendChatbotButton").addEventListener('click',
			function() {
				sendChatbot(document.getElementById("contentText").value);
			});
</script>
</html>