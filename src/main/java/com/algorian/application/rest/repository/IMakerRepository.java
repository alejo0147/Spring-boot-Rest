package com.algorian.application.rest.repository;

import com.algorian.application.rest.entities.Maker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMakerRepository extends JpaRepository<Maker, Long> {



}
