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
											id="navOwnerVets" name="ownerNavButton" value="SearchCH"
											disabled>Search Clinic/Hospital</button>
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
						<c:choose>
							<c:when test="${chProfileList.size() > 0}">
								<form action="/manageUCH/petOwner/viewCH" method="get">
									<table class="table" id="VCHTable">
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
										are no available clinic/hospital currently.</span>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#VCHTable').DataTable();
	});
</script>
</html>