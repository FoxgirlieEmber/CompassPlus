package net.compassplus.item;

import net.compassplus.CompassPlus;
import net.compassplus.item.custom.PortalCompass;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, CompassPlus.MODID);

    public static final RegistryObject<Item> PORTAL_COMPASS = ITEMS.register("portal_compass",
            () -> new PortalCompass(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
