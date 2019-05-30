package kr.ac.jejunu.sslab.gongdae.dao;

import java.util.Collection;

public interface Dao<T> {
    public Long add(T dto);
    public T get(Long id);
    public Collection<T> getAll();
    public void update(T dto);
    public void delete(Long id);
}
