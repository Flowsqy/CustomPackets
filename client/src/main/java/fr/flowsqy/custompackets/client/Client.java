package fr.flowsqy.custompackets.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
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

            f.channel().closeFuture().sync();
        } finally {
            workers.shutdownGracefully();
        }
    }

}
