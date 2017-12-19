package com.example.liujunhua.xiaomishutdownview;

import android.app.Dialog;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Dialog mTPwDialog;
    private Context mContext = null;
    private  AudioManager mAudioManager;
//    public ShutDownView(Context mContext) {
//        this.mContext = mContext;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
       // mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
        mContext = this;
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTPwDialog= new Dialog(mContext,R.style.Dialog_Fullscreen);
                // mTPwDialog= new Dialog(mContext,R.style.disable_background_dim_dialog);
                XiaomiShutdownView mShutdownView = new XiaomiShutdownView(mContext);


                XiaomiShutdownView.setTpwShutdownAction(new XiaomiShutdownView.ShutdownAction() {
                    @Override
                    public void TPwShutdonw() {

                        //mWindowManagerFuncs.shutdown(false);
                        // Toast.makeText(mComtext, "关机", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void TPwReboot() {
                        //mAudioManager = (AudioManager) mContext.getSystemService(Context.AUDIO_SERVICE);
                        //if(mAudioManager.getRingerMode()!= AudioManager.RINGER_MODE_NORMAL){
                        //    mAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
                        //}else{mAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                        //}
                        //Toast.makeText(mComtext, "重启", Toast.LENGTH_SHORT).show();
                    }
                    @Override
                    public void TPwCancel() {
                        mTPwDialog.dismiss();
                    }
                });
//                mTpwDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG);
                mTPwDialog.setContentView(mShutdownView);
                mTPwDialog.show();
            }
        });

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
