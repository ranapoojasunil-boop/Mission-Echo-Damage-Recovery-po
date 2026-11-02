package com.ranapoojsunil;

import android.media.audiofx.AcousticEchoCanceler;

public final class EchoProcessor {

    private AcousticEchoCanceler aec;

    public boolean enableAEC(int audioSessionId) {
        try {
            if (!AcousticEchoCanceler.isAvailable()) {
                return false;
            }

            aec = AcousticEchoCanceler.create(audioSessionId);

            if (aec == null) {
                return false;
            }

            aec.setEnabled(true);
            return aec.getEnabled();

        } catch (Exception e) {
            return false;
        }
    }

    public void release() {
        if (aec != null) {
            aec.release();
            aec = null;
        }
    }
}
