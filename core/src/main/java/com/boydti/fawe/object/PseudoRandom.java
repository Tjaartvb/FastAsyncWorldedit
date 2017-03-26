package com.boydti.fawe.object;

public class PseudoRandom {

    public static PseudoRandom random = new PseudoRandom();

    private long state;

    public PseudoRandom() {
        this.state = System.nanoTime();
    }

    public PseudoRandom(final long state) {
        this.state = state;
    }

    public void setSeed(long state) {
        this.state = state;
    }

    public long nextLong() {
        final long a = this.state;
        this.state = this.xorShift64(a);
        return a;
    }

    public long xorShift64(long a) {
        a ^= (a << 21);
        a ^= (a >>> 35);
        a ^= (a << 4);
        return a;
    }

    public double nextDouble() {
        return Math.max(0, Math.min(1, Math.abs((double) nextLong() / Long.MAX_VALUE)));
    }

    public int random(final int n) {
        if (n == 1) {
            return 0;
        }
        final long r = ((this.nextLong() >>> 32) * n) >> 32;
        return (int) r;
    }

    public int nextInt(int i) {
        return random(i);
    }

    public int nextInt(int start, int end) {
        return nextInt(end - start + 1) + start;
    }
}
