[#assign base = request.contextPath /]
<!DOCTYPE HTML>
<html>
<head>
<title>登录</title>
<!-- Custom Theme files -->
<link href="${base}/css/login/style.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<link rel="shortcut icon" href="${base}/img/favicon.png">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
<meta name="keywords" content="后台登录" />
<!--Google Fonts-->
<!--<link href='//fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>
--><!--Google Fonts-->
</head>
<style>
:-moz-placeholder { /* Mozilla Firefox 4 to 18 */
    color: #999; opacity:1; 
}
::-moz-placeholder { /* Mozilla Firefox 19+ */
    color: #999;opacity:1;
}
input:-ms-input-placeholder{
    color: #999;opacity:1;
}
input::-webkit-input-placeholder{
    color: #999;opacity:1;
}
	.loginCodeInp{
		width:60%;
	}
	.loginCodeImg{
		display:block;
		width:22%;
		height:100%;
		position: absolute;
    	top: 0;
    	right: 0;
    	bottom: 0;
	}
	.loginCodeImg img{
		width:100%;
		height:100%;
		display:block;
	}
</style>
<body>
<!--header start here-->
<div class="login-form">
			<div class="top-login">
				<span><img src="${base}/img/login/group.png" alt=""/></span>
			</div>
			<h1>登录</h1>
			<div class="login-top" >

				<div class="login-ic">
					<i ></i>
					<input type="text" id="user"   placeholder="用户名" />
					<div class="clear"> </div>
				</div>
				<div class="login-ic">
					<i class="icon"></i>
					<input type="password"  id="pwd"  placeholder="密码"/>
					<div class="clear"> </div>
				</div>
				
				<div class="login-ic" style="position: relative;">
					<i class="icon"></i>
					<input class="loginCodeInp" id="input1" type="text" placeholder="验证码" style="width:60%;"/>
					<div class="loginCodeImg">
						<img src="${base}/system/admin/verificationcode"  onclick='this.src="${base}/system/admin/verificationcode?r="+Math.random()'/>
					</div>
					<div class="clear"> </div>
				</div>
			
				<div class="log-bwn">
					<input type="button"  value="登录" >
				</div>

			</div>
			<p class="copy">© 2018-${.now?string("yyyy")} 重庆中海天钻微信预约服务系统。</p>
</div>		

<!-- rsa加密-->
  <script type="text/javascript" src="${base}/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="${base}/js/rsa/jsbn.min.js"></script>
	<script type="text/javascript" src="${base}/js/rsa/prng4.min.js"></script>
	<script type="text/javascript" src="${base}/js/rsa/rng.min.js"></script>
	<script type="text/javascript" src="${base}/js/rsa/rsa.min.js"></script>
	<script type="text/javascript" src="${base}/js/rsa/base64.min.js"></script>
	<script type="text/javascript" src="${base}/css/layer/layer.js"></script>
	 
    <script>
    
    $(function(){
	     var message='${loginPageInfo}';
	     if(message!=null&&message!=''){
	  			var index_01= layer.alert(message,{title:'系统提示',icon:2,closeBtn:0}, function(){
	     			layer.close(index_01);
				});       
	     }
    	    //加密字符
		    var modulus='${modulus}';
		    var exponent='${exponent}';
		    function validate () {
		        //前端验证
		       
		    }
		   //登录异步提交
		   function loginFormSumbit(){
		    var userName=$("#user").val();
		    var password=$("#pwd").val();
		    var captcha=$("#input1").val();
		    if(userName == ''){
		    	layer.msg('用户名不能为空！',{time:3000});
		    	return false;
		    }
		    if(password == ''){
		    	layer.msg('密码不能为空！',{time:3000});
		    	return false;
		    }
		    if(captcha == ''){
		    	layer.msg('验证码不能为空！',{time:3000});
		    	return false;
		    }
		    
		    //加密处理
		    var rsaKey = new RSAKey();
			rsaKey.setPublic(b64tohex(modulus), b64tohex(exponent));
		   $.ajax({
		         url:'${base}/system/admin/login',
		         data:{
		          username:hex2b64(rsaKey.encrypt(userName)),
				  password:hex2b64(rsaKey.encrypt(password)),
				  captcha:captcha
		         },
		         type:"post",
				 dataType:"json",
				 success:function(message){
					if(message.type=='error'){
					   layer.msg(message.cont,{time:3000});
				        modulus=message.modulus;
				        exponent=message.exponent;
			        }else{
			        	 window.location.href='${base}'+message.successUrl;
			        }
				 },
				 	error: function() {
			    }
		       });
		   
		   }
		   //提交
		   $('.log-bwn input').click(function(){
			   
			   if(!loginFormSumbit()){
				   return false;
			   }
		   });
		   //监听回车事件
		   document.onkeydown = function(e){ 
			   	var theEvent = e || window.event;    
	        	var code = theEvent.keyCode || theEvent.which || theEvent.charCode; 
				if(code == 13) {
				   $('.log-bwn input').click();
			   }  
		   }
	   });
    </script>
</body>
</html>