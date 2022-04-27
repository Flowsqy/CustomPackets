package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;

public record GlobalPacket(PacketHeader header, ByteBuf buffer) {
}
