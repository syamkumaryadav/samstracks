package com.syam.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syam.entity.StudentEntity;


@Repository
public interface MyRepository extends JpaRepository<StudentEntity, Serializable>{

}
