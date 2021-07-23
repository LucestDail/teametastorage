package com.teametastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teametastorage.domain.Work;

public interface WorkRepository extends JpaRepository<Work, Long>{

}
