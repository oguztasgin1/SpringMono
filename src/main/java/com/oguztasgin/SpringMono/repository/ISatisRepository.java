package com.oguztasgin.SpringMono.repository;

import com.oguztasgin.SpringMono.repository.entity.Satis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISatisRepository extends JpaRepository<Satis,Long> {
}
