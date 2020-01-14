package com.example.starwarscollectablegame.View.ui.profile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class AvatarViewAdapter extends RecyclerView.Adapter<AvatarViewAdapter.AvatarViewHolder> {

    private Context context;
    public static ArrayList<Integer> avatarStrings = new ArrayList<>(
            Arrays.asList(R.raw.icon_c3p0, R.raw.icon_chewee, R.raw.icon_hann_solo,
                    R.raw.icon_kaylo_ren, R.raw.icon_luke_skywalker, R.raw.icon_padmee,
                    R.raw.icon_sandperson, R.raw.icon_storm_trooper, R.raw.iconfinder_c3p0_1626611,
                    R.raw.iconfinder_chewbacca_1626612, R.raw.iconfinder_darth_maul_1626614, R.raw.iconfinder_ewok_1626617,
                    R.raw.iconfinder_jabba_the_hutt, R.raw.iconfinder_jawa_1626622, R.raw.iconfinder_lobot_1626627,
                    R.raw.iconfinder_luke_skywalker_1626628, R.raw.iconfinder_obiwan_kenobi_1626629, R.raw.iconfinder_princess_leia_1626630,
                    R.raw.iconfinder_r2d2_1626632, R.raw.iconfinder_tusken_raider_1626635, R.raw.iconfinder_yoda_1626636));

    private ImageView selectedAvatarImage;

    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = -1;

    public AvatarViewAdapter(Context context, ImageView selectedImg) {
        this.context = context;
        selectedAvatarImage = selectedImg;
        notifyDataSetChanged();

        //todo rewrite code to load current selected avatar from PlayerDataDatabase

        //final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_id), Context.MODE_PRIVATE);
        //this.checkedPosition = sharedPref.getInt(context.getString(R.string.preferences_player_id), -1);
    }

    @NonNull
    @Override
    public AvatarViewAdapter.AvatarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.avatar_item, parent, false);
        return new AvatarViewHolder(view, this.selectedAvatarImage);
    }

    @Override
    public void onBindViewHolder(@NonNull AvatarViewHolder holder, int position) {
        holder.bind(avatarStrings.get(position));
    }


    @Override
    public int getItemCount() {
        return avatarStrings.size();
    }

    class AvatarViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private ImageView avatarImg;

        private ImageView selectedAvatar;

        AvatarViewHolder(@NonNull View itemView, ImageView activity) {
            super(itemView);
            imageView = itemView.findViewById(R.id.avatar_check_sign);
            imageView.setImageAlpha(100);
            avatarImg = itemView.findViewById(R.id.avatar_img);
            selectedAvatar = activity;
        }

        void bind(final int img) {
            InputStream imageStream = context.getResources().openRawResource(img);
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
            this.avatarImg.setImageBitmap(bitmap);
            if (checkedPosition == -1) {
                imageView.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();

                        InputStream imageStream = context.getResources().openRawResource(avatarStrings.get(getAdapterPosition()));
                        Bitmap bitmap = BitmapFactory.decodeStream(imageStream);
                        selectedAvatar.setImageBitmap(bitmap);

                    }
                }
            });
        }
    }

    public int getSelected() {
        if (checkedPosition != -1) {
            return avatarStrings.get(checkedPosition);
        }
        return -1;
    }

    public void setSelected(int imgId) {
        checkedPosition = avatarStrings.indexOf(imgId);
    }
}
