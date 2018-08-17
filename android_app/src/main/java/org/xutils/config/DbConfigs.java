package org.xutils.config;

import org.xutils.DbManager;
import org.xutils.DbManager.DaoConfig;
import org.xutils.DbManager.DbOpenListener;
import org.xutils.DbManager.DbUpgradeListener;
import org.xutils.common.util.LogUtil;

public enum DbConfigs {
    HTTP(new DaoConfig().setDbName("xUtils_http_cache.db").setDbVersion(1).setDbOpenListener(new DbConfigs_2()).setDbUpgradeListener(new DbConfigs_1())),
    COOKIE(new DaoConfig().setDbName("xUtils_http_cookie.db").setDbVersion(1).setDbOpenListener(new DbConfigs_4()).setDbUpgradeListener(new DbConfigs_3()));
    
    private DaoConfig a;

    static class DbConfigs_1 implements DbUpgradeListener {
        DbConfigs_1() {
        }

        public void onUpgrade(DbManager dbManager, int i, int i2) {
            try {
                dbManager.dropDb();
            } catch (Throwable e) {
                LogUtil.e(e.getMessage(), e);
            }
        }
    }

    static class DbConfigs_2 implements DbOpenListener {
        DbConfigs_2() {
        }

        public void onDbOpened(DbManager dbManager) {
            dbManager.getDatabase().enableWriteAheadLogging();
        }
    }

    static class DbConfigs_3 implements DbUpgradeListener {
        DbConfigs_3() {
        }

        public void onUpgrade(DbManager dbManager, int i, int i2) {
            try {
                dbManager.dropDb();
            } catch (Throwable e) {
                LogUtil.e(e.getMessage(), e);
            }
        }
    }

    static class DbConfigs_4 implements DbOpenListener {
        DbConfigs_4() {
        }

        public void onDbOpened(DbManager dbManager) {
            dbManager.getDatabase().enableWriteAheadLogging();
        }
    }

    private DbConfigs(DaoConfig daoConfig) {
        this.a = daoConfig;
    }

    public DaoConfig getConfig() {
        return this.a;
    }
}
