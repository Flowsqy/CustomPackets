package fr.flowsqy.custompackets.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerPacketHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        final TargetedRawPacket packet = (TargetedRawPacket) msg;
        System.out.println("Server packet " + packet);
    }
}
