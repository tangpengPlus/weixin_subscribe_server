[#import "/base/spring.ftl" as spring /]
<!--定义排序-->
[#assign serialNumber={"1":"1","2":"2","3":"3","4":"4","5":"5","6":"6","7":"7","8":"8","9":"9","10":"10"} /]
<!--定义等级-->
[#assign grade={"1":"一级","2":"二级","3":"三级"}/]
<!--定义单选按钮-->
[#assign radioMap={"1":"是"}/]
[#assign radioMap2={"2":"否"}/]

[#assign radioMaps={"0":"否"}/]
[#assign radioMaps2={"1":"是"}/]

<!DOCTYPE html>
<html lang="en">
  <head>
    [#include "/base/base.ftl"/]
  </head>

  <body>

  <section id="container" class="">
      <!--header start-->
      [#include "/base/header.ftl"/]
      <!--header end-->
      <!--sidebar start-->
       [#include "/base/left.ftl"/]
      <!--sidebar end-->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
              <!-- page start-->
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          [#include "/base/crumbs.ftl"/]
                          <div class="panel-body">
                              <form class="form-horizontal tasi-form" method="post">
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">菜单名称</label>
                                      <div class="col-sm-4">
                                      [@spring.formInput "manageMenu.name",'class="form-control"'/]
                                      </div>
                                       <div class="col-sm-4 messageTip">
                                   		    <span>菜单名称100个字符以内</span>
                                            [@spring.showErrors /]
                                      </div>
                                  </div>
                                   <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">菜单路径</label>
                                      <div class="col-sm-4">
                                       [@spring.formInput "manageMenu.url",'class="form-control"'/]
                                      </div>
                                       <div class="col-sm-4 messageTip">
                                         	 菜单路径255个字符以内 
                                         	 [@spring.showErrors /]
                                      </div>
                                  </div>
                                   [@spring.formInput "manageMenu.id", "", "hidden"/]
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">是否为展示菜单</label>
                                       <div class="col-sm-4 text-center">
                                        [@spring.formRadioButtons "manageMenu.type" ,radioMap,'','checked="checked"'/]
                                		[@spring.formRadioButtons "manageMenu.type" ,radioMap2,'','class="radiobtn"'/]
                                       </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">是否为修改或增加菜单 </label>
                                       <div class="col-sm-4 text-center">
                                        [@spring.formRadioButtons "manageMenu.autoattach" ,radioMaps,'','checked="checked"'/]
                                		[@spring.formRadioButtons "manageMenu.autoattach" ,radioMaps2,'','class="radiobtn"'/]
                                       </div>
                                  </div>
                                 <div class="form-group">
                                      <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">菜单等级</label>
                                      <div class="col-lg-4">
                                      [@spring.formSingleSelect "manageMenu.grade" ,grade,"class='form-control m-bot15'"/]
                                      </div>
                                  </div>
                                 <div class="form-group">
                                      <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">显示顺序</label>
                                      <div class="col-lg-4">
                                         [@spring.formSingleSelect "manageMenu.sort" ,serialNumber,"class='form-control m-bot15'"/]
                                      </div>
                                  </div>
                                  
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">上级菜单</label>
                                      <div class="col-lg-4">
                                          <select class="form-control m-bot15" name="superior">
                                          [#if menuslist ??]
                                            [#list menuslist as menu]
                                            	[#if menu.grade==1&&menu.autoattach==0]
                                            			[#if manageMenu.superior==menu.id]
                                            				<option value="${menu.id}" selected="selected">${menu.name}</option>
                                            			[#else]
                                            				<option value="${menu.id}">${menu.name}</option>
                                            			[/#if]
                                            	 	[#list menuslist as meun2]
                                            	 	${menu2.id}
	                                            	 	[#if meun2.superior==menu.id&&meun2.grade==2&&meun2.autoattach==0]
		                                            	 	[#if manageMenu.superior==menu2.id]
		                                            	 		<option value="${meun2.id}" selected="selected">${meun2.name}</option>
			                                            	 	[#else]
			                                            	 	<option value="${meun2.id}">${meun2.name}</option>
		                                            	 	[/#if]
	                                            	 	[/#if]
                                            	 	[/#list]
                                            	[/#if]
                                            [/#list]
                                          [/#if]
                                          </select>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">关联菜单</label>
                                      <div class="col-lg-4">
                                          <select class="form-control m-bot15" name="relationmenu">
                                          [#if menuslist ??]
                                            [#list menuslist as menu]
                                            	[#if menu.autoattach==0]
                                            	[#if manageMenu.relationmenu==menu.id]
                                            	<option value="${menu.id}" selected="selected">${menu.name}</option>
                                            	[#else]
                                            	<option value="${menu.id}">${menu.name}</option>
                                            	[/#if]
                                            	 	
                                            	[/#if]
                                            [/#list]
                                          [/#if]
                                          </select>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">菜单图标</label>
                                      <div class="col-lg-4">
                                          <select class="form-control m-bot15" name="icopath">
                                              <option value="icon-compass">icon-compass</option>
                                          </select>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                          <label class="col-lg-2 control-label">菜单描述</label>
                                          <div class="col-lg-4">
                                           [@spring.formTextarea "manageMenu.describe",'cols="72" rows="5"'/]
                                          </div>
                                          <div class="col-lg-4">
                                              	菜单描述在255个字符以内[@spring.showErrors /]
                                          </div>
                                  </div>
                                  
                                   <div class="form-group">
                                      <div class="col-lg-offset-2 col-lg-10">
                                          <button class="btn btn-danger" type="submit">Submit</button>
                                      </div>
                                  </div>
                                  
                              </form>
                          </div>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
      <!--footer start-->
      [#include "/base/footer.ftl"/]
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="${base}/js/jquery.js"></script>
    <script src="${base}/js/bootstrap.min.js"></script>
    <script src="${base}/js/jquery.scrollTo.min.js"></script>
    <script src="${base}/js/jquery.nicescroll.js" type="text/javascript"></script>

    <script src="${base}/js/jquery-ui-1.9.2.custom.min.js"></script>
    <script class="include" type="text/javascript" src="${base}/js/jquery.dcjqaccordion.2.7.js"></script>

  <!--custom switch-->
  <script src="${base}/js/bootstrap-switch.js"></script>
  <!--custom tagsinput-->
  <script src="${base}/js/jquery.tagsinput.js"></script>
  <!--custom checkbox & radio-->
  <script type="text/javascript" src="${base}/js/ga.js"></script>

  <script type="text/javascript" src="${base}/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-daterangepicker/date.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
  <script type="text/javascript" src="${base}/assets/ckeditor/ckeditor.js"></script>

  <script type="text/javascript" src="${base}/assets/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
  <script src="${base}/js/respond.min.js" ></script>


  <!--common script for all pages-->
    <script src="${base}/js/common-scripts.js"></script>

  <!--script for this page-->
  <script src="${base}/js/form-component.js"></script>

  </body>
</html>
