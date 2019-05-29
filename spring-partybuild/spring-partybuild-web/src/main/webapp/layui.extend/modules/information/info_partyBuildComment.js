layui.define(['layer', 'element', 'laydate', 'layedit', 'jquery', 'table', 'zTree', 'form'], function(exports){  
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table,  form = layui.form, laydate = layui.laydate, layedit = layui.layedit, zTreeObj; 
  
  var tableIns = table.render({
	  	id:'mainData',
	    elem: '#main_table',
	    skin:'row',
	    even:true,
	    toolbar:'#toolbar',
	    url: '/api/v1/sys/dict/index/view',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'ID', title: '序号', width:300, align: 'center'},
	    	{field: 'PID', title: '标题', width:300, align: 'center'},
	    	{field: 'PARENT_ITEM_CODE', title: '状态', width:180, align: 'center'},
	    	{field: 'ITEM_CODE', title: '发布人', width:180, align: 'center'},
	    	{field: 'ITEM_NAME', title: '发布时间', width:150, align: 'center'},
	    	{fixed: 'right', title: '操作', width: 320, align:'center', toolbar: '#rowbar'}
	    ]],
	    done : function(){
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	    }
  });
  
  var setting = {
		  view: {
			  showLine: true,
			  fontCss:{'color':'black','font-weight':'bold'},
			  selectedMulti: true 
		  },
		  check:{
			  chkStyle: "checkbox",
			  enable: true 
		  },
		  data: {
			  simpleData: {
				  enable:true,
				  idKey: "id",
				  pIdKey: "pId",
				  rootPId: null,
			  }
		  }
  };
  var setting = {};
  
  var zNodes = [
	   {name:"test1", open:true, children:[
	      {name:"test1_1"}, {name:"test1_2"}]},
	   {name:"test2", open:true, children:[
	      {name:"test2_1"}, {name:"test2_2"}]}
	   ];

  zTreeObj = layui.zTree.init($("#org_tree"), setting, zNodes);
  
  table.on('toolbar(operation)', function(obj){
	  
	  	var checkStatus = table.checkStatus(obj.config.id) ,data = checkStatus.data; 
	  	switch(obj.event){
	  			case 'add': add(); break;
	  	};
  });
  
  table.on('tool(operation)', function(obj){
	    var data = obj.data, layEvent = obj.event; //获得 lay-event 对应的值
	    switch(obj.event){
			case 'his_read': history_read(data); break;
			case 'his_audit': his_audit(data); break;
			case 'del': del(data); break;
			case 'detail': detail(data); break;
			case 'edit': edit(data); break;
	    };
  });
  
  
  exports('info_partyBuildComment', function(){ }); 
  
  
  /*加载父级选项码选择表*/
  $(".btn_item_code").click(function(){
	  
	  var operate = this.value;
	  
	  table.on('rowDouble(item_code)', function(row){
		  
		  var pid = row.data.ID;
		  
		  var item_code = row.data.ITEM_CODE;
		  
		  if(operate.indexOf('add')!=-1){
			  
			  $('#add_form input[name="PID"]').val(pid);
			  
			  $('#add_form input[name="PARENT_ITEM_CODE"]').val(item_code);
			  
		  }
		  if(operate.indexOf('modify')!=-1){
			  
			  $('#modify_form input[name="PID"]').val(pid);
			  
			  $('#modify_form input[name="PARENT_ITEM_CODE"]').val(item_code);
			  
		  }
		  
		  layer.close(view_item_code);
	  });
	  
	  var view_item_code = layer.open({ 
		  type: 1, title:"父级选项码选择表",
		  resize : false,
		  maxmin: true,
		  btnAlign: 'c',
		  area: ['650px', '400px'],
		  skin: 'demo-class',
		  content: $('#div_item_code_tab'),
		  success:function(){
			  $('#div_item_code_tab').css('display','block');
		  }
	  });
	  
	  var item_code_tab = table.render({
		  id:'itemCodeData',
		  elem: '#tab_item_code',
		  skin:'row',
		  even:true,
		  url: '/api/v1/sys/dict/get/item/code',
		  page: true ,
		  cols: [[ 
			  {field: 'PID', title: '父级主键', width:150, align: 'center',hide:true},
			  {field: 'ITEM_CODE', title: '选项码', width:200, align: 'center'},
			  {field: 'ITEM_NAME', title: '选项描述', width:250, align: 'center'},
			  {field: 'STATUS', title: '选项状态', width:150, align: 'center',hide:true}
			  ]],
			  done : function(){
				  $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
			  }
	  });
  })
  
  /**新增发布通知公告**/
  
  function add(){
	  
	  var add = layer.open({ 
		  type: 1, title:"新增通知公告",
		  resize : false,
		  maxmin: true,
		  btn: [ '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['850px', '650px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  $("#add_form_submit").trigger("click");
		  },
		  success:function(){
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/read_mark', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.ITEM_NAME,value.ITEM_VALUE);
							  $('#add_form .is_read').append(option);
							  form.render('select');
						  })
					  }
				  }
			  });
			  layedit.build('content'); //初始化富文本编辑器
			  
			  laydate.render({
				  	elem: '#add_time', //绑定日期格式INPUT元素
				    type: 'datetime',
				    value: new Date()
			  });
		  },
		  end:function(){
			  location.reload();
		  }
	  });
	  
	  form.on('submit(addform)', function(data){
		  $.ajax({
			  type: 'POST',  url: '/api/v1/sys/dict/add_or_update', dataType : "json", data: data.field,
			  success: function(result) { 
				  if(result.data.status){
					  layer.msg('保存成功'); layer.close(add);  tableIns.reload({ page:{ curr: 1 }});
				  }else{
					  layer.msg('保存失败 [ '+result.data.message+']');
				  }
			  }
		  });
		  return false;
	  });
	  
	  form.on('radio(addform)', function(data){
		  $('input:radio[name=status]')[0].checked = false;
		  form.render('radio');
	  });
	  
  }
  
  /**编辑已发布通知公告**/
  
  function modify(data){
	  
	  form.val('modify_form', data[0]);
	  
	  var modify = layer.open({ 
		  type: 1, title:"修改字典",
		  resize : false,
		  btn: [  '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['650px', '480px'],
		  skin: 'demo-class',
		  content: $('#modify_form'),
		  yes:function(index,layero){
			  $("#modify_form_submit").trigger("click");
		  },
		  success:function(){
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/enable_disable_mark', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  var html = "";
						  $.each(result.data,function(index,value){
							  if(value.ITEM_VALUE=='1'){
								  html = html + '<input type="radio" name="STATUS" value="'+value.ITEM_VALUE+'" title="'+value.ITEM_NAME+'" checked>'
							  }else{
								  html =  '<input type="radio" name="STATUS" value="'+value.ITEM_VALUE+'" title="'+value.ITEM_NAME+'">'
							  }
						  })
						  $('#modify_form .status').append(html);
						  form.render('radio');
					  }
				  }
			  });
		  },
		  end:function(){
			  location.reload();
		  }
	  });
	  
	  form.on('submit(modifyform)', function(data){
		  $.ajax({
			  type: 'POST',  url: '/api/v1/sys/dict/add_or_update', dataType : "json", data: data.field,
			  success: function(result) { 
				  if(result.data.status){
					  layer.msg('修改成功'); layer.close(modify);  tableIns.reload({ page:{ curr: 1 }});
				  }else{
					  layer.msg(result.data.message);
				  }
			  }
		  });
		  return false;
	  });
	  
  }
  
  /**删除已发布通知公告**/
  
  function del(data){
	  
	  var ids = new Array(); 
	  
	  $.each(data,function(index,value){
		  
		  ids.push(value.ID);
	  })
	  $.ajax({
		  type: 'POST',  url: '/api/v1/sys/dict/delete/'+ids.join(','), dataType : "json",
		  success: function(result) { 
			  if(result.data.status){
				  layer.msg('删除成功');  tableIns.reload({ page:{ curr: 1 }});
			  }else{
				  layer.msg(result.data.message);
			  }
		  }
	  });
  }
  
  /**阅读记录展示页面**/
  
  function history_read(data){
	  
	  var hisread = layer.open({ 
		  type: 1, title:"阅读记录",
		  resize : false,
		  maxmin: true,
		  btnAlign: 'c',
		  area: ['650px', '450px'],
		  skin: 'demo-class',
		  content: $('.hisread'),
		  success:function(){
			  var hisread_table = table.render({
				  	id:'hisread_data',
				    elem: '#hisread_table',
				    skin:'row',
				    even:true,
				    url: '/api/v1/sys/dict/index/view',
				    page: false ,
				    cols: [[ 
				    	{field: 'ID', title: '党员名称', width:300, align: 'center'},
				    	{field: 'PID', title: '阅读时间', width:300, align: 'center'}
				    ]],
				    done : function(){
				       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
				    }
			  });
		  },
		  end:function(){
			  location.reload();
		  }
	  });
  }
  
  /**审批记录展示页面**/
  
  function his_audit(data){
	  
	  var hisread = layer.open({ 
		  type: 1, title:"审批记录",
		  resize : false,
		  maxmin: true,
		  btnAlign: 'c',
		  area: ['650px', '450px'],
		  skin: 'demo-class',
		  content: $('.hisaudit'),
		  success:function(){
		  },
		  end:function(){
			  location.reload();
		  }
	  });
  }
  
}).extend({zTree: '../ztree/jquery.ztree.core'});