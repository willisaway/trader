<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	    <%
          String path = request.getContextPath();
        %>
		<meta charset="utf-8" />
		<meta name="author" content="www.frebsite.nl" />
		<meta name="viewport" content="width=device-width initial-scale=1.0 maximum-scale=1.0 user-scalable=yes" />

		<title>jQuery.mmenu demo</title>

		<link type="text/css" rel="stylesheet" href="<%=path %>/assets/mmenu/demo/css/demo.css" />
		<link type="text/css" rel="stylesheet" href="<%=path %>/assets/mmenu/dist/css/jquery.mmenu.all.css" />

		<script type="text/javascript" src="<%=path %>/assets/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/assets/mmenu/dist/js/jquery.mmenu.min.all.js"></script>
		<script type="text/javascript">
			$(function() {
				$('nav#menu').mmenu({
					extensions	: [ 'effect-slide-menu', 'pageshadow' ],
					searchfield	: true,
					counters	: true,
					navbar 		: {
						title		: 'Advanced menu'
					},
					navbars		: [
						{
							position	: 'top',
							content		: [ 'searchfield' ]
						}, {
							position	: 'top',
							content		: [
								'prev',
								'title',
								'close'
							]
						}, {
							position	: 'bottom',
							content		: [
								'<a href="http://mmenu.frebsite.nl">Visit website</a>',
								'<a href="http://mmenu.frebsite.nl/wordpress-plugin.html">WordPress plugin</a>'
							]
						}
					]
				});
			});
		</script>
	</head>
	<body>
		<div id="page" class="mm-slideout">
			<div class="header">
				<a href="#menu"></a>
				Demo
			</div>
			<div class="content">
				<p><strong>This is an advanced demo.</strong><br />
					Click the menu icon to open the menu.</p>
				<p>For more demos, a tutorial, documentation and support, please visit <a href="http://mmenu.frebsite.nl" target="_blank">mmenu.frebsite.nl</a></p>			
			</div>
			<nav id="menu">
				<ul>
					<li><a href="#">Home</a></li>
					<li><a href="#about">About us</a>
						<ul>
							<li><a href="#about/history">History</a></li>
							<li><a href="#about/team">The team</a>
								<ul>
									<li><a href="#about/team/management">Management</a></li>
									<li><a href="#about/team/sales">Sales</a></li>
									<li><a href="#about/team/development">Development</a></li>
								</ul>
							</li>
							<li><a href="#about/address">Our address</a></li>
						</ul>
					</li>
					<li><a href="#contact">Contact</a></li>
				</ul>
			</nav>
		</div>
	</body>
</html>