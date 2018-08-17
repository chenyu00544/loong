package cn.itguy.zxingportrait.b;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.util.Log;
import cn.itguy.zxingportrait.a.g;

/* compiled from: BeepManager */
public final class b {
    private static final String a = b.class.getSimpleName();
    private final Activity b;
    private MediaPlayer c = null;
    private boolean d;
    private boolean e;

    /* compiled from: BeepManager */
    static class b_1 implements OnCompletionListener {
        b_1() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            mediaPlayer.seekTo(0);
        }
    }

    public b(Activity activity) {
        this.b = activity;
        a();
    }

    public void a() {
        this.d = a(PreferenceManager.getDefaultSharedPreferences(this.b), this.b);
        if (this.d && this.c == null) {
            this.b.setVolumeControlStream(3);
            this.c = a(this.b);
        }
    }

    public void b() {
        if (this.d && this.c != null) {
            this.c.start();
        }
        if (this.e) {
            ((Vibrator) this.b.getSystemService("vibrator")).vibrate(200);
        }
    }

    private static boolean a(SharedPreferences sharedPreferences, Context context) {
        if (((AudioManager) context.getSystemService("audio")).getRingerMode() != 2) {
            return false;
        }
        return true;
    }

    private static MediaPlayer a(Context context) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(3);
        mediaPlayer.setOnCompletionListener(new b_1());
        AssetFileDescriptor openRawResourceFd = context.getResources().openRawResourceFd(g.baidu_beep);
        try {
            mediaPlayer.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
            openRawResourceFd.close();
            mediaPlayer.setVolume(0.5f, 0.5f);
            mediaPlayer.prepare();
            return mediaPlayer;
        } catch (Throwable e) {
            Log.w(a, e);
            return null;
        }
    }
}
