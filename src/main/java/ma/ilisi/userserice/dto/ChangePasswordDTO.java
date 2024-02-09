package ma.ilisi.userserice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ChangePasswordDTO {
    private String login;
    private String oldPwd;
    private String newPwd;

}
