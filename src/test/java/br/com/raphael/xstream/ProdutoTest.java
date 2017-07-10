package br.com.raphael.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;


public class ProdutoTest {

	
	@Test
	public void verificaXmlProdutoTest(){
		String resultadoEsperado = "<produto codigo=\"1587\">\n" +
		        "  <nome>geladeira</nome>\n" +
		        "  <preco>1000.0</preco>\n" +
		        "  <descrição>geladeira duas portas</descrição>\n" +
		        "</produto>";		
		Produto geladeira = new Produto("geladeira", 1000, "geladeira duas portas", 1587);
		
		XStream xStream = new XStream();
		xStream.alias("produto", Produto.class);
		xStream.aliasField("descrição", Produto.class, "descricao");
		xStream.useAttributeFor(Produto.class, "codigo");
		String xmlGerado = xStream.toXML(geladeira);
		
		
		assertEquals(resultadoEsperado, xmlGerado);
		
	}
	
}
