layui.define(['layer', 'element', 'laydate', 'layedit', 'jquery', 'table', 'dtree', 'zTree', 'form'], function(exports){  
  
	
	var demoData  =  [{
		"id":"001",
		"title": "湖南省",
		"isLast": false,
		"level": "1",
		"parentId": "0",
		"checkArr": [{"type": "0", "isChecked": "0"}],  
                "basicData": {"data1": "自定义数据111", "data2": "自定义数据222", "data3": "自定义'我带了单引号'333"},
		"children":[{
			"id":"001001",
			"title": "长沙市",
			"isLast":true,
			"parentId": "001",
			"checkArr": [{"type": "0", "isChecked": "0"}],
			"level": "2"
		},{
			"id":"001002",
			"title": "株洲市",
			"isLast":true,
			"parentId": "001",
			"checkArr": [{"type": "0", "isChecked": "0"}],
			"level": "2"
		},{
			"id":"001003",
			"title": "湘潭市",
			"isLast":true,
			"parentId": "001",
			"checkArr": [{"type": "0", "isChecked": "0"}],
			"level": "2"
		},{
			"id":"001004",
			"title": "衡阳市",
			"isLast":true,
			"parentId": "001",
			"checkArr": [{"type": "0", "isChecked": "0"}],
			"level": "2"
		},{
			"id":"001005",
			"title": "郴州市",
			"isLast":true,
			"parentId": "001",
			"checkArr": [{"type": "0", "isChecked": "0"}],
			"level": "2"
		}]
	}]
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table,  form = layui.form, laydate = layui.laydate,  dtree = layui.dtree, layedit = layui.layedit, zTreeObj; 
  
  var tableIns = table.render({
	  	id:'mainData',
	    elem: '#main_table',
	    skin:'row',
	    even:true,
	    toolbar:'#toolbar',
	    url: '/api/v1/information/notice/index/view',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'orderNo', title: '序号', width:100, align: 'center'},
	    	{field: 'title', title: '标题', width:300, align: 'center'},
	    	{field: 'status', title: '状态', width:180, align: 'center', templet: '#statusTpl'},
	    	{field: 'createName', title: '发布人', width:180, align: 'center'},
	    	{field: 'createTime', title: '发布时间', width:180, align: 'center'},
	    	{fixed: 'right', title: '操作', width: 320, align:'center', toolbar: '#rowbar',}
//	    	{field: 'ID', title: '序号', width:300, align: 'center'},
//	    	{field: 'PID', title: '标题', width:300, align: 'center'},
//	    	{field: 'PARENT_ITEM_CODE', title: '状态', width:180, align: 'center'},
//	    	{field: 'ITEM_CODE', title: '发布人', width:180, align: 'center'},
//	    	{field: 'ITEM_NAME', title: '发布时间', width:150, align: 'center'},
//	    	{fixed: 'right', title: '操作', width: 320, align:'center', toolbar: '#rowbar'}
	    ]],
	    done : function(){
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	    }
  });
  
  /**加载组织机构树形列表**/
  
  dtree.render({
	  elem: "#org_tree",
	  skin: "layui",
	  data: demoData
//	  url: "../json/case/demoTree1.json"  //异步接口
  });
  
  /*var setting = {
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
  };*/
//  var setting = {};
  
  var zNodes = [
	   {name:"test1", open:true, children:[
	      {name:"test1_1"}, {name:"test1_2"}]},
	   {name:"test2", open:true, children:[
	      {name:"test2_1"}, {name:"test2_2"}]}
	   ];

//  zTreeObj = layui.zTree.init($("#org_tree"), setting, zNodes);
  
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
  
  
  exports('info_notice', function(){ }); 
  
  /**新增发布通知公告**/
  
  function add(){
	  
	  var index;
	  
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
			  
			  $("#add_form textarea[name='content']").val(layedit.getContent(index));
			  
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
			  
			  layedit.set({
				  uploadImage: {
					  url: '/api/v1/file/image/upload'
				  }
			  });
			  
			  index = layedit.build('content'); //初始化富文本编辑器
			  
			  $(window.frames["LAY_layedit_1"].document).find("body").append("<style>img{width:200px;height:200px;}</style>")
			  
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
			  type: 'POST',  url: '/api/v1/information/notice/add_or_update', dataType : "json", data: data.field,
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
  
  /**编辑展示页面**/
  
  function edit(data){
	  
	  form.val('modify_form', data);
	  
	  var edit = layer.open({ 
		  type: 1, title:"编辑页",
		  resize : false,
		  maxmin: true,
		  btnAlign: 'c',
		  area: ['850px', '650px'],
		  skin: 'demo-class',
		  content: $('#modify_form'),
		  success:function(){
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/read_mark', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.ITEM_NAME,value.ITEM_VALUE);
							  $('#modify_form .is_read').append(option);
							  form.render('select');
						  })
					  }
				  }
			  });
			  
			  layedit.set({
				  uploadImage: {
					  url: '/api/v1/file/image/upload'
				  }
			  });
			  
			  index = layedit.build('modifyContent'); //初始化富文本编辑器
			  
			  $(window.frames["LAY_layedit_1"].document).find("body").append("<style>img{width:200px;height:200px;}</style>")
			  
			  laydate.render({
				  	elem: '#modify_time',
				    type: 'datetime',
				    value: new Date()
			  });
		  },
		  end:function(){
			  location.reload();
		  }
	  });
  }
  
  /**详情展示页面**/
  
  function detail(data){
	  
	  var edit = layer.open({ 
		  type: 1, title:"详情页",
		  resize : false,
		  maxmin: true,
//		  btnAlign: 'c',
		  area: ['850px', '650px'],
		  skin: 'demo-class',
		  content: $('.detail'),
		  success:function(){
		  },
		  end:function(){
			  location.reload();
		  }
	  }); 
	  
  }
  
}).extend({
	zTree: '../ztree/jquery.ztree.core',
	dtree: '{/}/layui.extend/modules/layui_ext/dist/dtree'
});