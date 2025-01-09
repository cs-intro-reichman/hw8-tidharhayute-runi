public enum TesterMetadataMessagesEnum {
    TEST_CASE_NUMBER("Total number of test cases: "),
    PASSED("Passed: "),
    FAILED("Failed: "),
    ERRORS("Errors: "),
    TEST("Test"),
    QUESTION("Question: "),
    ALL_TESTS_ARE_PASSED("All test cases have passed");

    private final String message;
    TesterMetadataMessagesEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}