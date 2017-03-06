package org.pragyaa.pragyaa2017.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.wooplr.spotlight.SpotlightView;

import org.pragyaa.pragyaa2017.EventDetailsActivity;
import org.pragyaa.pragyaa2017.R;

/**
 * Created by Nikhya on 12/21/2016.
 */

public class DepartmentalFragment extends Fragment {

    public ProgressDialog mProgress;
    FloatingActionButton fab;
    private static boolean flag = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fab = (FloatingActionButton) getActivity().findViewById(R.id.dept_fab);

    }

    @Override
    public void onStart() {
        super.onStart();

        if (!flag) {
            new SpotlightView.Builder(getActivity())
                    .introAnimationDuration(400)
                    .enableRevalAnimation(true)
                    .performClick(true)
                    .fadeinTextDuration(400)
                    .headingTvColor(Color.parseColor("#eb273f"))
                    .headingTvSize(32)
                    .headingTvText("")
                    .subHeadingTvColor(Color.parseColor("#ffffff"))
                    .subHeadingTvSize(16)
                    .subHeadingTvText("Departmental event coordinators")
                    .maskColor(Color.parseColor("#dc000000"))
                    .target(fab)
                    .lineAnimDuration(400)
                    .lineAndArcColor(Color.parseColor("#e67e22"))
                    .dismissOnTouch(true)
                    .dismissOnBackPress(true)
                    .enableDismissAfterShown(true)
                    .usageId("info_dept_text")
                    .show();

        }
        flag = true;


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = (RecyclerView) inflater.inflate(
                R.layout.recycler_view, container, false);
        DepartmentalFragment.ContentAdapter adapter = new DepartmentalFragment.ContentAdapter(recyclerView.getContext());
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

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_event, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.tile_picture);
            name = (TextView) itemView.findViewById(R.id.tile_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, EventDetailsActivity.class);
                    intent.putExtra("image", ContentAdapter.images[getAdapterPosition()]);

                    intent.putExtra("event_category", "departmental");
                    intent.putExtra(EventDetailsActivity.EXTRA_POSITION, getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }
    }


    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        // Set numbers of Tiles in RecyclerView.
        private Context mContext;
        private ProgressDialog mProgress;

        //23 images
        private static final String[] images = {
                "http://codecondo.com/wp-content/uploads/2014/04/5-Coding-Challenges-to-Help-You-Train-Your-Brain.jpg",
                "http://2015.pragyaa.org/events/img/codenza.jpg",
                "http://www.pngall.com/wp-content/uploads/2016/07/Presentation-PNG-Image.png",
                "http://img.wonderhowto.com/img/86/37/63568648324037/0/c-c-programming-for-hackers-part-2-history-c-and-coding-our-first-program.1280x600.jpg",
                "http://clipartsign.com/upload/2016/10/31/powerpoint-presentation-clipart-clipart-kid-2.png",
                "http://4.bp.blogspot.com/-QoCVRobvIDA/Vj-kXqi0xmI/AAAAAAAACzg/Xr7R9DZjck4/s1600/DSC_0664.JPG",
                "http://www.puertomonttregional.cl/soporte/images/bg-img1.jpg",
                "http://www.sae.org/dlymagazineimages/web/516/9628_11205.JPG",
                "http://www.electroschematics.com/wp-content/uploads/2016/05/beginner-quiz-1.png",
                "http://diffusion.org.uk/wp-content/uploads/2010/09/old-ebooks.jpg",
                "http://www.hannovermesse.de/files/001-fs5/media/bilder/news/trendspots-produktneuheiten/microsoft-hmi-2016_content_image_position_right_left.jpg",
                "http://cdn01.pelfusion.com/wp-content/uploads/2015/03/powerpoint-presentation.png",
                "http://bimcrunch.com/wp-content/uploads/BIM%20crunch%20image.jpg",
                "http://civildigital.com/wp-content/uploads/2016/06/Topics-from-Surveying.jpg",
                "http://1742bq9a87b42a5t71473u0b.wpengine.netdna-cdn.com/wp-content/uploads/2013/12/chemical1.jpg",
                "http://altcareers.csmls.org/wp-content/uploads/2014/05/chemtech.jpg",
                "http://images.fibre2fashion.com/Newsresource/images/180/500million-green-fund-for-bangladesh-textile-sector_191708.jpg",
                "http://images.all-free-download.com/images/graphicthumb/business_presentation_vector_silhouettes_531241.jpg",
                "http://www.snscesanus.com/dept/img/poster.jpg",
                "http://www.satollo.net/wp-content/uploads/2015/03/quiz.jpg",
                "https://s-media-cache-ak0.pinimg.com/originals/71/c2/5b/71c25b4d918eb035cb84896ce3b34890.jpg",
                "http://www.freebyte.com/cad/screen/justcad.jpg",
                "https://www.presentationmagazine.com/newimages/ditch-ppt-510.jpg",
                "http://images.clipartpanda.com/city-clipart-community-city-clipart-1.jpg",
                "http://www.lboro.ac.uk/media/wwwlboroacuk/content/chemicalengineering/homepageslider/formula.jpg"

        };
        private final String[] eventName;

        public ContentAdapter(Context context) {
            mContext = context;
            Resources resources = context.getResources();
            eventName = resources.getStringArray(R.array.departmental);
            mProgress = new ProgressDialog(mContext);
            mProgress.setMessage("Loading...");
        }

        @Override
        public DepartmentalFragment.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new DepartmentalFragment.ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
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
