package com.project02;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
//import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import com.project02.model.User;
import com.project02.model.WatchList;
import com.project02.repository.UserRepository;
import com.project02.repository.WatchListRepository;
import com.project02.service.UserService;
import com.project02.service.WatchListService;
import com.project02.web.UserController;
import com.project02.web.WatchListController;

//@ContextConfiguration(locations="classpath:testContext.xml")
/*
 * This denotes that our application context is a Web Application Context.
 */
@WebAppConfiguration

/*
 * When using JUnit, you can specify a test runner. This test runner is a class
 * that defines how your tests will be run. Spring has its own JUnit test runner,
 * which is called the SpringJUnit4ClassRunner. We can use the @RunWith annotation
 * to denote that we want the tests in this class to be run using a specific test
 * runner.
 */
@RunWith(SpringJUnit4ClassRunner.class)



@SpringBootTest
class Project02ApplicationTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	RestTemplate restTemplate;
	
	@Mock
	private static UserService userService;
	
	@Mock
	private static WatchListService watchListService;
	
	@Mock
	private static UserRepository userRepo;
	
	@Mock
	private static WatchListRepository watchListRepo;
	
	@InjectMocks
	private UserController userController;
	
	@InjectMocks
	private WatchListController watchListController;
	
	private MockMvc mockMvc;
	private MockMvc mockMvc2;
	
	private User user;
	private WatchList watchList;
	
	private List<User> userList;
	private List<WatchList> wlList;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
		
		user = new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me");
		userList = new ArrayList<>();
		
		userList.add(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		userList.add(new User(2, "UsernameFake2", "FakePassword2", "NameFull2", "This my about me2"));
		
		mockMvc2 = MockMvcBuilders.standaloneSetup(watchListController).build();
		
		wlList = new ArrayList<>();
		
		wlList.add(new WatchList(1, "Drive", "2011", "I like retro music", 5, true, "imageurl1", userList.get(1)));
		wlList.add(new WatchList(1, "Drive", "2011", "I like retro music", 5, true, "imageurl1", userList.get(2)));
		
	}
	
	@Test
	public void testGetAllUsers() {
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		try {
			mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllWatchLists() {
		Mockito.when(watchListService.getAllWatchlists()).thenReturn(wlList);
		
		try {
			mockMvc2.perform(get("/watchlist/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUsername() {
		Mockito.when(userService.getUserByUsername("UsernameFake")).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/username/UsernameFake").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUserid() {
		Mockito.when(userService.getUserByUserid(1)).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/id/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testCreateEmployee()
	{
		User user3 = new User(3, "UsernameFake3", "FakePassword3", "NameFull3", "This my about me3");
		
		userService.createUser(user3);
		
		verify(userService, times(1)).createUser(user3);
	}
	
	@Test
	public void testGetUserByUsernameNotNull() {
		Mockito.when(userService.getUserByUsername("UsernameFake")).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/username/UsernameFake").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotNull(Project02ApplicationTests.userService.getUserByUsername("UsernameFake"));
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUseridNotNull() {
		Mockito.when(userService.getUserByUserid(1)).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/id/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotNull(Project02ApplicationTests.userService.getUserByUserid(1));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllUsersNotNull() {
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		try {
			mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotNull(Project02ApplicationTests.userService.getAllUsers());

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllWatchListNotNull() {
		Mockito.when(watchListService.getAllWatchlists()).thenReturn(wlList);
		
		try {
			mockMvc.perform(get("/watchlist/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotNull(Project02ApplicationTests.watchListService.getAllWatchlists());

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllUsersSame() {
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		try {
			mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertSame(Project02ApplicationTests.userService.getAllUsers(), userList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllWatchListSame() {
		Mockito.when(watchListService.getAllWatchlists()).thenReturn(wlList);
		
		try {
			mockMvc.perform(get("/watchlist/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertSame(Project02ApplicationTests.watchListService.getAllWatchlists(), wlList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUsernameSame() {
		Mockito.when(userService.getUserByUsername("UsernameFake")).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/username/UsernameFake").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertSame(Project02ApplicationTests.userService.getUserByUsername("UsernameFake"), "UsernameFake");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUseridSame() {
		Mockito.when(userService.getUserByUserid(1)).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/id/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertSame(Project02ApplicationTests.userService.getUserByUserid(1), new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllUsersNotSame() {
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		try {
			mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotSame(Project02ApplicationTests.userService.getAllUsers(), userList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllWatchListNotSame() {
		Mockito.when(watchListService.getAllWatchlists()).thenReturn(wlList);
		
		try {
			mockMvc.perform(get("/watchlist/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotSame(Project02ApplicationTests.watchListService.getAllWatchlists(), wlList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUsernameNotSame() {
		Mockito.when(userService.getUserByUsername("UsernameFake")).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/username/UsernameFake").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotSame(Project02ApplicationTests.userService.getUserByUsername("UsernameFake"), "UsernameFake");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUseridNotSame() {
		Mockito.when(userService.getUserByUserid(1)).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/id/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotSame(Project02ApplicationTests.userService.getUserByUserid(1), new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllUsersEquals() {
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		try {
			mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertEquals(Project02ApplicationTests.userService.getAllUsers(), userList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllWatchListEquals() {
		Mockito.when(watchListService.getAllWatchlists()).thenReturn(wlList);
		
		try {
			mockMvc.perform(get("/watchlist/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertEquals(Project02ApplicationTests.watchListService.getAllWatchlists(), wlList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUsernameEquals() {
		Mockito.when(userService.getUserByUsername("UsernameFake")).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/username/UsernameFake").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertEquals(Project02ApplicationTests.userService.getUserByUsername("UsernameFake"), "UsernameFake");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUseridEquals() {
		Mockito.when(userService.getUserByUserid(1)).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/id/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertEquals(Project02ApplicationTests.userService.getUserByUserid(1), new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllUsersNotEquals() {
		Mockito.when(userService.getAllUsers()).thenReturn(userList);
		
		try {
			mockMvc.perform(get("/user/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotEquals(Project02ApplicationTests.userService.getAllUsers(), userList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetAllWatchListNotEquals() {
		Mockito.when(watchListService.getAllWatchlists()).thenReturn(wlList);
		
		try {
			mockMvc.perform(get("/watchlist/all").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotEquals(Project02ApplicationTests.watchListService.getAllWatchlists(), wlList);

		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUsernameNotEquals() {
		Mockito.when(userService.getUserByUsername("UsernameFake")).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/username/UsernameFake").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotEquals(Project02ApplicationTests.userService.getUserByUsername("UsernameFake"), "UsernameFake");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetUserByUseridNotEquals() {
		Mockito.when(userService.getUserByUserid(1)).thenReturn(new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));
		
		try {
			mockMvc.perform(get("/user/id/1").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
			.andDo(print()).andReturn();
			Assert.assertNotEquals(Project02ApplicationTests.userService.getUserByUserid(1), new User(1, "UsernameFake", "FakePassword", "NameFull", "This my about me"));

		}catch(Exception e) {
			e.printStackTrace();
		}
	}


}
