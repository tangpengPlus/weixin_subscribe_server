package com.gency.subscribe.core.util.base;

import com.gency.subscribe.model.system.PageBean;
import com.github.pagehelper.Page;
public class PageUtil {
	/**
	 * 获得分页开始位置
	 * 
	 * @param pageSize
	 * @param currentpage
	 * @return
	 */
	public static int getBenginPage(int pageSize, int currentpage) {
		if (currentpage == 0) {
			currentpage = 1;
		}
		if (pageSize == 0) {
			pageSize = 20;
		}
		int countOffset = pageSize * (currentpage -1);
		return countOffset;
	}
	/**
	 * 获取页码
	 * 
	 * @param pagebean
	 * @return pagebean
	 */
	public static PageBean getPageNumber(PageBean pagebean,Page<?> page) {
		pagebean.setAllRow(BaseUtil.changeLongToInt(page.getTotal()));
		pagebean.setPageSize(page.getPageSize());
		if (pagebean.getAllRow() == 0) {
			pagebean.setTotalPage(0);
			pagebean.setBeforPage(0);
			pagebean.setNextPage(0);
			pagebean.setCurPage(0);
			pagebean.setAllRow(0);
		} else {
			// 计算总的页数
			int totalPage = pagebean.getAllRow() % pagebean.getPageSize() == 0 ? pagebean
					.getAllRow() / pagebean.getPageSize()
					: pagebean.getAllRow() / pagebean.getPageSize() + 1;

			// 上一页
			int beforPage = 0;
			if (pagebean.getCurPage() == 1) {

				beforPage = 1;
			} else {
				beforPage = pagebean.getCurPage() - 1;
			}
			// 下一页
			int nextPage = 0;
			if (pagebean.getCurPage() == totalPage) {

				nextPage = totalPage;
			} else {
				nextPage = pagebean.getCurPage() + 1;

			}
			pagebean.setTotalPage(totalPage);
			pagebean.setBeforPage(beforPage);
			pagebean.setNextPage(nextPage);
		}
		return pagebean;
	}

}
