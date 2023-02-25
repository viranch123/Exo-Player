package com.strong.exo.VoiceCommand;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.strong.exo.R;

import java.util.ArrayList;
import java.util.Locale;

public class voiceRecognise extends AppCompatActivity {
    Button button,speek;
    TextView textView;
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognise);
        button=findViewById(R.id.speek);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech=new TextToSpeech(voiceRecognise.this, new TextToSpeech.OnInitListener() {
                    @Override
                    public void onInit(int status) {
                        if (status!=TextToSpeech.ERROR)
                        {
                            textToSpeech.setLanguage(Locale.UK);
                        }
                        else
                        {
                            Toast.makeText(voiceRecognise.this, ""+TextToSpeech.ERROR, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                textToSpeech.speak("hello",TextToSpeech.QUEUE_FLUSH,null);

            }
        });


        button=findViewById(R.id.voice);
        textView=findViewById(R.id.result);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

                if (intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivityForResult(intent,100);
                }
                else
                {
                    Toast.makeText(voiceRecognise.this, "your device don't support ", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==RESULT_OK)
        {
            ArrayList<String>result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Toast.makeText(this, ""+result.get(0), Toast.LENGTH_SHORT).show();
            textView.setText(""+result.get(0));
        }
    }
}