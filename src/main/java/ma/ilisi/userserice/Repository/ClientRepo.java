package ma.ilisi.userserice.Repository;

import ma.ilisi.userserice.Model.Client;
import ma.ilisi.userserice.dto.ClientDTO;
import ma.ilisi.userserice.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@CrossOrigin("*")
@RestResource
public interface ClientRepo extends JpaRepository<Client, Long> {
    @Query("SELECT c FROM Clients c WHERE c.login = :login")
    Optional<Client> findByLogin(@Param("login") String login);



}
