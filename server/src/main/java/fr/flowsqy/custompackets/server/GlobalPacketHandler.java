package fr.flowsqy.custompackets.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class GlobalPacketHandler extends ChannelInboundHandlerAdapter {

    private final static byte SERVER_ID = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        final GlobalPacket globalPacket = (GlobalPacket) msg;

        final TargetedRawPacket targetedPacket = new TargetedRawPacket(
                globalPacket.header().packetId(),
                globalPacket.header().packetLength(),
                globalPacket.buffer()
        );

        // Send the global packet through the pipeline if it's for the server
        if (globalPacket.header().targetId() == SERVER_ID) {
            ctx.fireChannelRead(targetedPacket);
            return;
        }

        // Forward the packet to the target
        // TODO sendPacket(globalPacket.header().targetId(), targetedPacket);

        System.out.println("Forward to " + globalPacket.header().targetId() + ": " + targetedPacket);
    }
}
