[#import "/base/spring.ftl" as spring /]
<!DOCTYPE html>
<html lang="en">
  <head>
    [#include "/base/base.ftl"/]
    <link rel="stylesheet" type="text/css" href="${base}/assets/jquery-multi-select/css/multi-select.css" />
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
                                      <label class="col-sm-2 col-sm-2 control-label">商品名称</label>
                                      <div class="col-sm-4">
                                      [@spring.formInput "goods.name",'class="form-control"'/]
                                      </div>
                                       <div class="col-sm-4 messageTip">
                                   		    <span>商品名称不能为空且在15个汉字以内</span>
                                            [@spring.showErrors /]
                                      </div>
                                  </div>
                                   <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">商品价格</label>
                                      <div class="col-sm-4">
                                      [@spring.formInput "goods.price",'class="form-control"'/]
                                      </div>
                                       <div class="col-sm-4 messageTip">
                                   		    <span>商品价格 请输入金额格式 如 10.00</span>
                                            [@spring.showErrors /]
                                      </div>
                                  </div>
                                   <div class="form-group">
                                      <label class="col-sm-2 control-label col-lg-2" for="inputSuccess">所属商品类别</label>
                                      <div class="col-lg-4">
                                          <select class="form-control m-bot15" name="typeId">
                                         
                                          [#if goodsTypeList ??]
                                          ${goodsTypeList}
                                            [#list goodsTypeList as goodType]
                                            [#if goodType.id == goods.typeId]
                                            	<option value="${goodType.id}" selected="selected">${goodType.name}</option>
                                            [#else]
                                            	<option value="${goodType.id}">${goodType.name}</option>
                                            [/#if]
                                            [/#list]
                                          [/#if]
                                          </select>
                                      </div>
                                  </div>
                                  
                                   [@spring.formInput "goods.id", "", "hidden"/]
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
    <script class="include" type="text/javascript" src="${base}/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="${base}/js/jquery.scrollTo.min.js"></script>
    <script src="${base}/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="${base}/js/respond.min.js" ></script>
  
    <!--this page plugins-->

  <script type="text/javascript" src="${base}/assets/fuelux/js/spinner.min.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-fileupload/bootstrap-fileupload.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-wysihtml5/wysihtml5-0.3.0.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-wysihtml5/bootstrap-wysihtml5.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-datetimepicker/js/bootstrap-datetimepicker.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-daterangepicker/moment.min.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-daterangepicker/daterangepicker.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-colorpicker/js/bootstrap-colorpicker.js"></script>
  <script type="text/javascript" src="${base}/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
  <script type="text/javascript" src="${base}/assets/jquery-multi-select/js/jquery.multi-select.js"></script>
  <script type="text/javascript" src="${base}/assets/jquery-multi-select/js/jquery.quicksearch.js"></script>

    <!--common script for all pages-->
    <script src="${base}/js/common-scripts.js"></script>
    <!--this page  script only-->
    <script src="${base}/js/advanced-form-components.js"></script>

  </body>
</html>
