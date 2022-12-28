package com.deewana.filecompression.servlet;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.deewana.filecompression.util.FileInfo;

/**
 * Servlet implementation class UploadFileServlet
 */
@WebServlet("/UploadFile")
@MultipartConfig
public class UploadFileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");  
		  
        // creating path components for saving the file  
        final String path = FileInfo.filePath; 
        final Part filePart = request.getPart("file");  
        final String fileName = "temp.txt";  
          
        // declare instances of OutputStream, InputStream, and PrintWriter classes  
        OutputStream otpStream = null;  
        InputStream iptStream = null;  
        final PrintWriter writer = response.getWriter();  
          
        // try section handles the code for storing file into the specified location  
        try {  
            // initialize instances of OutputStream and InputStream classes  
            otpStream = new FileOutputStream(new File(path + File.separator + fileName));  
            iptStream = filePart.getInputStream();  
  
            int read = 0;  
              
            // initialize bytes array for storing file data  
            final byte[] bytes = new byte[1024];  
              
            // use while loop to read data from the file using iptStream and write into  the desination folder using writer and otpStream  
            while ((read = iptStream.read(bytes)) != -1) {  
                otpStream.write(bytes, 0, read);  
            }  
            RequestDispatcher rd=request.getRequestDispatcher("HomePage.html");
			writer.println("<html><h2>File Uploaded Successfully !!!</h2></html>");  
			rd.include(request, response);
			
        }  
        catch (FileNotFoundException fne){  
            RequestDispatcher rd=request.getRequestDispatcher("HomePage.html");
            writer.println("You either did not specify a file to upload or are trying to upload a file to a protected or nonexistent location.");  
            writer.println("<br/> ERROR: " + fne.getMessage());
            rd.include(request, response);
            
        }  
        finally {  
            if (otpStream != null) {  
                otpStream.close();  
            }  
            if (iptStream != null) {  
                iptStream.close();  
            }  
            if (writer != null) {  
                writer.close();  
            }  
        }  
    }  
    
}
