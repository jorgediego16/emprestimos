package com.justa.emprestimos.controllers;

import com.justa.emprestimos.models.UploadFileResponse;
import com.justa.emprestimos.services.FileStorageService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/fileserver")
@RestController
public class FileServerController {

	private static final Logger logger = LoggerFactory.getLogger(FileServerController.class);

	@Autowired
	private FileStorageService fileStorageService;

	/**
	 * The purpose of this method is to upload a file
	 * @param file
	 * @param request
	 * @param uuid
	 * @return boolean
	 * @throws Exception
	 */
	@ApiOperation(value = "File Update Method", response = UploadFileResponse.class)
	@PostMapping("/updateFile")
	public boolean updateFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, String uuid) throws Exception {
		return fileStorageService.updateFile(file, uuid, request);
	}

	/**
	 * The purpose of this method is to update a file
	 * @param file
	 * @param request
	 * @return UploadFileResponse
	 * @throws Exception
	 */
	@ApiOperation(value = "File Upload Method", response = UploadFileResponse.class)
	@PostMapping("/uploadFile")
	public UploadFileResponse uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
		return fileStorageService.criaArquivo(file, request);
	}

	/**
	 * The purpose of this method is to upload multiple files
	 * @param files
	 * @param request
	 * @return List<UploadFileResponse>
	 */
	@ApiOperation(value = "Multiple File Upload Method", response = UploadFileResponse.class)
	@PostMapping("/uploadMultipleFiles")
	public List<UploadFileResponse> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files, HttpServletRequest request) {

		List<Exception> exceptions = new ArrayList<>();

		return Arrays.asList(files).stream().map(file -> {
			try {
				return uploadFile(file, request);

			} catch (Exception e) {
				exceptions.add(e);
				e.printStackTrace();
			}
			return null;

		}).collect(Collectors.toList());
	}

	/**
	 * O objetivo deste método é realizar o download de um arquivo
	 * @param fileName
	 * @param request
	 * @return ResponseEntity<Resource>
	 * @throws Exception
	 */
	@ApiOperation(value = "File download method", response = UploadFileResponse.class)
	@GetMapping("/downloadFile/{fileName:.+}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws Exception {

		// Load file as Resource
		Resource resource = fileStorageService.loadFileAsResource(fileName);

		// Try to determine file's content type
		String contentType = null;
		try {
			contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException ex) {
			logger.info("Could not determine file type.");
		}

		// seta default content type se não puder ser determinado
		if (contentType == null) {
			contentType = "application/octet-stream";
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	/**
	 * The purpose of this method is to delete a file
	 * @param fileName
	 * @return boolean
	 * @throws Exception
	 */
	@ApiOperation(value = "File delete method", response = UploadFileResponse.class)
	@DeleteMapping("/delete/{fileName:.+}")
	public boolean deleteFile(@PathVariable String fileName) throws Exception {

		try {
			fileStorageService.loadFileAsResource(fileName).getFile().delete();
			fileStorageService.excluirArquivo(fileName);
		} catch (Exception e) {
			throw e;
		}
		return true;
	}

}