package com.side.dab.controller;

import com.side.dab.dto.record.RecordDto;
import com.side.dab.service.RecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/records")
@RestController
@RequiredArgsConstructor
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/search")
    public Page<RecordDto.SearchResponse> search(@ModelAttribute RecordDto.SearchRequest searchRequest,
                                                 Pageable pageable) {

        return recordService.search(searchRequest, pageable);
    }

    @PostMapping("")
    public void save(@RequestBody RecordDto.PostRequest postRequest) {

        recordService.save(postRequest);
    }
}
