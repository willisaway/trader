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
		<link type="text/css" rel="stylesheet" href="<%=path %>/assets/mmenu/dist/css/jquery.mmenu.css" />
		<link type="text/css" rel="stylesheet" href="<%=path %>/assets/mmenu/dist/css/addons/jquery.mmenu.dragopen.css" />
		
		<!-- for the one page layout -->
		<style type="text/css">
			#intro,
			#first,
			#second,
			#third
			{
				height: 400px;
			}
			#intro
			{
				padding-top: 0;
			}
			#first,
			#second,
			#third
			{
				border-top: 1px solid #ccc;
				padding-top: 150px;
			}
		</style>
		
		<!-- for the fixed header -->
		<style type="text/css">
			.header,
			.footer
			{
				box-sizing: border-box;
				width: 100%;
				position: fixed;
			}
			.header
			{
				top: 0;
			}
			.footer
			{
				bottom: 0;
			}
		</style>
		
		<script type="text/javascript" src="http://hammerjs.github.io/dist/hammer.min.js"></script>
		<script type="text/javascript" src="<%=path %>/assets/js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/assets/mmenu/dist/js/jquery.mmenu.min.js"></script>

		<script type="text/javascript" src="<%=path %>/assets/mmenu/dist/js/addons/jquery.mmenu.dragopen.min.js"></script>
		<script type="text/javascript" src="<%=path %>/assets/mmenu/dist/js/addons/jquery.mmenu.fixedelements.min.js"></script>
		<script type="text/javascript">
			$(function() {
				var $menu = $('nav#menu'),
					$html = $('html, body');

				$menu.mmenu({
					dragOpen: true
				});

				var $anchor = false;
				$menu.find( 'li > a' ).on(
					'click',
					function( e )
					{
						$anchor = $(this);
					}
				);

				var api = $menu.data( 'mmenu' );
				api.bind( 'closed',
					function()
					{
						if ( $anchor )
						{
							var href = $anchor.attr( 'href' );
							$anchor = false;

							//	if the clicked link is linked to an anchor, scroll the page to that anchor 
							if ( href.slice( 0, 1 ) == '#' )
							{
								$html.animate({
									scrollTop: $( href ).offset().top
								});	
							}
						}
					}
				);
			});
		</script>
	</head>
	<body>
		<div id="page">
			<div class="header Fixed">
				<a href="#menu"></a>
				Demo
			</div>
			<div class="content" id="content">
				<div id="intro">
					<p><strong>This is a demo.</strong><br />
						Click the menu icon to open the menu.</p>
					
					<p>The links in the menu link to a section on the same page, some small javascript makes the page scroll smoothly.</p>
				</div>
				<div id="first">
					<p><strong>This is the first section.</strong><br />
						Notice how the fixed header and footer slide out along with the page.</p>

					<p><a href="#menu">Open the menu.</a></p>
				</div>
				<div id="second">
					<p><strong>This is the second section.</strong><br />
						You can also drag the page to the right to open the menu.</p>
					<p><a href="#menu">Open the menu.</a></p>
				</div>
				<div id="third">
					<p><strong>This is the third section.</strong><br />
						<a href="#menu">Open the menu.</a></p>
				</div>
			</div>
			<div class="footer Fixed">
				Fixed footer :-)
			</div>
			<nav id="menu">
				<ul>
					<li><a href="#content">Introduction</a></li>
					<li><a href="#first">First section</a></li>
					<li><a href="#second">Second section</a></li>
					<li><a href="#third">Third section</a></li>
				</ul>
			</nav>
		</div>
	</body>
</html>