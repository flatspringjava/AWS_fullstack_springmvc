package co.flatjava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import co.flatjava.domain.ReplyVO;

public interface ReplyMapper {
	
	// 댓글 작성
	int insert(ReplyVO vo);
	
	// 댓글 조회 및 읽기
	ReplyVO read(Long rno);
		
	List<ReplyVO> getList(@Param("bno") Long bno, @Param("rno") Long rno);
	
	int update(ReplyVO vo);
	
	int delete(Long rno);
	
	int deleteByBno(Long bno);
}
