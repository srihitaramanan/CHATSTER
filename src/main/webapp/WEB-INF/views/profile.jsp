<%@page import="com.src.model.*"%>
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
<link href="font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

</head>
<body>
	<jsp:include page="layout/header.jsp" />
	<!-- header end here-->
	<div class="fixed-header"></div>
	<div class="light-gray-bg">
		<div class="container">
			<div class="row">
				<div class="col-md-12 col-xs-12">
					<div class="header-navbar">
						<ul>
							<li><a href="chat">Chat</a></li>
							<li class="active"><a href="profile">Profile</a></li>
							<li><a href="students">Student</a></li>
							<li><a href="staffs">Staff</a></li>
							<li ><a href="javascript:void(0);" data-toggle="modal" data-target="#groupModal">Create Group</a></li>
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
									</div>
									<div class="ibox-content">
										<% String authority = (String) session.getAttribute("authority"); %>
										
											<div class="form-group">
												<label class="col-lg-2 control-label">First Name</label>
												<div class="col-lg-4">
													<div class="col-lg-8">${details.getFirstName()}ok</div>
												</div>
											</div>
											<br><br>
											<div class="form-group">
												<label class="col-lg-2 control-label">Last Name</label>
												<div class="col-lg-4">
													<div class="col-lg-8">${details.getLastName()}</div>
												</div>
											</div><br><br>
											
											<div class="form-group">
												<label class="col-lg-2 control-label required">Email
													Address</label>
												<div class="col-lg-4">
													<div class="col-lg-8 control-label">${details.getEmailAddress()}</div>
												</div>
											</div><br><br>
											
											<div class="form-group">
												<label class="col-lg-2 control-label">Department</label>
												<div class="col-lg-4">
												<div class="col-lg-8 control-label">${details.getDepartment()}</div>
												</div>
											</div><br><br>
											<c:if test="${authority == 'ROLE_STUDENT'}">
												<div class="form-group">
													<label class="col-lg-2 control-label">Year</label>
													<div class="col-lg-4">
														<div class="col-lg-8 control-label">${details.getYear()}</div>
													</div>
												</div>
											</c:if>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<jsp:include page="layout/create_group.jsp" />
</body>
</html>
