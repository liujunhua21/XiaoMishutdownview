package com.example.liujunhua.xiaomishutdownview;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by liujunhua on 17-11-10.
 */

public class XiaomiShutdownViewUtils {

    private static  float TOTALDEGREE = 150f ;
    private static String TAG = "TpwShutdownViewUtils";
    private int halfWidth ;
    private int halfHeight ;
    private int startHeiget ;
    private int stopHeight ;
    private int startWidth ;
    private int stopWidth ;

    private int totalDistance;  //总共可移动距离
    private int R ; //圆圈半径
    private Paint mRebootButtonPaintPaint;  //RebootButtonPaint
    private Paint mStartPaint;  //shutdownPaint
    private Paint mAssociatedArcPaint; //圆弧Paint
    private Paint mRebootTextPaint; //取消 Paint
    private Paint mBackgroundPaintPaint;


    private float movingX = 0f;  //移动的距离 从start开始
    private float movingY = 0f;

    private float mDegree = 0f;  //百分比

    private float mStartFirst ;
    private float mStopFirst ;

    private float mStartTwo;

    private float mStartThree;

    private float mStartFour;

    private float mStartFive;

    private float mStartSix;

    private float mStartSeven;

    public float getStartSeven() {
        return mStartSeven;
    }

    public float getStartSix() {
        return mStartSix;
    }

    public float getStartFive() {
        return mStartFive;
    }


    public float getStartFour() {
        return mStartFour;
    }


    public float getStartThree() {
        return mStartThree;
    }

    public float getStartTwo() {
        return mStartTwo;
    }

    public void setStartTwo(float mStartTwo) {
        this.mStartTwo =mStartTwo;
    }

    public void setStartThree(float mStartThree) {
        this.mStartThree =mStartThree;
    }

    public void setStartFour(float mStartFour) {
        this.mStartFour =mStartFour;
    }

    public void setStartFive(float mStartFive) {
        this.mStartFive =mStartFive;
    }

    public void setmStartSeven(float mStartSeven) {
        this.mStartSeven =mStartSeven;
    }

    public void setStartSix(float mStartSix) {
        this.mStartSix =mStartSix;
    }


    public float getStartFirst() {
        return mStartFirst;
    }

    public void setStartFirst(float mStartFirst) {
        this.mStartFirst = mStartFirst;
    }

    public float getStopFirst() {
        return mStopFirst;
    }

    public void setStopFirst(float mStopFirst) {
        this.mStopFirst = mStopFirst;
    }

    private boolean mStartDown = false;
    private boolean mStopDown = false;

    public boolean getStartDown() {
        return mStartDown;
    }
    public float getDegree() {
        return mDegree;
    }

    public void setStartDown(boolean mStartDown) {
        this.mStartDown = mStartDown;
    }

    public boolean getStopDown() {
        return mStopDown;
    }

    public void setStopDown(boolean mStopDown) {
        this.mStopDown = mStopDown;
    }

    XiaomiShutdownViewUtils(){
        init();
    }

    private void init() { //初始化数据
        mRebootButtonPaintPaint = new Paint();
        mRebootButtonPaintPaint.setAntiAlias(true);
        mRebootButtonPaintPaint.setStrokeWidth(1);
        //mStopPaint.setColor(Color.WHITE);

        mStartPaint = new Paint();
        mStartPaint.setAntiAlias(true);
        mStartPaint.setStrokeWidth(1);
        //mStartPaint.setColor(Color.WHITE);

        mAssociatedArcPaint = new Paint();
        mAssociatedArcPaint.setAntiAlias(true);
        mAssociatedArcPaint.setStrokeWidth(10);
        mAssociatedArcPaint.setStyle(Paint.Style.STROKE);
        mAssociatedArcPaint.setColor(0XFFFFA07A);

        mRebootTextPaint = new Paint();
        mRebootTextPaint.setStrokeWidth(1);
        mRebootTextPaint.setAntiAlias(true);
        mRebootTextPaint.setTextAlign(Paint.Align.LEFT);//文字位置
        mRebootTextPaint.setColor(Color.WHITE);
        mRebootTextPaint.setAlpha(255);

    }

    public int getR() {
        return R;
    }


    public void setMovingX(float movingX) {
        if(movingX<=0) movingX = 0;
        else if(movingX>=totalDistance) movingX = totalDistance;
        this.movingX = movingX;
    }

    public void setMovingY(float movingY) {
        if(movingY<=0) movingY =0;
        else if(movingY>=totalDistance) movingY =totalDistance;
        this.movingY = movingY;
    }
    public float getMovingX() {
        return movingX;
    }

    public int getHalfWidth() {
        return halfWidth;
    }

    public void setHalfWidth(int halfWidth) {
        this.halfWidth = halfWidth;
    }

    public int getHalfHeight() {
        return halfHeight;
    }

