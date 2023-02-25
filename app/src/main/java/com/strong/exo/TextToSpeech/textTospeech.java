package com.strong.exo.TextToSpeech;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.strong.exo.R;
import com.strong.exo.VoiceCommand.voiceRecognise;

import java.util.ArrayList;
import java.util.Locale;

public class textTospeech extends AppCompatActivity {
EditText editText;
TextToSpeech textToSpeech;
Button button;
ArrayList<String>result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_tospeech);
        editText=findViewById(R.id.editText);
        button=findViewById(R.id.speekbtn);
        textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
           if (status==TextToSpeech.SUCCESS)
           {
               int result=textToSpeech.setLanguage(Locale.getDefault());
               if (result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED)
               {
                   Toast.makeText(textTospeech.this, "this is not supported", Toast.LENGTH_SHORT).show();
               }
               else
               {
                   button.setEnabled(true);
                   textToSpeech.setPitch(0.6f);
                   textToSpeech.setSpeechRate(1.0f);
               }
           }
            }

        });





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
                    Toast.makeText(textTospeech.this, "your device don't support ", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    public void speek(String data)
    {
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
        {
            textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null,null);
        }
        else
        {
            textToSpeech.speak(data,TextToSpeech.QUEUE_FLUSH,null);
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100&&resultCode==RESULT_OK)
        {

          result=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            Toast.makeText(this, ""+result.get(0), Toast.LENGTH_SHORT).show();
            speek(result.get(0));
        }
    }
}