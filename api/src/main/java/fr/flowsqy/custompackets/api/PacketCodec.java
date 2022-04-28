package fr.flowsqy.custompackets.api;

public interface PacketCodec<T extends Packet> extends PacketEncoder<T>, PacketDecoder<T> {
}
