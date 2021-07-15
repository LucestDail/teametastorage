package com.teametastorage.repository;


import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teametastorage.domain.Good;

public interface GoodRepository extends JpaRepository<Good, Long>{

	@Query("SELECT g FROM Good g WHERE g.goodId=:goodId AND g.boardId=:boardId")
	Good getByIdAndBoardSeq(@Param("goodId") String id, @Param("boardId") Long boardSeq);
}