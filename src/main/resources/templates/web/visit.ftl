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
		<link rel="stylesheet" type="text/css" href="${base}/web/css/visit.css"/>
		<title>到访登记</title>
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
    		<img src="${base}/web/img/topimg.png"/>
    	</div>
    	<img src="${base}/web/img/visition.png" class="title"/>
    	<div class="xian"></div>
    	<input class="telnumber" type="text" name="" id="" value=""  placeholder="请输入预约登记时的手机号码"/>
    	<p>温馨提示：请您到达售楼部后在进行到访登记</p>
    	<div class="xian"></div>
    	<div class="callbtn">确认</div>
    	<div class="kong"></div>
    		<a class="footer" href="http://www.magic-source.com/wap/index.html" >
    			<img src="${base}/web/img/backicon.png"/>Powered by 妙传索思		
    	</a>
    	<!--弹窗-->
    	<div class="blackscreen">
    		<div class="whitescreen">
    			<hr />
    			<img src="${base}/web/img/close.png" class="close"/>
    			<h1>您已登记成功</h1>
    			<p >置业顾问<span>李泽坤</span>为您服务</p>
    			<p class="tel"><img src="${base}/web/img/tel.png"/><span>12345678923</span></p>
    			<hr class="buttonxian"/>
    		</div>
    	</div>
    	
    </div>

<!--效果js-->
    <script type="text/javascript">
	$(function(){
		$(".callbtn").click(function(){
			$(".blackscreen").show();
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
	})
	
    </script>
	</body>
</html>
