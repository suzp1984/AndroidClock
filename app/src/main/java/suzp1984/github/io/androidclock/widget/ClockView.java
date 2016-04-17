package suzp1984.github.io.androidclock.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import suzp1984.github.io.androidclock.R;

/**
 * Created by jacobsu on 4/16/16.
 */
public class ClockView extends View {

    private final int[][][] DIGITS = {
            {
                    {0,0,1,1,1,0,0},
                    {0,1,1,0,1,1,0},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {0,1,1,0,1,1,0},
                    {0,0,1,1,1,0,0}
            },//0
            {
                    {0,0,0,1,1,0,0},
                    {0,1,1,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {1,1,1,1,1,1,1}
            },//1
            {
                    {0,1,1,1,1,1,0},
                    {1,1,0,0,0,1,1},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,1,1,0},
                    {0,0,0,1,1,0,0},
                    {0,0,1,1,0,0,0},
                    {0,1,1,0,0,0,0},
                    {1,1,0,0,0,0,0},
                    {1,1,0,0,0,1,1},
                    {1,1,1,1,1,1,1}
            },//2
            {
                    {1,1,1,1,1,1,1},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,1,1,0},
                    {0,0,0,1,1,0,0},
                    {0,0,1,1,1,0,0},
                    {0,0,0,0,1,1,0},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {0,1,1,1,1,1,0}
            },//3
            {
                    {0,0,0,0,1,1,0},
                    {0,0,0,1,1,1,0},
                    {0,0,1,1,1,1,0},
                    {0,1,1,0,1,1,0},
                    {1,1,0,0,1,1,0},
                    {1,1,1,1,1,1,1},
                    {0,0,0,0,1,1,0},
                    {0,0,0,0,1,1,0},
                    {0,0,0,0,1,1,0},
                    {0,0,0,1,1,1,1}
            },//4
            {
                    {1,1,1,1,1,1,1},
                    {1,1,0,0,0,0,0},
                    {1,1,0,0,0,0,0},
                    {1,1,1,1,1,1,0},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {0,1,1,1,1,1,0}
            },//5
            {
                    {0,0,0,0,1,1,0},
                    {0,0,1,1,0,0,0},
                    {0,1,1,0,0,0,0},
                    {1,1,0,0,0,0,0},
                    {1,1,0,1,1,1,0},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {0,1,1,1,1,1,0}
            },//6
            {
                    {1,1,1,1,1,1,1},
                    {1,1,0,0,0,1,1},
                    {0,0,0,0,1,1,0},
                    {0,0,0,0,1,1,0},
                    {0,0,0,1,1,0,0},
                    {0,0,0,1,1,0,0},
                    {0,0,1,1,0,0,0},
                    {0,0,1,1,0,0,0},
                    {0,0,1,1,0,0,0},
                    {0,0,1,1,0,0,0}
            },//7
            {
                    {0,1,1,1,1,1,0},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {0,1,1,1,1,1,0},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {0,1,1,1,1,1,0}
            },//8
            {
                    {0,1,1,1,1,1,0},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {1,1,0,0,0,1,1},
                    {0,1,1,1,0,1,1},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,0,1,1},
                    {0,0,0,0,1,1,0},
                    {0,0,0,1,1,0,0},
                    {0,1,1,0,0,0,0}
            },//9
            {
                    {0,0,0,0},
                    {0,0,0,0},
                    {0,1,1,0},
                    {0,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0},
                    {0,1,1,0},
                    {0,1,1,0},
                    {0,0,0,0},
                    {0,0,0,0}
            }//:
    };

    private int COLORS[] = {
            0xffff0000, 0xff00ff00, 0xff0000ff, 0xff1f1f00, 0xff001f1f, 0xffee1010, 0xff10f1f1
    };

    private Paint mBallPaint;
    private Paint mRectPaint;

    private int mBallColor;
    private boolean mShowBorder = false;
    private int mBorderColor;
    private int mBorderWidth;

    private RectF mBounds;

    private int mMarginLeft;
    private int mMarginTop;
    private int mRadius;
    private int mArcPadding;

    private int mCurrentHours;
    private int mCurrentMinutes;
    private int mCurrentSeconds;

    private int mWidth;
    private int mHeight;

    private List<Ball> mBalls;

    public ClockView(Context context) {
        super(context);

        init();
    }

    public ClockView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initAttributes(context, attrs);

        init();
    }

    public ClockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initAttributes(context, attrs);

        init();
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (mShowBorder) {
            canvas.drawRect(mBounds, mRectPaint);
        }

        render(canvas);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        Log.d("ClockView", "w: " + w + "; h: " + h + "; oldW: " + oldW + "; oldH: " + oldH);

        mWidth = w;
        mHeight = h;

        mBounds = new RectF(0, 0, w, h);
        int xpad = getPaddingLeft() + getPaddingRight();
        int ypad = getPaddingTop() + getPaddingBottom();

        mMarginLeft = Math.min(w / 100, 20);
        mMarginTop  = Math.min(h / 100, 20);

        mRadius = (w - mMarginLeft * 2) * 8 / (106 * 9);
        mArcPadding = mRadius / 8;
    }

