package com.zhc.sys.service.base.other;

public interface PaginationStrategy {
	/**
	 * ��ȡ��ݽ����
	 * 
	 * @return
	 */
	public int getStartPoint();

	/**
	 * ��ȡ�����ʼ��
	 * 
	 * @return
	 */
	public int getEndPoint();

	/**
	 * �������ҳʼ��ͽ����
	 * 
	 * @param currentPage
	 * @param pageSize
	 */
	public void setDataPoint(int currentPage, int pageSize);
}