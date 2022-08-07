package net.frozenblock.spongeworld.mixin;

import net.frozenblock.spongeworld.SpongeWorld;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.noise.NoiseParametersKeys;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(VanillaSurfaceRules.class)
public abstract class VanillaSurfaceRulesMixin {

	@Shadow
	private static MaterialRules.MaterialRule block(Block block) {
		return MaterialRules.block(block.getDefaultState());
	}

	private static final MaterialRules.MaterialRule SPONGE = block(Blocks.SPONGE);

	@ModifyVariable(method = "createDefaultRule", at = @At("STORE"), ordinal = 8)
	private static MaterialRules.MaterialRule injected(MaterialRules.MaterialRule materialRule) {

		return SPONGE;
	}
}
