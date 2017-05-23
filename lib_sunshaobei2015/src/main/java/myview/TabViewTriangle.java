package myview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.lib_sunshaobei2017.R;

import java.util.ArrayList;

public class TabViewTriangle extends View {

	@SuppressLint("Recycle")
	public TabViewTriangle(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TabTrangle);
		textSize = (int) typedArray.getDimension(R.styleable.TabTrangle_textSize, 26);
		SelColor = typedArray.getInteger(R.styleable.TabTrangle_selectcolor, Color.RED);
		initpaint();
	}
	private int indicatorHeight;
	private int textSize;
	private ArrayList<String> list = new ArrayList<String>();
	
	private int defColor = Color.BLACK;
	private int SelColor = Color.parseColor("#00ff00");
	
	private int count;
	private Paint paint;
	private Paint paint2;

	private int selectPosition;

	private int width;

	private Paint paint3;
	
	public int getSelectPosition()
	{
		return selectPosition;
	}

	public void setSelectPosition(int selectPosition)
	{
		this.selectPosition = selectPosition;
		invalidate();
	}

	public void setTabCustomsVew(ArrayList<String> s,int indicatorHeight,int textSize,int defColor,int SelColor)
	{
		this.indicatorHeight = indicatorHeight;
		this.textSize = textSize;
		this.defColor = defColor;
		this.SelColor = SelColor;
		list = s;
		count = s.size();
		initpaint();
		invalidate();
	}
	
	private void initpaint() {
		paint = new Paint();
		paint2 = new Paint();
		paint.setStyle(Style.FILL);
		paint.setTextSize(textSize);
		paint.setTextAlign(Align.CENTER);
		paint.setColor(defColor);
		
		paint2.setStyle(Style.FILL);
		paint2.setTextSize(textSize);
		paint2.setTextAlign(Align.CENTER);
		paint2.setColor(SelColor);
		
		paint3 = new Paint();
		paint3.setStyle(Style.FILL);
		paint3.setColor(defColor);
		
		paint4 = new Paint();
		paint4.setColor(Color.TRANSPARENT);
		paint4.setTextSize(textSize);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		float x = event.getX();
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			selectPosition = (int) (x/(width/count));
			if(listener != null)
			{
				listener.onSelect(selectPosition);
			}
			invalidate();
			break;
		case MotionEvent.ACTION_MOVE:
			selectPosition = (int) (x/(width/count));
			if(listener != null)
			{
				listener.onSelect(selectPosition);
			}
			invalidate();
			break;
			default:
			break;
		}
		return true;
	}

	public interface OnSelectListener
	{
		void onSelect(int Selectposition);
	}
	private OnSelectListener listener;
	private Paint paint4;
	public void setOnSelectListener(OnSelectListener l)
	{
		this.listener = l;
	}
	
	
	
	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		int height = canvas.getHeight()/2;
		width = canvas.getWidth();
		
		Rect rect = new Rect( 0, 0, width, 2*height);
		canvas.drawRect(rect, paint4);
		FontMetrics fontMetrics = paint4.getFontMetrics();
		int baseline = (int) ((rect.bottom+rect.top-fontMetrics.bottom-fontMetrics.top)/2);
		
			
		if(list.size() != 0)
		{
			for (int i = 0; i < count; i++) {
				canvas.drawText(list.get(i),width/count/2+i*width/count,baseline, paint);
				Path path = new Path();
			}
			canvas.drawText(list.get(selectPosition), selectPosition*width/count+width/count/2,baseline, paint2);
		}
	}
}
