package fr.flowsqy.custompackets.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class PacketEncoder extends MessageToMessageEncoder<TargetedPacket> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TargetedPacket msg, List<Object> out) throws Exception {

    }
}
