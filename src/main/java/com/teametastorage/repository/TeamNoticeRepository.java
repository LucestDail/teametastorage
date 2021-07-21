package com.teametastorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.teametastorage.domain.TeamNotice;

public interface TeamNoticeRepository extends JpaRepository<TeamNotice, Long>{

}
