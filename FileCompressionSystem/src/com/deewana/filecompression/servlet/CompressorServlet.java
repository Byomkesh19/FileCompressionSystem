package com.deewana.filecompression.servlet;

import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.deewana.filecompression.BO.CompressorBO;
import com.deewana.filecompression.util.FileUtilities;

/**
 * Servlet implementation class CompressorServlet
 */
@WebServlet("/Compressor")
public class CompressorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Fethcing the File to be operated
		File file = FileUtilities.getFile();

		// Sending it for compression
		CompressorBO compressorBO = new CompressorBO();
		compressorBO.compress(file);

		// Moving to download page for downloading the compressed file.
		RequestDispatcher rd = request.getRequestDispatcher("DownloadPage.html");
		rd.forward(request, response);
	}

}
