package ma.ilisi.userserice.Model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity(name = "Annonces")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Annonce {

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String productName;

    private String description;

    @NonNull
    private  double prixDepart;


    @Temporal(TemporalType.DATE)
    private Date dateDeb; // Optional field with a default value

    @NonNull
    @Temporal(TemporalType.DATE)
    private Date dateFin; // Required field


    private String images;

    private double bestPrice;
    private AnnonceState statut;


    @ManyToOne
    private Category category;


    @ManyToOne
    private Client vendeur;



    @ManyToMany
    @JoinTable(
            name = "annonce_client",
            joinColumns = @JoinColumn(name = "annonce_id"),
            inverseJoinColumns = @JoinColumn(name = "client_id")
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Client> participants = new ArrayList<>();

    public Annonce(double price, Date dateD ,Date dateF, String name, String desc, Client vend)
    {
        prixDepart = bestPrice = price;
        dateDeb = dateD;
        dateFin = dateF;
        productName = name;
        description = desc;
        statut = AnnonceState.EN_ATTENTE;
        vendeur = vend;
    }


}
