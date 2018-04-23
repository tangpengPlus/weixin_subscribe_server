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
              <section class="panel">
                  [#include "/base/crumbs.ftl"/]
                  <div class="panel-body">
                      <div class="adv-table editable-table ">
                          <div class="clearfix">
                           [@shiro.hasPermission name="/system/role/add"]
                              <div class="btn-group">
                                  <a id="editable-sample_new" class="btn btn-primary" href="${base}/system/role/add">
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
                                <table class="table table-bordered table-striped table-condensed cf">
                                  <thead class="cf">
                                  <tr>
                                      <th>角色编号</th>
                                      <th>创建时间</th>
                                      <th class="numeric">角色名称</th>
                                      <th class="numeric">角色描述</th>
                                      <th class="numeric">操作</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  [#if rolelist?? ]
	                                  [#list rolelist as role]
	                                   <tr>
	                                      <td>${role.number}</td>
	                                      <td class="numeric">[#if role.createtime ??]${role.createtime?datetime}[/#if]</td>
	                                      <td class="numeric">${role.rolename}</td>
	                                      <td class="numeric">${role.roledescribe}</td>
	                                      <td class="numeric">
	                                      [@shiro.hasPermission name="/system/role/update"]<a type="button" href="${base}/system/role/update?id=${role.id}" class="btn btn-danger btn-sm"><i class="icon-refresh"></i> Update</a> [/@shiro.hasPermission]
	                                      &nbsp;&nbsp;[@shiro.hasPermission name="/system/role/delete"]<a type="button" href="${base}/system/role/delete?id=${role.id}" class="btn btn-warning btn-sm"><i class=" icon-trash"></i> Delete</a> [/@shiro.hasPermission]
	                                      </td>
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
  [#include "/base/publicJs.ftl"/]
  </body>
</html>
