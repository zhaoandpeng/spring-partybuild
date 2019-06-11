layui.define(['layer', 'element', 'jquery', 'table', 'form'], function(exports){  
  
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table,  form = layui.form; 
  
  var tableIns = table.render({
	  	id:'mainData',
	    elem: '#main_table',
	    skin:'row',
	    even:true,
	    toolbar:'#toolbar',
	    url: '/api/v1/spv/report/index/view',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'orderNo', title: '序号', width:100, align: 'center'},
	    	{field: 'title', title: '标题', width:300, align: 'center'},
	    	{field: 'createTime', title: '添加时间', width:180, align: 'center'},
	    	{field: 'updateTime', title: '更新时间', width:180, align: 'center'},
	    	{fixed: 'right', title: '操作', width: 220, align:'center', toolbar: '#rowbar',}
	    ]],
	    done : function(){
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	    }
  });
  
  
  table.on('toolbar(operation)', function(obj){
	  
	  	var checkStatus = table.checkStatus(obj.config.id) ,data = checkStatus.data; 
	  	switch(obj.event){
	  			case 'add': add(); break;
//	  			case 'del': del(data); break;
	  	};
  });
  
  table.on('tool(operation)', function(obj){
	    var data = obj.data, layEvent = obj.event; //获得 lay-event 对应的值
	    switch(obj.event){
//			case 'rev_audit': rev_audit(data); break;
			case 'del': del(data); break;
			case 'edit': edit(data); break;
	    };
  });
  
  
  exports('spv_report', function(){ }); 
  
  /**新增**/
  
  function add(){
	  
	  var add = layer.open({ 
		  type: 1, title:"新增",
		  resize : false,
		  maxmin: true,
		  btn: [ '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['850px', '750px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  $("#add_form_submit").trigger("click");
		  },
		  success:function(){
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/report_type', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.ITEM_NAME,value.ITEM_VALUE);
							  $('#add_form .add_report_type').append(option);
							  form.render('select');
						  })
					  }
				  }
			  });
		  },
		  end:function(){
			  location.reload();
		  }
	  });
	  
	  form.on('submit(addform)', function(data){
		  $.ajax({
			  type: 'POST',  url: '/api/v1/spv/report/add_or_update', dataType : "json", data: data.field,
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
	  
	  $.ajax({
		  type: 'POST',  url: '/api/v1/spv/report/del/'+data.id, dataType : "json",
		  success: function(result) { 
			  if(result.data.status){
				  layer.msg('删除成功');  tableIns.reload({ page:{ curr: 1 }});
			  }else{
				  layer.msg(result.data.message);
			  }
		  }
	  });
  }
  
  /**取消审核**/
  
  /*function rev_audit(data){
	  
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
  }*/
  
  /**编辑展示页面**/
  
  function edit(data){
	  
	  form.val('modify_form', data);
	  
	  var edit = layer.open({ 
		  type: 1, title:"编辑页",
		  resize : false,
		  maxmin: true,
		  btnAlign: 'c',
		  btn: [ '保存', '取消', ],
		  area: ['850px', '750px'],
		  skin: 'demo-class',
		  content: $('#modify_form'),
		  yes:function(index,layero){
			  $("#modify_form_submit").trigger("click");
		  },
		  success:function(){
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/report_type', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.ITEM_NAME,value.ITEM_VALUE);
							  $('#modify_form .modify_report_type').append(option);
							  if(data.reportType==value.ITEM_VALUE){
								  $(".modify_report_type").val(value.ITEM_VALUE);
							  }
							  form.render('select');
						  })
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
			  type: 'POST',  url: '/api/v1/spv/report/add_or_update', dataType : "json", data: data.field,
			  success: function(result) { 
				  if(result.data.status){
					  layer.msg('保存成功'); layer.close(edit);  tableIns.reload({ page:{ curr: 1 }});
				  }else{
					  layer.msg('保存失败 [ '+result.data.message+']');
				  }
			  }
		  });
		  return false;
	  });
  }
})