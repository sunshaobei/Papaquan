package com.example.lib_sunshaobei2017.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.example.lib_sunshaobei2017.R;

/**
 * 自定义ViewPager指示器
 * 
 * @author teacher
 */
public class ViewPagerIndicator extends View
{
	private static int CX;
	private static int CY;
	private float radiusSize = 10;
	private static float r;

	private Paint paint;
	private Paint paint2;
	private Paint paint3;
	private float offset;
	private int layout_position;
	private int indicatorCount=3;

	public static final int LEFTPOSITION = 1;
	public static final int RIGHTPOSITION = 2;
	public static final int CENTERPOSITION = 0;

	private int indicatorColor = Color.LTGRAY;
	private int strokeColor = Color.BLACK;
	private int solidColor = Color.RED;

	private int linewidth = 10;
	private int lineheight = 2;
	private int lineColor = Color.RED;
	private Paint paint4;
	private Paint paint5;
	private int lineColorSelect = Color.GREEN;
	private int linePading = 2;

	private int indicatorStyle;
	public static final int Circle = 0;
	public static final int Line = 1;
    public static final int Rect = 2;

	private boolean istofirst = false;
    private Paint paint6;
    private Paint paint7;

    public void setindicatorStyle(int style)
	{
		indicatorStyle = style;
		invalidate();
	}

	public void setRadiusSize(float radiusSize)
	{
		this.radiusSize = radiusSize;
		invalidate();
	}
	
	public void setIndicatorColor(int color, int strokeColor, int solidColor)
	{
		indicatorColor = color;
		this.strokeColor = strokeColor;
		this.solidColor = solidColor;
		initPaint();
		invalidate();
	}

	public void setLineIndicator(int lineColorSelect, int lineColor, int linePading, int linewidth, int lineheight)
	{
		this.lineColorSelect = lineColorSelect;
		this.lineColor = lineColor;
		this.lineheight = lineheight;
		this.linewidth = linewidth;
		this.linePading = linePading;
		initlinepaint();
		invalidate();
	}

	public void setIndicatorCount(int count)
	{
		indicatorCount = count;
		invalidate();
	}

	public void setLaroutPosition(int position)
	{
		layout_position = position;
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		switch (indicatorStyle)
		{
		case Circle:
			// 圆形bunner
//			if (CX == 0)
//			{
				int width = getWidth();
				CY = getHeight() / 2;
				switch (layout_position)
				{
				case 0:// 居中
					CX = (int) (width / 2 - (indicatorCount - 1) * 1.5 * radiusSize);
					break;
				case 1:// 居左
					CX = (int) (2 * radiusSize);
					break;
				case 2:// 居右
					CX = (int) (width - indicatorCount * 3 * radiusSize);
					break;

				default:
					break;
				}
//			}
			for (int i = 0; i < indicatorCount; i++)
			{
				canvas.drawCircle(CX + i * 3 * radiusSize, CY, radiusSize, paint);
				canvas.drawCircle(CX + i * 3 * radiusSize, CY, radiusSize + 1, paint2);
			}
			canvas.drawCircle(CX + offset, CY, r + 1, paint3);

			break;
		case Line:

			// 矩形bunner
//			if (CX == 0)
//			{
				int width1 = getWidth();
				CY = getHeight() / 2;
				switch (layout_position)
				{
				case 0:// 居中
					CX = (int) (width1 / 2 - indicatorCount * linewidth / 2 - (indicatorCount - 1) * linePading / 2);
					break;
				case 1:// 居左
					CX = (int) (linewidth);
					break;
				case 2:// 居右
					CX = (int) (width1 - (indicatorCount * linewidth + (indicatorCount - 1) * linePading) + linewidth);
					break;

				default:
					break;
				}
//			}
			for (int i = 0; i < indicatorCount; i++)
			{
				canvas.drawLine(CX + i * (linewidth + linePading), CY, CX + i * (linewidth + linePading) + linewidth,
						CY, paint4);
				canvas.drawLine(CX + i * (linewidth + linePading)+3, CY, CX + i * (linewidth + linePading) + linewidth-3,
						CY, paint6);

			}
			canvas.drawLine(CX + offset, CY, CX + offset + linewidth1, CY, paint5);
			if (istofirst)
			{
				canvas.drawLine(CX, CY, CX + linewidth * percent, CY, paint5);
			}

			break;

		case Rect:
            int width2 = getWidth();
            CY = getHeight() / 2;
            switch (layout_position)
            {
                case 0:// 居中
                    CX = (int) (width2 / 2 - indicatorCount * linewidth / 2 - (indicatorCount - 1) * linePading / 2);
                    break;
                case 1:// 居左
                    CX = (int) (linewidth);
                    break;
                case 2:// 居右
                    CX = (int) (width2 - (indicatorCount * linewidth + (indicatorCount - 1) * linePading) + linewidth);
                    break;
                default:
                    break;
            }
//			}
            for (int i = 0; i < indicatorCount; i++)
            {
                RectF rectf = new RectF(CX + i * (linewidth + linePading), CY, CX + i * (linewidth + linePading) + linewidth, CY);
				canvas.drawRoundRect(rectf, 4, 4, paint4);
            }
            RectF rectF = new RectF(CX + offset, CY - lineheight / 2, CX + (linewidth + linePading) + linewidth, CY + lineheight / 2);
            canvas.drawRoundRect(rectF,4,4,paint7);
            if (istofirst)
            {
                RectF rectf = new RectF(CX, CY-lineheight/2, CX + linewidth * percent, CY+lineheight/2);
                canvas.drawRoundRect(rectf,4,4,paint7);
            }
            break;
		}

	}

