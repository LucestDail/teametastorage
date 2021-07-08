package com.teametastorage.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Member;

public interface MemberRepository  extends JpaRepository<Member, Long>{

	@Query(value = "SELECT m FROM Member m WHERE m.team =:team ORDER BY m.MemberSeq DESC", nativeQuery = true)
	Stream<Member> findAllDesc(@Param("team") String team);

	@Transactional
	@Modifying
	@Query("UPDATE FROM Member m SET m.id=:id, m.password=:password,  m.team=:team, m.name =:name WHERE m.memberSeq=:memberSeq")
	void update(@Param("memberSeq") Long memberSeq, @Param("id") String id, @Param("password") String password,
			@Param("name") String name, @Param("team") String team);
}
