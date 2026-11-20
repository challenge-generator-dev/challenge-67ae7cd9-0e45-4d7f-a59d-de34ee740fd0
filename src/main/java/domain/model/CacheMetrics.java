package domain.model;

import java.util.LongSummaryStatistics;

public record CacheMetrics(
        String cacheName,
        long hitCount,
        long missCount,
        long loadSuccessCount,
        long loadFailureCount,
        long evictionCount,
        LongSummaryStatistics hitRate,
        LongSummaryStatistics missRate
) {
    public double getHitRatePercentage() {
        long total = hitCount + missCount;
        return total == 0 ? 0.0 : (double) hitCount / total * 100;
    }

    public double getMissRatePercentage() {
        long total = hitCount + missCount;
        return total == 0 ? 0.0 : (double) missCount / total * 100;
    }
}