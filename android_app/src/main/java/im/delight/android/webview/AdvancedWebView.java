package im.delight.android.webview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build.VERSION;
import android.os.Environment;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ClientCertRequest;
import android.webkit.ConsoleMessage;
import android.webkit.CookieManager;
import android.webkit.DownloadListener;
import android.webkit.GeolocationPermissions.Callback;
import android.webkit.HttpAuthHandler;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.PermissionRequest;
import android.webkit.SslErrorHandler;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebChromeClient.FileChooserParams;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

public class AdvancedWebView extends WebView {
    protected static final String[] ALTERNATIVE_BROWSERS = new String[]{"org.mozilla.firefox", "com.android.chrome", "com.opera.browser", "org.mozilla.firefox_beta", "com.chrome.beta", "com.opera.browser.beta"};
    protected static final String CHARSET_DEFAULT = "UTF-8";
    protected static final String DATABASES_SUB_FOLDER = "/databases";
    protected static final String LANGUAGE_DEFAULT_ISO3 = "eng";
    public static final String PACKAGE_NAME_DOWNLOAD_MANAGER = "com.android.providers.downloads";
    protected static final int REQUEST_CODE_FILE_PICKER = 51426;
    protected WeakReference<Activity> mActivity;
    protected WebChromeClient mCustomWebChromeClient;
    protected WebViewClient mCustomWebViewClient;
    protected ValueCallback<Uri> mFileUploadCallbackFirst;
    protected ValueCallback<Uri[]> mFileUploadCallbackSecond;
    protected WeakReference<Fragment> mFragment;
    protected boolean mGeolocationEnabled;
    protected final Map<String, String> mHttpHeaders = new HashMap();
    protected String mLanguageIso3;
    protected long mLastError;
    protected a mListener;
    protected final List<String> mPermittedHostnames = new LinkedList();
    protected int mRequestCodeFilePicker = REQUEST_CODE_FILE_PICKER;

    public interface a {
        void a(int i, String str, String str2);

        void a(String str);

        void a(String str, Bitmap bitmap);

        void a(String str, String str2, String str3, String str4, long j);

        void b(String str);
    }

    class AdvancedWebView_1 extends WebViewClient {
        final /* synthetic */ AdvancedWebView a;

        AdvancedWebView_1(AdvancedWebView advancedWebView) {
            this.a = advancedWebView;
        }

        public void onPageStarted(WebView webView, String str, Bitmap bitmap) {
            if (!(this.a.hasError() || this.a.mListener == null)) {
                this.a.mListener.a(str, bitmap);
            }
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onPageStarted(webView, str, bitmap);
            }
        }

