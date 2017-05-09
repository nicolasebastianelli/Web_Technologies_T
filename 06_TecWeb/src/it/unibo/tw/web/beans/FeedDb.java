package it.unibo.tw.web.beans;

import java.io.Serializable;

import java.util.*;

public class FeedDb implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Feed> feeds;
	
	public FeedDb() {
		// initialize the database with some information, just to test this project
		super();
		this.feeds = new ArrayList<Feed>();
		
		Feed feed = new Feed(
				"Chiuse le urne, iniziano gli scrutini. In Sardegna sfida Soru-Cappellacci",
				"Bassa l'affluenza: alle 11 aveva votato meno del 55% dei cittadini. <br/>Occhi sulla poltrona di governatore: il leader Pd prova a difenderla.",
				"",
				"POLITICA",
				"Mon, 16 Feb 2009 15:58:35 +0200",
				"http://www.lastampa.it/redazione/cmsSezioni/politica/200902articoli/41071girata.asp",
				"http://www.lastampa.it/redazione/cmssezioni/politica/200902images/sardegna01p.jpg"
			);
		this.feeds.add(feed);
		
		feed = new Feed(
				"\"Tariffe cambiate senza informare gli utenti\". Maxi-multa dell'Antitrust ai colossi telefonici",
				"Sanzionati Tim e Vodafone con una contravvenzione di 500 mila euro. Cambiavano i piani senza dare informazioni adeguate ai consumatori. <em>Altroconsumo</em>: serve una class action.",
				"",
				"ECONOMIA",
				"Mon, 16 Feb 2009 15:58:35 +0200",
				"http://www.lastampa.it/redazione/cmsSezioni/economia/200902articoli/41064girata.asp",
				""
			);
		this.feeds.add(feed);
		
		feed = new Feed(
				"Del Noce:  \"Sanremo risorge o muore\"",
				"Il direttore di Rai 1: \"O arriveranno i risultati o &egrave; crisi. Pretestuose le polemiche sui compensi\". Bonolis su Povia: \"Arcigay mosso da rabbia\"<BR>" +
				"<DIV style=\"PADDING-TOP: 6px\"><DIV><SPAN class=boxocchiello><FONT face=Georgia color=#e2021c size=1>FOTO </FONT></SPAN><A class=link href=\"/multimedia/multimedia.asp?IDmsezione=17&amp;IDalbum=15725&amp;tipo=FOTOGALLERY\" target=_self><FONT color=#2f302f>La gag di Del Noce e Bonolis</FONT></A><SPAN class=boxocchiello><FONT face=Georgia color=#e2021c size=1> FOTO </FONT></SPAN><A class=link href=\"/multimedia/multimedia.asp?IDmsezione=17&amp;IDalbum=15728&amp;tipo=FOTOGALLERY\" target=_self><FONT color=#2f302f>GiovedÃ¬ Kevin Spacey sul palco</FONT></A></DIV></DIV>",
				"",
				"SPETTACOLI",
				"Mon, 16 Feb 2009 15:58:35 +0200",
				"http://www.lastampa.it/redazione/cmsSezioni/spettacoli/200902articoli/41068girata.asp",
				"http://www.lastampa.it/redazione/cmssezioni/spettacoli/200902images/sanremo01p.jpg"
			);
		this.feeds.add(feed);

		feed = new Feed(
				"Alla sbarra i pirati del Web: \"Andr&agrave;  tutto bene\"",
				"A Stoccolma si apre il processo contro i responsabili di piratebay.org dopo anni di battaglia fra multinazionali, polizia e attivisti della Rete. Gli imputati sicuri: \"Non abbiamo paura\".",
				"",
				"TECNOLOGIA",
				"Mon, 16 Feb 2009 15:58:35 +0200",
				"http://www.lastampa.it/_web/cmstp/tmplrubriche/tecnologia/grubrica.asp?ID_blog=30&ID_articolo=5767&ID_sezione=38&sezione=News",
				""
			);
		this.feeds.add(feed);
	}
	
	public List<Feed> getFeeds() {
		return feeds;
	}
	public void Feeds(List<Feed> feeds) {
		this.feeds = feeds;
	}

	public List<Feed> find(String category){
		List<Feed> res = new ArrayList<Feed>();
		for ( Feed feed : feeds ) {
			if(feed.getCategory().equals(category)){
				res.add(feed);
			}
		}
		return res;
	}
	
	public List<String> getCategories(String categoryStartingWith){
		List<String> result = new ArrayList<String>();
		for (int i =0; i< getFeeds().size();i++){
			if(getFeeds().get(i).getCategory().startsWith(categoryStartingWith))
				result.add(getFeeds().get(i).getCategory());
		}
		return result;
	}
	
}
