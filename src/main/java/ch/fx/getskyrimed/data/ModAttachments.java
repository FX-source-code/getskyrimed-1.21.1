package ch.fx.getskyrimed.data;

import net.minecraft.network.codec.ByteBufCodecs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import static ch.fx.getskyrimed.GetSkyrimed.MOD_ID;

public class ModAttachments {

    public static final DeferredRegister<AttachmentType<?>> DEFERRED_REGISTER = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<Long>> LEFT_HAND_LAST_ATTACK = DEFERRED_REGISTER.register("left_hand_last_attack", () -> AttachmentType.builder(() -> -1_000_000_000_000L).sync(ByteBufCodecs.VAR_LONG).build());

    public static void register(IEventBus eventBus){
        DEFERRED_REGISTER.register(eventBus);
    }
}
