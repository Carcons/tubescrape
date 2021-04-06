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

import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class TubeMedia{

    private String picture,
                   title,
                   youtubeUrl,
                   youtubeID;


    public TubeMedia(){};

    public TubeMedia(String youtubeUrl){
        this.youtubeUrl = youtubeUrl;
        setYoutubeIDfromUrl();
        setPicFromYoutubeID();
    }

    private void setPicFromYoutubeID(){
        picture = "https://img.youtube.com/vi/" + youtubeID + "/hqdefault.jpg";
    }

    private String setYoutubeIDfromUrl(){
        String pattern = "https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*";

        Pattern compiledPattern = Pattern.compile(pattern,
                Pattern.CASE_INSENSITIVE);
        Matcher matcher = compiledPattern.matcher(youtubeUrl);
        if (matcher.find()) {
            youtubeID = matcher.group(1);
        }
        return youtubeID;
    }

    @Override
    public String toString(){
        return youtubeUrl;
    }
    
    public void setYoutubeUrl(String youtubeUrl){
        this.youtubeUrl = youtubeUrl;
        setYoutubeIDfromUrl();
        setPicFromYoutubeID();
    }

    public void setYoutubeTitle(String title){
        this.title = title;
    }

    public void setYoutubeId(String youtubeID){
        this.youtubeID = youtubeID;
        this.youtubeUrl = "https://www.youtube.com/watch?v=" + youtubeID;
        setPicFromYoutubeID();
    }

    public void setPic(String picture){
        this.picture = picture;
    }

    public String getPic(){
        return picture;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getYouTubeUrl(){
        return youtubeUrl;
    }

    public String getYouTubeID(){
        return youtubeID;
    }

    public String getTitle(){
        return title;
    }

    

    
}