	private float percent;

	public void move(float percent, int position)
	{
		this.percent = percent;
		if (position == indicatorCount - 1)
		{
			offset = position * 3 * radiusSize + percent * radiusSize;
			r = radiusSize * (1 - percent);
		} else
		{
			offset = ((percent + position) * 3 * radiusSize);
			r = radiusSize * (0.5f + Math.abs(0.5f - percent));
		}
		invalidate();
	}

	public void linemove(float percent, int position)
	{
		this.percent = percent;
		if (position == indicatorCount - 1)
		{
			offset = (linewidth + linePading) * position + linewidth * percent;
			linewidth1 = linewidth * (1 - percent);
			istofirst = true;
		} else
		{
			istofirst = false;
			offset = (linewidth + linePading) * (percent + position);
			linewidth1 = linewidth * (0.5f + Math.abs(0.5f - percent));
		}
		invalidate();
	}

	private float linewidth1;

	private void initPaint()
	{
		paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint.setColor(indicatorColor);
		paint.setTextSize(26);
		paint3 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint3.setColor(solidColor);
		paint3.setTextSize(26);
		paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
		paint2.setColor(strokeColor);
		paint2.setStyle(Style.STROKE);// 绘制空心
		paint2.setStrokeWidth(2);
		paint2.setTextSize(26);
	}

	private void initlinepaint()
	{
		paint5 = new Paint();
		paint5.setColor(lineColorSelect);
		paint5.setStyle(Style.STROKE);
		paint5.setStrokeWidth(lineheight);
		paint4 = new Paint();
		paint4.setColor(lineColor);
		paint4.setStyle(Style.STROKE);
		paint4.setStrokeWidth(lineheight);
        paint6 = new Paint();
        paint6.setColor(Color.WHITE);
        paint6.setStyle(Style.STROKE);
        paint6.setStrokeWidth(lineheight-5);

        paint7 = new Paint();
        paint7.setColor(Color.RED);
        paint7.setStyle(Style.FILL);
        paint7.setStrokeWidth(lineheight);
        paint2.setAntiAlias(true);
	}

	public ViewPagerIndicator(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ViewPagerIndicator);
		radiusSize = array.getDimension(R.styleable.ViewPagerIndicator_radiusSize, 10);
		layout_position = array.getInt(R.styleable.ViewPagerIndicator_layout_position, 0);
		indicatorCount = array.getInteger(R.styleable.ViewPagerIndicator_indicatorCount, 5);
		array.recycle();// 释放内存
		initPaint();

		initlinepaint();
	}

}
