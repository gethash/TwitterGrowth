# TwitterGrowth

Adds followers of given Twitter-Screennames to your friendslist and checks if they follow you back in a while. Otherwise they will be removed after a configurable time.

## Configuration

You need to setup a twittergrowth.properties file in the source directory to run it. See the following example

```Java
# Basic Information
yourself=<your user>

# OAuth Credentials
oAuthConsumerKey=<your ConsumerKey>
oAuthConsumerSecret=<your ConsumerSecret>
oAuthAccessToken=<your AccessToken>
oAuthAccessTokenSecret=<your AccessTokenSecret>

# Persistent Stores
storeEvaluatedUsers=<file path to store evaluated users for further friendship>
storeAddedUsers=<file path to store added users for further removal>

# User Rate Limits
rateUserPerCycleFriend=<number of users to unfriend in a cyle in seconds>
rateUserPerCycleUnfried=<number of users to unfriend in a cyle in seconds>

# System Rate Limits
rateSystemCycleWait=<time to wait for the next add, remove cycle in seconds>

# User srouce
twitterUsers=<list of users as new friends list, seperated with ,>
´´´
