    [#--view 分页 显示公共部分--]
    <div class="pagingbox">
                <div class="jumppag">
                    <form class="inputpag">
                        <label for="pagNum">跳至</label>
                        <input id="pagNum" type='text' onkeyup="this.value=this.value.replace(/[^0-9-]+/,'');" />
                    </form>
                    <a class="jumppagBtn" href="${curryRequestUrl}curPage=$('#pagNum').val">跳转</a>
                </div>
                <div class="paging">
                    <a href="${curryRequestUrl}curPage=${pagebean.beforPage}" class="prevpag icon-chevron-left"></a>
                    [#--当前页数为零的情况--]
                    [#if pagebean.totalPage==0]
                        [#--当前只有一页的情况--]
                        [#elseif pagebean.totalPage==1]
                         <a href="${curryRequestUrl}curPage=1" class="active">1</a>
	                    [#--当前页数少于10的情况--]
	                    [#elseif pagebean.totalPage lte 10]
	                      [#list 1..(pagebean.totalPage-1) as pageNumber]
	                        [#if pageNumber==pagebean.curPage]
	                         <a href="${curryRequestUrl}curPage=${pageNumber}" class="active">${pageNumber}</a>
	                         [#else]
	                         <a href="${curryRequestUrl}curPage=${pageNumber}">${pageNumber}</a>
	                        [/#if]
	                      [/#list]
	                    [#--当前页数大于10页的情况--]
	                    [#elseif pagebean.totalPage gte 10]
	                    [#--判断当前页小于或者等于5的情况--]
	                     [#if pagebean.curPage lte 5]
		                       [#list 1..9 as pageNumber]
			                         [#if pageNumber==pagebean.curPage]
				                         <a href="${curryRequestUrl}curPage=${pageNumber}" class="active">${pageNumber}</a>
				                         [#else]
				                         <a href="${curryRequestUrl}curPage=${pageNumber}">${pageNumber}</a>
			                         [/#if]
		                       [/#list]
		                       	<span class="ellipsis">…</span>        
	                       	[#--当前情况判断 当前页大于第五页 然后后面的页数不足五页的情况--]
	                      	[#elseif pagebean.curPage gt 5 && (pagebean.totalPage-pagebean.curPage) lt  5]
			                        <a href="${curryRequestUrl}curPage=1">1</a>
		                      	    <span class="ellipsis">…</span>   
			                       [#list (pagebean.curPage-(10-(4+pagebean.totalPage-pagebean.curPage)))..(pagebean.totalPage-1) as pageNumber]   
			                         [#if pageNumber==pagebean.curPage]
				                         <a href="${curryRequestUrl}curPage=${pageNumber}" class="active">${pageNumber}</a>
				                         [#else]
				                         <a href="${curryRequestUrl}curPage=${pageNumber}">${pageNumber}</a>
			                         [/#if]
			                       [/#list]    
			                       
			                [#--当前情况判断 当前页大于第五页 然后后面的页数大于五页的情况--]
		                     [#elseif pagebean.curPage gt 5 && (pagebean.totalPage-pagebean.curPage) gte 5]
		                      	  <a href="${curryRequestUrl}curPage=1">1</a>
		                      	  <span class="ellipsis">…</span>   
			                      [#list (pagebean.curPage-2)..(pagebean.curPage+2) as pageNumber]
			                       [#if pageNumber==pagebean.curPage]
				                         <a href="${curryRequestUrl}curPage=${pageNumber}" class="active">${pageNumber}</a>
				                         [#else]
				                         <a href="${curryRequestUrl}curPage=${pageNumber}">${pageNumber}</a>
			                         [/#if]
			                      [/#list]
		                       		<span class="ellipsis">…</span>                
	                  [/#if]
	                     
                    [/#if]
                    [#if pagebean.totalPage !=1]
	                    [#if pagebean.curPage gte pagebean.totalPage]
	                   	    <a href="${curryRequestUrl}curPage=${pagebean.totalPage}" class="active">${pagebean.totalPage}</a>
	                       [#else]
	                       <a href="${curryRequestUrl}curPage=${pagebean.totalPage}">${pagebean.totalPage}</a>
	                    [/#if]
                    [/#if]
                    <a href="${curryRequestUrl}curPage=${pagebean.nextPage}" class="nextpag icon-chevron-right"></a>
                </div>
            </div>
            [#--跳转js处理--]
            <script>
                    var pagv=0;
			        $('#pagNum').change(function(){
			            pagv = $('#pagNum').val();
			        });
			        $('.jumppagBtn').click(function () {
			            $('.jumppagBtn').attr('href','${curryRequestUrl}curPage='+pagv);
			           
			        });
			        //开启loading弹层
			        $('.pagingbox').on('click','a',function(){
			        	if($(this).attr('href')!=''){
			        		openLoadingFun();
			        	}
			        });
            </script>
            
            