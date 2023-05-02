package co.flatjava.domain;

import lombok.Data;

@Data
public class PageDto {
	
	// 하단에 출력 된 페이지 사이즈
    private	int pageCount = 10;
	
	// 시작 페이지 숫자
    private int startPage;
		
	// 종료 페이지 숫자
    private int endPage;
	
	// 게시글 총 갯수
    private int total;
	
	// next, prev
    private boolean prev;
    private boolean next;
	
    private boolean doubleNext;
    private boolean doublePrev;
    
	// Criteria
    private Criteria cri;

	public PageDto(int total, Criteria cri) {		
		this(10, total, cri);
	}

	public PageDto(int pageCount, int total, Criteria cri) {		
		this.pageCount = pageCount;
		this.total = total;		
		this.cri = cri;		
		
		endPage = (cri.getPageNum() + (pageCount-1)) / pageCount * pageCount; // 마지막 번호의 값은 
		startPage = endPage - (pageCount-1);
		int realEnd = (total + (cri.getAmount() - 1)) / cri.getAmount(); //1페이지에 10개씩 보여주려면 +9 해야함.
		System.out.println(realEnd);									 //즉 cri.getAmount로 총 게시글에 -1을 해주면 123일 경우 2페이지에 담으려면 +99가 되도록 했음.
		
		if(endPage > realEnd) {
			endPage = realEnd;
		}
		prev = cri.getPageNum() > 1;
		next = cri.getPageNum() < realEnd;
		
		doublePrev = startPage > 1;
		doubleNext = endPage < realEnd;
	}	
	
	
	// 예정 <<, >>
    
}
