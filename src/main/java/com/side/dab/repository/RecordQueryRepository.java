package com.side.dab.repository;

import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.side.dab.dto.record.RecordDto;
import com.side.dab.helper.QueryDslHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;

import static com.querydsl.core.types.ExpressionUtils.count;
import static com.side.dab.entity.QRecord.record;

@Repository
@RequiredArgsConstructor
public class RecordQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Transactional(readOnly = true)
    public Page<RecordDto.SearchResponse> search(RecordDto.SearchRequest searchRequest, Pageable pageable){
        List<RecordDto.SearchResponse> searchList = getSearchList(searchRequest, pageable);

        Long totalCount = getSearchCount(searchRequest);

        return new PageImpl<>(searchList, pageable, totalCount);
    }

    public List<RecordDto.SearchResponse> getSearchList(RecordDto.SearchRequest searchRequest, Pageable pageable) {


        return queryFactory.selectDistinct(
                        Projections.constructor(
                                RecordDto.SearchResponse.class,
                                record
                        )
                )
                .from(record)
                .where(
                        likeIfExistTitle(searchRequest.getTitle()),
                        likeIfExistComposer(searchRequest.getComposer()),
                        likeIfExistSongwriter(searchRequest.getSongwriter()),
                        likeIfExistSinger(searchRequest.getSinger()),
                        eqIfExistIsTitle(searchRequest.getIsTitle()),
                        eqIfExistGenreId(searchRequest.getGenreId())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(getOrderByProperty(pageable.getSort()))
                .fetch();

    }

    public Long getSearchCount(RecordDto.SearchRequest searchRequest) {
        return queryFactory.select(
                        count(record.id)
                )
                .from(record)
                .where(
                        likeIfExistTitle(searchRequest.getTitle()),
                        likeIfExistComposer(searchRequest.getComposer()),
                        likeIfExistSongwriter(searchRequest.getSongwriter()),
                        likeIfExistSinger(searchRequest.getSinger()),
                        eqIfExistIsTitle(searchRequest.getIsTitle()),
                        eqIfExistGenreId(searchRequest.getGenreId())
                )
                .fetchOne();
    }

    private BooleanExpression likeIfExistTitle(String title) {
        if(ObjectUtils.isEmpty(title)) {
            return null;
        }

        return record.title.like(QueryDslHelper.transformToLikeString(title, true, true));
    }

    private BooleanExpression likeIfExistComposer(String composer) {
        if(ObjectUtils.isEmpty(composer)) {
            return null;
        }

        return record.composer.like(QueryDslHelper.transformToLikeString(composer, true, true));
    }

    private BooleanExpression likeIfExistSongwriter(String songwriter) {
        if(ObjectUtils.isEmpty(songwriter)) {
            return null;
        }

        return record.songwriter.like(QueryDslHelper.transformToLikeString(songwriter, true, true));
    }

    private BooleanExpression likeIfExistSinger(String singer) {
        if(ObjectUtils.isEmpty(singer)) {
            return null;
        }

        return record.singer.like(QueryDslHelper.transformToLikeString(singer, true, true));
    }

    private BooleanExpression eqIfExistIsTitle(Boolean isTitle) {
        if(ObjectUtils.isEmpty(isTitle)) {
            return null;
        }

        return record.isTitle.eq(isTitle);
    }

    private BooleanExpression eqIfExistGenreId(Short genreId) {
        if(ObjectUtils.isEmpty(genreId)) {
            return null;
        }

        return record.genreId.eq(genreId);
    }


    private OrderSpecifier<?>[] getOrderByProperty(Sort sort) {
        return sort.stream().map(x -> {
            String property = x.getProperty();
            Sort.Direction direction = x.getDirection();
            boolean isAscending = direction.isAscending();
            Order order = isAscending ? Order.ASC : Order.DESC;

            if(property.equals("id")) {
                return new OrderSpecifier(order, record.id);
            }

            if(property.equals("albumId")) {
                return new OrderSpecifier(order, record.album.id);
            }

            if(property.equals("title")) {
                return new OrderSpecifier(order, record.title);
            }

            if(property.equals("composer")) {
                return new OrderSpecifier(order, record.composer);
            }

            if(property.equals("songwriter")) {
                return new OrderSpecifier(order, record.songwriter);
            }

            if(property.equals("singer")) {
                return new OrderSpecifier(order, record.singer);
            }

            if(property.equals("genreId")) {
                return new OrderSpecifier(order, record.genreId);
            }


            return null;
        }).toArray(OrderSpecifier[]::new);
    }
}
