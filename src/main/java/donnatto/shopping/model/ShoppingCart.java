package donnatto.shopping.model;

import com.sun.javafx.beans.IDProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart {
    private String cartId;
    private String productId;
    private int quantity;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAdd = new Date();


}
