package com.example.starwarscollectablegame.View.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starwarscollectablegame.R;

import java.io.InputStream;
import java.util.Objects;

public class AddPlayerActivity extends AppCompatActivity {



    private TextView name;
    private ImageView selectedAvatar;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences(getString(R.string.preference_id), Context.MODE_PRIVATE);
        setTheme(sharedPref.getBoolean(getString(R.string.preferences_theme_use_sith), false) ? R.style.AppThemeSith : R.style.AppThemeJedi );

        setContentView(R.layout.activity_add_player);
        this.name = findViewById(R.id.player_name_input);
        this.recyclerView = findViewById(R.id.avatar_img_recycler);
        this.selectedAvatar = findViewById(R.id.selected_avatar_img);


        //getActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Player");

        this.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        AvatarViewAdapter adapter = new AvatarViewAdapter(getApplicationContext(), selectedAvatar);
        this.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void savePlayer() {
        if (name.getText().toString().trim().isEmpty()) {
            Toast.makeText(this, "You need to add a Name", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, this.name.getText().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_player_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_player:
                savePlayer();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
