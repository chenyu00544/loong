package com.umeng.socialize.common;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.view.KeyEvent;
import com.umeng.socialize.Config;
import com.umeng.socialize.utils.Log;
import com.umeng.socialize.utils.SocializeUtils;

public class QueuedWork {
    private static Handler uiHandler = new Handler(Looper.getMainLooper());

    public static abstract class UMAsyncTask<Result> {
        protected Runnable thread;

        class QueuedWork_UMAsyncTask_1 implements Runnable {
            QueuedWork_UMAsyncTask_1() {
            }

            public void run() {
                final Object doInBackground = UMAsyncTask.this.doInBackground();
                QueuedWork.runInMain(new Runnable() {
                    public void run() {
                        UMAsyncTask.this.onPostExecute(doInBackground);
                    }
                });
            }
        }

        class QueuedWork_UMAsyncTask_2 implements Runnable {
            QueuedWork_UMAsyncTask_2() {
            }

            public void run() {
                UMAsyncTask.this.onPreExecute();
            }
        }

        protected abstract Result doInBackground();

        protected void onPreExecute() {
        }

        protected void onPostExecute(Result result) {
        }

        public final UMAsyncTask<Result> execute() {
            this.thread = new QueuedWork_UMAsyncTask_1();
            QueuedWork.runInMain(new QueuedWork_UMAsyncTask_2());
            QueuedWork.runInBack(this.thread);
            return this;
        }

        public static void waitAsyncTask() {
        }
    }

    public static abstract class DialogThread<T> extends UMAsyncTask {
        Dialog dialog = null;

        class QueuedWork_DialogThread_1 implements OnKeyListener {
            QueuedWork_DialogThread_1() {
            }

            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                if (i == 4 && keyEvent.getRepeatCount() == 0) {
                    QueuedWork.removeInBack(DialogThread.this.thread);
                }
                return false;
            }
        }

        public DialogThread(Context context) {
            if ((context instanceof Activity) && Config.dialogSwitch) {
                if (Config.dialog != null) {
                    this.dialog = Config.dialog;
                } else {
                    this.dialog = new ProgressDialog(context);
                }
                this.dialog.setOwnerActivity((Activity) context);
                this.dialog.setOnKeyListener(new QueuedWork_DialogThread_1());
            }
        }

        protected void onPostExecute(Object obj) {
            super.onPostExecute(obj);
            SocializeUtils.safeCloseDialog(this.dialog);
        }

        protected void onPreExecute() {
            super.onPreExecute();
            SocializeUtils.safeShowDialog(this.dialog);
        }
    }

    public static void runInMain(Runnable runnable) {
        uiHandler.post(runnable);
    }

    public static void runInBack(Runnable runnable) {
        HandlerThread handlerThread = new HandlerThread(Log.TAG, 10);
        handlerThread.start();
        new Handler(handlerThread.getLooper()).post(runnable);
    }

    public static void removeInBack(Runnable runnable) {
    }

    public static void cancle(Runnable runnable) {
    }
}
