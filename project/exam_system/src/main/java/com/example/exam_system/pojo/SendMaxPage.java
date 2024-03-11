package com.example.exam_system.pojo;

public class SendMaxPage {
    private int maxPage;

    @Override
    public String toString() {
        return "SendMaxPage{" +
                "maxPage=" + maxPage +
                '}';
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }
}
