/*
MIT License

Copyright (c) 2021 Andrea Carcone

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package carcons.tubescrape;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class TubeScraper {

    private ScrapeOnFinishListener listener;
    private ArrayList<TubeMedia> mediaList;
    private String searchQuery;
    private final String SEARCH_URL = "https://www.youtube.com/results?search_query=";


    public TubeScraper(String searchQuery, ScrapeOnFinishListener listener) {
        try {
            this.listener = listener;
            this.searchQuery = URLEncoder.encode(searchQuery, java.nio.charset.StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    

    public void scrape() {

        new Thread(new Runnable() {

            public void run() {

                mediaList = new ArrayList<>();
                try {

                    String pageSource = new TubeConnectionInfo().getPageSource(SEARCH_URL + searchQuery);

                    String idSplit, titleSplit;
                    String videosIds[] = pageSource.split(Pattern.quote("{\"videoRenderer\":{\"videoId\":\""));
                    String videosTitles[] = pageSource.split(Pattern.quote("\"title\":{\"runs\":[{\"text\":\""));

                    for (int i = 1; i < videosIds.length - 1; i++) {

                        TubeMedia media = new TubeMedia();

                        idSplit = videosIds[i].split(Pattern.quote("\","))[0];
                        titleSplit = videosTitles[i].split(Pattern.quote("\"}"))[0];

                        media.setYoutubeId(idSplit);
                        media.setTitle(titleSplit);
                        mediaList.add(media);

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (URISyntaxException e2) {
                    e2.printStackTrace();

                }

                if (listener != null)
                    listener.OnFinish(mediaList);

            }

        }).start();

    }

}
