package toong.vn.pressableimageview;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class PressableImageView extends AppCompatImageView {
    private static final float DEFAULT_ALPHA_WHEN_PRESS = 0.5f;
    private static final float DEFAULT_ALPHA = 1f;

    public PressableImageView(Context context) {
        super(context);
    }

    public PressableImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PressableImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private void refresh() {
        if (isPressed()) {
            setAlpha(DEFAULT_ALPHA_WHEN_PRESS);
            invalidate();
            return;
        }
        setAlpha(DEFAULT_ALPHA);
        invalidate();
    }

    @Override
    public void setPressed(boolean pressed) {
        super.setPressed(pressed);
        refresh();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            setPressed(true);
        } else if (event.getAction() == MotionEvent.ACTION_UP
                || event.getAction() == MotionEvent.ACTION_CANCEL) {
            setPressed(false);
            float x = event.getX();
            float y = event.getY();
            boolean isInside = (x > 0 && x < getWidth() && y > 0 && y < getHeight());
            if (isInside) {
                performClick();
            }
        }
        return true;
    }
}