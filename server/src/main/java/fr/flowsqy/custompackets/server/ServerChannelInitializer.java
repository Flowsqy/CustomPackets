package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.*;

public class ServerChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) {
        final ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new GlobalPacketDecoder())
                .addLast("handler", new GlobalPacketHandler())
                .addLast("innerHandler", new ChannelInboundHandlerAdapter() {
                    @Override
                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                        ByteBuf buf = (ByteBuf) msg;
                        System.out.println(buf.readInt());
                        System.out.println(buf.readInt());
                        System.out.println(buf.readInt());
                    }
                });
    }
}
