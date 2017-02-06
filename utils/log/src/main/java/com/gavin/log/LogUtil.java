package com.gavin.log;

import android.util.Log;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogUtil {
    private static final String TAB = "    ";
    private static LogUtil.PrintLog mPrintLog = new LogUtil.PrintLog();
    private static int level = 2;
    private static String mTagPre = "";
    private static int callStackIndex = 3;
    private static int methodCount = 1;
    private static boolean showMethod = true;
    private static boolean showThread = true;
    private static boolean showStartLine = true;
    private static boolean showEndLine = true;
    private static boolean showCellLine = true;
    private static boolean jsonFormat = false;

    public LogUtil() {
    }

    public static void setLevel(int level) {
        level = level;
    }

    public static void setShowCellLine(boolean showCellLine) {
        showCellLine = showCellLine;
        showStartLine = showCellLine;
        showEndLine = showCellLine;
    }

    public static void setJsonFormat(boolean jsonFormat) {
        jsonFormat = jsonFormat;
    }

    public static void setShowStartLine(boolean showStartLine) {
        showStartLine = showStartLine;
    }

    public static void setShowEndLine(boolean showEndLine) {
        showEndLine = showEndLine;
    }

    public static void setTagPre(String mTagPre) {
        mTagPre = mTagPre;
    }

    public static void setCallStackIndex(int callStackIndex) {
        callStackIndex = callStackIndex;
    }

    public static void setMethodCount(int methodCount) {
        methodCount = methodCount;
    }

    public static void setShowThread(boolean showThread) {
        showThread = showThread;
    }

    public static void setShowMethod(boolean showMethod) {
        showMethod = showMethod;
    }

    public static LogUtil.PrintLog t(String tag) {
        return tag != null && !tag.trim().isEmpty()?mPrintLog.tag(tag):mPrintLog;
    }

    public static void v(String message, Object... args) {
        mPrintLog.v(message, args);
    }

    public static void v(Throwable tr, String message, Object... args) {
        mPrintLog.v(tr, message, args);
    }

    public static void i(String message, Object... args) {
        mPrintLog.i(message, args);
    }

    public static void i(Throwable tr, String message, Object... args) {
        mPrintLog.i(tr, message, args);
    }

    public static void d(String message, Object... args) {
        mPrintLog.d(message, args);
    }

    public static void d(Throwable tr, String message, Object... args) {
        mPrintLog.d(tr, message, args);
    }

    public static void w(String message, Object... args) {
        mPrintLog.w(message, args);
    }

    public static void w(Throwable tr, String message, Object... args) {
        mPrintLog.w(tr, message, args);
    }

    public static void w(Throwable tr) {
        mPrintLog.w(tr);
    }

    public static void e(String message, Object... args) {
        mPrintLog.e(message, args);
    }

    public static void e(Throwable tr, String message, Object... args) {
        mPrintLog.e(tr, message, args);
    }

    public static void e(Throwable tr) {
        mPrintLog.e(tr);
    }

    public static void wtf(Throwable tr) {
        mPrintLog.wtf(tr);
    }

    public static void wtf(String message, Object... args) {
        mPrintLog.wtf(message, args);
    }

    public static void wtf(Throwable tr, String message, Object... args) {
        mPrintLog.wtf(tr, message, args);
    }

    public static class PrintLog {
        private final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");
        private final String SINGLE_DIVIDER = "────────────────────────────────────────────────────────────────────────────────────────\r\n";
        private final ThreadLocal<String> localTag = new ThreadLocal();

        public PrintLog() {
        }

        public LogUtil.PrintLog tag(String tag) {
            if(tag != null) {
                this.localTag.set(tag);
            }

            return this;
        }

        public void v(String message, Object... args) {
            if(LogUtil.level <= 2) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.v(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, (Throwable)null, message, args));
            }
        }

        public void v(Throwable tr, String message, Object... args) {
            if(LogUtil.level <= 2) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.v(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, message, args));
            }
        }

        public void i(String message, Object... args) {
            if(LogUtil.level <= 4) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.i(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, (Throwable)null, message, args));
            }
        }

        public void i(Throwable tr, String message, Object... args) {
            if(LogUtil.level <= 4) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.i(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, message, args));
            }
        }

        public void d(String message, Object... args) {
            if(LogUtil.level <= 3) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.d(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, (Throwable)null, message, args));
            }
        }

        public void d(Throwable tr, String message, Object... args) {
            if(LogUtil.level <= 3) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.d(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, message, args));
            }
        }

        public void w(String message, Object... args) {
            if(LogUtil.level <= 5) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.w(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, (Throwable)null, message, args));
            }
        }

        public void w(Throwable tr) {
            if(LogUtil.level <= 5) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.w(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, (String)null, new Object[0]));
            }
        }

        public void w(Throwable tr, String message, Object... args) {
            if(LogUtil.level <= 5) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.w(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, message, args));
            }
        }

        public void e(Throwable tr) {
            if(LogUtil.level <= 6) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.e(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, (String)null, new Object[0]));
            }
        }

        public void e(String message, Object... args) {
            if(LogUtil.level <= 6) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.e(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, (Throwable)null, message, args));
            }
        }

        public void e(Throwable tr, String message, Object... args) {
            if(LogUtil.level <= 6) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.e(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, message, args));
            }
        }

        public void wtf(Throwable tr) {
            if(LogUtil.level <= 7) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.wtf(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, (String)null, new Object[0]));
            }
        }

        public void wtf(String message, Object... args) {
            if(LogUtil.level <= 7) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.wtf(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, (Throwable)null, message, args));
            }
        }

        public void wtf(Throwable tr, String message, Object... args) {
            if(LogUtil.level <= 7) {
                LogUtil.PrintLog.LogPreparedBean logPreparedBean = this.getLogPreparedBean();
                Log.wtf(logPreparedBean.tag, this.formatLogMessage(logPreparedBean.method, tr, message, args));
            }
        }

        private String getStackTraceString(Throwable t) {
            StringWriter sw = new StringWriter(256);
            PrintWriter pw = new PrintWriter(sw, false);
            t.printStackTrace(pw);
            pw.flush();
            return sw.toString();
        }

        private String getClazzSimpleName(StackTraceElement element) {
            String tag = element.getClassName();
            Matcher m = this.ANONYMOUS_CLASS.matcher(tag);
            if(m.find()) {
                tag = m.replaceAll("");
            }

            return tag.substring(tag.lastIndexOf(46) + 1);
        }

        private StackTraceElement getStackTraceElement(int index) {
            StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
            if(stackTrace.length <= index) {
                throw new IllegalStateException("Synthetic stacktrace didn\'t have enough elements: are you using proguard?");
            } else {
                return stackTrace[index];
            }
        }

        private StackTraceElement[] getStackTraceElement(int index, int count) {
            if(count < 1) {
                count = 1;
            }

            StackTraceElement[] stackTrace = (new Throwable()).getStackTrace();
            boolean resultSize = true;
            int var7;
            if(stackTrace.length - index > count) {
                var7 = count;
            } else {
                var7 = stackTrace.length - index;
            }

            StackTraceElement[] result = new StackTraceElement[var7];

            for(int i = 0; i < result.length; ++i) {
                result[i] = stackTrace[i + index];
            }

            return result;
        }

        private String formatLogMessage(String method, Throwable tr, String message, Object... args) {
            StringBuilder builder = new StringBuilder();
            if(LogUtil.showStartLine) {
                builder.append("────────────────────────────────────────────────────────────────────────────────────────\r\n");
            }

            if(LogUtil.showThread) {
                builder.append("├ thread: ").append(Thread.currentThread().getName()).append("\r\n");
            }

            if(LogUtil.showMethod) {
                if(LogUtil.showCellLine) {
                    method = method.replace("\n", "\n│");
                }

                builder.append("├ method: ").append(method).append("\r\n");
            }

            if(message != null) {
                if(args.length > 0) {
                    message = String.format(message, args);
                }

                if(LogUtil.jsonFormat) {
                    message = this.jsonFormat(message);
                }

                if(LogUtil.showCellLine) {
                    message = message.replace("\n", "\n│");
                    builder.append("│");
                }

                builder.append(message).append("\r\n");
            }

            if(tr != null) {
                String throwable = this.getStackTraceString(tr);
                if(LogUtil.showCellLine) {
                    throwable = throwable.replace("\n", "\n│");
                    builder.append("│");
                }

                builder.append(throwable);
            }

            if(LogUtil.showEndLine) {
                builder.append("────────────────────────────────────────────────────────────────────────────────────────\r\n");
            }

            return builder.toString();
        }

        private String getTag() {
            String tag = (String)this.localTag.get();
            if(tag != null) {
                this.localTag.remove();
                return tag;
            } else {
                return null;
            }
        }

        private LogUtil.PrintLog.LogPreparedBean getLogPreparedBean() {
            String tag = this.getTag();
            int callStackIndex = LogUtil.callStackIndex;
            if(tag == null) {
                ++callStackIndex;
            }

            StringBuffer method = new StringBuffer();
            StringBuffer level = new StringBuffer("    ");
            StackTraceElement[] elements = this.getStackTraceElement(callStackIndex, LogUtil.methodCount);

            for(int clazzName = elements.length - 1; clazzName >= 0; --clazzName) {
                method.append(elements[clazzName].toString());
                if(clazzName != 0) {
                    method.append("\r\n");
                    method.append(level);
                    level.append("    ");
                }
            }

            String var8 = null;
            if(tag != null) {
                var8 = tag;
            } else {
                var8 = this.getClazzSimpleName(elements[0]);
            }

            StringBuilder builder = new StringBuilder();
            builder.append(LogUtil.mTagPre);
            builder.append(var8);
            return new LogUtil.PrintLog.LogPreparedBean(builder.toString(), method.toString());
        }

        public String jsonFormat(String jsonStr) {
            int level = 0;
            StringBuffer jsonForMatStr = new StringBuffer();

            for(int i = 0; i < jsonStr.length(); ++i) {
                char c = jsonStr.charAt(i);
                if(level > 0 && 10 == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                    jsonForMatStr.append(this.getLevelStr(level));
                }

                switch(c) {
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '[':
                case '{':
                    jsonForMatStr.append(c + "\n");
                    ++level;
                    break;
                case ']':
                case '}':
                    jsonForMatStr.append("\n");
                    --level;
                    jsonForMatStr.append(this.getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                }
            }

            return jsonForMatStr.toString();
        }

        private String getLevelStr(int level) {
            StringBuffer levelStr = new StringBuffer();

            for(int levelI = 0; levelI < level; ++levelI) {
                levelStr.append("\t");
            }

            return levelStr.toString();
        }

        class LogPreparedBean {
            String tag;
            String method;

            public LogPreparedBean(String tag, String method) {
                this.tag = tag;
                this.method = method;
            }
        }
    }
}
