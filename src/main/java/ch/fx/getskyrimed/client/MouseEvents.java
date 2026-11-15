package ch.fx.getskyrimed.client;

import ch.fx.getskyrimed.network.HandActionPayload;
import net.minecraft.client.Minecraft;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
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
            HitResult hitResult = Minecraft.getInstance().hitResult;

            if (hitResult == null) {
                return;
            }

            if (hitResult.getType() != HitResult.Type.BLOCK) {
                event.setCanceled(true);
                event.setSwingHand(false);

                if (hitResult instanceof EntityHitResult entityHit){
                    PacketDistributor.sendToServer(new HandActionPayload(true, entityHit.getEntity().getId()));
                }
                else {
                    PacketDistributor.sendToServer(new HandActionPayload(true, -1));
                }
            }
        }
    }
}
