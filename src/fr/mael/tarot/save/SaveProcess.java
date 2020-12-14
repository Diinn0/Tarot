package fr.mael.tarot.save;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import fr.mael.tarot.card.CardList;
import fr.mael.tarot.utils.RenderedImageTypeAdapter;

public class SaveProcess {

	private GsonBuilder builder = new GsonBuilder();
	private Gson gson = builder.registerTypeHierarchyAdapter(BufferedImage.class, RenderedImageTypeAdapter.getRenderedImageTypeAdapter()).setPrettyPrinting().create();
	private File file;
	
	public SaveProcess(File file) {
		this.file = file;
	}
	
	public void serializeCardList(CardList list) throws JsonIOException, IOException {
		Writer writer = Files.newBufferedWriter(this.file.toPath(), StandardCharsets.UTF_8);
		gson.toJson(list, writer);
		writer.close();
	}
	
	public CardList deserializeCardList() throws JsonSyntaxException, JsonIOException, FileNotFoundException {
		return gson.fromJson(new InputStreamReader(new FileInputStream(this.file), StandardCharsets.UTF_8), CardList.class);
	}
	
}
