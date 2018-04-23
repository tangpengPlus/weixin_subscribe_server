[#import "/base/spring.ftl" as spring /]
[#assign radioMap={"0":"否"}/]
[#assign radioMap2={"1":"是"}/]

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
                                      <label class="col-sm-2 col-sm-2 control-label">权限名称</label>
                                      <div class="col-sm-4">
                                      [@spring.formInput "manageAuthority.authorityname",'class="form-control"'/]
                                      </div>
                                       <div class="col-sm-4 messageTip">
                                   		    <span>权限名称100个字符以内</span>
                                            [@spring.showErrors /]
                                      </div>
                                  </div>
                                   <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">权限路径</label>
                                      <div class="col-sm-4">
                                       [@spring.formInput "manageAuthority.authorityurl",'class="form-control"'/]
                                      </div>
                                       <div class="col-sm-4 messageTip">
                                         	 权限路径200个字符以内 
                                         	 [@spring.showErrors /]
                                      </div>
                                  </div>
                                   [@spring.formInput "manageAuthority.id", "", "hidden"/]
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">是否开启日志记录</label>
                                       <div class="col-sm-4 text-center">
                                        [@spring.formRadioButtons "manageAuthority.authoritylogstate" ,radioMap,'','checked="checked"'/]
                                		[@spring.formRadioButtons "manageAuthority.authoritylogstate" ,radioMap2,'','class="radiobtn"'/]
                                       </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">所属菜单</label>
                                      <div class="col-lg-4">
                                          <select class="form-control m-bot15" name="menuid">
                                          [#if menulist ??]
                                            [#list menulist as menu]
                                            	[#if menu.type==1&&menu.autoattach==0]
                                            		[#if manageAuthority.menuid==menu.id]
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
                                          <label class="col-lg-2 control-label">权限描述</label>
                                          <div class="col-lg-4">
                                           [@spring.formTextarea "manageAuthority.authoritydescribe",'cols="72" rows="5"'/]
                                          </div>
                                          <div class="col-lg-4">
                                              	权限描述在255个字符以内[@spring.showErrors /]
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
