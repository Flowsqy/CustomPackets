package fr.flowsqy.custompackets.server;

import fr.flowsqy.custompackets.api.Packet;

public record TargetedPacket(byte id, int length, Packet packet) {
}
