package se.pjodd.service.node.location.iw;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parse output from "iw dev scan" on Gluon
 *
 * @author kalle
 * @since 2017-03-22 08:37
 */
public class IwScanParser {

  private List<IwScanSignal> signals = new ArrayList<>();
  private IwScanSignal currentSignal;

  private void addSignal() {
    if (currentSignal != null) {
      signals.add(currentSignal);
      currentSignal = null;
    }
  }

  private Pattern bssidPattern = Pattern.compile("\\s*BSS ((\\d|[a-fA-F]){2}:(\\d|[a-fA-F]){2}:(\\d|[a-fA-F]){2}:(\\d|[a-fA-F]){2}:(\\d|[a-fA-F]){2}:(\\d|[a-fA-F]){2})\\(?.+");
  private Pattern signalPattern = Pattern.compile("\\s*signal: (-?(\\d+(\\.\\d*)?))\\s*dBm");
  private Pattern lastSeenMsPattern = Pattern.compile("\\s*last seen: (\\d+)\\s*ms ago");
  private Pattern freqPattern = Pattern.compile("\\s*freq: (\\d+)");

  public void parse(Reader input) throws Exception {


    String line;
    BufferedReader br = new BufferedReader(input);
    while ((line = br.readLine()) != null) {
      Matcher matcher = bssidPattern.matcher(line);
      if (matcher.matches()) {
        addSignal();
        currentSignal = new IwScanSignal();
        currentSignal.setBssid(matcher.group(1));
      } else {
        matcher = signalPattern.matcher(line);
        if (matcher.matches()) {
          currentSignal.setSignalStrength(Float.parseFloat(matcher.group(1)));
        } else {
          matcher = lastSeenMsPattern.matcher(line);
          if (matcher.matches()) {
            currentSignal.setLastSeenMs(Integer.valueOf(matcher.group(1)));
          } else {
            matcher = freqPattern.matcher(line);
            if (matcher.matches()) {
              currentSignal.setFreq(Integer.valueOf(matcher.group(1)));
            }
          }
        }
      }
    }

    addSignal();
  }

  public List<IwScanSignal> getSignals() {
    return signals;
  }
}