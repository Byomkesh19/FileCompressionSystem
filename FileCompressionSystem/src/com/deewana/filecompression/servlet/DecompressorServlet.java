package com.deewana.filecompression.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deewana.filecompression.BO.DecompressorBO;
import com.deewana.filecompression.util.FileUtilities;

/**
 * Servlet implementation class DecompressorServlet
 */
@WebServlet("/Decompressor")
public class DecompressorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Fetching the file for operation
		File file = FileUtilities.getFile();

		// Sending it for decompression
		DecompressorBO decompressorBO = new DecompressorBO();
		decompressorBO.decompress(file);

		// After the decompression is done, moving to the download page
		RequestDispatcher rd = request.getRequestDispatcher("DownloadPage.html");
		rd.forward(request, response);

	}

}
