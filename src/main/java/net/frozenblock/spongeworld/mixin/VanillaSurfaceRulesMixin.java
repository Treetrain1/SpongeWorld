package net.frozenblock.spongeworld.mixin;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import net.minecraft.world.gen.surfacebuilder.VanillaSurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(VanillaSurfaceRules.class)
public abstract class VanillaSurfaceRulesMixin {

	@Shadow
	private static MaterialRules.MaterialRule block(Block block) {
		return MaterialRules.block(block.getDefaultState());
	}

	private static final MaterialRules.MaterialRule SPONGE = block(Blocks.SPONGE);

	@ModifyVariable(method = "createDefaultRule", at = @At("STORE"), ordinal = 8)
	private static MaterialRules.MaterialRule overworld(MaterialRules.MaterialRule materialRule) {

		return SPONGE;
	}

	@Inject(method = "createNetherSurfaceRule", at  = @At("HEAD"), cancellable = true)
	private static void nether(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {

		cir.setReturnValue(SPONGE);
		cir.cancel();
	}

	@Inject(method = "getEndStoneRule", at = @At("HEAD"), cancellable = true)
	private static void getEndStoneRule(CallbackInfoReturnable<MaterialRules.MaterialRule> cir) {
		cir.setReturnValue(SPONGE);
		cir.cancel();
	}
}
