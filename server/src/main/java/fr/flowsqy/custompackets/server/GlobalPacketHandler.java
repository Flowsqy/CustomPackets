package fr.flowsqy.custompackets.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GlobalPacketHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final GlobalPacket globalPacket = (GlobalPacket) msg;
        System.out.println(globalPacket);
        ctx.fireChannelRead(globalPacket.buffer());
    }
}
