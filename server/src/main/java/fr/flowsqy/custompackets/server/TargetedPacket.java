package fr.flowsqy.custompackets.server;

import io.netty.buffer.ByteBuf;

public record TargetedPacket(byte id, int length, ByteBuf content) {
}
