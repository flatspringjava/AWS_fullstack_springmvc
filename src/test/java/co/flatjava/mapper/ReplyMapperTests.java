package co.flatjava.mapper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import co.flatjava.domain.BoardVO;
import co.flatjava.domain.Criteria;
import co.flatjava.domain.ReplyVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private ReplyMapper replyMapper;
	
	
	// 댓글 50개를 최근 게시글 다샛거에 10개씩 작성.
	@Test
	public void testCreate() {
//		boardMapper.getListWithPaging(new Criteria(1, 5)).forEach(log::info);
		
		List<BoardVO> boards = boardMapper.getListWithPaging(new Criteria(1, 5));
		boards.forEach(log::info);
		
		IntStream.rangeClosed(1, 50).forEach(i -> {
			ReplyVO vo = new ReplyVO();
			vo.setBno(boards.get(i%5).getBno());
			vo.setReply("댓글 테스트" + i);
			vo.setReplyer("tester" + i);
			
			replyMapper.insert(vo);
		});
	}
	
	@Test
	public void testRead() {
		Long rno = 5L;
		log.info(replyMapper.read(rno));
	}
	
	@Test
	public void testDelete() {
		Long rno = 5L;
		log.info(replyMapper.delete(rno));
	}
	
	
	@Test
	public void testUpdate() {
		ReplyVO vo = replyMapper.read(5L);
		vo.setReply("수정된 댓글");
		log.info(replyMapper.update(vo));
	}
	@Test
	public void testList() {
		replyMapper.getList(327210L, 5L).forEach(log::info);
	}
}
