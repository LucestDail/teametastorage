package com.teametastorage.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Board;
import com.teametastorage.domain.Meta;

public interface MetaRepository extends JpaRepository<Meta, Long> {

	@Query("SELECT m FROM Meta m WHERE m.saveTeam=:saveTeam ORDER BY m.metaSeq DESC")
	Stream<Board> findAllDesc(@Param("saveTeam") String saveTeam);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Meta m SET m.nameKor=:nameKor, m.nameEng=:nameEng,  m.explanation=:explanation, m.type=:type, m.saveName =:saveName WHERE m.metaSeq=:metaSeq")
	void update(@Param("metaSeq") Long metaSeq, @Param("nameKor") String nameKor, @Param("nameEng") String nameEng,
			@Param("explanation") String explanation, @Param("type") String type, @Param("saveName") String saveName);
}
