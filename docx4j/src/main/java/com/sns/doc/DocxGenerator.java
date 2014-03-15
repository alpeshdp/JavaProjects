package com.sns.doc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang.StringUtils;
import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.P;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

/**
 * Reference : http://java.dzone.com/articles/create-complex-word-docx
 * @author aparekh
 *
 */
public class DocxGenerator {

	public static void main(String[] args) throws Exception {
		URL resource = Thread.currentThread().getContextClassLoader().getResource("template2.docx");
		File templateFile = new File(resource.toURI());
		System.out.println("Using Template:" + templateFile.getAbsolutePath());
		
		DocxGenerator builder = new DocxGenerator();
		WordprocessingMLPackage mlPackage = builder.getTemplate(templateFile);
		builder.replacePlaceholder(mlPackage, "Alpesh", "SJ_EX1");
		builder.replaceParagraph("SJ_PARA1", paraText, mlPackage, mlPackage.getMainDocumentPart());
		builder.replaceTable(new String[]{"SJ_FUNCTION","SJ_DESC","SJ_PERIOD"}, tableData(), mlPackage);
		
		File target = new File("T1_" + new Date().getTime() + ".docx");
		builder.writeDocxToStream(mlPackage, target);
		System.out.println("Done @ :" + target.getAbsolutePath());
	}
	
	private WordprocessingMLPackage getTemplate(File templateFile) throws Docx4JException, FileNotFoundException {
		WordprocessingMLPackage template = WordprocessingMLPackage.load(new FileInputStream(templateFile));
		return template;
	}
	
	private void writeDocxToStream(WordprocessingMLPackage template, File target) throws Exception {
		template.save(target);
	}	
	
	
	/**
	 * Add a table:
	 */
	
	@SuppressWarnings("serial")
	public static List<Map<String,String>> tableData() {
		
		final Map<String,String> repl1 = new HashMap<String, String>();
		repl1.put("SJ_FUNCTION", "function1");
		repl1.put("SJ_DESC", "desc1");
		repl1.put("SJ_PERIOD", "period1");
	
		final Map<String,String> repl2 = new HashMap<String, String>();
		repl2.put("SJ_FUNCTION", "function2");
		repl2.put("SJ_DESC", "desc2");
		repl2.put("SJ_PERIOD", "period2");
	
		final Map<String,String> repl3 = new HashMap<String, String>();
		repl3.put("SJ_FUNCTION", "function3");
		repl3.put("SJ_DESC", "desc3");
		repl3.put("SJ_PERIOD", "period3");
		
		return new ArrayList<Map<String, String>>() {
			{
				add(repl1);
				add(repl2);
				add(repl3);
			}
		};
	}

	private void replaceTable(String[] placeholders, List<Map<String, String>> textToAdd, WordprocessingMLPackage template) throws Exception {
		List<Object> tables = getAllElementFromObject(template.getMainDocumentPart(), Tbl.class);

		// 1. find the table
		Tbl tempTable = getTemplateTable(tables, placeholders[0]);
		List<Object> rows = getAllElementFromObject(tempTable, Tr.class);

		// first row is header, second row is content
		if (rows.size() == 2) {
			// this is our template row
			Tr templateRow = (Tr) rows.get(1);

			for (Map<String, String> replacements : textToAdd) {
				// 2 and 3 are done in this method
				addRowToTable(tempTable, templateRow, replacements);
			}

			// 4. remove the template row
			tempTable.getContent().remove(templateRow);
		}
	}
	
	
	private Tbl getTemplateTable(List<Object> tables, String templateKey) throws Docx4JException, JAXBException {
		for (Iterator<Object> iterator = tables.iterator(); iterator.hasNext();) {
			Object tbl = iterator.next();
			List<?> textElements = getAllElementFromObject(tbl, Text.class);
			for (Object text : textElements) {
				Text textElement = (Text) text;
				if (textElement.getValue() != null && textElement.getValue().equals(templateKey))
					return (Tbl) tbl;
			}
		}
		return null;
	}	
	
