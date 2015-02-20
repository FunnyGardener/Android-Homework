package net.k6.browser;

import java.util.ArrayList;
import java.util.List;


public final class History {
    private static History history;
    private static List <String> historyList = new ArrayList<String>();

    private History() {
    }

    public static void addHistory(String urlHistory) {
        if (history == null) {
            history = new History();
            historyList.add(urlHistory);
        } else historyList.add(urlHistory);
    }
    public static List getHistory(){
        if (historyList.isEmpty()) {
            return null;
        }else return historyList;
    }
}
