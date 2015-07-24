package com.zhc.sys.service.base.other;


/**
 * @param <T>
 *            ����:like List<T></br>
 */
public class PageObjectS<T> {
	private int currentPage;
	private int dataCount;
	private int pageSize;
	private int maxPage;
	private int startPoint;
	private int endPoint;
	private PaginationStrategy paginationStrategy;

	/**
	 * @param dataCount
	 *            �������
	 * @param currentPage
	 *            ��ǰҳ
	 * @param pageSize
	 *            ҳ���С ע: Ĭ�Ϸ�ҳ�߼� startPoint & endPoint ��oracleʵ��</br>
	 *            �����Ҫʹ�õ�������ݿ���ʵ��PaginationStrategy�ӿ�</br> ʹ�ø����
	 *            setPaginationStrategy ���������Ӧ��ʵ��</br>
	 */
	public PageObjectS(int dataCount, int currentPage) {
		this.pageSize = 10;
		this.dataCount = dataCount;
		this.currentPage = currentPage;
		maxPage = dataCount % pageSize == 0 ? dataCount / pageSize : dataCount
				/ pageSize + 1;
		paginationStrategy = new PaginaionStrategyForJPA();
		setDataPoint(paginationStrategy);
	}

	/**
	 * @param dataCount
	 *            �������
	 * @param currentPage
	 *            ��ǰҳ
	 * @param pageSize
	 *            ҳ���С ע: Ĭ�Ϸ�ҳ�߼� startPoint & endPoint ��oracleʵ��</br>
	 *            �����Ҫʹ�õ�������ݿ���ʵ��PaginationStrategy�ӿ�</br> ʹ�ø����
	 *            setPaginationStrategy ���������Ӧ��ʵ��</br>
	 */
	public PageObjectS(int dataCount, int currentPage, int pageSize) {
		if (pageSize == 0)
			throw new IllegalArgumentException(
					" ��ҳ������� [pageSize]����ΪС�ڵ��� 0  ��ǰ[pageSize]��ֵ�� : "
							+ pageSize);
		this.pageSize = pageSize;
		this.dataCount = dataCount;
		this.currentPage = currentPage;
		maxPage = dataCount % pageSize == 0 ? dataCount / pageSize : dataCount
				/ pageSize + 1;
		paginationStrategy = new PaginaionStrategyForJPA();
		setDataPoint(paginationStrategy);
	}

	private void setDataPoint(PaginationStrategy paginationStrategy) {
		paginationStrategy.setDataPoint(currentPage, pageSize);
		startPoint = paginationStrategy.getStartPoint();
		endPoint = paginationStrategy.getEndPoint();
	}

	/**
	 * Ĭ�ϵ�ʵ���� PaginationStrategyForOracle �����Ҫʵ��������ݿ�ķ�ҳ�߼�����̳�
	 * PaginationStrategy�����뵱ǰҳ���ҳ���С���ò�ͬ��ݿ�ķ�ҳ
	 * 
	 * @param paginationStrategy
	 */
	public void setPaginationStrategy(PaginationStrategy paginationStrategy) {
		this.paginationStrategy = paginationStrategy;
		setDataPoint(paginationStrategy);
	}

	/**
	 * ��õ�ǰҳ��
	 * 
	 * @return ��ǰҳ��
	 */
	public int getCurrentPage() {
		return currentPage;
	}

	/**
	 * ��õ�ҳ�����
	 * 
	 * @return ��ҳ�����
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * ���һ���ж���ҳ
	 * 
	 * @return һ���ж���ҳ
	 */
	public int getMaxPage() {
		return maxPage;
	}

	/**
	 * ����������
	 * 
	 * @return �������
	 */
	public int getDataCount() {
		return dataCount;
	}

	/**
	 * ��÷�ҳ��ʼ��
	 * 
	 * @return ��ҳ��ʼ��
	 */
	public int getStartPoint() {
		return startPoint;
	}

	/**
	 * ��÷�ҳ�����
	 * 
	 * @return ��ҳ�����
	 */
	public int getEndPoint() {
		return endPoint;
	}

	@Override
	public String toString() {
		StringBuilder pageObjectPrintBuilder = new StringBuilder();
		pageObjectPrintBuilder.append("  ��ǰҳ�� ").append(currentPage)
				.append(" ������� ").append(dataCount);
		pageObjectPrintBuilder.append("  ��ʼ�� ").append(startPoint)
				.append(" ����� ").append(endPoint);
		pageObjectPrintBuilder.append("  ��ҳ�� ").append(maxPage)
				.append(" ��ҳ����� ").append(pageSize);
		return pageObjectPrintBuilder.toString();
	}
}