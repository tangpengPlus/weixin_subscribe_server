[#assign base = request.contextPath /]
<!DOCTYPE html>
<html data-use-rem="750">
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="renderer" content="webkit">
		<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="format-detection" content="telphone=no, email=no">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="HandheldFriendly" content="true">
		<!--引入css样式-->
		<link rel="stylesheet" type="text/css" href="${base}/web/css/waterbar.css"/>
		<title>水吧服务</title>
		<!--引入jQuery-->
		<script type="text/javascript" src="${base}/web/js/jquery-3.2.1.min.js"></script>
	</head>
	<body>
	<!--适配-->
		<script>
	!function(win){var doc=win.document,html=doc.documentElement,option=html.getAttribute("data-use-rem");if(null!==option){var baseWidth="NaN"==parseInt(option).toString()?750:parseInt(option),grids=baseWidth/100,clientWidth=html.clientWidth||320;html.style.fontSize=clientWidth/grids+"px";var testDom=document.createElement("div"),testDomWidth=0,adjustRatio=0;testDom.style.cssText="height:0;width:1rem;",doc.body.appendChild(testDom);var calcTestDom=function(){if(testDomWidth=testDom.offsetWidth,testDomWidth!==Math.round(clientWidth/grids)){adjustRatio=clientWidth/grids/testDomWidth;var reCalcRem=clientWidth*adjustRatio/grids;html.style.fontSize=reCalcRem+"px"}else doc.body.removeChild(testDom)};setTimeout(calcTestDom,20);var reCalc=function(){var newCW=html.clientWidth;newCW!==clientWidth&&(clientWidth=newCW,html.style.fontSize=newCW*(adjustRatio?adjustRatio:1)/grids+"px")};if(reCalc(),doc.addEventListener){var resizeEvt="orientationchange"in win?"orientationchange":"resize";win.addEventListener(resizeEvt,reCalc,!1),doc.addEventListener("DOMContentLoaded",reCalc,!1)}}}(window);
</script>
    <!--内容-->
    <div class="wrap">
    	<!--顶图-->
    	<div class="topimg">
    		<img src="${base}/web/img/watertop.png"/>
    		<input type="text" name="" id="table_num" value="" placeholder="请输入您的桌牌号"/>
    	</div>
    	<!--物品-->
    	<div class="box">
    	[#if goodsTypeList ??]
    	
    		[#list goodsTypeList as type]
    			<div class="box1">
	    			  <div class="left">
	    			    ${type.name}
	    			  </div>
    						<div class="right">
    						  <ul>
    						  	[#if goodsList ??]
    						  		[#list goodsList as goods]
	    						  		[#if goods.typeId == type.id]
	    						  			<li>
								    				<a href="javascript:void(0);" class="goods" data="${goods.name}"><img src="${base}/web/img/checkbox1.png"/><span class="title">${goods.name}</span>
								    				</a>
								    				<div class="number">
								    					<span class="jian">-</span><span class="shuliang">0</span><span class="jia">+</span>
								    				</div>
								    		</li>
	    						  		[/#if]
    						  		[/#list]
    						  	[/#if]
    						  </ul>
    						
    						</div>
    			</div>
    		<div class="xian"></div>
    		[/#list]
    	[/#if]
    	    <div class="callbtn">一键呼叫</div>
    	</div>
    	<div class="kong"></div>
    	<a class="footer" href="http://www.magic-source.com/wap/index.html" >
    			<img src="${base}/web/img/backicon.png"/>Powered by 妙传索思		
    	</a>
    	<!--预约成功提示-->
    	<div class="blackscreen">
    		<div class="whitescreen">
    			<hr />
    			<img src="${base}/web/img/close.png" class="close"/>
    			<h1>您已点单成功</h1>
    			<p>我们将尽快为您奉上美味</p>
    			<p class="tel">请稍等</p>
    			<p class="xyd">......</p>
    			<hr class="buttonxian"/>
    		</div>
    	</div>
    </div>

<!--效果js-->
    <script type="text/javascript">
    $(function(){
    	//选择数量
    	var count=1;
    	//选择商品
    	$(".goods").click(function(){
    		if($(this).children("img").attr("src")=="${base}/web/img/checkbox1.png"){
    			$(this).children("img").attr("src","${base}/web/img/checkbox2.png");
    			$(this).addClass("active");
    			$(this).siblings(".number").show();
    			$(this).siblings(".number").children(".shuliang").html(1);
    			count=1;
    		}else{
    			$(this).children("img").attr("src","${base}/web/img/checkbox1.png");
    			$(this).removeClass("active");
    			$(this).siblings(".number").hide();
    			$(this).siblings(".number").children(".shuliang").html(0);
    			count=0;
    		}
    	})
    	
    	
    	//加
    	$(".jia").click(function(){
    		count++;
    		$(this).siblings(".shuliang").html(count);
    	});
    	//减
    	$(".jian").click(function(){
    		if(count>1){
    			count--;
    		}else{
    			count=1;
    		}
    		$(this).siblings(".shuliang").html(count);
    	});
    	//呼叫后提示
    	$(".callbtn").click(function(){
    	//获取选中商品的数据
    	var datas= "";
    		$('.box1').find('li').each(function(){
    		var aname = $(this).find('a');
    		var classname = aname.attr("class");
    		if(classname == "goods active"){
    			var  data= aname.attr("data");
    			var num=$(aname).siblings('div').find('.shuliang').html();
    			datas = datas+"&"+data+":"+num;
    		}
    	
    	});		
    	console.log("datas:"+datas);
    	
    	var table_number =$("#table_num").val();
    	if(table_number == null || table_number ==""){
    	 alert("请输入桌号");
    	 return;
    	}
    		
    	if(datas == ""){
    		alert("请选择商品");
    		return;
    	}
    	
    	$.ajax({
    			url:"${base}/web/call/goodspullcall",
				data:{"tableNumber":table_number,"datas":datas},
				dataType:"text",
				type:"post",
				success: function(data) {
					if(data == "success"){
						$(".blackscreen").show();
					}else{
						alert(data);
					}
				}
    	
    	});
    	
    	
    		
    	});
    	//关闭弹窗
    	$(".close").click(function(){
    		$(".blackscreen").hide();
    	});
    	 //返回上一页
          $('.footer img').on('click',function(e){
             e.preventDefault();
			 history.go(-1);
		 });
		 
		  //小标题高度
		 var rheight=$(".right")
		 for(var i=0;i<rheight.length;i++){
		 	var heightr=rheight.eq(i).height();
		 	rheight.eq(i).siblings(".left").height(heightr);
		 	rheight.eq(i).siblings(".left").css('line-height',heightr+"px");
		 }
    })
			
    </script>
	</body>
</html>
