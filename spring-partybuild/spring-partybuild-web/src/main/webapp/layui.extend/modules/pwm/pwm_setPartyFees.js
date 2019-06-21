layui.define(['layer', 'element', 'jquery', 'form'], function(exports){  
  
  var layer = layui.layer, element = layui.element, $ = layui.jquery,  form = layui.form; 
  
  $.ajax({
	  type: 'POST',  url: '/api/v1/sys/dict/get/item/list/pay_dues_set', dataType : "json",
	  success: function(result) { 
		  if(result.data.length>0){
			  var html = "";
			  $.each(result.data,function(index,value){
				  html = html + '<div class="layui-form-item" style="margin: 0px 40px; margin-bottom: 20px"><label class="layui-form-label" style="font-weight: bold;width: 200px">'+value.ITEM_NAME+'</label>';
				  html = html + '<div class="layui-input-inline" style=width:650px;"><input type="text" readonly="readonly" name="title" value="'+value.ITEM_VALUE+'" class="layui-input"></div></div>';
			  })
			  $('#view_form').append(html);
			  form.render();
		  }
	  }
  });
  
  exports('pwm_setPartyFees', function(){ }); 
})