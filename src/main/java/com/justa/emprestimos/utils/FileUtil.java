package com.justa.emprestimos.utils;

import java.util.Optional;

public class FileUtil {

	public static Optional<String> obterExtensaoNomeArquivo(String filename) {
		return Optional.ofNullable(filename).filter(f -> f.contains("."))
				.map(f -> f.substring(filename.lastIndexOf(".") + 1));
	}

	public static String getFileNameWithoutExtension(String fileName) {

		try {
			return fileName.replaceFirst("[.][^.]+$", "");
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
