package com.common.lib.utils;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.List;

public final class SystemUtils {

    private SystemUtils() {
    }
    /**
     * 获取本机IP地址
     *
     * @return
     */
    public static String getLocalIpAddress() {
        try {
            String ipv4 = "";
            String ipv6 = "";
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        List<InterfaceAddress> listIa = intf.getInterfaceAddresses();
                        for (int i = 0; i < listIa.size(); i++) {
                            if (listIa.get(i).getNetworkPrefixLength() <= 32) { //IP V4
                                ipv4 = listIa.get(i).getAddress().getHostAddress();
                                return ipv4;
                            } else if (listIa.get(i).getNetworkPrefixLength() == 64) { //IP V6
                                ipv6 = listIa.get(i).getAddress().getHostAddress();
                            }
                        }
                    }
                }
            }
            if (!TextUtils.isEmpty(ipv6)) {
                return ipv6;
            }
        } catch (SocketException ex) {
        }
        return "";
    }


    public static final boolean is64BitCpu() {
        InputStream is = null;
        try {
            Runtime runtime = Runtime.getRuntime();
            Process proc = runtime.exec("cat /proc/cpuinfo");
            is = proc.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);

            BufferedReader br = new BufferedReader(isr);
            String line = br.readLine();
            if (line != null) {
                return line.contains("64");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is) {
                    is.close();
                }
            } catch (IOException e) {
            } finally {
                is = null;
            }
        }
        return false;
    }

}
