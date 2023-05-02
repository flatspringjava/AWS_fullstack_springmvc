package co.flatjava.service;

import java.util.List;

import co.flatjava.domain.AttachFileDTO;
import co.flatjava.domain.BoardVO;
import co.flatjava.domain.Criteria;

public interface BoardService {
	// 등록
	void register(BoardVO vo);
	// 조회
	BoardVO get(Long bno);
	// 수정
	boolean modify(BoardVO vo);
	// 삭제
	boolean remove(Long bno);
	
	List<BoardVO> getList(Criteria cri);	
	// 조회수
	int getTotalCnt(Criteria cri);
	
	String deleteFile(AttachFileDTO dto);
	
}
