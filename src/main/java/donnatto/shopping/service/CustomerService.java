package donnatto.shopping.service;

import donnatto.shopping.model.Customer;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CustomerService implements GenericService<Customer, Integer, String> {
    List<Customer> customers = new ArrayList<>(
            Arrays.asList(
                    new Customer(1, "1234", "Not Logged", LocalDate.now(),"Edwin", "Lima 123", "edwin@gmail.com", "Visa", 200),
                    new Customer(2, "abc", "Not Logged", LocalDate.now(), "Pedro", "Lima 456", "pedro@gmail.com", "MasterCard", 100)
            )
    );

    @Override
    public List getAll() {
        return customers;
    }

    @Override
    public boolean login(String email, String password) {
        Customer currentCustomer = findByEmail(email);
        if (currentCustomer != null) {
            return currentCustomer.login(password);
        }
        return false;
    }

    @Override
    public void register(Customer customer) {
        customers.add(customer);
    }

    @Override
    public void update(Customer customer) {
        Customer currentCustomer = findById(customer.getUserId());
        if (currentCustomer != null) {
            int index = customers.indexOf(currentCustomer);
            customer.setUserId(currentCustomer.getUserId());
            customers.set(index, customer);
        }
    }

    @Override
    public void delete(Customer customer) {
        customers.remove(customer);
    }

    @Override
    public Customer findById(Integer id) {
        return customers.stream()
                .filter(a -> (a.getUserId()== id)).findFirst().orElse(null);
    }

    @Override
    public Customer findByEmail(String email) {
        return customers.stream()
                .filter(a -> a.getEmail().equals(email)).findFirst().orElse(null);
    }
}
