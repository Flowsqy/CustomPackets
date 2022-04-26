package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

@ChannelHandler.Sharable
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private int count;

    public ServerHandler() {
        count = 0;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        final ByteBuf buf = ctx.alloc().buffer(1);
        buf.writeByte(0xFF);
        final ChannelFuture writeFuture = ctx.writeAndFlush(buf);

        final ChannelFuture closeFuture = ctx.channel().closeFuture();

        writeFuture.addListener((ChannelFutureListener) future -> future.channel().close());

        count ++;

        if(count >= 5) {
            closeFuture.addListener((ChannelFutureListener) future -> future.channel().parent().close());
        }
    }
}
