package fr.flowsqy.custompackets.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) {
        final ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("decoder", new GlobalPacketDecoder())
                .addLast("router", new GlobalPacketHandler())
                .addLast("handler", new ServerPacketHandler());

        // Register the channel somewhere to track it
    }
}
