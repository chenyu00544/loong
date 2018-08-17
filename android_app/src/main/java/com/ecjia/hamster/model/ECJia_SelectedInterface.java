package com.ecjia.hamster.model;

import java.io.Serializable;

public abstract class ECJia_SelectedInterface implements Serializable {
    boolean a;

    public abstract boolean isSelected();

    public abstract void setSelected(boolean z);
}
