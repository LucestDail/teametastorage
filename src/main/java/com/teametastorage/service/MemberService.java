package com.teametastorage.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Member;
import com.teametastorage.dto.MemberCreateRequestDto;
import com.teametastorage.dto.MemberReadRequestDto;
import com.teametastorage.dto.MemberUpdateRequestDto;
import com.teametastorage.repository.MemberRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberService {

	private MemberRepository memberRepository;
	
	@Autowired
	TeamService teamService;
	
	@Transactional
	public Long createMember(MemberCreateRequestDto dto) {
		System.out.println("MemberService - createMember : " + dto);
		if(!Objects.isNull(memberRepository.save(dto.toEntity()).getMemberSeq())) {
			return teamService.createTeam(dto.toEntityTeam());
		}
		return null;
	}

	public boolean validateMember(MemberCreateRequestDto dto) {
		System.out.println("MemberService - validateMember : " + dto);
		List<Member> currentMemberList = memberRepository.findAll();
		for(Member currentMember : currentMemberList) {
			if(currentMember.getId().equals(dto.getId())) {
				return false;
			}
		}
		return true;
	}

	public boolean loginMember(MemberReadRequestDto dto) {
		System.out.println("MemberService - loginMember : " + dto);
		List<Member> currentMemberList = memberRepository.findAll();
		for(Member currentMember : currentMemberList) {
			if(currentMember.getId().equals(dto.getId()) && currentMember.getPassword().equals(dto.getPassword())) {
				return true;
			}
		}
		return false;
	}
	
	public Member getMemberById(String id) {
		System.out.println("MemberService - getMemberById : " + id);
		List<Member> currentMemberList = memberRepository.findAll();
		Member findMember = null;
		for(Member currentMember : currentMemberList) {
			if(currentMember.getId().equals(id)) {
				findMember = currentMember;
			}
		}
		return findMember;
	}

	public boolean updateMember(MemberUpdateRequestDto dto) {
		System.out.println("MemberService - updateMember : " + dto);
		Member currentMember = getMemberById(dto.getId());
		memberRepository.update(currentMember.getMemberSeq(), currentMember.getId(), dto.getPassword(), dto.getName(), dto.getTeam());
		memberRepository.flush();
		return true;
	}

	public boolean deleteMember(String id) {
		System.out.println("MemberService - deleteMember : " + id);
		memberRepository.deleteById(getMemberById(id).getMemberSeq());
		if(Objects.isNull(getMemberById(id))){
			return true;
		}
		return false;
	}



}