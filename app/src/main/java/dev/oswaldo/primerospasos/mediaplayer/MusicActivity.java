package dev.oswaldo.primerospasos.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dev.oswaldo.primerospasos.R;

public class MusicActivity extends AppCompatActivity {

    @BindView(R.id.btnPlay) Button mBtnPlay;
    @BindView(R.id.btnPause) Button mBtnPause;
    @BindView(R.id.btnStop) Button mBtnStop;

    private MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnPlay) public void play(View view){
        if(player == null){
            player = MediaPlayer.create(this, R.raw.song);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    stopPlayer();
                }
            });
        }
        player.start();
    }

    @OnClick(R.id.btnPause) public void pause(View view) {
        if(player != null){
            player.pause();
        }
    }

    @OnClick(R.id.btnStop) public void stop(View view) {
        stopPlayer();
    }




    private void stopPlayer() {
        if(player != null){
            player.release();
            player= null;
            Toast.makeText(this, "MediaPlayer released", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        stopPlayer();
    }
}
