package com.xdx.textviewdemo.utils;

/**
 * <p>类名：com.baojun.baselib.utils<p>
 * <p>文件描述：<p>
 * <p>作者：h.yw<p>
 * <p>创建时间：2018/5/24 15:46<p>
 * <p>更改时间：2018/5/24 15:46<p>
 * <p>版本号：1<p>
 */

public class TouchUtils {
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

    private float downX = 0;
    private float downY = 0;

    private boolean isTouch = true;

    public TouchUtils(){}

    public TouchUtils setDownXY(float x , float y){
        this.downX = x;
        this.downY = y;
        return this;
    }

    /**
     * 判断手势方向，只判断一次跳出
     * 手指抬起时调用 actionUp() 重新判断手势方向
     *
     * @param moveX
     * @param moveY
     * @return state
     */
    public int getDirection(float moveX,float moveY){
//        if(isTouch){
            if((downY - moveY ) > Math.abs(downX - moveX)){
                return UP;
            }
            if((moveY - downY) > Math.abs(downX - moveX)){
                return DOWN;
            }
            if((downX - moveX) > Math.abs(downY - moveY)){
                return LEFT;
            }
            if((moveX - downX) > Math.abs(downY - moveY)){
                return RIGHT;
            }
//            isTouch = false;
//        }
        return 0;
    }

    /**
     * 手指抬起时调用
     */
    public void actionUp(){
        isTouch = true;
    }
}
