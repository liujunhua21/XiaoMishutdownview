package com.example.liujunhua.xiaomishutdownview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by liujunhua on 17-11-29.
 */

public class XiaomiShutdownView extends View {
    private XiaomiShutdownViewUtils mShutdownViewUtils;
    private Context mComtext;
    private Bitmap mShutdownButton;
    private Bitmap mRebootButton;
    private Bitmap mShutdownButtonPressd;
    private Bitmap mRebootButtonPressd;
    private Bitmap mFlymodeButton;
    private Bitmap mFlymodeButtonPressd;
    private Bitmap mNosoundmodeButton;
    private Bitmap mNosoundmodeButtonPressd;
    private Bitmap mNosoundButton;
    private Bitmap mNosoundpress;
    private Bitmap mShutdownButtonPlus;
    private Bitmap mShutdownButtonPlusPressd;
    private Bitmap mRebootButtonPlus;
    private Bitmap mRebootButtonPlusPressd;
    private  AudioManager mAudioManager;

    private static ShutdownAction mShutdownAction=null;

    public XiaomiShutdownView(Context context) {
        super(context);
        mComtext = context ;
        mAudioManager = (AudioManager) mComtext.getSystemService(Context.AUDIO_SERVICE);
        mShutdownButton = BitmapFactory.decodeResource(mComtext.getResources(), R.drawable.shutdownbutton2);
        mRebootButton= BitmapFactory.decodeResource(mComtext.getResources(), R.drawable.rebootbutton2);
        mShutdownButtonPressd= BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.shutdownpress2);
        mRebootButtonPressd=BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.rebootpress2);
        mFlymodeButton = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.flymodebutton);
        mFlymodeButtonPressd = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.flymodebuttonpress);
        mNosoundmodeButton = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.nosoundmodebutton);
        mNosoundmodeButtonPressd = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.nosoundmodebuttonpress);
        mNosoundButton = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.nosoundbutton2);
        mNosoundpress = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.nosoundpress2);
       // mShutdownButtonPlus = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.shutdownbuttonplus);
       // mShutdownButtonPlusPressd = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.shutdownbuttonpluspress);
       // mRebootButtonPlus = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.rebootbuttonplus);
       // mRebootButtonPlusPressd = BitmapFactory.decodeResource(mComtext.getResources(),R.drawable.rebootbuttonpluspress);

    }

    private static final int TPW_START_FLAG = 2;
    private static final int TPW_RUNING = 1;
    private static final int TPW_CANCEL_DIALOG = 3;
    private static final int TPW_SHUTDOWN  = 4;
    private static final int TPW_REBOOT = 5;

    private Handler mHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            // TODO Auto-generated method stub
            super.handleMessage(msg);
            switch (msg.what){
                case  TPW_RUNING:
                    postInvalidate();
                    break;
                case  TPW_START_FLAG:
                    postInvalidate();
                    break;
                case  TPW_CANCEL_DIALOG:
                    cancel();
                    break;
                case  TPW_SHUTDOWN:
                    shutdown();
                    break;
                case TPW_REBOOT:
                    reboot();
            }
        }
    };

    private void shutdown() {
        if(mShutdownAction!=null)
            mShutdownAction.TPwShutdonw();

    }

    private void cancel() {
        if(mShutdownAction!=null)
            mShutdownAction.TPwCancel();
    }

    private void reboot() {
        if(mShutdownAction!=null)
            mShutdownAction.TPwReboot();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if(mShutdownViewUtils ==null ){
            mShutdownViewUtils = new XiaomiShutdownViewUtils();
            mShutdownViewUtils.setHalf(canvas.getWidth()/2,canvas.getHeight()/2);
            //渐入动画 第一次打开画布时，组件有一个渐入的动画过程
            startTheFirstComeIn();

        }
        //mShutdownViewUtils.updata();
        super.onDraw(canvas);
        //DrawCancelButton(canvas);
        //tpwDrawShutdownButton
        DrawBackground(canvas);
        DrawMapshutdown(canvas);
        DrawMapreboot(canvas);
        DrawMapflymode(canvas);
        DrawMapnosoundmode(canvas);
        //tpwDrawText(canvas);
        //DrawRebootButton(canvas);
    }



    private Rect mSrcRect, mDestRect;
    private void DrawMapreboot(Canvas canvas) {
        int left = (int) (mShutdownViewUtils.getHalfWidth() - mShutdownButton.getWidth()-mShutdownViewUtils.getStartFirst()
                +mShutdownViewUtils.getStartTwo()-mShutdownViewUtils.getStartFour()/8+mShutdownViewUtils.getStartSix()/2);
// 计算上边位置
        int top = (int) (mShutdownViewUtils.getHalfHeight() +mShutdownButton.getHeight() / 6
                +mShutdownViewUtils.getStopFirst()-mShutdownViewUtils.getStartThree()
                +mShutdownViewUtils.getStartFive()/8-mShutdownViewUtils.getStartSeven()/3);

        mSrcRect = new Rect(0, 0, mShutdownButton.getWidth(), mShutdownButton.getHeight());
        mDestRect = new Rect(left, top, left + 140, top + 140);
        Log.d("liujunhua1111","2222222:"+mShutdownButton.getWidth());
        Log.d("liujunhua1111","3333333:"+mShutdownButton.getHeight());
        Paint p = new Paint();
        p.setAntiAlias(true);
        if(pressedreboot){
            canvas.drawBitmap(mRebootButtonPressd, mSrcRect, mDestRect, p);
        }else {
            canvas.drawBitmap(mRebootButton, mSrcRect, mDestRect, p);}
       // if(!mTheFirstComeInFlag){
            canvas.drawText("重新启动", left+25,top+180, mShutdownViewUtils.getRebootTextPaint());
       // }
    }

    private void DrawMapshutdown(Canvas canvas) {
        int left = (int) (mShutdownViewUtils.getHalfWidth() + mRebootButton.getWidth() / 2+mShutdownViewUtils.getStopFirst()
                -mShutdownViewUtils.getStartTwo()-mShutdownViewUtils.getStartFour()/2+mShutdownViewUtils.getStartSix()/8);
// 计算上边位置
        int top = (int) (mShutdownViewUtils.getHalfHeight() +mRebootButton.getHeight() / 6+mShutdownViewUtils.getStopFirst()
                -mShutdownViewUtils.getStartThree() -mShutdownViewUtils.getStartFive()/3+mShutdownViewUtils.getStartSeven()/8);

        mSrcRect = new Rect(0, 0, mRebootButton.getWidth(), mRebootButton.getHeight());
        mDestRect = new Rect(left, top, left + 140, top + 140);
        Paint p = new Paint();
        p.setAntiAlias(true);
        if(pressedFlag){
            canvas.drawBitmap(mShutdownButtonPressd, mSrcRect, mDestRect, p);
        }else {
            canvas.drawBitmap(mShutdownButton, mSrcRect, mDestRect, p);}
        //canvas.drawBitmap(mRebootButton, mSrcRect, mDestRect, mShutdownViewUtils.getRebootButtonPaintPaint());
        //if(!mTheFirstComeInFlag){
            canvas.drawText("关机", left+48,top+180, mShutdownViewUtils.getRebootTextPaint());
       // }
    }

    private void DrawMapflymode(Canvas canvas) {
        int left = (int) (mShutdownViewUtils.getHalfWidth()-mShutdownViewUtils.getHalfWidth()/20 - mShutdownButton.getWidth()
                -mShutdownViewUtils.getStartFirst() +mShutdownViewUtils.getStartTwo()+mShutdownViewUtils.getStartFour()/2
                +mShutdownViewUtils.getStartSix()/2);
// 计算上边位置
        int top = (int) (mShutdownViewUtils.getHalfHeight() - mShutdownButton.getHeight() -mShutdownViewUtils.getStopFirst()
                +mShutdownViewUtils.getStartThree()+mShutdownViewUtils.getStartFive()/3+mShutdownViewUtils.getStartSeven()/3);

        mSrcRect = new Rect(0, 0, mShutdownButton.getWidth(), mShutdownButton.getHeight());
        mDestRect = new Rect(left, top, left + 140, top + 140);
        Paint p = new Paint();
        p.setAntiAlias(true);
        if(pressedflymode){
            canvas.drawBitmap(mFlymodeButtonPressd, mSrcRect, mDestRect, p);
        }else {
            canvas.drawBitmap(mFlymodeButton, mSrcRect, mDestRect, p);}


        //if(!mTheFirstComeInFlag){
            canvas.drawText("飞行模式", left+25,top+180, mShutdownViewUtils.getRebootTextPaint());
       // }
    }

    private void DrawMapnosoundmode(Canvas canvas) {
        int left = (int) (mShutdownViewUtils.getHalfWidth() + mRebootButton.getWidth() / 2+mShutdownViewUtils.getStopFirst()
                -mShutdownViewUtils.getStartTwo()-mShutdownViewUtils.getStartFour()/2-mShutdownViewUtils.getStartSix()/2);
// 计算上边位置
        int top = (int) (mShutdownViewUtils.getHalfHeight() -mShutdownViewUtils.getStopFirst()- mShutdownButton.getHeight()
                +mShutdownViewUtils.getStartThree()+mShutdownViewUtils.getStartFive()/3+mShutdownViewUtils.getStartSeven()/3);

        mSrcRect = new Rect(0, 0, mRebootButton.getWidth(), mRebootButton.getHeight());
        mDestRect = new Rect(left, top, left + 140, top + 140);
        Paint p = new Paint();
        p.setAntiAlias(true);
        if(mAudioManager.getRingerMode()!= AudioManager.RINGER_MODE_NORMAL){
            if(pressednosoundmode){
            canvas.drawBitmap(mNosoundpress, mSrcRect, mDestRect, p);
            }else{
                canvas.drawBitmap(mNosoundButton, mSrcRect, mDestRect, p);
            }
        }else {
            if(pressednosoundmode){
            canvas.drawBitmap(mNosoundmodeButtonPressd, mSrcRect, mDestRect, p);
            }else{
                canvas.drawBitmap(mNosoundmodeButton, mSrcRect, mDestRect, p);
            }
            }
        //if(mAudioManager.getRingerMode()!= AudioManager.RINGER_MODE_NORMAL){
        //    canvas.drawBitmap(mNosoundButton, mSrcRect, mDestRect, p);
       // }

//        if(pressedreboottwo){
//            canvas.drawBitmap(mNosoundpress, mSrcRect, mDestRect, p);
//        }else {
//            canvas.drawBitmap(mNosoundButton, mSrcRect, mDestRect, p);}
        //if(!mTheFirstComeInFlag){
            canvas.drawText("静音模式", left+25,top+180, mShutdownViewUtils.getRebootTextPaint());
        //}
    }

    private void DrawMapshutdownPlus(Canvas canvas) {
        int left = (int) (mShutdownViewUtils.getHalfWidth() + mRebootButton.getWidth() / 2+mShutdownViewUtils.getStopFirst()
                -mShutdownViewUtils.getStartTwo());
// 计算上边位置
        int top = (int) (mShutdownViewUtils.getHalfHeight() -mShutdownViewUtils.getStopFirst()- mShutdownButton.getHeight()
                +mShutdownViewUtils.getStartThree());

        mSrcRect = new Rect(0, 0, mRebootButton.getWidth(), mRebootButton.getHeight());
        mDestRect = new Rect(left, top, left + mShutdownButton.getWidth()/2, top + mShutdownButton.getHeight()/2);
        Paint p = new Paint();
        p.setAntiAlias(true);
        if(pressednosoundmode){
            canvas.drawBitmap(mShutdownButtonPlusPressd, mSrcRect, mDestRect, p);
        }else {
            canvas.drawBitmap(mShutdownButtonPlus, mSrcRect, mDestRect, p);}
        //if(!mTheFirstComeInFlag){
            canvas.drawText("点击关机", left+20,top+160, mShutdownViewUtils.getRebootTextPaint());
       // }

    }

    private void DrawMaprebootPlus(Canvas canvas) {
        int left = (int) (mShutdownViewUtils.getHalfWidth() + mRebootButton.getWidth() / 2+mShutdownViewUtils.getStopFirst()
                -mShutdownViewUtils.getStartTwo());
// 计算上边位置
        int top = (int) (mShutdownViewUtils.getHalfHeight() -mShutdownViewUtils.getStopFirst()- mShutdownButton.getHeight()
                +mShutdownViewUtils.getStartThree());

        mSrcRect = new Rect(0, 0, mRebootButton.getWidth(), mRebootButton.getHeight());
        mDestRect = new Rect(left, top, left + mShutdownButton.getWidth()/2, top + mShutdownButton.getHeight()/2);
        Paint p = new Paint();
        p.setAntiAlias(true);
        if(pressednosoundmode){
            canvas.drawBitmap(mRebootButtonPlusPressd, mSrcRect, mDestRect, p);
        }else {
            canvas.drawBitmap(mRebootButtonPlus, mSrcRect, mDestRect, p);}
        //if(!mTheFirstComeInFlag){
            canvas.drawText("点击重新启动", left+15,top+160, mShutdownViewUtils.getRebootTextPaint());
        //}

    }

    //画背景
    private void DrawBackground(Canvas canvas) {
        //黑色区域
        float startAlpha = 255;
        Paint p=new Paint();
        p.setAlpha((int) startAlpha);
        canvas.drawRect(0,0,canvas.getWidth(),canvas.getHeight(),p);
    }

    public static void setTpwShutdownAction(ShutdownAction tpwShutdownAction){
        XiaomiShutdownView.mShutdownAction = tpwShutdownAction;
    }

    private boolean  pressedreboot = false;
    private boolean  pressedreboottwo = false;
    private boolean  pressedFlag = false ;
    private boolean  pressedflymode = false;
    private boolean  pressednosoundmode = false;
    private boolean  pressedmy = false;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (mAnimationRuning || mTheFirstComeInFlag) return super.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();
                if (x > mShutdownViewUtils.getHalfWidth() + mShutdownButton.getWidth() / 6        //关机
                        && x < mShutdownViewUtils.getHalfWidth() + mShutdownButton.getWidth()
                        && y > mShutdownViewUtils.getHalfHeight() - mShutdownButton.getHeight()/3
                        && y < mShutdownViewUtils.getHalfHeight() + mShutdownButton.getHeight() ) {
                    pressedFlag = true;
                    //pressedreboottwo = true;
                   // mHandler.sendEmptyMessage(TPW_START_FLAG);
                   // mHandler.sendEmptyMessage(TPW_REBOOT);
                } else if (x > mShutdownViewUtils.getHalfWidth() - mRebootButton.getWidth() / 2 - mRebootButton.getWidth() / 3    //重启
                        && x < mShutdownViewUtils.getHalfWidth() - mRebootButton.getWidth() / 6
                        && y > mShutdownViewUtils.getHalfHeight() - mRebootButton.getHeight()/3
                        && y < mShutdownViewUtils.getHalfHeight() + mRebootButton.getHeight() ) {
                    pressedreboot = true;
                   // mHandler.sendEmptyMessage(TPW_RUNING);
                   // mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                } else if (x > mShutdownViewUtils.getHalfWidth() - mFlymodeButton.getWidth() / 2 - mFlymodeButton.getWidth() / 3    //飞行模式
                        && x < mShutdownViewUtils.getHalfWidth() - mFlymodeButton.getWidth() / 6
                        && y > mShutdownViewUtils.getHalfHeight() - mFlymodeButton.getHeight()
                        && y < mShutdownViewUtils.getHalfHeight() - mFlymodeButton.getHeight() /4) {
                    pressedflymode = true;
                   // mHandler.sendEmptyMessage(TPW_START_FLAG);
                   // mHandler.sendEmptyMessage(TPW_REBOOT);
                } else if (x > mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth() / 6              //静音模式
                        && x < mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth()
                        && y > mShutdownViewUtils.getHalfHeight() -  mNosoundmodeButton.getHeight()
                        && y < mShutdownViewUtils.getHalfHeight() - mNosoundmodeButton.getHeight() /4) {
                    pressednosoundmode = true;
                    pressedreboottwo = true;
                   // mHandler.sendEmptyMessage(TPW_RUNING);
                   // mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                }else if (x > mShutdownViewUtils.getHalfWidth() - mNosoundmodeButton.getWidth() / 6   //重启
                        && x < mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth() / 6
                        && y > mShutdownViewUtils.getHalfHeight() -  mNosoundmodeButton.getHeight()/6
                        && y < mShutdownViewUtils.getHalfHeight() +mNosoundmodeButton.getHeight() /6) {
                    pressedreboot =true;
                   // mHandler.sendEmptyMessage(TPW_RUNING);
                    // mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                    //animateEnd();
                }else if (x > mShutdownViewUtils.getHalfWidth() - mNosoundmodeButton.getWidth() / 4   //关机
                        && x < mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth() / 4
                        && y > mShutdownViewUtils.getHalfHeight() -  mNosoundmodeButton.getHeight()/4
                        && y < mShutdownViewUtils.getHalfHeight() +mNosoundmodeButton.getHeight() /4) {
                    pressedFlag = true;
                    //mHandler.sendEmptyMessage(TPW_RUNING);
                    // mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                    //animateEnd();
                }else {
                    pressedmy = true;
                }
            }//animateEnd();
            // pressedFlag = false;
            // break;
            return true;
            case MotionEvent.ACTION_UP:
                float x = event.getX();
                float y = event.getY();
                if (x > mShutdownViewUtils.getHalfWidth() + mShutdownButton.getWidth() / 6          //关机
                        && x < mShutdownViewUtils.getHalfWidth() + mShutdownButton.getWidth()
                        && y > mShutdownViewUtils.getHalfHeight() + mShutdownButton.getHeight()/3
                        && y < mShutdownViewUtils.getHalfHeight() + mShutdownButton.getHeight() ) {
                    pressedFlag = false;
                    //pressedreboottwo = false;
                    mHandler.sendEmptyMessage(TPW_START_FLAG);
                    mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                    //animateEnd();
                    //rebootanimate();
                    shutdownanimate();
                } else if (x > mShutdownViewUtils.getHalfWidth() - mRebootButton.getWidth() / 2 - mRebootButton.getWidth() / 3    //重启
                        && x < mShutdownViewUtils.getHalfWidth() - mRebootButton.getWidth() / 6
                        && y > mShutdownViewUtils.getHalfHeight() - mRebootButton.getHeight()/3
                        && y < mShutdownViewUtils.getHalfHeight() + mRebootButton.getHeight() ) {
                    pressedreboot =false;
                    mHandler.sendEmptyMessage(TPW_RUNING);
                    mHandler.sendEmptyMessage(TPW_REBOOT);
                   // animateEnd();
                    rebootanimate();
                } else if (x > mShutdownViewUtils.getHalfWidth() - mFlymodeButton.getWidth() / 2 - mFlymodeButton.getWidth() / 3    //飞行模式
                        && x < mShutdownViewUtils.getHalfWidth() - mFlymodeButton.getWidth() / 6
                        && y > mShutdownViewUtils.getHalfHeight() - mFlymodeButton.getHeight()
                        && y < mShutdownViewUtils.getHalfHeight() - mFlymodeButton.getHeight() /4) {
                    pressedflymode = false;
                    mHandler.sendEmptyMessage(TPW_START_FLAG);
                    //mHandler.sendEmptyMessage(TPW_REBOOT);
                    //animateEnd();
                } else if (x > mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth() / 6    //静音模式
                        && x < mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth()
                        && y > mShutdownViewUtils.getHalfHeight() -  mNosoundmodeButton.getHeight()
                        && y < mShutdownViewUtils.getHalfHeight() - mNosoundmodeButton.getHeight() /4) {
                    pressedreboottwo = false;
                    pressednosoundmode = false;
                    //mHandler.sendEmptyMessage(TPW_RUNING);
                    //mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                    //animateEnd();
                } else if (x > mShutdownViewUtils.getHalfWidth() - mNosoundmodeButton.getWidth() / 6   //重启
                        && x < mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth() / 6
                        && y > mShutdownViewUtils.getHalfHeight() -  mNosoundmodeButton.getHeight()/6
                        && y < mShutdownViewUtils.getHalfHeight() +mNosoundmodeButton.getHeight() /6) {
                    pressedreboot =false;
                    mHandler.sendEmptyMessage(TPW_RUNING);
                   // mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                    //animateEnd();
                }else if (x > mShutdownViewUtils.getHalfWidth() - mNosoundmodeButton.getWidth() / 4   //关机
                        && x < mShutdownViewUtils.getHalfWidth() + mNosoundmodeButton.getWidth() / 4
                        && y > mShutdownViewUtils.getHalfHeight() -  mNosoundmodeButton.getHeight()/4
                        && y < mShutdownViewUtils.getHalfHeight() +mNosoundmodeButton.getHeight() /4) {
                    pressedFlag = false;
                    mHandler.sendEmptyMessage(TPW_START_FLAG);
                    // mHandler.sendEmptyMessage(TPW_SHUTDOWN);
                    //animateEnd();
                }else {
                //pressedreboot =false;
                //pressedFlag = false;
                //pressedflymode = false;
                //pressednosoundmode = false;
                    pressedmy = false;
                mHandler.sendEmptyMessage(TPW_RUNING);
                animateEnd();
                }

                break;
            case MotionEvent.ACTION_MOVE:
                //animateEnd();
                pressedreboottwo = false;
                mHandler.sendEmptyMessage(TPW_RUNING);
                break;

        }
        return super.onTouchEvent(event);
    }



    //渐入动画 第一次打开画布时，组件有一个渐入的动画过程,不会让人觉得突兀
    private boolean mTheFirstComeInFlag = false ;
    private void startTheFirstComeIn() {
        /*总时常为200毫秒，
        *这段时间event事件不作处理 -- 标志符
        * start区域的动画 -200
        * stop区域的动画 100
        * 文字区域的动画  alpha 0-100
        */
        int time = 250;
        final int times = 50 ;
        final int timemode = time/times;
        final float startmode = mShutdownViewUtils.getStartFirst()/times;
        final float stopmode = mShutdownViewUtils.getStopFirst()/times;

        mTheFirstComeInFlag = true ;

        new Thread(){
            public void run(){
                for(int i = 0 ; i < times ; i++) {
                    try {
                        //改变渐入动画的值
                        mShutdownViewUtils.setStartFirst(mShutdownViewUtils.getStartFirst() - startmode);
                        mShutdownViewUtils.setStopFirst(mShutdownViewUtils.getStopFirst() - stopmode);
                        mHandler.sendEmptyMessage(TPW_START_FLAG);
                        Thread.sleep(timemode);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                mShutdownViewUtils.setStartFirst(0);
                mShutdownViewUtils.setStopFirst(0);
                mHandler.sendEmptyMessage(TPW_START_FLAG);
                mTheFirstComeInFlag = false ;
            }
        }.start();
    }



    private boolean mAnimationRuning = false ;
    private void animateEnd() {
        //圆球back动画
        final float move = 1000;
        final int sum = 50 ;
        final float mode = move/sum;
        // 动画开始，不接受touch event
        mAnimationRuning = true ;
        mShutdownViewUtils.setAssociatedArcPaintAlpha(100);
        mShutdownViewUtils.setAssociatedArcPaintColor(0XFFFFA07A);

        new Thread(){
            public void run(){

                try {
                    Thread.sleep(400);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                for(int i = 0 ; i < sum ; i++){
                    mShutdownViewUtils.setStartTwo(mShutdownViewUtils.getStartTwo()-mode);
                    mShutdownViewUtils.setStartThree(mShutdownViewUtils.getStartThree() - mode);
                    //mShutdownViewUtils.setStopFirst(mShutdownViewUtils.getStopFirst() - mode);
                    mHandler.sendEmptyMessage(TPW_RUNING);
                    try {
                        Thread.sleep(12);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


                mHandler.sendEmptyMessage(TPW_CANCEL_DIALOG);
                //归位
                mShutdownViewUtils.setMovingX(0);
                mHandler.sendEmptyMessage(TPW_RUNING);
                mAnimationRuning = false;

            }
        }.start();
    }

    private boolean mRebootAnimation = false ;
    private void rebootanimate() {
        //圆球back动画
        final float move = 1000;
        final int sum = 50 ;
        final float mode = move/sum;
        // 动画开始，不接受touch event
        mRebootAnimation = true ;
        mShutdownViewUtils.setAssociatedArcPaintAlpha(100);
        mShutdownViewUtils.setAssociatedArcPaintColor(0XFFFFA07A);

        new Thread(){
            public void run(){
                for(int i = 0 ; i < sum ; i++){
                    mShutdownViewUtils.setStartFour(mShutdownViewUtils.getStartFour()-mode);
                    mShutdownViewUtils.setStartFive(mShutdownViewUtils.getStartFive() - mode);
                    //mShutdownViewUtils.setStopFirst(mShutdownViewUtils.getStopFirst() - mode);
                    mHandler.sendEmptyMessage(TPW_RUNING);
                    try {
                        Thread.sleep(12);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

               // mHandler.sendEmptyMessage(TPW_CANCEL_DIALOG);
                //归位
                mShutdownViewUtils.setMovingX(0);
                mHandler.sendEmptyMessage(TPW_RUNING);
                mRebootAnimation = false;

            }
        }.start();
    }

    private boolean mShutdownAnimation = false ;
    private void shutdownanimate() {
        //圆球back动画
        final float move = 1000;
        final int sum = 50 ;
        final float mode = move/sum;
        // 动画开始，不接受touch event
        mRebootAnimation = true ;
        mShutdownViewUtils.setAssociatedArcPaintAlpha(100);
        mShutdownViewUtils.setAssociatedArcPaintColor(0XFFFFA07A);

        new Thread(){
            public void run(){
                for(int i = 0 ; i < sum ; i++){
                    mShutdownViewUtils.setStartSix(mShutdownViewUtils.getStartSix()-mode);
                    mShutdownViewUtils.setmStartSeven(mShutdownViewUtils.getStartSeven() - mode);
                    mHandler.sendEmptyMessage(TPW_RUNING);
                    try {
                        Thread.sleep(12);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                // mHandler.sendEmptyMessage(TPW_CANCEL_DIALOG);
                //归位
                mShutdownViewUtils.setMovingX(0);
                mHandler.sendEmptyMessage(TPW_RUNING);
                mRebootAnimation = false;

            }
        }.start();
    }


    public interface ShutdownAction{
        void TPwShutdonw();
        void TPwCancel();
        void TPwReboot();
    }

}
