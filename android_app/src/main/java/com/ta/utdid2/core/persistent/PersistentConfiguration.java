package com.ta.utdid2.core.persistent;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import com.ta.utdid2.android.utils.StringUtils;
import com.ta.utdid2.core.persistent.MySharedPreferences.MyEditor;
import java.io.File;
import java.util.Map;
import java.util.Map.Entry;

public class PersistentConfiguration {
    private static final String KEY_TIMESTAMP = "t";
    private static final String KEY_TIMESTAMP2 = "t2";
    private boolean mCanRead = false;
    private boolean mCanWrite = false;
    private String mConfigName = "";
    private Context mContext = null;
    private Editor mEditor = null;
    private String mFolderName = "";
    private boolean mIsLessMode = false;
    private boolean mIsSafety = false;
    private MyEditor mMyEditor = null;
    private MySharedPreferences mMySP = null;
    private SharedPreferences mSp = null;
    private TransactionXMLFile mTxf = null;

    public PersistentConfiguration(Context context, String str, String str2, boolean z, boolean z2) {
        long j;
        long j2;
        Editor edit;
        MyEditor edit2;
        this.mIsSafety = z;
        this.mIsLessMode = z2;
        this.mConfigName = str2;
        this.mFolderName = str;
        this.mContext = context;
        long j3 = 0;
        if (context != null) {
            this.mSp = context.getSharedPreferences(str2, 0);
            j3 = this.mSp.getLong("t", 0);
        }
        String str3 = null;
        try {
            str3 = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(str3)) {
            this.mCanWrite = false;
            this.mCanRead = false;
        } else if (str3.equals("mounted")) {
            this.mCanWrite = true;
            this.mCanRead = true;
        } else if (str3.equals("mounted_ro")) {
            this.mCanRead = true;
            this.mCanWrite = false;
        } else {
            this.mCanWrite = false;
            this.mCanRead = false;
        }
        if (!((!this.mCanRead && !this.mCanWrite) || context == null || StringUtils.isEmpty(str))) {
            this.mTxf = getTransactionXMLFile(str);
            if (this.mTxf != null) {
                try {
                    this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                    j = this.mMySP.getLong("t", 0);
                    if (z2) {
                        j2 = this.mSp.getLong(KEY_TIMESTAMP2, 0);
                        try {
                            j3 = this.mMySP.getLong(KEY_TIMESTAMP2, 0);
                            if (j2 < j3 && j2 > 0) {
                                try {
                                    copySPToMySP(this.mSp, this.mMySP);
                                    this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                                } catch (Exception e2) {
                                    j = j2;
                                    j2 = j;
                                    if (j2 == j3) {
                                    }
                                    j = System.currentTimeMillis();
                                    if (this.mIsLessMode) {
                                    }
                                    if (this.mSp != null) {
                                        edit = this.mSp.edit();
                                        edit.putLong(KEY_TIMESTAMP2, j);
                                        edit.commit();
                                    }
                                    if (this.mMySP == null) {
                                        edit2 = this.mMySP.edit();
                                        edit2.putLong(KEY_TIMESTAMP2, j);
                                        edit2.commit();
                                    }
                                }
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.mIsLessMode) {
                                }
                                if (this.mSp != null) {
                                    edit = this.mSp.edit();
                                    edit.putLong(KEY_TIMESTAMP2, j);
                                    edit.commit();
                                }
                                if (this.mMySP == null) {
                                    edit2 = this.mMySP.edit();
                                    edit2.putLong(KEY_TIMESTAMP2, j);
                                    edit2.commit();
                                }
                            } else if (j2 > j3 && j3 > 0) {
                                copyMySPToSP(this.mMySP, this.mSp);
                                this.mSp = context.getSharedPreferences(str2, 0);
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.mIsLessMode) {
                                }
                                if (this.mSp != null) {
                                    edit = this.mSp.edit();
                                    edit.putLong(KEY_TIMESTAMP2, j);
                                    edit.commit();
                                }
                                if (this.mMySP == null) {
                                    edit2 = this.mMySP.edit();
                                    edit2.putLong(KEY_TIMESTAMP2, j);
                                    edit2.commit();
                                }
                            } else if (j2 == 0 && j3 > 0) {
                                copyMySPToSP(this.mMySP, this.mSp);
                                this.mSp = context.getSharedPreferences(str2, 0);
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.mIsLessMode) {
                                }
                                if (this.mSp != null) {
                                    edit = this.mSp.edit();
                                    edit.putLong(KEY_TIMESTAMP2, j);
                                    edit.commit();
                                }
                                if (this.mMySP == null) {
                                    edit2 = this.mMySP.edit();
                                    edit2.putLong(KEY_TIMESTAMP2, j);
                                    edit2.commit();
                                }
                            } else if (j3 != 0 || j2 <= 0) {
                                if (j2 == j3) {
                                    copySPToMySP(this.mSp, this.mMySP);
                                    this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                                }
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.mIsLessMode) {
                                }
                                if (this.mSp != null) {
                                    edit = this.mSp.edit();
                                    edit.putLong(KEY_TIMESTAMP2, j);
                                    edit.commit();
                                }
                                if (this.mMySP == null) {
                                    edit2 = this.mMySP.edit();
                                    edit2.putLong(KEY_TIMESTAMP2, j);
                                    edit2.commit();
                                }
                            } else {
                                copySPToMySP(this.mSp, this.mMySP);
                                this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                                if (j2 == j3) {
                                }
                                j = System.currentTimeMillis();
                                if (this.mIsLessMode) {
                                }
                                if (this.mSp != null) {
                                    edit = this.mSp.edit();
                                    edit.putLong(KEY_TIMESTAMP2, j);
                                    edit.commit();
                                }
                                if (this.mMySP == null) {
                                    edit2 = this.mMySP.edit();
                                    edit2.putLong(KEY_TIMESTAMP2, j);
                                    edit2.commit();
                                }
                            }
                        } catch (Exception e3) {
                            j3 = j;
                            j = j2;
                            j2 = j;
                            if (j2 == j3) {
                            }
                            j = System.currentTimeMillis();
                            if (this.mIsLessMode) {
                            }
                            if (this.mSp != null) {
                                edit = this.mSp.edit();
                                edit.putLong(KEY_TIMESTAMP2, j);
                                edit.commit();
                            }
                            if (this.mMySP == null) {
                                edit2 = this.mMySP.edit();
                                edit2.putLong(KEY_TIMESTAMP2, j);
                                edit2.commit();
                            }
                        }
                    } else if (j3 > j) {
                        try {
                            copySPToMySP(this.mSp, this.mMySP);
                            this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                            j2 = j3;
                            j3 = j;
                        } catch (Exception e4) {
                            long j4 = j;
                            j = j3;
                            j3 = j4;
                            j2 = j;
                            if (j2 == j3) {
                            }
                            j = System.currentTimeMillis();
                            if (this.mIsLessMode) {
                            }
                            if (this.mSp != null) {
                                edit = this.mSp.edit();
                                edit.putLong(KEY_TIMESTAMP2, j);
                                edit.commit();
                            }
                            if (this.mMySP == null) {
                                edit2 = this.mMySP.edit();
                                edit2.putLong(KEY_TIMESTAMP2, j);
                                edit2.commit();
                            }
                        }
                        if (j2 == j3 || (j2 == 0 && j3 == 0)) {
                            j = System.currentTimeMillis();
                            if (this.mIsLessMode || (this.mIsLessMode && j2 == 0 && j3 == 0)) {
                                if (this.mSp != null) {
                                    edit = this.mSp.edit();
                                    edit.putLong(KEY_TIMESTAMP2, j);
                                    edit.commit();
                                }
                                if (this.mMySP == null) {
                                    edit2 = this.mMySP.edit();
                                    edit2.putLong(KEY_TIMESTAMP2, j);
                                    edit2.commit();
                                }
                            }
                            return;
                        }
                        return;
                    } else {
                        if (j3 < j) {
                            copyMySPToSP(this.mMySP, this.mSp);
                            this.mSp = context.getSharedPreferences(str2, 0);
                            j2 = j3;
                            j3 = j;
                        } else if (j3 == j) {
                            copySPToMySP(this.mSp, this.mMySP);
                            this.mMySP = this.mTxf.getMySharedPreferences(str2, 0);
                            j2 = j3;
                            j3 = j;
                        } else {
                            j2 = j3;
                            j3 = j;
                        }
                        if (j2 == j3) {
                        }
                        j = System.currentTimeMillis();
                        if (this.mIsLessMode) {
                        }
                        if (this.mSp != null) {
                            edit = this.mSp.edit();
                            edit.putLong(KEY_TIMESTAMP2, j);
                            edit.commit();
                        }
                        if (this.mMySP == null) {
                            edit2 = this.mMySP.edit();
                            edit2.putLong(KEY_TIMESTAMP2, j);
                            edit2.commit();
                        }
                    }
                } catch (Exception e5) {
                    j = j3;
                    j3 = 0;
                    j2 = j;
                    if (j2 == j3) {
                    }
                    j = System.currentTimeMillis();
                    if (this.mIsLessMode) {
                    }
                    if (this.mSp != null) {
                        edit = this.mSp.edit();
                        edit.putLong(KEY_TIMESTAMP2, j);
                        edit.commit();
                    }
                    if (this.mMySP == null) {
                        edit2 = this.mMySP.edit();
                        edit2.putLong(KEY_TIMESTAMP2, j);
                        edit2.commit();
                    }
                }
            }
        }
        j2 = j3;
        j3 = 0;
        if (j2 == j3) {
        }
        j = System.currentTimeMillis();
        if (this.mIsLessMode) {
        }
        if (this.mSp != null) {
            edit = this.mSp.edit();
            edit.putLong(KEY_TIMESTAMP2, j);
            edit.commit();
        }
        try {
            if (this.mMySP == null) {
                edit2 = this.mMySP.edit();
                edit2.putLong(KEY_TIMESTAMP2, j);
                edit2.commit();
            }
        } catch (Exception e6) {
        }
    }

    private TransactionXMLFile getTransactionXMLFile(String str) {
        File rootFolder = getRootFolder(str);
        if (rootFolder == null) {
            return null;
        }
        this.mTxf = new TransactionXMLFile(rootFolder.getAbsolutePath());
        return this.mTxf;
    }

    private File getRootFolder(String str) {
        if (Environment.getExternalStorageDirectory() == null) {
            return null;
        }
        File file = new File(String.format("%s%s%s", new Object[]{Environment.getExternalStorageDirectory().getAbsolutePath(), File.separator, str}));
        if (file == null || file.exists()) {
            return file;
        }
        file.mkdirs();
        return file;
    }

    private void copySPToMySP(SharedPreferences sharedPreferences, MySharedPreferences mySharedPreferences) {
        if (sharedPreferences != null && mySharedPreferences != null) {
            MyEditor edit = mySharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : sharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private void copyMySPToSP(MySharedPreferences mySharedPreferences, SharedPreferences sharedPreferences) {
        if (mySharedPreferences != null && sharedPreferences != null) {
            Editor edit = sharedPreferences.edit();
            if (edit != null) {
                edit.clear();
                for (Entry entry : mySharedPreferences.getAll().entrySet()) {
                    String str = (String) entry.getKey();
                    Object value = entry.getValue();
                    if (value instanceof String) {
                        edit.putString(str, (String) value);
                    } else if (value instanceof Integer) {
                        edit.putInt(str, ((Integer) value).intValue());
                    } else if (value instanceof Long) {
                        edit.putLong(str, ((Long) value).longValue());
                    } else if (value instanceof Float) {
                        edit.putFloat(str, ((Float) value).floatValue());
                    } else if (value instanceof Boolean) {
                        edit.putBoolean(str, ((Boolean) value).booleanValue());
                    }
                }
                edit.commit();
            }
        }
    }

    private boolean checkSDCardXMLFile() {
        if (this.mMySP == null) {
            return false;
        }
        boolean checkFile = this.mMySP.checkFile();
        if (checkFile) {
            return checkFile;
        }
        commit();
        return checkFile;
    }

    private void initEditor() {
        if (this.mEditor == null && this.mSp != null) {
            this.mEditor = this.mSp.edit();
        }
        if (this.mCanWrite && this.mMyEditor == null && this.mMySP != null) {
            this.mMyEditor = this.mMySP.edit();
        }
        checkSDCardXMLFile();
    }

    public void putInt(String str, int i) {
        if (!StringUtils.isEmpty(str) && !str.equals("t")) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putInt(str, i);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putInt(str, i);
            }
        }
    }

    public void putLong(String str, long j) {
        if (!StringUtils.isEmpty(str) && !str.equals("t")) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putLong(str, j);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putLong(str, j);
            }
        }
    }

    public void putBoolean(String str, boolean z) {
        if (!StringUtils.isEmpty(str) && !str.equals("t")) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putBoolean(str, z);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putBoolean(str, z);
            }
        }
    }

    public void putFloat(String str, float f) {
        if (!StringUtils.isEmpty(str) && !str.equals("t")) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putFloat(str, f);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putFloat(str, f);
            }
        }
    }

    public void putString(String str, String str2) {
        if (!StringUtils.isEmpty(str) && !str.equals("t")) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.putString(str, str2);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.putString(str, str2);
            }
        }
    }

    public void remove(String str) {
        if (!StringUtils.isEmpty(str) && !str.equals("t")) {
            initEditor();
            if (this.mEditor != null) {
                this.mEditor.remove(str);
            }
            if (this.mMyEditor != null) {
                this.mMyEditor.remove(str);
            }
        }
    }

    public void reload() {
        if (!(this.mSp == null || this.mContext == null)) {
            this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
        }
        String str = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(str)) {
            if (str.equals("mounted") || (str.equals("mounted_ro") && this.mMySP != null)) {
                try {
                    if (this.mTxf != null) {
                        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
                    }
                } catch (Exception e2) {
                }
            }
        }
    }

    public void clear() {
        initEditor();
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mEditor != null) {
            this.mEditor.clear();
            this.mEditor.putLong("t", currentTimeMillis);
        }
        if (this.mMyEditor != null) {
            this.mMyEditor.clear();
            this.mMyEditor.putLong("t", currentTimeMillis);
        }
    }

    public boolean commit() {
        boolean z = true;
        long currentTimeMillis = System.currentTimeMillis();
        if (this.mEditor != null) {
            if (!(this.mIsLessMode || this.mSp == null)) {
                this.mEditor.putLong("t", currentTimeMillis);
            }
            if (!this.mEditor.commit()) {
                z = false;
            }
        }
        if (!(this.mSp == null || this.mContext == null)) {
            this.mSp = this.mContext.getSharedPreferences(this.mConfigName, 0);
        }
        String str = null;
        try {
            str = Environment.getExternalStorageState();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!StringUtils.isEmpty(str)) {
            if (str.equals("mounted")) {
                if (this.mMySP == null) {
                    TransactionXMLFile transactionXMLFile = getTransactionXMLFile(this.mFolderName);
                    if (transactionXMLFile != null) {
                        this.mMySP = transactionXMLFile.getMySharedPreferences(this.mConfigName, 0);
                        if (this.mIsLessMode) {
                            copyMySPToSP(this.mMySP, this.mSp);
                        } else {
                            copySPToMySP(this.mSp, this.mMySP);
                        }
                        this.mMyEditor = this.mMySP.edit();
                    }
                } else if (!(this.mMyEditor == null || this.mMyEditor.commit())) {
                    z = false;
                }
            }
            if (str.equals("mounted") || (str.equals("mounted_ro") && this.mMySP != null)) {
                try {
                    if (this.mTxf != null) {
                        this.mMySP = this.mTxf.getMySharedPreferences(this.mConfigName, 0);
                    }
                } catch (Exception e2) {
                }
            }
        }
        return z;
    }

    public String getString(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            String string = this.mSp.getString(str, "");
            if (!StringUtils.isEmpty(string)) {
                return string;
            }
        }
        if (this.mMySP != null) {
            return this.mMySP.getString(str, "");
        }
        return "";
    }

    public int getInt(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getInt(str, 0);
        }
        if (this.mMySP != null) {
            return this.mMySP.getInt(str, 0);
        }
        return 0;
    }

    public long getLong(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getLong(str, 0);
        }
        if (this.mMySP != null) {
            return this.mMySP.getLong(str, 0);
        }
        return 0;
    }

    public float getFloat(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getFloat(str, 0.0f);
        }
        if (this.mMySP != null) {
            return this.mMySP.getFloat(str, 0.0f);
        }
        return 0.0f;
    }

    public boolean getBoolean(String str) {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getBoolean(str, false);
        }
        if (this.mMySP != null) {
            return this.mMySP.getBoolean(str, false);
        }
        return false;
    }

    public Map<String, ?> getAll() {
        checkSDCardXMLFile();
        if (this.mSp != null) {
            return this.mSp.getAll();
        }
        if (this.mMySP != null) {
            return this.mMySP.getAll();
        }
        return null;
    }
}
