package ma.ilisi.userserice;

import ma.ilisi.userserice.Service.ClientService;
import ma.ilisi.userserice.dto.ChangePasswordDTO;
import ma.ilisi.userserice.dto.ClientDTO;
import ma.ilisi.userserice.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class ClientServiceTest {

    @Autowired
    private ClientService clientService;

    @Test
    void EmptyFields() throws Exception {
       ClientDTO client=new ClientDTO("","","testAddresse","060135448","","asma123");
       Assertions.assertEquals(0,clientService.Signup(client));
    }

    @Test
    void ClientAlreadyExist() throws Exception{
        ClientDTO client=new ClientDTO("asma","asma2","testAddresse","060135448","client1@gmail.com","asma123");
        Assertions.assertEquals(-1,clientService.Signup(client));
    }

    @Test
    void PasswordIncorrect() throws Exception{
        UserDTO user=new UserDTO("tes4@gmail.com","asma1234");
        Assertions.assertEquals(0,clientService.Login(user));
    }

    @Test
    void OldPasswordIncorrect() throws Exception{
        ChangePasswordDTO user=new ChangePasswordDTO("tes4@gmail.com","test123","test12");
        Assertions.assertEquals(-1,clientService.ChangePassword(user));
    }











}
