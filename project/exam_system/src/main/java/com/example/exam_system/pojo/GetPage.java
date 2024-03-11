package com.example.exam_system.pojo;

public class GetPage {
    private int page;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "GetPage{" +
                "page=" + page +
                '}';
    }
}
