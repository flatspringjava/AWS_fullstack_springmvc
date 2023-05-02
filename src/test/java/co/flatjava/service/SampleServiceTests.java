package co.flatjava.service;

import static org.junit.Assert.assertNotNull;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
@Transactional(propagation=Propagation.REQUIRED)
public class SampleServiceTests {
	
	@Autowired
	private SampleService service;
	
	@Test
	public void testExist() {
		assertNotNull(service);
	}
	
	@Test
	public void testAddData() throws Exception {
		String data = "제주에 강풍과 함께 지역에 따라 많은 비가 내리고 있는데요. 제주공항도 강풍과 이른바 '양배풍' 때문에 항공기 운항이 차질을 빚고 있습니다.";
		log.info(data.length());
		byte[] bs = data.getBytes("utf-8");
		byte[] bs2 = new byte[50];
		System.arraycopy(bs, 0, bs2, 0, 50);
		log.info(bs.length);
		String str = new String(bs2, "utf-8");
		log.info(str);
//		service.addData(data);		
	}
}
