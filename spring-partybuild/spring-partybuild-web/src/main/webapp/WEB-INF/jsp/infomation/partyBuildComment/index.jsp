<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>党建评论</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">

	
	<div class="layui-row">
		<div class="layui-col-xs2">
			<div class="grid-demo" id="org_tree"></div>
		</div>
		<div class="layui-col-xs10">
			<div class="grid-demo">
				<!-- <form action="" class="layui-form layui-form-pane" >
					<div class="layui-form-item">
						<div class="layui-inline">
							<label class="layui-form-label">验证手机</label>
							<div class="layui-input-inline">
								<input type="tel" name="phone" lay-verify="required|phone"
									autocomplete="off" class="layui-input">
							</div>
						</div>
						<div class="layui-inline">
							<label class="layui-form-label">验证邮箱</label>
							<div class="layui-input-inline">
								<input type="text" name="email" lay-verify="email"
									autocomplete="off" class="layui-input">
							</div>
						</div>
					</div>
				</form> -->
				<span class="layui-breadcrumb "  lay-separator="|">
				  <a class="layui-btn layui-btn-primary  layui-bg-orange layui-btn-xs">已发布</a>
				  <a class="layui-btn layui-btn-primary  layui-btn-xs">审批中</a>
				  <a class="layui-btn layui-btn-primary  layui-btn-xs">被驳回</a>
				  <a class="layui-btn layui-btn-primary  layui-btn-xs">已删除</a>
				</span>
			</div>
			<div class="grid-demo">
				<table class="layui-table" id="main_table" lay-filter="operation" lay-data="{id: 'mainData'}"> </table>
			</div>
		</div>
	</div>


	




	<div id="div_item_code_tab" style="display: none">
		<table class="layui-table" id="tab_item_code" lay-filter="item_code" lay-data="{id: 'itemCodeData'}"> </table>
	</div>
	
	
	<div id="toolbar" style="display: none" >
		<button  class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe608;</i>新增</button>
	</div>

<script type="text/html" id="rowbar">

  	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">详情</a>

  	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>

	<a class="layui-btn layui-btn-xs" lay-event="his_read">阅览记录</a>

	<a class="layui-btn layui-btn-xs" lay-event="his_audit">审批记录</a>

  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>

</script>

<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/information/'}).use('info_partyBuildComment', function(){
	
		var info_partyBuildComment = layui.info_partyBuildComment; info_partyBuildComment();
		
	})
</script>

</body>

<form class="layui-form layui-form-pane" id="add_form" style="display: none;padding-top: 30px">
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">标题</label>
		    <div class="layui-input-block">
		      <input type="text" name="title" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">审核人</label>
		    <div class="layui-input-block">
		      <input type="text" name="ITEM_CODE" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">来源</label>
		    <div class="layui-input-block">
		      <input type="text" name="ITEM_NAME" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">添加时间</label>
		    <div class="layui-input-inline" style="width:272px;">
		      <input type="text" name="ITEM_VALUE"  class="layui-input" id="add_time" readonly="readonly">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">内容</label>
		    <textarea id="content" style="display: none;"></textarea>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">是否必读</label>
		    <div class="layui-input-inline">
		      	<select name="IS_READ" lay-verify="required" class="is_read" lay-filter="addIsRead"></select>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">下发机构</label>
		    <div class="layui-input-block">
		      	
		    </div>
		  </div>
		  <button lay-submit lay-filter="addform" id="add_form_submit" class="layui-hide">提交</button>
</form>

<!-- <form class="layui-form layui-form-pane" id="modify_form" lay-filter="modify_form" style="display: none;padding-top: 30px">
		  <input type="text" name="ID" required  lay-verify="required" class="layui-input layui-hide">
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">上级选项码</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="PARENT_ITEM_CODE" required  lay-verify="required" value="0" class="layui-input"><button style="position: absolute;top: 0;right: 1px; cursor: pointer;" type="button" class="layui-btn btn_item_code" value="modify">选择</button>
		      <input type="text" name="PID" required  value="0" lay-verify="required" class="layui-input layui-hide">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项码</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_CODE" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项名称</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_NAME" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">选项值</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="ITEM_VALUE"  class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">状态</label>
		    <div class="layui-input-block status" style="width:300px;">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin-left: 120px">
		    <label class="layui-form-label layui-bg-green">描述</label>
		    <div class="layui-input-block" style="width:300px;">
		      <input type="text" name="REMARK"  class="layui-input"></input>
		    </div>
		  </div>
		  <button lay-submit lay-filter="modifyform" id="modify_form_submit" class="layui-hide">提交</button>
</form> -->

<div class="layui-card hisread" style="display: none">
	<div class="layui-card-header" style="height: 70px;">
		<div class="layui-row">
			<div class="layui-input-inline" style="margin: 15px 0px; width: 300px">
				<input type="text" name="ITEM_NAME" class="layui-input">
			</div>
			<a class="layui-btn layui-btn-danger">搜索</a>
			<div class="layui-input-inline" style="margin-left: 20px">
				<h1 style="color:#03a9f4 ">阅读人数:</h1>
			</div>
		</div>
		
	</div>
	<div class="layui-card-body">
   		<table class="layui-table" id="hisread_table" lay-data="{id: 'hisread_data'}"> </table>
  	</div>
</div>

<div class="layui-row hisaudit" style="display: none;margin: 15px 20px;">
	<ul class="layui-timeline">
		<li class="layui-timeline-item"><i
			class="layui-icon layui-timeline-axis"></i>
			<div class="layui-timeline-content layui-text">
				<div class="layui-timeline-title">2018年，layui 5.0
					发布。并发展成为中国最受欢迎的前端UI框架（期望）</div>
			</div></li>
		<li class="layui-timeline-item"><i
			class="layui-icon layui-timeline-axis"></i>
			<div class="layui-timeline-content layui-text">
				<div class="layui-timeline-title">2017年，layui 里程碑版本 2.0 发布</div>
			</div></li>
		<li class="layui-timeline-item"><i
			class="layui-icon layui-timeline-axis layui-bg-orange"></i>
			<div class="layui-timeline-content layui-text">
				<div class="layui-timeline-title">2016年，layui 首个版本发布</div>
			</div></li>
		<li class="layui-timeline-item"><i
			class="layui-icon layui-timeline-axis"></i>
			<div class="layui-timeline-content layui-text">
				<div class="layui-timeline-title">2015年，layui 孵化</div>
			</div></li>
	</ul>
</div>

</html>