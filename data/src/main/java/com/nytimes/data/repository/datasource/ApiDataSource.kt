package com.nytimes.data.repository.datasource

import com.nytimes.data.cache.Cache

abstract class ApiDataSource<Entity>(protected val cache: Cache<Entity>) : DataSource<Entity>