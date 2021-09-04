package com.liawan.mapper.custom;

import com.liawan.model.domain.ArticleCustom;
import com.liawan.model.domain.Category;
import com.liawan.model.domain.Tag;
import com.liawan.model.dto.ArchiveBo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :
 * @createDate : 2018年11月1日
 */
public interface ArticleMapperCustom {

	List<ArticleCustom> articleMapperCustom(@Param(value = "status") int status);

    List<ArticleCustom> findAllArticle(@Param(value = "status") int status, @Param(value = "post") String post);

    Integer countByStatus(@Param(value = "status") Integer status, @Param(value = "post") String post);

    void updateStatus(@Param(value = "id") int id, @Param(value = "status") int status);

    ArticleCustom findByArticleId(@Param(value = "id") Integer article_id);

    List<ArchiveBo> findDateAndCount();

    List<ArticleCustom> findPageArticle(ArticleCustom articleCustom);

    int findRepeatByUrl(@Param(value = "articleUrl") String articleUrl);

    ArticleCustom findByArticleUrl(@Param(value = "articleUrl") String articleUrl);

    List<ArticleCustom> findArtileByCategory(@Param("category") Category category, @Param(value = "status") int status);

    List<ArticleCustom> findArtileByTag(@Param("tag") Tag tag, @Param(value = "status") int status);
}
