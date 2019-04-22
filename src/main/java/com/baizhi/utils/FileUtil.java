package com.baizhi.utils;


public class FileUtil {
    public static String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }

    /*public static String formatDateTime(long milliseconds) {
        StringBuilder sb = new StringBuilder();
        long mss = milliseconds / 1000;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        DecimalFormat format = new DecimalFormat("00");
        Log.d("Time", "--days:" + days + "--hours:" + hours + "--minutes:" + minutes + "--seconds:" + seconds);
        if (days > 0 || hours > 0) {
            sb.append(format.format(hours)).append(":").append(format.format(minutes)).append(":").append(format.format(seconds));
        } else {
            sb.append(format.format(minutes)).append(":").append(format.format(seconds));
        }

        Log.d("Time", "--data:" + sb.toString());
        return sb.toString();

    }*/
    public static String formatDateTime(long mss) {
        String DateTimes = null;
        long days = mss / (60 * 60 * 24);
        long hours = (mss % (60 * 60 * 24)) / (60 * 60);
        long minutes = (mss % (60 * 60)) / 60;
        long seconds = mss % 60;
        if (hours > 0 && hours < 10) {
            DateTimes = "0" + hours + ":" + minutes + ":" + seconds;
        } else if (hours >= 10) {
            DateTimes = hours + ":" + minutes + ":" + seconds;
        } else if (minutes > 0 && minutes < 10) {
            DateTimes = "00:" + "0" + minutes + ":" + seconds;
        } else if (minutes >= 10) {
            DateTimes = "00:" + minutes + ":" + seconds;
        } else if (seconds > 0 && seconds < 10) {
            DateTimes = "00:00:0" + seconds;
        } else {
            DateTimes = "00:00:" + seconds;
        }
        return DateTimes;
    }
}
