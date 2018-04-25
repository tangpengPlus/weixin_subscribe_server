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
		<link rel="stylesheet" type="text/css" href="${base}/web/css/index.css"/>
		<title>中海天钻</title>
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
    		<img src="${base}/web/img/index1.png"/>
    		<p><img src="${base}/web/img/biaoti.png" class="biaoti"/></p>
    	</div>
    	<!--导航-->
    	<div class="nav">
    		<ul>
    			<li>
    				<a href="${base}/web/order/show">
    					<div class="box">
    				<div class="left">
    					<p>TO MAKE</p>
    					<p>APPOINTMENT TO SEE</p>
    					<p class="zhong"><img src="${base}/web/img/index_z1.png"/><span></span></p>
    				</div>
    				<div class="right">
    					<img src="${base}/web/img/index_h.png"/>
    				</div>
    				</div>
    			    </a>
    			</li>
    			<li class="xian_right"></li>
    			<li>
	    			<a href="${base}/web/order/visit">
	    				<div class="box">
	    				<div class="left">
    					<p>VISIT</p>
    					<p>THE REGISTRATION</p>
    					<p class="zhong"><img src="${base}/web/img/index_z2.png"/><span></span></p>
    				</div>
    				<div class="right">
    					<img src="${base}/web/img/index_p.png"/>
    				</div>
    				</div>
	    			</a>
    			</li>
    			<li class="xian_left"></li>
    			<li>
    				<a href="${base}/web/call/goodscall">
    					<div class="box">
    					<div class="left">
    					<p>WATER</p>
    					<p>SERVICE</p>
    					<p class="zhong"><img src="${base}/web/img/index_z3.png"/><span></span></p>
    				</div>
    				<div class="right">
    					<img src="${base}/web/img/index_c.png"/>
    				</div>
    				</div>
    				</a>
    			</li>
    			<li class="xian_right"></li>
    			<li>
    				<a href="${base}/web/call/nomocall">
    					<div class="box">
    					<div class="left">
    					<p>CALL</p>
    					<p>SERVICE</p>
    					<p class="zhong"><img src="${base}/web/img/index_z4.png"/><span></span></p>
    				</div>
    				<div class="right">
    					<img src="${base}/web/img/index_s.png"/>
    				</div>
    				
    				</div>
    				</a>
    			</li>
    		</ul>
    		<a class="footer" href="http://www.magic-source.com/wap/index.html" >
    			Powered by 妙传索思		
    	</a>
    	</div>
    	
    </div>

<!--效果js-->
    <script type="text/javascript">

			$(function(){
	             var biaoheight= $(".topimg p").height();
	    		 var outheight=$(".nav").offset().top;
	            $(".topimg p").css("top",(outheight-biaoheight)/2);
				//导航延时出现
				setTimeout(function(){
            $('.xian_right').css({'right':'0px'});
         },500);
         setTimeout(function(){
            $('.xian_left').css({'left':'0px'});
         },500);
        
       })
    </script>
	</body>
</html>
