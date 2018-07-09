package com.imagestudio.data.filter;

import com.imagestudio.data.Media;

/**
 * Created by dnld on 4/10/17.
 */

public interface IMediaFilter {
    boolean accept(Media media);
}
