package com.teametastorage.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Team;
import com.teametastorage.domain.TeamDetail;
import com.teametastorage.domain.TeamNotice;
import com.teametastorage.dto.TeamCreateRequestDto;
import com.teametastorage.dto.TeamDetailCreateRequestDto;
import com.teametastorage.dto.TeamDetailUpdateRequestDto;
import com.teametastorage.dto.TeamNoticeCreateRequestDto;
import com.teametastorage.dto.TeamNoticeUpdateRequestDto;
import com.teametastorage.repository.TeamDetailRepository;
import com.teametastorage.repository.TeamNoticeRepository;
import com.teametastorage.repository.TeamRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TeamService {

	private TeamRepository teamRepository;
	
	private TeamDetailRepository teamDetailRepository;
	
	private TeamNoticeRepository teamNoticeRepository;
	

	@Transactional
	public Long createTeam(TeamCreateRequestDto dto) {
		System.out.println("TeamService - createTeam : " + dto);
		if (!validateTeam(dto)) {
			return teamRepository.save(dto.toEntityNone()).getTeamSeq();
		}
		TeamDetailCreateRequestDto tddto = new TeamDetailCreateRequestDto();
		tddto.setTeam(dto.getTeam());
		createTeamDetail(tddto);
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
	
	public boolean createTeamDetail(TeamDetailCreateRequestDto dto) {
		if(Objects.isNull(teamDetailRepository.save(dto.toEntity()))) {
			return false;
		}
		return true;
	}

	public TeamDetail getTeamDetail(String team) {
		List<TeamDetail> teamDetailList = teamDetailRepository.findAll();
		for(TeamDetail target : teamDetailList) {
			if(target.getTeam().equals(team)) {
				return target;
			}
		}
		return null;
	}

	public boolean updateTeamDetail(TeamDetailUpdateRequestDto dto) {
		if(Objects.isNull(teamDetailRepository.saveAndFlush(dto.toEntity()))){
			return false;
		}
		return true;
	}

	public Page<TeamNotice> findPaginated(Pageable pageable, String team) {
		List<TeamNotice> listAll = teamNoticeRepository.findAll();
		List<TeamNotice> listQna = new ArrayList<>();
		for(TeamNotice target : listAll) {
			if(target.getSaveTeam().equals(team)) {
				listQna.add(target);
			}
		}
		Collections.reverse(listQna);
		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<TeamNotice> list;

		if (listQna.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, listQna.size());
			list = listQna.subList(startItem, toIndex);
		}

		Page<TeamNotice> boardPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), listQna.size());

		return boardPage;
	}

	public boolean createTeamNotice(TeamNoticeCreateRequestDto dto) {
		if(Objects.isNull(teamNoticeRepository.save(dto.toEntity()))) {
			return false;
		}
		return true;
	}
	
	public boolean updateTeamNotice(TeamNoticeUpdateRequestDto dto) {
		if(Objects.isNull(teamNoticeRepository.saveAndFlush(dto.toEntity()))) {
			return false;
		}
		return true;
	}

	public boolean deleteTeamNotice(long id) {
		teamNoticeRepository.deleteById(id);
		Optional<TeamNotice> chk = teamNoticeRepository.findById(id);
		if(chk.isPresent()){
			return false;
		}
		return false;
	}

	public TeamNotice getTeamNotice(long id) {
		return teamNoticeRepository.getById(id);
	}
}