        public void onPageFinished(WebView webView, String str) {
            if (!(this.a.hasError() || this.a.mListener == null)) {
                this.a.mListener.a(str);
            }
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onPageFinished(webView, str);
            }
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            this.a.setLastError();
            if (this.a.mListener != null) {
                this.a.mListener.a(i, str, str2);
            }
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onReceivedError(webView, i, str, str2);
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            if (!this.a.isHostnameAllowed(str)) {
                if (this.a.mListener != null) {
                    this.a.mListener.b(str);
                }
                return true;
            } else if (this.a.mCustomWebViewClient != null) {
                return this.a.mCustomWebViewClient.shouldOverrideUrlLoading(webView, str);
            } else {
                return false;
            }
        }

        public void onLoadResource(WebView webView, String str) {
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onLoadResource(webView, str);
            } else {
                super.onLoadResource(webView, str);
            }
        }

        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, String str) {
            if (VERSION.SDK_INT < 11) {
                return null;
            }
            if (this.a.mCustomWebViewClient != null) {
                return this.a.mCustomWebViewClient.shouldInterceptRequest(webView, str);
            }
            return super.shouldInterceptRequest(webView, str);
        }

        @SuppressLint({"NewApi"})
        public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
            if (VERSION.SDK_INT < 21) {
                return null;
            }
            if (this.a.mCustomWebViewClient != null) {
                return this.a.mCustomWebViewClient.shouldInterceptRequest(webView, webResourceRequest);
            }
            return super.shouldInterceptRequest(webView, webResourceRequest);
        }

        public void onFormResubmission(WebView webView, Message message, Message message2) {
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onFormResubmission(webView, message, message2);
            } else {
                super.onFormResubmission(webView, message, message2);
            }
        }

        public void doUpdateVisitedHistory(WebView webView, String str, boolean z) {
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.doUpdateVisitedHistory(webView, str, z);
            } else {
                super.doUpdateVisitedHistory(webView, str, z);
            }
        }

        public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onReceivedSslError(webView, sslErrorHandler, sslError);
            } else {
                super.onReceivedSslError(webView, sslErrorHandler, sslError);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedClientCertRequest(WebView webView, ClientCertRequest clientCertRequest) {
            if (VERSION.SDK_INT < 21) {
                return;
            }
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onReceivedClientCertRequest(webView, clientCertRequest);
            } else {
                super.onReceivedClientCertRequest(webView, clientCertRequest);
            }
        }

        public void onReceivedHttpAuthRequest(WebView webView, HttpAuthHandler httpAuthHandler, String str, String str2) {
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            } else {
                super.onReceivedHttpAuthRequest(webView, httpAuthHandler, str, str2);
            }
        }

        public boolean shouldOverrideKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (this.a.mCustomWebViewClient != null) {
                return this.a.mCustomWebViewClient.shouldOverrideKeyEvent(webView, keyEvent);
            }
            return super.shouldOverrideKeyEvent(webView, keyEvent);
        }

        public void onUnhandledKeyEvent(WebView webView, KeyEvent keyEvent) {
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onUnhandledKeyEvent(webView, keyEvent);
            } else {
                super.onUnhandledKeyEvent(webView, keyEvent);
            }
        }

        @SuppressLint({"NewApi"})
        public void onUnhandledInputEvent(WebView webView, InputEvent inputEvent) {
            if (VERSION.SDK_INT < 21) {
                return;
            }
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onUnhandledInputEvent(webView, inputEvent);
            } else {
                super.onUnhandledInputEvent(webView, inputEvent);
            }
        }

        public void onScaleChanged(WebView webView, float f, float f2) {
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onScaleChanged(webView, f, f2);
            } else {
                super.onScaleChanged(webView, f, f2);
            }
        }

        @SuppressLint({"NewApi"})
        public void onReceivedLoginRequest(WebView webView, String str, String str2, String str3) {
            if (VERSION.SDK_INT < 12) {
                return;
            }
            if (this.a.mCustomWebViewClient != null) {
                this.a.mCustomWebViewClient.onReceivedLoginRequest(webView, str, str2, str3);
            } else {
                super.onReceivedLoginRequest(webView, str, str2, str3);
            }
        }
    }

    class AdvancedWebView_2 extends WebChromeClient {
        final /* synthetic */ AdvancedWebView a;

        AdvancedWebView_2(AdvancedWebView advancedWebView) {
            this.a = advancedWebView;
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback) {
            openFileChooser(valueCallback, null);
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str) {
            openFileChooser(valueCallback, str, null);
        }

        public void openFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
            this.a.openFileInput(valueCallback, null);
        }

        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> valueCallback, FileChooserParams fileChooserParams) {
            this.a.openFileInput(null, valueCallback);
            return true;
        }

        public void onProgressChanged(WebView webView, int i) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onProgressChanged(webView, i);
            } else {
                super.onProgressChanged(webView, i);
            }
        }

        public void onReceivedTitle(WebView webView, String str) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onReceivedTitle(webView, str);
            } else {
                super.onReceivedTitle(webView, str);
            }
        }

        public void onReceivedIcon(WebView webView, Bitmap bitmap) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onReceivedIcon(webView, bitmap);
            } else {
                super.onReceivedIcon(webView, bitmap);
            }
        }

        public void onReceivedTouchIconUrl(WebView webView, String str, boolean z) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onReceivedTouchIconUrl(webView, str, z);
            } else {
                super.onReceivedTouchIconUrl(webView, str, z);
            }
        }

        public void onShowCustomView(View view, CustomViewCallback customViewCallback) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onShowCustomView(view, customViewCallback);
            } else {
                super.onShowCustomView(view, customViewCallback);
            }
        }

        @SuppressLint({"NewApi"})
        public void onShowCustomView(View view, int i, CustomViewCallback customViewCallback) {
            if (VERSION.SDK_INT < 14) {
                return;
            }
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onShowCustomView(view, i, customViewCallback);
            } else {
                super.onShowCustomView(view, i, customViewCallback);
            }
        }

        public void onHideCustomView() {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onHideCustomView();
            } else {
                super.onHideCustomView();
            }
        }

        public boolean onCreateWindow(WebView webView, boolean z, boolean z2, Message message) {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.onCreateWindow(webView, z, z2, message);
            }
            return super.onCreateWindow(webView, z, z2, message);
        }

        public void onRequestFocus(WebView webView) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onRequestFocus(webView);
            } else {
                super.onRequestFocus(webView);
            }
        }

        public void onCloseWindow(WebView webView) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onCloseWindow(webView);
            } else {
                super.onCloseWindow(webView);
            }
        }

        public boolean onJsAlert(WebView webView, String str, String str2, JsResult jsResult) {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.onJsAlert(webView, str, str2, jsResult);
            }
            return super.onJsAlert(webView, str, str2, jsResult);
        }

        public boolean onJsConfirm(WebView webView, String str, String str2, JsResult jsResult) {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.onJsConfirm(webView, str, str2, jsResult);
            }
            return super.onJsConfirm(webView, str, str2, jsResult);
        }

        public boolean onJsPrompt(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.onJsPrompt(webView, str, str2, str3, jsPromptResult);
            }
            return super.onJsPrompt(webView, str, str2, str3, jsPromptResult);
        }

        public boolean onJsBeforeUnload(WebView webView, String str, String str2, JsResult jsResult) {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.onJsBeforeUnload(webView, str, str2, jsResult);
            }
            return super.onJsBeforeUnload(webView, str, str2, jsResult);
        }

        public void onGeolocationPermissionsShowPrompt(String str, Callback callback) {
            if (this.a.mGeolocationEnabled) {
                callback.invoke(str, true, false);
            } else if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onGeolocationPermissionsShowPrompt(str, callback);
            } else {
                super.onGeolocationPermissionsShowPrompt(str, callback);
            }
        }

        public void onGeolocationPermissionsHidePrompt() {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onGeolocationPermissionsHidePrompt();
            } else {
                super.onGeolocationPermissionsHidePrompt();
            }
        }

        @SuppressLint({"NewApi"})
        public void onPermissionRequest(PermissionRequest permissionRequest) {
            if (VERSION.SDK_INT < 21) {
                return;
            }
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onPermissionRequest(permissionRequest);
            } else {
                super.onPermissionRequest(permissionRequest);
            }
        }

        @SuppressLint({"NewApi"})
        public void onPermissionRequestCanceled(PermissionRequest permissionRequest) {
            if (VERSION.SDK_INT < 21) {
                return;
            }
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onPermissionRequestCanceled(permissionRequest);
            } else {
                super.onPermissionRequestCanceled(permissionRequest);
            }
        }

        public boolean onJsTimeout() {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.onJsTimeout();
            }
            return super.onJsTimeout();
        }

        public void onConsoleMessage(String str, int i, String str2) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onConsoleMessage(str, i, str2);
            } else {
                super.onConsoleMessage(str, i, str2);
            }
        }

        public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.onConsoleMessage(consoleMessage);
            }
            return super.onConsoleMessage(consoleMessage);
        }

        public Bitmap getDefaultVideoPoster() {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.getDefaultVideoPoster();
            }
            return super.getDefaultVideoPoster();
        }

        public View getVideoLoadingProgressView() {
            if (this.a.mCustomWebChromeClient != null) {
                return this.a.mCustomWebChromeClient.getVideoLoadingProgressView();
            }
            return super.getVideoLoadingProgressView();
        }

        public void getVisitedHistory(ValueCallback<String[]> valueCallback) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.getVisitedHistory(valueCallback);
            } else {
                super.getVisitedHistory(valueCallback);
            }
        }

        public void onExceededDatabaseQuota(String str, String str2, long j, long j2, long j3, QuotaUpdater quotaUpdater) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
            } else {
                super.onExceededDatabaseQuota(str, str2, j, j2, j3, quotaUpdater);
            }
        }

        public void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
            if (this.a.mCustomWebChromeClient != null) {
                this.a.mCustomWebChromeClient.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            } else {
                super.onReachedMaxAppCacheSize(j, j2, quotaUpdater);
            }
        }
    }

    class AdvancedWebView_3 implements DownloadListener {
        final /* synthetic */ AdvancedWebView a;

        AdvancedWebView_3(AdvancedWebView advancedWebView) {
            this.a = advancedWebView;
        }

        public void onDownloadStart(String str, String str2, String str3, String str4, long j) {
            if (this.a.mListener != null) {
                this.a.mListener.a(str, str2, str3, str4, j);
            }
        }
    }

    public AdvancedWebView(Context context) {
        super(context);
        init(context);
    }

    public AdvancedWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context);
    }

    public AdvancedWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context);
    }

    public void setListener(Activity activity, a aVar) {
        setListener(activity, aVar, (int) REQUEST_CODE_FILE_PICKER);
    }

    public void setListener(Activity activity, a aVar, int i) {
        if (activity != null) {
            this.mActivity = new WeakReference(activity);
        } else {
            this.mActivity = null;
        }
        setListener(aVar, i);
    }

    public void setListener(Fragment fragment, a aVar) {
        setListener(fragment, aVar, (int) REQUEST_CODE_FILE_PICKER);
    }

    public void setListener(Fragment fragment, a aVar, int i) {
        if (fragment != null) {
            this.mFragment = new WeakReference(fragment);
        } else {
            this.mFragment = null;
        }
        setListener(aVar, i);
    }

    protected void setListener(a aVar, int i) {
        this.mListener = aVar;
        this.mRequestCodeFilePicker = i;
    }

    public void setWebViewClient(WebViewClient webViewClient) {
        this.mCustomWebViewClient = webViewClient;
    }

    public void setWebChromeClient(WebChromeClient webChromeClient) {
        this.mCustomWebChromeClient = webChromeClient;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    public void setGeolocationEnabled(boolean z) {
        if (z) {
            getSettings().setJavaScriptEnabled(true);
            getSettings().setGeolocationEnabled(true);
            setGeolocationDatabasePath();
        }
        this.mGeolocationEnabled = z;
    }

    @SuppressLint({"NewApi"})
    protected void setGeolocationDatabasePath() {
        Activity activity;
        if (this.mFragment != null && this.mFragment.get() != null && VERSION.SDK_INT >= 11 && ((Fragment) this.mFragment.get()).getActivity() != null) {
            activity = ((Fragment) this.mFragment.get()).getActivity();
        } else if (this.mActivity != null && this.mActivity.get() != null) {
            activity = (Activity) this.mActivity.get();
        } else {
            return;
        }
        getSettings().setGeolocationDatabasePath(activity.getFilesDir().getPath());
    }

    @SuppressLint({"NewApi"})
    public void onResume() {
        if (VERSION.SDK_INT >= 11) {
            super.onResume();
        }
        resumeTimers();
    }

    @SuppressLint({"NewApi"})
    public void onPause() {
        pauseTimers();
        if (VERSION.SDK_INT >= 11) {
            super.onPause();
        }
    }

    public void onDestroy() {
        try {
            ((ViewGroup) getParent()).removeView(this);
        } catch (Exception e) {
        }
        try {
            removeAllViews();
        } catch (Exception e2) {
        }
        destroy();
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != this.mRequestCodeFilePicker) {
            return;
        }
        if (i2 == -1) {
            if (intent == null) {
                return;
            }
            if (this.mFileUploadCallbackFirst != null) {
                this.mFileUploadCallbackFirst.onReceiveValue(intent.getData());
                this.mFileUploadCallbackFirst = null;
            } else if (this.mFileUploadCallbackSecond != null) {
                Object obj;
                try {
                    obj = new Uri[]{Uri.parse(intent.getDataString())};
                } catch (Exception e) {
                    obj = null;
                }
                this.mFileUploadCallbackSecond.onReceiveValue(obj);
                this.mFileUploadCallbackSecond = null;
            }
        } else if (this.mFileUploadCallbackFirst != null) {
            this.mFileUploadCallbackFirst.onReceiveValue(null);
            this.mFileUploadCallbackFirst = null;
        } else if (this.mFileUploadCallbackSecond != null) {
            this.mFileUploadCallbackSecond.onReceiveValue(null);
            this.mFileUploadCallbackSecond = null;
        }
    }

    public void addHttpHeader(String str, String str2) {
        this.mHttpHeaders.put(str, str2);
    }

    public void removeHttpHeader(String str) {
        this.mHttpHeaders.remove(str);
    }

    public void addPermittedHostname(String str) {
        this.mPermittedHostnames.add(str);
    }

    public void addPermittedHostnames(Collection<? extends String> collection) {
        this.mPermittedHostnames.addAll(collection);
    }

    public List<String> getPermittedHostnames() {
        return this.mPermittedHostnames;
    }

    public void removePermittedHostname(String str) {
        this.mPermittedHostnames.remove(str);
    }

    public void clearPermittedHostnames() {
        this.mPermittedHostnames.clear();
    }

    public boolean onBackPressed() {
        if (!canGoBack()) {
            return true;
        }
        goBack();
        return false;
    }

    @SuppressLint({"NewApi"})
    protected static void setAllowAccessFromFileUrls(WebSettings webSettings, boolean z) {
        if (VERSION.SDK_INT >= 16) {
            webSettings.setAllowFileAccessFromFileURLs(z);
            webSettings.setAllowUniversalAccessFromFileURLs(z);
        }
    }

    public void setCookiesEnabled(boolean z) {
        CookieManager.getInstance().setAcceptCookie(z);
    }

    @SuppressLint({"NewApi"})
    public void setThirdPartyCookiesEnabled(boolean z) {
        if (VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(this, z);
        }
    }

    public void setMixedContentAllowed(boolean z) {
        setMixedContentAllowed(getSettings(), z);
    }

    @SuppressLint({"NewApi"})
    protected void setMixedContentAllowed(WebSettings webSettings, boolean z) {
        if (VERSION.SDK_INT >= 21) {
            webSettings.setMixedContentMode(z ? 0 : 1);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void init(Context context) {
        if (context instanceof Activity) {
            this.mActivity = new WeakReference((Activity) context);
        }
        this.mLanguageIso3 = getLanguageIso3();
        setFocusable(true);
        setFocusableInTouchMode(true);
        setSaveEnabled(true);
        String path = context.getFilesDir().getPath();
        path = path.substring(0, path.lastIndexOf("/")) + DATABASES_SUB_FOLDER;
        WebSettings settings = getSettings();
        settings.setAllowFileAccess(false);
        setAllowAccessFromFileUrls(settings, false);
        settings.setBuiltInZoomControls(false);
        settings.setJavaScriptEnabled(true);
        settings.setDomStorageEnabled(true);
        if (VERSION.SDK_INT < 18) {
            settings.setRenderPriority(RenderPriority.HIGH);
        }
        settings.setDatabaseEnabled(true);
        if (VERSION.SDK_INT < 19) {
            settings.setDatabasePath(path);
        }
        setMixedContentAllowed(settings, true);
        setThirdPartyCookiesEnabled(true);
        super.setWebViewClient(new AdvancedWebView_1(this));
        super.setWebChromeClient(new AdvancedWebView_2(this));
        setDownloadListener(new AdvancedWebView_3(this));
    }

    public void loadUrl(String str, Map<String, String> map) {
        if (map == null) {
            map = this.mHttpHeaders;
        } else if (this.mHttpHeaders.size() > 0) {
            map.putAll(this.mHttpHeaders);
        }
        super.loadUrl(str, map);
    }

    public void loadUrl(String str) {
        if (this.mHttpHeaders.size() > 0) {
            super.loadUrl(str, this.mHttpHeaders);
        } else {
            super.loadUrl(str);
        }
    }

    public void loadUrl(String str, boolean z) {
        if (z) {
            str = makeUrlUnique(str);
        }
        loadUrl(str);
    }

    public void loadUrl(String str, boolean z, Map<String, String> map) {
        if (z) {
            str = makeUrlUnique(str);
        }
        loadUrl(str, (Map) map);
    }

    protected static String makeUrlUnique(String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        if (str.contains("?")) {
            stringBuilder.append('&');
        } else {
            if (str.lastIndexOf(47) <= 7) {
                stringBuilder.append('/');
            }
            stringBuilder.append('?');
        }
        stringBuilder.append(System.currentTimeMillis());
        stringBuilder.append('=');
        stringBuilder.append(1);
        return stringBuilder.toString();
    }

    protected boolean isHostnameAllowed(String str) {
        if (this.mPermittedHostnames.size() == 0) {
            return true;
        }
        String replace = str.replace("http://", "").replace("https://", "");
        for (String startsWith : this.mPermittedHostnames) {
            if (replace.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    protected void setLastError() {
        this.mLastError = System.currentTimeMillis();
    }

    protected boolean hasError() {
        return this.mLastError + 500 >= System.currentTimeMillis();
    }

    protected static String getLanguageIso3() {
        try {
            return Locale.getDefault().getISO3Language().toLowerCase(Locale.US);
        } catch (MissingResourceException e) {
            return LANGUAGE_DEFAULT_ISO3;
        }
    }

    protected String getFileUploadPromptLabel() {
        try {
            if (this.mLanguageIso3.equals("zho")) {
                return decodeBase64("6YCJ5oup5LiA5Liq5paH5Lu2");
            }
            if (this.mLanguageIso3.equals("spa")) {
                return "Elija un archivo";
            }
            if (this.mLanguageIso3.equals("hin")) {
                return decodeBase64("4KSP4KSVIOCkq+CkvOCkvuCkh+CksiDgpJrgpYHgpKjgpYfgpII=");
            }
            if (this.mLanguageIso3.equals("ben")) {
                return decodeBase64("4KaP4KaV4Kaf4Ka/IOCmq+CmvuCmh+CmsiDgpqjgpr/gprDgp43gpqzgpr7gpprgpqg=");
            }
            if (this.mLanguageIso3.equals("ara")) {
                return decodeBase64("2KfYrtiq2YrYp9ixINmF2YTZgSDZiNin2K3Yrw==");
            }
            if (this.mLanguageIso3.equals("por")) {
                return "Escolha um arquivo";
            }
            if (this.mLanguageIso3.equals("rus")) {
                return decodeBase64("0JLRi9Cx0LXRgNC40YLQtSDQvtC00LjQvSDRhNCw0LnQuw==");
            }
            if (this.mLanguageIso3.equals("jpn")) {
                return decodeBase64("MeODleOCoeOCpOODq+OCkumBuOaKnuOBl+OBpuOBj+OBoOOBleOBhA==");
            }
            if (this.mLanguageIso3.equals("pan")) {
                return decodeBase64("4KiH4Kmx4KiVIOCoq+CovuCoh+CosiDgqJrgqYHgqKPgqYs=");
            }
            if (this.mLanguageIso3.equals("deu")) {
                return "Wähle eine Datei";
            }
            if (this.mLanguageIso3.equals("jav")) {
                return "Pilih siji berkas";
            }
            if (this.mLanguageIso3.equals("msa")) {
                return "Pilih satu fail";
            }
            if (this.mLanguageIso3.equals("tel")) {
                return decodeBase64("4LCS4LCVIOCwq+CxhuCxluCwsuCxjeCwqOCxgSDgsI7gsILgsJrgsYHgsJXgsYvgsILgsKHgsL8=");
            }
            if (this.mLanguageIso3.equals("vie")) {
                return decodeBase64("Q2jhu41uIG3hu5l0IHThuq1wIHRpbg==");
            }
            if (this.mLanguageIso3.equals("kor")) {
                return decodeBase64("7ZWY64KY7J2YIO2MjOydvOydhCDshKDtg50=");
            }
            if (this.mLanguageIso3.equals("fra")) {
                return "Choisissez un fichier";
            }
            if (this.mLanguageIso3.equals("mar")) {
                return decodeBase64("4KSr4KS+4KSH4KSyIOCkqOCkv+CkteCkoeCkvg==");
            }
            if (this.mLanguageIso3.equals("tam")) {
                return decodeBase64("4K6S4K6w4K+BIOCuleCvh+CuvuCuquCvjeCuquCviCDgrqTgr4fgrrDgr43grrXgr4E=");
            }
            if (this.mLanguageIso3.equals("urd")) {
                return decodeBase64("2KfbjNqpINmB2KfYptmEINmF24zauiDYs9uSINin2YbYqtiu2KfYqCDaqdix24zaug==");
            }
            if (this.mLanguageIso3.equals("fas")) {
                return decodeBase64("2LHYpyDYp9mG2KrYrtin2Kgg2qnZhtuM2K8g24zaqSDZgdin24zZhA==");
            }
            if (this.mLanguageIso3.equals("tur")) {
                return "Bir dosya seçin";
            }
            if (this.mLanguageIso3.equals("ita")) {
                return "Scegli un file";
            }
            if (this.mLanguageIso3.equals("tha")) {
                return decodeBase64("4LmA4Lil4Li34Lit4LiB4LmE4Lif4Lil4LmM4Lir4LiZ4Li24LmI4LiH");
            }
            if (this.mLanguageIso3.equals("guj")) {
                return decodeBase64("4KqP4KqVIOCqq+CqvuCqh+CqsuCqqOCrhyDgqqrgqrjgqoLgqqY=");
            }
            return "Choose a file";
        } catch (Exception e) {
        }
    }

    protected static String decodeBase64(String str) throws IllegalArgumentException, UnsupportedEncodingException {
        return new String(Base64.decode(str, 0), "UTF-8");
    }

    @SuppressLint({"NewApi"})
    protected void openFileInput(ValueCallback<Uri> valueCallback, ValueCallback<Uri[]> valueCallback2) {
        if (this.mFileUploadCallbackFirst != null) {
            this.mFileUploadCallbackFirst.onReceiveValue(null);
        }
        this.mFileUploadCallbackFirst = valueCallback;
        if (this.mFileUploadCallbackSecond != null) {
            this.mFileUploadCallbackSecond.onReceiveValue(null);
        }
        this.mFileUploadCallbackSecond = valueCallback2;
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.addCategory("android.intent.category.OPENABLE");
        intent.setType("*/*");
        if (this.mFragment != null && this.mFragment.get() != null && VERSION.SDK_INT >= 11) {
            ((Fragment) this.mFragment.get()).startActivityForResult(Intent.createChooser(intent, getFileUploadPromptLabel()), this.mRequestCodeFilePicker);
        } else if (this.mActivity != null && this.mActivity.get() != null) {
            ((Activity) this.mActivity.get()).startActivityForResult(Intent.createChooser(intent, getFileUploadPromptLabel()), this.mRequestCodeFilePicker);
        }
    }

    public static boolean isFileUploadAvailable() {
        return isFileUploadAvailable(false);
    }

    public static boolean isFileUploadAvailable(boolean z) {
        if (VERSION.SDK_INT != 19) {
            return true;
        }
        String str = VERSION.RELEASE == null ? "" : VERSION.RELEASE;
        if (z || (!str.startsWith("4.4.3") && !str.startsWith("4.4.4"))) {
            return false;
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    public static boolean handleDownload(Context context, String str, String str2) {
        if (VERSION.SDK_INT < 9) {
            throw new RuntimeException("Method requires API level 9 or above");
        }
        Request request = new Request(Uri.parse(str));
        if (VERSION.SDK_INT >= 11) {
            request.allowScanningByMediaScanner();
            request.setNotificationVisibility(1);
        }
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, str2);
        DownloadManager downloadManager = (DownloadManager) context.getSystemService("download");
        try {
            downloadManager.enqueue(request);
        } catch (SecurityException e) {
            try {
                if (VERSION.SDK_INT >= 11) {
                    request.setNotificationVisibility(0);
                }
                downloadManager.enqueue(request);
            } catch (IllegalArgumentException e2) {
                openAppSettings(context, PACKAGE_NAME_DOWNLOAD_MANAGER);
                return false;
            }
        }
        return true;
    }

    @SuppressLint({"NewApi"})
    private static boolean openAppSettings(Context context, String str) {
        if (VERSION.SDK_INT < 9) {
            throw new RuntimeException("Method requires API level 9 or above");
        }
        try {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            intent.setData(Uri.parse("package:" + str));
            intent.setFlags(268435456);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
