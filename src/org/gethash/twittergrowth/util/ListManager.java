/*
 * Copyright (C) 2017 berk
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.gethash.twittergrowth.util;

import java.util.List;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author berk
 */
public class ListManager {

    public List<Long> removeCurrentUser(Twitter twitter, List<Long> workingSet) throws TwitterException {
        workingSet.remove(twitter.getId());
        return workingSet;
    }

    public List<Long> removeFollowers(Twitter twitter, List<Long> workingSet) throws TwitterException {
        long cursor = -1;
        IDs followerIds;
        do {
            followerIds = twitter.getFollowersIDs(twitter.getId(), cursor);
            for (long id : followerIds.getIDs()) {
                workingSet.remove(id);
            }
        } while ((cursor = followerIds.getNextCursor()) != 0);
        return workingSet;
    }

    public List<Long> removeFriends(Twitter twitter, List<Long> workingSet) throws TwitterException {
        long cursor = -1;
        IDs friendIds;
        do {
            friendIds = twitter.getFriendsIDs(twitter.getId(), cursor);
            for (long id : friendIds.getIDs()) {
                workingSet.remove(id);
            }
        } while ((cursor = friendIds.getNextCursor()) != 0);
        return workingSet;
    }
}
