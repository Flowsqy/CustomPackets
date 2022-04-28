package fr.flowsqy.custompackets.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;

public class ServerChannelInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) {
        final ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("global_decoder", new GlobalPacketDecoder())
                .addLast("packet_encoder", new PacketEncoder())
                .addLast("encoder", new TargetedPacketEncoder())
                .addLast("router", new GlobalPacketHandler())
                .addLast("packet_decoder", new PacketDecoder())
                .addLast("packet_handler", new ServerPacketHandler());

        // Register the channel somewhere to track it
    }
}
