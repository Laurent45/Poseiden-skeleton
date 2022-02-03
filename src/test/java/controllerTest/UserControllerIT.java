package controllerTest;

import com.nnk.springboot.controllers.UserController;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserController.class)
public class UserControllerIT {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @MockBean
    private UserRepository userRepository;
    @MockBean
    private BCryptPasswordEncoder encoder;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc =
                MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenHomeThenReturnViewUserAndAllUser() throws Exception {
        User user = new User(1
                , "admin"
                , "password"
                , "adminadmin"
                , "ADMIN");
        List<User> users = Arrays.asList(user);
        when(userRepository.findAll()).thenReturn(users);

        mockMvc.perform(get("/user/list"))
                .andExpect(view().name("user/list"))
                .andExpect(model().attributeExists("users"));
        verify(userRepository).findAll();
    }

    @Test
    public void whenAddUserFormThenReturnViewUserAdd() throws Exception {
        mockMvc.perform(get("/user/add"))
                .andExpect(view().name("user/add"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    public void whenValidateThenReturnFormUserAdd() throws Exception {
        User user = new User(null
                , "admin"
                , "password"
                , "adminadmin"
                , "ADMIN");
        when(userRepository.save(user)).thenReturn(user);
        when(encoder.encode(user.getPassword())).thenReturn("password");
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));

        mockMvc.perform(post("/user/validate")
                        .param("fullName", "adminadmin")
                        .param("userName", "admin")
                        .param("password", "password")
                        .param("role", "ADMIN"))
                .andExpect(view().name("redirect:/user/list"));
        verify(userRepository).save(user);
    }

    @Test
    public void whenGetShowUpdateFormThenReturnFormAndModel() throws Exception {
        User user = new User(1
                , "admin"
                , "password"
                , "adminadmin"
                , "ADMIN");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        mockMvc.perform(get("/user/update/{id}", 1))
                .andExpect(view().name("user/update"))
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("user/update"));
        verify(userRepository).findById(1);
    }

    @Test
    public void whenPostUpdateUserThenReturnUserList() throws Exception {
        User user = new User(1
                , "admin"
                , "password"
                , "adminadmin"
                , "ADMIN");
        when(encoder.encode(user.getPassword())).thenReturn("password");

        mockMvc.perform(post("/user/update/{id}", "1")
                        .param("fullName", "adminadmin")
                        .param("userName", "admin")
                        .param("password", "password")
                        .param("role", "ADMIN"))
                .andExpect(view().name("redirect:/user/list"))
                .andExpect(model().attributeExists("users"));
        verify(userRepository).save(user);
    }

    @Test
    public void whenGetDeleteBidThenReturnUserList() throws Exception {
        User user = new User(1
                , "admin"
                , "password"
                , "adminadmin"
                , "ADMIN");
        when(userRepository.findById(1)).thenReturn(Optional.of(user));
        mockMvc.perform(get("/user/delete/{id}", "1"))
                .andExpect(view().name("redirect:/user/list"));
        verify(userRepository).delete(user);
    }
}