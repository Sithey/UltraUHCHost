/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package twitter4j;

import twitter4j.api.*;
import twitter4j.auth.OAuth2Support;
import twitter4j.auth.OAuthSupport;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.2.0
 */
public interface Twitter extends java.io.Serializable,
        OAuthSupport,
        OAuth2Support,
        TwitterBase,
        TimelinesResources,
        TweetsResources,
        SearchResource,
        DirectMessagesResources,
    FriendsFollowersResources,
        UsersResources,
        SuggestedUsersResources,
    FavoritesResources,
        ListsResources,
        SavedSearchesResources,
        PlacesGeoResources,
        TrendsResources,
        SpamReportingResource,
        HelpResources {

    /**
     * @return {@link TimelinesResources}
     * @since Twitter4J 3.0.4
     */
    TimelinesResources timelines();

    /**
     * @return {@link TweetsResources}
     * @since Twitter4J 3.0.4
     */
    TweetsResources tweets();

    /**
     * @return {@link SearchResource}
     * @since Twitter4J 3.0.4
     */
    SearchResource search();

    /**
     * @return {@link DirectMessagesResources}
     * @since Twitter4J 3.0.4
     */
    DirectMessagesResources directMessages();

    /**
     * @return {@link FriendsFollowersResources}
     * @since Twitter4J 3.0.4
     */
    FriendsFollowersResources friendsFollowers();

    /**
     * @return {@link UsersResources}
     * @since Twitter4J 3.0.4
     */
    UsersResources users();

    /**
     * @return {@link SuggestedUsersResources}
     * @since Twitter4J 3.0.4
     */
    SuggestedUsersResources suggestedUsers();

    /**
     * @return {@link FavoritesResources}
     * @since Twitter4J 3.0.4
     */
    FavoritesResources favorites();

    /**
     * @return {@link ListsResources}
     * @since Twitter4J 3.0.4
     */
    ListsResources list();

    /**
     * @return {@link SavedSearchesResources}
     * @since Twitter4J 3.0.4
     */
    SavedSearchesResources savedSearches();

    /**
     * @return {@link PlacesGeoResources}
     * @since Twitter4J 3.0.4
     */
    PlacesGeoResources placesGeo();

    /**
     * @return {@link TrendsResources}
     * @since Twitter4J 3.0.4
     */
    TrendsResources trends();

    /**
     * @return {@link SpamReportingResource}
     * @since Twitter4J 3.0.4
     */
    SpamReportingResource spamReporting();

    /**
     * @return {@link HelpResources}
     * @since Twitter4J 3.0.4
     */
    HelpResources help();
}
