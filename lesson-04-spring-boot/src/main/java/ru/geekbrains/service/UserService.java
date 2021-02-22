package ru.geekbrains.service;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<UserRepr> findAll();

    List<UserRepr> findWithFilter(String usernameFilter);

    Optional<UserRepr> findById(long id);

    void save(UserRepr user);

    void delete(long id);
}
