package ru.job4j.tracker.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.model.Item;
import ru.job4j.tracker.store.Store;

import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public void init() {
    }

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        final Session session = sf.openSession();
        try {
            session.beginTransaction();
            item.setId(id);
            session.update(item);
            session.getTransaction().commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        final Session session = sf.openSession();
        try {
            session.beginTransaction();
            Item item = new Item();
            item.setId(id);
            session.delete(item);
            session.getTransaction().commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            return false;
        } finally {
            session.close();
        }
        return true;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = List.of();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery("from Item", Item.class).list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = List.of();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.createQuery(
                    "from Item where name = :paramName", Item.class)
                    .setParameter("paramName", key)
                    .list();
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public Item findById(int id) {
        Item result = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = session.get(Item.class, id);
            session.getTransaction().commit();
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