    public void setHalfHeight(int halfHeight) {
        this.halfHeight = halfHeight;
    }
    public  void setHalf(int halfWidth,int halfHeight){
        this.halfWidth = halfWidth;
        this.halfHeight = halfHeight;
        startWidth = halfWidth/2;
        stopWidth = halfWidth+halfWidth/2+halfWidth/4;
        totalDistance = stopWidth - startWidth;

        //设置R
        R = this.halfWidth/5;

        Log.d("zhangyu8181","R:"+R);
        //设置键入动画初始距离
        mStartFirst = R/2 ;
        mStopFirst = R/2;
        mStartTwo = R/2;
        mStartThree = R/20;
        mStartFour = R/4;
        mStartFive = R/20;
        mStartSix = R/6;
        mStartSeven = R/20;
        mRebootTextPaint.setTextSize(halfWidth/16);
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public int getStartWidth() {
        return startWidth;
    }

    public int getStopHeight() {
        return stopHeight;
    }
    //获取圆弧的Rectf
    public RectF getAssociatedArcRectF(){
        return  new RectF(halfWidth-R*2,startHeiget-R*2+movingX-mStartFirst,halfWidth+R*2,startHeiget+R*2+movingX-mStartFirst);
    }

    public RectF getShutdownButtonRectf(int imageW , int imageH){
        int mode = 2 ;
        return  new RectF(halfWidth-imageW/mode,startHeiget+movingX-mStartFirst-imageH/mode,
                halfWidth+imageW/mode,startHeiget+movingX-mStartFirst + imageH/mode);
    }
    public RectF getRebootButtonRectf(int imageW , int imageH){
        int mode = 2 ;
        return  new RectF(halfWidth-imageW/mode,stopHeight+mStopFirst-imageH/mode
                ,halfWidth+imageW/mode,stopHeight+mStopFirst+imageH/mode);
    }

    public float getAssociatedArcStartDegree(){
        //总长度stopHeight - startHeiget

        //换算一下
        //0.8 - 1 1.25
        float degree = mDegree*1.25f;
        if(mDegree>=0.8) degree = 0.8f*1.25f;

        return  195+degree*TOTALDEGREE;
    }
    public float getAssociatedArcTotalDegree(){
        //总长度stopHeight - startHeiget
        float degree = mDegree*1.25f;
        if(mDegree>=0.8) degree = 0.8f*1.25f;
        return  (1-degree)*TOTALDEGREE;
    }

    public Paint getRebootButtonPaintPaint(){
        return  mRebootButtonPaintPaint;
    }
    public Paint getRebootTextPaint(){
        return  mRebootTextPaint;
    }
    public void setRebootButtonPaintPaintAlpha(int Alpha){
        if(mRebootButtonPaintPaint.getAlpha() != Alpha)
            mRebootButtonPaintPaint.setAlpha(Alpha);
    }

    public void setBackgroundPaintPaintAlpha(int Alpha){
        if(mBackgroundPaintPaint.getAlpha() != Alpha)
         mBackgroundPaintPaint.setAlpha(Alpha);
    }

    public Paint getAssociatedArcPaint() {
        return mAssociatedArcPaint;
    }

    public void setAssociatedArcPaintColor(int color){
        mAssociatedArcPaint.setColor(color);
    }
    public void setAssociatedArcPaintAlpha(int alpha){
        mAssociatedArcPaint.setAlpha(alpha);
    }

    public boolean isShutDownBUttonRect(MotionEvent event){
        RectF mDownRectF = new RectF(halfWidth-R,startWidth-R,halfWidth+R,startWidth+R);
        return mDownRectF.contains(event.getX(),event.getY());
    }
    public boolean isRebootButtonRect(MotionEvent event , int upordown){
        int down = 1;
        int up = 2;
        if(upordown == down){
            RectF mDownRectF = new RectF(halfWidth-R,stopWidth-R,halfWidth+R,stopWidth+R);
            return mDownRectF.contains(event.getX(),event.getY());
        }
        else {
            RectF mUpRectF = new RectF(halfWidth-3*R,stopWidth-3*R,halfWidth+3*R,stopWidth+3*R);
            return mUpRectF.contains(event.getX(),event.getY());
        }

    }

//    public boolean isisRebootButtonRect(MotionEvent event,int upordown){
//        int down =1;
//        int up = 2;
//        if(upordown == down){
//            Rect mDownRect = new Rect(halfWidth-R,stopWidth-R,halfWidth+R,stopWidth+R);
//            return mDownRect.contains(event.getX(),event.getY());
//        }
//        else {
//            Rect mUpRect = new Rect(halfWidth-3*R,stopWidth-3*R,halfWidth+3*R,stopWidth+3*R);
//            return mUpRect.contains(event.getX(),event.getY());
//        }
//    }

    //在使用数据之前,更新相关数据
    public void updata(){
        if(mStartDown){//start updata
            mDegree = movingX / totalDistance;
            mDegree = movingY / totalDistance;
            //Alpha 0-255
            mRebootButtonPaintPaint.setAlpha((int)(255*(1-(mDegree>=0.5?1:mDegree*2)))); //更新stopPaint的Alpha值
            mRebootTextPaint.setAlpha((int)(100*(1-(mDegree>=0.5?1:mDegree*2)))); //更新CancelTextPaint的Alpha值
        }
    }

}


