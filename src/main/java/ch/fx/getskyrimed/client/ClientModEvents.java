package ch.fx.getskyrimed.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import static ch.fx.getskyrimed.GetSkyrimed.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class ClientModEvents {

    @SubscribeEvent
    public static void registerKeyMappingsEvent(RegisterKeyMappingsEvent event){
        event.register(ModKeys.LEFT_HAND_MAPPING);
    }

}
