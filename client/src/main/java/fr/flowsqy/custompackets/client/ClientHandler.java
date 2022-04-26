package fr.flowsqy.custompackets.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        final ByteBuf buf = (ByteBuf) msg;
        try {
            System.out.println(buf.readByte());
        } finally {
            buf.release();
        }
    }
}
