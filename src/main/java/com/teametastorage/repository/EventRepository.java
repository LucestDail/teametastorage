package com.teametastorage.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.teametastorage.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	
	@Query("select b from Event b where b.start >= ?1 and b.finish <= ?2")
	public List<Event> findByDateBetween(LocalDateTime start, LocalDateTime end);
}
