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
		<link rel="stylesheet" type="text/css" href="${base}/web/css/order.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/web/css/public.css"/>
		<link rel="stylesheet" type="text/css" href="${base}/web/css/mobileSelect.css"/>
		<title>看房预约</title>
		<!--引入jQuery-->
		<script type="text/javascript" src="${base}/web/js/jquery-3.2.1.min.js"></script>
		<script type="text/javascript" src="${base}/web/js/mobileSelect.js"></script>
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
    	 <img class="title" src="${base}/web/img/order.png"/>
    	    <hr>
    	<div class="contain">
    		<!--信息提示-->
    		<div class="hint"></div>
    	<div class="fixWidth">
    	<!--表单-->
    	<form id="myform" action="#" method="post" >
	    	<div class="demo">
	            <input id="trigger1" type="text" name=""  value="" placeholder="姓名" />
	            <input id="trigger2" type="text" name=""  value="" placeholder="请填写车牌号码" />
	            <!--<input id="trigger3" type="text" name=""  value="" placeholder="随行人数" />
	            <input id="trigger4" type="text" name=""  value="" placeholder="预计到访日期" />-->
	            <div id="trigger3">随行人数 </div>
	            <div id="trigger4">预计到访日期</div>
	            <input id="trigger5" type="text" name="" value="" placeholder="手机号输入"/>
	            <input id="trigger6" type="text" name=""   placeholder="输入验证码"/>
	            <div id="trigger7">获取验证码</div>
	        </div>
	         <hr>
	        <div class="subm">确认</div>
    	</form>
    	</div>
    	
    	</div>
    	<div class="kong"></div>
    	<!--页脚-->
    	<a class="footer" href="http://www.magic-source.com/wap/index.html" >
    			<img src="${base}/web/img/backicon.png"/>Powered by 妙传索思		
    	</a>
    	<!--预约成功提示-->
    	<div class="blackscreen">
    		<div class="whitescreen">
    			<hr />
    			<img src="${base}/web/img/close.png" class="close"/>
    			<h1>您已预约成功</h1>
    			<p>置业顾问<span>李泽坤</span>为您服务</p>
    			<p class="tel"><img src="${base}/web/img/tel.png"/>12345678923</p>
    			<hr class="buttonxian"/>
    		</div>
    	</div>
    </div>
   
<!--效果js-->
    <script type="text/javascript">
    //数据
    //随行人数
    var numb=["1","2","3","4","5","6","7","8","9"];
    //年月日
    var yearz=["年"];
    var monthz=["月"];
    var dayz=["日"];
    var year=["2020","2019","2018","2017","2016","2015","2014"];
    var month=["1","2","3","4","5","6","7","8","9","10","11","12"];
    var day=["1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"];
    var halfDay=["上午","下午"];
	 
//选择随行人数
    var mobileSelect1 = new MobileSelect({
    trigger: '#trigger3',
//  title: '日期',
    wheels: [
                {data: numb}
            ],
    position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0
    transitionEnd:function(indexArr, data){
        //console.log(data);
    },
    callback:function(indexArr, data){
        console.log(data);
    }
    });
    //选择日历
     var mobileSelect1 = new MobileSelect({
    trigger: '#trigger4',
//  title: '日期',
    wheels: [
                {data: year},
                {data: yearz},
                {data: month},
                {data: monthz},
                {data: day},
                {data: dayz},
                {data: halfDay}
            ],
    position:[2], //初始化定位 打开时默认选中的哪个 如果不填默认为0
    transitionEnd:function(indexArr, data){
        //console.log(data);
    },
    callback:function(indexArr, data){
        console.log(data);
    }
    });
    </script>
    <!--验证-->
    <script type="text/javascript">
    	$(function(){
    		var myreg=/^[1][3,4,5,7,8,9][0-9]{9}$/; 
    		//获取验证码
    		$("#trigger7").click(function(){
    			var yanzvalue=$("#trigger5").val();
    			if(yanzvalue!=""&&yanzvalue!=" "){
	    			if(myreg.test(yanzvalue)){
	    				$(".hint").html("验证码已发送!");
	    				$(".hint").show();
		    		    $(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
		    				setTimeout(function(){
		    		 	$(".hint").hide();
    		 },1000);
	    			}else{
	    				$(".hint").html("请输入正确的号码！");
	    				$(".hint").show();
	    				$(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
	    				setTimeout(function(){
    		 	           $(".hint").hide();
    		 },1000);
	    			}
	    			$.ajax({
    							url:"${base}/web/order/sendValide",
    							data:{"phone":yanzvalue},
    							dataType:"text",
    							type:"post",
    							success:function(data){
    							  if(data == "success"){
    							  
    							  }else{
    							  	alert(data);
    							  }
    							}
    					
    					
    					
    					});
    			
	    			
	    			
    			}else{
    				$(".hint").html("请输入号码！");
    				$(".hint").show();
    				$(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
    				setTimeout(function(){
    		 	$(".hint").hide();
    		 },1000);
    			}
    		});
    		//弹窗
    		$(".blackscreen .close").click(function(){
    			$(".blackscreen").hide();
    		})
    		//提交
    		$(".subm").click(function(){
    			var xianm=$("#trigger1").val();
    			var xianm1=$("#trigger2").val();
    			var xianm4=$("#trigger5").val();
    			var xianm5=$("#trigger6").val();
    			if(xianm!=""&&xianm!=" "){
    				if(xianm1!=""&&xianm!=" "){
    						if(xianm4!=""&&xianm4!=" "){
    					if(myreg.test(xianm4)){
	    				if(xianm5!=""&&xianm5!=" "){
    						//提交是否跳转
    						$("#myform").submit(false);
    						$(".blackscreen").show();
    					}else{
    				$(".hint").show();
    				$(".hint").html("请填写验证码");
    				$(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
    				setTimeout(function(){
    		 	$(".hint").hide();
    		 },1000);
    			}
	    			}else{
	    				$(".hint").html("请输入正确的号码！");
	    				$(".hint").show();
	    				$(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
	    				setTimeout(function(){
    		 	           $(".hint").hide();
    		 },1000);
	    			}
    					
    				}else{
    				$(".hint").show();
    				$(".hint").html("请填写手机号码");
    				$(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
    				setTimeout(function(){
    		 	$(".hint").hide();
    		 },1000);
    			}
    				}else{
    					$(".hint").show();
    				$(".hint").html("请填写车牌号");
    				$(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
    				setTimeout(function(){
    		 	$(".hint").hide();
    		 },1000);
    				}
    			
    			}else{
    				$(".hint").show();
    				$(".hint").html("请填写姓名");
    				$(".hint").css("margin-left","-"+$(".hint").width()/2+"px");
    				setTimeout(function(){
    		 	$(".hint").hide();
    		 },1000);
    			}
    			
    			$.ajax({
    							url:"${base}/web/order/save",
    							data:{"phone":yanzvalue},
    							dataType:"text",
    							type:"post",
    							success:function(data){
    							  if(data == "success"){
    							  
    							  }else{
    							  	alert(data);
    							  }
    							}
    					
    					
    					
    					});
    			
    		});
    		 //车牌号
    		 $("#trigger2").change(function(){
    		 	$("#trigger2").val($("#trigger2").val().toUpperCase());
    		 })
    		 //返回上一页
          $('.footer img').on('click',function(e){
             e.preventDefault();
			 history.go(-1);
		 });
    		
    		
    	})
    </script>
	</body>
</html>
