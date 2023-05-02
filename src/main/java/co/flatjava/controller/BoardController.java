package co.flatjava.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.flatjava.domain.AttachVO;
import co.flatjava.domain.BoardVO;
import co.flatjava.domain.Criteria;
import co.flatjava.domain.PageDto;
import co.flatjava.domain.SampleVO;
import co.flatjava.service.BoardService;
import lombok.Data;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("board/*")
@Data
public class BoardController {
	private final BoardService boardService;

	@GetMapping("list") // board/list
	public void list(Model model, Criteria cri) {
		log.info("list()");
		model.addAttribute("list", boardService.getList(cri)); //
		model.addAttribute("page", new PageDto(boardService.getTotalCnt(cri), cri)); // 123은 페이지 총 개수, cri는 1페이지에 얼만큼의 게시글을 보여 줄지?
	}

	@GetMapping({"get", "modify"}) // board/get, modify void
	public void get(Model model, Long bno, @ModelAttribute("cri") Criteria cri) {
		log.info("get() or modify()");
		log.info(bno);
		model.addAttribute("cri", cri);
		model.addAttribute("board", boardService.get(bno));
	}

	@GetMapping("{bno}")
	public String getByPath(Model model, @PathVariable Long bno) {
		log.info("get() or modify()");
		log.info(bno);
		model.addAttribute("board", boardService.get(bno)); //
		return "board/get";
	}
	
	@GetMapping("register")
	@PreAuthorize("isAuthenticated()")
	public void register() {}
	
	@GetMapping("register2")
	public void register2() {}
	
	@PostMapping("register")
	@PreAuthorize("isAuthenticated()")
	public String register(BoardVO vo, RedirectAttributes rttr, Criteria cri) {
		log.info("register");
		log.info(vo);
		boardService.register(vo);
		rttr.addFlashAttribute("result", vo.getBno());
		rttr.addAttribute("pageNum", 1);
		rttr.addAttribute("amount", cri.getAmount());
		return "redirect:/board/list";
	}

	@PreAuthorize("isAuthenticated() and principal.username eq #vo.writer")
	@PostMapping("modify")
	public String modify(BoardVO vo, RedirectAttributes rttr, Criteria cri) {

		if (boardService.modify(vo)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list" + cri.getFullQueryString();
	}
	@PreAuthorize("isAuthenticated() and principal.username eq #writer")
	@PostMapping("remove")
	public String remove(String writer, RedirectAttributes rttr, Long bno, Criteria cri) {
		log.info("modify");
		log.info(bno);
		log.info(cri);
		List<AttachVO> list = boardService.get(bno).getAttachs();

		if (boardService.remove(bno)) {
			for(AttachVO vo : list) {
				boardService.deleteFile(vo);
			}
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list" + cri.getFullQueryString();
	}
	@GetMapping("getSample")
	public SampleVO getSample() {
		return new SampleVO(112, "스타", "로드");
	}
}
