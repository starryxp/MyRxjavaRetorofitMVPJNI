package com.xiaopeng.myrxjavaretorofitmvpjni.api.interneter;

import com.orhanobut.logger.Logger;
import com.xiaopeng.myrxjavaretorofitmvpjni.util.secret.SecretUtil;

import java.io.Serializable;
import java.lang.reflect.Field;

/**
 * ParamsSet
 * Created by xiaopeng on 2017/11/29.
 */

public interface ParamsSet {
    public static final String VERSION_1_0 = "2.0";
    public static final String CURRENT_DEFAULT = "1";
    public static final String PAGE_SIZE_DEFAULT = "10";

    public abstract class Param implements Serializable {

        public static final String TAG = "Param";
        private static final long serialVersionUID = 5610476206066911907L;
        /**
         * 版本信息，当前版本是1.0 M
         */
        private String versionCode = VERSION_1_0;

        /**
         * 版本信息，version name
         */
//        private String appVersion = Constants.VERSION_NAME;

        /**
         * 统计用到
         */
        private String from = "ANDROID";

        /**
         * 同盾设备指纹黑盒数据
         */
//        private String blackBox = Constants.BLACK_BOX;
        @Override
        public String toString() {
            Field[] allFields = getFields(this.getClass());
            StringBuilder parameters = new StringBuilder("");
            try {
                for (Field field : allFields) {
                    field.setAccessible(true);
                    Object object = field.get(this);
                    String name = field.getName();
                    if (object != null && !"serialVersionUID".equals(name)
                            && !"TAG".equals(name)) {
                        parameters.append(name).append("=").append(object)
                                .append("&");
                    }
                }
                parameters.deleteCharAt(parameters.length() - 1);
            } catch (IllegalAccessException e) {
                Logger.e(TAG, e.getMessage());
            }
            return parameters.toString();
        }

        private Field[] getFields(Class c) {
            Field[] fields = c.getDeclaredFields();
            Class superClass = c.getSuperclass();
            if (superClass == null) {
                return fields;
            }
            Field[] superFields = getFields(superClass);
            Field[] allFields = new Field[fields.length + superFields.length];
            System.arraycopy(fields, 0, allFields, 0, fields.length);
            System.arraycopy(superFields, 0, allFields, fields.length,
                    superFields.length);
            return allFields;
        }

        /**
         * 字符串加密
         */
        public String toEncryptString() {
            return SecretUtil.encryption(toString());
        }

    }

    public abstract class QueryParam extends Param {

        private static final long serialVersionUID = -3004991768256043008L;

        public static final String TAG = "QueryParam";

        /**
         * 当前页数 M
         */
        private String current;

        /**
         * 页大小 M
         */
        private String pageSize;

        /**
         *统计用到
         */
//        private String from="ANDROID";

        /**
         * 设置当前页
         */
        public void setCurrent(String current) {
            this.current = current;
        }

        public String getCurrent() {
            return current;
        }

        /**
         * 当前页自加1
         */
        public void setCurrentAddSelf() {
            try {
                current = String.valueOf(Integer.valueOf(current) + 1);
            } catch (Exception e) {
                Logger.e(TAG, e.getMessage(), e);
            }
        }

        /**
         * 当前页大小
         */
        public void setPageSize(String pageSize) {
            this.pageSize = pageSize;
        }

        public QueryParam(String current, String pageSize) {
            this.current = current;
            this.pageSize = pageSize;
        }

    }
}
