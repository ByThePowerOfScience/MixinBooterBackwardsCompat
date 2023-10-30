package zone.rong.mixinbooter.mixin;

import net.minecraftforge.fml.common.ModClassLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.File;
import java.util.List;

@Mixin(ModClassLoader.class)
abstract class ModClassLoaderMixin {
    @Shadow
    private List<File> sources;
    
    @Inject(
            remap = false,
            method = "addFile",
            at = @At("HEAD"),
            cancellable = true
    )
    private void preventDuplicates(File modFile, CallbackInfo ci) {
        if (this.sources.contains(modFile))
            ci.cancel();
    }
}
