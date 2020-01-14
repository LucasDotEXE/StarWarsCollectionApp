package com.example.starwarscollectablegame.View.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ProfileViewAdapter extends ListAdapter<PlayerData, ProfileViewAdapter.SingleViewHolder> {

    private static final String TAG = "ProfileViewAdapter";

    private Context context;

    private OnItemClickListener listener;


    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition = -1;

//    public ProfileViewAdapter(Context context) {
//        super();
//        this.context = context;
//        this.playerData = new ArrayList<>();
//    }

    public ProfileViewAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<PlayerData> DIFF_CALLBACK = new DiffUtil.ItemCallback<PlayerData>() {
        @Override
        public boolean areItemsTheSame(@NonNull PlayerData oldItem, @NonNull PlayerData newItem) {
            return oldItem.getPlayer_name().equals(newItem.getPlayer_name());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PlayerData oldItem, @NonNull PlayerData newItem) {
            return oldItem.getAvatar_id() == newItem.getAvatar_id();
        }
    };

    @Override
    public void submitList(@Nullable List<PlayerData> list) {
        super.submitList(list);

        final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_id), Context.MODE_PRIVATE);
        this.checkedPosition = getPlayerNames().indexOf(sharedPref.getString(context.getString(R.string.preferences_player_id), ""));
        notifyDataSetChanged();
    }

    //    public void setPlayerData(List<PlayerData> playerData) {
//        this.playerData = playerData;
//
//        final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_id), Context.MODE_PRIVATE);
//        this.checkedPosition = getPlayerNames().indexOf(sharedPref.getString(context.getString(R.string.preferences_player_id), ""));
//        notifyDataSetChanged();
//    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_item, viewGroup, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int position) {
        singleViewHolder.bind(getItem(position));
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;

        private ImageView imageView;
        private ImageView avatarImg;

        SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.player_name_textview);
            imageView = itemView.findViewById(R.id.check_sign);
            avatarImg = itemView.findViewById(R.id.player_avatar);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener == null || getAdapterPosition() == RecyclerView.NO_POSITION) {
                        return false;
                    }
                    listener.onItemClick(getPlayerDataAt(getAdapterPosition()));
                    return true;
                }
            });
        }

        void bind(final PlayerData playerData) {
            if (checkedPosition == -1) {
                imageView.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
            textView.setText(playerData.getPlayer_name());
            InputStream imageStream = context.getResources().openRawResource(playerData.getAvatar_id());
            Bitmap bitmap = BitmapFactory.decodeStream(imageStream);

            avatarImg.setImageBitmap(bitmap);
            avatarImg.setMaxHeight(50);
            avatarImg.setMaxWidth(50);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    imageView.setVisibility(View.VISIBLE);
                    if (checkedPosition != getAdapterPosition()) {
                        notifyItemChanged(checkedPosition);
                        checkedPosition = getAdapterPosition();
                    }
                    final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_id), Context.MODE_PRIVATE);
                    final SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putString(context.getString(R.string.preferences_player_id), playerData.getPlayer_name()).apply();
                    Log.wtf(TAG, "Selected Player ID:" +playerData.getPlayer_name());
                }
            });
        }

    }
    public List<String> getPlayerNames() {
        List<String> names = new ArrayList<>();
        for (PlayerData playerData : getCurrentList()) {
            names.add(playerData.getPlayer_name());
        }
        return names;
    }

    public interface OnItemClickListener {

        void onItemClick(PlayerData playerData);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public PlayerData getPlayerDataAt(int pos) {
        return getItem(pos);
    }
}