/*    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int minW = getPaddingLeft() + getPaddingRight() + getSuggestedMinimumWidth();
        int w = resolveSizeAndState(minW, widthMeasureSpec, 1);

        int minH = getPaddingBottom() + getPaddingTop() + getSuggestedMinimumHeight();
        int h = resolveSizeAndState(minH, heightMeasureSpec, 1);

        setMeasuredDimension(w, h);
    }*/

    public int getBallColor() {
        return mBallColor;
    }

    public void setBallColor(int mBallColor) {
        this.mBallColor = mBallColor;
    }

    public boolean isShowBorder() {
        return mShowBorder;
    }

    public void setShowBorder(boolean mShowBorder) {
        this.mShowBorder = mShowBorder;
    }

    public int getBorderColor() {
        return mBorderColor;
    }

    public void setBorderColor(int mBorderColor) {
        this.mBorderColor = mBorderColor;
    }

    public int getBorderWidth() {
        return mBorderWidth;
    }

    public void setBorderWidth(int mBorderWidth) {
        this.mBorderWidth = mBorderWidth;
    }


    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClockView, 0, 0);

        try {
            mBallColor = a.getColor(R.styleable.ClockView_ballColor, 0x201f1f);
            mShowBorder = a.getBoolean(R.styleable.ClockView_showBorder, false);
            mBorderColor = a.getColor(R.styleable.ClockView_borderColor, 0xffff0000);
            mBorderWidth = a.getInt(R.styleable.ClockView_borderWidth, 2);
        } finally {
            a.recycle();
        }
    }

    private void init() {
        mBalls = new Vector<>();

        mBallColor = mBallColor != 0 ? mBallColor : 0x201f1f;
        mBorderColor = mBorderColor != 0 ? mBorderColor : 0xffff0000;
        mBorderWidth = mBorderWidth != 0 ? mBorderWidth : 1;


        mBallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //mBallPaint.setColor(0xff201F1F);
        mBallPaint.setColor(mBallColor);
        mBallPaint.setStyle(Paint.Style.FILL);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(mBorderColor);
        mRectPaint.setStrokeWidth(mBorderWidth);
        mRectPaint.setStyle(Paint.Style.STROKE);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Calendar c = Calendar.getInstance();
                int nextHours = c.get(Calendar.HOUR);
                int nextMinutes = c.get(Calendar.MINUTE);
                int nextSeconds = c.get(Calendar.SECOND);

                if (mCurrentSeconds != nextSeconds) {
                    // add ballls
                    if (mCurrentHours / 10 != nextHours / 10) {
                        addBalls(mMarginLeft, mMarginTop, mCurrentHours / 10);
                    }

                    if (mCurrentHours % 10 != nextHours % 10) {
                        addBalls(mMarginLeft + (mRadius + mArcPadding) * 15, mMarginTop, mCurrentHours % 10);
                    }

                    if (mCurrentMinutes / 10 != nextMinutes / 10) {
                        addBalls(mMarginLeft + (mRadius + mArcPadding) * 39, mMarginTop, mCurrentMinutes / 10);
                    }

                    if (mCurrentMinutes % 10 != nextMinutes % 10) {
                        addBalls(mMarginLeft + (mRadius + mArcPadding) * 54, mMarginTop, mCurrentMinutes % 10);
                    }

                    if (mCurrentSeconds / 10 != nextSeconds / 10) {
                        addBalls(mMarginLeft + (mRadius + mArcPadding) * 78, mMarginTop, mCurrentSeconds / 10);
                    }

                    if (mCurrentSeconds % 10 != nextSeconds % 10) {
                        addBalls(mMarginLeft + (mRadius + mArcPadding) * 93, mMarginTop, mCurrentSeconds % 10);
                    }
                }

                mCurrentHours = nextHours;
                mCurrentMinutes = nextMinutes;
                mCurrentSeconds = nextSeconds;

                updateBalls();

                postInvalidate();

            }
        }, 0, 50);
    }

    private void drawDigit(Canvas canvas, int x, int y, int numb) {
        if (numb > 10) {
            numb = numb % 10;
        }

        for (int i = 0; i < DIGITS[numb].length; i++) {
            for (int j = 0; j < DIGITS[numb][i].length; j++) {
                if (DIGITS[numb][i][j] == 1) {
                    int tx = x + j*2*(mRadius + mArcPadding) + (mRadius + mArcPadding);
                    int ty = y + i*2*(mRadius + mArcPadding) + (mRadius + mArcPadding);
                    canvas.drawArc(tx + mArcPadding, ty + mArcPadding,
                            tx + (mRadius + mArcPadding) * 2 - mArcPadding, ty + (mRadius + mArcPadding) * 2 - mArcPadding,
                            0, 360, true, mBallPaint);
                }
            }
        }
    }

    private void render(Canvas canvas) {
        /*
        Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);
        */

        int hour = mCurrentHours;
        int minutes = mCurrentMinutes;
        int seconds= mCurrentSeconds;

        drawDigit(canvas, mMarginLeft, mMarginTop, hour/10);
        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 15, mMarginTop, hour % 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 30, mMarginTop, 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 39, mMarginTop, minutes/10);
        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 54, mMarginTop, minutes % 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 69, mMarginTop, 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 78, mMarginTop, seconds / 10);
        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 93, mMarginTop, seconds % 10);

        for (int i = 0; i < mBalls.size(); i++) {
            Ball b = mBalls.get(i);

            mBallPaint.setColor(b.color);
            canvas.drawArc(b.x - mRadius - mArcPadding, b.y - mRadius - mArcPadding,
                    b.x + mRadius + mArcPadding, b.y + mRadius + mArcPadding, 0, 360, true, mBallPaint);
        }

        mBallPaint.setColor(mBallColor);
    }

    private void updateBalls() {
        if (mBalls.size() == 0) {
            return;
        }

        for (Ball b : mBalls) {
            b.x += b.vx;
            b.y += b.vy;
            b.vy += b.g;

            if (b.y >= mHeight - mRadius) {
                b.y = mHeight - mRadius;
                b.vy = (int)(-b.vy * 0.75);
            }
        }

        for(int i = 0; i < mBalls.size(); i++) {
            Ball b = mBalls.get(i);
            if ((b.x + mRadius) < 0 || (b.x + mRadius) > mWidth) {
                mBalls.remove(b);
            }
        }
    }

    private void addBalls(int x, int y, int num) {
        if (num > 10) {
            num = num % 10;
        }

        for(int i = 0; i < DIGITS[num].length; i++) {
            for(int j = 0; j < DIGITS[num][i].length; j++) {
                if (DIGITS[num][i][j] == 1) {
                    int bx = x + j*2*(mRadius + mArcPadding) + (mRadius + mArcPadding);
                    int by = y + i*2*(mRadius + mArcPadding) + (mRadius + mArcPadding);
                    float g = (float) (1.5 + Math.random());
                    int vx = (int) (-4 + Math.random() * 9);
                    int vy = -5;
                    int color = COLORS[(int)(Math.random() * COLORS.length)];

                    Ball b = new Ball(bx, by, vx, vy, g, color);

                    mBalls.add(b);
                }
            }
        }
    }

    public class Ball {
        public int x;
        public int y;
        public int vx;
        public int vy;
        public float g;
        public int color;

        public Ball(int x, int y, int vx, int vy, float g, int color) {
            this.x = x;
            this.y = y;
            this.vx = vx;
            this.vy = vy;
            this.g = g;
            this.color = color;
        }
    }
}
