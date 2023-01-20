package com.side.dab.service;

import com.side.dab.dto.record.RecordDto;
import com.side.dab.entity.Record;
import com.side.dab.repository.RecordQueryRepository;
import com.side.dab.repository.RecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecordService {

    private final RecordRepository recordRepository;
    private final RecordQueryRepository recordQueryRepository;

    @Transactional(readOnly = true)
    public Page<RecordDto.SearchResponse> search(RecordDto.SearchRequest searchRequest, Pageable pageable){
        return recordQueryRepository.search(searchRequest, pageable);
    }

    @Transactional
    public void save(RecordDto.PostRequest postRequest) {

        //예외처리
        throwExceptionIfInvalidPreConditionByPostRequest(postRequest);

        Record record = new Record(postRequest);

        recordRepository.save(record);
    }

    // ExceptionHandler 추가 필요
    public void throwExceptionIfInvalidPreConditionByPostRequest(RecordDto.PostRequest postRequest) {

        if(ObjectUtils.isEmpty(postRequest.getTitle())) {
            throw new RuntimeException("원석아 타이틀은 넣어줘야지");
        }

        if(ObjectUtils.isEmpty(postRequest.getIsTitle())) {
            throw new RuntimeException("원석아 타이틀인지 아닌지는 넣어줘야지");
        }

        if(ObjectUtils.isEmpty(postRequest.getMusicFileUrl())) {
            throw new RuntimeException("원석아 음악 경로는 넣어줘야지");
        }

        if(ObjectUtils.isEmpty(postRequest.getGenreId())) {
            throw new RuntimeException("원석아 장르는 넣어줘야지");
        }
    }


}
