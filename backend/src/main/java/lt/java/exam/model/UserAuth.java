package lt.java.exam.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class UserAuth {
    public String token;
    public User user;
}
