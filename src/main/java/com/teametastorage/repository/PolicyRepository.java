package com.teametastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teametastorage.domain.Policy;

public interface PolicyRepository extends JpaRepository<Policy, Long>{

}
