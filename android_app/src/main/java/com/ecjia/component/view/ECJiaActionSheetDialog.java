package com.ecjia.component.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.ecmoban.android.missmall.R;
import java.util.ArrayList;
import java.util.List;

public class ECJiaActionSheetDialog {
    private Context a;
    private Dialog b;
    private TextView c;
    private TextView d;
    private LinearLayout e;
    private ScrollView f;
    private boolean g = false;
    private List<b> h;
    private Display i;

    class ECJiaActionSheetDialog_1 implements OnClickListener {
        final /* synthetic */ ECJiaActionSheetDialog a;

        ECJiaActionSheetDialog_1(ECJiaActionSheetDialog eCJiaActionSheetDialog) {
            this.a = eCJiaActionSheetDialog;
        }

        public void onClick(View view) {
            this.a.b.dismiss();
        }
    }

    public enum SheetItemColor {
        Blue("#037BFF"),
        Red("#FD4A2E");
        
        private String a;

        private SheetItemColor(String str) {
            this.a = str;
        }

        public String getName() {
            return this.a;
        }

        public void setName(String str) {
            this.a = str;
        }
    }

    public interface a {
        void a(int i);
    }

    public class b {
        String a;
        a b;
        SheetItemColor c;
        final /* synthetic */ ECJiaActionSheetDialog d;

        public b(ECJiaActionSheetDialog eCJiaActionSheetDialog, String str, SheetItemColor sheetItemColor, a aVar) {
            this.d = eCJiaActionSheetDialog;
            this.a = str;
            this.c = sheetItemColor;
            this.b = aVar;
        }
    }

    public ECJiaActionSheetDialog(Context context) {
        this.a = context;
        this.i = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
    }

    public ECJiaActionSheetDialog a() {
        View inflate = LayoutInflater.from(this.a).inflate(R.layout.view_actionsheet, null);
        inflate.setMinimumWidth(this.i.getWidth());
        this.f = (ScrollView) inflate.findViewById(R.id.sLayout_content);
        this.e = (LinearLayout) inflate.findViewById(R.id.lLayout_content);
        this.c = (TextView) inflate.findViewById(R.id.txt_title);
        this.d = (TextView) inflate.findViewById(R.id.txt_cancel);
        this.d.setOnClickListener(new ECJiaActionSheetDialog_1(this));
        this.b = new Dialog(this.a, R.style.ActionSheetDialogStyle);
        this.b.setContentView(inflate);
        Window window = this.b.getWindow();
        window.setGravity(83);
        LayoutParams attributes = window.getAttributes();
        attributes.x = 0;
        attributes.y = 0;
        window.setAttributes(attributes);
        return this;
    }

    public ECJiaActionSheetDialog a(String str) {
        this.g = true;
        this.c.setVisibility(0);
        this.c.setText(str);
        return this;
    }

    public ECJiaActionSheetDialog a(boolean z) {
        this.b.setCancelable(z);
        return this;
    }

    public ECJiaActionSheetDialog b(boolean z) {
        this.b.setCanceledOnTouchOutside(z);
        return this;
    }

    public ECJiaActionSheetDialog a(String str, SheetItemColor sheetItemColor, a aVar) {
        if (this.h == null) {
            this.h = new ArrayList();
        }
        this.h.add(new b(this, str, sheetItemColor, aVar));
        return this;
    }

    private void d() {
        if (this.h != null && this.h.size() > 0) {
            int size = this.h.size();
            if (size >= 7) {
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.f.getLayoutParams();
                layoutParams.height = this.i.getHeight() / 2;
                this.f.setLayoutParams(layoutParams);
            }
            int i = 1;
            while (i <= size) {
                b bVar = (b) this.h.get(i - 1);
                CharSequence charSequence = bVar.a;
                SheetItemColor sheetItemColor = bVar.c;
                final a aVar = bVar.b;
                View textView = new TextView(this.a);
                textView.setText(charSequence);
                textView.setTextSize(15.0f);
                textView.setGravity(17);
                if (size == 1) {
                    if (this.g) {
                        textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.actionsheet_single_selector);
                    }
                } else if (this.g) {
                    if (i < 1 || i >= size) {
                        textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                    } else {
                        textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                    }
                } else if (i == 1) {
                    textView.setBackgroundResource(R.drawable.actionsheet_top_selector);
                } else if (i < size) {
                    textView.setBackgroundResource(R.drawable.actionsheet_middle_selector);
                } else {
                    textView.setBackgroundResource(R.drawable.actionsheet_bottom_selector);
                }
                if (sheetItemColor == null) {
                    textView.setTextColor(Color.parseColor(SheetItemColor.Blue.getName()));
                } else {
                    textView.setTextColor(Color.parseColor(sheetItemColor.getName()));
                }
                textView.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) ((this.a.getResources().getDisplayMetrics().density * 45.0f) + 0.5f)));
                textView.setOnClickListener(new OnClickListener(this) {
                    final /* synthetic */ ECJiaActionSheetDialog c;

                    public void onClick(View view) {
                        aVar.a(i);
                        this.c.b.dismiss();
                    }
                });
                this.e.addView(textView);
                i++;
            }
        }
    }

    public void b() {
        d();
        this.b.show();
    }

    public void c() {
        d();
        this.b.dismiss();
    }
}
