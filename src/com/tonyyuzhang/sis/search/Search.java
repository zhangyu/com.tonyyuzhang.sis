package com.tonyyuzhang.sis.search;

import java.io.*;
import java.net.*;

import com.tonyyuzhang.sis.util.StringUtil;

public class Search {
   private URL url;
   private String searchString;
   private int matches = 0;
   private Exception exception = null;
   
   public Search(String url, String searchString) throws MalformedURLException {
      this.url = new URL(url);
      this.searchString = searchString;
   }

   public String getUrl() {
      return url.toString();
   }

   public String getText() {
      return searchString;
   }

   public void execute() {
      try {
         searchUrl();
      }
      catch (IOException e) {
         exception = e;
      }
   }

   private void searchUrl() throws IOException {
      URLConnection connection = url.openConnection();
      InputStream input = connection.getInputStream();
      BufferedReader reader = null;
      try {
         reader = new BufferedReader(new InputStreamReader(input));
         String line;
         while ((line = reader.readLine()) != null)
            matches += StringUtil.occurrences(line, searchString);
      }
      finally {
         if (reader != null)
            reader.close();
      }
   }

   public int matches() {
      return matches;
   }

   public boolean errored() {
      return exception != null;
   }

   public Exception getError() {
      return exception;
   }
}
