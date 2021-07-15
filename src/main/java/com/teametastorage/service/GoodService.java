package com.teametastorage.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.teametastorage.domain.Good;
import com.teametastorage.domain.Member;
import com.teametastorage.dto.GoodCreateRequestDto;
import com.teametastorage.repository.GoodRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class GoodService {
	
	private GoodRepository goodRepository;
	
	public Good getGoodDetail(Long goodSeq) {
		return goodRepository.getById(goodSeq);
	}
	
	public Good getGoodDetailByIdAndBoardSeq(String goodId, Long boardId) {
		return goodRepository.getByIdAndBoardSeq(goodId, boardId);
		
	}
	
	public void deleteGood(Long goodSeq) {
		goodRepository.deleteById(goodSeq);
		goodRepository.flush();
	}
	
	public List<Good> getAllGood(Long boardSeq){
		List<Good> currentGoodList = goodRepository.findAll();
		List<Good> targetGoodList = new ArrayList<>();
		for(Good targetGood : currentGoodList) {
			if(targetGood.getBoardId().equals(String.valueOf(boardSeq))) {
				targetGoodList.add(targetGood);
			}
		}
		return targetGoodList;
	}
	
	public boolean checkGood(Long id, Long boardSeq) {
		List<Good> currentGoodList = getAllGood(boardSeq);
		System.out.println("currentGoodList : " + currentGoodList);
		for(Good targetGood : currentGoodList) {
			if(targetGood.getGoodId().equals(String.valueOf(id))) {
				return true;
			}
		}
		return false;
	}
	
	public Good getTargetGood(Long id, Long boardSeq) {
		List<Good> currentGoodList = getAllGood(boardSeq);
		System.out.println("currentGoodList : " + currentGoodList);
		for(Good targetGood : currentGoodList) {
			if(targetGood.getGoodId().equals(String.valueOf(id))) {
				return targetGood;
			}
		}
		return null;
	}


	public void createGood(GoodCreateRequestDto dto) {
		goodRepository.save(dto.toEntity());
		goodRepository.flush();
	}
}
