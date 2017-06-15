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

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author berk
 */
public class Configure {

    Properties properties = new Properties();

    public void loadProperties() {

        InputStream input = null;

        try {

            String filename = "twittergrowth.properties";
            input = getClass().getClassLoader().getResourceAsStream(filename);
            if (input == null) {
                Logger.getLogger("Not able to to find " + filename);
                return;
            }
            properties.load(input);
        } catch (IOException ioe) {
            Logger.getLogger("Not able to load properties file " + ioe.toString());
            Logger.getLogger(Configure.class.getName()).log(Level.SEVERE, null, ioe);
        }
    }

    public Properties getProperties() {
        return properties;
    }

    public void print() {

        InputStream input = null;

        Enumeration<?> e = properties.propertyNames();
        while (e.hasMoreElements()) {
            String key = (String) e.nextElement();
            String value = properties.getProperty(key);
            System.out.println("Key : " + key + ", Value : " + value);
        }
    }
}
