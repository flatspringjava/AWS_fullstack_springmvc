package co.flatjava.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.flatjava.domain.MemberVO;
import co.flatjava.mapper.MemberMapper;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MemberServiceImpl implements MemberService{

	private MemberMapper memberMapper;
	
//	@Override
//	public List<MemberVO> getList() {
//		return memberMapper.getList();
//	}

	@Override
	public MemberVO get(String id) {
		return memberMapper.read(id);
	}

//	@Override
//	public MemberVO get(MemberVO vo) {
//		return memberMapper.login(vo);
//	}
}
