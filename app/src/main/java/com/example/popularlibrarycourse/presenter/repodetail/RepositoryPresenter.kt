package com.example.popularlibrarycourse.presenter.repodetail

import android.annotation.SuppressLint
import com.example.popularlibrarycourse.domain.model.GitHubRepository
import com.example.popularlibrarycourse.domain.repository.IRepository
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import moxy.MvpPresenter



class RepositoryPresenter(
    private val gitHubRepository: GitHubRepository,
    private val schedulers: com.example.popularlibrarycourse.scheduler.Schedulers,
    private val repository: IRepository
) :
    MvpPresenter<IRepositoryView>() {

    private var disposables = CompositeDisposable()

    @SuppressLint("CheckResult")
    override fun onFirstViewAttach() {
        repository
            .fetchRepositoryInfo(gitHubRepository.login, gitHubRepository.name)
            .observeOn(schedulers.main())
            .subscribeOn(schedulers.background())
            .subscribe(
                viewState::showDetail
            ) {
                viewState.showMessage(it.message.toString())
            }.addTo(disposables)
    }

    override fun onDestroy() {
        disposables.dispose()
    }

    fun setTitle() {
        viewState.setTitle(gitHubRepository.name.uppercase())
    }
}