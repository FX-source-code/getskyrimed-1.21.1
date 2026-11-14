package ch.fx.getskyrimed.server;

import ch.fx.getskyrimed.network.HandActionPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class AttackHandler {

    public static void leftHandAttack(HandActionPayload payload, IPayloadContext context) {
        Entity entityHit = context.player().level().getEntity(payload.hitId());
        if (entityHit != null && entityHit.distanceTo(context.player()) <= context.player().entityInteractionRange() + 1) {
            context.player().sendSystemMessage(Component.literal("An Entity was hit : " + entityHit.getName()));
        } else {
            context.player().sendSystemMessage(Component.literal("Miss"));
        }
    }
}
