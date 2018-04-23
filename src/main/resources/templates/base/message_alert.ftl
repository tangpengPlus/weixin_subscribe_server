    [#--消息展示--]
<script>
 $(document).ready(function(){
    var message='${pageShowMessage}';
    if(message!=null&&message!=''){
      layer.msg(message,{time:4000});
    }
});
</script>
