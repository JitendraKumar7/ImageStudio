package com.imagestudio.interfaces;

import com.imagestudio.data.Album;
import com.imagestudio.data.Media;

import java.util.ArrayList;

public interface MediaClickListener {

    void onMediaClick(Album album, ArrayList<Media> media, int position);
}
