package controllers;
import dao.EmployeeDao;
import dto.UserDto;
import models.AppUser;
import ninja.Context;
import ninja.NinjaTest;
import ninja.Result;
import ninja.session.Session;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;





@RunWith(MockitoJUnitRunner.class)
public class EmpControllerTest extends NinjaTest {

    @Mock
    private EmployeeDao userDao;
    @Mock
    private Session session;
    @Mock
    private Context context;

    private EmpController empController;

    // this will be executed only once before the execution of all the test cases we can use @Before and @BeforeAll
    @Before
    public void setupTest(){
        empController = new EmpController();
        empController.userDao = userDao;

    }
    @Test
    public void createUser(){

        UserDto userDto = new UserDto();
        userDto.setEmail("sam@gmail.com");
        userDto.setName("HeySam");
        userDto.setPassword("HiHello");

        AppUser appUser = new AppUser();
        appUser.setEmail("sam@gmail.com");
        appUser.setName("HeySam");
        appUser.setPassword("HiHello");

        when(userDao.AddUser(userDto)).thenReturn(appUser);
        Result result = empController.CreateUser(userDto,session);
        assertEquals(200,result.getStatusCode());
//        System.out.println(result);
    }
    @Test
    public void count() throws Exception {

        // Stubbing passing data on our own
        when(userDao.countAllUsers()).thenReturn(10l);
        Result result = empController.countAllUsers();
        assertEquals(200,result.getStatusCode());
    }
    @Test
    public void count2() throws Exception {
        when(userDao.countAllUsers()).thenReturn(null);
        Result result = empController.countAllUsers();
        assertEquals(400,result.getStatusCode());
    }
    @Test
    public void validate(){
        String email = "Seller1@gmail.com";
        String password = "seller1@123";

        AppUser appUser = new AppUser();
        appUser.setEmail("Seller1@gmail.com");
        appUser.setName("Seller1");
        appUser.setPassword("seller1@123");


        when(userDao.validate(email,password)).thenReturn(appUser);
        Result result = empController.validateUser(email,password,session);
        assertEquals(200,result.getStatusCode());
    }


    @Test
    public void validate2(){
        when(userDao.validate(null,null)).thenReturn(null);
        Result result = empController.validateUser(null,null,session);
        assertEquals(400,result.getStatusCode());
    }

    @Test
    public void getUser() throws Exception {
        String email = "Seller1@gmail.com";


        AppUser appUser = new AppUser();
        appUser.setEmail("Seller1@gmail.com");
        appUser.setName("Seller1");
        appUser.setPassword("seller1@123");


        lenient().when(userDao.getUser(email)).thenReturn(appUser);
        when(context.getSession()).thenReturn(session);
        Result result = empController.getUser(context);
        assertEquals(200,result.getStatusCode());

    }



}
