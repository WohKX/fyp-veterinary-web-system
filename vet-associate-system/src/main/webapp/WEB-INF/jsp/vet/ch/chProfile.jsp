<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Check Clinic/Hospital</title>
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
													id="navVetInquiry" name="vetNavButton" value="Inquiry">Inquiry</button>
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
							<div class="container">
								<div class="container-sm mt-5">
									<div class="card text-dark bg-light">
										<div class="card-header fs-3">
											<c:out value="${ch.chName}" />
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Street</h5>
													<c:out value="${ch.street}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">Poscode</h5>
													<c:out value="${ch.poscode}" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">District</h5>
													<c:out value="${ch.district}" />
												</div>
											</div>
											<div class="col-sm-6">
												<div class="card-body mb-3">
													<h5 class="card-title">States</h5>
													<c:out value="${ch.states}" />
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-sm-4">
												<div class="card-body mb-3">
													<h5 class="card-title">Status</h5>
													<c:out value="${ch.chStatus}" />
												</div>
											</div>
											<div class="col-sm-4">
												<div class="card-body mb-3">
													<h5 class="card-title">Ratings</h5>
													<c:out value="${ch.ratings}" />
												</div>
											</div>
											<div class="col-sm-4">
												<div class="card-body mb-3">
													<h5 class="card-title">Rating No</h5>
													<c:out value="${ch.ratingNo}" />
												</div>
											</div>
										</div>
										<c:if test="${ch.phoneNo != null}">
											<div class="row">
												<div class="col-sm-6">
													<div class="card-body mb-3">
														<h5 class="card-title">Phone Number</h5>
														<c:out value="${ch.phoneNo}" />
													</div>
												</div>
											</div>
										</c:if>
										<c:if test="${operationTimeList.size() > 0}">
											<div class="row">
												<div class="card-body mb-3">
													<c:forEach items="${operationTimeList}" var="operationTime">
														<div class="row ms-4">
															<c:out value="${operationTime.day}" />
															:
															<c:out value="${operationTime.startTime}" />
															-
															<c:out value="${operationTime.endTime}" />
														</div>
													</c:forEach>
												</div>
											</div>
										</c:if>
										<div class="card-body mt-5">
											<c:choose>
												<c:when test="${chOwner != null}">
													<form action="/messages/vet" method="get">
														<div class="mb-3 row justify-content-start">
															<div class="col-12 col-sm-6">
																<label for="nameLabel" class="form-label fs-4">Person
																	In Charge: </label>
															</div>
															<div class="col-12 col-sm-6">
																<c:out value="${chOwner.realName}" />
															</div>
														</div>
														<div class="mb-3 row justify-content-start">
															<div class="col-12 col-sm-6">
																<label for="emailLabel" class="form-label fs-4">Registered
																	Email: </label>
															</div>
															<div class="col-12 col-sm-6">
																<c:out value="${chOwner.email}" />
															</div>
														</div>
														<div class="mb-3 row justify-content-start">
															<div class="col-12 col-sm-6">
																<label for="phoneNoLabel" class="form-label fs-4">Phone
																	No: </label>
															</div>
															<div class="col-12 col-sm-6">
																<c:out value="${chOwner.phoneNo}" />
															</div>
														</div>
														<div class="mt-5 row justify-content-end">
															<div class="col-xl-4 fs-5">
																<button type="submit" class="btn btn-primary btn-xxl"
																	name="veterinarianId" value="${chOwner.id}">Chat
																	with the P.I.C</button>
															</div>
															<div class="col-sm-2">
																<button type="button" class="btn btn-secondary btn-xxl"
																	onclick="history.back()">Back</button>
															</div>
														</div>
													</form>
												</c:when>
												<c:otherwise>
													<div class="mt-5 row justify-content-end">
														<div class="col-12 col-sm-4">
															<button type="button" class="btn btn-primary btn-sm fs-5"
																onclick="formToggle()" id="toggleFormButton">
																<small>I am the owner</small>
															</button>
														</div>
														<div class="col-12 col-sm-2">
															<button type="button"
																class="btn btn-secondary btn-sm fs-5"
																onclick="history.back()">Back</button>
														</div>
													</div>
												</c:otherwise>
											</c:choose>
										</div>
										<c:if test="${vetCHId != ch.chId }">
											<div class="card-body mt-5">
												<div class="mb-3 row justify-content-sm-center">
													<form action="/manageUCH/vet/addAsMember" method="get">
														<div class="col-sm-6">
															<button type="submit"
																class="btn btn-outline-success btn-sm fs-5" name="chId"
																value="${ch.chId}">I am working in this
																clinic/hospital.</button>
														</div>
													</form>
												</div>
											</div>
										</c:if>
										<span class="badge rounded-pill bg-info text-dark fs-5">${msg}</span>
									</div>
								</div>
								<div class="container-sm mt-4 justify-content-center"
									id="formDiv" style="display: none;">
									<form action="/ticket/vet/addAsOwner" method="post"
										enctype="multipart/form-data">
										<div class="card text-dark bg-light">
											<div class="card-header fs-3">Details and Information</div>
											<div class="card-body mb-3">
												<div class="card-body">
													<div class="mb-3 row">
														<label for="descriptionLabel"
															class="col-12 col-sm-3 col-form-label">Description</label>
														<div class="col-12 col-sm-8 mt-3">
															<textarea class="form-control" name="description"
																id="description"
																placeholder="Please enter the description" rows="3"></textarea>
														</div>
													</div>
													<div class="mb-3 row">
														<label for="provenImage"
															class="col-12 col-sm-3 col-form-label">Proven
															Image</label>
														<div class="col-12 col-sm-4 mt-3">
															<input class="form-control" type="file"
																accept=".png, .jpg, .jpeg" id="provenImage"
																name="provenImage">
														</div>
													</div>
													<input type="hidden" value="${ch.chId}" name="chId">
												</div>
											</div>
											<div class="card-body mb-3">
												<div class="card-body">
													<div class="mb-3 row justify-content-sm-center">
														<div class=" col-12 col-sm-3">
															<button type="submit" class="btn btn-primary btn-sm fs-5"
																id="submitButton">Confirm</button>
														</div>
													</div>
												</div>
											</div>
										</div>
									</form>
								</div>
								<div class="container-sm mt-5 justify-content-center"
									id="commentDiv" style="display: block;">
									<c:choose>
										<c:when test="${comments.size() > 0 }">
											<c:forEach items="${comments}" var="comment">
												<div class="container-sm justify-content-start">
													<div class="card">
														<div class="card-body">
															<div class="row mb-3">
																<div class="col-sm-6">
																	<p class="text-dark text-start fs-5">${comment.writter.name}</p>
																</div>
																<div class="col-sm-3">
																	<p class="text-black-50 text-end fs-6">${comment.commentTime}</p>
																</div>
																<div class="col-sm-3">
																	<p class="text-black-50 text-end fs-6">Rating:
																		${comment.ratings}</p>
																</div>
															</div>
															<div class="row mb-10">
																<p class="text-black-50 text-end fs-6">${comment.descriptions}</p>
															</div>
														</div>
													</div>
												</div>
											</c:forEach>
										</c:when>
										<c:otherwise>
											<div class="container-sm justify-content-center">
												<span class="badge rounded-pill bg-info text-dark">There
													are no comments about this clinic/hospital currently.</span>
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
	</div>
</body>
<script
	src="${pageContext.request.contextPath}/js/veterinarianPage/chProfile.js"></script>
</html>