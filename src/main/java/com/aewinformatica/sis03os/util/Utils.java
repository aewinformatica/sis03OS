package com.aewinformatica.sis03os.util;

import com.aewinformatica.sis03os.model.Cliente;
import com.aewinformatica.sis03os.model.enun.RegimeTributario;
import com.aewinformatica.sis03os.model.enun.TipoPessoa;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;



/**
 * @author Jessica
 *
 */
public class Utils {

	public static void error(String error) {
		System.err.println("| ERROR: " + error);
	}

	public static void info(String info) {
		System.out.println("| INFO: " + info);
	}

	public static String removerMascara(String str) {

		if (str.equals("") || str == null)
			return null;

		str = str.replace(" ", "");
		str = str.replace(".", "");
		str = str.replace("/", "");
		str = str.replace("-", "");
		str = str.replace("(", "");
		str = str.replace(")", "");
		return str;
	}

	public static boolean isNotCPF(Cliente cliente) {
		return "00000000000".equals(removerMascara(cliente.getDocReceitaFederal()));
	}

	public static BigDecimal dif(BigDecimal x, BigDecimal y) {
		return x.subtract(y);
	}



	public static String lpadTo(String input, int width, char ch) {
		String strPad = "";

		StringBuffer sb = new StringBuffer(input.trim());
		while (sb.length() < width)
			sb.insert(0, ch);
		strPad = sb.toString();

		if (strPad.length() > width) {
			strPad = strPad.substring(0, width);
		}
		return strPad;
	}

	public static boolean isPessoaFisica(Cliente cliente) {
		return cliente.getTipoPessoa().equals(TipoPessoa.FISICA);
	}
	
	public static boolean isSimplesNacional(RegimeTributario regimeTributario) {
		return regimeTributario.equals(RegimeTributario.SIMPLES);
	}
	
	public static boolean isRegimeNormal(RegimeTributario regimeTributario) {
		return regimeTributario.equals(RegimeTributario.NORMAL);
	}



	public static String emptyToNull(String str) {

		if (str == null)
			return str;

		if (str.equals(""))
			return null;

		return str;
	}

	public static boolean isMaiorQZero(BigDecimal valor) {
		return (valor.compareTo(BigDecimal.ZERO) > 0);
	}

	public static boolean isMenorQZero(BigDecimal valor) {
		return (valor.compareTo(BigDecimal.ZERO) < 0);
	}

	public static boolean isNotBigDecimalZeroOrNull(BigDecimal num) {
		return num != null && isMaiorQZero(num);
	}

	public static boolean isNotZero(BigDecimal valor) {
		return !isZero(valor);
	}

	public static boolean isZero(BigDecimal valor) {
		return valor.compareTo(BigDecimal.ZERO) == 0;
	}

	public static String getAnoDuasPosicoes(Date data) {
		String ret = "";
		try {
			SimpleDateFormat dt = new SimpleDateFormat("yy");
			ret = dt.format(data);
		} catch (Exception e) {
			ret = "";
			throw new RuntimeException(e);
		}

		return ret;
	}

	public static String formataXML(String xml) {

		try {
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));
			System.setProperty(DOMImplementationRegistry.PROPERTY,
					"com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();
			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);

			return writer.writeToString(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String removeCaracteresEspeciais(String text) {
		String passa = text;
		passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
		passa = passa.replaceAll("[âãàáä]", "a");
		passa = passa.replaceAll("[ÊÈÉË]", "E");
		passa = passa.replaceAll("[êèéë]", "e");
		passa = passa.replaceAll("ÎÍÌÏ", "I");
		passa = passa.replaceAll("îíìï", "i");
		passa = passa.replaceAll("í", "i");
		passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
		passa = passa.replaceAll("[ôõòóö]", "o");
		passa = passa.replaceAll("[ÛÙÚÜ]", "U");
		passa = passa.replaceAll("[ûúùü]", "u");
		passa = passa.replaceAll("Ç", "C");
		passa = passa.replaceAll("ç", "c");
		passa = passa.replaceAll("[ýÿ]", "y");
		passa = passa.replaceAll("Ý", "Y");
		passa = passa.replaceAll("ñ", "n");
		passa = passa.replaceAll("Ñ", "N");
		passa = passa.replaceAll("°", "");
		// passa = passa.replaceAll("[-+=*&amp;%$#@!_]", "");
		passa = passa.replaceAll("['\"]", "");
		passa = passa.replaceAll("[<>()\\{\\}]", "");
		passa = passa.replaceAll("['\\\\.,()|/]", "");
		passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ]{0,}[^!-ÿ]{1}|[^!-ÿ]{1}", " ");
		return passa;
	}

