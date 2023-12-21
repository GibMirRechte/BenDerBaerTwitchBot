package de.bot.utils;

public class News {

    private final String newsKey;
    private String title;
    private String text;
    private final String creator;
    private final long created;
    private final AccountRank creatorAccountRank;
    private final String lastEditor;
    private final AccountRank lastEditorAccountType;
    private final long lastEdited;

    public News(String newsKey, String title, String text, long created, String creator, AccountRank creatorAccountRank, String lastEditor, AccountRank lastEditorAccountType, long lastEdited) {
        this.newsKey = newsKey;
        this.title = title;
        this.text = text;
        this.created = created;
        this.creator = creator;
        this.creatorAccountRank = creatorAccountRank;
        this.lastEditor = lastEditor;
        this.lastEditorAccountType = lastEditorAccountType;
        this.lastEdited = lastEdited;
    }

    public AccountRank getCreatorAccountRank() {
        return creatorAccountRank;
    }


    public String getNewsKey() {
        return newsKey;
    }

    public String getText() {
        return text.replace("&&&-", "\n");
    }

    public String getTitle() {
        return title;
    }

    public long getCreated() {
        return created;
    }

    public AccountRank getLastEditorAccountType() {
        return lastEditorAccountType;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLastEdited() {
        return lastEdited;
    }

    public String getCreator() {
        return creator;
    }

    public String getLastEditor() {
        return lastEditor;
    }
}