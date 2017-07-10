package br.com.raphael.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.thoughtworks.xstream.XStream;


public class CompraTest {
	
	@Test
	public void verificarXmGeradoCompraProduto(){

        String resultadoEsperado = "<compra>\n"+
            "  <codigo>15</codigo>\n"+
            "  <produtos>\n"+
            "    <produto codigo=\"1587\">\n"+
            "      <nome>geladeira</nome>\n"+
            "      <preco>1000.0</preco>\n"+
            "      <descrição>geladeira duas portas</descrição>\n"+
            "    </produto>\n"+
            "    <produto codigo=\"1588\">\n"+
            "      <nome>ferro de passar</nome>\n"+
            "      <preco>100.0</preco>\n"+
            "      <descrição>ferro de passar com vaporizador</descrição>\n"+
            "    </produto>\n"+
            "  </produtos>\n"+
            "</compra>";
        
		
		List<Produto> produtos = compraProdutos();
		Compra compra = new Compra(15, produtos);
		
        XStream xStream = xStremCompraProdutos();		
		
		String xmlGerado = xStream.toXML(compra);
		
		assertEquals(resultadoEsperado, xmlGerado);
		
	}
	
	@Test
	public void deveUsarUmConversorDiferente() {
        String resultadoEsperado = "<compra estilo=\"novo\">\n" 
                + "  <id>15</id>\n"
                + "  <fornecedor>guilherme.silveira@caelum.com.br</fornecedor>\n"
                + "  <endereco>\n"
                + "    <linha1>Rua Vergueiro 3185</linha1>\n"
                + "    <linha2>8 andar - Sao Paulo - SP</linha2>\n"
                + "  </endereco>\n"
                + "  <produtos>\n" 
                + "    <produto codigo=\"1589\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descrição>geladeira duas portas</descrição>\n"
                + "    </produto>\n"
                + "    <produto codigo=\"1590\">\n"
                + "      <nome>geladeira</nome>\n"
                + "      <preco>1000.0</preco>\n"
                + "      <descrição>geladeira duas portas</descrição>\n"
                + "    </produto>\n"
                + "  </produtos>\n" 
                + "</compra>";
		Compra compra = compraDuasGeladeirasIguais();

		XStream xstream = xstreamParaCompraEProduto();
		xstream.registerConverter(new CompraDiferenteConverter());
		String xmlGerado = xstream.toXML(compra);

		assertEquals(resultadoEsperado, xmlGerado);
		
	    Compra deserializada = (Compra) xstream.fromXML(xmlGerado);
        assertEquals(compra, deserializada);		
	}


	private XStream xStremCompraProdutos() {
		XStream xStream = new XStream();
        xStream.alias("produto", Produto.class);
        xStream.alias("compra", Compra.class);
		xStream.aliasField("descrição", Produto.class, "descricao");
		xStream.useAttributeFor(Produto.class, "codigo");
		return xStream;
	}


	private List<Produto> compraProdutos() {
		Produto geladeira = geladeira();
		Produto ferroDePassar = ferroDePassar();
		List<Produto> produtos = new ArrayList();
		produtos.add(geladeira);
		produtos.add(ferroDePassar);
		return produtos;
	}


	private Produto ferroDePassar() {
		return new Produto("ferro de passar", 100, "ferro de passar com vaporizador", 1588);
	}


	private Produto geladeira() {
		return new Produto("geladeira", 1000, "geladeira duas portas", 1587);
	}
	
	
	@Test
	public void verificaXmlGeradoDoObjetoCompraProduto(){
        String xmlOrigem = "<compra>\n"+
                "  <codigo>15</codigo>\n"+
                "  <produtos>\n"+
                "    <produto codigo=\"1587\">\n"+
                "      <nome>geladeira</nome>\n"+
                "      <preco>1000.0</preco>\n"+
                "      <descrição>geladeira duas portas</descrição>\n"+
                "    </produto>\n"+
                "    <produto codigo=\"1588\">\n"+
                "      <nome>ferro de passar</nome>\n"+
                "      <preco>100.0</preco>\n"+
                "      <descrição>ferro de passar com vaporizador</descrição>\n"+
                "    </produto>\n"+
                "  </produtos>\n"+
                "</compra>";		
		List<Produto> produtos = compraProdutos();
		Compra compra = new Compra(15, produtos);
        XStream xStream = xStremCompraProdutos();		
		
		Compra compraDeserializada = (Compra)xStream.fromXML(xmlOrigem);		
		
		assertEquals(compra, compraDeserializada);
	}

	
	private Compra compraDuasGeladeirasIguais() {
	    Produto geladeira = geladeira();

	    List<Produto> produtos = new ArrayList<Produto>();
	    produtos.add(geladeira);
	    produtos.add(geladeira);

	    Compra compra = new Compra(15, produtos);
	    return compra;
	}
	
