package com.zhc.sys.service.base.other;

public class PaginaionStrategyForJPA implements PaginationStrategy {
	private int startPoint;
	private int endPoint;

	public int getEndPoint() {
		return endPoint;
	}

	public int getStartPoint() {
		return startPoint;
	}

	public void setDataPoint(int currentPage, int pageSize) {
		startPoint = (currentPage - 1) * pageSize;
		endPoint = pageSize;
	}
}