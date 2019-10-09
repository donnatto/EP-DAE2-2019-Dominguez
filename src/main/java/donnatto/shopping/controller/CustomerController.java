package donnatto.shopping.controller;

import donnatto.shopping.model.Customer;
import donnatto.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping("/customers/add")
    public String addCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "customer-add";
    }

    @PostMapping("/customers/save")
    public String saveCustomer(Customer customer) {
        customerService.register(customer);

        return "redirect:/customers";
    }

    @GetMapping("/customers/login/{userId}")
    public String loginCustomer(@PathVariable Integer userId, Model model) {
        Customer currentCustomer = customerService.findById(userId);
        model.addAttribute("customer", currentCustomer);
        return "login";
    }

    @GetMapping("/customers/logout/{userId}")
    public String logoutCustomer(@PathVariable Integer userId) {
        Customer currentCustomer = customerService.findById(userId);
        currentCustomer.setLoginStatus("Not Logged");
        return "redirect:/customers";
    }

    @PostMapping("/customers/login/{userEmail}")
    public String validLogin(@PathVariable String userEmail, Customer customer) {
        Customer currentCustomer = customerService.findByEmail(userEmail);
        if (customerService.login(currentCustomer.getEmail(), customer.getPassword())) {
            currentCustomer.setLoginStatus("Logged in");
        }
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{userId}")
    public String getCustomerForUpdate(@PathVariable Integer userId, Model model) {
        Customer currentCustomer = customerService.findById(userId);
        model.addAttribute("customer", currentCustomer);
        return "customer-edit";
    }

    @PostMapping("/customers/update/{userId}")
    public String updateCustomer(@PathVariable Integer userId, Customer customer) {
        customerService.update(customer);

        return "redirect:/customers";
    }

    @GetMapping("/customers")
    public String getCustomersList(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

}
