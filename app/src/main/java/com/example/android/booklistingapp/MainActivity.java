package com.example.android.booklistingapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    TextView textViewNoData;
    BookAdapter bookAdapter;
    ArrayList<Book> bookList = new ArrayList<>();

    static final String KEY_RESULTS = "booksKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        final EditText searchBookEditText = (EditText) findViewById(R.id.edit_text_id);

        textViewNoData = (TextView) findViewById(R.id.text_view_no_data_id);

        bookAdapter = new BookAdapter(this, 0, bookList);
        final ListView listView = findViewById(R.id.list_view_id);
        listView.setAdapter(bookAdapter);

        Button searchButton = (Button) findViewById(R.id.search_btn_id);
        searchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (Utils.isNetworkAvailable(getApplicationContext())) {

                    String searchUrl = getString(R.string.baseUrl) + searchBookEditText.getText().toString().trim().replaceAll("\\s+", "+");

                    BookListingSyncTask bookSyncTask = new BookListingSyncTask();

                    bookSyncTask.execute(searchUrl);
                    listView.setSelection(0);
                } else {
                    // display toast if internet connection unavailable
                    handleNoInternetToast();
                }
            }
        });

        if (savedInstanceState != null) {
            Book[] books = (Book[]) savedInstanceState.getParcelableArray(KEY_RESULTS);
            bookAdapter.addAll(books);
        }

    }

    void updateUI(List<Book> bookList) {

        bookAdapter.clear();
        if (bookList.isEmpty()) {
            // if no books found, show a message
            textViewNoData.setVisibility(View.VISIBLE);
        } else {
            textViewNoData.setVisibility(View.GONE);
        }

        bookAdapter.addAll(bookList);

    }

    private class BookListingSyncTask extends AsyncTask<String, Void, List<Book>> {


        @Override
        protected List<Book> doInBackground(String... urls) {

            List<Book> bookList = Utils.fetchBookData(urls[0]);
            return bookList;
        }

        @Override
        protected void onPostExecute(List<Book> books) {

            updateUI(books);
        }

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        super.onSaveInstanceState(savedInstanceState);

        Book[] books = new Book[bookAdapter.getCount()];

        for (int i = 0; i < books.length; i++) {
            books[i] = bookAdapter.getItem(i);
        }

        savedInstanceState.putParcelableArray(KEY_RESULTS, (Parcelable[]) books);
    }

    private void handleNoInternetToast() {
        Toast toast = Toast.makeText(getApplicationContext(), getString(R.string.noInernet),
                Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

    }

}


