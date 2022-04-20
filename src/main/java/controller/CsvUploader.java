package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class CsvUploader {
//	private final static String RESOURCES_PATH = "src/main/resources/";
//	private final static char SEPARATOR = ';';

	public static List<String> readFile(String fileName) throws IOException {
		List<String> liste = new ArrayList<>();

		InputStream fileInputStream = CsvUploader.class.getResourceAsStream("/" + fileName);

		boolean eof = false;
		System.out.println("FileName : " + fileName);
		System.out.println("Stream : " + fileInputStream);

		InputStreamReader streamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
		for (String line; (line = reader.readLine()) != null;) {
			// System.out.println(line);
			liste.add(line);
		}

		fileInputStream.close();

		return liste;
	}
}
