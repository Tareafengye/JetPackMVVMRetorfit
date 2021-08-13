package com.zw.jetpackmvvmdemo.view;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.core.content.ContextCompat;

import com.zw.jetpackmvvmdemo.R;

import org.jetbrains.annotations.NotNull;

/**
 * @ClassName: AppViewEditText
 * @Description: 类作用描述
 * @Author: ltt
 * @CreateDate: 2021/8/13 13:43
 * @UpdateUser: 更新者
 * @UpdateDate: 2021/8/13 13:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class AppViewEditText extends AppCompatEditText {
    private Drawable icUser;
    private Drawable icDelete;
    private Context mContext;

    public AppViewEditText(Context context) {
        this(context, null);
    }

    public AppViewEditText(Context context, AttributeSet attrs) {
        this(context, attrs, android.R.attr.editTextStyle);
    }

    public AppViewEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }

    private void init() {
        icUser = ContextCompat.getDrawable(mContext, R.drawable.ic_user);
        icDelete = ContextCompat.getDrawable(mContext, R.drawable.ic_edit_clean);

        addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                setImage();
            }
        });
        setImage();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (icDelete != null && event.getAction() == MotionEvent.ACTION_UP) {
            //得到手指离开EditText时的X Y坐标
            int x = (int) event.getRawX();
            int y = (int) event.getRawY();
            //创建一个长方形
            Rect rect = new Rect();
            //让长方形的宽等于edittext的宽，让长方形的高等于edittext的高
            getGlobalVisibleRect(rect);
            //把长方形缩短至右边30dp，约等于（padding+图标分辨率）
            rect.left = rect.right - dp2px(mContext, 30);
            //如果x和y坐标在长方形当中，说明你点击了右边的xx图片,清空输入框
            if (rect.contains(x, y)) {
                setText("");
            }
        }
        return super.onTouchEvent(event);
    }

    private void setImage() {
        if (length() > 0) {
            setCompoundDrawablesWithIntrinsicBounds(icUser, null, icDelete, null);
        } else {
            setCompoundDrawablesWithIntrinsicBounds(icUser, null, null, null);
        }
    }

    public int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale);
    }
}
