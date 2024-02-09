package ma.ilisi.userserice.Controller;

import lombok.AllArgsConstructor;
import ma.ilisi.userserice.Model.Client;
import ma.ilisi.userserice.dto.ChangePasswordDTO;
import ma.ilisi.userserice.dto.ClientDTO;
import ma.ilisi.userserice.dto.UserDTO;
import ma.ilisi.userserice.Service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class Users {
    ClientService clientService;



   /* @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user){
       int result=clientService.Login(user);
       if(result==1)
           return ResponseEntity.ok("1");///login succes client
       if(result==2)
           return ResponseEntity.ok("2");///login succes admin

       if(result==-1)
           return ResponseEntity.ok("-1");///Login dosn't exist

        return ResponseEntity.ok("Password incorrect");
    }*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user){
        Optional<Client> result=clientService.Login(user);
        if (result != null) {
            // Authentification réussie
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            // Authentification échouée
            return new ResponseEntity<>("Email ou mot de passe incorrect.", HttpStatus.UNAUTHORIZED);
        }
    }





    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody ClientDTO client){

        int result=clientService.Signup(client);
        if(result ==-1)
            return ResponseEntity.badRequest().body("Login deja existe");
        if(result ==1)
            return ResponseEntity.ok("Sign up succefuly");
        return ResponseEntity.badRequest().body("Fields required");

    }

    @PostMapping("/changepwd")
    public ResponseEntity<String> ChangePassword(@RequestBody ChangePasswordDTO userA){

        int result=clientService.ChangePassword(userA);
        if(result==-1)
        return ResponseEntity.badRequest().body("Old password is incorrect");

        return ResponseEntity.ok("Password changed succefuly");
    }

    @PostMapping("/GetByEmail")
    public ResponseEntity<?> GetClientByEmail(@RequestBody UserDTO user)
    {
        Optional<Client> client = clientService.GetByEmail(user);

        if (client != null) {
            // Authentification réussie
            return new ResponseEntity<>(client, HttpStatus.OK);
        } else {
            // Authentification échouée
            return new ResponseEntity<>("Email ou mot de passe incorrect.", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/EditProfil")
    public ResponseEntity<?> EditProfile(@RequestBody ClientDTO user)
    {
       int result = clientService.editProfile(user);

        if (result != -1) {
            // Authentification réussie
            return new ResponseEntity<>("profile was sucessufuly changed", HttpStatus.OK);
        } else {
            // Authentification échouée
            return new ResponseEntity<>("Email ou mot de passe incorrect.", HttpStatus.UNAUTHORIZED);
        }
    }


    @GetMapping("/GetClient/{id}")
    public ClientDTO getClient(@PathVariable Long id) {
        Client client = clientService.getCandidatById(id);
        return mapToClientDTO(client);
    }

    private ClientDTO mapToClientDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setF_name(client.getFName());
        clientDTO.setL_name(client.getLName());
        clientDTO.setLogin(client.getLogin());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setAddress(client.getAddress());
        clientDTO.setPhone(client.getPhone());

        return clientDTO;
    }



}
