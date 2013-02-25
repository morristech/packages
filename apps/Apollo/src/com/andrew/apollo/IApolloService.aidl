package com.andrew.apollo;

import android.graphics.Bitmap;

interface IApolloService
{
    void openFile(String path);
    void open(in long [] list, int position);
<<<<<<< HEAD
    long getIdFromPath(String path);
    int getQueuePosition();
    boolean isPlaying();
=======
>>>>>>> 1ad22a3ff979c349d80add0f56eaa888421aec75
    void stop();
    void pause();
    void play();
    void prev();
    void next();
    void enqueue(in long [] list, int action);
    void setQueuePosition(int index);
    void setShuffleMode(int shufflemode);
    void setRepeatMode(int repeatmode);
    void moveQueueItem(int from, int to);
    void toggleFavorite();
    void refresh();
    boolean isFavorite();
    boolean isPlaying();
    long [] getQueue();
    long duration();
    long position();
    long seek(long pos);
    long getAudioId();
    long getArtistId();
    long getAlbumId();
    Bitmap getAlbumBitmap();
    String getArtistName();
    String getTrackName();
    String getAlbumName();
    String getPath();
    int getQueuePosition();
    int getShuffleMode();
    int removeTracks(int first, int last);
    int removeTrack(long id); 
    int getRepeatMode();
    int getMediaMountedCount();
    int getAudioSessionId();
}

