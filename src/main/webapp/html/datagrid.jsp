<%@ page language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="../easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../html/css/style.css"/>
	<script type="text/javascript" src="../easyui/jquery.min.js"></script>
	<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../easyui/locale/easyui-lang-zh_CN.js"></script>
	
	<style type="text/css">
		li:hover {
			color:#C1C1C1;
		}
	</style>
</head>
<body>
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a></li>
			<li><a href="#">easyui</a></li>
		</ul>
	</div>
	
	<div class="rightinfo">
		<div class="tools">
			<ul class="toolbar">
				<li class="click" style=""><span><img src="images/t01.png" /></span>添加</li>
				<li class="click"><span><img src="images/t02.png" /></span>修改</li>
				<li><span><img src="images/t03.png" /></span>删除</li>
				<li><span><img src="images/t04.png" /></span>统计</li>
			</ul>
			<ul class="toolbar1">
				<li><span><img src="images/t05.png" /></span>设置</li>
			</ul>
		</div>

		<div style="padding:8px 0;">
			<a id="add" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">Add</a>
			<a id="remove" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">Remove</a>
			<a id="" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">Save</a>
			<a id="" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cut'">Cut</a>
			<a href="#" class="easyui-linkbutton">Text Button</a>
		</div>
		
		<table id="dg" class="easyui-datagrid"></table>
		
		<!-- 模板自定义的弹框 -->
		<div class="tip">
			<div class="tiptop">
				<span>提示信息</span><a></a>
			</div>
			<div class="tipinfo">
				<span><img src="images/ticon.png" /></span>
				<div class="tipright">
					<p>是否确认对信息的修改 ？</p>
					<cite>如果是请点击确定按钮 ，否则请点取消。</cite>
				</div>
			</div>

			<div class="tipbtn">
				<input name="" type="button" class="sure" value="确定" />
				<input name="" type="button" class="cancel" value="取消" />
			</div>
		</div>
		
		<!-- 模仿 easyui 写的遮罩层 -->
		<div class="window-mask" style="display: none; z-index: 9006; position: fixed;"></div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		$("#dg").datagrid({
			url:"/phone/get",
			columns:[drawColumns()],
			fitColumns:true,		//默认是：关闭自适应，这里改为自适应（width:100 就是 100% 的意思）
			pagination:true,		//默认是：不显示分页，这里添加分页
			rownumbers:true,		//默认是：不显示行号，这里改为显示行号
			singleSelect:true,		//默认是：可选中多行，这里改为只能选中一行
			checkOnSelect:false,	//默认是：点击行自动勾选，这里将其关闭
			selectOnCheck:false,	//默认是：选中就不能取消，这里改为单击选中或者取消
			pageNumber:1,
			pageSize:15,
			pageList:[10,15,20,30,40],
			scrollbarSize:8,		//最右侧滚动条
			striped:true
		});
			
		function drawColumns(){
			var columns = [
		        {field:'ck',checkbox:true},
		        {field:'id',title:'ID',width:100},
		        {field:'name',title:'名称',width:100},
		        {field:'amount',title:'数量',width:100},
		        {field:'version',title:'版本号',width:100},
		        {field:'operation',title:'操作',width:60	,align:'center',formatter:aa}
			];
			return columns;
		}
		
		function aa(){
			return "<a href='javascript:;' onclick=\"cc();return false;\">修改</a>&nbsp;&nbsp;&nbsp"+
				   "<a href='javascript:;' onclick=\"cc();return false;\">删除</a>";
		}

		$("#add").click(function(){
			$.messager.alert('我的消息','easyui自带alert','info');
		});
			
	});
		$(".click, .opt_update, .opt_delete").click(function() {
			$(".tip").fadeIn(100);
			show();
		});

		$(".tiptop a, .sure, .cancel").click(function() {
			$(".tip").fadeOut(100);
			hide();
		});
		function show(){
			$(".window-mask").show();
		}
		function hide(){
			$(".window-mask").hide();
		}
		function cc(){
			$(".tip").fadeIn(100);
			show();
		}		
</script>
</html>