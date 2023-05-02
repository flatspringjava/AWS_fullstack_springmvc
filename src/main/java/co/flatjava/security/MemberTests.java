package co.flatjava.security;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/security-context.xml", 
	"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@Log4j
public class MemberTests {
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void testInsert() throws SQLException {
		String sql = "insert into tbl_member(userid, userpw, username) values(?, ?, ?)";
		
		for(int i = 0; i<100; i++) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, encoder.encode("pw" + i));
			
			if(i < 80) {
				pstmt.setString(1, "user"+i);
				pstmt.setString(3, "일반 사용자"+i);
			} else if(i < 90) {
				pstmt.setString(1, "manager"+i);
				pstmt.setString(3, "운영자"+i);
			} else {
				pstmt.setString(1, "admin"+i);
				pstmt.setString(3, "관리자"+i);
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
	}	
	
	@Test
	public void testInsertAuth() throws SQLException {
		String sql = "insert into tbl_auth values(?, ?)";
		
		for(int i = 0; i<100; i++) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			
			conn = dataSource.getConnection();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(2, encoder.encode("pw" + i));
			
			if(i < 80) {
				pstmt.setString(1, "user"+i);
				pstmt.setString(2, "ROLE_USER");
			} else if(i < 90) {
				pstmt.setString(1, "manager"+i);
				pstmt.setString(2, "ROLE_MANAGER");
			} else {
				pstmt.setString(1, "admin"+i);
				pstmt.setString(2, "ROLE_ADMIN");
			}
			pstmt.executeUpdate();
			pstmt.close();
			conn.close();
		}
	}
}
