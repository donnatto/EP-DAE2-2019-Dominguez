package donnatto.shopping.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends User {
    private String customerName;
    private String address;
    private String email;
    private String creditCardInfo;
    private double accountBalance = 0;

    public Customer(Integer userId, String password, String loginStatus, LocalDate registerDate, String customerName, String address, String email, String creditCardInfo, double accountBalance) {
        super(userId, password, loginStatus, registerDate);
        this.customerName = customerName;
        this.address = address;
        this.email = email;
        this.creditCardInfo = creditCardInfo;
        this.accountBalance = accountBalance;
    }

    public Customer(Integer userId, String password, String customerName, String address, String email, String creditCardInfo) {
        super(userId, password);
        this.customerName = customerName;
        this.address = address;
        this.email = email;
        this.creditCardInfo = creditCardInfo;
    }

    public boolean login(String password) {
        return this.verifyLogin(password);
    }


}
