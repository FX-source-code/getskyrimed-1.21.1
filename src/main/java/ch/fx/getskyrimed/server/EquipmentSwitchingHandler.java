package ch.fx.getskyrimed.server;


import ch.fx.getskyrimed.data.ModAttachments;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingEquipmentChangeEvent;

import static ch.fx.getskyrimed.GetSkyrimed.MOD_ID;

@EventBusSubscriber(modid = MOD_ID)
public class EquipmentSwitchingHandler {

    @SubscribeEvent
    public static void onEquipmentChange(LivingEquipmentChangeEvent event){
        if (event.getSlot() != EquipmentSlot.OFFHAND || !(event.getEntity() instanceof Player) || ItemStack.isSameItem(event.getFrom(), event.getTo())) {
            return;
        }
        event.getEntity().setData(ModAttachments.LEFT_HAND_LAST_ATTACK, event.getEntity().level().getGameTime());
    }
}
