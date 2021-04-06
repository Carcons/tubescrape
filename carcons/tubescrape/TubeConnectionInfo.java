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

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;


public class TubeConnectionInfo {

    private final String userAgent[] = { "User-Agent",
            "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.9; rv:50.0) Gecko/20100101 Firefox/50.0" };
    private final String cookie[] = { "CONSENT", "YES+aaa.bbb-00000000-0-ccc.dd+ee+000" };

    public String getPageSource(String urlStr) throws IOException, URISyntaxException {

        URL url = new URL(urlStr);
        HttpURLConnection urlc = (HttpURLConnection) url.openConnection();
        urlc.addRequestProperty(userAgent[0], userAgent[1]);
        urlc.setRequestProperty(cookie[0], cookie[1]);

        BufferedInputStream buffer;
        buffer = new BufferedInputStream(urlc.getInputStream());

        StringBuilder builder = new StringBuilder();
        byte[] by = new byte[1024];
        int byteRead;

        while ((byteRead = buffer.read(by)) != -1) {
            builder.append(new String(by, 0, byteRead));
        }

        buffer.close();
        return builder.toString();
    }


}
