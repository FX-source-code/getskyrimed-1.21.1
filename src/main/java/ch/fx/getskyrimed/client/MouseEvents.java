package ch.fx.getskyrimed.client;

import ch.fx.getskyrimed.network.HandActionPayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.network.PacketDistributor;

import static ch.fx.getskyrimed.GetSkyrimed.MOD_ID;

@EventBusSubscriber(modid = MOD_ID, value = Dist.CLIENT)
public class MouseEvents {

    @SubscribeEvent
    public static void mouseEvents(InputEvent.InteractionKeyMappingTriggered event) {
        if (event.isAttack()) {
            event.setCanceled(true);
            PacketDistributor.sendToServer(new HandActionPayload(true));
        }
    }
}
