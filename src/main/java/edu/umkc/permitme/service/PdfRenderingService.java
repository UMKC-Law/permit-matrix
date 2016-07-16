package edu.umkc.permitme.service;

import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.sax.SAXResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.fop.apps.Fop;
import org.apache.fop.apps.FopFactory;
import org.apache.fop.apps.MimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

/**
 * Renders a PDF to the HttpServletResponse output stream.
 *
 */
@Service
public class PdfRenderingService {

	private final Logger log = LoggerFactory.getLogger(PdfRenderingService.class);
	private FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
	private TransformerFactory tFactory = TransformerFactory.newInstance();
	private String xslTemplatePath = "templates/forms/";

	public ByteArrayOutputStream renderPdf(Source src, String templatePath) {

		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, out);
			Source xslt = new StreamSource(new ClassPathResource(xslTemplatePath + templatePath).getInputStream());
			Transformer transformer = tFactory.newTransformer(xslt);
			Result res = new SAXResult(fop.getDefaultHandler());
			transformer.transform(src, res);
		} catch (Exception e) {
			log.error("Error rendering PDF: {}", e.getMessage());
			out = null;
			throw new RuntimeException("Error producing PDF.");
		} finally {
		}

		return out;
	}

}
