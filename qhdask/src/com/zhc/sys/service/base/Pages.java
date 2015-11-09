package com.zhc.sys.service.base;

/**
 * 分页对象
 * 
 * @ClassName: Pages
 * @Description: TODO
 * @author zhangchong
 * @date 2014年6月17日 下午5:26:05
 * 
 */
public class Pages {

	private long reCount;// 总条数
	private int page;//当前页码，从1开始
	private int pageSize;//每页显示条数
	private int rePages;//总页数
	private String path;//分页提交请求的url
	
	//dataTables控件相关参数
	

	public Pages() {
	}

	public Pages(long reCount, int page, int pageSize) {
		this.reCount = reCount;
		this.page = page;
		this.pageSize = pageSize;
		if (reCount <= 0) {
			reCount = 0;
		}
		account();
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setReCount(long reCount) {
		this.reCount = reCount;
	}

	public void setRePages(int rePages) {
		this.rePages = rePages;
	}

	public void account() {
		int temp_i = this.reCount % this.pageSize == 0 ? 0 : 1;
		this.rePages = ((int)this.reCount / this.pageSize + temp_i);
		if (this.page > this.rePages) {
			this.page = this.rePages;
		}
		if (this.page < 1) {
			this.page = 1;
		}
	}

	public long getReCount() {
		return this.reCount;
	}

	public int getPage() {
		return this.page;
	}

	public int getPageSize() {
		return this.pageSize;
	}

	public int getRePages() {
		return this.rePages;
	}

	public int getRestat() {
		return (this.page - 1) * this.pageSize - 1;
	}

	public boolean atFirstPage() {
		return this.page == 1;
	}

	public boolean atLastPage() {
		return this.page == this.rePages;
	}

	public int getFirstItemIndex() {
		return (this.page - 1) * this.pageSize + 1;
	}

	public long getLastItemIndex() {
		long nCurrentIndex = (this.page - 1) * this.pageSize + this.pageSize;
		if (nCurrentIndex > this.reCount) {
			return this.reCount;
		}
		return nCurrentIndex;
	}

	public int getPrePageIndex() {
		int nRet = this.page - 1;
		if (nRet < 1) {
			nRet = 1;
		}
		return nRet;
	}

	public int getNextPageIndex() {
		int nRet = this.page + 1;
		if (nRet > this.rePages) {
			nRet = this.rePages;
		}
		return nRet;
	}
	
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}


}
