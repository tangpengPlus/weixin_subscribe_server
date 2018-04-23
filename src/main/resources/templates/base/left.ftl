     <aside>
     <div id="sidebar"  class="nav-collapse " >
      <ul class="sidebar-menu" id="nav-accordion">
	     [#if curryLoginAdminMenu ??]
	     	[#list curryLoginAdminMenu as menu1]
	     	 		[#if menu1.grade==1 && menu1.autoattach==0]
	     	 		[#--判断一级菜单下是否有下级菜单--]
	                       [#assign hasSecnedMenu=0]
	                       [#list curryLoginAdminMenu as menu2]
	                       	[#if menu2.superior==menu1.id&& menu2.grade==2&& menu2.autoattach==0]
	                       		[#assign hasSecnedMenu=1]
	                       		[#break]
	                       	[/#if]
                   [/#list]
	     	 		[#if hasSecnedMenu==1]
	     	 			<li class="sub-menu">
	     	 			   [#if firstId==menu1.id]
			     	 		 <a class="active" href="javascript:;">
			     	 		[#else]
			     	 		 <a  href="javascript:;">
			     	 		[/#if]
	     	 	   [#else]
		     	 		<li>
		     	 			[#if firstId==menu1.id]
			     	 		 <a class="active" href="${menu1.url}">
			     	 		[#else]
			     	 		 <a  href="${menu1.url}">
			     	 		[/#if]
	     	 		[/#if]
	                          <i class="icon-laptop"></i>
	                          <span>${menu1.name}</span>
	                      </a>
	                      [#--生成二级菜单开始--]
	                      [#if hasSecnedMenu==1]
	                        <ul class="sub">
	                        [#list curryLoginAdminMenu as menu3]
	                           [#if menu3.superior==menu1.id&& menu3.grade==2 && menu3.autoattach==0]
	                           			    [#--判断二级菜单下面是否有三级菜单--]
			                                 [#assign hasThreedMenu=0]
			                                 [#list curryLoginAdminMenu as menu4]
			                                   [#if menu4.superior==menu3.id&&menu4.grade==3&&menu4.autoattach==0]
			                                   [#assign hasThreedMenu=1]
			                                   [#break]
			                                   [/#if]
			                                 [/#list]
	                           [#if hasThreedMenu ==1]
	                                  [#if secendId==menu3.id]
	                                  <li class="sub-menu active">
	                                  <a  href="javascript:;">${menu3.name}</a>
	                                  [#else]
	                                  <li class="sub-menu">
	                                   <a  href="javascript:;">${menu3.name}</a>
	                                  [/#if]
	                                 [#else]
	                                  [#if secendId==menu3.id]
	                                  <li class="active">
	                                  <a  href="${menu3.url}">${menu3.name}</a>
	                                  [#else]
	                                  <li>
	                                   <a  href="${menu3.url}">${menu3.name}</a>
	                                  [/#if]
	                                 [/#if]
		                                [#if hasThreedMenu ==1]
			                                	<ul class="sub"> 
				                                 	[#list curryLoginAdminMenu as menu5]
					                                   [#if menu5.superior==menu3.id&& menu5.grade==3&&menu5.autoattach==0]
						                                      [#if threeId==menu5.id]
						                                      	<li><a class="active" href="${menu5.url}">${menu5.name}</a></li>
						                                      [#else] 	
						                                       <li><a  href="${menu5.url}">${menu5.name}</a></li>
						                                      [/#if]
					                                   [/#if]
				                                 [/#list]
			                                </ul>
		                                [/#if]
	                              </li>
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
     </aside>
     