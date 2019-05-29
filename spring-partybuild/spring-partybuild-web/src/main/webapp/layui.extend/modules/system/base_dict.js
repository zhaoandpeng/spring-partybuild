layui.define(['layer', 'element', 'jquery', 'table',  'form'], function(exports){  
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table,  form = layui.form; 
  
  var tableIns = table.render({
	  	id:'mainData',
	    elem: '#demo',
	    skin:'row',
	    even:true,
	    toolbar:'#toolbar',
	    url: '/api/v1/sys/dict/index/view',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'ID', title: '主键', width:300, align: 'center',hide:true},
	    	{field: 'PID', title: '父级', width:300, align: 'center',hide:true},
	    	{field: 'PARENT_ITEM_CODE', title: '父级选项码', width:180, align: 'center'},
	    	{field: 'ITEM_CODE', title: '选项码', width:180, align: 'center'},
	    	{field: 'ITEM_NAME', title: '选项名称', width:150, align: 'center'},
	    	{field: 'ITEM_VALUE', title: '选项值', width:150, align: 'center'},
	    	{field: 'STATUS', title: '选项状态', width:150, align: 'center'},
	    	{field: 'REMARK', title: '描述', width:250, align: 'center',hide:true}
	    ]],
	    done : function(){
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	    }
  });
  
  table.on('toolbar(operate)', function(obj){
	  
	  	var checkStatus = table.checkStatus(obj.config.id) ,data = checkStatus.data; 
	  	switch(obj.event){
	  			case 'add': add(); break;
	  			case 'modify': if(data.length === 0){ layer.msg('请选择一项数据'); } else if(data.length > 1){ layer.msg('只允许单条编辑')}else{modify(data)} break;
	  			case 'delete': if(data.length === 0){ layer.msg('请选择一行'); } else { del(data) } break;
	  	};
  });
  
  
  exports('base_dict', function(){ }); 
  
  
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
  
  function add(){
	  
	  var add = layer.open({ 
		  type: 1, title:"新增字典",
		  resize : false,
		  maxmin: true,
		  btn: [ '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['650px', '450px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  $("#add_form_submit").trigger("click");
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
						  $('#add_form .status').append(html);
						  form.render('radio');
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
  
}).extend({iconPicker: '../iconPicker/iconPicker'});



