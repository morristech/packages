/*
 * Copyright (C) 2012 Andrew Neal Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.andrew.apollo.adapters;

<<<<<<< HEAD
import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.AnimationDrawable;
import android.os.RemoteException;
import android.support.v4.widget.SimpleCursorAdapter;
=======
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
>>>>>>> 1ad22a3ff979c349d80add0f56eaa888421aec75
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
<<<<<<< HEAD
import com.andrew.apollo.R;
import com.andrew.apollo.grid.fragments.AlbumsFragment;
import com.andrew.apollo.utils.ImageUtils;
=======
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.andrew.apollo.Config;
import com.andrew.apollo.R;
import com.andrew.apollo.cache.ImageFetcher;
import com.andrew.apollo.model.Album;
import com.andrew.apollo.ui.MusicHolder;
import com.andrew.apollo.ui.MusicHolder.DataHolder;
import com.andrew.apollo.utils.ApolloUtils;
>>>>>>> 1ad22a3ff979c349d80add0f56eaa888421aec75
import com.andrew.apollo.utils.MusicUtils;

import java.lang.ref.WeakReference;

/**
 * This {@link ArrayAdapter} is used to display all of the albums on a user's
 * device for {@link RecentsFragment} and {@link AlbumsFragment}.
 * 
 * @author Andrew Neal (andrewdneal@gmail.com)
 */
<<<<<<< HEAD
public class AlbumAdapter extends SimpleCursorAdapter {
=======
public class AlbumAdapter extends ArrayAdapter<Album> {

    /**
     * Number of views (ImageView and TextView)
     */
    private static final int VIEW_TYPE_COUNT = 2;

    /**
     * The resource Id of the layout to inflate
     */
    private final int mLayoutId;

    /**
     * Image cache and image fetcher
     */
    private final ImageFetcher mImageFetcher;

    /**
     * Semi-transparent overlay
     */
    private final int mOverlay;

    /**
     * Determines if the grid or list should be the default style
     */
    private boolean mLoadExtraData = false;

    /**
     * Sets the album art on click listener to start playing them album when
     * touched.
     */
    private boolean mTouchPlay = false;

    /**
     * Used to cache the album info
     */
    private DataHolder[] mData;

    /**
     * Constructor of <code>AlbumAdapter</code>
     * 
     * @param context The {@link Context} to use.
     * @param layoutId The resource Id of the view to inflate.
     * @param style Determines which layout to use and therefore which items to
     *            load.
     */
    public AlbumAdapter(final Activity context, final int layoutId) {
        super(context, 0);
        // Get the layout Id
        mLayoutId = layoutId;
        // Initialize the cache & image fetcher
        mImageFetcher = ApolloUtils.getImageFetcher(context);
        // Cache the transparent overlay
        mOverlay = context.getResources().getColor(R.color.list_item_background);
    }
>>>>>>> 1ad22a3ff979c349d80add0f56eaa888421aec75

