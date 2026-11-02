package com.ranapoojsunil;

import android.media.audiofx.AcousticEchoCanceler;

public final class EchoProcessor {

    private AcousticEchoCanceler aec;

    public boolean enable(int audioSessionId) {
        try {
            if (!AcousticEchoCanceler.isAvailable()) return false;

            aec = AcousticEchoCanceler.create(audioSessionId);

            if (aec == null) return false;

            return aec.setEnabled(true) == android.media.audiofx.AudioEffect.SUCCESS;

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
