package com.teametastorage.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Board;
/*
public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query("SELECT b FROM Board b WHERE b.saveTeam=:saveTeam ORDER BY b.boardSeq DESC")
	Stream<Board> findAllDesc(@Param("saveTeam") String saveTeam);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Board b SET b.title=:title, b.content=:content,  b.saveTeam=:saveTeam, b.saveName =:saveName WHERE b.id=:id")
	void update(@Param("boardSeq") Long boardSeq, @Param("title") String title, @Param("content") String content,
			@Param("saveTeam") String saveTeam, @Param("saveName") String saveName);
}
*/