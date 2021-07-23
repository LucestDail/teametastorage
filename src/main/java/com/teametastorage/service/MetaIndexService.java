package com.teametastorage.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.teametastorage.domain.MetaIndex;
import com.teametastorage.dto.MetaIndexCreateRequestDto;
import com.teametastorage.dto.MetaIndexUpdateRequestDto;
import com.teametastorage.repository.MetaIndexRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MetaIndexService {
	
	private MetaIndexRepository metaIndexRepository;

	public MetaIndex getMetaIndex(long metaIndexSeq) {
		return metaIndexRepository.getById(metaIndexSeq);
	}

	public List<MetaIndex> getMetaIndexAll(long workSeq) {
		List<MetaIndex> allMetaIndex = metaIndexRepository.findAll();
		List<MetaIndex> targetMetaIndex = new ArrayList<>();
		for(MetaIndex target : allMetaIndex) {
			if(target.getWorkSeq().equals(workSeq)) {
				targetMetaIndex.add(target);
			}
		}
		return targetMetaIndex;
	}

	public boolean putMetaIndex(MetaIndexCreateRequestDto dto, long workSeq) {
		return Objects.isNull(metaIndexRepository.save(dto.toEntity())) ? false : true;
	}

	public boolean postMetaIndex(MetaIndexUpdateRequestDto dto, long workSeq, long metaIndexSeq) {
		return Objects.isNull(metaIndexRepository.saveAndFlush(dto.toEntity())) ? false : true;
	}

	public boolean deleteMetaIndex(long metaIndexSeq) {
		metaIndexRepository.deleteById(metaIndexSeq);
		return Objects.isNull(metaIndexRepository.getById(metaIndexSeq)) ? true : false;
	}

}
