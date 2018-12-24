package org.app.mail.common;

import java.io.File;

/**
 * Represents both:
 * - Attached File
 * - Inline Image
 * 
 * @author haglo
 *
 */
public class AIFile {

	private String fileId;
	private String fileName;
	private String filePath;
	private String fileExtension;
	private String fileFullName;
	private FILE_TYPE fileType;
	private File fileBody;

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}



	public String getFileExtension() {
		return fileExtension;
	}

	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}

	public String getFileFullName() {
		return fileFullName;
	}

	public void setFileFullName(String fileFullName) {
		this.fileFullName = fileFullName;
	}

	public FILE_TYPE getFileType() {
		return fileType;
	}

	public void setFileType(FILE_TYPE fileType) {
		this.fileType = fileType;
	}



	public File getFileBody() {
		return fileBody;
	}

	public void setFileBody(File fileBody) {
		this.fileBody = fileBody;
	}



	public enum FILE_TYPE {
		INLINE, ATTACHMENT;
	}

}
