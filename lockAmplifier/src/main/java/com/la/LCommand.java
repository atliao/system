package com.la;

/**
 * @author LA
 * @createDate 2023-02-20-12:45
 */
public class LCommand {

    public static char LF = 10;

    //每次编程前都要用OUTX i命令，任何询问命令前，指定响应的接口(0:RS232, 1:GPIB)
    public static String OUTX = "OUTX 0" +LF;

    //查询设备ID
    public static String QID = "*IDN?" + LF;

    //查询参考源 0:内部参考，1:内部扫描，2:外部参考
    public static String QFMOD = "FMOD?" + LF;
    //设置参考源 0:内部参考，1:内部扫描，2:外部参考
    public static String FMOD = "FMOD <i>" + LF;

    //查询和设置参考源的相位改变量
    public static String QPASH = "PASH?" + LF;
    public static String PASH = "PASH <x>" + LF;

    //查询参考源频率(HZ)
    public static String QFREQ = "FREQ?" + LF;
    //设置内部振动器的频率，只有在内部参考源模式下可以使用(Hz)
    public static String FREQ = "FREQ <f>" + LF;

    //查询和设置正弦信号输出的振幅（VL的振幅，单位 V）
    public static String QSLVL = "SLVL?" + LF;
    public static String SLVL = "SLVL <x>" + LF;

    //查询和设置 输入情况（0：A，1：A-B，2：I）
    public static String QISRC = "ISRC?" + LF;
    public static String ISRC = "ISRC <i>" + LF;

    //查询输入信号的电压（R）（Vsig的振幅）（在相位相同的情况下，也可以查询 X ：VsigCosΘ）
    public static String QOUTP = "OUTP? 3" + LF;

    //查询和设置CH1或CH2的输出：可选择X(Y)，R等
    public static String QFOUT = "FOUT? <ch>" + LF;
    public static String FOUT = "FOUT <ch>, <i>" + LF;

    //查询和设置本地和远程功能(0:本地模式, 1:远程模式, 2:LOCAL LOCKOUT
    public static String QLOCL = "LOCL?" + LF;
    public static String LOCL = "LOCL <i>" + LF;
}
