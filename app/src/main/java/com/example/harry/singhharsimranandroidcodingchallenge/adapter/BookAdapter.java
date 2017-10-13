package com.example.harry.singhharsimranandroidcodingchallenge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.harry.singhharsimranandroidcodingchallenge.R;
import com.google.gson.internal.LinkedTreeMap;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by harry on 10/12/17.
 */



public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder>{


    private static final String IMAGE_KEY = "imageURL";
    private static final String AUTHOR_KEY = "author";
    private static final String BOOK_KEY = "title";
    private ArrayList<LinkedTreeMap<String,String>> mList;
    private Context mContext;

    public BookAdapter(ArrayList<LinkedTreeMap<String,String>> list, Context context) {
        mList = list;
        mContext = context;
    }

    public BookAdapter.BookViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_layout, parent, false);
        BookViewHolder vh = new BookViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mBookView.setText(mList.get(position).get(BOOK_KEY));
        holder.mAuthorView.setText(mList.get(position).get(AUTHOR_KEY));
        Picasso.with(mContext).load(mList.get(position).get(IMAGE_KEY)).into(holder.mImageView);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mList.size();
    }


    public static class BookViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mBookView;
        public TextView mAuthorView;
        public ImageView mImageView;
        public BookViewHolder(View v) {
            super(v);
            mAuthorView = (TextView) v.findViewById(R.id.author);
            mBookView = (TextView) v.findViewById(R.id.book_name);
            mImageView = (ImageView) v.findViewById(R.id.imageView);
        }
    }

}

