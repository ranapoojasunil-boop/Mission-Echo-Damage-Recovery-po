package com.ranapoojsunil.mixin;

import java.util.function.Consumer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(
    targets = "de.maxhenkel.voicechat.voice.client.microphone.JavaxMicrophone",
    remap = false
)
public abstract class JavaxMicrophoneMixin {

    private static volatile Consumer<short[]> pcmListener;

    public static void setPcmListener(Consumer<short[]> listener) {
        pcmListener = listener;
    }

    @Inject(
        method = "read",
        at = @At("RETURN"),
        remap = false
    )
    private void missionEcho$capturePCM(
        CallbackInfoReturnable<short[]> cir
    ) {
        short[] samples = cir.getReturnValue();

        if (samples == null || samples.length == 0) {
            return;
        }

        Consumer<short[]> listener = pcmListener;

        if (listener != null) {
            try {
                listener.accept(samples.clone());
            } catch (Exception e) {
                // Keep microphone capture running.
            }
        }
    }
}
