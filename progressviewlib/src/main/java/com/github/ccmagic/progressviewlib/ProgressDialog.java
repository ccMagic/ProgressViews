package com.github.ccmagic.progressviewlib;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

/**
 * 加载进度对话框
 *
 * @author kxmc
 */
public class ProgressDialog extends DialogFragment {
    private ProgressView progressView;

    public static ProgressDialog newInstance() {
        return new ProgressDialog();
    }

    public ProgressDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //不可取消
        setCancelable(true);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.common_transparent_dialog);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.common_dialog_progress, container, false);
        progressView = view.findViewById(R.id.progressView3);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
//        if (getDialog() != null) {
//            //是否点击外部区域可取消
//            getDialog().setCanceledOnTouchOutside(true);
//        }
        if (getDialog() != null) {
            Window window = getDialog().getWindow();
            if (window != null) {
                window.setBackgroundDrawableResource(R.color.transparent);
            }
        }
    }

    @Override
    public void dismiss() {
        if (progressView != null) {
            progressView.cancellation();
        }
        super.dismiss();
    }

    @Override
    public void onDestroy() {
        if (progressView != null) {
            progressView.cancellation();
        }
        super.onDestroy();
    }
}
