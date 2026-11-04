package com.ranapoojsunil;

import android.media.audiofx.AcousticEchoCanceler;
import android.media.audiofx.NoiseSuppressor;

public final class MicrophonePcmBridge {

    private static AcousticEchoCanceler aec;
    private static NoiseSuppressor noiseSuppressor;

    private MicrophonePcmBridge() {
    }

    public static synchronized boolean initialize(int audioSessionId) {
        release();

        try {
            if (AcousticEchoCanceler.isAvailable()) {
                aec = AcousticEchoCanceler.create(audioSessionId);

                if (aec != null) {
                    aec.setEnabled(true);
                }
            }

            if (NoiseSuppressor.isAvailable()) {
                noiseSuppressor = NoiseSuppressor.create(audioSessionId);

                if (noiseSuppressor != null) {
                    noiseSuppressor.setEnabled(true);
                }
            }

            return aec != null || noiseSuppressor != null;

        } catch (Exception e) {
            release();
            return false;
        }
    }

    public static synchronized void release() {
        if (aec != null) {
            aec.release();
            aec = null;
        }

        if (noiseSuppressor != null) {
            noiseSuppressor.release();
            noiseSuppressor = null;
        }
    }
}
