package fr.flowsqy.custompackets.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Main {

    private final static String LOCALHOST = "127.0.0.1";

    public static void main(String[] args) throws InterruptedException {
        final EventLoopGroup workers = new NioEventLoopGroup();

        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(workers)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<>() {
                        @Override
                        protected void initChannel(Channel channel) throws Exception {
                            channel.pipeline().addLast("handler", new ChannelInboundHandlerAdapter() {

                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf buffer = (ByteBuf) msg;
                                    if (buffer.capacity() < 4) {
                                        return;
                                    }
                                    try {
                                        System.out.println(buffer.readInt());
                                    } finally {
                                        buffer.release();
                                    }
                                }
                            });
                        }
                    });

            ChannelFuture f = bootstrap.connect(LOCALHOST, 8080).sync();

            f.channel().closeFuture().sync();
        } finally {
            workers.shutdownGracefully();
        }
    }

}
