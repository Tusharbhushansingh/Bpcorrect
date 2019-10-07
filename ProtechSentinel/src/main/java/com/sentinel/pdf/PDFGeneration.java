package com.sentinel.pdf;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

public class PDFGeneration {

	public static boolean createPDF(String path, String htmlPath) {
		try {
			// step 1
			Document document = new Document();
			// step 2
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(path));
			// step 3
			document.open();
			// step 4
			XMLWorkerHelper.getInstance().parseXHtml(writer, document,
					new FileInputStream(htmlPath));
			// step 5
			document.close();

			System.out.println("PDF Created!");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
