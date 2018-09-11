function formatLongSentence(data,maxSize) {
    if (data.length >= maxSize) {
        return data.substring(0, maxSize) + '...';
    }
    return data;
}