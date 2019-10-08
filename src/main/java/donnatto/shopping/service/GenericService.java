package donnatto.shopping.service;

import donnatto.shopping.model.Customer;

import java.util.List;

public interface GenericService<T, K, S> {

    List<T> getAll();

    boolean login(S s1, S s2);

    void register(T t);

    void update(T t);

    void delete(T t);

    T findById(K k);

    T findByEmail(S s);
}
