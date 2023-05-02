package co.flatjava.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import co.flatjava.domain.CustomUser;
import co.flatjava.domain.MemberVO;
import co.flatjava.mapper.MemberMapper;
import lombok.extern.log4j.Log4j;

@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private MemberMapper mapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn(String.format("CustomUserDetailsServiceloadUserByUsername(%s)", username));
		MemberVO vo = mapper.read(username);
		// null 체크 : vo가 null이면 null값, 아니면 vo 생성
		return vo == null ? null : new CustomUser(vo);
	}
}
