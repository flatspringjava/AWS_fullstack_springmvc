package co.flatjava.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.flatjava.domain.ReplyVO;
import co.flatjava.service.ReplyService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("replies")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	private ReplyService replyService;
	
	// replies/list/{bno}
	// replies/list/{bno}/{rno}
	
	@GetMapping({"list/{bno}", "list/{bno}/{rno}"})
	public List<ReplyVO> getList(@PathVariable Long bno, @PathVariable(required=false) Optional<Long> rno) {
		log.info(bno);
		log.info(rno.orElse(0L));
//		log.info(rno.get());
		return replyService.getList(bno, rno.orElse(0L));
	}
	
	@PostMapping("new")
	@PreAuthorize("isAuthenticated()")
	public Long create(@RequestBody ReplyVO vo) {
		log.info(vo);
		replyService.register(vo);
		return vo.getRno();
	}
	
	@GetMapping("{rno}")
	public ReplyVO get(@PathVariable Long rno) {
		log.info(rno);
		return replyService.get(rno);
	}
	
	@DeleteMapping("{rno}")
	@PreAuthorize("isAuthenticated() and principal.username eq #vo.replyer")
	public int remove(@PathVariable Long rno, @RequestBody ReplyVO vo){
		log.info(rno);
		return replyService.remove(rno);
	}
	
	// rno는 경로를 알아보기 위함이며 vo는 수정을 위해 필요한 파라미터 값
	@PutMapping("{rno}")
	@PreAuthorize("isAuthenticated() and principal.username eq #vo.replyer")
	public int modify(@PathVariable Long rno, @RequestBody ReplyVO vo) {
//		log.info(rno);
		return replyService.modify(vo);
	}
}
