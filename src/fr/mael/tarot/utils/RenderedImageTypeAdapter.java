package fr.mael.tarot.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * This class give informations to Gson to serialize and deserialize BufferedImage
 *
 */
public class RenderedImageTypeAdapter extends TypeAdapter<BufferedImage> {

	private static final TypeAdapter<BufferedImage> renderedImageTypeAdapter = new RenderedImageTypeAdapter()
			.nullSafe();

	private RenderedImageTypeAdapter() {
	}

	public static TypeAdapter<BufferedImage> getRenderedImageTypeAdapter() {
		return renderedImageTypeAdapter;
	}

	@Override
	@SuppressWarnings("resource")
	public void write(final JsonWriter out, final BufferedImage image) throws IOException {
		// Intermediate buffer
		final ByteArrayOutputStream output = new ByteArrayOutputStream();
		// By the way, how to pick up the target image format? BMP takes more space, PNG
		// takes more time, JPEG is lossy...
		ImageIO.write(image, "PNG", output);
		// Not sure about this, but converting to base64 is more JSON-friendly
		final Base64.Encoder encoder = Base64.getEncoder();
		// toByteArray() returns a copy, not the original array (x2 more memory)
		// + creating a string requires more memory to create the String internal buffer
		// (x3 more memory)
		final String imageBase64 = encoder.encodeToString(output.toByteArray());
		out.value(imageBase64);
	}

	@Override
	public BufferedImage read(final JsonReader in) throws IOException {
		// The same in reverse order
		final String imageBase64 = in.nextString();
		final Base64.Decoder decoder = Base64.getDecoder();
		final byte[] input = decoder.decode(imageBase64);
		return ImageIO.read(new ByteArrayInputStream(input));
	}

}