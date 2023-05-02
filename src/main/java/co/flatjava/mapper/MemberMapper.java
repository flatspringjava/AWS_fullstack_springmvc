package co.flatjava.mapper;

import co.flatjava.domain.MemberVO;

public interface MemberMapper {
	public MemberVO read(String userid);
}
