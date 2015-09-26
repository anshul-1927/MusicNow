/*
 * Copyright (C) 2014 Kartik Sharma Licensed under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
 * or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */

package com.ksharkapps.musicnow.widgets;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageButton;

import com.ksharkapps.musicnow.R;
import com.ksharkapps.musicnow.MusicPlaybackService;
import com.ksharkapps.musicnow.utils.ApolloUtils;
import com.ksharkapps.musicnow.utils.MusicUtils;

/**
 * A custom {@link ImageButton} that represents the "repeat" button.
 * 
 * @author Kartik Sharma (kshark.apps@gmail.com)
 */
public class RepeatButton extends ImageButton implements OnClickListener, OnLongClickListener {

    /**
     * Repeat one theme resource
     */
    private static final String REPEAT_ALL = "btn_playback_repeat_all";

    /**
     * Repeat one theme resource
     */
    private static final String REPEAT_CURRENT = "btn_playback_repeat_one";

    /**
     * Repeat one theme resource
     */
    private static final String REPEAT_NONE = "btn_playback_repeat";

   

    /**
     * @param context The {@link Context} to use
     * @param attrs The attributes of the XML tag that is inflating the view.
     */
    @SuppressWarnings("deprecation")
    public RepeatButton(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        // Initialze the theme resources
        // Control playback (cycle repeat modes)
        setOnClickListener(this);
        // Show the cheat sheet
        setOnLongClickListener(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onClick(final View v) {
        MusicUtils.cycleRepeat();
        updateRepeatState();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onLongClick(final View view) {
        if (TextUtils.isEmpty(view.getContentDescription())) {
            return false;
        } else {
            ApolloUtils.showCheatSheet(view);
            return true;
        }
    }

    /**
     * Sets the correct drawable for the repeat state.
     */
    public void updateRepeatState() {
        switch (MusicUtils.getRepeatMode()) {
            case MusicPlaybackService.REPEAT_ALL:
                setContentDescription(getResources().getString(R.string.accessibility_repeat_all));
                setImageResource(R.drawable.btn_playback_repeat_all);
                break;
            case MusicPlaybackService.REPEAT_CURRENT:
                setContentDescription(getResources().getString(R.string.accessibility_repeat_one));
                setImageResource(R.drawable.btn_playback_repeat_one);
                break;
            case MusicPlaybackService.REPEAT_NONE:
                setContentDescription(getResources().getString(R.string.accessibility_repeat));
                setImageResource(R.drawable.btn_playback_repeat);
                break;
            default:
                break;
        }
    }
}
