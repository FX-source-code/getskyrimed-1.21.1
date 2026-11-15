package ch.fx.getskyrimed.server;

import ch.fx.getskyrimed.network.HandActionPayload;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class AttackHandler {

    public static void leftHandAttack(HandActionPayload payload, IPayloadContext context) {

        Player player = context.player();

        Entity entityHit = player.level().getEntity(payload.hitId());

        player.swing(InteractionHand.OFF_HAND, true);

        if (payload.hitId() == -1) {
            player.sendSystemMessage(Component.literal("Miss"));

        } else if (entityHit != null && player.canInteractWithEntity(entityHit, 1)) {
            player.sendSystemMessage(Component.literal("An Entity was hit : " + entityHit.getName().getString()));
            damageEntity(entityHit, player, player.getItemInHand(InteractionHand.OFF_HAND));
        } else {
            player.sendSystemMessage(Component.literal("How the heck"));
        }
    }

    public static void damageEntity(Entity entityHit, Player player, ItemStack item){
        if (!(entityHit instanceof LivingEntity living)) {
            return;
        }

        double damage = player.getAttributeBaseValue(Attributes.ATTACK_DAMAGE);

        for (ItemAttributeModifiers.Entry entry :item.getAttributeModifiers().modifiers()) {
            if (entry.attribute().equals(Attributes.ATTACK_DAMAGE) && entry.modifier().operation().equals(AttributeModifier.Operation.ADD_VALUE)){
                damage += entry.modifier().amount();
            }
        }


        living.hurt(player.damageSources().playerAttack(player), (float) damage);
    }
}
