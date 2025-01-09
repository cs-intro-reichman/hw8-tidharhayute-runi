public enum TesterQuestionEnum {
    USER_FOLLOWS("User Class, follows method", 8),
    USER_ADD_FOLLOWEE("User Class, addFollowee method", 8),
    USER_REMOVE_FOLLOWEE("User Class, removeFollowee method", 8),
    USER_COUNT_MUTUAL("User Class, countMutual method", 8),
    USER_IS_FRIEND_OF("User Class, isFriendOf method", 10),
    NETWORK_GET_USER("Network Class, getUser method", 10),
    NETWORK_ADD_USER("Network Class, addUser method", 10),
    NETWORK_ADD_FOLLOWEE("Network Class, addFollowee method", 10),
    NETWORK_RECOMMEND_WHO_TO_FOLLOW("Network Class, recommendWhoToFollow method", 10),
    NETWORK_MOST_POPULAR_USER("Network Class, mostPopularUser method", 10),
    NETWORK_TO_STRING("Network Class, toString method", 8);

    private final String question;
    private final int maxPoints;
    TesterQuestionEnum(String question, int maxPoints) {
        this.question = question;
        this.maxPoints = maxPoints;
    }

    public String getQuestion() {
        return this.question;
    }

    public int getMaxPoints() {
        return this.maxPoints;
    }
}