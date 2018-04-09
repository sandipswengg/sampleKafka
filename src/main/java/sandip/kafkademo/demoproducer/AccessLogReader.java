package sandip.kafkademo.demoproducer;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccessLogReader {
	private static final String REG_EX = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})\\] \"(\\S+) (\\S+) (\\S+)\" (\\d{3}) (\\d+)";
	private static Pattern pattern = Pattern.compile(REG_EX);
	
	/**
	 * Convert the Access log file and translate into List.
	 * @return
	 */
	public static ArrayList logConverter(String logLine) {
		// 127.0.0.1 - - [21/Jul/2014:9:55:27 -0800] \"GET /home.html HTTP/1.1\" 200 2048
		Matcher m = pattern.matcher(logLine);
		
//		if (!m.find()) {
//			throw new RuntimeException("Regex did not match with the the log entry");
//		}
		
		int matchCounter = m.groupCount();
		
		ArrayList matchGroup = new ArrayList();
		
		for (int i = 1; i <= matchCounter; i++) {
			matchGroup.add(m.group(i));
		}
		return matchGroup;
	}
	
}
