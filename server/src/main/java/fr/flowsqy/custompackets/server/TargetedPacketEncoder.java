package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class TargetedPacketEncoder extends MessageToByteEncoder<TargetedRawPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TargetedRawPacket msg, ByteBuf out) {
        out.writeByte(msg.id());
        out.writeInt(msg.length());
        out.writeBytes(out);
    }

}
