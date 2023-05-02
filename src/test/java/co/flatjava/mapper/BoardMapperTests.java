package co.flatjava.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.flatjava.domain.BoardVO;
import co.flatjava.domain.Criteria;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testGetList() {
//		boardMapper.getList().forEach(log::info);
	}
	
	
	@Test
	public void testGetListWithPaging() {
		boardMapper.getListWithPaging(new Criteria(3, 10, "TW", "1")).forEach(log::info);
	}
	
	//댓글 총 개수는 int type 이어야 한다.
	@Test
	public void testGetTotalCnt() {
		log.info(boardMapper.getTotalCnt(new Criteria(1, 10, "T", "1")));
	}
	
	@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트 코드 작성 insertSelectKey() 제목");
		vo.setContent("테스트 코드 작성 insertSelectKey() 내용");
		vo.setWriter("작성자");
		boardMapper.insertSelectKey(vo);
		log.info(vo);
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("테스트 코드 작성 insertSelectKey() 제목");
		vo.setContent("테스트 코드 작성 insertSelectKey() 내용");
		vo.setWriter("작성자");
		boardMapper.insertSelectKey(vo);
		log.info(vo);
	}
	
	@Test
	public void testRead() {
		Long bno = 327317L;
		log.info(boardMapper.read(bno));
	}
	
	@Test
	public void testDelete() {
		Long bno = 327317L;
		log.info(boardMapper.read(bno));
		log.info(boardMapper.delete(bno));
		log.info(boardMapper.read(bno));
	}
	
	@Test
	public void testUpdate() {
		BoardVO vo = boardMapper.read(6L); //3번글 호출
		vo.setTitle("수정된 제목"); //setter로 수정.
		vo.setContent("수정된 제목");
		vo.setWriter("user00");
		
		log.info(vo);
		boardMapper.update(vo);
		
		BoardVO vo2 = boardMapper.read(6L);
		vo = boardMapper.read(3L);
		log.info(vo);
		
		assertSame(vo, vo2);
	}
}
