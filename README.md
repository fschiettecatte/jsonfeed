# JSONFeed

JSONFeed is a library for creatign and parsing [JSONFeed](https://jsonfeed.org/) feeds.


### Parsing a existing JSON Feed string:

```java
String jsonString = "{ ... }";

Feed feed = DefaultFeed.fromString(jsonString);
Version version = feed.getVersion();
String tite = feed.getTitle();
String description = feed.getDescription();
URL homePageUrl = feed.getHomePageUrl();
URL feedUrl = feed.getFeedUrl();

List<Item> itemList = feed.getItemList()
```


### Creating a new JSON Feed:

```java
Item item = new DefaultItem("1");
item.setTitle("First Item");
List<Item> itemList = new ArrayList<Item>();
itemList.add(item)

Feed feed = new DefaultFeed(Version.VERSION_1_1);
feed.setTitle("Feed Title");
feed.setDescription("Feed Description");
feed.setItemList(itemList);

String jsonString = feed.toJSONString()
```


### Support:

This library supports both [JSONFeed](https://jsonfeed.org/) 1.0 and 1.1, and there is support for 'upgrading' a feed from version 1.0 to 1.1.


### Dependencies:

This depends on [JSON-java](https://github.com/stleary/JSON-java) for JSON parsing and creation.



## Credits

Credit goes to Martin McCallion [(@devilgate)](https://github.com/devilgate) and his project [devilgate/pertwee](https://github.com/devilgate/pertwee) for inspiring this.
