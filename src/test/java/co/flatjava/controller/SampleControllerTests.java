package co.flatjava.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import co.flatjava.domain.SampleVO;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Log4j
public class SampleControllerTests {
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void testExist() {
		log.info(context);
		log.info(mockMvc);
	}
	@Test
	public void testList() throws Exception {
		SampleVO sampleVO = new SampleVO(10, "길동", "홍");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(sampleVO);
		RequestBuilder builder = MockMvcRequestBuilders.get("/sample/sample").contentType(MediaType.APPLICATION_JSON);
		mockMvc.perform(builder);
//		ModelMap map = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap();
							 //perform이 끝나고 난 후 andReturn 호출 -> getModelAndView 호출 -> ModelMap 호출
//		log.info(map.get("list"));
		
//		List<?> list = (List<?>) map.get("list");
		// List의 참조는 모름(?) map.get("list");를 해야하나 list<?>로 형변환
		// Map<?, "list"> 했다고 볼 수 있음.
//		list.forEach(log::info);
		// 요청 주소에 대해 처리하는 테스트 로직. 
	}
}
