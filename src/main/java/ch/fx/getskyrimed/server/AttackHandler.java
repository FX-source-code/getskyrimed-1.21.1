package ch.fx.getskyrimed.server;

import ch.fx.getskyrimed.network.HandActionPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class AttackHandler {

    public static void leftHandAttack(HandActionPayload payload, IPayloadContext context) {

        Player player = context.player();

        Entity entityHit = player.level().getEntity(payload.hitId());

        if (entityHit != null && player.canInteractWithEntity(entityHit, 1)) {
            player.sendSystemMessage(Component.literal("An Entity was hit : " + entityHit.getName().getString()));
        } else if (payload.hitId() == -1) {
            player.sendSystemMessage(Component.literal("Miss"));
        } else {
            player.sendSystemMessage(Component.literal("How the heck"));
        }
    }
}
