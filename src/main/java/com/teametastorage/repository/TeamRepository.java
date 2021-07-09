package com.teametastorage.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Team;

public interface TeamRepository extends JpaRepository<Team, Long>{

	@Query(value = "SELECT t FROM Team t WHERE t.team =:team ORDER BY t.teamSeq DESC", nativeQuery = true)
	Stream<Team> findAllDesc(@Param("team") String team);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Team t SET t.memberId=:memberId, t.team=:team, t.rank =:rank WHERE t.teamSeq=:teamSeq")
	void update(@Param("teamSeq") Long teamSeq, @Param("memberId") String memberId, @Param("rank") String rank,
			@Param("team") String team);
}
