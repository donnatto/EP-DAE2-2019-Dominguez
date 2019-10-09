package donnatto.shopping.controller;

import donnatto.shopping.model.ShoppingCart;
import donnatto.shopping.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/shoppingcart")
    public String getCartList(Model model) {
        List<ShoppingCart> cart = shoppingCartService.getAll();
        model.addAttribute("cart", cart);
        return "shoppingcart";
    }

    @GetMapping("/shoppingcart/add")
    public String addProduct(Model model) {
        model.addAttribute("shoppingCart", new ShoppingCart());
        return "shoppingcart-add";
    }

    @PostMapping("/shoppingcart/save")
    public String saveProduct(ShoppingCart shoppingCart) {
        shoppingCartService.register(shoppingCart);
        return "redirect:/shoppingcart";
    }

    @GetMapping("/shoppingcart/edit/{cartId}")
    public String getProductforUpdate(@PathVariable String cartId, Model model) {
        ShoppingCart currentCart = shoppingCartService.findById(cartId);
        model.addAttribute("shoppingCart", currentCart);
        return "shoppingcart-edit";
    }

    @PostMapping("/shoppingcart/edit/{cartId}")
    public String updateProduct(@PathVariable String cartId, ShoppingCart shoppingCart) {
        shoppingCartService.update(shoppingCart);

        return "redirect:/shoppingcart";
    }
}
