package com.todook.crocodile.infrastructure;

import com.todook.crocodile.domain.Hello;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HelloMapper {
    List<Hello> findAll();
    Hello findById(Long id);
    void save(Hello hello);
    void delete(Long id);
}
