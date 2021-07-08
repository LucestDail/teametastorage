package com.teametastorage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.teametastorage.domain.Member;
import com.teametastorage.domain.Meta;
import com.teametastorage.dto.MetaCreateRequestDto;
import com.teametastorage.repository.MetaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MetaService {
	
	private MetaRepository metaRepository;
	
	@Transactional
	public String createMeta(MetaCreateRequestDto dto, Member sessionMember) {
		System.out.println("MetaService - createMeta : " + dto);
		metaRepository.save(dto.toEntity(sessionMember));
		return null;
	}
	
	public List<Meta> getMetaListByName(String name) {
		System.out.println("MetaService - getMetaListByName");
		List<Meta> findMetaList = new ArrayList<>();
		List<Meta> currentMetaList = metaRepository.findAll();
		for(Meta targetMeta : currentMetaList) {
			if(targetMeta.getNameEng().contains(name) || targetMeta.getNameKor().contains(name)) {
				findMetaList.add(targetMeta);
			}
		}
		return findMetaList;
	}

	public List<Meta> getAllMetaByTeam(String team) {
		System.out.println("MetaService - getAllMetaName : " + team);
		return null;
	}

	public List<Meta> getAllMetaByName(String name) {
		System.out.println("MetaService - getAllMetaByName : " + name);
		return null;
	}

	public Meta getMetaDetail(String searchid, String team) {
		System.out.println("MetaService - getMetaDetail : " + searchid + " : " + team);
		return null;
	}



}
