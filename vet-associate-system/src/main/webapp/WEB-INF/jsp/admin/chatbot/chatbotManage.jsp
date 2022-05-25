<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Chatbot</title>
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

				<div class="col-12 col-sm-8 mt-3">
					<div class="container-sm mt-5">
						<c:choose>
							<c:when test="${chatbot != null}">
								<form action="/chatbot/admin/edit" method="post">
									<div class="card text-dark bg-light">
										<div class="card-header fs-3">
											<c:out value="${chatbot.id}" />
											-
											<c:out value="${chatbot.classes}" />
											:
											<c:out value="${chatbot.questions}" />
										</div>
										<div class="mb-3 row">
											<div class="col-sm-6 ms-2">
												<div class="card-body mb-3">
													<h5 class="card-title">Class</h5>
													<c:out value="${chatbot.classes}" />
												</div>
											</div>
											<div class="col-sm-6 ms-2">
												<div class="card-body mb-3">
													<h5 class="card-title">Edit/Created By</h5>
													<c:out value="${chatbot.editors.username}" />
												</div>
											</div>
										</div>
										<div class="mb-3 row">
											<label for="questionsLabel"
												class="col-sm-3 ms-2 col-form-label">Question</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" name="questions"
													id="questions" value="${chatbot.questions}"
													onkeyup="valQuestions()" onblur="valQuestions()"
													maxlength="200" />
											</div>
											<div class="col-sm-3">
												<span class="badge bg-light text-danger pt-3"
													id="questionsErr"></span>
											</div>
										</div>
										<div class="mb-3 row">
											<label for="answersLabel"
												class="col-sm-3 ms-2 col-form-label">Answer</label>
											<div class="col-sm-6">
												<textarea class="form-control" id="answers" name="answers"
													rows="3" onkeyup="valAnswers()" onblur="valAnswers()"
													maxlength="2000">${chatbot.answers}</textarea>
											</div>
											<div class="col-sm-3">
												<span class="badge bg-light text-danger pt-3"
													id="answersErr"></span>
											</div>
										</div>
										<div class="card-body mb-3">
											<div class="card-body">
												<div class="mb-3 row justify-content-sm-center">
													<div class="col-xl-2">
														<button type="submit" class="btn btn-primary btn-xxl"
															id="saveButton" value="Save" name="manageChatbotButton">Save</button>
													</div>
													<div class="col-xl-2">
														<button type="button" class="btn btn-secondary btn-xxl"
															onclick="history.back()">Back</button>
													</div>
													<div class="col-xl-2">
														<button type="submit" class="btn btn-secondary btn-xxl"
															id="deleteButton" value="Delete"
															name="manageChatbotButton">Delete</button>
													</div>
													<input type="hidden" value="${chatbot.id}" name="id"></input>
												</div>
											</div>
										</div>
									</div>
								</form>
							</c:when>
							<c:otherwise>
								<form action="/chatbot/admin/create" method="post">
									<div class="card text-dark bg-light">
										<div class="card-header fs-3">Create New Answer for
											Chatbot</div>
										<div class="mb-3 row">
											<label for="petClassLabel"
												class="col-sm-3 ms-2 col-form-label">Class</label>
											<div class="col-sm-4">
												<select name="classes" id="classes" class="form-control">
													<c:forEach items="${classes}" var="type">
														<option value="${type}">${type}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="mb-3 row">
											<label for="questionsLabel"
												class="col-sm-3 ms-2 col-form-label">Question</label>
											<div class="col-sm-6">
												<input type="text" class="form-control" name="questions"
													id="questions" placeholder="Please enter the question"
													onkeyup="valQuestions()" onblur="valQuestions()"
													maxlength="200" />
											</div>
											<div class="col-sm-3">
												<span class="badge bg-light text-danger pt-3"
													id="questionsErr"></span>
											</div>
										</div>
										<div class="mb-3 row">
											<label for="answersLabel"
												class="col-sm-3 ms-2 col-form-label">Answer</label>
											<div class="col-sm-6">
												<textarea class="form-control" id="answers" name="answers"
													rows="3" placeholder="Please enter the answer"
													onkeyup="valAnswers()" onblur="valAnswers()"
													maxlength="2000"></textarea>
											</div>
											<div class="col-sm-3">
												<span class="badge bg-light text-danger pt-3"
													id="answersErr"></span>
											</div>
										</div>
										<div class="card-body mb-3">
											<div class="card-body">
												<div class="mb-3 row justify-content-sm-center">
													<div class="col-xl-2">
														<button type="submit" class="btn btn-primary btn-xxl"
															id="saveButton" value="Save" name="manageChatbotButton">Save</button>
													</div>
													<div class="col-xl-2">
														<button type="button" class="btn btn-secondary btn-xxl"
															onclick="history.back()">Back</button>
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
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/js/adminPage/chatbotFunction.js"></script>
</html>