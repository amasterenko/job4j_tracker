package ru.job4j.tracker.hbm;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.tracker.model.Item;

import java.sql.Timestamp;
import java.util.List;

public class HibernateRun {
    public static void main(String[] args) {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure().build();
        try {
            SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();
            Item item1 = create(
                    new Item("Learn Hibernate - Item1",
                            "+update DB",
                            new Timestamp(System.currentTimeMillis())
                    ),
                    sf);
            Item item2 = create(
                    new Item("Learn Hibernate - Item2",
                            "+update DB",
                            new Timestamp(System.currentTimeMillis())
                    ),
                    sf);
            System.out.println(item1);
            System.out.println(item2);
            item1.setName("Learn Hibernate 5.");
            item2.setName("Learn Hibernate 4.");
            update(item1, sf);
            update(item2, sf);
            System.out.println(item1);
            System.out.println(item2);
            Item rsl = findById(item1.getId(), sf);
            System.out.println(rsl);
            rsl = findById(item2.getId(), sf);
            System.out.println(rsl);
            List<Item> list = findAll(sf);
            for (Item it : list) {
                System.out.println(it);
            }
            list.forEach(i -> delete(i.getId(), sf));
            list.forEach(System.out::println);
        }  catch (Exception e) {
            e.printStackTrace();
        } finally {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static Item create(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        return item;
    }

    public static void update(Item item, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        session.close();
    }

    public static void delete(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item item = new Item();
        item.setId(id);
        session.delete(item);
        session.getTransaction().commit();
        session.close();
    }

    public static List<Item> findAll(SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        List<Item> result = session.createQuery("from ru.job4j.tracker.model.Item").list();
        session.getTransaction().commit();
        session.close();
        return result;
    }

    public static Item findById(Integer id, SessionFactory sf) {
        Session session = sf.openSession();
        session.beginTransaction();
        Item result = session.get(Item.class, id);
        session.getTransaction().commit();
        session.close();
        return result;
    }
}
