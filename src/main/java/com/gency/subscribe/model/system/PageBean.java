package com.gency.subscribe.model.system;
public class PageBean  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private int allRow;//总的行数
	private int totalPage;//总页数
	private int curPage;//当前页
	private int pageSize;//每页显示的记录数
	private int beforPage;//上一页
	private int nextPage;//下一页
	private int startRow;//起始页
	private String requestUrl;//请求地址
	public PageBean(){
		this.curPage=1;
	}
	
	
	public int getBeforPage() {
		return beforPage;
	}
	public void setBeforPage(int beforPage) {
		this.beforPage = beforPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}
	public int getAllRow() {
		return allRow;
	}
	public void setAllRow(int allRow) {
		this.allRow = allRow;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getStartRow() {
		return startRow;
	}
	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}


	public String getRequestUrl() {
		return requestUrl;
	}


	public void setRequestUrl(String requestUrl) {
		this.requestUrl = requestUrl;
	}
	
	
	
}
