package com.foxleezh.middleware.bindingadapter.tab;

import android.widget.LinearLayout;
import android.widget.TextView;

import com.foxleezh.middleware.R;

/**
 * Created by kelin on 16-3-24.
 */
public final class ViewBindingAdapter {

    @android.databinding.BindingAdapter({"tabSelect"})
    public static void loadurl(LinearLayout root, boolean selected) {
        root.getChildAt(0).setSelected(selected);
        ((TextView)root.getChildAt(1)).setTextColor(selected? root.getContext().getResources().getColor(R.color.sk_tab_text):
                root.getContext().getResources().getColor(R.color.sk_tab_text_unselect));
    }


}

