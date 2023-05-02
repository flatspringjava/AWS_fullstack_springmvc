package co.flatjava.service;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.flatjava.controller.UploadController;
import co.flatjava.domain.AttachFileDTO;
import co.flatjava.domain.AttachVO;
import co.flatjava.domain.BoardVO;
import co.flatjava.domain.Criteria;
import co.flatjava.mapper.AttachMapper;
import co.flatjava.mapper.BoardMapper;
import co.flatjava.mapper.ReplyMapper;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@ToString
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{
//	@Setter
//	@Autowired
	private final BoardMapper boardMapper;
	private final AttachMapper attachMapper;
	private ReplyMapper replyMapper;
	
	@Override
	@Transactional
	public void register(BoardVO vo) {		
		boardMapper.insertSelectKey(vo);
		Long bno = vo.getBno();
		
		List<AttachVO> list = vo.getAttachs();
		int idx = 0;
		for(AttachVO attach : list) {
			attach.setBno(bno);
			attach.setOdr(idx++);
			attachMapper.insert(attach);
		}
	}	
	
	@Override
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}

	@Override
	@Transactional
	public boolean modify(BoardVO vo) {
		attachMapper.deleteAll(vo.getBno());
		
		List<AttachVO> list = vo.getAttachs();
		int idx = 0;
		for(AttachVO attach : list) {
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		}
		return boardMapper.update(vo) > 0;
	}

	@Override
	@Transactional
	public boolean remove(Long bno) {

		log.info(" remove test1 :" + bno);
		replyMapper.deleteByBno(bno);
		log.info(" remove test2 :" + bno);
		attachMapper.deleteAll(bno);
		log.info(" remove test3 :" + bno);
		return boardMapper.delete(bno) > 0;
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return boardMapper.getListWithPaging(cri);
	}
	
	@Override
	public int getTotalCnt(Criteria cri) {
		return boardMapper.getTotalCnt(cri);
	}

	@Override
	public String deleteFile(AttachFileDTO dto) {
		log.info(dto);
		
		String s = UploadController.getPATH() + "/" + dto.getPath() + "/" + dto.getUuid() + "_" + dto.getName();
		
		File file = new File(s);
		file.delete();
		if(dto.isImage()) {
			s = UploadController.getPATH() + "/" + dto.getPath() + "/s_" + dto.getUuid() + "_" + dto.getName();
			file = new File(s);
			file.delete();
		} 
		return dto.getUuid();
	}		
	
}
