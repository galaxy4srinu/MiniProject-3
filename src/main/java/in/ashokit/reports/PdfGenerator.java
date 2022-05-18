package in.ashokit.reports;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.ashokit.response.SearchResponse;

public class PdfGenerator {

	public void generatePdf(List<SearchResponse> records, HttpServletResponse response)
			throws DocumentException, IOException {
		Document document = new Document();
		PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
		document.open();

		Font font = new Font(Font.HELVETICA, 5, Font.BOLDITALIC, Color.RED);
		Paragraph para = new Paragraph("Eligibility Details",font);
		document.add(para);

		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(new float[] {3.0f,5.0f,6.0f,6.0f,6.0f,6.0f,6.0f,6.0f,6.0f});

		table.addCell("S.No");
		table.addCell("Holder Name");
		table.addCell("Holder SSN");
		table.addCell("Plan Name");
		table.addCell("Plan Status");
		table.addCell("Start Date");
		table.addCell("End Date");
		table.addCell("Benefit Amount");
		table.addCell("Denial Reason");

		int sno = 1;
		for (SearchResponse record : records) {
			table.addCell(String.valueOf(sno));
			table.addCell(record.getHolderName());
			table.addCell(String.valueOf(record.getHolderSsn()));
			table.addCell(record.getPlanName());
			table.addCell(record.getPlanStatus());
			table.addCell(String.valueOf(record.getStartDate()));
			table.addCell(String.valueOf(record.getEndDate()));
			table.addCell(String.valueOf(record.getBenefitAmt()));
			table.addCell(record.getDenialReason());
			sno++;
		}
		document.add(table);
		document.close();
		writer.close();

	}

}
