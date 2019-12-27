package br.org.graaccto.util.http;

public class GraaccApiResponse <T> {

	private T payload;

	private GraaccApiResponse(T payload) {
		this.payload = payload;
	}

	public static <T> GraaccApiResponse<T> withBody(T t) {
		return new GraaccApiResponse<T>(t);
	}

	public T getPayload() {
		return payload;
	}
}
