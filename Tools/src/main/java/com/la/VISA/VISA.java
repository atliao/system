package com.la.VISA;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.NativeLong;
import com.sun.jna.ptr.LongByReference;

/**
 * @author LA
 * @createDate 2023-02-19-11:11
 */
public interface VISA  extends Library {

    VISA INSTANCE = (VISA) Native.loadLibrary("visa32", VISA.class);

    public static final long VI_NULL = 0;
    public static final long VI_SUCCESS = 0;

    public int viOpenDefaultRM(LongByReference session);

    public int viOpen(NativeLong viSession, String rsrcName,
                      NativeLong accessMode, NativeLong timeout,
                      LongByReference session);

    public int viClose(NativeLong vi);

    public int viScanf(NativeLong vi, String readFmt, Object... args);

    public int viPrintf(NativeLong vi, String writeFmt, Object... args);

    public int viRead(NativeLong vi, byte[] buf, NativeLong count,NativeLong retCount);
}
