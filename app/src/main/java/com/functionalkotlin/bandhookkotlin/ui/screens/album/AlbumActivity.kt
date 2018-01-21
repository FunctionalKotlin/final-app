// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.screens.album

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.annotation.VisibleForTesting
import android.support.v7.widget.LinearLayoutManager
import android.view.MenuItem
import android.view.ViewPropertyAnimator
import android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
import com.functionalkotlin.bandhookkotlin.R
import com.functionalkotlin.bandhookkotlin.di.ApplicationComponent
import com.functionalkotlin.bandhookkotlin.di.subcomponent.album.AlbumActivityModule
import com.functionalkotlin.bandhookkotlin.domain.entity.AlbumNotFound
import com.functionalkotlin.bandhookkotlin.ui.activity.BaseActivity
import com.functionalkotlin.bandhookkotlin.ui.adapter.TracksAdapter
import com.functionalkotlin.bandhookkotlin.ui.entity.AlbumDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.TrackDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.track.transform
import com.functionalkotlin.bandhookkotlin.ui.presenter.AlbumPresenter
import com.functionalkotlin.bandhookkotlin.ui.util.getNavigationId
import com.functionalkotlin.bandhookkotlin.ui.util.into
import com.functionalkotlin.bandhookkotlin.ui.util.supportsLollipop
import com.functionalkotlin.bandhookkotlin.ui.view.AlbumView
import com.squareup.picasso.Picasso
import org.jetbrains.anko.dimen
import javax.inject.Inject

class AlbumActivity : BaseActivity<AlbumLayout>(), AlbumView {

    override val ui = AlbumLayout()

    companion object {
        private const val LIST_ANIMATION_ON_START_DELAY = 500L
        private const val NO_TRANSLATION = 0f
        private const val TRANSPARENT = 0f
    }

    val albumListBreakingEdgeHeight by lazy { dimen(R.dimen.album_breaking_edge_height).toFloat() }

    @Inject
    @VisibleForTesting
    lateinit var presenter: AlbumPresenter

    @Inject
    lateinit var adapter: TracksAdapter

    @Inject
    lateinit var layoutManager: LinearLayoutManager

    @Inject
    lateinit var picasso: Picasso

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpTransition()
        setUpActionBar()
        setUpTrackList()
    }

    override fun injectDependencies(applicationComponent: ApplicationComponent) {
        applicationComponent.plus(AlbumActivityModule(this))
            .injectTo(this)
    }

    @SuppressLint("NewApi")
    private fun setUpTransition() {
        supportPostponeEnterTransition()
        supportsLollipop { ui.image.transitionName = IMAGE_TRANSITION_NAME }
    }

    private fun setUpTrackList() {
        ui.trackList.adapter = adapter
        ui.trackList.layoutManager = layoutManager
        ui.listCard.translationY = -albumListBreakingEdgeHeight
    }

    private fun setUpActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = null
    }

    override fun onResume() {
        super.onResume()
        presenter.init(getNavigationId())
    }

    override fun showAlbum(albumDetail: AlbumDetail?) {

        albumDetail?.let(this::updateAlbumDetail)
            ?: postponeTransitions()
    }

    override fun showAlbumNotFound(e: AlbumNotFound) {
        supportStartPostponedEnterTransition()
        supportFinishAfterTransition()
    }

    private fun updateAlbumDetail(albumDetail: AlbumDetail) {
        picasso.load(albumDetail.url).fit().centerCrop().into(ui.image) {
            makeStatusBarTransparent()
            supportStartPostponedEnterTransition()
            populateTrackList(transform(albumDetail.tracks))
            animateTrackListUp()
        }
    }

    private fun postponeTransitions() {
        supportStartPostponedEnterTransition()
        supportFinishAfterTransition()
    }

    private fun animateTrackListUp() {
        ui.listCard.animate().setStartDelay(LIST_ANIMATION_ON_START_DELAY).translationY(NO_TRANSLATION)
    }

    private fun populateTrackList(trackDetails: List<TrackDetail>) {
        adapter.items = trackDetails
    }

    @SuppressLint("InlinedApi")
    private fun makeStatusBarTransparent() {
        supportsLollipop {
            window.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item != null && item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        ui.listCard.animate().alpha(TRANSPARENT)
            .onAnimationEnd { supportFinishAfterTransition() }
    }
}

inline fun ViewPropertyAnimator.onAnimationEnd(
    crossinline continuation: (Animator) -> Unit) {

    setListener(object : AnimatorListenerAdapter() {
        override fun onAnimationEnd(animation: Animator) {
            continuation(animation)
        }
    })

}
