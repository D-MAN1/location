package com.example.mygame;

import java.nio.ByteBuffer;

import android.util.Log;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.utils.ScreenUtils;

   public class ScreenshotFactory {
        private static int counter = 1;
        final static String LOG="myLogs";
        static Pixmap pixmap;
        public static void saveScreenshot(){
        	
            try{
                FileHandle fh;
                Log.d(LOG, "fh=");
                
                    fh = new FileHandle(Gdx.files.getExternalStoragePath() + "screenshot.png");
                 //   Log.d(LOG, "fh="+fh);
                
                pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
                
                PixmapIO.writePNG(fh, pixmap);
                Log.d(LOG, "fh="+fh);
                pixmap.dispose();
            }catch (Exception e){           
            }
        }
        private static Pixmap getScreenshot(int x, int y, int w, int h, boolean yDown){
            final Pixmap pixmap = ScreenUtils.getFrameBufferPixmap(x, y, w, h);
            if (yDown) {
                ByteBuffer pixels = pixmap.getPixels();
                int numBytes = w * h * 4;
                byte[] lines = new byte[numBytes];
                int numBytesPerLine = w * 4;
                for (int i = 0; i < h; i++) {
                    pixels.position((h - i - 1) * numBytesPerLine);
                    pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
                }
                pixels.clear();
                pixels.put(lines);
            }
            return pixmap;
        }
    }
