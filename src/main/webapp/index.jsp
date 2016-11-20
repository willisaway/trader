<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@include file="/assets/common/base.jsp"%>
<%@include file="/assets/common/menu.jsp"%>
<html>
<head>
	
</head>
<body>
<div id="page" class="mm-slideout">
	<!--头部 start-->
	<header>
		<div class="l_tbn"><a id="hamburger" href="#menu" class="glyphicon glyphicon-th-large" ><span></span></a></div>
		<!--标题-->
	</header>
	<!--头部 end-->
	
	<!--正文 start-->
	<div class="container-fluid">
		<div class="row-fluid">
			<div class="span12">
				<iframe id="main" src="<%=path%>/data/stock/operator/page.do" style="width:100%;"></iframe>
			</div>
		</div>
	</div>
	<!--正文 end-->
	
	<!--侧边菜单 start-->
	<nav id="menu">
		<ul>
			<li class="Selected"><a href="#">数据下载操作</a></li>
			<li><a href="#">按钮2</a></li>
			<li><a href="#">按钮3</a></li>
			<li><a href="#">按钮4</a></li>
			<li><a href="#">按钮5</a></li>
		</ul>
	</nav>
	<!--侧边菜单 end-->
</div>
</body>
<script language="javascript">
	$(window.parent.document).find("#main").load(function(){
		resizeHeight();
	});
	$(window).load(function(){
		resizeHeight();
	});
	function resizeHeight(){
		var main = $(window.parent.document).find("#main");
		var thisheight = $(document).height()-30;
		main.height(thisheight);
	}
</script>
</html>