    /**
     * {@inheritDoc}
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        // Recycle ViewHolder's items
        MusicHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(mLayoutId, parent, false);
            holder = new MusicHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (MusicHolder)convertView.getTag();
        }

        // Retrieve the data holder
        final DataHolder dataHolder = mData[position];

        // Set each album name (line one)
        holder.mLineOne.get().setText(dataHolder.mLineOne);
        // Set the artist name (line two)
        holder.mLineTwo.get().setText(dataHolder.mLineTwo);
        // Asynchronously load the album images into the adapter
        mImageFetcher.loadAlbumImage(dataHolder.mLineTwo, dataHolder.mLineOne, dataHolder.mItemId,
                holder.mImage.get());
        // List view only items
        if (mLoadExtraData) {
            // Make sure the background layer gets set
            holder.mOverlay.get().setBackgroundColor(mOverlay);
            // Set the number of songs (line three)
            holder.mLineThree.get().setText(dataHolder.mLineThree);
            // Asynchronously load the artist image on the background view
            mImageFetcher.loadArtistImage(dataHolder.mLineTwo, holder.mBackground.get());
        }
        if (mTouchPlay) {
            // Play the album when the artwork is touched
            playAlbum(holder.mImage.get(), position);
        }
        return convertView;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasStableIds() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getViewTypeCount() {
        return VIEW_TYPE_COUNT;
    }

    /**
     * Method used to cache the data used to populate the list or grid. The idea
     * is to cache everything before {@code #getView(int, View, ViewGroup)} is
     * called.
     */
    public void buildCache() {
        mData = new DataHolder[getCount()];
        for (int i = 0; i < getCount(); i++) {
            // Build the album
            final Album album = getItem(i);

            // Build the data holder
            mData[i] = new DataHolder();
            // Album Id
            mData[i].mItemId = album.mAlbumId;
            // Album names (line one)
            mData[i].mLineOne = album.mAlbumName;
            // Album artist names (line two)
            mData[i].mLineTwo = album.mArtistName;
            // Number of songs for each album (line three)
            mData[i].mLineThree = MusicUtils.makeLabel(getContext(),
                    R.plurals.Nsongs, album.mSongNumber);
        }
    }

    /**
     * Starts playing an album if the user touches the artwork in the list.
     * 
     * @param album The {@link ImageView} holding the album
     * @param position The position of the album to play.
     */
    private void playAlbum(final ImageView album, final int position) {
        album.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View v) {
                final long id = getItem(position).mAlbumId;
                final long[] list = MusicUtils.getSongListForAlbum(getContext(), id);
                MusicUtils.playAll(getContext(), list, 0, false);
            }
        });
    }

    /**
     * Method that unloads and clears the items in the adapter
     */
    public void unload() {
        clear();
        mData = null;
    }

    /**
     * @param pause True to temporarily pause the disk cache, false otherwise.
     */
    public void setPauseDiskCache(final boolean pause) {
        if (mImageFetcher != null) {
            mImageFetcher.setPauseDiskCache(pause);
        }
    }

    /**
     * @param album The key used to find the cached album to remove
     */
    public void removeFromCache(final Album album) {
        if (mImageFetcher != null) {
            mImageFetcher.removeFromCache(album.mAlbumName + Config.ALBUM_ART_SUFFIX);
        }
    }

    /**
     * Flushes the disk cache.
     */
    public void flush() {
        mImageFetcher.flush();
    }

    /**
     * @param extra True to load line three and the background image, false
     *            otherwise.
     */
    public void setLoadExtraData(final boolean extra) {
        mLoadExtraData = extra;
        setTouchPlay(true);
    }

<<<<<<< HEAD
        ImageUtils.setAlbumImage(viewholder.mViewHolderImage, artistName, albumName);

        // Now playing indicator
        long currentalbumid = MusicUtils.getCurrentAlbumId();
        long albumid = mCursor.getLong(AlbumsFragment.mAlbumIdIndex);
        if (currentalbumid == albumid) {
            holderReference.get().mPeakOne.setImageResource(R.anim.peak_meter_1);
            holderReference.get().mPeakTwo.setImageResource(R.anim.peak_meter_2);
            mPeakOneAnimation = (AnimationDrawable)holderReference.get().mPeakOne.getDrawable();
            mPeakTwoAnimation = (AnimationDrawable)holderReference.get().mPeakTwo.getDrawable();
            try {
                if (MusicUtils.mService.isPlaying()) {
                    mPeakOneAnimation.start();
                    mPeakTwoAnimation.start();
                } else {
                    mPeakOneAnimation.stop();
                    mPeakTwoAnimation.stop();
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        } else {
            holderReference.get().mPeakOne.setImageResource(0);
            holderReference.get().mPeakTwo.setImageResource(0);
        }
        return view;
=======
    /**
     * @param play True to play the album when the artwork is touched, false
     *            otherwise.
     */
    public void setTouchPlay(final boolean play) {
        mTouchPlay = play;
>>>>>>> 1ad22a3ff979c349d80add0f56eaa888421aec75
    }
}
