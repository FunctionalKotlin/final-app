// Copyright © FunctionalKotlin.com 2018. All rights reserved.

package com.functionalkotlin.bandhookkotlin.ui.screens.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.support.test.InstrumentationRegistry
import android.test.ActivityInstrumentationTestCase2
import android.test.UiThreadTest
import android.widget.ImageView
import com.functionalkotlin.bandhookkotlin.ui.activity.BaseActivity
import com.functionalkotlin.bandhookkotlin.ui.adapter.ArtistDetailPagerAdapter
import com.functionalkotlin.bandhookkotlin.ui.entity.ArtistDetail
import com.functionalkotlin.bandhookkotlin.ui.entity.ImageTitle
import com.functionalkotlin.bandhookkotlin.ui.presenter.ArtistPresenter
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.`when`
import org.mockito.Mockito.anyString
import org.mockito.Mockito.eq
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ArtistActivityTest : ActivityInstrumentationTestCase2<ArtistActivity>(
    ArtistActivity::class.java) {

    lateinit var presenter: ArtistPresenter
    lateinit var picasso: Picasso

    lateinit var artistActivity: ArtistActivity

    lateinit var image: ImageView
    lateinit var biographyFragment: BiographyFragment
    lateinit var albumsFragment: AlbumsFragment

    private val id = "id"

    public override fun setUp() {
        super.setUp()

        presenter = mock(ArtistPresenter::class.java)
        picasso = mock(Picasso::class.java)

        injectInstrumentation(InstrumentationRegistry.getInstrumentation())

        val intent = Intent(instrumentation.context, ArtistActivity::class.java)
        intent.putExtra(id, id)
        setActivityIntent(intent)

        artistActivity = activity

        image = artistActivity.ui.image
        biographyFragment = artistActivity.biographyFragment
        albumsFragment = artistActivity.albumsFragment
    }

    @SuppressLint("NewApi")
    fun testOnCreate() {
        // When created
        // Then
        assertNotNull(artistActivity.ui.image)
        assertEquals(BaseActivity.IMAGE_TRANSITION_NAME, artistActivity.ui.image.transitionName)
        assertNull(artistActivity.title)
        assertNotNull(artistActivity.ui.tabLayout)
        assertNotNull(artistActivity.ui.viewPager)
        assertEquals(
            ArtistDetailPagerAdapter::class.java, artistActivity.ui.viewPager.adapter.javaClass)
        assertEquals(
            biographyFragment, (artistActivity.ui.viewPager.adapter as ArtistDetailPagerAdapter)
            .getItem(0))
        assertEquals(
            albumsFragment, (artistActivity.ui.viewPager.adapter as ArtistDetailPagerAdapter)
            .getItem(1))
    }

    fun testViewPagerTitles() {
        // Given
        val desiredBioTitle = instrumentation.targetContext.getString(
            com.functionalkotlin.bandhookkotlin.R.string.bio_fragment_title)

        val desiredAlbumsTitle = instrumentation.targetContext.getString(
            com.functionalkotlin.bandhookkotlin.R.string.albums_fragment_title)

        // Then
        assertEquals(desiredBioTitle, artistActivity.ui.viewPager.adapter.getPageTitle(0))
        assertEquals(desiredAlbumsTitle, artistActivity.ui.viewPager.adapter.getPageTitle(1))
    }

    @UiThreadTest
    fun testLifecycleDelegatedToPresenter() {
        // Given
        artistActivity.presenter = presenter

        // When
        instrumentation.callActivityOnResume(artistActivity)
        instrumentation.callActivityOnPause(artistActivity)

        // Then
        verify(presenter).init(id)
    }

    @UiThreadTest
    fun testShowArtist() {
        // Given
        artistActivity.picasso = picasso
        val picassoRequestCreator = mock(RequestCreator::class.java)
        `when`(picasso.load(anyString())).thenReturn(picassoRequestCreator)
        `when`(picassoRequestCreator.fit()).thenReturn(picassoRequestCreator)
        `when`(picassoRequestCreator.centerCrop()).thenReturn(picassoRequestCreator)
        val picassoCallbackArgumentCaptor = ArgumentCaptor.forClass(
            Callback.EmptyCallback::class.java)
        val artistDetail = ArtistDetail("artist id", "name", "url", "bio")

        // When
        artistActivity.showArtist(artistDetail)

        // Then
        verify(picasso).load(artistDetail.url)
        verify(picassoRequestCreator).into(eq(image), picassoCallbackArgumentCaptor.capture())

        // When
        picassoCallbackArgumentCaptor.value.onSuccess()

        // Then
        assertEquals(artistDetail.name, artistActivity.supportActionBar?.title)
        assertEquals(artistDetail.bio, biographyFragment.getBiographyText())
    }

    @UiThreadTest
    fun testShowAlbums() {
        // Given
        val albumImageTitles = listOf(ImageTitle("album id", "album name", "album url"))

        // When
        artistActivity.showAlbums(albumImageTitles)

        // Then
        assertEquals(albumImageTitles, albumsFragment.adapter?.items)
    }
}
