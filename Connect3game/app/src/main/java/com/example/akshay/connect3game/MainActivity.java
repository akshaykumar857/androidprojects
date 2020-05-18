package com.example.akshay.connect3game;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
//0 ak,1:fifi
    int active=0;
    int[] gamestate={2,2,2,2,2,2,2,2,2};
    int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{1,4,7},{2,5,8},{0,3,6},{0,4,8},{2,4,6}};
    boolean gactive=true;
    public void dropIt(View view)
    {

        ImageView counter=(ImageView) view;

       int tappedtag=Integer.parseInt(counter.getTag().toString());
       if(gamestate[tappedtag]==2 && gactive)
       {
       gamestate[tappedtag]=active;
        counter.setTranslationY(-1500);
        if(active==0) {
            counter.setImageResource(R.drawable.ak);
            active=1;
        }
        else
        {
            active=0;
            counter.setImageResource(R.drawable.fifi);
        }
        counter.animate().translationYBy(1500).rotation(3600).setDuration(300);
        for(int[] winpos1:winpos)
        {
            if(gamestate[winpos1[0]]==gamestate[winpos1[1]] && gamestate[winpos1[1]]==gamestate[winpos1[2]] && gamestate[winpos1[0]]!=2)
            {
                String win="";
                if(active==1)
                {
                    win="Akshay";
                }
                else
                    {
                        win="FIFI";
                    }
                Toast.makeText(this,win,Toast.LENGTH_LONG).show();
                gactive=false;
                Button  btn=(Button)findViewById(R.id.button2);
                TextView tx=(TextView)findViewById(R.id.textView);
                tx.setText(win+" has won!Bayankara maare");
                btn.setVisibility(view.VISIBLE);
                tx.setVisibility(view.VISIBLE);
            }}
        }
    }
    public void newGame(View view)
    {
        Button  btn=(Button)findViewById(R.id.button2);
        TextView tx=(TextView)findViewById(R.id.textView);
        btn.setVisibility(view.INVISIBLE);
        tx.setVisibility(view.INVISIBLE);
        GridLayout gr=(GridLayout)findViewById(R.id.gridLayout);
        for(int i=0;i<gr.getChildCount();i++)
        {
            ImageView counter=(ImageView)gr.getChildAt(i);
            counter.setImageDrawable(null);
        }
        for(int i=0;i<gamestate.length;i++)
        {
            gamestate[i]=2;
        }
        active=0;
        gactive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
