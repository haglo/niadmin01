package org.app.mail.smtp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.apache.commons.io.IOUtils;
import org.app.common.Const;
import org.app.mail.common.AIFile;
import org.app.mail.common.AIFile.FILE_TYPE;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;

/**
 * UploadDailog
 * important are:
 * - getUpload()
 * - get AiFiles()
 * This two methods offer the API to be called from outside
 * The Constuctor starts the upload until the upload is done/failed
 * @author haglo
 *
 */
@Tag("upload-button")
public class ExtendedUpload extends Component implements Const {

	private static final long serialVersionUID = 1L;

	private Upload upload;
	private String uploadPath;
	private Set<AIFile> aiFiles;
	private UUID uuid;

	public ExtendedUpload() {
		uuid = UUID.randomUUID();
		aiFiles = new HashSet<AIFile>();
		uploadPath = MAIL_UPLOAD_PATH_ABSOLUT + uuid.toString() + "/";
		createComposite();
	}

	private void createComposite() {
		final long UPLOAD_LIMIT = 1000000l;
		MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
		upload = new Upload(buffer);

		upload.addSucceededListener(event -> {
			InputStream stream = buffer.getInputStream(event.getFileName());
			AIFile aiFile = new AIFile();
			aiFile.setFileExtension(event.getMIMEType());
			aiFile.setFileName(event.getFileName());
			aiFile.setFilePath(uploadPath);
			aiFile.setFileFullName(uploadPath + event.getFileName());
			aiFile.setFileType(FILE_TYPE.ATTACHMENT);
			if (uploadFile(stream, aiFile)) {
				aiFiles.add(aiFile);
			}
		});

		upload.addStartedListener(event -> {
			if (event.getContentLength() > UPLOAD_LIMIT) {
				Notification.show("Too big file to upload");
				upload.interruptUpload();
			}
			if (!createUploadDir()) {
				Notification.show("ERROR: Could not create upload dir " + uploadPath);
			}

		});

		upload.addProgressListener(event -> {
			if (event.getReadBytes() > UPLOAD_LIMIT) {
				Notification.show("Too big file");
				upload.interruptUpload();
			}
		});

		upload.setMaxFileSize(200 * 1024);
	}

	private boolean createUploadDir() {
		File uploads = new File(uploadPath);

		if (!uploads.exists() && !uploads.mkdirs())
			return false;
		else
			return true;
	}

	private boolean uploadFile(InputStream stream, AIFile aiFile) {
		File file;
		FileOutputStream fos = null; // Stream to write to
		try {
			file = new File(aiFile.getFileFullName());
			fos = new FileOutputStream(file);
			byte[] bytes = IOUtils.toByteArray(stream);
			fos.write(bytes);
			fos.close();
			return true;
		} catch (final IOException e) {
			return false;
		} 
	}


	public Upload getUpload() {
		return upload;
	}

	public Set<AIFile> getAiFiles() {
		return aiFiles;
	}

}
