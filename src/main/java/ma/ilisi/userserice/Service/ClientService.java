package ma.ilisi.userserice.Service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;
import ma.ilisi.userserice.dto.ChangePasswordDTO;
import ma.ilisi.userserice.dto.ClientDTO;
import ma.ilisi.userserice.dto.UserDTO;
import ma.ilisi.userserice.Model.Client;
import ma.ilisi.userserice.Repository.ClientRepo;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Data
public class ClientService {

    private final ClientRepo clientRepo;

    public int Signup(ClientDTO client) {
        //Tester Si les champs ne  sont pas vides
        if (client.getLogin().isEmpty() || client.getF_name().isEmpty() || client.getL_name().isEmpty())
            return 0;

        ///Tester si le login existe deja
        if (!clientRepo.findByLogin(client.getLogin()).isEmpty())
            return -1;

        /// Hacher le mot de passe avant de l'enregistrer
        String hashedPassword = BCrypt.hashpw(client.getPassword(), BCrypt.gensalt());
        client.setPassword(hashedPassword);

        ///Ajout du client
        System.out.println("tester" + client.getF_name() + " " + client.getL_name());
        Client newclient = new Client(client.getF_name(), client.getF_name(), client.getAddress(), client.getPhone(), client.getLogin(), client.getPassword(),"client");
        clientRepo.save(newclient);
        return 1;
    }

    /*public int Login(UserDTO user) {
        Optional<Client> client = clientRepo.findByLogin(user.getLogin());

        //Si l user n'existe pas
        if (!client.isPresent()) return -1;

        //sinon si le mot de passe correct
        else if (client.get().getRole().equals("client") && BCrypt.checkpw(user.getPassword(), client.get().getPassword())) {
            return 1;
        }
       else if (client.get().getRole().equals("admin") && BCrypt.checkpw(user.getPassword(), client.get().getPassword())) {
            return 2;
        }

        ///Sinon mot de passe incorrect
        else return 0;

    }*/

    public  Optional<Client>  Login(UserDTO user) {
        Optional<Client> client = clientRepo.findByLogin(user.getLogin());


            //sinon si le mot de passe correct
        if (BCrypt.checkpw(user.getPassword(), client.get().getPassword())) {
            return client;
        }
        else
            return null;

        ///Sinon mot de passe incorrect


    }


    public int ChangePassword(ChangePasswordDTO user)
    {
        Optional<Client> c = clientRepo.findByLogin(user.getLogin());
        Client client = c.get();
     ///si l'ancien mot de passe est correcte
        if(BCrypt.checkpw(user.getOldPwd(),client.getPassword()))
        {
            String hashedPassword1 = BCrypt.hashpw(user.getNewPwd(), BCrypt.gensalt());
            client.setPassword(hashedPassword1);
            clientRepo.save(client);
            return 1;
        }

        ///sinon
        return -1;

    }

    public Optional<Client> GetByEmail(UserDTO user) {
       return  clientRepo.findByLogin(user.getLogin());
    }


    public int editProfile(ClientDTO user) {
        // Retrieve the existing client from the database
        Optional<Client> optionalClient = clientRepo.findByLogin(user.getLogin());

        if (optionalClient.isPresent()) {
            // Update the existing client's information with the new data
            Client existingClient = optionalClient.get();
            // Set all properties from the DTO to the existing client entity
            existingClient.setfName(user.getF_name());
            existingClient.setlName(user.getL_name());
            existingClient.setAddress(user.getAddress());
            existingClient.setPhone(user.getPhone());
            existingClient.setLogin(user.getLogin());
           // String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            existingClient.setPassword(user.getPassword());
            // Save the updated client information
            clientRepo.save(existingClient);
            return 1; // Indicate success
        } else {
            // Client with the given login not found
            return -1; // Indicate failure
        }
    }

    public Client getCandidatById(Long id) {
        return  clientRepo.getById(id);
    }
}
