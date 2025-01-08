/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        for(int i=0; i < this.userCount; i++){
            if (name.equalsIgnoreCase(this.users[i].getName())  ) {
                return this.users[i];
            }
        }
        return null;
    }

    /** Adds a new user with the given name to this network.
     *  If ths network is full, does nothing and returns false;
     *  If the given name is already a user in this network, does nothing and returns false;
     *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        if (name == null || this.getUser(name) != null || userCount >= this.users.length) {
            return false;
        }

        this.users[userCount] = new User(name);
        userCount++;

        return true;
    }


    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        if (name1 == null || name2 == null) return false;
        if (name1.equalsIgnoreCase(name2)) return false;
        if (this.getUser(name1) == null || this.getUser(name2) == null) return false;

        if (!this.getUser(name1).follows(name2)) {
            this.getUser(name1).addFollowee(name2);
            return true;
        }

        return false;
    }

    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        User mostRecommendedUserToFollow = this.users[0];
        int mostRecommendedUserToFollowCount = 0;

        for (int i=1; i < userCount; i++) { //
            if (name == this.users[i].getName()) continue;

            int thisUserCount = this.users[i].countMutual(this.getUser(name));

            if ( thisUserCount > mostRecommendedUserToFollowCount ) {
                mostRecommendedUserToFollow = this.users[i];
                mostRecommendedUserToFollowCount = thisUserCount;
            }
        }

        return mostRecommendedUserToFollow.getName();
    }

    /** Computes and returns the name of the most popular user in this network:
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        if (this.users == null || this.users.length == 0 || userCount == 0) {
            return null;
        }

        if (this.users.length == 1 ) {
            return this.users[0].getName();
        }

        User mostPopular = this.users[0];
        int mostPopularUserCount = followeeCount(this.users[0].getName());

        for (int i=1; i < userCount; i++) {
            int thisUserFollowers = followeeCount(this.users[i].getName());

            if (thisUserFollowers > mostPopularUserCount) {
                mostPopular = this.users[i];
                mostPopularUserCount = thisUserFollowers;
            }
        }

        return mostPopular.getName();
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        int foCount = 0;

        for(int i=0; i < this.userCount; i++){
            if (this.users[i].follows(name)) foCount++;

        }

        return foCount;
    }


    public String wordToLower(String str){
        String ans = "";
        for(int i=0; i< str.length(); i++){
            ans += letterToLower(str.charAt(i));
        }
        return ans;
    }

    public static char letterToLower(char chr) {
        // If the character is uppercase, convert it to lowercase
        if ('A' <= chr && chr <= 'Z') {
            return (char) (chr + ('a' - 'A')); // Convert to lowercase
        }
        return chr; // Return as is for non-uppercase characters
    }

    public static char letterToUpper(char chr) {
        // If the character is lowercase, convert it to uppercase
        if ('a' <= chr && chr <= 'z') {
            return (char) (chr - ('a' - 'A')); // Shift ASCII value to uppercase
        }
        return chr; // Return as is for non-lowercase characters
    }

    public static String firstLetterToUpper(String str) {
        String uppercaseWord = "";
        uppercaseWord += letterToUpper(str.charAt(0)); // Initialize an empty string

        for (int i = 0; i < str.length()-1; i++) {
            // Convert each character to uppercase using letterToUpper
            uppercaseWord += str.charAt(i);
        }
        return uppercaseWord; // Return the resulting uppercase word
    }
    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
        if(this.users[0] == null){
            return "Network:";
        }

        String ans = "Network:" + "\n";
        ans = ans + this.users[0].toString();

        for(int i=1; i<this.userCount; i++){
            ans = ans + "\n" + this.users[i].toString();
        }

        return ans;
    }
}