package com.tonyyuzhang.sis.search;

import java.util.*;

public class Server extends Thread {
   private ResultsListener listener;
   private List<Search> queue = new LinkedList<Search>();

   public Server(ResultsListener listener) {
      this.listener = listener;
      start();
   }
   
   @Override
   public void run() {
      while (true) {
         if(!queue.isEmpty())
            execute(queue.remove(0));
         Thread.yield();
      }
   }
   
   private void execute(Search search) {
      search.execute();
      listener.executed(search);
   }

   public void add(Search search) {
      queue.add(search);
   }

}
