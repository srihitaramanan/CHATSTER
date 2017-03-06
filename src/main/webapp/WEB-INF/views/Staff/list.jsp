<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
	<meta name="apple-mobile-web-app-capable" content="yes"/>
	<meta name = "format-detection" content = "telephone=no">
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
    <!-- Data Tables -->
	<link href="css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
  
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
							<li><a href="students">Student</a></li>
							<li class="active"><a href="staffs">Staff</a></li>
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
						<div class="col-lg-2"></div>&nbsp;
					</div>
					<div class="wrapper wrapper-content animated fadeInRight">
				<div class="row">
					<div class="col-lg-12">
						<div class="ibox float-e-margins">
							<div class="ibox-title">
								<span style="color: #18a689">${message}</span>
								<div class="ibox-tools">
									<a href="addStaff" class="btn btn-primary btn-xs"><i
										class="fa fa-pencil"></i> Add New</a>
								</div>
							</div>
							<div class="clearfix">&nbsp;</div>
							<div class="ibox-content">
										<table class="table table-striped table-bordered table-hover dataTables-example">
											<thead>
												<tr>
													<th>First Name</th>
													<th>Last Name</th>
													<th>Email Address</th>
													<th>Department</th>
													<th>Group Name</th>
													<th>Date Created</th>
													<th>Action</th>
												</tr>
											</thead>
											<tbody>
											<c:if test="${staffList != null}">
												<c:forEach items="${staffList}" var="staff">
													<tr>
														<td><c:out value="${staff.firstName}" /></td>
														<td><c:out value="${staff.lastName}" /></td>
														<td><c:out value="${staff.emailAddress}" /></td>
														<td><c:out value="${staff.department}" /></td>
														<td><c:out value="${staff.groupName}" /></td>
														<td><c:out value="${staff.rowCreated}" /></td>
														<td><a href="editStaff?id=<c:out value="${staff.userId}" />">Edit</a>&nbsp;|&nbsp;<a href="deleteStaff?id=<c:out value="${staff.userId}" />">Delete</a></td>
													</tr>
												</c:forEach>
											</c:if>
											</tbody>
										</table>
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

<!-- Data Tables -->
<script src="js/plugins/dataTables/jquery.dataTables.js"></script>
<script src="js/plugins/dataTables/dataTables.bootstrap.js"></script>

<script type="text/javascript">

$(document).ready(function() {
	 $('.dataTables-example').dataTable();
});

</script>
</body>
</html>
