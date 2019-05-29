<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>党组织机构管理</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">
	
	<table  id="org_charts" lay-filter="operate"> </table>

<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/system/'}).use('base_organization', function(){
	
		var base_organization = layui.base_organization; base_organization()
		
	})
</script>

</body>

<form class="layui-form layui-form-pane" id="add_form" style="display: none;padding-top: 30px">
		  <input type="hidden" name="id" class="pid">	
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">组织名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="orgName" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">组织地址</label>
		    <div class="layui-input-block">
		      <input type="text" name="address" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">描述</label>
		    <div class="layui-input-block">
		      <input type="text" name="remark" class="layui-input">
		    </div>
		  </div>
		  <!-- <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">论文名称</label>
		    <div class="layui-input-block">
		      <input type="text" name="orgName" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">发表人</label>
		    <div class="layui-input-block">
		      <input type="text" name="address" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">论文内容</label>
		    <div class="layui-input-block">
		      <input type="text" name="remark" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">指导教师</label>
		    <div class="layui-input-block">
		      <input type="text" name="remark" class="layui-input">
		    </div>
		  </div> -->
		  <button lay-submit lay-filter="addform" id="add_form_submit" class="layui-hide">提交</button>
</form>


</html>