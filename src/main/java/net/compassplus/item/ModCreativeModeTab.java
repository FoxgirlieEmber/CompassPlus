package net.compassplus.item;

import net.compassplus.CompassPlus;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CompassPlus.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab COMPASSPLUS_TAB;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        COMPASSPLUS_TAB = event.registerCreativeModeTab(new ResourceLocation(CompassPlus.MODID, "compassplus_tab"),
                builder -> builder.icon(() -> new ItemStack(ModItems.PORTAL_COMPASS.get())).title(Component.literal("Compass Plus")).build());
    }
}