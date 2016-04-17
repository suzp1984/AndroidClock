package suzp1984.github.io.androidclock.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

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

    private Paint mBallPaint;
    private Paint mRectPaint;
    private int mBallColor;

    private RectF mBounds;

    private int mMarginLeft;
    private int mMarginTop;
    private int mRadius;
    private int mArcPadding;


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
        
        canvas.drawRect(mBounds, mRectPaint);

        render(canvas);
    }

    @Override
    public void onSizeChanged(int w, int h, int oldW, int oldH) {
        Log.d("ClockView", "w: " + w + "; h: " + h + "; oldW: " + oldW + "; oldH: " + oldH);

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

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.ClockView, 0, 0);

        try {
            mBallColor = a.getColor(R.styleable.ClockView_ballColor, 0x201f1f);
        } finally {
            a.recycle();
        }
    }

    private void init() {
        mBallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        // mBallPaint.setColor(0xff201F1F);
        mBallColor = mBallColor != 0 ? mBallColor : 0x201f1f;

        mBallPaint.setColor(mBallColor);
        mBallPaint.setStyle(Paint.Style.FILL);

        mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRectPaint.setColor(0xffff0000);
        mRectPaint.setStrokeWidth(1);
        mRectPaint.setStyle(Paint.Style.STROKE);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                postInvalidate();
            }
        }, 0, 500);
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
        Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR);
        int minutes = c.get(Calendar.MINUTE);
        int seconds = c.get(Calendar.SECOND);;

        drawDigit(canvas, mMarginLeft, mMarginTop, hour/10);
        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 15, mMarginTop, hour % 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 30, mMarginTop, 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 39, mMarginTop, minutes/10);
        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 54, mMarginTop, minutes % 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 69, mMarginTop, 10);

        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 78, mMarginTop, seconds / 10);
        drawDigit(canvas, mMarginLeft + (mRadius + mArcPadding) * 93, mMarginTop, seconds % 10);

    }

}
