package ma.ilisi.userserice.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Entity(name = "Clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString


public  class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String fName;

    @NonNull
    private String lName;

    private String address;
    private String phone;

    @NonNull
    private String login;

    @NonNull
    private String password;
    @NonNull
    private String role;

    @Nullable
    @ManyToMany(mappedBy = "participants")
    private List<Annonce> annonces;


    @Nullable
    @OneToMany(mappedBy = "vendeur")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Annonce> annoncesPublies;


    public Client(String fName, String lName, String address, String phone, String login, String password,String role)
    {
        this.fName=fName;
        this.lName=lName;
        this.address=address;
        this.phone=phone;
        this.login=login;
        this.password=password;
        this.role=role;

    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
