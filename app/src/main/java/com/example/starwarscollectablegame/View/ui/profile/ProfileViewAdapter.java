package com.example.starwarscollectablegame.View.ui.profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starwarscollectablegame.Model.Database.PlayerDataDatabse.PlayerData;
import com.example.starwarscollectablegame.R;

import java.util.ArrayList;
import java.util.List;

public class ProfileViewAdapter extends RecyclerView.Adapter<ProfileViewAdapter.SingleViewHolder> {
    private Context context;
    private List<PlayerData> playerData;


    // if checkedPosition = -1, there is no default selection
    // if checkedPosition = 0, 1st item is selected by default
    private int checkedPosition;

    public ProfileViewAdapter(Context context) {
        this.context = context;
        this.playerData = new ArrayList<>();

        final SharedPreferences sharedPref = context.getSharedPreferences(context.getString(R.string.preference_id), Context.MODE_PRIVATE);
        this.checkedPosition = sharedPref.getInt(context.getString(R.string.preferences_player_id), -1);
    }

    public void setPlayerData(List<PlayerData> playerData) {
        this.playerData = new ArrayList<>();
        this.playerData = playerData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SingleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.player_item, viewGroup, false);
        return new SingleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SingleViewHolder singleViewHolder, int position) {
        singleViewHolder.bind(playerData.get(position));
    }

    @Override
    public int getItemCount() {
        return playerData.size();
    }

    class SingleViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;

        SingleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.player_name_textview);
            imageView = itemView.findViewById(R.id.check_sign);
        }

        void bind(final PlayerData employee) {
            if (checkedPosition == -1) {
                imageView.setVisibility(View.GONE);
            } else {
                if (checkedPosition == getAdapterPosition()) {
                    imageView.setVisibility(View.VISIBLE);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
            textView.setText(employee.getPlayer_name());

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
                    editor.putInt(context.getString(R.string.preferences_player_id), getAdapterPosition()).apply();
                }
            });
        }
    }

    public PlayerData getSelected() {
        if (checkedPosition != -1) {
            return playerData.get(checkedPosition);
        }
        return null;
    }
}
