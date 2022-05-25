<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Clinic/Hospital List</title>
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
    background-image: url('${pageContext.request.contextPath}/images/bg2.png');
    height: 100vh;
  ">
		<div class="container-sm" style="height: 100vh">
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
												name="vetNavButton" value="MyCH">My Clinic/Hospital</button>
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
						<div class="row">
							<div class="container">
								<c:choose>
									<c:when test="${chProfileList.size() > 0}">
										<form action="/manageUCH/vet/viewCH" method="get">
											<table class="table" id="UCHTable">
												<thead>
													<tr>
														<th>Name</th>
														<th>Poscode</th>
														<th>District</th>
														<th>States</th>
														<th>Status</th>
														<th>Rating</th>
														<th>##</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${chProfileList}" var="ch">
														<tr>
															<td><c:out value="${ch.chName}" /></td>
															<td><c:out value="${ch.poscode}" /></td>
															<td><c:out value="${ch.district}" /></td>
															<td><c:out value="${ch.states}" /></td>
															<td><c:out value="${ch.chStatus}" /></td>
															<td><c:out value="${ch.ratings}" /></td>
															<td><button class="btn btn-outline-info btn-sm"
																	type="submit" name="chId" value="${ch.chId}">View</button></td>
														</tr>
													</c:forEach>
												</tbody>
											</table>
										</form>

									</c:when>
									<c:otherwise>
										<div class="container justify-content-center fs-3">
											<span class="badge rounded-pill bg-info text-dark">There
												are no clinic/hospitals available currently.</span>
										</div>
									</c:otherwise>
								</c:choose>
								<span class="badge rounded-pill bg-light text-info">${msg }</span>
							</div>
						</div>
						<div class="row">
							<div class="col-12 col-sm-8 mt-3"></div>
							<div class="col-12 col-sm-4 mt-3">
								<form action="/manageUCH/vet/addCH">
									<button type="submit" class="btn btn-outline-success btn-md">Add
										New Clinic/Hospital</button>
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
		$('#UCHTable').DataTable();
	});
</script>
</html>