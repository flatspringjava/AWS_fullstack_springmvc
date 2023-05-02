package co.flatjava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.flatjava.domain.BoardVO;
import co.flatjava.domain.Criteria;

public interface BoardMapper {
	// 목록 조회
	List<BoardVO> getList();
	
	// 목록 조회 페이징 처리
	List<BoardVO> getListWithPaging(Criteria criteria);		
	
	// 글 등록
	void insert(BoardVO vo);
	
	// 글 등록	
	void insertSelectKey(BoardVO vo);
	
	// 조회
	BoardVO read(Long bno);
	
	// 삭제
	int delete(Long bno);
	
	// 수정
	int update(BoardVO vo);
	
	// 갯수
	int getTotalCnt(Criteria cri);
	
	// 댓글 갯수 반영 파라미터가 2개이상이면 @Param 어노테이션을 이용해주자.
	int updateReplyCnt(@Param("bno") Long bno,@Param("amount") int amount);
}
