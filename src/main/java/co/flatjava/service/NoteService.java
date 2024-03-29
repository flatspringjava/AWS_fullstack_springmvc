package co.flatjava.service;

import java.util.List;

import co.flatjava.domain.NoteVO;

public interface NoteService {
	
	int send(NoteVO vo);
	
	NoteVO get(Long noteno);
	
	int receive(Long noteno);
	
	int remove(Long noteno);
	
	List<NoteVO> getSendList(String id);
	
	List<NoteVO> getReceiveList(String id);
	
	List<NoteVO> getReceiveUncheckedList(String id);
}
