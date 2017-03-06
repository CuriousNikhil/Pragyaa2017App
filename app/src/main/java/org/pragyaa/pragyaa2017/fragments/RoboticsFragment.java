package org.pragyaa.pragyaa2017.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.pragyaa.pragyaa2017.EventDetailsActivity;
import org.pragyaa.pragyaa2017.R;

/**
 * Created by Nikhya on 12/21/2016.
 */

public class RoboticsFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        // Set padding for Tiles
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.vsm_keyLines);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return recyclerView;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;

        public ViewHolder(final LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_event, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.tile_picture);
            name = (TextView) itemView.findViewById(R.id.tile_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EventDetailsActivity.class);
                    intent.putExtra("image", ContentAdapter.images[getAdapterPosition()]);
                    intent.putExtra("event_category", "robotics");
                    intent.putExtra(EventDetailsActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }


    /**
     * Adapter to display recycler view.
     */
    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Tiles in RecyclerView.
        private Context mContext;
        private ProgressDialog mProgress;

        // 4 images
        private static final String[] images = {

                "https://i.ytimg.com/vi/fUGD0Ae6Nvc/maxresdefault.jpg",
                "http://www.roboflux.com/admin/uploads/product/oPvmOkv19EpJHvIpYovVTS6FD37(19319)oiy0pb(19319)lee76WHA=1.jpg",
                "https://cdn.instructables.com/FSH/TRZR/INUV6HNJ/FSHTRZRINUV6HNJ.MEDIUM.jpg",
                "http://www.sporttechie.com/wp-content/uploads/2015/08/soccer1.jpg",
        };
        private final String[] eventName;

        public ContentAdapter(Context context) {
            mContext = context;
            Resources resources = context.getResources();
            eventName = resources.getStringArray(R.array.robotics);
            mProgress = new ProgressDialog(mContext);
            mProgress.setMessage("Loading...");
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new RoboticsFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            mProgress.show();
            Picasso.with(mContext).load(images[position]).into(holder.picture);
            holder.name.setText(eventName[position % eventName.length]);
            mProgress.dismiss();
        }

        @Override
        public int getItemCount() {
            return images.length;
        }
    }
}