	private static void addRowToTable(Tbl reviewtable, Tr templateRow, Map<String, String> replacements) {
		Tr workingRow = (Tr) XmlUtils.deepCopy(templateRow);
		List<?> textElements = getAllElementFromObject(workingRow, Text.class);
		for (Object object : textElements) {
			Text text = (Text) object;
			String replacementValue = (String) replacements.get(text.getValue());
			if (replacementValue != null)
				text.setValue(replacementValue);
		}

		reviewtable.getContent().add(workingRow);
	}	
	
	
	/**
	 *  To add paragraph:
	 *      1. Find the paragraph to replace from the template
	 *      2. Split the input text into seperate lines
	 *      3. For each line create a new paragraph based on the paragraph from the template
	 *      4. Remove the original paragraph
	 *      
	 *      SJ_PARA1
	 */
	private void replaceParagraph(String placeholder, String textToAdd, WordprocessingMLPackage template, ContentAccessor addTo) {
		
		// 1. get the paragraph
		List<Object> paragraphs = getAllElementFromObject(template.getMainDocumentPart(), P.class);
 
		P toReplace = null;
		for (Object p : paragraphs) {
			List<Object> texts = getAllElementFromObject(p, Text.class);
			for (Object t : texts) {
				Text content = (Text) t;
				if (content.getValue().equals(placeholder)) {
					toReplace = (P) p;
					break;
				}
			}
		}
		
		// 1.1 Empty the paragraph container. So that nput text can be appended to it
		if(toReplace != null) {
			List<?> texts = getAllElementFromObject(toReplace, Text.class);
			if (texts.size() > 0) {
				Text textToReplace = (Text) texts.get(0);
				textToReplace.setValue("");
			}
		}
 
		// we now have the paragraph that contains our placeholder: toReplace
		// 2. split into seperate lines
		String as[] = StringUtils.splitPreserveAllTokens(textToAdd, '\n');
 
		for (int i = 0; i < as.length; i++) {
			String ptext = as[i];
 
			// 3. copy the found paragraph to keep styling correct
			P copy = (P) XmlUtils.deepCopy(toReplace);
 
			// replace the text elements from the copy
			List<?> texts = getAllElementFromObject(copy, Text.class);
			if (texts.size() > 0) {
				Text textToReplace = (Text) texts.get(0);
				textToReplace.setValue(ptext);
			}
 
			// add the paragraph to the document
			((ContentAccessor)toReplace).getContent().add(copy);
		}
 
		
		// Due to 1.1, we dont have to do step 4
		// 4. remove the original one 
		// ((ContentAccessor)toReplace.getParent()).getContent().remove(toReplace);
 
	}
	
	
	/**
	 *  Dynamically set the title of a document. 
	 *  First add a custom placeholder in the word template you created. I'll use SJ_EX1 for this. 
	 *  We'll replace this value with our name. 
	 *  
	 *  The basic text elements in a docx4j are represented by the org.docx4j.wml.Text class
	 */
	private void replacePlaceholder(WordprocessingMLPackage template, String name, String placeholder ) {
		List<Object> texts = getAllElementFromObject(template.getMainDocumentPart(), Text.class);
 
		for (Object text : texts) {
			Text textElement = (Text) text;
			if (textElement.getValue().equals(placeholder)) {
				textElement.setValue(name);
			}
		}
	}	
	
	private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement) obj = ((JAXBElement) obj).getValue();
 
		if (obj.getClass().equals(toSearch))
			result.add(obj);
		else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}
 
		}
		return result;
	}
	
	static String paraText = "You might wonder why we need to be able to add paragraphs? We can already add text, and isn't a paragraph just a large piece of text?" +
			"\nWell, yes and no. A paragraph indeed looks like a big piece of text, but what you need to take into account are the linebreaks. If you add a Text element, like we did earlier, and add linebreaks to the text, they won't show up." +
			"\nWhen you want linebreaks, you'll need to create a new paragraph. Luckily, though, this is also very easy to do with Docx4j." +
			"\nWe'll do this by taking the following steps:";
	
	
}
