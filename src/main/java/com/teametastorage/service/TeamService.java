package com.teametastorage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Team;
import com.teametastorage.dto.TeamCreateRequestDto;
import com.teametastorage.repository.TeamRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TeamService {
	
	private TeamRepository teamRepository;
	
	@Transactional
	public Long createTeam(TeamCreateRequestDto dto) {
		System.out.println("TeamService - createTeam : " + dto);
		if(!validateTeam(dto)) {
			return teamRepository.save(dto.toEntityNormal()).getTeamSeq();
		}
		return teamRepository.save(dto.toEntityAdmin()).getTeamSeq();
	}

	public boolean validateTeam(TeamCreateRequestDto dto) {
		System.out.println("TeamService - validateTeam : " + dto);
		List<Team> currentTeamList = teamRepository.findAll();
		for(Team currentTeam : currentTeamList) {
			if(currentTeam.getTeam().equals(dto.getTeam())) {
				return false;
			}
		}
		return true;
	}

	public List<Team> getAllMember(String team) {
		List<Team> currentTeamList = teamRepository.findAll();
		List<Team> targetTeamList = new ArrayList<>();
		for(Team teamTarget : currentTeamList) {
			if(teamTarget.getTeam().equals(team)) {
				targetTeamList.add(teamTarget);
			}
		}
		return targetTeamList;
	}
}
