package com.example.starwarscollectablegame.View.ui.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.starwarscollectablegame.R;

import java.io.InputStream;
import java.util.Objects;

public class AddEditPlayerActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
               "om.example.starwarscollectablegame.View.ui.profile.EXTRA_NAME";
    public static final String EXTRA_AVATARID =
               "om.example.starwarscollectablegame.View.ui.profile.EXTRA_AVATARID";

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



        this.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        AvatarViewAdapter adapter = new AvatarViewAdapter(getApplicationContext(), selectedAvatar);
        this.recyclerView.setAdapter(adapter);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_NAME)) {
            setTitle("Edit Player");
            name.setText(intent.getStringExtra(EXTRA_NAME));
            name.setEnabled(false);
            name.setInputType(InputType.TYPE_NULL);
            adapter.setSelected(intent.getIntExtra(EXTRA_AVATARID, R.raw.icon_c3p0));
        } else {
            setTitle("Add Player");
        }

        adapter.notifyDataSetChanged();
    }

    private void savePlayer() {
        String name = this.name.getText().toString();
        int avatarId = ((AvatarViewAdapter) this.recyclerView.getAdapter()).getSelected();


        if (name.trim().isEmpty()) {
            Toast.makeText(this, "You need to add a Name", Toast.LENGTH_SHORT).show();
        } else {
            Intent data = new Intent();
            data.putExtra(EXTRA_NAME, name);
            data.putExtra(EXTRA_AVATARID, avatarId);

            setResult(RESULT_OK, data);
            finish();
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
