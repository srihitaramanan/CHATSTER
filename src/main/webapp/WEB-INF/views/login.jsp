<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>::: insert title :::</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="font-awesome/css/font-awesome.css" rel="stylesheet">
<link href="css/animate.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">
<script type="text/javascript">
	history.pushState(null, null, '');
	window.addEventListener('popstate', function() {
		history.pushState(null, null, '');
	});
</script>
</head>
<body class="login-page">
	<div>
		<div class="middle-box loginscreen  animated fadeInDown">
			<div class="container">
				<div class="row">
					<div class="text-center">
						<h1 class="logo-name">Logo</h1>
						<small class="small"></small>
					</div>
					<div class="col-md-4 center-align text-center">
						<div class="login-form">
							<div class="circle">
								<i class="fa fa-user" aria-hidden="true"></i>
							</div>
							<div id="signIn">

								<h3 class="m-t text-center">
									<strong>Welcome to SNR</strong>
								</h3>
								<form class="login-m-t" name="Login" action="signIn" method="POST">
								
									<div class="form-group input-group">
										<span class="input-group-addon"><i
											class="fa fa-envelope"></i></span> <input type="email"
											class="form-control" id="emailAddress" name="emailAddress"
											placeholder="Email Address" />
									</div>
									<div class="form-group mar-bot-0 input-group">
										<span class="input-group-addon"><i class="fa fa-lock"></i></span>
										<input type="password" class="form-control" id="password"
											name="password" placeholder="Enter password" />
									</div>
									<div class="clearfix">&nbsp;</div>
									<span id="loginErr" style="color: red;">&nbsp;${message}</span>
									<div class="row">
									<div class="col-sm-6 col-xs-12">
										<input type="submit"
											class="btn btn-primary block full-width m-b" value="Login" style="margin-left: 75px;">
									</div>
									
								</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<script src="js/jquery-1.11.0.min.js"></script>
		<div class="clearfix"></div>
	</div>
	<div class="clearfix"></div>
</body>
</html>

