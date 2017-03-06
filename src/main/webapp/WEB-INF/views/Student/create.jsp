<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="format-detection" content="telephone=no">
<meta name="description" content="">
<meta name="author" content="Mian Zaid Bin Khalid">
  <title>::: insert title :::</title>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="css/colorbox.css" rel="stylesheet" type="text/css">
<!-- Custom CSS -->
<link href="css/new-style.css" rel="stylesheet" type="text/css">
<!-- Custom Fonts -->
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">

</head>
<body>
	<jsp:include page="../layout/header.jsp" />
	<!-- header end here-->
	<div class="fixed-header"></div>
	<div class="light-gray-bg">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<div class="header-navbar">
						<ul>
							<li><a href="chat">Chat</a></li>
							<li><a href="profile">Profile</a></li>
							<li class="active"><a href="students">Student</a></li>
							<li><a href="staffs">Staff</a></li>
							<li><a href="javascript:void(0);" data-toggle="modal" data-target="#groupModal">Create Group</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<section class="new-profile-bg">
		<div class="container">
			<div class="row">
				<div class="col-sm-12 col-xs-12">
					<div class="linked-profile"></div>
					<div class="row wrapper border-bottom white-bg page-heading">
						<div class="col-lg-2"></div>
						
					</div>
					<div class="wrapper wrapper-content animated fadeInRight">
						<div class="row">
							<div class="col-lg-12">
								<div class="ibox float-e-margins">
									<div class="ibox-title">
										<h3>Add Student</h3>
									</div>
									<div class="ibox-content">
										<span style="color: red" id="message">${message}</span><br>

										<form:form class="form-horizontal" action="saveStudent"
											method="post" modelAttribute="student" name="student">

											<div class="form-group">
												<label class="col-lg-4 control-label">First Name</label>
												<div class="col-lg-4">
													<form:input type="text" placeholder="First Name"
														class="form-control" name="firstName" id="fname"
														path="firstName" />
													<span id="fnameErr" style="color: red"></span>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-4 control-label">Last Name</label>
												<div class="col-lg-4">
													<form:input type="text" placeholder="Last Name"
														class="form-control" name="lastName" id="lname"
														path="lastName" />
													<span id="lnameErr" style="color: red"></span>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-4 control-label required">Email
													Address</label>
												<div class="col-lg-4">
													<form:input type="text" placeholder="Email Address"
														class="form-control" name="emailAddress" id="emailAddress"
														path="emailAddress" />
													<span id="emailErr" style="color: red"></span>
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-4 control-label">Password</label>
												<div class="col-lg-4">
													<form:input type="password" placeholder="Password"
														class="form-control" name="password" id="password"
														path="password" />
												</div>
											</div>
											
											<div class="form-group">
												<label class="col-lg-4 control-label">Department</label>
												<div class="col-lg-4">
													<select name="department" class="form-control">
														<option value="">Select Department</option>
														<c:forEach items="${departmentList}" var="dept">
															<option value="${dept.departmentName}"> ${dept.departmentName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-4 control-label">Year</label>
												<div class="col-lg-4">
													<select name="year" class="form-control">
														<option value="">Select Year</option>
														<c:forEach items="${yearList}" var="year">
															<option value="${year.year}"> ${year.year}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-4 control-label required">User
													Type</label>
												<div class="col-lg-4">
													<select name="userRole" class="form-control" id="role">
														<option value="">Select User Type</option>
														<c:forEach items="${roleList}" var="role">
														<c:if test="${role.authority == 'ROLE_STUDENT'}">
															<option value="${role.authority}" selected="selected"> ${role.authority}</option>
														</c:if>
															
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group">
												<label class="col-lg-4 control-label">Select Group</label>
												<div class="col-lg-4">
													<select name="groupName" class="form-control">
														<option value="">Select Group</option>
														<c:forEach items="${groupList}" var="group">
															<option value="${group.groupName}"> ${group.groupName}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="clearfix">&nbsp;</div>
											
											<div class="form-group">
												<label class="col-lg-4 control-label">&nbsp;</label>
												<div class="col-lg-8">
													<a href="students" class="btn btn-primary"><strong>Cancel</strong></a>
													&nbsp; &nbsp; <input type="submit" class="btn btn-primary"
														value="Save">
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="../layout/create_group.jsp" />
	<script type="text/javascript" src="js/jquery-1.10.2.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
</body>
</html>
