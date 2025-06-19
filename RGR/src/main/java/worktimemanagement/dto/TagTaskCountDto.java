package worktimemanagement.dto;

public class TagTaskCountDto {
    private String tagName;
    private long taskCount;

    public TagTaskCountDto(String tagName, long taskCount) {
        this.tagName = tagName;
        this.taskCount = taskCount;
    }

    public String getTagName() {
        return tagName;
    }

    public long getTaskCount() {
        return taskCount;
    }
}
