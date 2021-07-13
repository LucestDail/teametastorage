package com.teametastorage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		if (!validateTeam(dto)) {
			return teamRepository.save(dto.toEntityNone()).getTeamSeq();
		}
		return teamRepository.save(dto.toEntityAdmin()).getTeamSeq();
	}

	public boolean noneToNormal(Team teamMember) {
		System.out.println("TeamService - noneToNormal : " + teamMember);
		teamRepository.update(teamMember.getTeamSeq(), teamMember.getMemberId(), teamMember.getName(), "normal",
				teamMember.getTeam());
		Team afterTeam = teamRepository.getById(teamMember.getTeamSeq());
		if (afterTeam.getRank().equals("normal")) {
			return true;
		}
		return false;
	}

	public boolean validateTeam(TeamCreateRequestDto dto) {
		System.out.println("TeamService - validateTeam : " + dto);
		List<Team> currentTeamList = teamRepository.findAll();
		for (Team currentTeam : currentTeamList) {
			if (currentTeam.getTeam().equals(dto.getTeam())) {
				return false;
			}
		}
		return true;
	}

	public List<Team> getAllMember(String team) {
		List<Team> currentTeamList = teamRepository.findAll();
		List<Team> targetTeamList = new ArrayList<>();
		for (Team teamTarget : currentTeamList) {
			if (teamTarget.getTeam().equals(team)) {
				targetTeamList.add(teamTarget);
			}
		}
		return targetTeamList;
	}

	public String getRank(String team, String memberId) {
		List<Team> currentTeamList = teamRepository.findAll();
		for (Team teamTarget : currentTeamList) {
			if (teamTarget.getTeam().equals(team)) {
				if (teamTarget.getMemberId().equals(memberId)) {
					return teamTarget.getRank();
				}
			}
		}
		return null;
	}

	public boolean deleteTeamMember(Team teamMember) {
		System.out.println("TeamService - deleteTeamMember : " + teamMember);
		List<Team> targetTeamList = new ArrayList<>();
		List<Team> currentTeamList = teamRepository.findAll();
		System.out.println("currentTeamList : " + currentTeamList);

		for (Team teamTarget : currentTeamList) {
			if (teamTarget.getTeam().equals(teamMember.getTeam())) {
				targetTeamList.add(teamTarget);
			}
		}
		System.out.println("targetTeamList : " + targetTeamList);
		if (targetTeamList.contains(teamMember)) {
			System.out.println("Delete Success : " + teamMember);
			teamRepository.deleteById(teamMember.getTeamSeq());
			teamRepository.flush();
			return true;
		} else {
			System.out.println("Delete Fail : " + teamMember);
			return false;
		}
	}

	public Team getTeamObject(String team, String memberId) {
		List<Team> currentTeamList = teamRepository.findAll();
		for (Team teamTarget : currentTeamList) {
			if (teamTarget.getTeam().equals(team)) {
				if (teamTarget.getMemberId().equals(memberId)) {
					return teamTarget;
				}
			}
		}
		return null;
	}

	public Team getTeamBySeq(Long teamSeq) {
		Optional<Team> targetTeam = teamRepository.findById(teamSeq);
		if (targetTeam.isPresent()) {
			return targetTeam.get();
		}
		return null;
	}
}
