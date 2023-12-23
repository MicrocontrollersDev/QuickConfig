package dev.microcontrollers.quickconfig.mixin;

import cc.polyfrost.oneconfig.gui.OneConfigGui;
import cc.polyfrost.oneconfig.platform.Platform;
import cc.polyfrost.oneconfig.utils.TickDelay;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FMLClientHandler.class, remap = false)
public class FMLClientHandlerMixin {
    @Inject(method = "showInGameModOptions(Lnet/minecraft/client/gui/GuiIngameMenu;)V", at = @At("HEAD"), cancellable = true)
    private void showOneConfig(GuiIngameMenu guiIngameMenu, CallbackInfo ci) {
        new TickDelay(() -> Platform.getGuiPlatform().setCurrentScreen(OneConfigGui.create()), 1);
        ci.cancel();
    }
}
