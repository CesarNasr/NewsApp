package com.example.newsapp.data.utils

import com.example.newsapp.data.localdb.model.LocalArticle
import com.example.newsapp.data.model.response.Article
import javax.inject.Inject

/**
 * Each level of our app (local data source/ remote datasource / UI) has it's own entity/ models and this class helps us map
 * them to one another
 */
class ArticleMapper @Inject constructor() : EntityMapper<LocalArticle, Article> {

    override fun mapFromLocalEntity(entity: LocalArticle): Article {
        return Article(
            author = entity.author,
            content = entity.content,
            description = entity.description,
            publishedAt = entity.publishedAt,
            title = entity.title,
            urlToImage = entity.urlToImage
        )
    }

    override fun mapToEntity(domainModel: Article): LocalArticle {
        return LocalArticle(
            author = domainModel.author,
            content = domainModel.content,
            description = domainModel.description,
            publishedAt = domainModel.publishedAt,
            title = domainModel.title,
            urlToImage = domainModel.urlToImage
        )
    }

    fun mapFromEntityList(entities: List<LocalArticle>): List<Article> {
        return entities.map { mapFromLocalEntity(it) }
    }


    fun mapToEntityList(entities: List<Article>): List<LocalArticle> {
        return entities.map { mapToEntity(it) }
    }
}