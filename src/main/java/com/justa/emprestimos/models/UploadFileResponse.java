package com.justa.emprestimos.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UploadFileResponse {

	private String id;
	private String fileName;
	private String fileDownloadUri;
	private String fileType;
	private long size;

	public UploadFileResponse() {

	}

	public UploadFileResponse(String uuid, String fileName, String fileDownloadUri, String fileType, long size) {
		this.id = uuid;
		this.fileName = fileName;
		this.fileDownloadUri = fileDownloadUri;
		this.fileType = fileType;
		this.size = size;
	}
}