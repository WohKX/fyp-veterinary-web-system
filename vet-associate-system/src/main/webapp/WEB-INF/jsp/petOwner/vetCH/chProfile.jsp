<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Clinic/Hospital</title>
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
							<div class="container">
								<form action="/apt/petOwner/bookApt" method="post">
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
														<c:forEach items="${operationTimeList}"
															var="operationTime">
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
											<div class="card-body mt-5 mb-3">
												<c:choose>
													<c:when test="${chOwner != null}">
														<div class="container">
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
														</div>
														<div class="mb-3 row justify-content-center">
															<div class="col-12 col-sm-4">
																<input type="date" class="form-control" name="dateApt"
																	id="dateApt" min="2022-04-10" max="2022-12-31">
															</div>
															<div class="col-12 col-sm-3">
																<input type="time" class="form-control" name="timeApt"
																	id="timeApt">
															</div>
															<div class="col-12 col-sm-2">
																<button type="submit" class="btn btn-primary btn-xxl"
																	name="selectUCHButton" value="Book">Book</button>
															</div>
															<div class="col-12 col-sm-2">
																<button type="button" class="btn btn-secondary btn-xxl"
																	onclick="history.back()">Back</button>
															</div>
															<input type="hidden" value="${ch.chId}" name="chId"></input>
														</div>

													</c:when>
													<c:otherwise>
														<div class="mt-5 row justify-content-end">
															<div class="col-xl-2">
																<button type="button" class="btn btn-secondary btn-xxl"
																	onclick="history.back()">Back</button>
															</div>
														</div>
													</c:otherwise>
												</c:choose>
											</div>
											<div class="row justify-content-center">
												<div class="col-12 col-sm-8">
													<span class="badge rounded-pill bg-info text-dark fs-6">${msg}</span>
												</div>
											</div>
										</div>
									</div>
								</form>

								<div class="container-sm mt-5 justify-content-center">
									<div class="row mb-5">
										<form action="/manageUCH/petOwner/comments" method="post">
											<div class="card mt-5 justify-content-start">
												<div class="card-body">
													<label for="rating" class="form-label">Rating</label>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="ratingOption" id="inlineRadio1" value="1">
														<label class="form-check-label" for="inlineRadio1">1</label>
													</div>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="ratingOption" id="inlineRadio2" value="2">
														<label class="form-check-label" for="inlineRadio2">2</label>
													</div>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="ratingOption" id="inlineRadio3" value="3">
														<label class="form-check-label" for="inlineRadio3">3</label>
													</div>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="ratingOption" id="inlineRadio4" value="4">
														<label class="form-check-label" for="inlineRadio4">4</label>
													</div>
													<div class="form-check form-check-inline">
														<input class="form-check-input" type="radio"
															name="ratingOption" id="inlineRadio5" value="5" checked>
														<label class="form-check-label" for="inlineRadio5">5</label>
													</div>
													<div class="mt-3 row fs-4">
														<textarea class="form-control" id="descriptions"
															name="descriptions" rows="3" maxlength="500"></textarea>
													</div>
													<input type="hidden" value="${ch.chId}" name="chId"></input>
													<div class="mx-3 row fs-4 justify-content-end">
														<div class="col-12 col-sm-2">
															<button type="submit"
																class="btn btn-outline-primary fs-6" id="shareButton"
																name="shareButton">Share</button>
														</div>
													</div>
												</div>
											</div>
										</form>
									</div>
									<c:choose>
										<c:when test="${comments.size() > 0 }">
											<c:forEach items="${comments}" var="comment">
												<div class="container-sm justify-content-start">
													<div class="card">
														<div class="card-body">
															<div class="row mb-3 ps-2">
																<div class="col-sm-4">
																	<p class="text-dark text-start fs-5">${comment.writter.name}</p>
																</div>
																<div class="col-sm-4">
																	<p class="text-black-50 text-end fs-6">${comment.timeString}</p>
																</div>
																<div class="col-sm-3">
																	<p class="text-black-50 text-end fs-6">Rating:
																		${comment.ratings}</p>
																</div>
															</div>
															<div class="row mb-10 ps-2">
																<p class="text-black text-start fs-6">${comment.descriptions}</p>
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
<script type="text/javascript">
	$(function() {
		$('#shareButton').prop('disabled', true);
		$('#descriptions').bind('keyup change blur', function() {
			if ($('#descriptions').val() != '') {
				$('#shareButton').prop('disabled', false);
			} else {
				$('#shareButton').prop('disabled', true);
			}
		});
	});
</script>
</html>