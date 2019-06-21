layui.define(['layer', 'element', 'jquery', 'table',  'iconPicker', 'form', 'slider'], function(exports){  
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  table = layui.table,  iconPicker = layui.iconPicker,  form = layui.form; 
  
  var tableIns = table.render({
	  	id:'mainData',
	    elem: '#demo',
	    skin:'row',
	    even:true,
	    height: 670,
	    toolbar:'#toolbar',
	    url: '/api/v1/sys/menu/index/view',
	    page: true ,
	    cols: [[ 
	    	{checkbox: true,fixed: 'left'},
	    	{field: 'menuName', title: '菜单名称', width:150, align: 'center'},
	    	{field: 'menuUrl', title: '菜单链接', width:400, align: 'center'},
	    	{field: 'menuIcon', title: '菜单图标', width:150, align: 'center',
	    		templet: function(d){
	    			return "<i class='layui-icon "+d.menuIcon+"' ></i>"
	    		}
	    	},
	    	{field: 'creatorName', title: '创建人', width:150, align: 'center'},
	    	{field: 'createDate', title: '创建时间', width:180, align: 'center'}
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
  
  exports('base_menu', function(){ }); 
  
  function add(){
	  
	  var add = layer.open({ 
		  type: 1, title:"新增菜单",
		  resize : false,
		  maxmin: true,
		  btn: [ '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['650px', '480px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  
			  $("#add_form_submit").trigger("click");
		  },
		  success:function(){
			  
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/menu/list/view', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  if(value.pid !='0'){ return true; }
							  var option =new Option(value.menuName,value.id);
							  $('#add_form .pid').append(option);
							  form.render('select');
						  })
					  }
				  }
			  });
			  
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/enable_disable_mark', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.ITEM_NAME,value.ITEM_VALUE);
							  $('#add_form .is_disabled').append(option);
							  form.render('select');
						  })
					  }
				  }
			  });
			  
			  iconPicker.render({
			      elem: '#iconPicker_add',
			      type: 'fontClass',
			      search: false,
			      page: true,
			      limit: 20,
			  });
			  
		  },
		  end:function(){
			  location.reload();
		  }
	  });
	  
	  form.on('submit(addform)', function(data){
		  $.ajax({
			  type: 'POST',  url: '/api/v1/sys/menu/add_or_update', dataType : "json", data: data.field,
			  success: function(result) { 
				  if(result.data.status){
					  layer.msg('保存成功'); layer.close(add);  tableIns.reload({ page:{ curr: 1 }});
				  }else{
					  layer.msg('保存失败['+result.data.message+']');
				  }
			  }
		  });
		  return false;
	  });
  }
  
  function modify(data){
	  
	  form.val('modify_form', data[0]);
	  
	  var modify = layer.open({ 
		  type: 1, title:"修改菜单",
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
			  
			  if(data[0].pid==0){
//				  $("#modify_form div.layui-form-item:eq(3)").hide()
			  }else{
				  $.ajax({
					  type: 'POST',  url: '/api/v1/sys/menu/list/view', dataType : "json",
					  success: function(result) { 
						  if(result.data.length>0){
							  $.each(result.data,function(index,value){
								  if(value.pid !='0'){ return true; }
								  var option =new Option(value.menuName,value.id);
								  $('#modify_form .modify_pid').append(option);
								  if(data[0].pid==value.id){
									  $('#modify_form .modify_pid').val(value.id);
								  }
							  })
							  form.render('select');
						  }
					  }
				  });
			  }
			  
			  $.ajax({
				  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/enable_disable_mark', dataType : "json",
				  success: function(result) { 
					  if(result.data.length>0){
						  $.each(result.data,function(index,value){
							  var option =new Option(value.ITEM_NAME,value.ITEM_VALUE);
							  $('#modify_form .is_disabled').append(option);
							  if(data[0].isDisabled==value.ITEM_VALUE){
								  $('#modify_form .is_disabled').val(value.ITEM_VALUE);
							  }
						  })
						  form.render('select');
					  }
				  }
			  });
		  },
		  end:function(){
			  location.reload();
		  }
	  });
	  
	  iconPicker.render({
		  elem: '#iconPicker_modify',
		  type: 'fontClass',
		  search: false,
		  page: true,
		  limit: 12,
		  click: function (data) {
			   console.log(data.icon)
               iconPicker.checkIcon('iconPicker2', data.icon);
          }
	  });
	  
	  iconPicker.checkIcon('iconPicker2', data[0].menuIcon);
	  
	  form.on('submit(modifyform)', function(data){
		  
		  console.log(data.field)
		  
		  /*$.ajax({
			  type: 'POST',  url: '/api/v1/sys/menu/add_or_update', dataType : "json", data: data.field,
			  success: function(result) { 
				  if(result.data.status){
					  layer.msg('修改成功'); layer.close(modify);  tableIns.reload({ page:{ curr: 1 }});
				  }else{
					  layer.msg(result.data.message);
				  }
			  }
		  });*/
		  return false;
	  });
	  
  }
  
  function del(data){
	  
	  var ids = new Array(); 
	  
	  $.each(data,function(index,value){
		  
		  ids.push(value.id);
	  })
	  $.ajax({
		  type: 'POST',  url: '/api/v1/sys/menu/delete/'+ids.join(','), dataType : "json",
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



