package com.demo.util.common.util;

import java.math.BigDecimal;

/**
 * @author dp
 * @date 2020/3/15 2:18 下午
 */

public class BigDecimaUtil {
    /**
     * 默认除法运算精度
     */
    private static final int DEF_DIV_SCALE = 2;

    private BigDecimaUtil() { }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        private BigDecimal v1;

        public Builder with(BigDecimal digital) {
            v1 = digital;
            return this;
        }

        public Builder with(double digital) {
            v1 = new BigDecimal(Double.toString(digital));
            return this;
        }

        public Builder with(float digital) {
            v1 = new BigDecimal(Float.toString(digital));
            return this;
        }

        public Builder with(int digital) {
            v1 = new BigDecimal(Integer.toString(digital));
            return this;
        }

        public Builder with(long digital) {
            v1 = new BigDecimal(Long.toString(digital));
            return this;
        }

        public Builder with(String digital) {
            v1 = new BigDecimal(digital);
            return this;
        }

        public Builder with(short digital) {
            v1 = new BigDecimal(Short.toString(digital));
            return this;
        }

        public Builder with(byte digital) {
            v1 = new BigDecimal(Byte.toString(digital));
            return this;
        }

        /**
         * 提供精确的加法运算。
         *
         * @param v2 加数
         * @return 两个参数的和
         */

        public Builder add(double v2) {
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            v1 = v1.add(b2);
            return this;
        }

        /**
         * 提供精确的加法运算。
         *
         * @param v2 加数
         * @return Builder
         */
        public Builder add(String v2) {
            BigDecimal b2 = new BigDecimal(v2);
            v1 = v1.add(b2);
            return this;
        }

        /**
         * 提供精确的减法运算。
         *
         * @param v2 减数
         * @return 两个参数的差
         */

        public Builder sub(double v2) {
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            v1 = v1.subtract(b2);
            return this;
        }

        /**
         * 提供精确的减法运算。
         *
         * @param v2
         * @return
         */
        public Builder sub(String v2) {
            BigDecimal b2 = new BigDecimal(v2);
            v1 = v1.subtract(b2);
            return this;
        }

        /**
         * 提供精确的乘法运算。
         *
         * @param v2 乘数
         * @return 两个参数的积
         */

        public Builder mul(double v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v2));
            v1 = v1.multiply(b1);
            return this;
        }

        /**
         * @param v2
         * @return
         */
        public Builder mul(int v2) {
            BigDecimal b1 = new BigDecimal(Double.toString(v2));
            v1 = v1.multiply(b1);
            return this;
        }

        /**
         * 提供精确的乘法运算。
         *
         * @param v2
         * @return
         */
        public Builder mul(String v2) {
            BigDecimal b1 = new BigDecimal(v2);
            v1 = v1.multiply(b1);
            return this;
        }

        /**
         * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
         * 定精度，以后的数字四舍五入。
         *
         * @param v2    除数
         * @param scale 表示表示需要精确到小数点以后几位。
         * @return 两个参数的商
         */

        public Builder div(double v2, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
            }
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            v1 = v1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        /**
         * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
         * 定精度，以后的数字四舍五入。
         *
         * @param v2    除数
         * @param scale 表示表示需要精确到小数点以后几位。
         * @return 两个参数的商
         */

        public Builder div(int v2, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
            }
            BigDecimal b2 = new BigDecimal(Double.toString(v2));
            v1 = v1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        /**
         * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
         * 定精度，以后的数字四舍五入。
         *
         * @param v2    除数
         * @param scale 表示表示需要精确到小数点以后几位。
         * @return 两个参数的商
         */

        public Builder div(String v2, int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
            }
            BigDecimal b2 = new BigDecimal(v2);
            v1 = v1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);
            return this;
        }


        /**
         * 提供精确的小数位四舍五入处理。
         *
         * @param scale 小数点后保留几位
         * @return 四舍五入后的结果
         */

        public Builder round(int scale) {
            if (scale < 0) {
                throw new IllegalArgumentException("The   scale   must   be   a   positive   integer   or   zero");
            }
            BigDecimal one = new BigDecimal("1");
            v1 = v1.divide(one, scale, BigDecimal.ROUND_HALF_UP);
            return this;
        }

        /**
         * @return
         */
        public double getDouble() {
            return v1.doubleValue();
        }
        /**
         * @return
         */
        public float getFloat() {
            return v1.floatValue();
        }
        /**
         * @return
         */
        public int getInt() {
            return v1.intValue();
        }

        /**
         * @return
         */
        public Long getLong() {
            return v1.longValue();
        }

        public BigDecimal get() {
            return v1;
        }

        /**
         * @param pattern
         * @return
         */
        public String format(String pattern) {
            String s = String.format(pattern, v1);
            s = s.replaceAll("0+?$", "");
            return s.replaceAll("[.]$", "");
        }
    }
}
