package domain;

public class PagingVO {

	private int pageNum; //현재페이지번호
	private int qty; //한 페이지에 보여줄 게시글 수(10)
	private String type;
	private String keyword;
	
	public PagingVO() {
		//페이지 번호를 클릭하기 전의 기본값
		this.pageNum = 1;
		this.qty = 10;
	}
	
	public PagingVO(int pageNum, int qty, String type, String keyword) {
		this.pageNum = pageNum;
		this.qty = qty;
		this.type = type;
		this.keyword = keyword;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public int getPageStart() { //db에서 사용되는 시작번지 값을 결정 
		// 1->0, 2->10, 3->20
		return (this.pageNum-1)*qty;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "PagingVO [pageNum=" + pageNum + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}
	
}
