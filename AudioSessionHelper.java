package com.ranapoojsunil;

import android.media.AudioRecord;

public final class AudioSessionHelper {

    private AudioSessionHelper() {
    }

    public static int getSessionId(AudioRecord audioRecord) {
        if (audioRecord == null) {
            return -1;
        }

        return audioRecord.getAudioSessionId();
    }
}
