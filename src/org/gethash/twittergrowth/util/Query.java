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

import java.util.ArrayList;
import java.util.List;
import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author berk
 */
public class Query {

    public List<Long> getUserFollowers(Twitter twitter, String screenName) throws TwitterException {
        List<Long> evaluatedUsers = new ArrayList<>();
        long cursor = -1;
        IDs ids;
        do {
            ids = twitter.getFollowersIDs(screenName, cursor);

            for (long id : ids.getIDs()) {
                evaluatedUsers.add(id);
            }
        } while ((cursor = ids.getNextCursor()) != 0);
        return evaluatedUsers;
    }
}
