package com.syam.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.syam.entityes.Achievement;

@Repository
public interface AchievementRepository extends JpaRepository<Achievement, Serializable> {
}
