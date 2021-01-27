package no.hvl.dat110.messaging;

import java.security.InvalidParameterException;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {

		if (payload.length <= 128) {
			this.payload = payload;
		}
		else {
			throw new InvalidParameterException("Payload cant be longer than 128 bytes.");
		}
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload;
	}

	public byte[] encapsulate() {

		byte[] encoded = new byte[128];
		int length = payload.length;

		encoded[0] = (byte) length;

		for (int i = 0; i < length; i++) {

			encoded[i + 1] = payload[i];
		}
		return encoded;
	}

	public void decapsulate(byte[] received) {

		int length = received[0];
		payload = new byte[length];

		for (int i = 0; i < length; i++) {

			payload[i] = received[i + 1];
		}
	}
}
