package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;

public record TargetedRawPacket(byte id, int length, ByteBuf content) {
}
