package com.nytimes.data.repository

import com.nytimes.data.cache.Cache
import com.nytimes.data.model.StoryEntity
import com.nytimes.data.repository.datasource.DbDataSource

class StoryDbDataSource(cache: Cache<StoryEntity>): DbDataSource<StoryEntity>(cache)