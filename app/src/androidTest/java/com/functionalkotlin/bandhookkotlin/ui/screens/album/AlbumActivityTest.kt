// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.screens.album

import android.annotation.SuppressLint
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.test.ActivityInstrumentationTestCase2
import android.test.UiThreadTest
import android.widget.ImageView
import com.functionalkotlin.bandhookkotlin.domain.entity.Track
import com.functionalkotlin.bandhookkotlin.ui.activity.BaseActivity
import com.functionalkotlin.bandhookkotlin.ui.entity.AlbumDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.mapper.track.TrackDataMapper
import com.functionalkotlin.bandhookkotlin.ui.presenter.AlbumPresenter
import com.functionalkotlin.bandhookkotlin.ui.screens.detail.ArtistActivity
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.verify

class AlbumActivityTest : ActivityInstrumentationTestCase2<AlbumActivity>(
    AlbumActivity::class.java) {

    lateinit var presenter: AlbumPresenter
    lateinit var picasso: Picasso

    lateinit var albumActivity: AlbumActivity

    lateinit var image: ImageView

    private val id = "id"

    public override fun setUp() {
        super.setUp()

        presenter = mock(AlbumPresenter::class.java)
        picasso = mock(Picasso::class.java)

        injectInstrumentation(InstrumentationRegistry.getInstrumentation())

        val intent = Intent(instrumentation.context, ArtistActivity::class.java)
        intent.putExtra(id, id)
        setActivityIntent(intent)

        albumActivity = activity

        image = albumActivity.ui.image
    }

    @SuppressLint("NewApi")
    fun testOnCreate() {
        // When created
        // Then
        assertNotNull(albumActivity.ui.image)
        assertNotNull(albumActivity.ui.trackList)
        assertNotNull(albumActivity.ui.listCard)
        assertNotNull(albumActivity.albumListBreakingEdgeHeight)
        assertNotNull(albumActivity.trackDataMapper)
        assertNotNull(albumActivity.presenter)
        assertNotNull(albumActivity.adapter)
        assertNull(albumActivity.title)
        assertEquals(BaseActivity.IMAGE_TRANSITION_NAME, albumActivity.ui.image.transitionName)
        assertEquals(albumActivity.adapter, albumActivity.ui.trackList.adapter)
        assertEquals(
            -albumActivity.albumListBreakingEdgeHeight, albumActivity.ui.listCard.translationY)
    }

    @UiThreadTest
    fun testLifecycleDelegatedToPresenter() {
        // Given
        albumActivity.presenter = presenter

        // When
        instrumentation.callActivityOnResume(albumActivity)
        instrumentation.callActivityOnPause(albumActivity)

        // Then
        verify(presenter).onPause()
        verify(presenter).onResume()
        verify(presenter).init(id)
    }

    @UiThreadTest
    fun testShowAlbum() {
        // Given
        albumActivity.picasso = picasso
        val picassoRequestCreator = mock(RequestCreator::class.java)
        `when`(picasso.load(anyString())).thenReturn(picassoRequestCreator)
        `when`(picassoRequestCreator.fit()).thenReturn(picassoRequestCreator)
        `when`(picassoRequestCreator.centerCrop()).thenReturn(picassoRequestCreator)
        val picassoCallbackArgumentCaptor = ArgumentCaptor.forClass(
            Callback.EmptyCallback::class.java)
        val track = Track("track name", 10)
        val albumDetail = AlbumDetail("album id", "name", "url", listOf(track, track))

        // When
        albumActivity.showAlbum(albumDetail)

        // Then
        verify(picasso).load(albumDetail.url)
        verify(picassoRequestCreator).fit()
        verify(picassoRequestCreator).into(eq(image), picassoCallbackArgumentCaptor.capture())

        // When
        picassoCallbackArgumentCaptor.value.onSuccess()

        // Then
        assertEquals(albumActivity.adapter.items, TrackDataMapper().transform(albumDetail.tracks))
    }

    @UiThreadTest
    fun testShowAlbumNothingToShow() {
        // Given
        albumActivity.picasso = picasso

        // When
        albumActivity.showAlbum(null)

        // Then
        verify(picasso, never()).load(anyString())
        assertTrue(albumActivity.isFinishing)
    }
}

