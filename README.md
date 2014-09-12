EhCache's JSR107 support
===============================
See jsr107 annotation support utilized in [CustomerService](src/main/java/org/terracotta/demo/CustomerService.java)
JSR107

Also see basic [test case](src/test/java/org/terracotta/demo/test/CacheTest.java) that shows the following:

* use of [jsr 107 cache](https://github.com/jsr107/jsr107spec/blob/master/src/main/java/javax/cache/Cache.java) interface
* asserts that the [jsr107 @cache annotations](https://github.com/jsr107/jsr107spec/tree/master/src/main/java/javax/cache/annotation) are being utilized
* jsr107 [cache statistics Mbean](https://github.com/jsr107/jsr107spec/tree/master/src/main/java/javax/cache/management) is registered
* , and obviously that the [jsr107 caching provider](https://github.com/ehcache/ehcache-jcache) in this test case is indeed [EhCache](https://github.com/ehcache/ehcache-jcache) :)

