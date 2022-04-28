package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketDecoder extends MessageToByteEncoder<TargetedRawPacket> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TargetedRawPacket msg, ByteBuf out) throws Exception {

    }
}
