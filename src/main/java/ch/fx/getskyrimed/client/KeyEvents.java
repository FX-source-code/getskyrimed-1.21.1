package ch.fx.getskyrimed.client;

import ch.fx.getskyrimed.network.HandActionPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static ch.fx.getskyrimed.GetSkyrimed.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class KeyEvents {

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        while (ModKeys.LEFT_HAND_MAPPING.consumeClick()) {
            PacketDistributor.sendToServer(new HandActionPayload(true));
        }
    }
}
