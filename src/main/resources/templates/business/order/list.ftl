<!DOCTYPE html>
<html lang="en">
  <head>
 [#include "/base/base.ftl"/]
  </head>
  <body>
  <section id="container" class="">
      [#include "/base/header.ftl"/]
      <!--header end-->
      [#include "/base/left.ftl"/]
      <section id="main-content">
          <section class="wrapper">
              <!-- page start-->
              <section class="panel">
                   [#include "/base/crumbs.ftl"/]
                  <div class="panel-body">
                      <div class="adv-table editable-table ">
                          <div class="space15"></div>
                           <div class="panel-body">
                              <section id="no-more-tables">
                                <table class="table table-bordered table-striped table-condensed cf">
                                  <thead class="cf">
                                  <tr>
                                      <th>数据编号</th>
                                       <th>客户姓名</th>
                                      <th>客户电话</th>
                                      <th>置业顾问姓名</th>
                                       <th>置业顾问电话号码</th>
                                      <th>到访状态</th>
                                      <th>车牌号</th>
                                      <th>到访人数</th>
                                      <th>预约到访时间</th>
                                      <th>实际到访时间</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  [#if orderList?? ]
	                                  [#list orderList as order]
	                                   <tr>
	                                      <td>${order.orderNo}</td>
	                                      <td class="numeric">${order.userName}</td>
	                                      <td class="numeric">${order.userTel}</td>
	                                      <td class="numeric">${order.adviserName}</td>
                                          <td class="numeric">${order.adviserPhone}</td>
                                          <td class="numeric">[#if order.state ==0]未到访 [#else]已到访 [/#if]</td>
                                          <td class="numeric">${order.licensePlate}</td>
                                          <td class="numeric">${order.numberOfPeople}</td>
                                          <td class="numeric">${order.dateOfVisit}   [#if order.morningOrAfternoon ==1]上午 [#else]下午 [/#if]</td>
                                          <td class="numeric">[#if order.confirmTime ??]${order.confirmTime?datetime}[/#if]</td>
	                                  </tr>
	                                  [/#list]
                                  [/#if]
                              </tbody>
                              </table>
                              [#include "/base/page.ftl"/]
                              </section>
                          </div>
                      </div>
                  </div>
              </section>
              <!-- page end-->
          </section>
      </section>
      <!--main content end-->
      <!--footer start-->
      [#include "/base/footer.ftl"/]
      <!--footer end-->
  </section>
 	<script src="${base}/js/jquery-1.8.3.min.js"></script>
    <script src="${base}/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="${base}/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="${base}/js/jquery.scrollTo.min.js"></script>
    <script src="${base}/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script type="text/javascript" src="${base}/assets/data-tables/jquery.dataTables.js"></script>
    <script type="text/javascript" src="${base}/assets/data-tables/DT_bootstrap.js"></script>
    <script src="${base}/js/respond.min.js" ></script>
    <!--common script for all pages-->
    <script src="${base}/js/common-scripts.js"></script>
      <script src="${base}/js/editable-table.js"></script>
  </body>
</html>
