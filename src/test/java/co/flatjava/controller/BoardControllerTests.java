package co.flatjava.controller;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
@Log4j
public class BoardControllerTests {
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
		RequestBuilder builder = MockMvcRequestBuilders.get("/board/list").param("amount", "20").param("pageNum", "5");
		ModelMap map = mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn().getModelAndView().getModelMap();
							 //perform이 끝나고 난 후 andReturn 호출 -> getModelAndView 호출 -> ModelMap 호출
//		log.info(map.get("list"));
		
		List<?> list = (List<?>) map.get("list");
		// List의 참조는 모름(?) map.get("list");를 해야하나 list<?>로 형변환
		// Map<?, "list"> 했다고 볼 수 있음.
		list.forEach(log::info);
		// 요청 주소에 대해 처리하는 테스트 로직. 
		// 	
	}
	
	@Test
	public void testRegister() throws Exception{
		RequestBuilder builder = 
				MockMvcRequestBuilders
				.post("/board/register")
				.param("title", "컨트롤러 테스트 제목")
				.param("content", "컨트롤러 테스트 내용")
				.param("writer", "컨트롤러 테스터");
		String result = mockMvc.perform(builder).andReturn().getModelAndView().getViewName();
		log.info(result);
		// 글 등록 후 목록으로 가는 로직.
	}
	@Test
	public void testGet() throws Exception{
		RequestBuilder builder = MockMvcRequestBuilders
				.get("/board/get")
				.param("bno", "501");
		ModelMap map =  mockMvc.perform(builder).andReturn().getModelAndView().getModelMap();
		log.info(map.get("board"));
		// 글 등록 후 목록으로 가는 로직.
	}
	@Test
	public void testModify() throws Exception{
		RequestBuilder builder = 
				MockMvcRequestBuilders
				.post("/board/modify")
				.param("bno", "7")
				.param("title", "컨트롤러 테스트 제목")
				.param("content", "컨트롤러 테스트 내용")
				.param("writer", "컨트롤러 테스터");
		String result = mockMvc.perform(builder).andReturn().getModelAndView().getViewName();
		log.info(result);
		// 글 등록 후 목록으로 가는 로직.
	}
	@Test
	public void testRemove() throws Exception{
		RequestBuilder builder = 
				MockMvcRequestBuilders
				.post("/board/remove")
				.param("bno", "8");
		String result = mockMvc.perform(builder).andReturn().getModelAndView().getViewName();
		log.info(result);
		// 글 등록 후 목록으로 가는 로직.
	}	
}
