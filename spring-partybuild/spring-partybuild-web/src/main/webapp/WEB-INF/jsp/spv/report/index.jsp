<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>举报信息处理</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">

	
	<div class="layui-row">
		<div class="layui-col-xs10">
			<div id="toolbar" style="display: none" >
				<button  class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe608;</i>新增</button>
				<!-- <button  class="layui-btn layui-btn-sm" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</button> -->
			</div>
			<div class="grid-demo">
				<table class="layui-table" id="main_table" lay-filter="operation" lay-data="{id: 'mainData'}"> </table>
			</div>
		</div>
	</div>

<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/spv/'}).use('spv_report', function(){
	
		var spv_report = layui.spv_report; spv_report();
		
	})
</script>

<script type="text/html" id="rowbar">

  	
  	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>

  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  	

</script>

</body>

<form class="layui-form layui-form-pane" id="add_form" style="display: none;padding-top: 30px">
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">举报人</label>
		    <div class="layui-input-block">
		      <input type="text" name="reportName" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">电话</label>
		    <div class="layui-input-block">
		      <input type="text" name="tel" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">身份证</label>
		    <div class="layui-input-block">
		      <input type="text" name="cardNo" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">被举报人</label>
		    <div class="layui-input-block">
		      <input type="text" name="reportedName" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">单位</label>
		    <div class="layui-input-block">
		      <input type="text" name="company" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">职务</label>
		    <div class="layui-input-block">
		      <input type="text" name="job" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">标题</label>
		    <div class="layui-input-block">
		      <input type="text" name="title" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">内容</label>
		    <div class="layui-input-block">
		      <textarea name="content" placeholder="请输入内容" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">举报类型</label>
		    <div class="layui-input-inline">
		      <select name="reportType" class="add_report_type" lay-verify="required"></select>
		    </div>
		  </div>
		  <button lay-submit lay-filter="addform" id="add_form_submit" class="layui-hide">提交</button>
</form>

<form class="layui-form layui-form-pane" id="modify_form" lay-filter="modify_form" style="display: none;padding-top: 30px">
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">举报人</label>
		    <div class="layui-input-block">
		      <input type="text" name="reportName" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">电话</label>
		    <div class="layui-input-block">
		      <input type="text" name="tel" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">身份证</label>
		    <div class="layui-input-block">
		      <input type="text" name="cardNo" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">被举报人</label>
		    <div class="layui-input-block">
		      <input type="text" name="reportedName" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">单位</label>
		    <div class="layui-input-block">
		      <input type="text" name="company" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">职务</label>
		    <div class="layui-input-block">
		      <input type="text" name="job" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">标题</label>
		    <div class="layui-input-block">
		      <input type="text" name="title" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">内容</label>
		    <div class="layui-input-block">
		      <textarea name="content" placeholder="请输入内容" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">举报类型</label>
		    <div class="layui-input-inline">
		      <select name="reportType" class="modify_report_type" lay-verify="required"></select>
		    </div>
		  </div>
		  <button lay-submit lay-filter="modifyform" id="modify_form_submit" class="layui-hide">提交</button>
</form>
</html>