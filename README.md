# JSONFeed

JSONFeed is a library for parsing [JSON Feed](https://jsonfeed.org/) feeds.


## How To Use

To use it, you just need to pass the JSON string as follows:

```java
String jsonString = "{ ... }";
Feed feed = DefaultFeed.fromString(jsonString);
```

## Credits

Credit goes to Martin McCallion [(@devilgate)](https://github.com/devilgate) and his project [devilgate/pertwee](https://github.com/devilgate/pertwee) for inspiring this.
