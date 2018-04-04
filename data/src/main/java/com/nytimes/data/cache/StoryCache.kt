package com.nytimes.data.cache

import android.content.Context
import com.nytimes.data.db.NYTimesDb
import com.nytimes.data.model.StoryEntity
import io.reactivex.Observable
import java.util.Date

class StoryCache(context: Context) : Cache<StoryEntity> {

    companion object {
        const val EXPIRATION_TIME = 3600000 // 1h
    }

    var database = NYTimesDb.create(context, false)

    override fun isExpired(): Observable<Boolean> {
        return Observable.fromCallable {
            var isExpired = false

            val dao = database.storyDao()

            if (dao.size() != 0) {
                val currentTime = Date(System.currentTimeMillis())

                val lastUpdated = dao.getFirst().lastUpdated

                isExpired = currentTime.time - lastUpdated > EXPIRATION_TIME

                if (isExpired) {
                    dao.deleteAll()
                }
            }

            isExpired
        }
    }

    override fun isCached(): Observable<Boolean> =
        Observable.fromCallable { database.storyDao().size() > 0 }

    override fun get(): Observable<List<StoryEntity>> =
        database.storyDao().stories().toObservable()

    override fun put(list: List<StoryEntity>) =
        database.storyDao().insert(list)

    override fun invalidate(): Observable<Unit> {
        return Observable.fromCallable {
            database.storyDao().deleteAll()
        }
    }


}