package sandip.kafkademo.demoproducer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class AccessLogReader {
	final static Logger logger = Logger.getLogger(AccessLogReader.class);
	
	private static final String REG_EX = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+)";
	private static final Pattern pattern = Pattern.compile(REG_EX);
	
	/**
	 * Convert the Access log file and translate into List.
	 * @return
	 */
	public static ArrayList logConverter(String logLine) {
		logger.debug("Logline: " + logLine);
		// logLine = "127.0.0.1 - - [21/Jul/2014:9:55:27 -0800] \"GET /home.html HTTP/1.1\" 200 2048";
		Matcher m = pattern.matcher(logLine);
		
	    if (!m.find()) {
		      logger.debug("Cannot parse logline{0}");
		      throw new RuntimeException("Error parsing logline");
		}
		
		int matchCounter = m.groupCount();
		
		ArrayList matchGroup = new ArrayList();
		
		for (int i = 1; i <= matchCounter; i++) {
			matchGroup.add(m.group(i));
		}
		return matchGroup;
	}
	
}
