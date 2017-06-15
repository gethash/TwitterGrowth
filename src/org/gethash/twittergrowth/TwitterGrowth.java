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
package org.gethash.twittergrowth;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gethash.twittergrowth.util.Configure;
import org.gethash.twittergrowth.util.Connection;
import org.gethash.twittergrowth.util.ListManager;
import org.gethash.twittergrowth.util.Query;
import org.gethash.twittergrowth.util.StoreManager;
import twitter4j.Twitter;
import twitter4j.TwitterException;

/**
 *
 * @author berk
 */
public class TwitterGrowth {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        List<Long> followingUsers = new ArrayList<>();
        List<Long> evaluatedUsers = new ArrayList<>();

        try {
            //load configuration properties
            Configure configure = new Configure();
            configure.loadProperties();

            //open Twitter connection
            Connection connection = new Connection();
            Twitter twitter = connection.createConnection(configure.getProperties());

            //try to load existing list
            StoreManager storeManager = new StoreManager();
            followingUsers = storeManager.loadArrayList(configure.getProperties().getProperty("storeEvaluatedUsers"));

            //check if workingSet is already determined
            if (followingUsers.isEmpty()) {
                //get IDs for configured users
                Query query = new Query();
                String[] twitterUsers = configure.getProperties().getProperty("twitterUsers").split(",");
                for (String twitterUser : twitterUsers) {
                    List<Long> workingSet;
                    workingSet = query.getUserFollowers(twitter, twitterUser);
                    workingSet.removeAll(followingUsers);
                    followingUsers.addAll(workingSet);
                }

                //cleaning determined IDs
                ListManager listManager = new ListManager();

                //removing yourself
                listManager.removeCurrentUser(twitter, followingUsers);
                //removing users already following you
                listManager.removeFollowers(twitter, followingUsers);
                //removing users you are already following
                listManager.removeFriends(twitter, followingUsers);
                
                //store list do store
                storeManager.storeArrayList(followingUsers, configure.getProperties().getProperty("storeEvaluatedUsers"));
            }

                
            System.out.println(twitter.getScreenName());

        } catch (TwitterException | IllegalStateException ex) {
            Logger.getLogger(TwitterGrowth.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
