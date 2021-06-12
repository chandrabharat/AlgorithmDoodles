import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

class Twitter {

    private class User {

        HashSet<Integer> following;
        List<Integer> newsFeed;
        List<Integer> myTweets;
        int userId;
        public User(int id) {
            following = new HashSet<>();
            newsFeed = new ArrayList<>();
            myTweets = new ArrayList<>();
            userId = id;
        }

    }


    /** Initialize your data structure here. */
    ArrayList<User> allUsers;
    public Twitter() {
        allUsers = new ArrayList<>();
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        boolean existingUser = false;
        for (int i = 0; i < allUsers.size(); i++) {
            User currUser = allUsers.get(i);
            if (currUser.userId == userId
                    || currUser.following.contains(userId)) {
                if (currUser.newsFeed.size() >= 10) {
                    currUser.newsFeed.remove(currUser.newsFeed.size() - 1);
                }
                if (currUser.userId == userId) {
                    currUser.myTweets.add(tweetId);
                    existingUser = true;
                }
                currUser.newsFeed.add(0, tweetId);
            }
        }
        if (!existingUser) {
            createUser(userId);
            allUsers.get(allUsers.size() - 1).newsFeed.add(tweetId);
            allUsers.get(allUsers.size() - 1).myTweets.add(tweetId);
        }
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        for (int i = 0; i < allUsers.size(); i++) {
            User currUser = allUsers.get(i);
            if (currUser.userId == userId) {
                return currUser.newsFeed;
            }
        }
        return null;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        boolean existingUser = false;
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).userId == followerId) {
                existingUser = true;
            }
        }
        if (!existingUser) {
            createUser(followerId);
        }
        for (int i = 0; i < allUsers.size(); i++) {
            User currUser = allUsers.get(i);
            if (currUser.userId == followerId) {
                currUser.following.add(followeeId);
            }
        }
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        for (int i = 0; i < allUsers.size(); i++) {
            User currUser = allUsers.get(i);
            if (currUser.userId == followerId) {
                currUser.following.remove(followeeId);
            }
        }
        remove(followerId, followeeId);
    }

    public void remove(int followerId, int followeeId) {
        User unFollowedUser = null;
        for (int i = 0; i < allUsers.size(); i++) {
            if (allUsers.get(i).userId == followeeId) {
                unFollowedUser = allUsers.get(i);
            }
        }
        for (int i = 0; i < allUsers.size(); i++) {
            User currUser = allUsers.get(i);
            if (currUser.userId == followerId) {
                int j = 0;
                while (j < currUser.newsFeed.size()) {
                    if (unFollowedUser.myTweets.contains(currUser.newsFeed.get(j))){
                        currUser.newsFeed.remove(j);
                    } else {
                        j++;
                    }
                }
            }
        }
    }

    public void createUser(int userId) {
        allUsers.add(new User(userId));
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */