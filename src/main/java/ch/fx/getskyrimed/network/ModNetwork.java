package ch.fx.getskyrimed.network;

import ch.fx.getskyrimed.server.AttackHandler;
import net.minecraft.world.entity.Entity;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

import static ch.fx.getskyrimed.GetSkyrimed.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public class ModNetwork {

    @SubscribeEvent
    public static void registerPayloadHandlersEvent(RegisterPayloadHandlersEvent event){
        event.registrar("1").playToServer(HandActionPayload.TYPE, HandActionPayload.STREAM_CODEC,
                AttackHandler::leftHandAttack
        );
    }

}
