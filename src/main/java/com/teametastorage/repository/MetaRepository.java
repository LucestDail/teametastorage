package com.teametastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.teametastorage.domain.Meta;

@Repository
public interface MetaRepository extends JpaRepository<Meta, Long> {

}
