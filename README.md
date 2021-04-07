# tubescrape
A java library that scrapes the youtube-search webpage.

Download source code and compile java classes:
javac carcons/tubescrape/*.java

Then create the jar library:
jar cf tubescrape.jar carcons/tubescrape/*.class

In your app you can implement the jar and use it:

```java
import carcons.tubescrape.ScrapeOnFinishListener;
import carcons.tubescrape.TubeMedia;
import carcons.tubescrape.TubeScraper;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        try {
            Scanner s = new Scanner(System.in);
            System.out.print("Search on youtube: ");
            String query = s.nextLine();
            s.close();
            new TubeScraper(query, new ScrapeOnFinishListener() {
                @Override
                public void OnFinish(ArrayList<TubeMedia> arg0) {
                    System.out.println("Found: " + arg0.size() + " elements.");
                    for (TubeMedia m : arg0) {
                        System.out.print("Title: " + m.getTitle() + "\nUrl: " + m.getYouTubeUrl() + "\n\n");
                    }
                }
            }).scrape();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
```
The OnFinish method allows you to use the searched results

```java
new TubeScraper(query, new ScrapeOnFinishListener() {
    @Override
    public void OnFinish(ArrayList<TubeMedia> arg0) {
        //The arg0 arraylist contains the finded media.
    }
}).scrape();
```
