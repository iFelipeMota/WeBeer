package ubhiya.Controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Blob;

import ubhiya.Model.FileSaver;

/**
 * Servlet implementation class ImageSaver
 */
@MultipartConfig
public class ImageSaver extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageSaver() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Part filePart = request.getPart("file");
		final String fileName = request.getParameter("file");
		ServletContext imageUrl = request.getServletContext();
		
		if (fileName != null && !fileName.isEmpty() && fileName.contains(".")) {
			final String extension = fileName.substring(fileName.lastIndexOf('.') + 1);
			String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
			for (String s : allowedExt) {
				if (extension.equals(s)) {
					FileSaver fs = new FileSaver();
					fs.salvarImagem(filePart);
					System.out.println("Salvo com sucesso.");
				}
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    Part filePart = request.getPart("upload");
		String nameFile = filePart.getSubmittedFileName();
	   InputStream filecontent = filePart.getInputStream();
	   
	    String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
	    InputStream fileContent = filePart.getInputStream();
	    
	    
	    FileSaver fs = new FileSaver();
	    fs.salvarImagem(filePart);
	    //fs.teste();
	    
		System.out.println("Salvo com sucesso.");
	    
	}

}
