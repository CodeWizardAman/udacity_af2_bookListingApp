package com.example.android.booklistingapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by UFO_24 on 13-11-2017.
 */

public class Book implements Parcelable {

    public String getBookTitle() {
        return bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public int getBookPages() {
        return bookPages;
    }

    public String getBookPublishedDate() {
        return bookPublishedDate;
    }

    private String bookTitle;
    private String bookAuthor;
    private int bookPages;
    private String bookPublishedDate;

    public Book() {
    }

    public Book(String title, String author, int pages, String publishDate) {

        bookTitle = title;
        bookAuthor = author;
        bookPages = pages;
        bookPublishedDate = publishDate;
    }


    public Book(Parcel parcel) {

        bookTitle = parcel.readString();
        bookAuthor = parcel.readString();
        bookPublishedDate = parcel.readString();
        bookPages = parcel.readInt();

    }

    public static final Parcelable.Creator<Book> CREATOR
            = new Creator<Book>() {
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int i) {
            return new Book[i];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {

        out.writeString(bookTitle);
        out.writeString(bookAuthor);
        out.writeString(bookPublishedDate);
        out.writeInt(bookPages);

    }
}
