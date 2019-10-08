package donnatto.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer userId;
    private String password;
    private String loginStatus = "Not Logged";
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate = LocalDate.now();

    public User(Integer userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public boolean verifyLogin(String pass) {
        if (pass.equals(this.password)) {
            return true;
        }
        return false;
    }
}
