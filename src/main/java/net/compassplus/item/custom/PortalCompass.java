package net.compassplus.item.custom;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.item.CompassItemPropertyFunction;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.NbtUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.village.poi.PoiTypes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

public class PortalCompass extends Item {
    public PortalCompass(Properties properties) {
        super(properties);
        new ResourceLocation("angle");
        }

    public void inventoryTick(ItemStack stack, Level level, Entity player, int p_40723_, boolean p_40724_) {
        if (!level.isClientSide) {
            CompoundTag storedpos = stack.getOrCreateTag();
            CompoundTag storeddim = stack.getOrCreateTag();
            BlockPos pos = new BlockPos(player.getBlockX(), player.getBlockY(), player.getBlockZ());
            BlockState state = level.getBlockState(pos);
            if (state.getBlock() == Blocks.NETHER_PORTAL) {
                storedpos.put("PortalPos", NbtUtils.writeBlockPos(pos));
                ResourceKey<Level> dimension = level.dimension();
                storeddim.putString("dim", dimension.toString());
            }
            if (storedpos.contains("PortalPos") && !storedpos.getBoolean("PortalPos")) {

                CompoundTag subtagpos = stack.getOrCreateTagElement("PortalPos");
                BlockPos portalpos = NbtUtils.readBlockPos(subtagpos);
                if(testDim(stack.getTag().getString("dim"), level)) {
                    CompassItemPropertyFunction(GlobalPos.of(level.dimension(), portalpos));
                    CompassItemPropertyFunction.unclampedCall(stack, level.dimension(), player, 0);
                }
            }
            BlockPos blockpos = NbtUtils.readBlockPos(storedpos.getCompound("PortalPos"));
            if (!level.isInWorldBounds(blockpos) || !((ServerLevel) level).getPoiManager().existsAtPosition(PoiTypes.NETHER_PORTAL, blockpos)) {
                storedpos.remove("PortalPos");
            }
        }
    }

    public boolean testDim(String dima, Level level) {
        String dimb = level.dimension().toString();
        return(dima == dimb);
    }
}
