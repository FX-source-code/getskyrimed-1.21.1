package ch.fx.getskyrimed.network;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

import static ch.fx.getskyrimed.GetSkyrimed.MOD_ID;

public record HandActionPayload(boolean leftHand) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<HandActionPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(MOD_ID, "hand_action_payload"));

    public static final StreamCodec<ByteBuf, HandActionPayload> STREAM_CODEC = new StreamCodec<ByteBuf, HandActionPayload>() {
        @Override
        public HandActionPayload decode(ByteBuf buffer) {
            return new HandActionPayload(buffer.readBoolean());
        }

        @Override
        public void encode(ByteBuf buffer, HandActionPayload value) {
            buffer.writeBoolean(value.leftHand());
        }
    };

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
