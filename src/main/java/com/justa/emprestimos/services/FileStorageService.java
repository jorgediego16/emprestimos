package com.justa.emprestimos.services;

import com.justa.emprestimos.models.Arquivo;
import com.justa.emprestimos.models.UploadFileResponse;
import com.justa.emprestimos.properties.FileStorageProperties;
import com.justa.emprestimos.repositories.ArquivoRepository;
import com.justa.emprestimos.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	private ArquivoRepository arquivoRepository;

	@Autowired
	public FileStorageService(FileStorageProperties fileStorageProperties) throws Exception {
		this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir()).toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new Exception("Could not create the directory where the uploaded files will be stored.");
		}
	}

	public UploadFileResponse criaArquivo(MultipartFile file, HttpServletRequest request) throws Exception {

		// Generate UUID for file
		String uuid = UUID.randomUUID().toString();

		// Get file name extension
		String extensao = FileUtil.obterExtensaoNomeArquivo(file.getOriginalFilename())
				.orElseThrow(() -> new Exception("Invalid extension"));

		// Generate the new file name
		String novoNomeArquivo = uuid + "." + extensao;

		// Creates the file entity
		Arquivo arquivo = new Arquivo();
		arquivo.setId(uuid);
		arquivo.setIpLocal(request.getLocalAddr());
		arquivo.setIpRemoto(request.getRemoteAddr());
		arquivo.setNomeArquivo(novoNomeArquivo);
		arquivo.setExtensao(extensao);
		arquivo.setNomeOriginalArquivo(file.getOriginalFilename());
		arquivo.setTamanho(file.getSize());
		arquivo.setContentType(file.getContentType());

		// Save file to disk
		storeFile(file, arquivo.getNomeArquivo());

		// File persists in database
		arquivoRepository.save(arquivo);

		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/downloadFile/token/")
				.path(novoNomeArquivo).toUriString();

		return new UploadFileResponse(arquivo.getId(), novoNomeArquivo, fileDownloadUri, file.getContentType(),
				file.getSize());
	}

	public String storeFile(MultipartFile file, String nomeArquivo) throws Exception {

		// Normaliza nome do arquivo
		String fileName = StringUtils.cleanPath(nomeArquivo);

		try {

			// valida caracteres inválidos no nome do arquivo
			if (fileName.contains("..")) {
				throw new Exception("Excuse me! invalid filename " + fileName);
			}

			// copia o arquivo em disco local sobrescrevendo quando existente
			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

		} catch (IOException ex) {
			throw new Exception("File could not be stored " + fileName + ". Try again!", ex);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public Resource loadFileAsResource(String uuid) throws Exception {
		try {
			Path filePath = this.fileStorageLocation.resolve(uuid).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new Exception("File not found " + uuid);
			}
		} catch (MalformedURLException ex) {
			throw new Exception("File not found " + uuid, ex);
		}
	}

	public void deletar(String nomeArquivo) throws IOException {
		Files.delete(this.fileStorageLocation.resolve(nomeArquivo).normalize());
	}

	public Arquivo obterArquivo(String fileName) throws Exception {
		return arquivoRepository.findById(FileUtil.getFileNameWithoutExtension(fileName))
				.orElseThrow(() -> new Exception("File not found " + fileName));
	}

	public void excluirArquivo(String fileName) throws Exception {
		arquivoRepository.delete(obterArquivo(fileName));
	}

	public boolean updateFile(MultipartFile file, String uuid, HttpServletRequest request) throws Exception {

		// obtem entidade arquivo
		Optional<Arquivo> arquivoOptional = arquivoRepository.findById(uuid);

		if (!arquivoOptional.isPresent()) {
			throw new Exception(uuid);
		}

		// obtem arquivo
		Arquivo arquivo = arquivoOptional.get();
		arquivo.setIpLocal(request.getLocalAddr());
		arquivo.setIpRemoto(request.getRemoteAddr());
		arquivo.setTamanho(file.getSize());
		arquivo.setContentType(file.getContentType());
		arquivo.setNomeOriginalArquivo(file.getOriginalFilename());

		// exclui arquivo antigo
		deletar(arquivo.getNomeArquivo());

		// salva arquivo em disco
		storeFile(file, arquivo.getNomeArquivo());

		// atualiza arquivo banco de dados
		arquivoRepository.save(arquivo);

		return true;
	}
}