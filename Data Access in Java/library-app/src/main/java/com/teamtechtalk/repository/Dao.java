package com.teamtechtalk.repository;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> findById(long id);

    List<T> findAll();

    T create(T t);

    T update(T t);

    int[] update(List<T> t);

    int delete(T r);
}
