package com.example.android.booklistingapp;

import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by UFO_24 on 18-11-2017.
 */

public class BookViewHolder {

@BindView(R.id.book_title_id)
TextView bookTitle;

    public TextView getBookTitle() {
        return bookTitle;
    }

    public TextView getBookWriter() {
        return bookWriter;
    }

    public TextView getBookPages() {
        return bookPages;
    }

    public TextView getBookPublishDate() {
        return bookPublishDate;
    }

    @BindView(R.id.book_writer_id)
TextView bookWriter;

@BindView(R.id.book_pages_id)
TextView bookPages;

@BindView(R.id.book_publish_date_id)
TextView bookPublishDate;

    public BookViewHolder(View view) {
        ButterKnife.bind(this, view);

    }


}


