package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection cn;

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        int rsl;
        try(PreparedStatement ps = cn.prepareStatement("INSERT INTO items (name) VALUES (?)")) {
            ps.setString(1, item.getName());
            rsl = ps.executeUpdate();
        } catch (SQLException e) {
            return null;
        }
        return rsl == 1 ? item : null;
    }

    @Override
    public boolean replace(int id, Item item) {
        int rsl;
        try(PreparedStatement ps = cn.prepareStatement("UPDATE items SET name = ? WHERE id = ?")) {
            ps.setString(1, item.getName());
            ps.setInt(2, id);
            rsl = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return rsl == 1;
    }

    @Override
    public boolean delete(int id) {
        int rsl;
        try(PreparedStatement ps = cn.prepareStatement("DELETE FROM items WHERE id = ?")) {
            ps.setInt(1, id);
            rsl = ps.executeUpdate();
        } catch (SQLException e) {
            return false;
        }
        return rsl == 1;
    }

    @Override
    public List<Item> findAll() {
        ResultSet set;
        List<Item> resList = new ArrayList<>();
        try(PreparedStatement ps = cn.prepareStatement("SELECT id, name FROM items")) {
            set = ps.executeQuery();
            while(set.next()){
                Item item = new Item(set.getInt("id"), set.getString("name"));
                resList.add(item);
            }
        } catch (SQLException e) {
            return null;
        }
        return resList;
    }

    @Override
    public List<Item> findByName(String key) {
        ResultSet set;
        List<Item> resList = new ArrayList<>();
        try(PreparedStatement ps = cn.prepareStatement("SELECT id, name FROM items WHERE name = ?")) {
            ps.setString(1, key);
            set = ps.executeQuery();
            while(set.next()){
                Item item = new Item(set.getInt("id"), set.getString("name"));
                resList.add(item);
            }
        } catch (SQLException e) {
            return null;
        }
        return resList;
    }

    @Override
    public Item findById(int id) {
        ResultSet set;
        Item resItem = null;
        try (PreparedStatement ps = cn.prepareStatement("SELECT id, name FROM items WHERE id = ?")) {
            ps.setInt(1, id);
            set = ps.executeQuery();
            if (set.next()){
                resItem = new Item(set.getInt("id"), set.getString("name"));
            }
        } catch (SQLException e) {
            return null;
        }
        return resItem;
    }
}