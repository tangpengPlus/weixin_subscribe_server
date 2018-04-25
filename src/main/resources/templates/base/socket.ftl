    [#--socket封装--]
<script type="text/javascript" src="${base}/js/socket/sockjs.min.js"></script>
<script type="text/javascript" src="${base}/js/socket/stomp.min.js"></script>
<script type="text/javascript" src="${base}/layer/layer.js"></script>
<script type="text/javascript">
$(document).ready(function(){  
     connect();
});  
  var stompClient = null;
  function connect() {
    var socket = new SockJS('/endpointAric');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
      console.log('Connected:' + frame);
      stompClient.subscribe('/topic/send', function (response) {
      var message = JSON.parse(response.body).message;
      var type = JSON.parse(response.body).type;
      var data = JSON.parse(response.body).goods;
      if(type == 1){
      	layer.open({
			  type: 1 //Page层类型
			  ,area: ['600px', '450px']
			  ,title: '呼叫服务台'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: '<div style="padding:130px; "><font size="5" color="red">'+message+'</font></div><audio src ="${base}/layer/tis.mp3" autoplay="true"></audio>'
			});  
      
      }else if(type ==2){
       var html='<div style="padding-left:250px; "><font size="5" color="red">'+message+'号桌菜单</font></div><audio src ="${base}/layer/tis.mp3" autoplay="true"></audio></br>';
       var table1 ='<table class="table table-bordered table-striped table-condensed cf"><thead class="cf"><tr><th>商品名称</th><th class="numeric">数量</th></tr></thead><tbody>';
      var table2 ="";
         for(var i=0;i<data.length;i++){
         table2 = table2+ "<tr><td>"+data[i].name+"</td><td>"+data[i].num+"</td></tr>";
			}
	  var table3= " </tbody></table>";
	  
	  var html = html+table1+table2+table3;
      		layer.open({
			  type: 1 //Page层类型
			  ,area: ['600px', '400px']
			  ,title: '点单提醒'
			  ,shade: 0.6 //遮罩透明度
			  ,maxmin: true //允许全屏最小化
			  ,anim: 1 //0-6的动画形式，-1不开启
			  ,content: html
			});  
      
      }
      
      
      
      
    });
  });
  }
  function disconnect() {
    if (stompClient != null) {
      stompClient.disconnect();
    }
  }
</script>