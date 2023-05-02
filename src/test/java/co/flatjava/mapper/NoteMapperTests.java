package co.flatjava.mapper;

import java.lang.reflect.Member;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.flatjava.domain.MemberVO;
import co.flatjava.domain.NoteVO;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class NoteMapperTests {
	@Autowired
	private NoteMapper notemapper;
	@Autowired
	private MemberMapper memberMapper;
	
//	@Test
//	public void testInsert2() {
//		List<MemberVO> members = memberMapper.getList();
//		int i = 1;
//		for(MemberVO vo : members) {
//			for(MemberVO vo2 : members) {
//				NoteVO noteVO = new NoteVO();
//				noteVO.setSender(vo.getId());
//				noteVO.setReceiver(vo2.getId());
//				noteVO.setMessage("mapper 테스트 발송 " + i++);
//				notemapper.insert(noteVO);
//			}
//		}
//	}
	
	@Test
	public void testGetList() {
		
	}
	
	@Test
	public void testInsert() {
		NoteVO vo = new NoteVO();
		vo.setSender("id5");
		vo.setReceiver("id1");
		vo.setMessage("mapper Tests 발송");
		
		notemapper.insert(vo);
	}
	
	@Test
	public void testSelectOne() {
		log.info(notemapper.selectOne(21L));
	}
	
	@Test
	public void testUpdate() {
		notemapper.update(21L);
	}
	@Test
	public void testDelete() {
		notemapper.delete(22L);
	}
}
//@Insert("INTO tbl_note (noteno, sender, receiver, message) values(seq_note.nextval, #{sender}, #{receiver}, #{message})")
//int insert(NoteVO vo);
//
//@Select("select * from tbl_note where noteno = #{noteno}")
//NoteVO selectOne(Long noteno);
//
//@Update("update tbl_note set rdate = sysdate where noteno = #{noteno}")
//int update(Long noteno);	
//
//@Delete("delete from tbl_note where noteno = #{noteno}")
//int delete(Long noteno);