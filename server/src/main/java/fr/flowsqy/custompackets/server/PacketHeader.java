package fr.flowsqy.custompackets.server;

public record PacketHeader(byte targetId, byte packetId, int packetLength) {
}
