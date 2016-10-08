package org.queelt.model.repository;


import java.util.List;

import org.queelt.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends PagingAndSortingRepository<User, String> {

    public User findByName(String name);
    public User findByEmail(String email);
    public User findById(String id);
    @Query("{'email':{$ne : ?0}}")
    public Page<User> findAllNotEmail(String email,Pageable pageable);
    public Page<User> findAll(Pageable pageable);
    @Query("{'id':{$in : ?0}}")
    public Page<User> findIds(List<String> ids,Pageable pageable);
    @Query("{$or:[{name:{$regex:?0}},{email:{$regex:?0}}]}")
    public Page<User> search(String text,Pageable pageable);

}