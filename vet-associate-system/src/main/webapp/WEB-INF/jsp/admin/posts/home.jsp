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
					<div class="row justify-content-start">
						<form action="/home/admin/search" method="post">
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
							<form action="/home/admin/newPost" method="post"
								enctype="multipart/form-data">
								<div class="row mb-3 justify-content-start">
									<div class="col-12 col-sm-3">
										<label for="username" class="form-label fs-5">${admin.username}</label>
									</div>
								</div>
								<div class="row mb-3">
									<textarea class="form-control fs-6" id="descriptions"
										name="descriptions" rows="3"></textarea>
								</div>
								<div class="row mb-3">
									<div class="col-12 col-sm-6 justify-content-start">
										<input class="form-control" type="file"
											accept=".png, .jpg, .jpeg" id="snImage" name="snImage">
									</div>
									<div class="col-12 col-sm-6 justify-content-end">
										<button type="submit" class="btn btn-inline-primary fs-6"
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
									<form action="/home/admin/view" method="get">
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
																	<c:out value="${comment.writter.id}"></c:out>
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
										<div class="row justify-content-end">
											<div class="col-12 col-sm-3">
												<button type="submit"
													class="btn btn-outline-success fs-6 mb-3"
													value="${socialNetwork.id}" name="socialNetworkId">View
													Details</button>
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
	});
</script>
</html>