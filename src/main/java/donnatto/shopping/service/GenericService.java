package donnatto.shopping.service;

import donnatto.shopping.model.Customer;

import java.util.List;

public interface GenericService<T, K> {

    List<T> getAll();

    void register(T t);

    void update(T t);

    T findById(K k);
}
