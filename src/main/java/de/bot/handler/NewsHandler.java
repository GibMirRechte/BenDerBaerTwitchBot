package de.bot.handler;

import de.bot.utils.AccountRank;
import de.bot.utils.News;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class NewsHandler {

    private static NewsHandler instance;
    private static final HashMap<String, News> newsList = new HashMap<>();

    AccountHandler accountHandler = AccountHandler.getInstance();

    public News getNews(String newsKey) {
        if (newsList.containsKey(newsKey)) {
            return newsList.get(newsKey);

        }
        try {
            Socket socket = new Socket("45.93.249.139", 3459);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream.println("NEWS");
            printStream.println(accountHandler.getAccount().getName());
            printStream.println("GET");
            printStream.println(newsKey);
            String title = bufferedReader.readLine();
            String text = bufferedReader.readLine();
            String creator = bufferedReader.readLine();
            AccountRank creatorAccountRank = accountHandler.getRank(bufferedReader.readLine());
            long created = Long.parseLong(bufferedReader.readLine());
            String lastEditor = bufferedReader.readLine();
            AccountRank lastEditorAccountType = accountHandler.getRank(bufferedReader.readLine());
            long lastEdited = Long.parseLong(bufferedReader.readLine());
            boolean isPublic = Boolean.parseBoolean(bufferedReader.readLine());

            News news = new News(newsKey, title, text, created, creator, creatorAccountRank, lastEditor, lastEditorAccountType, lastEdited, isPublic);
            newsList.put(newsKey, news);
            return news;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<News> getNewestNews(int numberOfNews) {
        List<News> newestNews = new ArrayList<>();
        List<String> newestNewsKeyList = new ArrayList<>();
        try {
            Socket socket = new Socket("45.93.249.139", 3459);
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            printStream.println("NEWS");
            printStream.println("SYSTEM");
            printStream.println("GET LATEST");
            printStream.println("nullkey");
            printStream.println(numberOfNews);

            for (int i = 0; i < numberOfNews; i++) {
                String textLine = bufferedReader.readLine();
                if (textLine.equalsIgnoreCase("END OF NEWEST NEWS LIST")) {
                    break;
                } else {
                    newestNewsKeyList.add(textLine);
                }
            }
            socket.close();
        } catch (Exception e) {
            //IGNORED
        }

        for (String newsKey : newestNewsKeyList) {
            newestNews.add(getNews(newsKey));
        }

        return newestNews;
    }

    public static NewsHandler getInstance() {
        if (instance == null) instance = new NewsHandler();
        return instance;
    }
}
