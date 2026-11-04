
package com.ranapoojsunil;

import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.media.AudioFormat;

public final class MicrophoneRecorder {

    private AudioRecord audioRecord;

    public boolean initialize() {
        int sampleRate = 44100;

        int minBuffer = AudioRecord.getMinBufferSize(
                sampleRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT
        );

        if (minBuffer <= 0) {
            return false;
        }

        audioRecord = new AudioRecord(
                MediaRecorder.AudioSource.VOICE_COMMUNICATION,
                sampleRate,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT,
                minBuffer * 2
        );

        if (audioRecord.getState()
                != AudioRecord.STATE_INITIALIZED) {
            release();
            return false;
        }

        MicrophonePcmBridge.initialize(
                audioRecord.getAudioSessionId()
        );

        return true;
    }

    public void start() {
        if (audioRecord != null) {
            audioRecord.startRecording();
        }
    }

    public void release() {
        MicrophonePcmBridge.release();

        if (audioRecord != null) {
            if (audioRecord.getRecordingState()
                    == AudioRecord.RECORDSTATE_RECORDING) {
                audioRecord.stop();
            }

            audioRecord.release();
            audioRecord = null;
        }
    }
}
