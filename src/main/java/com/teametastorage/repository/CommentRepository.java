package com.teametastorage.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{

	@Query("SELECT c FROM Comment c WHERE c.boardId=:boardId ORDER BY c.commentSeq DESC")
	Stream<Comment> findAllDesc(@Param("boardId") String boardId);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Comment c SET c.boardId=:boardId, c.content=:content, c.saveTeam=:saveTeam, c.saveName =:saveName, c.saveId =:saveId WHERE c.commentSeq=:commentSeq")
	void update(@Param("boardId") Long boardId, @Param("content") String content, @Param("saveTeam") String saveTeam,
			@Param("saveName") String saveName, @Param("saveId") String saveId, @Param("commentSeq") String commentSeq);
}