	private Compra compraDuasGeladeirasEFerro() {
	    Produto geladeira = geladeira();
	    Produto ferro = ferroDePassar();

	    List<Produto> produtos = new ArrayList<Produto>();
	    produtos.add(geladeira);
	    produtos.add(ferro);

	    Compra compra = new Compra(15, produtos);
	    return compra;
	}
	
	@Test
	public void deveSerializarDuasGeladeiras(){
		String resultadoEsperado = "<compra>\n" 
		        + "  <codigo>15</codigo>\n"
		        + "  <produtos>\n" 
		        + "    <produto codigo=\"1587\">\n"
		        + "      <nome>geladeira</nome>\n"
		        + "      <preco>1000.0</preco>\n"
		        + "      <descrição>geladeira duas portas</descrição>\n"
		        + "    </produto>\n"
		        + "    <produto codigo=\"1587\">\n"
		        + "      <nome>geladeira</nome>\n"
		        + "      <preco>1000.0</preco>\n"
		        + "      <descrição>geladeira duas portas</descrição>\n"
		        + "    </produto>\n"
		        + "  </produtos>\n" 
		        + "</compra>";
		
        XStream xStream = xStremCompraProdutos();
        xStream.setMode(XStream.NO_REFERENCES);
        String xmlGerado = xStream.toXML(compraDuasGeladeirasIguais());
		
        assertEquals(resultadoEsperado, xmlGerado);
	}
	
	@Test
	public void deveSerializarDuasGeladeirasImplicitaTest(){
		String resultadoEsperado = "<compra>\n" 
                + "  <codigo>15</codigo>\n"
                + "  <produto codigo=\"1587\">\n"
                + "    <nome>geladeira</nome>\n"
                + "    <preco>1000.0</preco>\n"
                + "    <descrição>geladeira duas portas</descrição>\n"
                + "  </produto>\n" 
                + "  <produto codigo=\"1588\">\n"
                + "    <nome>ferro de passar</nome>\n"
                + "    <preco>100.0</preco>\n"
                + "    <descrição>ferro de passar com vaporizador</descrição>\n"
                + "  </produto>\n"
                + "</compra>";
		
        XStream xStream = xStremCompraProdutos();
        xStream.addImplicitCollection(Compra.class, "produtos");
        String xmlGerado = xStream.toXML(compraDuasGeladeirasEFerro());
		
        assertEquals(resultadoEsperado, xmlGerado);
	}	
	
	private Compra compraComLivroEMusica() {
	    Produto passaro = new Livro("O Pássaro Raro", 100.0, "dez histórias sobre a existência", 1589);
	    Produto passeio = new Musica("Meu Passeio", 100.0, "música livre", 1590);
	    List<Produto> produtos = new LinkedList<Produto>();
	    produtos.add(passaro);
	    produtos.add(passeio);

	    return new Compra(15, produtos);
	}		
	
	private XStream xstreamParaCompraEProduto() {
	    XStream xstream = new XStream();
	    xstream.alias("compra", Compra.class);
	    xstream.alias("produto", Produto.class);
	    xstream.alias("livro", Livro.class);
	    xstream.alias("musica", Musica.class);
	    xstream.aliasField("descrição", Produto.class, "descricao");
	    xstream.useAttributeFor(Produto.class, "codigo");
	    return xstream;
	}	
	
	@Test
	public void deveSerializarLivroEMusica(){
		String resultadoEsperado = "<compra>\n" 
                + "  <codigo>15</codigo>\n"
                + "  <produtos class=\"linked-list\">\n" 
                + "    <livro codigo=\"1589\">\n"
                + "      <nome>O Pássaro Raro</nome>\n"
                + "      <preco>100.0</preco>\n"
                + "      <descrição>dez histórias sobre a existência</descrição>\n"
                + "    </livro>\n" 
                + "    <musica codigo=\"1590\">\n"
                + "      <nome>Meu Passeio</nome>\n"
                + "      <preco>100.0</preco>\n"
                + "      <descrição>música livre</descrição>\n"
                + "    </musica>\n"
                + "  </produtos>\n" 
                + "</compra>";
		
		XStream xStream = xstreamParaCompraEProduto();
		String xmlGerado = xStream.toXML(compraComLivroEMusica());
		
		assertEquals(resultadoEsperado, xmlGerado);
		
	}

}
