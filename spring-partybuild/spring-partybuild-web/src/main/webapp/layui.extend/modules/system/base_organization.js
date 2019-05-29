layui.define(['layer', 'element', 'form', 'treeGrid', 'jquery', 'table'], function(exports){  
  
	
  var layer = layui.layer, element = layui.element, $ = layui.jquery, treeGrid = layui.treeGrid, form = layui.form;
  
  var ptable=treeGrid.render({
      id:'org_charts'
      ,elem: '#org_charts'
      ,idField:'id'
      ,url:'/api/v1/sys/organization/index/view'
      ,cellMinWidth: 100
      ,treeId:'id'
      ,treeUpId:'pid'
      ,treeShowName:'orgName'
      ,cols: [[
          {width:150,title: '操作', align:'center' ,templet: function(d){
	              var html='';
	              var addBtn='<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="add">添加</a>';
	              var delBtn='<a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>';
	              return addBtn+delBtn;
          		}
          }
          ,{field:'orgName', align:'center', width:300, title: '组织名称'}
          ,{field:'address', align:'center', width:300, title: '地址'}
      ]]
      ,page:false
      ,done : function(){
	       $('th').css({'background-color': '#009688', 'color': '#fff','font-weight':'bold'})
	  }
  });

  treeGrid.on('tool(operate)',function (obj) {
	  var data = obj.data
      if(obj.event === 'del'){//删除行
    	  del(data);
      }else if(obj.event==="add"){//添加行
    	  add(data);
      }
  });
 
  exports('base_organization', function(){ }); 
  
  /**新增组织机构**/
  
  function add(data){
	  
	  var add = layer.open({ 
		  type: 1, title:"新增组织机构",
		  resize : false,
		  maxmin: true,
		  btn: [ '保存', '取消', ],
		  btnAlign: 'c',
		  area: ['450px', '350px'],
		  skin: 'demo-class',
		  content: $('#add_form'),
		  yes:function(index,layero){
			  $("#add_form_submit").trigger("click");
		  },
		  success:function(){
			  $(".pid").val(data.id);
		  },
		  end:function(){
			  location.reload();
		  }
	  });
	  
	  form.on('submit(addform)', function(data){
		  alert("RRR")
		  $.ajax({
			  type: 'POST',  url: '/api/v1/sys/organization/add', dataType : "json", data: data.field,
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
  
  /**删除组织机构**/
  
  function del(data){
	  
	  alert(data.id)
	  
	  $.ajax({
		  type: 'POST',  url: '/api/v1/sys/organization/del', dataType: "json", data: {'id':data.id, 'pid':data.pid},
		  success: function(result) { 
			  if(result.data.status){
				  layer.msg('删除成功');  location.reload();
			  }else{
				  layer.msg(result.data.message);
			  }
		  }
	  });
  }
  
  
}).extend({treeGrid:'../layui_ext/treeGrid/treeGrid'});



