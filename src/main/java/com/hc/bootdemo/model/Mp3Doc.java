package com.hc.bootdemo.model;

import java.util.Arrays;

/**
 * @desc
 * @Author：hanchuang
 * @Version 1.0
 * @Date：add on 16:15 2019/6/10
 */
public class Mp3Doc {

    private Integer mid;

    private byte[] mp3;

    private String name;

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public byte[] getMp3() {
        return mp3;
    }

    public void setMp3(byte[] mp3) {
        this.mp3 = mp3;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Mp3Doc{" +
                "mid=" + mid +
                ", mp3=" + Arrays.toString(mp3) +
                ", name='" + name + '\'' +
                '}';
    }
}
