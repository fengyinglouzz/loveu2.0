//package com.zxzyyyy.loveu.repository;
//
//import com.zxzyyyy.loveu.entity.EsBlog;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
//
///**
// * @Description:
// */
//public interface EsBlogRepository extends ElasticsearchCrudRepository<EsBlog, String> {
//
//    /**
//     * 分页查询博客 （去重）
//     * @param title
//     * @param summary
//     * @param content
//     * @param pageable
//     * @return
//     */
//    Page<EsBlog> findDistinctByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);
//}
