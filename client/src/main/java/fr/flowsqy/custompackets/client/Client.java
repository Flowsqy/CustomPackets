package fr.flowsqy.custompackets.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {

    private final static String LOCALHOST = "127.0.0.1";

    public static void main(String[] args) throws InterruptedException {
        final EventLoopGroup workers = new NioEventLoopGroup();

        try {
            final Bootstrap bootstrap = new Bootstrap();
            bootstrap
                    .group(workers)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientHandler());

            ChannelFuture f = bootstrap.connect(LOCALHOST, 8080).sync();

            for (int i = 0; i < 4; i++) {
                sendPacket(f.channel(), (byte) i);
            }

            sendPacket(f.channel(), (byte) 0).addListener((ChannelFutureListener) future -> future.channel().close());

            f.channel().closeFuture().sync();
        } finally {
            workers.shutdownGracefully();
        }
    }

    private static ChannelFuture sendPacket(Channel channel, byte target) {
        ByteBuf buf = channel.alloc().buffer(18);
        buf.writeByte(target);
        buf.writeByte(7);
        buf.writeInt(12);
        buf.writeInt(6578679);
        buf.writeInt(239023);
        buf.writeInt(185895);

        return channel.writeAndFlush(buf);
    }

}
