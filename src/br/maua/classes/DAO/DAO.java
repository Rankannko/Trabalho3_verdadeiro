package br.maua.classes.DAO;

import java.util.List;

public interface DAO <T>{
    T get(String condition);
    List<T> getAll();
    void update(T t);
    void delete(T t);
    void create(T t);
}
