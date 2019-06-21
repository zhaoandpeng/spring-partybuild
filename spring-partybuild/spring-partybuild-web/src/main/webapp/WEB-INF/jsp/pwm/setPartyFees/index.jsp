<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>党费设置</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">

	<div class="layui-row">
		<form class="layui-form" id="view_form" style="padding-top: 30px"></form>
	</div>

	<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/pwm/'}).use('pwm_setPartyFees', function(){
	
		var pwm_setPartyFees = layui.pwm_setPartyFees; pwm_setPartyFees();
		
	})
</script>

</body>
</html>