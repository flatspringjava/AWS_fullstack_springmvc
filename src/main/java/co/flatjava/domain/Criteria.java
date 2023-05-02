package co.flatjava.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Criteria {
	private int pageNum = 1;
	private int amount = 10;
	private String type; // T C W TC TW CW TCW
	private String keyword;
	
	
	// 페이징 처리 메서드
	public int getOffset() {
		return (pageNum - 1) * amount;
	}
	
	public Criteria(int pageNum, int amount) {
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public String getQueryString() {
		return UriComponentsBuilder.fromPath("")
//		 		.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.build()
				.toUriString();
	}

	public String getFullQueryString() {
		return UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", pageNum)
				.queryParam("amount", amount)
				.queryParam("type", type)
				.queryParam("keyword", keyword)
				.build().toUriString();		
	}

	
	public String[] getTypeArr() {
		return type == null ? new String[]{} : type.split(""); // null 체크
	}


}
