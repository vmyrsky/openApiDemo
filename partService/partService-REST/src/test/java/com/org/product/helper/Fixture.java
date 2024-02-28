package com.org.product.helper;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.org.product.generic.model.Identifiable;
import com.org.product.generic.service.GenericService;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * A fixture should keep record of entities handled during the tests so that it can clean the data afterwards the tests
 * have been done.
 * @param <T> The type of the entity to handle. The entity is expected to be identifiable to record the parts used.
 */
@Service
public abstract class Fixture<T extends Identifiable> {

    private Set<Identifiable> persisted;
    @Inject
    private GenericService<T> service;

    /**
     * Default constructor.
     */
    public Fixture() {
    }

    @PostConstruct
    private void init() {
        this.persisted = new HashSet<>();
    }

    /**
     * Persist an entity to test DB. Records the id of the entity to clear the data when the tests are done.
     * @param entity The entity to persist.
     */
    public void persistToDB(T entity) {
        this.service.persistEntity(entity);
        this.persisted.add(entity);
    }

    /**
     * Not very safe method to create ids but should suffice for tests.
     * @return A random long number to use as id for identifiable object.
     */
    public long generateId() {
        return new Random().nextLong();
    }

    /**
     * Not very safe method to create ids but should suffice for tests.
     * @return A random long number to use as id for identifiable object.
     */
    public Identifiable generateIdentifier() {
        return new EntityId(this.generateId());
    }

    /**
     * Create a dummy object of type T with some default attributes preset.
     * @param id The identification to use with the dummy instance.
     * @return A dummy instance of object type T to fill with more specific details.
     */
    public T buildDummyEntity() {
        return this.buildDummyEntity(this.generateIdentifier());
    }

    /**
     * Create a dummy object of type T with some default attributes preset.
     * @param id The identification to use with the dummy instance.
     * @return A dummy instance of object type T to fill with more specific details.
     */
    public abstract T buildDummyEntity(Identifiable id);

    /**
     * Delete all test data of the specified type in the test DB. When a fixture is instantiated it should be
     * automatically added to fixture pool which will be iterated and all clear methods called automatically.
     */
    @PreDestroy
    public void clearDB() {
        this.persisted.stream().forEach(entity -> this.service.deleteEntity(entity.getId()));
    }

    private class EntityId implements Identifiable {
        private long id;

        private EntityId(long id) {
            this.id = id;
        }

        @Override
        public Long getId() {
            return this.id;
        }
    };
}
