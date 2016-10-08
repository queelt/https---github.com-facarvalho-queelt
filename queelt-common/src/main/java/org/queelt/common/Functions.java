package org.queelt.common;



import org.apache.commons.lang.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.safety.Whitelist;
import org.springframework.util.SerializationUtils;

public class Functions {
	public static String html2text (String input)
	{
		// breaks multi-level of escaping, preventing &amp;lt;script&amp;gt; to be rendered as <script>
		String replace = input.replace("&amp;", "");
		// decode any encoded html, preventing &lt;script&gt; to be rendered as <script>
		String html = StringEscapeUtils.unescapeHtml(replace);
		// remove all html tags, but maintain line breaks
		String clean = Jsoup.clean(html, "", Whitelist.none(), new Document.OutputSettings().prettyPrint(false));
		// decode html again to convert character entities back into text
		return StringEscapeUtils.unescapeHtml(clean);
	}
	
	public static byte[] serialize(Object state) {
		return SerializationUtils.serialize(state);
    }

	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] byteArray) {
		return (T)SerializationUtils.deserialize(byteArray);
    }

	
	
}
