package com.org.product.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.org.product.entity.Identifiable;

import jakarta.annotation.PostConstruct;

@Service
public class GenericService<T extends Identifiable> {
    private Map<Long, T> surrogateOfDB;

    @PostConstruct
    private void init() {
        this.surrogateOfDB = new HashMap<Long, T>();
    }

    public T getEntity(long id) {
        return this.surrogateOfDB.get(id);
    }

    public T persistEntity(T item) {
        this.surrogateOfDB.put(item.getId(), item);
        return item;
    }

    public T deleteEntity(Long id) {
        return this.surrogateOfDB.remove(id);
    }
}
