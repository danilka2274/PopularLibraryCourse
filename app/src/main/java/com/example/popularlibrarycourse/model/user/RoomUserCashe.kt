package com.example.popularlibrarycourse.model.user

import com.example.popularlibrarycourse.model.database.Database
import io.reactivex.rxjava3.core.Single

class RoomUserCache : IUsersCache {

    companion object {
        fun getInstance() = RoomUserCache()
    }

    private val db = Database.getInstance()

    override fun setCachedData(roomRepos: List<RoomGithubUser>) {
        db.userDao.insert(roomRepos)
    }

    override fun getCachedData(): Single<List<GithubUser>> {
        return Single.fromCallable {
            db.userDao.getAll().map { roomUser ->
                GithubUser(roomUser.id, roomUser.login, roomUser.avatarUrl, roomUser.reposUrl)
            }
        }
    }
}