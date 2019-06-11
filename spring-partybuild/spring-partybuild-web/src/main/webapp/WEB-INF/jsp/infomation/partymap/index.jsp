<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<title>党建地图</title>
<link rel="stylesheet" type="text/css" href="../../../../layui/css/layui.css">
<link rel="stylesheet" type="text/css" href="../../../../css/main.css">
<link rel="stylesheet" href="../../../../css/tabris.css">
<link rel="stylesheet" href="../../../../css/lib.css">
<script type="text/javascript" src="../../../../layui/layui.js"></script>
<script type="text/javascript" src="//api.map.baidu.com/api?v=2.0&ak=EHbGZtlDeTgL5OHZwIn73ZfcHGG46G2v"></script><!-- &type=lite&v=1.0 -->
</head>
<body class="layui-layout-body" >

	<div class="layui-fluid" style="height: 100%">
		<div class="layui-row" style="height: 8%">
			<div class="layui-col-md12">你的内容 12/12</div>
		</div>
		<div class="layui-row" style="height: 90%; border:1px solid #FFFFFF">
			<div class="layui-col-md12">
					<div class="layui-col-md8" id="map" style="height:692px; border:1px solid #FFFFFF">
				<!-- 		因工作需要，经研究，苏州市中级人民法院将面向社会公开招聘公益性岗位。现将有关事项公布如下：

一、招聘岗位及人数

公益性岗位16名（详见岗位简介表）

二、报名条件：

1.拥护中华人民共和国宪法,具有良好的政治素质、职业道德和敬业奉献精神，无违法违纪等不良记录。

2.户籍不限，1989年1月1日以后出生，无影响正常履行职责的疾病。

3.具有大专及以上学历，专业不限。

4.能熟练应用office等办公软件。

二、招聘程序

㈠报名

报名时间：2019年 5 月 17 日 8 时至2019年 6 月 10 日 8 时。

报名方式：登陆网上报名系统（报名网址：http://121.43.192.253:8053/），按照报名系统要求填写个人信息和提交电子图片，持有计算机速录技能等级证书的，应一并提交。

㈡笔试及技能测试

考试时间：2019年6月下旬至7月中旬进行。

考试地点：江苏省南京市（具体地址另行通知）

考试具体安排详见江苏法院网（www.jsfy.gov.cn）。

笔试主要考察应聘人员的法律常识和文字表达能力，不指定参考用书；考试采用闭卷方式，实行百分制，成绩达60分及以上者为合格。

技能考试为计算机文字速录考试，速录方式不限（需要专业速录工具的请自备），速录考试的形式为听打，播放二段录音，每段时长均为10分钟，语速分别为120字/分钟、150字/分钟，根据记录速度和准确率评分。

笔试和技能测试结束后，根据考试成绩确定参加面试人员按成绩从高到低顺序，以2∶1的比例从考生中确定面试人选。

㈢面试

面试前进行资格复审，进入面试人员应提供下列材料：身份证、学历学位证书（应届生提供双向就业推荐表）、户籍证明、近期同底免冠一寸照片2张（相关材料请同时提供复印件1份）。资格复审不合格的，取消面试资格。

面试具体时间及地点另定。

面试实行百分制，成绩达60分及以上者为合格。

㈣体检、政审、聘用 -->
					
					</div>
				<!-- <div class="layui-row" style="height: 90%">
					<div class="layui-col-md10">你的内容 12/12</div>
				</div> -->
			</div>
			<div class="hx-box pa">
				<ul class="pr">
					<li class="hx-k1 pa0"><span></span></li>
					<li class="hx-k2 pa0"><span></span></li>
					<li class="hx-k3 pa0"><span></span></li>
				</ul>
			</div>
		</div>
	</div>

	<script type="text/javascript">
	
	layui.config({base:'/layui.extend/modules/information/'}).use('info_partymap', function(){
	
		var info_partymap = layui.info_partymap; info_partymap();
		var map = new BMap.Map('map');// 创建地图实例
	  	var point = new BMap.Point(110.097,34.572);// 创建点坐标
	  	map.centerAndZoom(point, 11);// 初始化地图， 设置中心点坐标和地图级别
	  	
	  	map.enableScrollWheelZoom(true);
	  	
	  	var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	  	map.addControl(top_left_control);
	  	
	  	var top_left_navigation = new BMap.NavigationControl();
	  	map.addControl(top_left_navigation);
	  	
	  	var marker = new BMap.Marker(point);
	  	map.addOverlay(marker);
	  	//var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});// 左上角，添加比例尺
	  	//var top_left_navigation = new BMap.NavigationControl();  //左上角，添加默认缩放平移控件
	  	//var top_right_navigation = new BMap.NavigationControl({anchor: BMAP_ANCHOR_TOP_RIGHT, type: BMAP_NAVIGATION_CONTROL_SMALL}); //右上角，仅包含平移和缩放按钮 
	  	//map.addControl(top_left_control);        
	 	//map.addControl(top_left_navigation);     
	  	//map.addControl(top_right_navigation); 
	})
	
	</script>

</body>
</html>