package ubhiya.Model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.api.services.storage.model.ObjectAccessControl;
import com.google.api.services.storage.model.StorageObject;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Acl.Role;
import com.google.cloud.storage.Acl.User;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Bucket.BlobTargetOption;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.Storage.PredefinedAcl;
import com.google.cloud.storage.StorageOptions;

public class FileSaver {
	
	Storage storage = StorageOptions.getDefaultInstance().getService();
	
	public void teste() throws FileNotFoundException, IOException {
		
		Credentials credentials = GoogleCredentials
				  .fromStream(new FileInputStream("/ubhiya/src/main/webapp/WEB-INF/lib/WeBeer-b5bae8591bbc.json"));
				Storage storage = StorageOptions.newBuilder().setCredentials(credentials)
				  .setProjectId("helical-rhythm-215921").build().getService();
				
		Bucket bucket = storage.create(BucketInfo.of("webeer-cervejas"));
		
		String value = "Hello, World!";
		byte[] bytes = value.getBytes();
	}
	
	@SuppressWarnings("deprecation")
	public String salvarImagem(Part file) {
		
		try {
			DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
			DateTime dt = DateTime.now(DateTimeZone.UTC);
			String dtString = dt.toString(dtf);
			final String fileName = file.getSubmittedFileName() + dtString;

			//List<Acl> acls = new ArrayList<>();
			//Acl acl = storage.getAcl("webeer-cervejas", User.ofAllAuthenticatedUsers());
		    //acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.OWNER));

			
			BlobInfo blobInfo = storage.create(
					BlobInfo.newBuilder("webeer-cervejas", fileName)
					//.setAcl(acls)
					.setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
					.build(),
					file.getInputStream());
			
			return blobInfo.getMediaLink();

		} catch (IllegalStateException | IOException e) {
			throw new RuntimeException(e);
		}
	} 

	@SuppressWarnings("deprecation")
	public String uploadFile(Part filePart, final String bucketName) throws IOException{
		
		DateTimeFormatter dtf = DateTimeFormat.forPattern("-YYYY-MM-dd-HHmmssSSS");
		DateTime dt = DateTime.now(DateTimeZone.UTC);
		String dtString = dt.toString(dtf);
		final String fileName = filePart.getSubmittedFileName() + dtString;

		BlobInfo blobInfo =
				storage.create(
						BlobInfo
						.newBuilder(bucketName, fileName)
						// Modify access list to allow all users with link to read file
						.setAcl(new ArrayList<>(Arrays.asList(Acl.of(User.ofAllUsers(), Role.READER))))
						.build(),
						filePart.getInputStream());
		return blobInfo.getMediaLink();
	}

	public String getImageUrl(HttpServletRequest req, HttpServletResponse resp,
			final String bucket) throws IOException, ServletException {
		Part filePart = req.getPart("file");
		final String fileName = filePart.getSubmittedFileName();
		String imageUrl = req.getParameter("imageUrl");
		if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
			final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
			String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
			for (String s : allowedExt) {
				if (extension.equals(s)) {
					return this.uploadFile(filePart, bucket);
				}
			}
			throw new ServletException("file must be an image");
		}
		return imageUrl;
	}
}
