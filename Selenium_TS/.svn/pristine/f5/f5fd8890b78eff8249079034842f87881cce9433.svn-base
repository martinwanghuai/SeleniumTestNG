package test;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.sikuli.basics.ImageLocator;
import org.sikuli.basics.Settings;
import org.sikuli.script.App;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Finder;
import org.sikuli.script.Match;
import org.sikuli.script.Region;
import org.sikuli.script.Screen;
import org.sikuli.webdriver.SikuliFirefoxDriver;

public class Test {
  
  private static List<Found> matches = new ArrayList<Found>();

  public static void main(String[] args) throws FindFailed {
    // set the imagepath 
    ImageLocator.setBundlePath(new File(System.getProperty("user.dir"), "images.sikuli").getAbsolutePath());
    p("ImagePath %s", ImageLocator.getBundlePath());
//    Settings.OcrDataPath = true;
    
    Settings.OcrTextRead = true;
    Settings.OcrTextSearch = true;
    
    Screen s = new Screen();
    
    // the site as in images.sikuli/ScreenShot.png must be visible
    App.open("D:/Application/Safari/Safari.exe");
    SikuliFirefoxDriver driver = new SikuliFirefoxDriver();        
    driver.get("https://answers.launchpad.net/sikuli/+question/242405");

    //    App.focus("Safari");
    Region win = App.focusedWindow().grow(-20);
    
    int debugLight = 1; // set to 0 to switch off highlighting
    
//    Match match = s.find("Hi RaiMan (and others) !");
//    match.highlight(debugLight);
    
    
    
    // to eat up primary Sikuli initialization
    s.find("person.png");

    // restrict to a region that makes sense
    Region reg = s.find("head.png").below();/*.intersection(win).highlight(debugLight);*/
//    Region reg = win.find("head.png").below().intersection(win).highlight(debugLight);
    
    long start = (new Date()).getTime();
    // we look on the screen
    Iterator<Match> persons = reg.findAll("person.png");
    
    // copy the matches to a more flexible list
    while (persons.hasNext()) {
      matches.add(new Found(persons.next()));
    }
    // sort the matches top down
    Collections.sort(matches);
    
    p("ScreenMatches %d", matches.size());
    for (Found m : matches) {
      // show the matches
      m.getMatch().highlight(debugLight);
    }
    p("elapsed: %d", (new Date()).getTime() - start);
    matches.clear();
    
    start = (new Date()).getTime();
    // we look in the image taken from reg
    Finder fndr = new Finder(s.capture(reg));
    fndr.findAll("person.png");
    while (fndr.hasNext()) {
      matches.add(new Found(fndr.next(), reg));
    }
    Collections.sort(matches);

    p("ImageMatches %d", matches.size());
    for (Found m : matches) {
      m.getMatch().highlight(debugLight);
    }
    p("elapsed: %d", (new Date()).getTime() - start);
   
    // to get back to the dev IDE at normal and
    App.focus("NetBeans 7.3.1.app");
  }
  
  private static void p(String m, Object... args) {
    System.out.println(String.format("[user] " + m, args));
  }
}

class Found implements Comparable<Found> {
  Match match;
  
  public Found() {
    match = null;
  }
  
  public Found(Match m) {
    match = m; 
  }
  
  public Found(Match m, Region r) {
    match = m;
    m.x += r.x;
    m.y += r.y;
  }
  
  public Match getMatch() {
    return match;
  }
  
  @Override
  public int compareTo(Found f) {
    Match m1 = getMatch();
    Match m2 = f.getMatch();
    if (m1.y < m2.y) {
      return -1;
    }
    if (m1.y > m2.y) {
      return 1;
    }
    return 0;
  }  
}