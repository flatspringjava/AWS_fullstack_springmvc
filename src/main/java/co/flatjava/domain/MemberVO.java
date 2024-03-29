package co.flatjava.domain;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Alias("member")
@Data
public class MemberVO {
	private String userid;
	private String userpw;
	private String userName;
	private Boolean enabled;
	
	private Date regDate;
	private Date updateDate;
	
	private List<AuthVO> auths;
}
