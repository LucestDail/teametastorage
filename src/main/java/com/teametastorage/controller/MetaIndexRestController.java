package com.teametastorage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.teametastorage.domain.MetaIndex;
import com.teametastorage.dto.MetaIndexCreateRequestDto;
import com.teametastorage.dto.MetaIndexUpdateRequestDto;
import com.teametastorage.service.MetaIndexService;

@RestController
@RequestMapping("/metaindex")
public class MetaIndexRestController {

	
	@Autowired
	MetaIndexService metaIndexService;
	/*
	 * read one
	 */
	@RequestMapping(value = "/{workseq}/{seq}", method = RequestMethod.GET)
	public MetaIndex getMetaIndex(@PathVariable("workseq") String workSeq, @PathVariable("seq") String seq) {
		return metaIndexService.getMetaIndex(Long.parseLong(seq));
	}

	/*
	 * read all
	 */
	@RequestMapping(value = "/{workseq}", method = RequestMethod.GET)
	public List<MetaIndex> getMetaIndexAll(@PathVariable("workseq") String workSeq) {
		return metaIndexService.getMetaIndexAll(Long.parseLong(workSeq));

	}

	/*
	 * create
	 */
	@RequestMapping(value = "/{workseq}", method = RequestMethod.PUT)
	public boolean putMetaIndex(@RequestBody MetaIndexCreateRequestDto dto, @PathVariable("workseq") String workSeq) {
		return metaIndexService.putMetaIndex(dto,Long.parseLong(workSeq));
	}

	/*
	 * update
	 */
	@RequestMapping(value = "/{workseq}/{seq}", method = RequestMethod.POST)
	public boolean postMetaIndex(@RequestBody MetaIndexUpdateRequestDto dto,@PathVariable("workseq") String workSeq, @PathVariable("seq") String seq) {
		return metaIndexService.postMetaIndex(dto,Long.parseLong(workSeq), Long.parseLong(seq));
	}

	/*
	 * delete
	 */
	@RequestMapping(value = "/{workseq}/{seq}", method = RequestMethod.DELETE)
	public boolean delteMetaIndex(@PathVariable("seq") String seq) {
		return metaIndexService.deleteMetaIndex(Long.parseLong(seq));
	}
}
