package com.example.ourgame;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.ourgame.Games.HangmanGame.HangmanActivity;
import com.example.ourgame.Games.PictureGame.PictureInstructions;
import com.example.ourgame.Games.ReactionGame.ReactionGameActivity;
import com.example.ourgame.Games.RunningGame.EndlessRunnerActivity;
import com.example.ourgame.LanguageSetters.LanguageTextSetter;
import com.example.ourgame.LanguageSetters.TextSetter;
import com.example.ourgame.Statistics.DataWriter;
import com.example.ourgame.Games.TileGame.TileGameInstructions;
import com.example.ourgame.ThemeSetters.Theme;
import com.example.ourgame.ThemeSetters.ThemeBuilder;

/**
 * An activity class for the main homepage the user is brought to once they register or sign in
 */
public class MainActivity extends AppCompatActivity {

    public String user;
    private DataWriter data;
    private ScreenLoader screenLoader;

    private TextView welcomeText;
    private Button playButton;
    private Button statisticsButton;
    private Button leaderBoardButton;
    private Button settingsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConstraintLayout constraintLayout = findViewById(R.id.mainactivityLayout);
//        Drawable drawable = getDrawable(R.drawable.autumn_option_temp);
//        constraintLayout.setBackground(drawable);

        screenLoader = new ScreenLoader(this);

        welcomeText = findViewById(R.id.welcomeText);
        playButton = findViewById(R.id.playButton);
        statisticsButton = findViewById(R.id.statisticsButton);
        leaderBoardButton = findViewById(R.id.leaderBoardButton);
        settingsButton = findViewById(R.id.settingsButton);

        data = new DataWriter(this);
        user = data.getUser();
        setLanguage();

        ThemeBuilder themeBuilder = new ThemeBuilder(data.getThemeData(user));
        Theme theme = themeBuilder.getTheme();
        constraintLayout.setBackgroundResource(theme.mainActivityLayout());
    }

    private void setLanguage() {
        String language = data.getLanguage(user);
        LanguageTextSetter text = new LanguageTextSetter(language);
        TextSetter textsetter = text.getTextsetter();

        String welcomeMessage;
        if (language.equals("english")) {
            welcomeMessage = "Welcome " + user + "!";
        } else if (language.equals("french")) {
            welcomeMessage = "Bienvenu " + user + "!";
        } else { // spanish
            welcomeMessage = "Welcome " + user + "!";
        }
        playButton.setText(textsetter.getMainPlayButton());
        statisticsButton.setText(textsetter.statistics());
        leaderBoardButton.setText(textsetter.getMainLeaderBoard());
        settingsButton.setText(textsetter.getMainSettings());
        welcomeText.setText(welcomeMessage);
    }


    /**
     * Method to send the user to play the next game
     *
     * @param view the button object that was tapped
     */
    public void playGame(View view) {
        screenLoader.loadNextGame();
    }

    public void checkStats(View view) {
        screenLoader.loadStatistics();
    }

    public void checkLeaderBoard(View view) {
        screenLoader.loadLeaderBoard();
    }

    public void openSettings(View view) {
        screenLoader.loadSettings();
    }

    /**
     * Sends the user to the Reaction Time Game -- TESTING ONLY
     */
    public void playReactionGame(View view){
        Intent intent = new Intent(this, ReactionGameActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Tile Game
     */
    private void playTileGame(){
        Intent intent = new Intent(this, TileGameInstructions.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Running Game -- TESTING ONLY
     */
    public void playRunningGame(View view){
        Intent intent = new Intent(this, EndlessRunnerActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Picture Guessing Game
     */
    private void playPictureGame(){
        Intent intent = new Intent(this, PictureInstructions.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Hangman Game -- TESTING ONLY
     */
    public void playHangmanGame(View view) {
        Intent intent = new Intent(this, HangmanActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Tile Game -- TESTING ONLY
     */
    public void playTileGame(View view) {
        Intent intent = new Intent(this, TileGameInstructions.class);
        startActivity(intent);
        finish();
    }

    /**
     * Sends the user to the Picture Game -- TESTING ONLY
     */
    public void playPictureGame(View view) {
        Intent intent = new Intent(this, PictureInstructions.class);
        startActivity(intent);
        finish();
    }

}
