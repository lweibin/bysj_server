package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import service.TestJpaService;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import common.TimeAssistant;
import dao.SheetDao;
import dto.RegisterDTO;
import entity.Sheet;
import entity.User;

@Controller
@RequestMapping("testJpa")
public class TestJPA {
	ObjectMapper mapper = new ObjectMapper();

	@RequestMapping("test1")
	public @ResponseBody String test1() {
		EntityManagerFactory factory = Persistence
				.createEntityManagerFactory("eclipselink");
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		User user = new User();
		user.setType("MTN");
		user.setName("test16");
		user.setPsw("123");
		user.setPhone("10086");
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		// em.getTransaction().begin();
		// for (int i = 0; i < 10; i++) {
		// Customer customer = new Customer();
		// customer.setName("customer" + i);
		// customer.setEmail("customer" + i + "@my.com");
		// customer.setBirthday(Calendar.getInstance().getTime());
		//
		// Address addressHome = new Address();
		// addressHome.setName("Home");
		// addressHome.setDescription("Home");
		// addressHome.setZipcode("123456");
		// em.persist(addressHome);
		//
		// Address addressOffice = new Address();
		// addressOffice.setName("Office");
		// addressOffice.setDescription("Office");
		// addressOffice.setZipcode("654321");
		// em.persist(addressOffice);
		//
		// customer.addAddress(addressHome);
		// customer.addAddress(addressOffice);
		// em.persist(customer);
		// }
		// em.getTransaction().commit();
		// em.close();
		//
		// em = factory.createEntityManager();
		// TypedQuery<Customer> q = em.createQuery("select c from Customer c",
		// Customer.class);
		// List<Customer> customers = q.getResultList();
		// for(Customer c: customers) {
		// System.out.println(c);
		// }
		// em.close();

		return "success";
	}

	@RequestMapping("/test")
	public @ResponseBody String testUrl(@RequestParam String user)
			throws JsonParseException, JsonMappingException, IOException {
		RegisterDTO dto = this.mapper.readValue(user, RegisterDTO.class);
		return "success";
	}

	@RequestMapping("/test2/{id}")
	public @ResponseBody String testUrl2(@PathVariable String id) {
		return id;
	}

	@RequestMapping("select")
	public @ResponseBody String select() {
		TestJpaService service = new TestJpaService();
		service.findAll();
		return "success";
	}

	@RequestMapping("insert")
	public @ResponseBody String inset() {
		TestJpaService service = new TestJpaService();
		service.insettRecord();
		return "success";
	}

	@RequestMapping("update")
	public @ResponseBody String update() throws Exception {
		TestJpaService service = new TestJpaService();
		service.updateRecord();
		return "success";
	}

	@RequestMapping("testSheet")
	public @ResponseBody String testSheet() {
		SheetDao dao = new SheetDao();
		List<Sheet> sheet = dao.findAll();
		return "";
	}

	@RequestMapping("getSession")
	public @ResponseBody String getSession(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession(false);
		return "";
	}

	// not test
	@RequestMapping("getImgList")
	public @ResponseBody String getImgList(
			@RequestParam("imgfile") List<MultipartFile> fileList,
			HttpServletRequest request) throws IOException {
		return "";
	}

	@RequestMapping("testMap")
	public @ResponseBody String testMap() throws JsonProcessingException {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("a", 1);
		map.put("b", "world");
		return this.mapper.writeValueAsString(map);

	}

	@RequestMapping("testTimeFormat")
	public @ResponseBody String testTimeFormat() throws ParseException {
		DateFormat df1 = new SimpleDateFormat(TimeAssistant.FONT_TIME_FORMAT);
		DateFormat df2 = new SimpleDateFormat(TimeAssistant.BACKEND_TIME_FORMAT);
		Date date = df2.parse("20160822111111");
		// Date date = df.parse(df.format(nowDate));
		return df1.format(date).toString();
	}

}
