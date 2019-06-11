<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>纪检监察</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<link rel="stylesheet" href="/layui.extend/modules/layui_ext/dtree/dtree.css">
<link rel="stylesheet" href="/layui.extend/modules/layui_ext/dtree/font/dtreefont.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
</head>
<body class="layui-layout-body">

	<div class="layui-row">
		<div class="layui-col-xs2">
			<div class="grid-demo">
				<ul id="org_tree_view" class="dtree" data-id="0"></ul>
			</div>
		</div>
		<div class="layui-col-xs10">
			<div class="grid-demo">
				<span class="layui-breadcrumb "  lay-separator="|">
				  <a class="layui-btn layui-btn-primary  layui-bg-orange layui-btn-xs" status="">全部</a>
				  <a class="layui-btn layui-btn-primary  layui-btn-xs" status="3">已发布</a>
				  <a class="layui-btn layui-btn-primary  layui-btn-xs" status="2">审批中</a>
				  <a class="layui-btn layui-btn-primary  layui-btn-xs" status="1">被驳回</a>
				  <a class="layui-btn layui-btn-primary  layui-btn-xs" status="0">已删除</a>
				</span>
			</div>
			<div class="grid-demo">
				<table class="layui-table" id="main_table" lay-filter="operation" lay-data="{id: 'mainData'}"> </table>
			</div>
		</div>
		
	</div>

	<div id="toolbar" style="display: none" >
		<button  class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon">&#xe608;</i>新增</button>
	</div>

