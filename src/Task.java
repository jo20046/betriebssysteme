class Task {

    private int startTime;
    private int duration;

    Task(int startTime, int duration) {
        setStartTime(startTime);
        setDuration(duration);
    }

    int getStartTime() {
        return startTime;
    }

    private void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    int getDuration() {
        return duration;
    }

    private void setDuration(int duration) {
        this.duration = duration;
    }
}
