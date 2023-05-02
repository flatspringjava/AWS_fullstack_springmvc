package co.flatjava.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.flatjava.domain.NoteVO;
import co.flatjava.service.NoteService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RestController
@Log4j
@RequestMapping("note")
@AllArgsConstructor
public class NoteController {
	private NoteService service;
	
	// 발송
	@PostMapping("new")
	public int send(@RequestBody NoteVO vo) {
		log.info(vo);
		return service.send(vo);
	}	
	@GetMapping("{noteno}")
	public NoteVO getNote(@PathVariable Long noteno) {
		log.info(noteno);
		return service.get(noteno);
	}
	@PutMapping("{noteno}")
	public int receive(@PathVariable Long noteno) {
		log.info(noteno);
		return service.receive(noteno);
	}
	@DeleteMapping("{noteno}")
	public int remove(@PathVariable Long noteno) {
		log.info(noteno);
		return service.remove(noteno);
	}
	
	@GetMapping("s/{id}")
	public List<NoteVO> getSendList(@PathVariable String id) {
		log.info(id);
		return service.getSendList(id);
	}
	@GetMapping("r/{id}")
	public List<NoteVO> getRecieveList(@PathVariable String id) {
		log.info(id);
		return service.getReceiveList(id);
	}
	@GetMapping("r/c/{id}")
	public List<NoteVO> getRecieveCheckedList(@PathVariable String id) {
		log.info(id);
		return service.getReceiveUncheckedList(id);
	}
}
