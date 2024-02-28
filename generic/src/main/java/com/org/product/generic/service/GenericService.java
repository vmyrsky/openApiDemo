package com.org.product.generic.service;

import java.util.HashMap;
import java.util.Map;

import com.org.product.generic.model.Identifiable;

public class GenericService<T extends Identifiable> {
    private Map<Long, T> surrogateOfDB;

    public GenericService() {
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
