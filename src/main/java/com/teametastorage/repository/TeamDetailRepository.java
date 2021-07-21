package com.teametastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teametastorage.domain.TeamDetail;

public interface TeamDetailRepository extends JpaRepository<TeamDetail, Long> {

}
