//import com.company.dao.impl.UserRepositoryCustom;
//import com.company.entity.Country;
//import com.company.entity.User;
//import com.company.service.impl.UserServiceImpl;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.springframework.test.context.event.annotation.BeforeTestClass;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class UserServiceTest {
//
//    //Mock ozunden bir obyekt yaradib test edir, eger ki real obyekt uzerunde islemek istesek Spy istifade ederik
//    @Mock
//    UserRepositoryCustom userDao;
//
//    @InjectMocks
//    UserServiceImpl userService;
//
//    @BeforeTestClass
//    public static void setUp() {
//        System.out.println("set up called");
//    }
//
//    @BeforeAll
//    public void before() {
//        System.out.println("before called");
//
//        MockitoAnnotations.initMocks(this);
//
//        List<User> list = new ArrayList<>();
//
//        User u = new User();
//        u.setName("test");
//        u.setSurname("test");
//        u.setEmail("test@test.com");
//        u.setNationality(new Country(1));
//
//        list.add(u);
//        Mockito.when(userDao.getAll(null, null, null)).thenReturn(list);
//        Mockito.when(userDao.getAll("test", "test", 1)).thenReturn(list);
//
//        Mockito.when(userDao.findByEmailAndPassword(null, null)).thenReturn(null);
//    }
//
//    @Test
//    public void testGivenNullThenGetAll() {
//        List<User> list = userService.getAll(null, null, null);
//
//        Assertions.assertEquals(1, list.size());
//
//        Mockito.verify(userDao, Mockito.atLeastOnce())
//                .getAll(null, null, null);
//    }
//
//    @Test
//    public void testGivenAllParamsThenGetAllByFilter() {
//        List<User> list = userService.getAll("test", "test", 1);
//
//        Assertions.assertTrue(list.size() > 0);
//
//        User user = list.get(0);
//
//        Assertions.assertEquals("test", user.getName());
//        Assertions.assertEquals("test", user.getSurname());
//        Assertions.assertEquals(1, user.getNationality());
//
//        Mockito.verify(userDao, Mockito.atLeastOnce())
//                .getAll("test", "test", 1);
//    }
//
//    @Test
//    public void testGivenNullFindByEmailAndPassword() {
//        User user = userService.findByEmailAndPassword(null, null);
//        Assertions.assertNull(user);
//
//        Mockito.verify(userDao, Mockito.atLeastOnce())
//                .findByEmailAndPassword(null, null);
//    }
//
//}
