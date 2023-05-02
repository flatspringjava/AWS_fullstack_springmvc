package co.flatjava.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import co.flatjava.domain.NoteVO;

public interface NoteMapper {
	// CRUD
	
	@Insert("Insert INTO tbl_note (noteno, sender, receiver, message) values(seq_note.nextval, #{sender}, #{receiver}, #{message})")
	int insert(NoteVO vo);
	
	@Select("select * from tbl_note where noteno = #{noteno}")
	NoteVO selectOne(Long noteno);
	
	@Update("update tbl_note set rdate = sysdate where noteno = #{noteno}")
	int update(Long noteno);	
	
	@Delete("delete from tbl_note where noteno = #{noteno}")
	int delete(Long noteno);
	
	// 보낸거
	@Select("SELECT * FROM tbl_note WHERE noteno > 0 AND sender = #{sender} ORDER BY 1 DESC")
	List<NoteVO> sendList(String sender);
	
	// 받은거
	@Select("SELECT * FROM tbl_note WHERE noteno > 0 AND receiver = #{receiver} ORDER BY 1 DESC")
	List<NoteVO> receiveList(String receiver);
	
	// 받았는데 확인 안한거
	@Select("SELECT * FROM tbl_note WHERE noteno > 0 AND receiver = #{receiver} AND RDATE IS NULL ORDER BY 1 DESC")
	List<NoteVO> receiveUncheckedList(String receiver);
	
}
