// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.screens.main

import android.os.Bundle
import android.view.View
import com.functionalkotlin.bandhookkotlin.di.ApplicationComponent
import com.functionalkotlin.bandhookkotlin.di.subcomponent.main.MainActivityModule
import com.functionalkotlin.bandhookkotlin.ui.activity.BaseActivity
import com.functionalkotlin.bandhookkotlin.ui.adapter.BaseAdapter
import com.functionalkotlin.bandhookkotlin.ui.adapter.ImageTitleAdapter
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.presenter.MainPresenter
import com.functionalkotlin.bandhookkotlin.ui.screens.detail.ArtistActivity
import com.functionalkotlin.bandhookkotlin.ui.util.navigate
import com.functionalkotlin.bandhookkotlin.ui.view.MainView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class MainActivity : BaseActivity<MainLayout>(), MainView {

    override val ui = MainLayout()

    @Inject
    lateinit var presenter: MainPresenter

    val adapter = ImageTitleAdapter { presenter.onArtistClicked(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui.recycler.adapter = adapter
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(MainActivityModule(this))
            .injectTo(this)
    }

    override fun onResume() {
        super.onResume()

        launch(UI) {
            presenter.onResume()
        }
    }

    override fun showArtists(artists: List<ImageTitle>) {
        adapter.items = artists
    }

    override fun navigateToDetail(id: String) {
        navigate<ArtistActivity>(id, findItemById(id), BaseActivity.IMAGE_TRANSITION_NAME)
    }

    private fun findItemById(id: String): View {
        val pos = adapter.findPositionById(id)
        val holder = ui.recycler.findViewHolderForLayoutPosition(pos)
            as BaseAdapter.BaseViewHolder<ImageTitleAdapter.Component>
        return holder.ui.image
    }
}