<script type="text/html" id="rowbar">

  {{#  if(d.status =='2'||d.status =='1'){ }}
  	
  	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">详情</a>

  	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>

  	<a class="layui-btn layui-btn-xs" lay-event="his_audit">审批记录</a>

  	<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
  	
  {{#  } }}

  {{#  if(d.status =='3'){ }}
  	
  	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">详情</a>
  	
  	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
  	
  	<a class="layui-btn layui-btn-xs" lay-event="his_audit">审批记录</a>
  	
  	<a class="layui-btn layui-btn-xs" lay-event="his_read">阅览记录</a>
  {{#  } }}

  {{#  if(d.status =='0'){ }}
  	
  	<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="detail">详情</a>
  	
  	<a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>

  	<a class="layui-btn layui-btn-xs" lay-event="his_read">重新编辑并提交</a>
  	
  	<a class="layui-btn layui-btn-xs" lay-event="his_audit">审批记录</a>
  	
  {{#  } }}

</script>

<script type="text/html" id="statusTpl">

  {{# if(d.status=='0'){ }}
	 	已删除
  {{# } else if(d.status=='1') { }}
 		被驳回
  {{# } else if(d.status=='2') { }}
     	审核中
  {{# } else if(d.status=='3') { }}
		已发布
  {{# } }}

</script>

<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/spv/'}).use('spv_inspect', function(){
	
		var spv_inspect = layui.spv_inspect; spv_inspect();
		
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
		    <div class="layui-input-block"><!--  style="width: 600px" -->
		    	<div style="float:left;width:300px;height:450px;border:1px solid #CDC5BF;background-color: #CDC5BF;overflow-y:scroll;">
		    		<ul id="org_tree" class="dtree" data-id="0"></ul>
		    	</div>
		    	<div style="float:left;width:300px;height:450px;border:1px solid #CDC5BF;">
		    		<div style="background-color: #228B22;height: 40px;text-align: center;line-height:40px;color: white;"><h3>会员列表</h3></div>
		    		<div style="overflow-y:scroll;height:410px;"></div>
		    	</div>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">来源</label>
		    <div class="layui-input-block">
		      <input type="text" name="articleSource" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">添加时间</label>
		    <div class="layui-input-inline" style="width:272px;">
		      <input type="text" name="createTime"  class="layui-input" id="add_time" readonly="readonly">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">内容</label>
		    <textarea id="content" name="content" style="display: none;"></textarea>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">是否必读</label>
		    <div class="layui-input-inline">
		      	<select name="isRead" lay-verify="required" class="is_read"></select>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">下发机构</label>
		    <div class="layui-input-block" style="padding-left: 2px">
				<div class="layui-col-lg5" style="border:1px solid #CDC5BF;">
					<div class="layui-card">
						<div class="layui-card-header" style="background-color: #228B22;text-align: center;color: white;">请选择接收机构</div>
						<div class="layui-card-body">
							<div style="height: 350px; overflow: auto;">
								<ul id="leftTree" class="dtree" data-id="0"></ul>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-lg2">
					<div style="height: 350px;">
						<button class="layui-btn layui-btn-normal" style="margin:180px 0px 0px 7px;" id="set_btn">设置选中</button>
						<button class="layui-btn layui-btn-normal" style="margin:10px 0px 0px 7px;" id="set_btn">取消选中</button>
					</div>
				</div>
				<div class="layui-col-lg5" style="border:1px solid #CDC5BF;">
					<div class="layui-card">
						<div class="layui-card-header" style="background-color: #228B22;text-align: center;color: white;">已选择机构</div>
						<div class="layui-card-body">
							<div style="height: 350px; overflow: auto;">
								<ul id="rightTree" class="dtree" data-id="0"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		  </div>
		  <button lay-submit lay-filter="addform" id="add_form_submit" class="layui-hide">提交</button>
</form>

<form class="layui-form layui-form-pane" id="modify_form" lay-filter="modify_form" style="display: none;padding-top: 30px">
		  <input type="text" name="id" required  lay-verify="required" class="layui-input layui-hide">
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">标题</label>
		    <div class="layui-input-block">
		      <input type="text" name="title" required  lay-verify="required" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">审核人</label>
		    <div class="layui-input-block">
		    	<div style="float:left;width:300px;height:450px;border:1px solid #CDC5BF;background-color: #CDC5BF;overflow-y:scroll;">
		    		<ul id="org_tree" class="dtree" data-id="0"></ul>
		    	</div>
		    	<div style="float:left;width:300px;height:450px;border:1px solid #CDC5BF;">
		    		<div style="background-color: #228B22;height: 40px;text-align: center;line-height:40px;color: white;"><h3>会员列表</h3></div>
		    		<div style="overflow-y:scroll;height:410px;"></div>
		    	</div>
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">来源</label>
		    <div class="layui-input-block">
		      <input type="text" name="articleSource" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">添加时间</label>
		    <div class="layui-input-inline" style="width:272px;">
		      <input type="text" name="createTime"  class="layui-input" id="modify_time" readonly="readonly">
		    </div>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">内容</label>
		    <textarea id="modifyContent" name="content" style="display: none;"></textarea>
		  </div>
		  <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">是否必读</label>
		    <div class="layui-input-inline">
		      	<select name="isRead" lay-verify="required" class="is_read"></select>
		    </div>
		  </div>
		  <!-- <div class="layui-form-item" style="margin:0px 40px; margin-bottom:20px">
		    <label class="layui-form-label layui-bg-green">下发机构</label>
		    <div class="layui-input-block" style="padding-left: 2px">
				<div class="layui-col-lg5" style="border:1px solid #CDC5BF;">
					<div class="layui-card">
						<div class="layui-card-header" style="background-color: #228B22;text-align: center;color: white;">请选择接收机构</div>
						<div class="layui-card-body">
							<div style="height: 350px; overflow: auto;">
								<ul id="cskTree1" class="dtree" data-id="0"></ul>
							</div>
						</div>
					</div>
				</div>
				<div class="layui-col-lg2">
					<div style="height: 350px;">
						<button class="layui-btn layui-btn-normal" style="margin:180px 0px 0px 7px;" id="csk_btn">设置选中</button>
					</div>
				</div>
				<div class="layui-col-lg5" style="border:1px solid #CDC5BF;">
					<div class="layui-card">
						<div class="layui-card-header" style="background-color: #228B22;text-align: center;color: white;">已选择机构</div>
						<div class="layui-card-body">
							<div style="height: 350px; overflow: auto;">
								<ul id="cskTree2" class="dtree" data-id="0"></ul>
							</div>
						</div>
					</div>
				</div>
			</div>
		  </div> -->
		  <button lay-submit lay-filter="modifyform" id="modify_form_submit" class="layui-hide">提交</button>
</form>

<div class="layui-card detail" style="display: none;overflow: auto">
	<div class="layui-card-body" style="height: 580px;">
		<form class="layui-form detail_form" action="" lay-filter="detail_form">
			<div class="layui-form-item">
				<label class="layui-form-label">添加时间</label>
				<div class="layui-input-block">
					<input type="text" name="createTime"  readonly="readonly"  class="layui-input createTime">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">更新时间</label>
				<div class="layui-input-block">
					<input type="text" name="updateTime" readonly="readonly"   class="layui-input updateTime">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">浏览数量</label>
				<div class="layui-input-block">
					<input type="text" name="readCount" readonly="readonly" value="0"  class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">标题</label>
				<div class="layui-input-block">
					<input type="text" name="title" readonly="readonly" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">发布人</label>
				<div class="layui-input-block">
					<input type="text" name="createName" readonly="readonly" value="0"  class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">审核人</label>
				<div class="layui-input-block">
					<input type="text" name="checkerName" readonly="readonly" value="0"  class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">来源</label>
				<div class="layui-input-block">
					<input type="text" name="articleSource" readonly="readonly" value="0"  class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">状态</label>
				<div class="layui-input-block">
					<input type="text" name="readCount" readonly="readonly" value="0"  class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">是否必读</label>
				<div class="layui-input-block">
					<input type="text" name="isRead" readonly="readonly" class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">下发组织机构</label>
				<div class="layui-input-block">
					<input type="text" name="readCount" readonly="readonly" value="0"  class="layui-input">
				</div>
			</div>
			<div class="layui-form-item">
				<label class="layui-form-label">正文</label>
				<div class="layui-input-block">
					<textarea id="detail_content" name="content" readonly="readonly" class="layui-textarea"></textarea>
				</div>
			</div>
			
		</form>
	</div>
</div>

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