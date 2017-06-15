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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gethash.twittergrowth.TwitterGrowth;

/**
 *
 * @author berk
 */
public class StoreManager {

    public List<Long> loadArrayList(String source) {
        List<Long> arrayList = new ArrayList<>();
        try {
            
            FileInputStream fis = new FileInputStream(source);
            ObjectInputStream ois = new ObjectInputStream(fis);
            arrayList = (List<Long>) ois.readObject();
            ois.close();
            
        } catch (IOException ioe) {
            Logger.getLogger(TwitterGrowth.class.getName()).log(Level.SEVERE, null, ioe);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StoreManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arrayList;
    }

    public void storeArrayList(List<Long> arrayList, String destination) {
        try {
            FileOutputStream fos = new FileOutputStream(destination);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(arrayList);
            oos.close();
        } catch (IOException ioe) {
            Logger.getLogger(TwitterGrowth.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }
}
