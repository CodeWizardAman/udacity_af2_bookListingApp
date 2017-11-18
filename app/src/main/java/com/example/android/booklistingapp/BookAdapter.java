package com.example.android.booklistingapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by UFO_24 on 15-11-2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Activity context, int id, List<Book> bookList) {

        super(context, 0, bookList);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItemView;
        BookViewHolder viewHolder;

        Book item = getItem(position);

        if (convertView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

            viewHolder= new BookViewHolder(listItemView);

            listItemView.setTag(viewHolder);


        }
        else{
            listItemView = convertView;

           viewHolder =  (BookViewHolder) listItemView.getTag();
        }

        populateViews(viewHolder, item);

        return listItemView;
    }
    
    void populateViews(BookViewHolder viewHolder, Book item){

        TextView bookTitleTextView = viewHolder.getBookTitle();
        bookTitleTextView.setText(item.getBookTitle());

        TextView bookPagesTextView = viewHolder.getBookPages();
        bookPagesTextView.setText(String.valueOf(item.getBookPages()));

        TextView bookPublishDateTextView = viewHolder.getBookPublishDate();
        bookPublishDateTextView.setText(item.getBookPublishedDate());

        TextView bookWriterTextView  = viewHolder.getBookWriter();
        bookWriterTextView.setText(item.getBookAuthor());

    }
}
