package com.teametastorage.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Board;

public interface BoardRepository extends JpaRepository<Board, Long> {

	@Query("SELECT b FROM Board b WHERE b.saveTeam=:saveTeam ORDER BY b.boardSeq DESC")
	Stream<Board> findAllDesc(@Param("saveTeam") String saveTeam);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Board b SET b.title=:title, b.content=:content,  b.saveTeam=:saveTeam, b.saveName =:saveName, b.saveId =:saveId WHERE b.boardSeq=:boardSeq")
	void update(@Param("boardSeq") Long boardSeq, @Param("title") String title, @Param("content") String content,
			@Param("saveTeam") String saveTeam, @Param("saveName") String saveName, @Param("saveId") String saveId);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Board b SET b.count = b.count+1 WHERE b.boardSeq=:boardSeq")
	void addCount(@Param("boardSeq") Long boardSeq);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Board b SET b.good = b.good+1 WHERE b.boardSeq=:boardSeq")
	void addGood(@Param("boardSeq") Long boardSeq);
	
	@Transactional
	@Modifying
	@Query("UPDATE FROM Board b SET b.good = b.good-1 WHERE b.boardSeq=:boardSeq")
	void minusGood(@Param("boardSeq") Long boardSeq);
}