	public String removeCaracteresEspeciaisDadosAdicionais(String text) {
		String passa = text;
		passa = passa.replaceAll("[ÂÀÁÄÃ]", "A");
		passa = passa.replaceAll("[âãàáä]", "a");
		passa = passa.replaceAll("[ÊÈÉË]", "E");
		passa = passa.replaceAll("[êèéë]", "e");
		passa = passa.replaceAll("ÎÍÌÏ", "I");
		passa = passa.replaceAll("îíìï", "i");
		passa = passa.replaceAll("í", "i");
		passa = passa.replaceAll("[ÔÕÒÓÖ]", "O");
		passa = passa.replaceAll("[ôõòóö]", "o");
		passa = passa.replaceAll("[ÛÙÚÜ]", "U");
		passa = passa.replaceAll("[ûúùü]", "u");
		passa = passa.replaceAll("Ç", "C");
		passa = passa.replaceAll("ç", "c");
		passa = passa.replaceAll("[ýÿ]", "y");
		passa = passa.replaceAll("Ý", "Y");
		passa = passa.replaceAll("ñ", "n");
		passa = passa.replaceAll("Ñ", "N");
		passa = passa.replaceAll("°", "");
		// passa = passa.replaceAll("[-+=*&amp;%$#@!_]", "");
		passa = passa.replaceAll("['\"]", "");
		passa = passa.replaceAll("[<>()\\{\\}]", "");
		passa = passa.replaceAll("[^!-ÿ]{1}[^ -ÿ][^!-ÿ]{1}|[^!-ÿ]{1}", " ");
		return passa;
	}

	/**
	 * Remove espaços do Final
	 * 
	 * @param testeTrim
	 * @return String
	 */
	public static String removeEspacoFinal(String text) {

		text = text.replaceAll("\\s+$", "");
		return text;
	}

	/**
	 * Remove espaços do Começo
	 * 
	 * @param testeTrim
	 * @return String
	 */
	public static String ltrim(String testeTrim) {
		return testeTrim.replaceAll("^\\s+", "");
	}

	/**
	 * Remove espaços do Final
	 * 
	 * @param testeTrim
	 * @return String
	 */
	public static String rtrim(String testeTrim) {
		return testeTrim.replaceAll("\\s+$", "");
	}

	/**
	 * Remove Quebra de Linha
	 * 
	 * @param text
	 * @return String
	 */
	public static String removeQuebraLinha(String text) {
		String passa = text;

		passa = passa.replaceAll("\r", "");
		passa = passa.replaceAll("\t", "");
		passa = passa.replaceAll("\n", "");

		return passa;
	}

	/**
	 * Ajusta String para XML Retirando os quebras de linha e os espaços inicial e
	 * final e por ultimo retira os caracteres especiais
	 * 
	 * @param text
	 * @return text
	 */
	public static String ajustaString(String text) {

		text = removeQuebraLinha(text);
		text = ltrim(text);
		text = rtrim(text);
		text = removeCaracteresEspeciais(text);

		return text;
	}

	public static Boolean isVogal(String s) {

		s = s.substring(s.length() - 1, s.length());
		return s.equals("a") || s.equals("e") || s.equals("i") || s.equals("o") || s.equals("u");
	}

	public static Boolean isNotVogal(String s) {

		return !isVogal(s);
	}

	private static String ajustaXmlParaDanfe(String texto) {
		texto = texto.replaceAll("UTF-16", "UTF-8");
		texto = texto.replaceAll("enviNFe", "nfeProc");
		texto = texto.replaceAll("<idLote>1</idLote><indSinc>0</indSinc>", "");

		return texto;
	}
}
