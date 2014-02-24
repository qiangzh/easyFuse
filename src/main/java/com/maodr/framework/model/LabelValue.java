package com.maodr.framework.model;

import java.io.Serializable;

/**
 * 
 *  键值对模型
 *  @author Administrator
 *  @created 2014年1月21日 上午7:21:28
 *  @lastModified       
 *  @history
 */
public class LabelValue implements Comparable<LabelValue>, Serializable {

    private static final long serialVersionUID = 3689355407466181430L;

    public LabelValue() {
        super();
    }

    public LabelValue(final String label, final String value) {
        this.label = label;
        this.value = value;
    }

    private String label;

    private String value;

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return this.value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(LabelValue o) {
        String otherLabel = ((LabelValue) o).getLabel();
        return this.getLabel().compareTo(otherLabel);
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("LabelValue[");
        sb.append(this.label);
        sb.append(", ");
        sb.append(this.value);
        sb.append("]");
        return (sb.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof LabelValue)) {
            return false;
        }

        LabelValue bean = (LabelValue) obj;
        int nil = (this.getValue() == null) ? 1 : 0;
        nil += (bean.getValue() == null) ? 1 : 0;

        if (nil == 2) {
            return true;
        }
        else if (nil == 1) {
            return false;
        }
        else {
            return this.getValue().equals(bean.getValue());
        }

    }

    @Override
    public int hashCode() {
        return (this.getValue() == null) ? 17 : this.getValue().hashCode();
    }
}