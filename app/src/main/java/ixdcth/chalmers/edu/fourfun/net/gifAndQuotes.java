package ixdcth.chalmers.edu.fourfun.net;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Movie;
import android.view.View;

import java.io.InputStream;

import ixdcth.chalmers.edu.fourfun.R;


/**
 * Created by adam on 29/12/2015.
 */
public class gifAndQuotes extends View {

        Movie movie,movie1;
        InputStream is=null,is1=null;
        long moviestart;
        public gifAndQuotes(Context context) {
            super(context);


            //Provide your own gif animation file

            is=context.getResources().openRawResource(+ R.drawable.loading);
            movie=Movie.decodeStream(is);

        }

        @Override
        protected void onDraw(Canvas canvas) {

            canvas.drawColor(Color.WHITE);
            super.onDraw(canvas);
            long now=android.os.SystemClock.uptimeMillis();
            System.out.println("now="+now);
            if (moviestart == 0) { // first time
                moviestart = now;

            }
            System.out.println("\tmoviestart="+moviestart);
            int relTime = (int)((now - moviestart) % movie.duration()) ;
            System.out.println("time="+relTime+"\treltime="+movie.duration());
            movie.setTime(relTime);
            movie.draw(canvas,this.getWidth()/2-20,this.getHeight()/2-40);
            this.invalidate();
        }
    }