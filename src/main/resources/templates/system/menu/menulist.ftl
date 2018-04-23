<!DOCTYPE html>
<html lang="en">
  <head>
 [#include "/base/base.ftl"/]
 <link href="${base}/css/tableAccordion.css" rel="stylesheet" />
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
              <section class="panel">
                  [#include "/base/crumbs.ftl"/]
                  <div class="panel-body">
                      <div class="adv-table editable-table ">
                          <div class="clearfix">
                          [@shiro.hasPermission name="/system/menu/add"]
                              <div class="btn-group">
                                  <a id="editable-sample_new" class="btn btn-primary" href="${base}/system/menu/add">
                                      Add New <i class="icon-plus"></i>
                                  </a>
                              </div>
                         [/@shiro.hasPermission]
                              <div class="btn-group pull-right">
                                  <button class="btn dropdown-toggle" data-toggle="dropdown">Tools <i class="icon-angle-down"></i>
                                  </button>
                                  <ul class="dropdown-menu pull-right">
                                      <li><a href="#">Print</a></li>
                                      <li><a href="#">Save as PDF</a></li>
                                      <li><a href="#">Export to Excel</a></li>
                                  </ul>
                              </div>
                          </div>
                          <div class="space15"></div>
                           <div class="panel-body">
                              <section id="no-more-tables">
                                <table class="tableAcc table table-bordered table-striped table-condensed cf">
                                  <thead class="cf">
                                  <tr>
                                      <th></th>
                                      <th>菜单编号</th>
                                      <th class="numeric">菜单名称</th>
                                      <th class="numeric">菜单图标</th>
                                      <th class="numeric">创建时间</th>
                                      <th class="numeric">创建人</th>
                                      <th class="numeric">菜单等级</th>
                                      <th class="numeric">菜单排序</th>
                                      <th class="numeric">操作</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  [#if menuList?? ]
	                                  [#list menuList as menu]
		                                  [#if menu.grade==1&&menu.autoattach==0]
		                                  [#assign hasSecendMenu=0]
		                                 	 [#list menuList as menu2]
		                                 	 	[#if menu2.superior==menu.id&&menu2.grade==2&&menu2.autoattach==0]
												[#assign hasSecendMenu=1]
												[#break]
		                                 	 	[/#if]
		                                 	 [/#list]
		                                 	 [#if hasSecendMenu==1]
		                                 	  <tr class="table_level_01">
		                                 	   <td><i class="icon-plus-sign-alt"></i></td>
		                                 	 [#else]
		                                 	  <tr>
		                                 	   <td></td>
		                                 	 [/#if]
		                                      <td>${menu.number}</td>
		                                      <td class="numeric">${menu.name}</td>
		                                      <td class="numeric">${menu.icopath}</td>
		                                      <td class="numeric">[#if menu.createtime ??]${menu.createtime?datetime}[/#if]</td>
		                                      <td class="numeric">${menu.createadmin}</td>
		                                      <td class="numeric">${menu.grade}</td>
		                                      <td class="numeric">${menu.sort}</td>
		                                      <td class="numeric">
		                                      [@shiro.hasPermission name="/system/menu/update"] <a type="button" href="${base}/system/menu/update?id=${menu.id}" class="btn btn-danger btn-sm"><i class="icon-refresh"></i> Update</a>[/@shiro.hasPermission]
		                                      &nbsp;&nbsp;[@shiro.hasPermission name="/system/menu/delete"]<a type="button" href="${base}/system/menu/delete?id=${menu.id}" class="btn btn-warning btn-sm"><i class=" icon-trash"></i> Delete</a>[/@shiro.hasPermission]
		                                      </td>
	                                  		</tr>
		                                  [/#if]
											[#if hasSecendMenu==1]
											[#list menuList as menu2]
		                                 	 	[#if menu2.superior==menu.id&&menu2.grade==2&&menu2.autoattach==0]
		                                 	 			[#assign hasThreedMenu=0]
					                                 	 [#list menuList as menu3]
					                                 	 	[#if menu3.superior==menu2.id&&menu3.grade==3&&menu3.autoattach==0]
															[#assign hasThreedMenu=1]
															[#break]
					                                 	 	[/#if]
					                                 	 [/#list]
												   [#if hasThreedMenu==1]
												   <tr class="table_level_02">
							                       <td><i class="icon-plus-sign-alt"></i></td>
												   [#else]
												   <tr class="table_level_02">
							                       <td></td>
												   [/#if]	                                 	 	
									                       <td>${menu2.number}</td>
					                                      <td class="numeric">${menu2.name}</td>
					                                      <td class="numeric">${menu2.icopath}</td>
					                                      <td class="numeric">[#if menu2.createtime ??]${menu2.createtime?datetime}[/#if]</td>
					                                      <td class="numeric">${menu2.createadmin}</td>
					                                      <td class="numeric">${menu2.grade}</td>
					                                      <td class="numeric">${menu2.sort}</td>
									                      <td class="numeric">
									                          <a type="button" href="/system/menu/update?id=${menu2.id}" class="btn btn-danger btn-sm"><i class="icon-refresh"></i> Update</a>
									                          &nbsp;&nbsp;<a type="button" href="/system/menu/delete?id=${menu2.id}" class="btn btn-warning btn-sm"><i class=" icon-trash"></i> Delete</a>
									                      </td>
									                  </tr>
									                   [#if hasThreedMenu==1]
									                   [#list menuList as menu3]
									                   	[#if menu3.superior==menu2.id&&menu3.grade==3&&menu3.autoattach==0]
									                   		 <tr class="table_level_03">
									                      <td>${menu3.number}</td>
					                                      <td class="numeric">${menu3.name}</td>
					                                      <td class="numeric">${menu3.icopath}</td>
					                                      <td class="numeric">[#if menu3.createtime ??]${menu3.createtime?datetime}[/#if]</td>
					                                      <td class="numeric">${menu3.createadmin}</td>
					                                      <td class="numeric">${menu3.grade}</td>
					                                      <td class="numeric">${menu3.sort}</td>
									                      <td class="numeric">
									                          <a type="button" href="/system/menu/update?id=${menu3.id}" class="btn btn-danger btn-sm"><i class="icon-refresh"></i> Update</a>
									                          &nbsp;&nbsp;<a type="button" href="/system/menu/delete?id=${menu3.id}" class="btn btn-warning btn-sm"><i class=" icon-trash"></i> Delete</a>
									                      </td>
									                  </tr>
									                   	
									                   	[/#if]
									                   [/#list]
									                   [/#if]
									                  
		                                 	 	[/#if]
		                                 	 [/#list]
											
											[/#if]		                                 	 
	                                  [/#list]
                                  [/#if]
                              </tbody>
                              </table>
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
    <script src="${base}/js/tableAccordion.js"></script>
  </body>
</html>
