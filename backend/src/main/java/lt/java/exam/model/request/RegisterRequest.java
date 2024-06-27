package lt.java.exam.model.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterRequest {
    public String email;
    public String password;
    public String passwordRepeat;
}
