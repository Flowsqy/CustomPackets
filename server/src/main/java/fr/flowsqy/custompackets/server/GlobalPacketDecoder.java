package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class GlobalPacketDecoder extends ByteToMessageDecoder {

    // Target ID (byte) + Packet ID (byte) + Packet Length (int) + Packet Content (Object) [Skipped]

    private static final byte HEADER_LENGTH = 6; // 1 + 1 + 4

    private PacketHeader packetHeader;

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf globalPacketBuffer, List<Object> out) {
        // Get the packet header
        if (packetHeader == null) {
            if (globalPacketBuffer.readableBytes() < HEADER_LENGTH) {
                return;
            }

            this.packetHeader = new PacketHeader(globalPacketBuffer.readByte(), globalPacketBuffer.readByte(), globalPacketBuffer.readInt());
        }

        // Wait until the global packet is fully received
        if (globalPacketBuffer.readableBytes() < packetHeader.packetLength()) {
            return;
        }

        // Send the GlobalPacket through the pipeline
        final ByteBuf contentBuffer = globalPacketBuffer.readBytes(packetHeader.packetLength());
        out.add(new GlobalPacket(packetHeader, contentBuffer));

        // Reset the header. Ready to decode another packet
        packetHeader = null;
    }
}
