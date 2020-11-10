# JSONFeed

JSONFeed is a library for creating and parsing [JSONFeed](https://jsonfeed.org/) feeds.


### Parsing a JSON Feed string:

```java
String jsonFeedString = "{ ... }";

// Parse the JSON feed string
Feed feed = DefaultFeed.fromString(jsonFeedString);

// Get some fields from the feed
Version version = feed.getVersion();
String title = feed.getTitle();
String description = feed.getDescription();
URL homePageUrl = feed.getHomePageUrl();
URL feedUrl = feed.getFeedUrl();

// Get the item list from the feed
List<Item> itemList = feed.getItemList()
```


### Creating a new JSON Feed:

```java
// Create a new item
Item item = new DefaultItem("1");
item.setTitle("First Item");
item.setSummary("First item summary.");
item.setUrl(new URL("https://somehost.com/article/1"));

// Create an item list and add the item
List<Item> itemList = new ArrayList<Item>();
itemList.add(item)

// Create a new feed
Feed feed = new DefaultFeed();

// Add some fields to the feed
feed.setTitle("Feed Title");
feed.setDescription("Feed Description");
feed.setHomePageUrl(new URL("https://somehost.com/"));
feed.setFeedUrl(new URL("https://somehost.com/feed.json"));

// Add the item list to the feed
feed.setItemList(itemList);

// Get the feed as a JSON feed string
String jsonFeedString = feed.toJSONString()
```


### Support:

This library supports both [JSONFeed](https://jsonfeed.org/) 1.0 and 1.1, and there is
support for 'upgrading' a feed from version 1.0 to 1.1. Additionally the parser will
inspect the feed and upgrade it if needed.



### Dependencies:

This depends on [JSON-java](https://github.com/stleary/JSON-java) for JSON parsing and creation.



## Credits

Thanks go to Martin McCallion [(@devilgate)](https://github.com/devilgate) and his [devilgate/pertwee](https://github.com/devilgate/pertwee) project for inspiring this.
