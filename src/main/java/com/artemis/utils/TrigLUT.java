/**
 * This file is part of Artemis, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2014 SpongePowered <http://spongepowered.org/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.artemis.utils;

// Thanks to Riven
// From: http://riven8192.blogspot.com/2009/08/fastmath-sincos-lookup-tables.html
public class TrigLUT {
    public static void main(String[] args) {
        System.out.println(cos((float) Math.PI));
        System.out.println(cosDeg(180f));
    }

    public static final float sin(float rad) {
        return sin[(int) (rad * radToIndex) & SIN_MASK];
    }

    public static final float cos(float rad) {
        return cos[(int) (rad * radToIndex) & SIN_MASK];
    }

    public static final float sinDeg(float deg) {
        return sin[(int) (deg * degToIndex) & SIN_MASK];
    }

    public static final float cosDeg(float deg) {
        return cos[(int) (deg * degToIndex) & SIN_MASK];
    }

    private static final float RAD, DEG;
    private static final int SIN_BITS, SIN_MASK, SIN_COUNT;
    private static final float radFull, radToIndex;
    private static final float degFull, degToIndex;
    private static final float[] sin, cos;

    static {
        RAD = (float) Math.PI / 180.0f;
        DEG = 180.0f / (float) Math.PI;

        SIN_BITS = 12;
        SIN_MASK = ~(-1 << SIN_BITS);
        SIN_COUNT = SIN_MASK + 1;

        radFull = (float) (Math.PI * 2.0);
        degFull = (float) (360.0);
        radToIndex = SIN_COUNT / radFull;
        degToIndex = SIN_COUNT / degFull;

        sin = new float[SIN_COUNT];
        cos = new float[SIN_COUNT];

        for (int i = 0; i < SIN_COUNT; i++) {
            sin[i] = (float) Math.sin((i + 0.5f) / SIN_COUNT * radFull);
            cos[i] = (float) Math.cos((i + 0.5f) / SIN_COUNT * radFull);
        }
    }
}
