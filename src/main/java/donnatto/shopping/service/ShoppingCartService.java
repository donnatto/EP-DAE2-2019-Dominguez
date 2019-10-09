package donnatto.shopping.service;

import donnatto.shopping.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShoppingCartService implements GenericService<ShoppingCart, String> {
    List<ShoppingCart> cart = new ArrayList<>();


    @Override
    public List<ShoppingCart> getAll() {
        return cart;
    }

    @Override
    public void register(ShoppingCart shoppingCart) {
        cart.add(shoppingCart);
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        ShoppingCart currentShoppingCart = findById(shoppingCart.getCartId());
        if (currentShoppingCart != null) {
            currentShoppingCart.setQuantity(shoppingCart.getQuantity());
        }
    }

    @Override
    public ShoppingCart findById(String cartId) {
        return cart.stream().filter(a -> a.getCartId().equals(cartId)).findFirst().orElse(null);
    }

}
