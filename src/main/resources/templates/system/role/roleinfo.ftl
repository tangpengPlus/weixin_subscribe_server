[#import "/base/spring.ftl" as spring /]
[#assign radioMap={"0":"普通角色"}/]
[#assign radioMap2={"1":"超级管理员"}/]

<!DOCTYPE html>
<html lang="en">
  <head>
    [#include "/base/base.ftl"/]
    <link href="${base}/css/treeList.css" rel="stylesheet">
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
                                      <label class="col-sm-2 col-sm-2 control-label">角色名称</label>
                                      <div class="col-sm-4">
                                      [@spring.formInput "manageRole.rolename",'class="form-control"'/]
                                      </div>
                                       <div class="col-sm-4 messageTip">
                                   		    <span>角色名称50个字符以内</span>
                                            [@spring.showErrors /]
                                      </div>
                                  </div>
                                   [@spring.formInput "manageRole.id", "", "hidden"/]
                                   [#if isSuperAdmin]
                                   <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">角色类型</label>
                                       <div class="col-sm-4 text-center">
                                        [@spring.formRadioButtons "manageRole.roletype" ,radioMap,'','checked="checked"'/]
                                		[@spring.formRadioButtons "manageRole.roletype" ,radioMap2,'','class="radiobtn"'/]
                                       </div>
                                  </div>
                                   [/#if]
                                  <div class="form-group">
                                          <label class="col-lg-2 control-label">角色描述</label>
                                          <div class="col-lg-4">
                                           [@spring.formTextarea "manageRole.roledescribe",'cols="72" rows="5"'/]
                                          </div>
                                          <div class="col-lg-4">
                                              	角色描述在255个字符以内[@spring.showErrors /]
                                          </div>
                                  </div>
                                   <div class="form-group">
                                          <label class="col-lg-2 control-label">角色菜单及权限</label>
                                          <div class="col-lg-8">
                                                <div class="treeList">
              <a class="checkAll" href="javascript:;">
                  <i class="icon-check-empty"></i><span>全部</span>
                  <input type="checkbox"/>
              </a>
                <ul class="tree_level_1">
                 [#if menuList ??]
                 	[#list menuList as menu1]
                 	[#if menu1.grade==1&&menu1.type==1]
                 	<li>
                 		<a class="check_item" href="javascript:;">
     						[#--判断一级菜单复选框是否被选择开始--]
                            [#assign isChoseFirstMenu=0/]
                            	[#list manageRole.choseMenu as chosemenu]
                            	 	[#if chosemenu==menu1.id]
                            	 		[#assign isChoseFirstMenu=1]
                            	 		[#break]
                            	 	[/#if]
                            	[/#list]
                            [#--判断一级菜单复选框是否被选择结束--]
                            [#if isChoseFirstMenu==1]
                           		 <i class="icon-check-sign"></i><span>${menu1.name}</span>
                             	<input name="choseMenu" value="${menu1.id}" type="checkbox" checked="checked"/>
                        	[#else]
                        		<i class="icon-check-empty"></i><span>${menu1.name}</span>
                            	 <input name="choseMenu" value="${menu1.id}" type="checkbox"/>
                            [/#if]
                        </a>
                        [#--初始化一级菜单下是否有权限开始--]
                        [#assign isFunction=0 /]
                 		[#if authorList ??]
                 			[#list authorList as author]
                 				[#if author.menuid==menu1.id]
                 				 [#assign isFunction=1 /]
                 				[/#if]
                 			[/#list]
                 		[/#if]
                 		[#--初始化一级菜单下是否有权限结束--]
                 		[#--循环一级菜单下的权限信息开始--]
	                 	[#if isFunction==1]
	                 		<ul class="tree_menu">
		                 		[#list authorList as author]
		                 		    [#if author.menuid==menu1.id]
	                 				   <li>
			                                <a href="javascript:;">
			                                    [#--判断一级菜单下的权限是否被选中开始--]
			                                    [#assign isChoseFirstAuthor=0 /]
			                                    [#list manageRole.choseAuthor as  choseauthor1]
			                                      [#if choseauthor1==author.id]
				                                      [#assign isChoseFirstAuthor=1 /]
				                                      [#break]
			                                      [/#if]
			                                    [/#list]
			                                     [#--判断一级菜单下的权限是否被选中结束--]
			                                     [#if isChoseFirstAuthor==1]
			                                      	 <i class="icon-check-sign"></i><span>${author.authorityname}</span>
				                                     <input name="choseAuthor" value="${author.id}" type="checkbox" checked="checked"/>
			                                     [#else]
			                                      	 <i class="icon-check-empty"></i><span>${author.authorityname}</span>
				                                     <input name="choseAuthor" value="${author.id}" type="checkbox"/>
			                                     [/#if]
			                                    
			                                </a>
                            		  </li>
	                 				[/#if]
		                 		[/#list]
	                 		</ul>
	                 	[/#if]
                 		[#--循环一级菜单下的权限信息结束--]
                 				[#--初始化一级菜单下是否有二级菜单开始--]
                 		 		[#assign isSecendMenu=0 /]
                 		 		[#list menuList as menu2]
                 		 		  [#if menu2.grade==2&&menu2.superior==menu1.id&&menu2.type==1]
                 		 		   [#assign isSecendMenu=1 /]
                 		 		  [/#if]
                 		 		[/#list]
                 		       [#--初始化一级菜单下是否有二级菜单结束--]
                 		       [#--循环一级菜单下二级菜单信息开始--]
                 		       [#if isSecendMenu==1]
                 		       <ul class="tree_level_2">
                 		         [#list menuList as menu2]
	                 		          [#if menu2.grade==2&&menu2.superior==menu1.id&&menu2.type==1]
	                 		          <li>
	                 		 		    <a class="check_item" href="javascript:;">
                                    		[#--判断二级级菜单复选框是否被选择开始--]
				                            [#assign isChoseSecendMenu=0]
				                            	[#list manageRole.choseMenu as chosemenu2]
				                            	 	[#if chosemenu2==menu2.id]
				                            	 		[#assign isChoseSecendMenu=1]
				                            	 		[#break]
				                            	 	[/#if]
				                            	[/#list]
                            				[#--判断二级菜单复选框是否被选择结束--]
                            				[#if isChoseSecendMenu==1]
                            				<i class="icon-check-sign"></i><span>${menu2.name}</span>
                            				<input name="choseMenu" value="${menu2.id}" type="checkbox" checked="checked"/>
                            				[#else]
                            				<i class="icon-check-empty"></i><span>${menu2.name}</span>
                            				<input name="choseMenu" value="${menu2.id}" type="checkbox"/>
                            				[/#if]
                                    		 
                               			 </a>
                               			 [#--判断二级菜单下是否有权限开始--]
                               			 [#assign isSecendFunction=0 /]
                               			 [#list authorList as author2]
                               			 	[#if author2.menuid==menu2.id]
                 				 				[#assign isSecendFunction=1 /]
                 							[/#if]
                               			 [/#list]
                               			 [#--判断二级菜单下是否有权限结束--] 
                               			 [#--生成二级菜单下的权限信息开始--]
                               			 [#if isSecendFunction==1]
                               			  <ul class="tree_menu">
                               			  	[#list authorList as author2]
	                               			 	[#if author2.menuid==menu2.id]
	                 				 				 <li>
                                        				<a href="javascript:;">
				                                                [#--判断二级菜单下的权限是否被选中开始--]
							                                    [#assign isChoseSecendAuthor=0 /]
							                                    [#list manageRole.choseAuthor as  choseauthor2]
							                                      [#if choseauthor2==author2.id]
								                                      [#assign isChoseSecendAuthor=1 /]
								                                      [#break]
							                                      [/#if]
							                                    [/#list]
			                                     				[#--判断二级菜单下的权限是否被选中结束--]
			                                     				[#if isChoseSecendAuthor==1]
			                                     				 <i class="icon-check-sign"></i><span>${author2.authorityname}</span>
			                                     				 <input name="choseAuthor" value="${author2.id}" type="checkbox" checked="checked"/>
			                                     				[#else]
			                                     				 <i class="icon-check-empty"></i><span>${author2.authorityname}</span>
			                                     				 <input name="choseAuthor" value="${author2.id}" type="checkbox"/>
			                                     				[/#if]
                                        				</a>
                                    				</li>
	                 							[/#if]
                               			 	[/#list]
                               			 	</ul>
                               			 [/#if]
                               			 [#--生成二级菜单下的权限信息结束--]
                               			 [#--判断二级菜单下是否有三级菜单开始--]
                               			 [#assign isThreedMenu=0 /]
                               			 [#list menuList as menu3]
                               			   [#if menu3.grade==3&&menu3.superior==menu2.id&&menu3.type==1]
                               			      [#assign isThreedMenu=1 /]
                               			   [/#if]
                               			 [/#list]
                               			 [#--判断二级菜单下是否有三级菜单结束--]
                               			 [#--生成二级菜单下的三级菜单开始--]
                               			 [#if isThreedMenu==1]
                               			   <ul class="tree_level_3">
                               			  	[#list menuList as menu3]
	                               			   [#if menu3.grade==3&&menu3.superior==menu2.id&&menu3.type==1]
	                               			      <li>
				                                        <a class="check_item" href="javascript:;">
				                                        [#--判断三级级菜单复选框是否被选择开始--]
							                            [#assign isChoseThreedMenu=0]
							                            	[#list manageRole.choseMenu as chosemenu3]
							                            	 	[#if chosemenu3==menu3.id]
							                            	 		[#assign isChoseThreedMenu=1]
							                            	 		[#break]
							                            	 	[/#if]
							                            	[/#list]
                            							[#--判断三级菜单复选框是否被选择结束--]
                            							[#if isChoseThreedMenu==1]
                            							<i class="icon-check-sign"></i><span>${menu3.name}</span>
                            							<input name="choseMenu" value="${menu3.id}" type="checkbox" checked="checked"/>
                            							[#else]
                            							<i class="icon-check-empty"></i><span>${menu3.name}</span>
                            							<input name="choseMenu" value="${menu3.id}" type="checkbox"/>
                            							[/#if]
                                   					 </a>
                                   					 [#--判断三级菜单下是否有权限信息开始--]
                                   					  [#assign isThreedFunction=0 /]
                                   					  [#list authorList as author3]
                                   					    [#if author3.menuid==menu3.id]
                                   					    	[#assign isThreedFunction=1 /]
                                   					  	[/#if]
                                   					  [/#list]
                                   					  [#--判断三级菜单下是否有权限信息结束--]
                                   					  [#--生成三级菜单下的权限信息开始--]
                                   					  [#if isThreedFunction==1]
	                                   					  <ul class="tree_menu">
	                                   					  [#list authorList as author3]
	                                   					  	[#if author3.menuid==menu3.id]
                                   					    		<li>
					                                                <a href="javascript:;">
					                                                     	[#--判断三级菜单下的权限是否被选中开始--]
										                                    [#assign isChoseThreedAuthor=0 /]
										                                    [#list manageRole.choseAuthor as  choseauthor3]
										                                      [#if choseauthor3==author3.id]
											                                      [#assign isChoseThreedAuthor=1 /]
											                                      [#break]
										                                      [/#if]
										                                    [/#list]
						                                     				[#--判断三级菜单下的权限是否被选中结束--]
								                                            [#if isChoseThreedAuthor==1]
								                                            <i class="icon-check-sign"></i><span>${author3.authorityname}</span>
								                                            <input name="choseAuthor" value="${author3.id}" type="checkbox" checked="checked"/>
								                                            [#else]
								                                            <i class="icon-check-empty"></i><span>${author3.authorityname}</span>
								                                            <input name="choseAuthor" value="${author3.id}" type="checkbox"/>
								                                            [/#if]         
					                                                </a>
                                           						 </li>
                                   					  		[/#if]
	                                   					  [/#list]
	                                   					  </ul>
                                   					  [/#if]
                                   				 </li>
	                               			   [/#if]
                               			 	[/#list]
                               			 	</ul>
                               			 [/#if]
                               			  [#--生成二级菜单下的三级菜单结束--]
                               		   <li>
	                 		 		  [/#if]
                 		       	 [/#list]
                 		       	 </ul>
                 		       [/#if]
                 		       
                 		</li>
                 		[/#if]
                 	[/#list]
                 [/#if]
                 </ul>
              </div>
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
 [#include "/base/publicJs.ftl"/] 
 <script src="${base}/js/treeList.js"></script>
  </body>
</html>
