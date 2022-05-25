<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chatbot List</title>
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
											name="adminNavButton" value="ManageChatbot" disabled>Chatbot</button>
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
					<div class="row">
						<form action="/chatbot/admin/view" method="post">
							<div class="container">

								<table class="table" id="chatbotTable">
									<thead>
										<tr>
											<th>Id</th>
											<th>PetClass</th>
											<th>Questions</th>
											<th>Answers</th>
											<th>##</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${chatbotList}" var="chatbot">
											<tr>
												<td><c:out value="${chatbot.id}" /></td>
												<td><c:out value="${chatbot.classes}" /></td>
												<td><c:out value="${chatbot.questions}" /></td>
												<td><c:out value="${chatbot.answers}" /></td>
												<td><button class="btn btn-outline-info btn-sm"
														type="submit" name="id" value="${chatbot.id}">Edit</button></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</form>
					</div>
					<div class="row">
						<div class="col-12 col-sm-10 mt-3 justify-content-center">
							<span class="badge rounded-pill bg-info text-dark">${msg}</span>
						</div>
						<div class="col-12 col-sm-2 mt-3 justify-content-end">
							<form action="/chatbot/admin/add" method="get">
								<button class="btn btn-primary btn-md" type="submit"
									name="manageChatbotButton" value="add">Add</button>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		$('#chatbotTable').DataTable();
	});
</script>
</html>