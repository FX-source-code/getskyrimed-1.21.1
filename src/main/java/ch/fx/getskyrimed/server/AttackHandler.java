package ch.fx.getskyrimed.server;

import ch.fx.getskyrimed.data.ModAttachments;
import ch.fx.getskyrimed.network.HandActionPayload;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public class AttackHandler {

    public static double getAttributeValueWithItem(Player player, ItemStack item, Holder<Attribute> wantedAttribute) {

        double total = player.getAttributeBaseValue(wantedAttribute);

        for (ItemAttributeModifiers.Entry entry :item.getAttributeModifiers().modifiers()) {
            if (entry.attribute().equals(wantedAttribute) && entry.modifier().operation().equals(AttributeModifier.Operation.ADD_VALUE)){
                total += entry.modifier().amount();
            }
        }

        return total;
    }

    public static void leftHandAttack(HandActionPayload payload, IPayloadContext context) {

        Player player = context.player();

        ItemStack offHandItem = player.getItemInHand(InteractionHand.OFF_HAND);
        Entity entityHit = player.level().getEntity(payload.hitId());
        long currentTick = player.level().getGameTime();

        final long attackCooldown = Math.max(1, Math.round(20 / Math.max(0.05,getAttributeValueWithItem(player, offHandItem, Attributes.ATTACK_SPEED))));

        player.swing(InteractionHand.OFF_HAND, true);

        double chargeRatio = Math.min(Math.max(0, (double) (currentTick - player.getData(ModAttachments.LEFT_HAND_LAST_ATTACK)) / attackCooldown), 1);

        player.setData(ModAttachments.LEFT_HAND_LAST_ATTACK, currentTick);

        if (payload.hitId() == -1) {
            player.sendSystemMessage(Component.literal("Miss"));

        } else if (entityHit != null && player.canInteractWithEntity(entityHit, 1)) {
            player.sendSystemMessage(Component.literal("An Entity was hit : " + entityHit.getName().getString()));
            damageEntity(entityHit, player, offHandItem, chargeRatio);

        } else {
            player.sendSystemMessage(Component.literal("How the heck"));
        }
    }

    public static void damageEntity(Entity entityHit, Player player, ItemStack item, double chargeRatio){
        if (!(entityHit instanceof LivingEntity living)) {
            return;
        }

        double damage = getAttributeValueWithItem(player, item, Attributes.ATTACK_DAMAGE) * (0.2 + chargeRatio * chargeRatio * 0.8);

        living.hurt(player.damageSources().playerAttack(player), (float) damage);
    }
}
