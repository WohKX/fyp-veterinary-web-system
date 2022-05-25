<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pet Owner Post</title>
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
												<button type="submit" class="btn btn-black px-4 fs-5"
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
													id="navAdminManageCH" name="adminNavButton"
													value="ManageCH">Clinic/Hospital</button>
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
												<button type="button" class="btn btn-black px-4 fs-5"
													id="navAdminAdvance" onclick="toggleAdvancedList()">Advanced</button>
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
							<div class="row justify-content-start mb-5">
								<form action="/home/admin" method="post">
									<div class="col-12 col-sm-2">
										<button type="submit" class="btn btn-secondary fs-5">Back</button>
									</div>
								</form>
							</div>
							<div class="card text-dark bg-light mb-4">
								<div class="row justify-content-start">
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<div
												class="row justify-content-start text-primary ms-2 fs-4 ">
												<c:out value="${socialNetwork.writter.name}"></c:out>
											</div>
										</div>
									</div>
								</div>
								<div class="row justify-content-start">
									<div class="col-sm-9">
										<div class="card-body mb-3">
											<div class="row justify-content-start fs-5 mb-2 ms-4">
												<c:out value="${socialNetwork.descriptions}"></c:out>
											</div>
										</div>
									</div>
								</div>
								<c:if test="${socialNetwork.snImage != null}">
									<div class="row justify-content-center">
										<div class="col-sm-9">
											<div class="card-body mb-3">
												<div class="row">
													<img
														src="data:${socialNetwork.imageType};base64,${socialNetwork.snImage}"
														style="width: 800px;">
												</div>
											</div>
										</div>
									</div>
								</c:if>

								<div class="row justify-content-end">
									<div class="col-sm-6">
										<div class="card-body mb-3">
											<div class="row justify-content-end fs-6 text-info">
												<c:out value="${socialNetwork.timeString}"></c:out>
											</div>
										</div>
									</div>
								</div>
								<form action="/home/admin/comment" method="post">
									<div class="row justify-content-center">
										<div class="col-12 col-sm-3 ">
											<div class="card-body mb-3 fs-6">
												<c:out value="${user.name}"></c:out>
											</div>
										</div>
										<div class="col-12 col-sm-7 ">
											<div class="card-body mb-3 fs-5">
												<input type="text" class="form-control" name="descriptions"
													id="inputText">
											</div>
										</div>
										<div class="col-12 col-sm-2 ">
											<div class="card-body mb-3 fs-6">
												<button type="submit" class="btn btn-outline-success"
													value="${socialNetwork.id}" name="socialNetworkId"
													id="sendButton">Send</button>
											</div>

										</div>
									</div>
									<c:if test="${socialNetwork.comments.size() > 0}">
										<div class="container-sm">
											<c:forEach items="${socialNetwork.comments}" var="comment">
												<div class="row py-2">
													<div class="col-12 col-sm-3">
														<c:out value="${comment.writter.name}"></c:out>
													</div>
													<div class="col-12 col-sm-1">:</div>

													<div class="col-12 col-sm-6">
														<c:out value="${comment.descriptions}"></c:out>
													</div>
													<div class="col-12 col-sm-2 fs-6 text-info">
														<c:out value="${comment.timeString}"></c:out>
													</div>
												</div>
											</c:forEach>
										</div>
									</c:if>
								</form>
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

		$('#sendButton').prop('disabled', true);
		$('#inputText').focus();
		$('#inputText').bind('keyup change blur', function() {
			if ($('#inputText').val() != '') {
				$('#sendButton').prop('disabled', false);
			} else {
				$('#sendButton').prop('disabled', true);
			}
		});

		$('#inputText').keypress(function(e) {
			if ($('#inputText').val() != '') {
				if (e.keyCode == 13)
					$('#sendButton').click();
			}
		});
	});
</script>
</html>