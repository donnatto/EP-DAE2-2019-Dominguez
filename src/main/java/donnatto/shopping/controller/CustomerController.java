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
    public String saveCustomer(Customer customer, Model model) {
        customerService.register(customer);

        return "redirect:/customers";
    }

    @GetMapping("/customers/login/{userId}")
    public String loginCustomer(@PathVariable Integer userId, Model model) {
        Customer currentCustomer = customerService.findById(userId);
        model.addAttribute("customer", currentCustomer);
        return "login";
    }

    @PostMapping("/customers/login/{userEmail}")
    public String validLogin(@PathVariable String userEmail, Customer customer) {
        Customer currentCustomer = customerService.findByEmail(userEmail);
        if (customerService.login(currentCustomer.getEmail(), customer.getPassword())) {
            currentCustomer.setLoginStatus("Logged");
        }
        return "redirect:/customers";
    }

    @GetMapping("/customers/edit/{userId}")
    public String getCustomerForUpdate(@PathVariable Integer userId, Model model) {
        Customer currentCustomer = customerService.findById(userId);
        model.addAttribute("customer", currentCustomer);
        return "customer-edit";
    }

//    @GetMapping("/customers/addauthor/{isbn}")
//    public String getBookForAuthorSave(@PathVariable String isbn, Model model) {
//        Book currentBook = bookService.findById(isbn);
//        model.addAttribute("book", currentBook);
//        List<Author> authors = authorService.getAll();
//        model.addAttribute("authors", authors);
//        return "book-add-author";
//    }

//    @PostMapping("/customer/saveauthor/{isbn}")
//    public String saveAuthor(@PathVariable String isbn, Book book, Model model) {
//        bookService.update(book);
//        return "redirect:/books";
//    }

    @PostMapping("/customers/update/{userId}")
    public String updateCustomer(@PathVariable Integer userId, Customer customer, Model model) {
        customerService.update(customer);

        return "redirect:/customers";
    }

    @GetMapping("/customers")
    public String getCustomersList(Model model) {
        List<Customer> customers = customerService.getAll();
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/customers/delete/{userId}")
    public String deleteBook(@PathVariable Integer userId, Model model) {
        Customer currentCustomer = customerService.findById(userId);
        if (currentCustomer != null) {
            customerService.delete(currentCustomer);
        }

        return "redirect:/customers";
    }

}
