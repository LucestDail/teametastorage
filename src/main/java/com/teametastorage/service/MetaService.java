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

	public List<Meta> getMetaListByName(String name, String team) {
		System.out.println("MetaService - getMetaListByName");
		List<Meta> findMetaList = new ArrayList<>();
		List<Meta> currentMetaList = metaRepository.findAll();
		for (Meta targetMeta : currentMetaList) {
			if (targetMeta.getNameEng().contains(name) || targetMeta.getNameKor().contains(name)) {
				if (targetMeta.getSaveTeam().equals(team)) {
					findMetaList.add(targetMeta);
				}
			}
		}
		return findMetaList;
	}

	public List<Meta> getAllMetaByTeam(String team) {
		System.out.println("MetaService - getAllMetaName : " + team);
		List<Meta> findMetaList = new ArrayList<>();
		List<Meta> currentMetaList = metaRepository.findAll();
		for (Meta targetMeta : currentMetaList) {
			if (targetMeta.getSaveTeam().equals(team)) {
				findMetaList.add(targetMeta);
			}
		}
		return findMetaList;
	}

	public List<Meta> getAllMetaByName(String name) {
		System.out.println("MetaService - getAllMetaByName : " + name);
		return null;
	}

	public Meta getMetaDetail(Long metaSeq) {
		System.out.println("MetaService - getMetaDetail : " + metaSeq);
		return metaRepository.getById(metaSeq);
	}

	public boolean updateMeta(Meta inputMeta, Member sessionMember) {
		System.out.println("MetaService - updateMeta : " + inputMeta + " : " + sessionMember);
		metaRepository.update(inputMeta.getMetaSeq(), inputMeta.getNameKor(), inputMeta.getNameEng(),
				inputMeta.getExplanation(), inputMeta.getType(), sessionMember.getName());
		return true;
	}

	public void deleteMeta(long id) {
		System.out.println("MetaService - deleteMeta : " + id);
		// TODO Auto-generated method stub
		metaRepository.deleteById(id);
	}

}
